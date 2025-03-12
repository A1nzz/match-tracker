import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ItemsPage = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/items')
      .then(response => setItems(response.data))
      .catch(error => console.error('Error fetching items:', error));
  }, []);

  return (
    <div className="container">
      <h1>Items</h1>
      <div className="grid-list">
        {items.map(item => (
          <div key={item.id} className="card">
            <h3>{item.name}</h3>
            <img src={item.logoUrl} alt={item.name} className='itemLogo' />
            <p>Cost: {item.cost}</p>
            <p>{item.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ItemsPage;