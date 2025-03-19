# Match Tracker

Целью данного проекта является создание системы отслеживания информации о командах, игроках, матчах и статистике профессинальных матчей по игре Dota 2. Платформа обеспечит удобный просмотр и анализ профессиональных матчей.

## Основные сущности
### 1. Турнир (Tournament)
* Описание турнира, включая его название, даты проведения и призовой фонд.
* Организатор турнира.
### 2. Команда (Team)
* Информация о командах, участвующих в турнире, включая название, регион и рейтинг.
* Логотип команды
### 3. Игрок (Player)
* Данные об игроках, включая никнейм, реальное имя, команду, роль и страну.
### 4. Матч (Match)
* Записи о матчах, включая информацию о турнире, участвующих командах, формате матча и результате.
### 5. Игра (Game)
* Статистика каждой игры, включая продолжительность, победителя и счет.
### 6. Герой (Hero)
* Информация о героях, их атрибутах и ролях в игре.
### 7. Статистика Игры (GameStats)
* Подробная статистика для каждого игрока в каждой игре, включая количество убийств, смертей, ассистов и другие метрики.
### 8. PlayerHero
* Связь между игроками и героями, показывающая, сколько игр игрок выбрал конкретного героя и его среднюю производительность.

## Task completion time estimate

| |Этап|По плану|По факту|
|---|-----|-----|-----|
|1|DB|2.5|3|
|2|JDBC|6|8|
|3|JSP|25|30|
|4|Spring|10|11|
|5|React|14|17|
|6|Angular|18|18|
|*|Total|75.5|87|
