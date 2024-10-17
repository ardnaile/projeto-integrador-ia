import React, { useState } from "react";
import ModalAdicionarRoteiro from "../components/ModalAdicionarRoteiro"; // Importe o modal criado acima

const Roteiros = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [roteiros, setRoteiros] = useState([]);

  const handleAddRoteiro = async (novoRoteiro) => {
    try {
      const response = await fetch('http://localhost:8080/roteiro/gerar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(novoRoteiro),
      });
  
      if (!response.ok) {
        throw new Error(`Erro HTTP: ${response.status}`);
      }
  
      const data = await response.json();
      console.log('Roteiro gerado com sucesso:', data);
      // Atualize o estado ou faça algo com o retorno aqui
    } catch (error) {
      console.error('Erro ao gerar o roteiro:', error.message);
    }
  };
  

  return (
    <div>
      <button
        onClick={() => setIsModalOpen(true)}
        className="bg-green-500 text-white font-semibold py-2 px-4 rounded hover:bg-green-600 transition duration-300"
      >
        Adicionar Roteiro
      </button>

      <ModalAdicionarRoteiro
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onAdd={handleAddRoteiro}
      />

      <div>
        {roteiros.length > 0 ? (
          <ul>
            {roteiros.map((roteiro, index) => (
              <li key={index}>
                {roteiro.atividades} - {roteiro.acomodacao} -{" "}
                {roteiro.dataInicio} até {roteiro.dataFim}
              </li>
            ))}
          </ul>
        ) : (
          <p>Nenhum roteiro adicionado ainda.</p>
        )}
      </div>
    </div>
  );
};

export default Roteiros;
