import React, { useState } from 'react';

const Login = ({ onLogin }) => {
  // Estados para email e senha digitados pelo usuário
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  // Credenciais fixas
  const validEmail = 'admin@gmail.com';
  const validPassword = '1234';

  const handleLogin = () => {
    // Verifica se as credenciais estão corretas
    if (email === validEmail && password === validPassword) {
      onLogin(); // Loga o usuário
    } else {
      setError('Email ou senha incorretos!'); // Exibe uma mensagem de erro
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-500 to-purple-600">
      <div className="w-full max-w-md bg-white rounded-lg shadow-lg p-8">
        <h2 className="text-2xl font-bold text-center text-gray-800">Bem-vindo ao TravelApp!</h2>
        <p className="text-center text-gray-600 mb-6">Faça login para continuar</p>
        {error && <p className="text-red-500 text-center mb-4">{error}</p>} {/* Mensagem de erro */}
        <form onSubmit={(e) => e.preventDefault()}>
          <div className="mb-4">
            <label htmlFor="email" className="block text-gray-700 font-medium mb-2">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)} // Atualiza o estado do email
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
              placeholder="Digite seu email"
              required
            />
          </div>
          <div className="mb-6">
            <label htmlFor="password" className="block text-gray-700 font-medium mb-2">Senha</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)} // Atualiza o estado da senha
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
              placeholder="Digite sua senha"
              required
            />
          </div>
          <button
            type="button"
            onClick={handleLogin} // Valida o login ao clicar
            className="w-full bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg transition duration-200"
          >
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
