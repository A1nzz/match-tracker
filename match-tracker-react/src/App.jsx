import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import HomePage from './pages/HomePage';
import './styles/main.scss';
import HeroesPage from './pages/HeroesPage';
import ItemsPage from './pages/ItemsPage';
import TeamsPage from './pages/TeamsPage';
import TournamentsPage from './pages/TournamentsPage';
import TournamentMatchesPage from './pages/TournamentMatchesPage';
import MatchGamesPage from './pages/MatchGamesPage';
import GameStatsPage from './pages/GameStatsPage';
import AdminPanel from './pages/AdminPanel';




const App = () => {
  return (
    
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/heroes" element={<HeroesPage />} /> 
        <Route path="/items" element={<ItemsPage />} /> 
        <Route path="/teams" element={<TeamsPage />} /> 
        <Route path="/tournaments" element={<TournamentsPage />} /> 
        <Route path="/tournaments/:tournamentId/matches" element={<TournamentMatchesPage />} />
        <Route path="/matches/:matchId/games" element={<MatchGamesPage />} />
        <Route path="/games/:gameId/stats" element={<GameStatsPage />} />
        <Route path="/admin" element={<AdminPanel />} />
      </Routes>
    </Router>
  );
};

export default App;