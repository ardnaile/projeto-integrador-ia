import React, { useState } from "react";

const ModalAdicionarRoteiro = ({ isOpen, onClose, onAdd }) => {
  const [novoRoteiro, setNovoRoteiro] = useState({
    atividades: "",
    acomodacao: "",
    dt_inicio: "",
    dt_fim: "",
  });
  const [formError, setFormError] = useState("");

  const handleChange = (e) => {
    setNovoRoteiro({
      ...novoRoteiro,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = () => {
    if (
      !novoRoteiro.atividades ||
      !novoRoteiro.acomodacao ||
      !novoRoteiro.dt_inicio ||
      !novoRoteiro.dt_fim
    ) {
      setFormError("Todos os campos são obrigatórios.");
      return;
    }
    onAdd(novoRoteiro);
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white rounded-lg shadow-lg p-6 max-w-md w-full mx-4">
        <h2 className="text-xl font-bold mb-4">Adicionar Novo Roteiro</h2>

        <div className="mb-4">
          <label
            htmlFor="atividades"
            className="block text-sm font-medium text-gray-700"
          >
            Atividades
          </label>
          <input
            type="text"
            name="atividades"
            value={novoRoteiro.atividades}
            onChange={handleChange}
            placeholder="Ex: Passeio, Compras, Aventura..."
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="acomodacao"
            className="block text-sm font-medium text-gray-700"
          >
            Acomodação
          </label>
          <select
            name="acomodacao"
            value={novoRoteiro.acomodacao}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          >
            <option value="">Selecione...</option>
            <option value="Hotel Padrão">Hotel Padrão</option>
            <option value="Hostel">Hostel</option>
            <option value="Resort">Resort</option>
            <option value="Apartamento">Apartamento</option>
          </select>
        </div>

        <div className="mb-4">
          <label
            htmlFor="dt_inicio"
            className="block text-sm font-medium text-gray-700"
          >
            Data de Início
          </label>
          <input
            type="date"
            name="dt_inicio"
            value={novoRoteiro.dt_inicio}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="dt_fim"
            className="block text-sm font-medium text-gray-700"
          >
            Data de Fim
          </label>
          <input
            type="date"
            name="dt_fim"
            value={novoRoteiro.dt_fim}
            onChange={handleChange}
            className="mt-1 p-2 w-full border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        {formError && <p className="text-red-500 text-sm mb-4">{formError}</p>}

        <div className="flex justify-end space-x-2">
          <button
            onClick={handleSubmit}
            className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 transition"
          >
            Adicionar
          </button>
          <button
            onClick={onClose}
            className="bg-gray-300 text-gray-700 py-2 px-4 rounded hover:bg-gray-400 transition"
          >
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalAdicionarRoteiro;
