import React from 'react';
import { NavLink } from 'react-router-dom';
import logo from '../assets/logo.png';
import '../styles/navbar.scss';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <img src={logo} alt="Match Tracker Logo" />
      </div>
      <ul className="navbar-list">
        <li className="navbar-item">
          <NavLink to="/" className="navbar-link" activeclassname="active">
            Home
          </NavLink>
        </li>
        <li className="navbar-item">
          <NavLink to="/heroes" className="navbar-link" activeclassname="active">
            Heroes
          </NavLink>
        </li>
        <li className="navbar-item">
          <NavLink to="/items" className="navbar-link" activeclassname="active">
            Items
          </NavLink>
        </li>
        <li className="navbar-item">
          <NavLink to="/teams" className="navbar-link" activeclassname="active">
            Teams
          </NavLink>
        </li>
        <li className="navbar-item">
          <NavLink to="/tournaments" className="navbar-link" activeclassname="active">
            Tournaments
          </NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;