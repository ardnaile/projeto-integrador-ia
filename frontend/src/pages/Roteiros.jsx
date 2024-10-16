import React, { useState } from 'react';

const Roteiros = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [roteiros, setRoteiros] = useState([
    { pais: 'Suíça', dataInicio: '15/10/2024', dataFim: '22/10/2024', cliente: 'João da Silva', estilo: 'Aventura', orcamento: 2000 },
    { pais: 'França', dataInicio: '05/11/2024', dataFim: '12/11/2024', cliente: 'Maria Oliveira', estilo: 'Cultural', orcamento: 1500 },
    // Adicione mais roteiros conforme necessário
  ]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [novoRoteiro, setNovoRoteiro] = useState({ pais: '', dataInicio: '', dataFim: '', estilo: '', orcamento: '' });
  const [formError, setFormError] = useState(''); // Para exibir erros no formulário

  const handleAddRoteiro = () => {
    // Verificando se todos os campos estão preenchidos
    if (!novoRoteiro.pais || !novoRoteiro.dataInicio || !novoRoteiro.dataFim || !novoRoteiro.estilo || !novoRoteiro.orcamento) {
      setFormError('Todos os campos são obrigatórios.');
      return;
    }

    setRoteiros([...roteiros, novoRoteiro]);
    setIsModalOpen(false); // Fechar o modal após adicionar o roteiro
    setNovoRoteiro({ pais: '', dataInicio: '', dataFim: '', estilo: '', orcamento: '' }); // Limpar os campos
    setFormError(''); // Limpar mensagem de erro
  };

  // Filtrando roteiros com base no termo de pesquisa
  const filteredRoteiros = roteiros.filter((roteiro) =>
    roteiro.pais.toLowerCase().includes(searchTerm.toLowerCase()) || roteiro.cliente.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      {/* Campo de pesquisa */}
      <div className="relative mb-6">
        <input
          type="text"
          className="w-full p-4 border border-gray-300 rounded-full shadow-lg focus:outline-none focus:ring-2 focus:ring-blue-600"
          placeholder="Pesquise por roteiros..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      {/* Botão para gerar novo roteiro */}
      <button
        onClick={() => setIsModalOpen(true)}
        className="mb-4 bg-blue-600 text-white px-4 py-2 rounded-lg shadow-lg hover:bg-blue-700 transition-all"
      >
        Gerar Novo Roteiro
      </button>

      {/* Lista de Roteiros */}
      <div className="space-y-6">
        {filteredRoteiros.map((roteiro, index) => (
          <div key={index} className="flex items-center bg-white p-6 rounded-lg shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-300">
            {/* Imagem do Roteiro */}
            <div className="w-24 h-24 bg-gray-300 rounded-lg mr-6"></div>

            {/* Informações do Roteiro */}
            <div>
              <p className="text-xl font-semibold text-blue-700">País: {roteiro.pais}</p>
              <p className="text-gray-500">Data Início: {roteiro.dataInicio}</p>
              <p className="text-gray-500">Data Fim: {roteiro.dataFim}</p>
              <p className="text-gray-500">Cliente: {roteiro.cliente}</p>
              <p className="text-gray-500">Estilo: {roteiro.estilo}</p>
              <p className="text-gray-500">Orçamento: R$ {roteiro.orcamento}</p>
            </div>
          </div>
        ))}
      </div>

      {/* Modal para adicionar novo roteiro */}
      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-8 rounded-lg shadow-lg w-1/3">
            <h2 className="text-2xl font-bold mb-6 text-blue-700">Adicionar Novo Roteiro</h2>

            {/* Mensagem de erro caso algum campo não esteja preenchido */}
            {formError && <p className="text-red-500 mb-4">{formError}</p>}

            <label className="block text-gray-700 mb-2">País</label>
            <input
              type="text"
              value={novoRoteiro.pais}
              onChange={(e) => setNovoRoteiro({ ...novoRoteiro, pais: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="Digite o país"
            />

            <label className="block text-gray-700 mb-2">Data de Início</label>
            <input
              type="text"
              value={novoRoteiro.dataInicio}
              onChange={(e) => setNovoRoteiro({ ...novoRoteiro, dataInicio: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="DD/MM/YYYY"
            />

            <label className="block text-gray-700 mb-2">Data de Fim</label>
            <input
              type="text"
              value={novoRoteiro.dataFim}
              onChange={(e) => setNovoRoteiro({ ...novoRoteiro, dataFim: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="DD/MM/YYYY"
            />

            <label className="block text-gray-700 mb-2">Estilo de Atividade</label>
            <input
              type="text"
              value={novoRoteiro.estilo}
              onChange={(e) => setNovoRoteiro({ ...novoRoteiro, estilo: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="Ex: Aventura, Cultural..."
            />

            <label className="block text-gray-700 mb-2">Orçamento</label>
            <input
              type="number"
              value={novoRoteiro.orcamento}
              onChange={(e) => setNovoRoteiro({ ...novoRoteiro, orcamento: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="Valor em R$"
            />

            {/* Botões do Modal */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsModalOpen(false)}
                className="bg-gray-400 text-white px-4 py-2 rounded-full hover:bg-gray-500 transition-all"
              >
                Cancelar
              </button>
              <button
                onClick={handleAddRoteiro}
                className="bg-blue-600 text-white px-4 py-2 rounded-full hover:bg-blue-700 transition-all"
              >
                Adicionar Roteiro
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Roteiros;
