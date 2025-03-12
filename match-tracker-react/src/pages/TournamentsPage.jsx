import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';


const TournamentsPage = () => {
  const [tournaments, setTournaments] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/tournaments')
      .then(response => setTournaments(response.data))
      .catch(error => console.error('Error fetching tournaments:', error));
  }, []);

  return (
    <div className="container">
      <h1>Tournaments</h1>
      <div className="grid-list">
        {tournaments.map(tournament => (
          <div key={tournament.id} className="card">
            <h3>{tournament.name}</h3>
            <p>Prize Pool: ${tournament.prizePool.toLocaleString()}</p>
            <p>Dates: {new Date(tournament.startDate).toLocaleDateString()} - {new Date(tournament.endDate).toLocaleDateString()}</p>
            <Link to={`/tournaments/${tournament.id}/matches`} className="button">Посмотреть матчи</Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TournamentsPage;