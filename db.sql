DROP TABLE IF EXISTS GameStats CASCADE;
DROP TABLE IF EXISTS PlayerHero CASCADE;
DROP TABLE IF EXISTS Game CASCADE;
DROP TABLE IF EXISTS Match CASCADE;
DROP TABLE IF EXISTS Player CASCADE;
DROP TABLE IF EXISTS Team CASCADE;
DROP TABLE IF EXISTS Tournament CASCADE;
DROP TABLE IF EXISTS Hero CASCADE;
DROP TABLE IF EXISTS Item CASCADE;
DROP TABLE IF EXISTS GameItemStats CASCADE;
DROP TABLE IF EXISTS MatchType CASCADE;

-- Сущность: Турнир (Tournament)
CREATE TABLE Tournament (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    prize_pool NUMERIC(15, 2) NOT NULL,
    organizer VARCHAR(255)
);

-- Сущность: Команда (Team)
CREATE TABLE Team (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    region VARCHAR(100),
    rating NUMERIC(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    logo_url TEXT
);

-- Сущность: Игрок (Player)
CREATE TABLE Player (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    real_name VARCHAR(255),
    team_id INT REFERENCES Team(id) ON DELETE SET NULL,
    role VARCHAR(50),
    country VARCHAR(100)
);

CREATE TABLE MatchType (
    id SERIAL PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL UNIQUE -- Название типа матча (например, 'Group', 'Quarterfinal', 'Final')
);

-- Сущность: Матч (Match)
CREATE TABLE Match (
    id SERIAL PRIMARY KEY,
    tournament_id INT REFERENCES Tournament(id) ON DELETE CASCADE,
    team_radiant_id INT REFERENCES Team(id) ON DELETE CASCADE,
    team_dire_id INT REFERENCES Team(id) ON DELETE CASCADE,
	match_type_id INT REFERENCES MatchType(id) ON DELETE SET NULL,
    best_of INT NOT NULL CHECK (best_of IN (1, 3, 5)), -- Формат BO1, BO3, BO5
    match_date DATE NOT NULL
);

-- Сущность: Игра (Game)
CREATE TABLE Game (
    id SERIAL PRIMARY KEY,
    match_id INT REFERENCES Match(id) ON DELETE CASCADE,
    duration INT NOT NULL, -- Длительность в минутах
    winner VARCHAR(10) NOT NULL CHECK (winner IN ('Radiant', 'Dire')),
    start_time TIMESTAMP NOT NULL
);

-- Сущность: Герой (Hero)
CREATE TABLE Hero (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    primary_attribute VARCHAR(50) NOT NULL CHECK (primary_attribute IN ('Strength', 'Agility', 'Intelligence', 'Universal')),
    attack_type VARCHAR(10) NOT NULL CHECK (attack_type IN ('Ranged', 'Melee')),
    roles TEXT NOT NULL -- Список ролей героя (например, 'Carry, Support')
);

-- Сущность: PlayerHero (многие ко многим между Player и Hero)
CREATE TABLE PlayerHero (
    id SERIAL PRIMARY KEY,
    player_id INT REFERENCES Player(id) ON DELETE CASCADE,
    hero_id INT REFERENCES Hero(id) ON DELETE CASCADE,
    games_played INT NOT NULL DEFAULT 1
);

-- Сущность: Статистика Игры (GameStats)
CREATE TABLE GameStats (
    id SERIAL PRIMARY KEY,
    game_id INT REFERENCES Game(id) ON DELETE CASCADE,
    player_hero_id INT REFERENCES PlayerHero(id) ON DELETE CASCADE,
    kills INT NOT NULL DEFAULT 0,
    deathes INT NOT NULL DEFAULT 0,
    assists INT NOT NULL DEFAULT 0,
    last_hits INT NOT NULL DEFAULT 0,
    gold_per_minute INT NOT NULL DEFAULT 0,
    xp_per_minute INT NOT NULL DEFAULT 0,
    net_worth NUMERIC(15, 2) NOT NULL DEFAULT 0,
	final_level INT NOT NULL DEFAULT 1 CHECK (final_level <= 30)
);

-- Сущность: Предмет (Item)
CREATE TABLE Item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cost INT, -- Стоимость предмета
    description TEXT -- Описание предмета
);

-- Сущность: Статистика предметов в игре (GameItemStats)
CREATE TABLE GameItemStats (
    id SERIAL PRIMARY KEY,
    game_stats_id INT REFERENCES GameStats(id) ON DELETE CASCADE,
    item_id INT REFERENCES Item(id) ON DELETE CASCADE,
    quantity INT NOT NULL DEFAULT 1
);

CREATE OR REPLACE VIEW GameWithScores AS
SELECT 
    g.id AS game_id,
    g.match_id,
    g.duration,
    g.winner,
    g.start_time,
    -- Считаем radiant_score и dire_score на основе данных из GameStats
    COALESCE(SUM(CASE WHEN ph.player_id IN (SELECT player_id FROM Player WHERE team_id = m.team_radiant_id) THEN gs.kills ELSE 0 END), 0) AS radiant_score,
    COALESCE(SUM(CASE WHEN ph.player_id IN (SELECT player_id FROM Player WHERE team_id = m.team_dire_id) THEN gs.kills ELSE 0 END), 0) AS dire_score
FROM 
    Game g
LEFT JOIN GameStats gs ON g.id = gs.game_id
LEFT JOIN PlayerHero ph ON gs.player_hero_id = ph.id
LEFT JOIN Match m ON g.match_id = m.id
GROUP BY g.id, g.match_id, g.duration, g.winner, g.start_time;



CREATE OR REPLACE VIEW PlayerHeroPerformance AS
SELECT 
    ph.id AS player_hero_id,
    ph.player_id,
    ph.hero_id,
    ph.games_played,
    COALESCE(AVG(gs.kills + gs.assists - gs.deathes) / NULLIF(ph.games_played, 0), 0) AS average_performance -- Средняя производительность с учетом смертей
FROM 
    PlayerHero ph
LEFT JOIN 
    GameStats gs ON ph.id = gs.player_hero_id
GROUP BY 
    ph.id, ph.player_id, ph.hero_id, ph.games_played;

CREATE OR REPLACE VIEW MatchResults AS
SELECT 
    m.id AS match_id,
    CASE 
        WHEN COUNT(g.id) = 0 THEN 'No Result'
        WHEN MAX(g.winner) = 'Radiant' THEN t_r.name
        WHEN MAX(g.winner) = 'Dire' THEN t_d.name
    END AS result
FROM 
    Match m
LEFT JOIN 
    Game g ON m.id = g.match_id
LEFT JOIN 
    Team t_r ON m.team_radiant_id = t_r.id
LEFT JOIN 
    Team t_d ON m.team_dire_id = t_d.id
GROUP BY 
    m.id, t_r.name, t_d.name;



-- Индексы для улучшения производительности
CREATE INDEX idx_match_tournament ON Match(tournament_id);
CREATE INDEX idx_game_match ON Game(match_id);
CREATE INDEX idx_player_team ON Player(team_id);
CREATE INDEX idx_gamestats_game ON GameStats(game_id);
CREATE INDEX idx_gamestats_playerhero ON GameStats(player_hero_id);
CREATE INDEX idx_gameitemstats_game ON GameItemStats(game_stats_id);
CREATE INDEX idx_gameitemstats_item ON GameItemStats(item_id);
CREATE INDEX idx_playerhero_player ON PlayerHero(player_id);
CREATE INDEX idx_playerhero_hero ON PlayerHero(hero_id);