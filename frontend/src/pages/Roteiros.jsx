import React, { useState } from "react";
import ModalAdicionarRoteiro from "../components/ModalAdicionarRoteiro"; // Importe o modal criado acima

const Roteiros = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [roteiros, setRoteiros] = useState([]); // Inicializa o estado para armazenar os roteiros

  const handleAddRoteiro = async (novoRoteiro) => {
    try {
      const response = await fetch("http://localhost:8080/roteiro/gerar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          destino: novoRoteiro.destino,
          atividades: novoRoteiro.atividades,
          acomodacao: novoRoteiro.acomodacao,
          transporte: novoRoteiro.transporte,
          gastronomia: novoRoteiro.gastronomia,
          orcamento: novoRoteiro.orcamento,
          dt_inicio: novoRoteiro.dt_inicio,
          dt_fim: novoRoteiro.dt_fim,
        }),
      });
  
      if (!response.ok) {
        throw new Error(`Erro HTTP: ${response.status}`);
      }
  
      const data = await response.json();
      console.log("Roteiro gerado com sucesso:", data);
  
      // Atualiza o estado local com o novo roteiro
      setRoteiros((prevRoteiros) => [...prevRoteiros, data]);
    } catch (error) {
      console.error("Erro ao gerar o roteiro:", error.message);
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
                <p>
                  <strong>Destino:</strong> {roteiro.destino}
                </p>
                <p>
                  <strong>Atividades:</strong> {roteiro.atividades}
                </p>
                <p>
                  <strong>Acomodação:</strong> {roteiro.acomodacao}
                </p>
                <p>
                  <strong>Data de Início:</strong> {roteiro.dt_inicio}
                </p>
                <p>
                  <strong>Data de Fim:</strong> {roteiro.dt_fim}
                </p>
                <p>
                  <strong>Transporte:</strong>{" "}
                  {roteiro.transporte || "Não especificado"}
                </p>
                <p>
                  <strong>Gastronomia:</strong>{" "}
                  {roteiro.gastronomia || "Não especificada"}
                </p>
                <p>
                  <strong>Orçamento:</strong>{" "}
                  {roteiro.orcamento || "Não especificado"}
                </p>
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
