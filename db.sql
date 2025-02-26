-- Создание таблицы Игрок
CREATE TABLE players (
    player_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    age INT CHECK (age > 0),
    country VARCHAR(100) NOT NULL
);

-- Создание таблицы Команда
CREATE TABLE teams (
    team_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    region VARCHAR(100),
    founded_date DATE
);

-- Создание таблицы Игра
CREATE TABLE games (
    game_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(100),
    developer VARCHAR(100)
);

-- Создание таблицы Турнир
CREATE TABLE tournaments (
    tournament_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    prize_pool DECIMAL(15, 2)
);

-- Создание таблицы Матч
CREATE TABLE matches (
    match_id SERIAL PRIMARY KEY,
    match_date TIMESTAMP NOT NULL,
    result VARCHAR(100),
    tournament_id INT REFERENCES tournaments(tournament_id)
);

-- Создание таблицы Коуч
CREATE TABLE coaches (
    coach_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    experience VARCHAR(100),
    team_id INT REFERENCES teams(team_id)
);

-- Создание таблицы Спонсор
CREATE TABLE sponsors (
    sponsor_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    sponsorship_amount DECIMAL(15, 2)
);

-- Создание таблицы для связи многие-ко-многим между Игроками и Командами
CREATE TABLE player_team (
    player_id INT REFERENCES players(player_id),
    team_id INT REFERENCES teams(team_id),
    PRIMARY KEY (player_id, team_id)
);

-- Создание таблицы для связи многие-ко-многим между Спонсорами и Турнирами
CREATE TABLE sponsor_tournament (
    sponsor_id INT REFERENCES sponsors(sponsor_id),
    tournament_id INT REFERENCES tournaments(tournament_id),
    PRIMARY KEY (sponsor_id, tournament_id)
);