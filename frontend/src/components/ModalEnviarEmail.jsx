import React, { useState, useEffect } from "react";

const ModalEnviarEmail = ({ isOpen, onClose, onSend }) => {
  const [emailCliente, setEmailCliente] = useState("");
  const [mensagem, setMensagem] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    if (isOpen) {
      // Limpa os campos e a mensagem ao abrir o modal
      setEmailCliente("");
      setMensagem("");
      setSuccessMessage("");
    }
  }, [isOpen]);

  const handleSubmit = async () => {
    try {
      await onSend({ emailCliente, mensagem });
      setSuccessMessage("E-mail enviado com sucesso!");

      // Fecha o modal após 2 segundos
      setTimeout(() => {
        setSuccessMessage("");
        onClose();
      }, 2000);
    } catch (error) {
      setSuccessMessage("Erro ao enviar o e-mail. Tente novamente.");
    }
  };

  return (
    isOpen && (
      <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
        <div className="bg-white p-6 rounded shadow-lg w-1/3">
          <h2 className="text-lg font-bold mb-4">Enviar E-mail</h2>
          {successMessage ? (
            <p className="text-green-500 font-semibold text-center">
              {successMessage}
            </p>
          ) : (
            <>
              <div>
                <label className="block font-semibold">E-mail do Cliente:</label>
                <input
                  type="email"
                  value={emailCliente}
                  onChange={(e) => setEmailCliente(e.target.value)}
                  className="border border-gray-300 rounded w-full p-2 mt-1"
                />
              </div>
              <div className="mt-4">
                <label className="block font-semibold">Mensagem:</label>
                <textarea
                  value={mensagem}
                  onChange={(e) => setMensagem(e.target.value)}
                  className="border border-gray-300 rounded w-full p-2 mt-1"
                ></textarea>
              </div>
              <div className="flex justify-end mt-6">
                <button
                  onClick={onClose}
                  className="bg-gray-500 text-white font-semibold py-2 px-4 rounded mr-2 hover:bg-gray-600"
                >
                  Cancelar
                </button>
                <button
                  onClick={handleSubmit}
                  className="bg-blue-500 text-white font-semibold py-2 px-4 rounded hover:bg-blue-600"
                >
                  Enviar
                </button>
              </div>
            </>
          )}
        </div>
      </div>
    )
  );
};

export default ModalEnviarEmail;
