import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css'; // Import do DatePicker
import '../../src/customStyles.css'; // Importando estilos personalizados

const Calendario = () => {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [viagens, setViagens] = useState([
    { date: new Date(2024, 9, 15), pais: 'Suíça', cliente: 'João da Silva', dinheiro: 2000 },
  ]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newViagem, setNewViagem] = useState({ pais: '', cliente: '', dinheiro: '', date: new Date() });
  const [formError, setFormError] = useState(''); // Para exibir erros no formulário

  const handleAddViagem = () => {
    // Verificando se todos os campos estão preenchidos
    if (!newViagem.pais || !newViagem.cliente || !newViagem.dinheiro || !newViagem.date) {
      setFormError('Todos os campos são obrigatórios.');
      return;
    }

    const novaViagem = { ...newViagem };
    setViagens([...viagens, novaViagem]);
    setIsModalOpen(false); // Fechar o modal após adicionar a viagem
    setNewViagem({ pais: '', cliente: '', dinheiro: '', date: new Date() }); // Limpar os campos
    setFormError(''); // Limpar mensagem de erro
  };

  // Função para comparar a data
  const isSameDay = (date1, date2) => {
    return (
      date1.getDate() === date2.getDate() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getFullYear() === date2.getFullYear()
    );
  };

  // Função para formatar a data em DD/MM/YYYY
  const formatDate = (date) => {
    return date.toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });
  };

  return (
    <div>
      <h2 className="text-3xl font-bold text-blue-700 mb-4">Calendário de Viagens</h2>

      {/* Calendário */}
      <div className="bg-white p-6 rounded-lg shadow-lg">
        <Calendar
          value={selectedDate}
          onClickDay={(value) => setSelectedDate(value)}
          tileClassName={({ date }) =>
            viagens.some((viagem) => isSameDay(viagem.date, date)) ? 'highlight' : ''
          }
        />
      </div>

      {/* Botão para abrir o modal */}
      <button
        onClick={() => setIsModalOpen(true)}
        className="mt-6 bg-blue-600 text-white px-6 py-3 rounded-full shadow-lg hover:bg-blue-700 transition-all"
      >
        Adicionar nova viagem
      </button>

      {/* Lista de Viagens */}
      <div className="mt-8">
        <h3 className="text-lg font-bold text-blue-700 mb-4">Viagens Marcadas:</h3>
        <ul className="space-y-4">
          {viagens.map((viagem, index) => (
            <li key={index} className="p-4 bg-white shadow-md rounded-lg">
              <strong>{viagem.pais}</strong> - {viagem.cliente} (R$ {viagem.dinheiro}) em {formatDate(viagem.date)}
            </li>
          ))}
        </ul>
      </div>

      {/* Modal */}
      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-8 rounded-lg shadow-lg w-1/3">
            <h2 className="text-2xl font-bold mb-6 text-blue-700">Adicionar Nova Viagem</h2>

            {/* Mensagem de erro caso algum campo não esteja preenchido */}
            {formError && <p className="text-red-500 mb-4">{formError}</p>}

            <label className="block text-gray-700 mb-2">País</label>
            <input
              type="text"
              value={newViagem.pais}
              onChange={(e) => setNewViagem({ ...newViagem, pais: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="Digite o país"
            />

            <label className="block text-gray-700 mb-2">Cliente</label>
            <input
              type="text"
              value={newViagem.cliente}
              onChange={(e) => setNewViagem({ ...newViagem, cliente: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="Nome do cliente"
            />

            <label className="block text-gray-700 mb-2">Dinheiro</label>
            <input
              type="number"
              value={newViagem.dinheiro}
              onChange={(e) => setNewViagem({ ...newViagem, dinheiro: e.target.value })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              placeholder="Valor em R$"
            />

            <label className="block text-gray-700 mb-2">Data da Viagem</label>
            <DatePicker
              selected={newViagem.date}
              onChange={(date) => setNewViagem({ ...newViagem, date })}
              className="w-full p-2 mb-4 border rounded-lg focus:ring-2 focus:ring-blue-600"
              dateFormat="dd/MM/yyyy"
            />

            {/* Botões do Modal */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsModalOpen(false)}
                className="bg-gray-400 text-white px-4 py-2 rounded-full hover:bg-gray-500 transition-all"
              >
                Cancelar
              </button>
              <button
                onClick={handleAddViagem}
                className="bg-blue-600 text-white px-4 py-2 rounded-full hover:bg-blue-700 transition-all"
              >
                Adicionar Viagem
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Calendario;
