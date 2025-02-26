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

-- Сущность: Матч (Match)
CREATE TABLE Match (
    id SERIAL PRIMARY KEY,
    tournament_id INT REFERENCES Tournament(id) ON DELETE CASCADE,
    team_radiant_id INT REFERENCES Team(id) ON DELETE CASCADE,
    team_dire_id INT REFERENCES Team(id) ON DELETE CASCADE,
    best_of INT NOT NULL CHECK (best_of IN (1, 3, 5)), -- Формат BO1, BO3, BO5
    result VARCHAR(10),
    match_date DATE NOT NULL
);

-- Сущность: Игра (Game)
CREATE TABLE Game (
    id SERIAL PRIMARY KEY,
    match_id INT REFERENCES Match(id) ON DELETE CASCADE,
    duration INT NOT NULL, -- Длительность в минутах
    winner VARCHAR(10) NOT NULL CHECK (winner IN ('Radiant', 'Dire')),
    radiant_score INT NOT NULL,
    dire_score INT NOT NULL,
    start_time TIMESTAMP NOT NULL
);

-- Сущность: Герой (Hero)
CREATE TABLE Hero (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    primary_attribute VARCHAR(50) NOT NULL CHECK (primary_attribute IN ('Strength', 'Agility', 'Intelligence')),
    attack_type VARCHAR(10) NOT NULL CHECK (attack_type IN ('Ranged', 'Melee')),
    roles TEXT NOT NULL -- Список ролей героя (например, 'Carry, Support')
);

-- Сущность: Статистика Игры (GameStats)
CREATE TABLE GameStats (
    id SERIAL PRIMARY KEY,
    game_id INT REFERENCES Game(id) ON DELETE CASCADE,
    player_id INT REFERENCES Player(id) ON DELETE CASCADE,
    hero_id INT REFERENCES Hero(id) ON DELETE CASCADE,
    kills INT NOT NULL DEFAULT 0,
    deaths INT NOT NULL DEFAULT 0,
    assists INT NOT NULL DEFAULT 0,
    last_hits INT NOT NULL DEFAULT 0,
    gold_per_minute INT NOT NULL DEFAULT 0,
    xp_per_minute INT NOT NULL DEFAULT 0,
    net_worth NUMERIC(15, 2) NOT NULL DEFAULT 0
);

-- Сущность: PlayerHero (многие ко многим между Player и Hero)
CREATE TABLE PlayerHero (
    id SERIAL PRIMARY KEY,
    player_id INT REFERENCES Player(id) ON DELETE CASCADE,
    hero_id INT REFERENCES Hero(id) ON DELETE CASCADE,
    games_played INT NOT NULL DEFAULT 1,                -- Сколько раз игрок выбирал этого героя
    average_performance NUMERIC(5, 2) DEFAULT 0.0       -- Средняя производительность игрока на этом герое
);

-- Индексы для улучшения производительности
CREATE INDEX idx_match_tournament ON Match(tournament_id);
CREATE INDEX idx_game_match ON Game(match_id);
CREATE INDEX idx_player_team ON Player(team_id);
CREATE INDEX idx_gamestats_game ON GameStats(game_id);
CREATE INDEX idx_gamestats_player ON GameStats(player_id);
CREATE INDEX idx_gamestats_hero ON GameStats(hero_id);
CREATE INDEX idx_playerhero_player ON PlayerHero(player_id);
CREATE INDEX idx_playerhero_hero ON PlayerHero(hero_id);