import React, { useState } from "react";

const ModalAdicionarRoteiro = ({ isOpen, onClose, onAdd }) => {
  const [formData, setFormData] = useState({
    destino: "",
    atividades: "",
    acomodacao: "",
    transporte: "",
    gastronomia: "",
    orcamento: "",
    dt_inicio: "",
    dt_fim: "",
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const validateForm = () => {
    const newErrors = {};
    if (!formData.dt_inicio) newErrors.dt_inicio = "A data de início é obrigatória.";
    if (!formData.dt_fim) newErrors.dt_fim = "A data de fim é obrigatória.";
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // Retorna true se não houver erros
  };

  const handleSubmit = () => {
    if (!validateForm()) return;

    onAdd(formData); // Envia os dados para o handler no componente pai
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
        <h2 className="text-xl font-semibold mb-4">Adicionar Roteiro</h2>

        {["destino", "atividades", "acomodacao", "transporte", "gastronomia", "orcamento"].map(
          (field) => (
            <div key={field} className="mb-4">
              <label className="block text-gray-700 capitalize">{field}</label>
              <input
                type="text"
                name={field}
                value={formData[field]}
                onChange={handleChange}
                className="w-full p-2 border rounded"
                placeholder={`Digite ${field === "orcamento" ? "o orçamento (opcional)" : `o/a ${field}`}`}
              />
              {errors[field] && <p className="text-red-500">{errors[field]}</p>}
            </div>
          )
        )}

        <div className="mb-4">
          <label className="block text-gray-700">Data de Início</label>
          <input
            type="date"
            name="dt_inicio"
            value={formData.dt_inicio}
            onChange={handleChange}
            className="w-full p-2 border rounded"
          />
          {errors.dt_inicio && <p className="text-red-500">{errors.dt_inicio}</p>}
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Data de Fim</label>
          <input
            type="date"
            name="dt_fim"
            value={formData.dt_fim}
            onChange={handleChange}
            className="w-full p-2 border rounded"
          />
          {errors.dt_fim && <p className="text-red-500">{errors.dt_fim}</p>}
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
