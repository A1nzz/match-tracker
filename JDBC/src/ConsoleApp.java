import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TournamentDAO tournamentDAO = new TournamentDAO();
        TeamDAO teamDAO = new TeamDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        MatchDAO matchDAO = new MatchDAO();
        GameDAO gameDAO = new GameDAO();
        HeroDAO heroDAO = new HeroDAO();
        GameStatsDAO gameStatsDAO = new GameStatsDAO();
        PlayerHeroDAO playerHeroDAO = new PlayerHeroDAO();
        ItemDAO itemDAO = new ItemDAO();
        GameItemStatsDAO gameItemStatsDAO = new GameItemStatsDAO();
        MatchTypeDAO matchTypeDAO = new MatchTypeDAO();


        int choice;
        do {
            System.out.println("Выберите сущность:");
            System.out.println("1. Турнир");
            System.out.println("2. Команда");
            System.out.println("3. Игрок");
            System.out.println("4. Матч");
            System.out.println("5. Игра");
            System.out.println("6. Герой");
            System.out.println("7. Статистика Игры");
            System.out.println("8. PlayerHero");
            System.out.println("9. Предмет");
            System.out.println("10. GameItemStats");
            System.out.println("11. Тип матча");
            System.out.println("12. Выход");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleTournament(scanner, tournamentDAO);
                    break;
                case 2:
                    handleTeam(scanner, teamDAO);
                    break;
                case 3:
                    handlePlayer(scanner, playerDAO);
                    break;
                case 4:
                    handleMatch(scanner, matchDAO);
                    break;
                case 5:
                    handleGame(scanner, gameDAO);
                    break;
                case 6:
                    handleHero(scanner, heroDAO);
                    break;
                case 7:
                    handleGameStats(scanner, gameStatsDAO);
                    break;
                case 8:
                    handlePlayerHero(scanner, playerHeroDAO);
                    break;
                case 9:
                    handleItem(scanner, itemDAO);
                    break;
                case 10:
                    handleGameItemStats(scanner, gameItemStatsDAO);
                    break;
                case 11:
                    handleMatchType(scanner, matchTypeDAO);
                    break;
                case 12:
                    System.out.println("Выход из приложения...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void handleTournament(Scanner scanner, TournamentDAO tournamentDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Турниром:");
            System.out.println("1. Добавить турнир");
            System.out.println("2. Просмотреть все турниры");
            System.out.println("3. Обновить турнир");
            System.out.println("4. Удалить турнир");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createTournament(scanner, tournamentDAO);
                    break;
                case 2:
                    readTournaments(tournamentDAO);
                    break;
                case 3:
                    updateTournament(scanner, tournamentDAO);
                    break;
                case 4:
                    deleteTournament(scanner, tournamentDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createTournament(Scanner scanner, TournamentDAO tournamentDAO) {
        scanner.nextLine();
        System.out.print("Введите название турнира: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату начала (YYYY-MM-DD): ");
        Date startDate = Date.valueOf(scanner.nextLine());
        System.out.print("Введите дату окончания (YYYY-MM-DD): ");
        Date endDate = Date.valueOf(scanner.nextLine());
        System.out.print("Введите призовой фонд (например, 15000.50): ");
        double prizePool = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Введите организатора турнира: ");
        String organizer = scanner.nextLine();

        tournamentDAO.createTournament(name, startDate.toString(), endDate.toString(), prizePool, organizer);
        System.out.println("Турнир успешно добавлен!");
    }

    private static void readTournaments(TournamentDAO tournamentDAO) {

        List<String> tournaments = tournamentDAO.getAllTournaments();
        if (tournaments.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Турниры:");
            for (String tournament : tournaments) {
                System.out.println(tournament);
            }
        }
    }

    private static void updateTournament(Scanner scanner, TournamentDAO tournamentDAO) {
        System.out.print("Введите ID турнира для обновления: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите новое название турнира: ");
        String name = scanner.nextLine();
        System.out.print("Введите новую дату начала (YYYY-MM-DD): ");
        Date startDate = Date.valueOf(scanner.nextLine());
        System.out.print("Введите новую дату окончания (YYYY-MM-DD): ");
        Date endDate = Date.valueOf(scanner.nextLine());
        System.out.print("Введите новый призовой фонд (например, 16000.75): ");
        double prizePool = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Введите нового организатора турнира: ");
        String organizer = scanner.next();

        tournamentDAO.updateTournament(id, name, startDate.toString(), endDate.toString(), prizePool, organizer);
        System.out.println("Турнир успешно обновлен!");
    }

    private static void deleteTournament(Scanner scanner, TournamentDAO tournamentDAO) {
        System.out.print("Введите ID турнира для удаления: ");
        int id = scanner.nextInt();
        tournamentDAO.deleteTournament(id);
        System.out.println("Турнир успешно удален!");

    }

    private static void handleTeam(Scanner scanner, TeamDAO teamDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Командой:");
            System.out.println("1. Добавить команду");
            System.out.println("2. Просмотреть все команды");
            System.out.println("3. Обновить команду");
            System.out.println("4. Удалить команду");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createTeam(scanner, teamDAO);
                    break;
                case 2:
                    readTeams(teamDAO);
                    break;
                case 3:
                    updateTeam(scanner, teamDAO);
                    break;
                case 4:
                    deleteTeam(scanner, teamDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createTeam(Scanner scanner, TeamDAO teamDAO) {
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите название команды: ");
        String name = scanner.nextLine();
        System.out.print("Введите регион команды: ");
        String region = scanner.nextLine();
        System.out.print("Введите рейтинг команды: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите URL логотипа команды: ");
        String logoUrl = scanner.nextLine();

        teamDAO.createTeam(name, region, rating, logoUrl);
        System.out.println("Команда успешно добавлена!");
    }

    private static void readTeams(TeamDAO teamDAO) {
        List<String> teams = teamDAO.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Команды:");
            for (String team : teams) {
                System.out.println(team);
            }
        }
    }

    private static void updateTeam(Scanner scanner, TeamDAO teamDAO) {
        System.out.print("Введите ID команды для обновления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новое название команды: ");
        String name = scanner.nextLine();
        System.out.print("Введите новый регион команды: ");
        String region = scanner.nextLine();
        System.out.print("Введите новый рейтинг команды: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новый URL логотипа команды: ");
        String logoUrl = scanner.nextLine();

        teamDAO.updateTeam(id, name, region, rating, logoUrl);
        System.out.println("Команда успешно обновлена!");
    }

    private static void deleteTeam(Scanner scanner, TeamDAO teamDAO) {
        System.out.print("Введите ID команды для удаления: ");
        int id = scanner.nextInt();
        teamDAO.deleteTeam(id);
        System.out.println("Команда успешно удалена!");
    }

    private static void handlePlayer(Scanner scanner, PlayerDAO playerDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Игроком:");
            System.out.println("1. Добавить игрока");
            System.out.println("2. Просмотреть всех игроков");
            System.out.println("3. Обновить игрока");
            System.out.println("4. Удалить игрока");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createPlayer(scanner, playerDAO);
                    break;
                case 2:
                    readPlayers(playerDAO);
                    break;
                case 3:
                    updatePlayer(scanner, playerDAO);
                    break;
                case 4:
                    deletePlayer(scanner, playerDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createPlayer(Scanner scanner, PlayerDAO playerDAO) {
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите никнейм игрока: ");
        String nickname = scanner.nextLine();
        System.out.print("Введите реальное имя игрока: ");
        String realName = scanner.nextLine();
        System.out.print("Введите ID команды (или 0, если нет команды): ");
        int teamId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите роль игрока: ");
        String role = scanner.nextLine();
        System.out.print("Введите страну игрока: ");
        String country = scanner.nextLine();

        playerDAO.createPlayer(nickname, realName, teamId == 0 ? null : teamId, role, country);
        System.out.println("Игрок успешно добавлен!");
    }

    private static void readPlayers(PlayerDAO playerDAO) {
        List<String> players = playerDAO.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Игроки:");
            for (String player : players) {
                System.out.println(player);
            }
        }
    }

    private static void updatePlayer(Scanner scanner, PlayerDAO playerDAO) {
        System.out.print("Введите ID игрока для обновления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новый никнейм игрока: ");
        String nickname = scanner.nextLine();
        System.out.print("Введите новое реальное имя игрока: ");
        String realName = scanner.nextLine();
        System.out.print("Введите новый ID команды (или 0, если нет команды): ");
        int teamId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новую роль игрока: ");
        String role = scanner.nextLine();
        System.out.print("Введите новую страну игрока: ");
        String country = scanner.nextLine();

        playerDAO.updatePlayer(id, nickname, realName, teamId == 0 ? null : teamId, role, country);
        System.out.println("Игрок успешно обновлен!");
    }

    private static void deletePlayer(Scanner scanner, PlayerDAO playerDAO) {
        System.out.print("Введите ID игрока для удаления: ");
        int id = scanner.nextInt();
        playerDAO.deletePlayer(id);
        System.out.println("Игрок успешно удален!");
    }

    private static void handleMatch(Scanner scanner, MatchDAO matchDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Матчем:");
            System.out.println("1. Добавить матч");
            System.out.println("2. Просмотреть все матчи");
            System.out.println("3. Обновить матч");
            System.out.println("4. Удалить матч");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createMatch(scanner, matchDAO);
                    break;
                case 2:
                    readMatches(matchDAO);
                    break;
                case 3:
                    updateMatch(scanner, matchDAO);
                    break;
                case 4:
                    deleteMatch(scanner, matchDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createMatch(Scanner scanner, MatchDAO matchDAO) {
        System.out.print("Введите ID турнира: ");
        int tournamentId = scanner.nextInt();
        System.out.print("Введите ID команды Radiant: ");
        int teamRadiantId = scanner.nextInt();
        System.out.print("Введите ID команды Dire: ");
        int teamDireId = scanner.nextInt();
        System.out.print("Введите формат матча (1, 3 или 5): ");
        int bestOf = scanner.nextInt();
        System.out.print("Введите идентификатор типа матча");
        int matchTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите дату матча (YYYY-MM-DD): ");
        String matchDateStr = scanner.nextLine();

        matchDAO.createMatch(tournamentId, teamRadiantId, teamDireId, bestOf, matchTypeId, matchDateStr);
        System.out.println("Матч успешно добавлен!");
    }

    private static void readMatches(MatchDAO matchDAO) {
        List<String> matches = matchDAO.getAllMatches();
        if (matches.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Матчи:");
            for (String match : matches) {
                System.out.println(match);
            }
        }
    }

    private static void updateMatch(Scanner scanner, MatchDAO matchDAO) {
        System.out.print("Введите ID матча для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новый ID турнира: ");
        int tournamentId = scanner.nextInt();
        System.out.print("Введите новый ID команды Radiant: ");
        int teamRadiantId = scanner.nextInt();
        System.out.print("Введите новый ID команды Dire: ");
        int teamDireId = scanner.nextInt();
        System.out.print("Введите новый формат матча (1, 3 или 5): ");
        int bestOf = scanner.nextInt();
        System.out.print("Введите новый id типа матча: ");
        int matchTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новую дату матча (YYYY-MM-DD): ");
        String matchDateStr = scanner.nextLine();
        matchDAO.updateMatch(id, tournamentId, teamRadiantId, teamDireId, bestOf, matchTypeId, matchDateStr);
        System.out.println("Матч успешно обновлен!");
    }

    private static void deleteMatch(Scanner scanner, MatchDAO matchDAO) {
        System.out.print("Введите ID матча для удаления: ");
        int id = scanner.nextInt();
        matchDAO.deleteMatch(id);
        System.out.println("Матч успешно удалён!");
    }

    private static void handleGame(Scanner scanner, GameDAO gameDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Игрой:");
            System.out.println("1. Добавить игру");
            System.out.println("2. Просмотреть все игры");
            System.out.println("3. Обновить игру");
            System.out.println("4. Удалить игру");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createGame(scanner, gameDAO);
                    break;
                case 2:
                    readGames(gameDAO);
                    break;
                case 3:
                    updateGame(scanner, gameDAO);
                    break;
                case 4:
                    deleteGame(scanner, gameDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createGame(Scanner scanner, GameDAO gameDAO) {
        System.out.print("Введите ID матча: ");
        int matchId = scanner.nextInt();
        System.out.print("Введите продолжительность игры (в секундах): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите победителя: ");
        String winner = scanner.nextLine();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите время начала (YYYY-MM-DD HH:MM:SS): ");
        String startTimeStr = scanner.nextLine();
        Timestamp startTime = Timestamp.valueOf(startTimeStr);

        gameDAO.createGame(matchId, duration, winner, startTime);
        System.out.println("Игра успешно добавлена!");
    }

    private static void readGames(GameDAO gameDAO) {
        List<String> games = gameDAO.getAllGames();
        if (games.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Игры:");
            for (String game : games) {
                System.out.println(game);
            }
        }
    }

    private static void updateGame(Scanner scanner, GameDAO gameDAO) {
        System.out.print("Введите ID игры для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новый ID матча: ");
        int matchId = scanner.nextInt();
        System.out.print("Введите новую продолжительность игры (в секундах): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите нового победителя: ");
        String winner = scanner.nextLine();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новое время начала (YYYY-MM-DD HH:MM:SS): ");
        String startTimeStr = scanner.nextLine();
        Timestamp startTime = Timestamp.valueOf(startTimeStr);

        gameDAO.updateMatch(id, matchId, duration, winner, startTime);
        System.out.println("Игра успешно обновлена!");
    }

    private static void deleteGame(Scanner scanner, GameDAO gameDAO) {
        System.out.print("Введите ID игры для удаления: ");
        int id = scanner.nextInt();
        gameDAO.deleteGame(id);
        System.out.println("Игра успешно удалена!");
    }

    private static void handleHero(Scanner scanner, HeroDAO heroDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Героем:");
            System.out.println("1. Добавить героя");
            System.out.println("2. Просмотреть всех героев");
            System.out.println("3. Обновить героя");
            System.out.println("4. Удалить героя");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createHero(scanner, heroDAO);
                    break;
                case 2:
                    readHeroes(heroDAO);
                    break;
                case 3:
                    updateHero(scanner, heroDAO);
                    break;
                case 4:
                    deleteHero(scanner, heroDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createHero(Scanner scanner, HeroDAO heroDAO) {
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите имя героя: ");
        String name = scanner.nextLine();
        System.out.print("Введите основной атрибут героя: ");
        String primaryAttribute = scanner.nextLine();
        System.out.print("Введите тип атаки героя: ");
        String attackType = scanner.nextLine();
        System.out.print("Введите роли героя (через запятую): ");
        String roles = scanner.nextLine();

        heroDAO.createHero(name, primaryAttribute, attackType, roles);
        System.out.println("Герой успешно добавлен!");
    }

    private static void readHeroes(HeroDAO heroDAO) {
        List<String> heroes = heroDAO.getAllHeroes();
        if (heroes.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Герои:");
            for (String hero : heroes) {
                System.out.println(hero);
            }
        }
    }

    private static void updateHero(Scanner scanner, HeroDAO heroDAO) {
        System.out.print("Введите ID героя для обновления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите новое имя героя: ");
        String name = scanner.nextLine();
        System.out.print("Введите новый основной атрибут героя: ");
        String primaryAttribute = scanner.nextLine();
        System.out.print("Введите новый тип атаки героя: ");
        String attackType = scanner.nextLine();
        System.out.print("Введите новые роли героя (через запятую): ");
        String roles = scanner.nextLine();

        heroDAO.updateHero(id, name, primaryAttribute, attackType, roles);
        System.out.println("Герой успешно обновлен!");
    }

    private static void deleteHero(Scanner scanner, HeroDAO heroDAO) {
        System.out.print("Введите ID героя для удаления: ");
        int id = scanner.nextInt();
        heroDAO.deleteHero(id);
        System.out.println("Герой успешно удалён!");
    }

    private static void handleGameStats(Scanner scanner, GameStatsDAO gameStatsDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Игровой статистикой:");
            System.out.println("1. Добавить игровую статистику");
            System.out.println("2. Просмотреть всю игровую статистику");
            System.out.println("3. Обновить игровую статистику");
            System.out.println("4. Удалить игровую статистику");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createGameStats(scanner, gameStatsDAO);
                    break;
                case 2:
                    readGameStats(gameStatsDAO);
                    break;
                case 3:
                    updateGameStats(scanner, gameStatsDAO);
                    break;
                case 4:
                    deleteGameStats(scanner, gameStatsDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createGameStats(Scanner scanner, GameStatsDAO gameStatsDAO) {
        System.out.print("Введите ID игры: ");
        int gameId = scanner.nextInt();
        System.out.print("Введите ID героя-игрока: ");
        int playerHeroId = scanner.nextInt();
        System.out.print("Введите количество убийств: ");
        int kills = scanner.nextInt();
        System.out.print("Введите количество смертей: ");
        int deaths = scanner.nextInt();
        System.out.print("Введите количество ассистов: ");
        int assists = scanner.nextInt();
        System.out.print("Введите количество последних ударов: ");
        int lastHits = scanner.nextInt();
        System.out.print("Введите золото в минуту: ");
        int goldPerMinute = scanner.nextInt();
        System.out.print("Введите опыт в минуту: ");
        int xpPerMinute = scanner.nextInt();
        System.out.print("Введите чистую стоимость: ");
        int netWorth = scanner.nextInt();
        System.out.print("Введите уровень героя: ");
        int level = scanner.nextInt();

        gameStatsDAO.createGameStats(gameId, playerHeroId, kills, deaths, assists, lastHits, goldPerMinute, xpPerMinute, netWorth, level);
        System.out.println("Игровая статистика успешно добавлена!");
    }

    private static void readGameStats(GameStatsDAO gameStatsDAO) {
        List<String> gameStats = gameStatsDAO.getAllGameStats();
        if (gameStats.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Игровая статистика:");
            for (String stat : gameStats) {
                System.out.println(stat);
            }
        }
    }

    private static void updateGameStats(Scanner scanner, GameStatsDAO gameStatsDAO) {
        System.out.print("Введите ID статистики для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новый ID игры: ");
        int gameId = scanner.nextInt();
        System.out.print("Введите новый ID игрока-героя: ");
        int playerHeroId = scanner.nextInt();
        System.out.print("Введите новое количество убийств: ");
        int kills = scanner.nextInt();
        System.out.print("Введите новое количество смертей: ");
        int deaths = scanner.nextInt();
        System.out.print("Введите новое количество ассистов: ");
        int assists = scanner.nextInt();
        System.out.print("Введите новое количество последних ударов: ");
        int lastHits = scanner.nextInt();
        System.out.print("Введите новое золото в минуту: ");
        int goldPerMinute = scanner.nextInt();
        System.out.print("Введите новый опыт в минуту: ");
        int xpPerMinute = scanner.nextInt();
        System.out.print("Введите новую чистую стоимость: ");
        int netWorth = scanner.nextInt();
        System.out.print("Введите уровень героя: ");
        int level = scanner.nextInt();

        gameStatsDAO.updateGameStats(id, gameId, playerHeroId, kills, deaths, assists, lastHits, goldPerMinute, xpPerMinute, netWorth, level);
        System.out.println("Игровая статистика успешно обновлена!");
    }

    private static void deleteGameStats(Scanner scanner, GameStatsDAO gameStatsDAO) {
        System.out.print("Введите ID статистики для удаления: ");
        int id = scanner.nextInt();
        gameStatsDAO.deleteGameStats(id);
        System.out.println("Игровая статистика успешно удалена!");
    }

    private static void handlePlayerHero(Scanner scanner, PlayerHeroDAO playerHeroDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с Игроком-Героем:");
            System.out.println("1. Добавить игрока-героя");
            System.out.println("2. Просмотреть всех игроков-героев");
            System.out.println("3. Обновить игрока-героя");
            System.out.println("4. Удалить игрока-героя");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createPlayerHero(scanner, playerHeroDAO);
                    break;
                case 2:
                    readPlayerHeroes(playerHeroDAO);
                    break;
                case 3:
                    updatePlayerHero(scanner, playerHeroDAO);
                    break;
                case 4:
                    deletePlayerHero(scanner, playerHeroDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createPlayerHero(Scanner scanner, PlayerHeroDAO playerHeroDAO) {
        System.out.print("Введите ID игрока: ");
        int playerId = scanner.nextInt();
        System.out.print("Введите ID героя: ");
        int heroId = scanner.nextInt();
        System.out.print("Введите количество сыгранных игр: ");
        int gamesPlayed = scanner.nextInt();

        playerHeroDAO.createPlayerHero(playerId, heroId, gamesPlayed);
        System.out.println("Игрок-герой успешно добавлен!");
    }

    private static void readPlayerHeroes(PlayerHeroDAO playerHeroDAO) {
        List<String> playerHeroes = playerHeroDAO.getAllPlayerHeroes();
        if (playerHeroes.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Игроки-герои:");
            for (String playerHero : playerHeroes) {
                System.out.println(playerHero);
            }
        }
    }

    private static void updatePlayerHero(Scanner scanner, PlayerHeroDAO playerHeroDAO) {
        System.out.print("Введите ID игрока-героя для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новый ID игрока: ");
        int playerId = scanner.nextInt();
        System.out.print("Введите новый ID героя: ");
        int heroId = scanner.nextInt();
        System.out.print("Введите новое количество сыгранных игр: ");
        int gamesPlayed = scanner.nextInt();

        playerHeroDAO.updatePlayerHero(id, playerId, heroId, gamesPlayed);
        System.out.println("Игрок-герой успешно обновлен!");
    }

    private static void deletePlayerHero(Scanner scanner, PlayerHeroDAO playerHeroDAO) {
        System.out.print("Введите ID игрока-героя для удаления: ");
        int id = scanner.nextInt();
        playerHeroDAO.deletePlayerHero(id);
        System.out.println("Игрок-герой успешно удалён!");
    }
    private static void handleItem(Scanner scanner, ItemDAO itemDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с предметом:");
            System.out.println("1. Добавить предмет");
            System.out.println("2. Просмотреть все предметы");
            System.out.println("3. Обновить предмет");
            System.out.println("4. Удалить предмет");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createItem(scanner, itemDAO);
                    break;
                case 2:
                    readItems(itemDAO);
                    break;
                case 3:
                    updateItem(scanner, itemDAO);
                    break;
                case 4:
                    deleteItem(scanner, itemDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createItem(Scanner scanner, ItemDAO itemDAO) {
        System.out.print("Введите название предмета: ");
        String name = scanner.next();
        System.out.print("Введите стоимость предмета (или 0, если не указана): ");
        int cost = scanner.nextInt();
        System.out.print("Введите описание предмета: ");
        String description = scanner.next();

        itemDAO.createItem(name, cost == 0 ? null : cost, description);
        System.out.println("Предмет успешно добавлен!");
    }

    private static void readItems(ItemDAO itemDAO) {
        List<String> items = itemDAO.getAllItems();
        if (items.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Предметы:");
            for (String item : items) {
                System.out.println(item);
            }
        }
    }

    private static void updateItem(Scanner scanner, ItemDAO itemDAO) {
        System.out.print("Введите ID предмета для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новое название предмета: ");
        String newName = scanner.next();
        System.out.print("Введите новую стоимость предмета (или 0, если не указана): ");
        int newCost = scanner.nextInt();
        System.out.print("Введите новое описание предмета: ");
        String newDescription = scanner.next();

        itemDAO.updateItem(id, newName, newCost == 0 ? null : newCost, newDescription);
        System.out.println("Предмет успешно обновлен!");
    }

    private static void deleteItem(Scanner scanner, ItemDAO itemDAO) {
        System.out.print("Введите ID предмета для удаления: ");
        int id = scanner.nextInt();
        itemDAO.deleteItem(id);
        System.out.println("Предмет успешно удалён!");
    }

    private static void handleGameItemStats(Scanner scanner, GameItemStatsDAO gameItemStatsDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию со статистикой предметов в игре:");
            System.out.println("1. Добавить статистику предмета");
            System.out.println("2. Просмотреть все статистики предметов");
            System.out.println("3. Обновить статистику предмета");
            System.out.println("4. Удалить статистику предмета");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createGameItemStats(scanner, gameItemStatsDAO);
                    break;
                case 2:
                    readGameItemStats(gameItemStatsDAO);
                    break;
                case 3:
                    updateGameItemStats(scanner, gameItemStatsDAO);
                    break;
                case 4:
                    deleteGameItemStats(scanner, gameItemStatsDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createGameItemStats(Scanner scanner, GameItemStatsDAO gameItemStatsDAO) {
        System.out.print("Введите ID статистики игры: ");
        int gameStatsId = scanner.nextInt();
        System.out.print("Введите ID предмета: ");
        int itemId = scanner.nextInt();
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();

        gameItemStatsDAO.createGameItemStats(gameStatsId, itemId, quantity);
        System.out.println("Статистика предмета успешно добавлена!");
    }

    private static void readGameItemStats(GameItemStatsDAO gameItemStatsDAO) {
        List<String> gameItemStats = gameItemStatsDAO.getAllGameItemStats();
        if (gameItemStats.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Статистики предметов:");
            for (String stats : gameItemStats) {
                System.out.println(stats);
            }
        }
    }

    private static void updateGameItemStats(Scanner scanner, GameItemStatsDAO gameItemStatsDAO) {
        System.out.print("Введите ID статистики предмета для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новый ID статистики игры: ");
        int newGameStatsId = scanner.nextInt();
        System.out.print("Введите новый ID предмета: ");
        int newItemId = scanner.nextInt();
        System.out.print("Введите новое количество: ");
        int newQuantity = scanner.nextInt();

        gameItemStatsDAO.updateGameItemStats(id, newGameStatsId, newItemId, newQuantity);
        System.out.println("Статистика предмета успешно обновлена!");
    }

    private static void deleteGameItemStats(Scanner scanner, GameItemStatsDAO gameItemStatsDAO) {
        System.out.print("Введите ID статистики предмета для удаления: ");
        int id = scanner.nextInt();
        gameItemStatsDAO.deleteGameItemStats(id);
        System.out.println("Статистика предмета успешно удалена!");
    }

    private static void handleMatchType(Scanner scanner, MatchTypeDAO matchTypeDAO) {
        int choice;
        do {
            System.out.println("\nВыберите операцию с типом матча:");
            System.out.println("1. Добавить тип матча");
            System.out.println("2. Просмотреть все типы матчей");
            System.out.println("3. Обновить тип матча");
            System.out.println("4. Удалить тип матча");
            System.out.println("5. Назад");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createMatchType(scanner, matchTypeDAO);
                    break;
                case 2:
                    readMatchTypes(matchTypeDAO);
                    break;
                case 3:
                    updateMatchType(scanner, matchTypeDAO);
                    break;
                case 4:
                    deleteMatchType(scanner, matchTypeDAO);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 5);
    }

    private static void createMatchType(Scanner scanner, MatchTypeDAO matchTypeDAO) {
        System.out.print("Введите название типа матча: ");
        String typeName = scanner.next();

        matchTypeDAO.createMatchType(typeName);
        System.out.println("Тип матча успешно добавлен!");
    }

    private static void readMatchTypes(MatchTypeDAO matchTypeDAO) {
        List<String> matchTypes = matchTypeDAO.getAllMatchTypes();
        if (matchTypes.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Типы матчей:");
            for (String matchType : matchTypes) {
                System.out.println(matchType);
            }
        }
    }

    private static void updateMatchType(Scanner scanner, MatchTypeDAO matchTypeDAO) {
        System.out.print("Введите ID типа матча для обновления: ");
        int id = scanner.nextInt();
        System.out.print("Введите новое название типа матча: ");
        String newTypeName = scanner.next();

        matchTypeDAO.updateMatchType(id, newTypeName);
        System.out.println("Тип матча успешно обновлен!");
    }

    private static void deleteMatchType(Scanner scanner, MatchTypeDAO matchTypeDAO) {
        System.out.print("Введите ID типа матча для удаления: ");
        int id = scanner.nextInt();
        matchTypeDAO.deleteMatchType(id);
        System.out.println("Тип матча успешно удалён!");
    }
}

