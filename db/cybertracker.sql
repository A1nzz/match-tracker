DROP TABLE IF EXISTS game_stats CASCADE;
DROP TABLE IF EXISTS player_hero CASCADE;
DROP TABLE IF EXISTS game CASCADE;
DROP TABLE IF EXISTS match CASCADE;
DROP TABLE IF EXISTS player CASCADE;
DROP TABLE IF EXISTS team CASCADE;
DROP TABLE IF EXISTS tournament CASCADE;
DROP TABLE IF EXISTS hero CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS game_item_stats CASCADE;
DROP TABLE IF EXISTS match_type CASCADE;
DROP TABLE IF EXISTS admin CASCADE;

CREATE TABLE public.admin (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    created_at timestamp(6) without time zone,
    email character varying(255),
    last_login timestamp(6) without time zone,
    password_hash character varying(255),
    username character varying(255)
);

CREATE TABLE public.team (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    created_at timestamp(6) without time zone,
    logo_url character varying(255),
    name character varying(255),
    rating double precision,
    region character varying(255)
);

CREATE TABLE public.tournament (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    end_date date,
    name character varying(255),
    organizer character varying(255),
    prize_pool double precision,
    start_date date
);

CREATE TABLE public.hero (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    attack_type character varying(255),
    logo_url character varying(255),
    name character varying(255),
    primary_attribute character varying(255),
    roles character varying(255)
);

CREATE TABLE public.item (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    cost integer,
    description character varying(255),
    logo_url character varying(255),
    name character varying(255)
);

CREATE TABLE public.match_type (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    type_name character varying(255)
);

CREATE TABLE public.match (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    best_of integer,
    match_date date,
    match_type_id bigint REFERENCES public.match_type(id) ON DELETE SET NULL,
    team_dire_id bigint REFERENCES public.team(id) ON DELETE CASCADE,
    team_radiant_id bigint REFERENCES public.team(id) ON DELETE CASCADE,
    tournament_id bigint REFERENCES public.tournament(id) ON DELETE CASCADE
);

CREATE TABLE public.player (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    country character varying(255),
    nickname character varying(255),
    real_name character varying(255),
    role character varying(255),
    team_id bigint REFERENCES public.team(id) ON DELETE SET NULL
);

CREATE TABLE public.player_hero (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    games_played integer,
    hero_id bigint REFERENCES public.hero(id) ON DELETE CASCADE,
    player_id bigint REFERENCES public.player(id) ON DELETE CASCADE
);

CREATE TABLE public.game (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    duration integer,
    start_time timestamp(6) without time zone,
    winner character varying(255),
    match_id bigint REFERENCES public.match(id) ON DELETE CASCADE
);

CREATE TABLE public.game_stats (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    assists integer,
    deaths integer,
    final_level integer,
    gold_per_minute integer,
    kills integer,
    last_hits integer,
    net_worth double precision,
    xp_per_minute integer,
    game_id bigint REFERENCES public.game(id) ON DELETE CASCADE,
    player_hero_id bigint REFERENCES public.player_hero(id) ON DELETE CASCADE
);

CREATE TABLE public.game_item_stats (
    id bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    quantity integer,
    game_stats_id bigint REFERENCES public.game_stats(id) ON DELETE CASCADE,
    item_id bigint REFERENCES public.item(id) ON DELETE CASCADE
);

CREATE OR REPLACE VIEW game_with_scores AS
SELECT 
    g.id AS game_id,
    g.match_id,
    g.duration,
    g.winner,
    g.start_time,
    -- Считаем radiant_score и dire_score на основе данных из GameStats
    COALESCE(SUM(CASE WHEN ph.player_id IN (SELECT player_id FROM player WHERE team_id = m.team_radiant_id) THEN gs.kills ELSE 0 END), 0) AS radiant_score,
    COALESCE(SUM(CASE WHEN ph.player_id IN (SELECT player_id FROM player WHERE team_id = m.team_dire_id) THEN gs.kills ELSE 0 END), 0) AS dire_score
FROM 
    game g
LEFT JOIN game_stats gs ON g.id = gs.game_id
LEFT JOIN player_hero ph ON gs.player_hero_id = ph.id
LEFT JOIN Match m ON g.match_id = m.id
GROUP BY g.id, g.match_id, g.duration, g.winner, g.start_time;



CREATE OR REPLACE VIEW player_hero_performance AS
SELECT 
    ph.id AS player_hero_id,
    ph.player_id,
    ph.hero_id,
    ph.games_played,
    COALESCE(AVG(gs.kills + gs.assists - gs.deaths) / NULLIF(ph.games_played, 0), 0) AS average_performance -- Средняя производительность с учетом смертей
FROM 
    player_hero ph
LEFT JOIN 
    game_stats gs ON ph.id = gs.player_hero_id
GROUP BY 
    ph.id, ph.player_id, ph.hero_id, ph.games_played;

CREATE OR REPLACE VIEW match_results AS
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
    game g ON m.id = g.match_id
LEFT JOIN 
    team t_r ON m.team_radiant_id = t_r.id
LEFT JOIN 
    team t_d ON m.team_dire_id = t_d.id
GROUP BY 
    m.id, t_r.name, t_d.name;

CREATE OR REPLACE VIEW match_with_wins AS
SELECT 
    m.id AS match_id,
    m.best_of,
    m.match_date,
    m.match_type_id,
    m.team_dire_id,
    m.team_radiant_id,
    m.tournament_id,
    COALESCE(SUM(CASE WHEN g.winner = 'Radiant' THEN 1 ELSE 0 END), 0) AS radiant_score,
    COALESCE(SUM(CASE WHEN g.winner = 'Dire' THEN 1 ELSE 0 END), 0) AS dire_score
FROM 
    public.match m
LEFT JOIN 
    public.game g ON m.id = g.match_id
GROUP BY 
    m.id, m.best_of, m.match_date, m.match_type_id, m.team_dire_id, m.team_radiant_id, m.tournament_id;
