export const salvarRoteiro = async (novoRoteiro) => {
    try {
      const response = await fetch("http://localhost:8080/roteiros", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novoRoteiro),
      });
  
      if (!response.ok) {
        throw new Error(`Erro ao processar o roteiro: ${response.status}`);
      }
  
      const data = await response.json();
  
      // Validação básica do formato esperado da resposta
      if (!data.roteiro || !data.dica) {
        throw new Error("Estrutura de dados inesperada do backend.");
      }
  
      // Retorna os dados formatados para o componente chamar
      return {
        roteiro: {
          destino: data.roteiro.destino,
          atividades: data.roteiro.atividades,
          acomodacao: data.roteiro.acomodacao,
          transporte: data.roteiro.transporte,
          gastronomia: data.roteiro.gastronomia,
        },
        dicas: {
          bagagem: data.dica.bagagem,
          costumes: data.dica.costumes,
          moedas: data.dica.moeda, // Verifique a chave correta no JSON
          idioma: data.dica.idioma,
          documentos: data.dica.documentos,
          clima: data.dica.clima,
        },
      };
    } catch (error) {
      console.error("Erro ao salvar roteiro:", error.message);
      throw error;
    }
  };
  