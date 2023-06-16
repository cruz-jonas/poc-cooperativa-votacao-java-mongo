# COOPERATIVE
Este é um projeto de gerenciamento de sessão de votação de pautas cooperativas.

Os dados são enviados e recebidos no formato JSON.

## Pré-requisitos
Antes de executar o projeto, é necessário ter o seguinte software instalado em sua máquina:
- Java 11
- Maven
- Kafka 3.0.0

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

3) Suba o zookeeper
`zookeeper-server-start.sh ../config/zookeeper.properties`

4) Suba o Kafka
`kafka-server-start.sh ../config/server.properties`

5) Crie o tópico
`kafka-topics.sh --bootstrap-server localhost:9092 --topic agenda.votes --create --partitions 1 --replication-factor 1`

6) Execute o build com o Maven:
`mvn clean verify`

7) Execute o projeto com o Maven
`mvn spring-boot:run`

8) Acesse a aplicação em seu navegador em:
`http://localhost:8080`

## SWAGGER
Os endpoints podem ser acessados pelo Swagger
`http://localhost:8080/swagger-ui/index.html#/`