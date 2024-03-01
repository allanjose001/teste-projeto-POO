import React from 'react';
import { Link } from 'react-router-dom';
import './LayoutComponents.css';

const Header = () => {
  return (
    <header className="header">
      <div className="logo-container">
        <img src="/src/assets/LogoAF.jpg" alt="Logo Agenda Facil" className="logo"/>
      </div>
      <div className="profile-container">
        {/* logica de perfil*/}
        <Link to="/login">
          <button>Login</button>
        </Link>
      </div>
    </header>
  );
};

const Sidebar = () => {
  return (
    <aside className="sidebar">
      <nav>
        <ul>
          <li>
            <Link to="/marcar-consulta">Marcar consulta</Link>
          </li>
          <li>
            <Link to="/historico-consultas">Historico de Consultas</Link>
          </li>
        </ul>
      </nav>
    </aside>
  );
};

const Footer = () => {
  return (
    <footer>
      {/* Conteúdo do rodapé aqui */}
    </footer>
  );
};

export { Header, Sidebar, Footer };