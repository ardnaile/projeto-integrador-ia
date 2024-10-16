import React from 'react';

const Sobre = () => {
  return (
    <div className="bg-white p-8 rounded-lg shadow-lg">
      <h2 className="text-3xl font-bold text-blue-700 mb-4">Sobre a TravelApp</h2>
      <p className="text-lg text-gray-600">
        A TravelApp é especializada em criar experiências de viagem únicas e personalizadas para clientes ao redor do mundo.
        Nosso compromisso é fornecer roteiros de alta qualidade que garantem satisfação e momentos inesquecíveis.
      </p>

      <div className="mt-8 text-lg font-medium text-blue-700">
        <p>Missão: Transformar viagens em experiências memoráveis.</p>
        <p>Visão: Ser referência em planejamento de viagens personalizadas.</p>
      </div>
    </div>
  );
};

export default Sobre;
