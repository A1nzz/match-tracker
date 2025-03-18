export interface Tournament {
  id: number;
  name: string;
  prizePool: number;
  startDate: string;
  endDate: string;
  organizer: string;
}

export interface MatchType {
  id: number;
  typeName: string;
}

export interface Match {
  id: number;
  tournament: Tournament;
  teamRadiant: Team;
  teamDire: Team;
  matchType: MatchType;
  bestOf: number;
  matchDate: string;
}

export interface Player {
  id: number;
  nickname: string;
  role: string;
  country: string;
  team: Team;
  realName: string;
}

export interface Team {
  id: number;
  name: string;
  logoUrl: string;
  region: string;
  rating: number;
  players: Player[];
}


export interface Game {
  id: number;
  winner: string;
  duration: number;
  startTime: string;
  match: Match;
}

export interface Item {
  id: number;
  name: string;
  logoUrl: string;
  cost: number;
  description: string;
}


export interface Hero {
  id: number;
  name: string;
  logoUrl: string;
  primaryAttribute: string;
  roles: string;
}

export interface GameStat {
  gameStats: {
  id: number;
  kills: number;
  deaths: number;
  assists: number;
  lastHits: number;
  goldPerMinute: number;
  xpPerMinute: number;
  netWorth: number;
  finalLevel: number;
  playerHero: {
    player: Player;
    hero: Hero;
  };
  game: Game
  };
  items: {
    id: number;
    name: string;
    description: string;
    quantity: number;
  }[];
}

export interface GameStats {
  id: number;
  game: Game;
  playerHero: PlayerHero;
  kills: number;
  deaths: number;
  assists: number;
  lastHits: number;
  goldPerMinute: number;
  xpPerMinute: number;
  netWorth: number;
  finalLevel: number;
}

export interface PlayerHero {
  id: number;
  player: Player;
  hero: Hero;
  gamesPlayed: number;
}

export interface GameItemStats {
  id: number;
  gameStats: GameStats;
  item: Item;
  quantity: number;
}

export interface HomePageDTO {
  tournaments: Tournament[];
  teams: Team[];
  heroes: Hero[];
}