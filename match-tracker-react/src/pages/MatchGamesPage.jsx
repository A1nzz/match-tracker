import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

const MatchGamesPage = () => {
  const { matchId } = useParams();
  const [games, setGames] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/matches/${matchId}/games`)
      .then(response => setGames(response.data))
      .catch(error => console.error('Error fetching games:', error));
  }, [matchId]);

  return (
    <div className="container">
      <h1>Игры матча</h1>
      <div className="grid-list">
        {games.map(game => (
          <div key={game.id} className="card">
            <Link to={`/games/${game.id}/stats`} className="card-link">
                <h3>Игра #{game.id}</h3>
                <p>Победитель: {game.winner}</p>
                <p>Длительность: {game.duration} минут</p>
                <p>Начало: {new Date(game.startTime).toLocaleString()}</p>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MatchGamesPage;