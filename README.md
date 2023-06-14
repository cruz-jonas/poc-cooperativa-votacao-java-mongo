# COOPERATIVE
Este é um projeto de gerenciamento de sessão de votação de pautas cooperativas.

Os dados são enviados e recebidos no formato JSON.

## Pré-requisitos
Antes de executar o projeto, é necessário ter o seguinte software instalado em sua máquina:
- Java 11
- Maven

## Configuração
### Banco de dados
O banco de dados é o MongoDB e sua URL de conexão está disponível em:

`spring.data.mongodb.uri`

## Como executar
1) Clone o repositório em sua máquina local:
`by SSH git clone git@github.com:cruz-jonas/poc-cooperativa-votacao-java-mongo.git`
`by HTTP git clone https://github.com/cruz-jonas/poc-cooperativa-votacao-java-mongo.git`

2) Acesse o diretório do projeto:
`cd cooperative`

3) Execute o build com o Maven:
`mvn clean verify`

4) Execute o projeto com o Maven
`mvn spring-boot:run`

5) Acesse a aplicação em seu navegador em:
`http://localhost:8080`
