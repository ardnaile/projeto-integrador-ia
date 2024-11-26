import React, { useState } from "react";

const ModalAdicionarRoteiro = ({ isOpen, onClose, onAdd }) => {
  const [destino, setDestino] = useState('');
  const [atividades, setAtividades] = useState('');
  const [acomodacao, setAcomodacao] = useState('');
  const [transporte, setTransporte] = useState('');
  const [gastronomia, setGastronomia] = useState('');
  const [orcamento, setOrcamento] = useState('');
  const [dtInicio, setDtInicio] = useState('');
  const [dtFim, setDtFim] = useState('');

  const handleSubmit = () => {
    // Validação
    if (!destino || !atividades || !acomodacao || !dtInicio || !dtFim) {
      alert("Os campos 'destino', 'atividades', 'acomodação', 'data de início' e 'data de fim' são obrigatórios!");
      return;
    }

    const novoRoteiro = {
      destino,
      atividades,
      acomodacao,
      transporte: transporte || null,
      gastronomia: gastronomia || null,
      orcamento: orcamento || null,
      dt_inicio: dtInicio,
      dt_fim: dtFim,
    };

    onAdd(novoRoteiro); // Envia o roteiro para o handler
    onClose(); // Fecha o modal
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
        <h2 className="text-xl font-semibold mb-4">Adicionar Roteiro</h2>
        <div className="mb-4">
          <label className="block text-gray-700">Destino</label>
          <input
            type="text"
            value={destino}
            onChange={(e) => setDestino(e.target.value)}
            className="w-full p-2 border rounded"
            placeholder="Digite o destino"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Atividades</label>
          <input
            type="text"
            value={atividades}
            onChange={(e) => setAtividades(e.target.value)}
            className="w-full p-2 border rounded"
            placeholder="Digite as atividades"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Acomodação</label>
          <input
            type="text"
            value={acomodacao}
            onChange={(e) => setAcomodacao(e.target.value)}
            className="w-full p-2 border rounded"
            placeholder="Digite a acomodação"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Data de Início</label>
          <input
            type="date"
            value={dtInicio}
            onChange={(e) => setDtInicio(e.target.value)}
            className="w-full p-2 border rounded"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Data de Fim</label>
          <input
            type="date"
            value={dtFim}
            onChange={(e) => setDtFim(e.target.value)}
            className="w-full p-2 border rounded"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Transporte</label>
          <input
            type="text"
            value={transporte}
            onChange={(e) => setTransporte(e.target.value)}
            className="w-full p-2 border rounded"
            placeholder="Digite o transporte (opcional)"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Gastronomia</label>
          <input
            type="text"
            value={gastronomia}
            onChange={(e) => setGastronomia(e.target.value)}
            className="w-full p-2 border rounded"
            placeholder="Digite a gastronomia (opcional)"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Orçamento</label>
          <input
            type="text"
            value={orcamento}
            onChange={(e) => setOrcamento(e.target.value)}
            className="w-full p-2 border rounded"
            placeholder="Digite o orçamento (opcional)"
          />
        </div>
        <div className="flex justify-end space-x-4">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
          >
            Cancelar
          </button>
          <button
            onClick={handleSubmit}
            className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Adicionar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalAdicionarRoteiro;
