import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';

const TournamentMatchesPage = () => {
  const { tournamentId } = useParams();
  const [matches, setMatches] = useState([]);
  

  useEffect(() => {
    axios.get(`http://localhost:8080/tournaments/${tournamentId}/matches`)
      .then(response => setMatches(response.data))
      .catch(error => console.error('Error fetching matches:', error));
  }, [tournamentId]);

  return (
    <div className="container">
      <h1>Матчи турнира</h1>
      <div className="grid-list">
        {matches.map(match => (
          <div key={match.id} className="card">
            <Link to={`/matches/${match.id}/games`} className="card-link">
                <h3>{match.teamRadiant.name} vs {match.teamDire.name}</h3>
                <p>Тип матча: {match.matchType.typeName}</p>
                <p>Формат: BO{match.bestOf}</p>
                <p>Счет: {match.radiantScore} - {match.direScore}</p>
                <p>Дата: {new Date(match.matchDate).toLocaleDateString()}</p>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TournamentMatchesPage;