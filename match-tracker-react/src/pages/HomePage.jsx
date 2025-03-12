import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';


const HomePage = () => {
  const [tournaments, setTournaments] = useState([]);
  const [teams, setTeams] = useState([]);
  const [heroes, setHeroes] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const tournamentsResponse = await axios.get('http://localhost:8080/tournaments');
        const teamsResponse = await axios.get('http://localhost:8080/teams');
        const heroesResponse = await axios.get('http://localhost:8080/heroes');

        setTournaments(tournamentsResponse.data);
        setTeams(teamsResponse.data);
        setHeroes(heroesResponse.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="container">
      <h1>Welcome to Match Tracker</h1>
      <p>Track your favorite matches and stay updated with the latest results!</p>

      {/* Секция с актуальными турнирами */}
      <section className="section">
        <div className="section-header">
          <h2>Актуальные турниры</h2>
          <Link to="/tournaments" className="button">Показать все</Link>
        </div>
        <div className="grid-list">
          {tournaments.slice(0, 3).map(tournament => (
            <div key={tournament.id} className="card">
              <h3>{tournament.name}</h3>
              <p>Prize Pool: ${tournament.prizePool.toLocaleString()}</p>
              <p>Dates: {new Date(tournament.startDate).toLocaleDateString()} - {new Date(tournament.endDate).toLocaleDateString()}</p>
              <Link to={`/tournaments/${tournament.id}/matches`} className="button">Посмотреть матчи</Link>
            </div>
          ))}
        </div>
      </section>

      {/* Секция с сильнейшими командами */}
      <section className="section">
        <div className="section-header">
          <h2>Сильнейшие команды</h2>
          <Link to="/teams" className="button">Показать все</Link>
        </div>
        <div className="grid-list">
          {teams.slice(0, 3).map(team => (
            <div key={team.id} className="card">
              <h3>{team.name}</h3>
              <img src={team.logoUrl} alt={team.name} className='teamLogo' />
              <p>Region: {team.region}</p>
              <p>Rating: {team.rating}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Секция с актуальными героями */}
      <section className="section">
        <div className="section-header">
          <h2>Актуальные герои</h2>
          <Link to="/heroes" className="button">Показать все</Link>
        </div>
        <div className="grid-list">
          {heroes.slice(0, 3).map(hero => (
            <div key={hero.id} className="card">
              <h3>{hero.name}</h3>
              <img src={hero.logoUrl} alt={hero.name} className='heroLogo' />
              <p>Primary Attribute: {hero.primaryAttribute}</p>
              <p>Roles: {hero.roles}</p>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
};

export default HomePage;