import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ReactCountryFlag from 'react-country-flag';

const TeamsPage = () => {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/teams')
      .then(response => {
        const teamsWithPlayers = response.data.map(async team => {
          const playersResponse = await axios.get(`http://localhost:8080/teams/${team.id}/players`);
          return { ...team, players: playersResponse.data };
        });
        Promise.all(teamsWithPlayers).then(setTeams);
      })
      .catch(error => console.error('Error fetching teams:', error));
  }, []);

  return (
    <div className="container">
      <h1>Команды</h1>
      <div className="grid-list team">
        {teams.map(team => (
          <div key={team.id} className="card team">
            <div className="team-info">
              <h3>{team.name}</h3>
              <img src={team.logoUrl} alt={team.name} className='teamLogo' />
              <p>Регион: {team.region}</p>
              <p>Рейтинг: {team.rating}</p>
            </div>
            <div className="players-list">
              <h4>Игроки:</h4>
              <ul>
                {team.players.map(player => (
                  <li key={player.id}>
                    <ReactCountryFlag
                      countryCode={player.country}
                      svg
                      style={{
                        width: '1.5em',
                        height: '1.5em',
                        marginRight: '0.5em',
                      }}
                      title={player.country}
                    />
                    {player.nickname} ({player.role})</li>
                ))}
              </ul>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TeamsPage;