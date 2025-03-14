-- 1. Загружаем команды, в которых нет зависимостей
COPY tournament FROM 'D:/saved/tournament.csv' DELIMITER ',' CSV HEADER;
COPY team FROM 'D:/saved/team.csv' DELIMITER ',' CSV HEADER;
COPY match_type FROM 'D:/saved/match_type.csv' DELIMITER ',' CSV HEADER;
COPY hero FROM 'D:/saved/hero.csv' DELIMITER ',' CSV HEADER;
COPY item FROM 'D:/saved/item.csv' DELIMITER ',' CSV HEADER;

-- 2. Загружаем таблицы, которые зависят от загруженных ранее
COPY player FROM 'D:/saved/player.csv' DELIMITER ',' CSV HEADER;
COPY match FROM 'D:/saved/match.csv' DELIMITER ',' CSV HEADER;
COPY game FROM 'D:/saved/game.csv' DELIMITER ',' CSV HEADER;

-- 3. Загружаем таблицы, которые содержат статистику и также зависят от загруженных ранее
COPY player_hero FROM 'D:/saved/player_hero.csv' DELIMITER ',' CSV HEADER;
COPY game_stats FROM 'D:/saved/game_stats.csv' DELIMITER ',' CSV HEADER;
COPY game_item_stats FROM 'D:/saved/game_item_stats.csv' DELIMITER ',' CSV HEADER;
