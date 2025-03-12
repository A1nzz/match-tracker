import React, { useState, useEffect } from 'react';
import axios from 'axios';

const HeroesPage = () => {
  const [heroes, setHeroes] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/heroes')
      .then(response => setHeroes(response.data))
      .catch(error => console.error('Error fetching heroes:', error));
  }, []);

  return (
    <div className="container">
      <h1>Heroes</h1>
      <div className="grid-list">
        {heroes.map(hero => (
          <div key={hero.id} className="card">
            <h3>{hero.name}</h3>
            <img src={hero.logoUrl} alt={hero.name} className='heroLogo' />
            <p>Primary Attribute: {hero.primaryAttribute}</p>
            <p>Roles: {hero.roles}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default HeroesPage;