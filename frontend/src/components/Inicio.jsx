import React from "react";
import { FaUserCircle } from "react-icons/fa"; // Importando o ícone de perfil

const Inicio = () => {
  return (
    <div className="flex flex-col min-h-screen bg-gray-100">
      {/* Cabeçalho com Menu */}
      <header className="bg-blue-600 p-4 flex justify-between items-center">
        {/* Logo no lado esquerdo */}
        <div className="text-white text-2xl font-bold">
          <a href="/">Logo</a>
        </div>

        {/* Menu de navegação */}
        <nav className="hidden md:flex space-x-4">
          <a href="/" className="text-white hover:underline">
            Roteiros
          </a>
          <a href="/sobre" className="text-white hover:underline">
            Sobre
          </a>
          <a href="/calendario" className="text-white hover:underline">
            Calendário
          </a>
        </nav>

        {/* Botão de perfil com ícone no lado direito */}
        <div>
          <button className="flex items-center text-white space-x-2">
            <FaUserCircle className="text-3xl" />
          </button>
        </div>
      </header>

      {/* Conteúdo Principal */}
      <main className="flex-grow p-8">
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-2xl font-semibold mb-4">
            Bem-vindo à minha tela com Tailwind CSS!
          </h2>
          <p className="text-gray-700">
            Este é um exemplo de uma página web simples usando React e Tailwind
            CSS.
          </p>
        </div>
      </main>

      {/* Rodapé */}
      <footer className="bg-gray-800 p-4 text-white text-center">
        <p>© 2024 TravelApp</p>
      </footer>
    </div>
  );
};

export default Inicio;
