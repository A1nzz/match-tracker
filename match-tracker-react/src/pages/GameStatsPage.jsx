import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const GameStatsPage = () => {
  const { gameId } = useParams();
  const [stats, setStats] = useState([]);

  useEffect(() => {

    axios.get(`http://localhost:8080/games/${gameId}/stats`)
      .then(response => setStats(response.data))
      .catch(error => console.error('Error fetching game stats:', error));
  }, [gameId]);

  // Разделяем статистику на Radiant и Dire
  const radiantStats = stats.filter(stat => stat.gameStats.game.match.teamRadiant.name === stat.gameStats.playerHero.player.team.name);
  const direStats = stats.filter(stat => stat.gameStats.game.match.teamDire.name === stat.gameStats.playerHero.player.team.name);

  return (
    <div className="container">
      <h1>Послеигровая статистика</h1>
      <div className="stats-grid">
        {/* Radiant Team */}
        <div className="team-stats radiant">
          <h2>Radiant</h2>
          <div className="players-grid">
            {radiantStats.map(stat => (
              <div key={stat.gameStats.id} className="player-card">
                <img src={stat.gameStats.playerHero.hero.logoUrl} alt={stat.gameStats.playerHero.hero.name} className="hero-image" />
                <div className="player-info">
                  <h3>{stat.gameStats.playerHero.player.nickname}</h3>
                  <p>{stat.gameStats.playerHero.hero.name}</p>
                </div>
                <div className="stats-info">
                  <p>K/D/A: {stat.gameStats.kills}/{stat.gameStats.deaths}/{stat.gameStats.assists}</p>
                  <p>LH: {stat.gameStats.lastHits}</p>
                  <p>GPM: {stat.gameStats.goldPerMinute}</p>
                  <p>XPM: {stat.gameStats.xpPerMinute}</p>
                  <p>Net Worth: {stat.gameStats.netWorth}</p>
                  <p>Level: {stat.gameStats.finalLevel}</p>
                </div>
                <div className="items-info">
                  {stat.items.map(item => (
                    <div key={item.id} className="item-card">
                      <h4>
                        {item.quantity > 1 ? `${item.name} x ${item.quantity}` : item.name}
                      </h4>  
                    </div>
                  ))}
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Dire Team */}
        <div className="team-stats dire">
          <h2>Dire</h2>
          <div className="players-grid">
            {direStats.map(stat => (
              <div key={stat.gameStats.id} className="player-card">
                <img src={stat.gameStats.playerHero.hero.logoUrl} alt={stat.gameStats.playerHero.hero.name} className="hero-image" />
                <div className="player-info">
                  <h3>{stat.gameStats.playerHero.player.nickname}</h3>
                  <p>{stat.gameStats.playerHero.hero.name}</p>
                </div>
                <div className="stats-info">
                  <p>K/D/A: {stat.gameStats.kills}/{stat.gameStats.deaths}/{stat.gameStats.assists}</p>
                  <p>LH: {stat.gameStats.lastHits}</p>
                  <p>GPM: {stat.gameStats.goldPerMinute}</p>
                  <p>XPM: {stat.gameStats.xpPerMinute}</p>
                  <p>Net Worth: {stat.gameStats.netWorth}</p>
                  <p>Level: {stat.gameStats.finalLevel}</p>
                </div>
                <div className="items-info">
                  {stat.items.map(item => (
                    <div key={item.id} className="item-card">
                      <h4>{item.name}</h4>
                      <p>{item.description}</p>
                      <p>Количество: {item.quantity}</p>
                    </div>
                  ))}
                </div>

                
              </div>      
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default GameStatsPage;