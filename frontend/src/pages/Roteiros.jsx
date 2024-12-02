import React, { useState } from "react";
import ModalAdicionarRoteiro from "../components/ModalAdicionarRoteiro";

const Roteiros = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [roteiros, setRoteiros] = useState([]);
  const [error, setError] = useState("");

  const handleAddRoteiro = async (novoRoteiro) => {
    setError(""); // Limpa erros anteriores
    try {
      const response = await fetch("http://localhost:8080/roteiros", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novoRoteiro),
      });

      if (!response.ok) {
        throw new Error(`Erro ao adicionar o roteiro: ${response.status}`);
      }

      const data = await response.json();
      setRoteiros((prevRoteiros) => [...prevRoteiros, data]); // Atualiza a lista de roteiros
      setIsModalOpen(false); // Fecha o modal após sucesso
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div className="p-4">
      {/* Botão para abrir o modal */}
      <button
        onClick={() => setIsModalOpen(true)}
        className="bg-green-500 text-white font-semibold py-2 px-4 rounded hover:bg-green-600 transition duration-300"
      >
        Adicionar Roteiro
      </button>

      {/* Exibição de erros */}
      {error && <p className="text-red-500 mt-4">{error}</p>}

      {/* Modal para adicionar roteiro */}
      <ModalAdicionarRoteiro
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onAdd={handleAddRoteiro}
      />

      {/* Lista de roteiros adicionados */}
      <div className="mt-6">
        {roteiros.length > 0 ? (
          <ul className="space-y-4">
            {roteiros.map((roteiro, index) => (
              <li
                key={index}
                className="bg-gray-100 p-4 rounded shadow border border-gray-300"
              >
                <p><strong>Destino:</strong> {roteiro.destino}</p>
                <p><strong>Atividades:</strong> {roteiro.atividades}</p>
                <p><strong>Acomodação:</strong> {roteiro.acomodacao}</p>
                <p><strong>Data de Início:</strong> {roteiro.dt_inicio}</p>
                <p><strong>Data de Fim:</strong> {roteiro.dt_fim}</p>
                <p><strong>Transporte:</strong> {roteiro.transporte || "Não especificado"}</p>
                <p><strong>Gastronomia:</strong> {roteiro.gastronomia || "Não especificada"}</p>
                <p><strong>Orçamento:</strong> {roteiro.orcamento || "Não especificado"}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p className="text-gray-500">Nenhum roteiro adicionado ainda.</p>
        )}
      </div>
    </div>
  );
};

export default Roteiros;
