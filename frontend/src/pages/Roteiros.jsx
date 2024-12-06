import React, { useState, useEffect } from "react";
import ModalAdicionarRoteiro from "../components/ModalAdicionarRoteiro";
import ModalEnviarEmail from "../components/ModalEnviarEmail";

const Roteiros = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEmailModalOpen, setIsEmailModalOpen] = useState(false);
  const [selectedRoteiroId, setSelectedRoteiroId] = useState(null);
  const [roteiros, setRoteiros] = useState([]);
  const [error, setError] = useState("");

  // Função para carregar os roteiros do usuário logado
  const fetchRoteiros = () => {
    const idUsuario = localStorage.getItem("idUsuario");
    if (!idUsuario) {
      setError("Usuário não identificado. Faça login novamente.");
      return;
    }
  
    const storedRoteiros = localStorage.getItem(`roteiros_${idUsuario}`);
  
    if (storedRoteiros) {
      setRoteiros(JSON.parse(storedRoteiros)); // Carrega os roteiros armazenados para o usuário
    } else {
      setError("Nenhum roteiro encontrado. Adicione um novo.");
    }
  };

  // Chama a função de carregar roteiros quando o componente for montado
  useEffect(() => {
    fetchRoteiros();
  }, []); // O array vazio significa que a função será chamada apenas uma vez, na primeira renderização

  // Função para adicionar um novo roteiro
  const handleAddRoteiro = async (novoRoteiro) => {
    setError("");
    const idUsuario = localStorage.getItem("idUsuario");
  
    if (!idUsuario) {
      setError("Usuário não identificado. Faça login novamente.");
      return;
    }
  
    try {
      const response = await fetch(`http://localhost:8080/roteiros/${idUsuario}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          destino: novoRoteiro.destino,
          atividades: novoRoteiro.atividades,
          acomodacao: novoRoteiro.acomodacao,
          transporte: novoRoteiro.transporte,
          gastronomia: novoRoteiro.gastronomia,
          orcamento: novoRoteiro.orcamento,
          dt_inicio: novoRoteiro.dt_inicio,
          dt_fim: novoRoteiro.dt_fim,
          idUsuario, // Inclui o idUsuario no payload
        }),
      });
  
      if (!response.ok) {
        throw new Error("Erro ao processar o roteiro: " + response.status);
      }
  
      // Recarregar os roteiros após adicionar um novo
      const data = await response.json();
  
      if (!data.roteiro || !data.dica) {
        throw new Error("Estrutura de dados inesperada do backend.");
      }
  
      const roteiroFormatado = {
        roteiro: {
          destino: data.roteiro.destino,
          atividades: data.roteiro.atividades,
          acomodacao: data.roteiro.acomodacao,
          transporte: data.roteiro.transporte,
          gastronomia: data.roteiro.gastronomia,
        },
        dicas: {
          idRoteiro: data.dica.idRoteiro,
          bagagem: data.dica.bagagem,
          costumes: data.dica.costumes,
          moedas: data.dica.moeda,
          idioma: data.dica.idioma,
          documentos: data.dica.documentos,
          clima: data.dica.clima,
        },
      };
  
      // Atualiza o estado com o novo roteiro adicionado
      setRoteiros((prevRoteiros) => {
        const novosRoteiros = [...prevRoteiros, roteiroFormatado];
        localStorage.setItem(`roteiros_${idUsuario}`, JSON.stringify(novosRoteiros)); // Armazena os roteiros com a chave baseada no idUsuario
        return novosRoteiros;
      });
      setIsModalOpen(false);
    } catch (error) {
      console.error("Erro ao adicionar roteiro:", error.message);
      setError(error.message); // Exibe o erro no frontend
    }
  };
  
  // Função para enviar o e-mail com base no roteiro selecionado
  const handleEnviarEmail = async (emailData) => {
    try {
      const response = await fetch(
        `http://localhost:8080/roteiros/${selectedRoteiroId}/enviar-email`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(emailData),
        }
      );

      if (!response.ok) {
        throw new Error("Erro ao enviar o e-mail: " + response.status);
      }

      alert("E-mail enviado com sucesso!");
      setIsEmailModalOpen(false);
    } catch (error) {
      console.error("Erro ao enviar e-mail:", error.message);
      alert("Erro ao enviar o e-mail. Verifique os dados e tente novamente.");
    }
  };

  return (
    <div className="p-4">
      <button
        onClick={() => setIsModalOpen(true)}
        className="bg-green-500 text-white font-semibold py-2 px-4 rounded hover:bg-green-600 transition duration-300"
      >
        Adicionar Roteiro
      </button>

      {error && <p className="text-red-500 mt-4">{error}</p>}

      <ModalAdicionarRoteiro
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onAdd={handleAddRoteiro}
      />

      <ModalEnviarEmail
        isOpen={isEmailModalOpen}
        onClose={() => setIsEmailModalOpen(false)}
        onSend={handleEnviarEmail}
      />

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
                  <strong>Gastronomia:</strong> {roteiroData.roteiro.gastronomia}
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
                <button
                  onClick={() => {
                    setSelectedRoteiroId(roteiroData.dicas.idRoteiro);
                    setIsEmailModalOpen(true);
                  }}
                  className="bg-blue-500 text-white font-semibold py-2 px-4 rounded hover:bg-blue-600 transition duration-300 mt-4"
                >
                  Enviar E-mail
                </button>
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
