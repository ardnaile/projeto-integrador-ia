import React, { useState } from 'react';
import { FaUserCircle } from 'react-icons/fa';
import Roteiros from './pages/Roteiros';
import Sobre from './pages/Sobre';
import Calendario from './pages/Calendario';
import Login from './pages/Login'; // Importando a página de login

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Estado para controle de login
  const [activePage, setActivePage] = useState('roteiros');

  const renderPage = () => {
    switch (activePage) {
      case 'roteiros':
        return <Roteiros />;
      case 'sobre':
        return <Sobre />;
      case 'calendario':
        return <Calendario />;
      default:
        return <Roteiros />;
    }
  };

  // Verificar se o usuário não está logado e exibir a página de login
  if (!isLoggedIn) {
    return <Login onLogin={() => setIsLoggedIn(true)} />; // Passa o callback para autenticar
  }

  return (
    <div className="flex flex-col min-h-screen bg-gray-100">
      {/* Cabeçalho */}
      <header className="bg-blue-700 p-4 flex justify-between items-center shadow-lg">
        {/* Logo */}
        <div className="text-white text-3xl font-bold">
          <a href="#" onClick={() => setActivePage('roteiros')}>TravelApp</a>
        </div>

        {/* Menu de navegação */}
        <nav className="hidden md:flex space-x-8 text-lg">
          <a href="#" className="text-white hover:text-blue-300" onClick={() => setActivePage('roteiros')}>
            Roteiros
          </a>
          <a href="#" className="text-white hover:text-blue-300" onClick={() => setActivePage('sobre')}>
            Sobre
          </a>
          <a href="#" className="text-white hover:text-blue-300" onClick={() => setActivePage('calendario')}>
            Calendário
          </a>
        </nav>

        {/* Botão de perfil */}
        <button className="flex items-center text-white space-x-2">
          <FaUserCircle className="text-3xl" />
        </button>
      </header>

      {/* Conteúdo principal */}
      <main className="flex-grow p-8">
        {renderPage()}
      </main>

      {/* Rodapé */}
      <footer className="bg-gray-800 p-4 text-white text-center">
        <p>© 2024 TravelApp. Todos os direitos reservados.</p>
      </footer>
    </div>
  );
};

export default App;
