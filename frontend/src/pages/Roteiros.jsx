import React, { useState } from "react";
import ModalAdicionarRoteiro from "../components/ModalAdicionarRoteiro";
import { salvarRoteiro } from "../api/apiGPT";

const Roteiros = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [roteiros, setRoteiros] = useState([]);
  const [error, setError] = useState("");

  const handleAddRoteiro = async (novoRoteiro) => {
    setError(""); // Limpa erros anteriores

    try {
      const roteiroFormatado = await salvarRoteiro(novoRoteiro);
      setRoteiros((prevRoteiros) => [...prevRoteiros, roteiroFormatado]);
      setIsModalOpen(false); // Fecha o modal ao concluir
    } catch (error) {
      console.error("Erro ao adicionar roteiro:", error.message);
      setError(error.message); // Exibe o erro no frontend
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
            {roteiros.map((roteiroData, index) => (
              <li
                key={index}
                className="bg-gray-100 p-4 rounded shadow border border-gray-300"
              >
                <h3 className="font-bold text-lg">Roteiro</h3>
                <p>
                  <strong>Destino:</strong> {roteiroData.roteiro.destino}
                </p>
                <p>
                  <strong>Atividades:</strong> {roteiroData.roteiro.atividades}
                </p>
                <p>
                  <strong>Acomodação:</strong> {roteiroData.roteiro.acomodacao}
                </p>
                <p>
                  <strong>Transporte:</strong> {roteiroData.roteiro.transporte}
                </p>
                <p>
                  <strong>Gastronomia:</strong>{" "}
                  {roteiroData.roteiro.gastronomia}
                </p>

                <h4 className="font-bold mt-4">Dicas</h4>
                <p>
                  <strong>Bagagem:</strong> {roteiroData.dicas.bagagem}
                </p>
                <p>
                  <strong>Costumes:</strong> {roteiroData.dicas.costumes}
                </p>
                <p>
                  <strong>Moedas:</strong> {roteiroData.dicas.moedas}
                </p>
                <p>
                  <strong>Idioma:</strong> {roteiroData.dicas.idioma}
                </p>
                <p>
                  <strong>Documentos:</strong> {roteiroData.dicas.documentos}
                </p>
                <p>
                  <strong>Clima:</strong> {roteiroData.dicas.clima}
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
