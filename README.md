# Assistente de viagens com IA

Projeto integrador de inteligência artificial na Unisatc.

## Descrição do projeto

### Integrantes

- [Eliandra Cardoso](https://github.com/ardnaile)
- [Juliano Cardoso Felipe](https://github.com/julianocfelipe)

### Cliente
A [Tahiti Viagens & Turismo](https://www.instagram.com/tahiti_viagens/) será cliente do projeto, testando ao longo do desenvolvimento e utilizando a ferramenta quando ela estiver pronta.

### Problema
Dificuldade em gerar ideias e roteiros quando o cliente chega com poucas informações sobre a viagem que pretende fazer.

### Solução
Ferramenta web que utiliza IA para ajudar agentes de viagem a criar itinerários personalizados com sugestões de destinos, atividades, acomodações e dicas exclusivas. 

### Fluxo do sistema
O sistema contará com um fluxo simples conforme a imagem abaixo.
![image](https://github.com/user-attachments/assets/5138b31d-f24c-4175-b83f-49c44acfb078)
Os usuários e as agências serão cadastrados internamente, por isso já inicia com o login.

### Protótipo
O [protótipo](https://www.figma.com/design/F1GwuW1vkJ7PzmWurU243I/projeto-integrador-ia?node-id=0-1&t=lzzX7vj8duGQ8Tsv-0) está sendo feito no Figma. A princípio tinha sido pensada uma solução mobile first, porém em conversa com o cliente, vimos que a necessidade maior é poder utilizar a aplicação no pc.

### Modelagem dos dados
A [modelagem dos dados](https://dbdiagram.io/d/modelagem-projeto-ia-66d257a6eef7e08f0e47324e) está sendo feita no dbdiagram.io. Será utilizado um banco noSQL para armazenar e gerenciar os dados da aplicação.

---
## Requisições

### `POST` /agencia/register
Registro de agências de viagem, não será implementado formulário no Front-end.

Body:
```
{
	"nome": "teste1",
	"email": "teste1@email.com",
	"logo": "teste1"
}
```

Retorno:

ok: retorna o id da agência

bad request: mensagem de dados faltantes

internal server error: erro ao registrar agência + mensagem de erro

### `POST` /usuario/register
Registro de usuários, só é possível registrar usuários com uma agência existente.

Body:
```
{
	"nomeUsuario": "teste1",
	"email": "teste1@email.com",
	"senha": "teste1",
	"idAgencia": "670d7490aa23a06753c17dc5"
}
```

Retorno:
ok: retorna o nome do usuário

bad request: caso a agência informada não exista no banco

internal server error: erro ao registrar usuário + mensagem de erro

### `POST` /usuario/login
Login de usuário por enquanto sem camada de segurança e autorização de rotas.

Body:
```
{
	"nomeUsuario": "teste1",
	"senha": "teste1"
}
```

Retorno:

ok: Login successful!

unauthorized: Invalid credentials

### `POST` /roteiro/gerar
Gera um roteiro e dicas com base em inputs coleados do Front-end. Posteriormente, será gerado com IA. O usuário pode deixar os inputs em branco, caso queira. Os únicos campos que são obrigatórios são as datas:

Body:
```
{
	"titulo": "TESTE",
  	"dt_inicio": "2024-11-01T00:00:00Z",
	"dt_fim": "2024-11-01T00:00:00Z"
}
```

Retorna o roteiro recém gerado.


### `POST` /roteiro/enviar/{id}
Gera um pdf do roteiro cujo id foi fornecido e envia por email.

Body:
```
{
	"emailCliente": "emailteste@gmail.com",
	"mensagem": "email teste"
}
```
Retorna se o roteiro foi enviado com sucesso ou uma mensagem de erro.


### `PATCH` /roteiro/editar/{id}
Permite alterar informações do roteiro e das dicas. Assim como para gerar o roteiro, nem todos os campos precisam ser preenchidos.

Body:
```
{
	"roteiro": {
		"destino": "TESTE DESTINO EDITADO"
	},
	"dica": {
		"bagagem": "TESTE BAGAGEM EDITADA"
	}
}
```

Retorna o roteiro com as alterações aplicadas.

### `DELETE` /roteiro/excluir/{id}
Deleta um roteiro do banco. Não necessita de corpo, apenas retorna se a exclusão foi bem sucedida.


