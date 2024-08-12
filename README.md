
## Desafio Backend Itaú
 Este projeto é uma solução desenvolvida em Java para um desafio backend.

## Índice
- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação-via-repositório)
- [Execução](#execução)
- [Endpoints da API](#endpoints-da-api)

## Visão Geral
Este projeto consiste em uma API RESTful desenvolvida em Java com Spring Boot. A API é capaz de receber transações e retornar estatísticas sobre essas transações, seguindo as melhores práticas de desenvolvimento e design de software.

## Tecnologias Utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com)

## Instalação via Repositório
1. Clone o repositório:

```bash
git clone https://github.com/patrickmarinho/desafio-Itau.git
```

2. Instale as dependências com o Maven

## Execução
1. Inicie a aplicação com o Maven

2. A API vai estar acessível em http://localhost:8080/


## Instalação via Docker
1. Certifique-se que o Docker está instalado em sua máquina. Caso não esteja você pode baixa-lo aqui: [Docker](https://www.docker.com)
2. Baixe a imagem do Docker Hub.

```bash
docker pull 01patrick/desafioitau
```

## Execução
1. Execute a aplicação
```bash
docker run -p 8080:8080 01patrick/desafioitau:1.0
```
2. A API vai estar acessível em http://localhost:8080/


## Endpoints da API

#### Receber Transações:

```http
  POST /transaction
```
Recebe os parâmetros no seguinte formato JSON:

```json
{
    "amount": 123.45,
    "dateTime": "2020-08-07T12:34:56.789-03:00"
}
```

| Parâmetro | Tipo            | Descrição                                                              |
|-----------|-----------------|------------------------------------------------------------------------|
|`amount`   |`float`          |**Obrigatório**. O valor da sua transação, maior que 0.                 |
|`dateTime` |`OffsetDateTime` |**Obrigatório**. A data da sua transação, não podendo ocorrer no futuro.|

Como resposta a API pode retornar:
- `201 Created`
    - A transação foi aceita, validada e registrada
- `422 Unprocessable Entity`
    - A transação **não** foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que `0`)
- `400 Bad Request`
    - A API não compreendeu a requisição (por exemplo: um JSON inválido)

#### Limpar Transações:
Deleta todas as transações salvas.
```http
  DELETE /transaction
```

#### Calcular Estatísticas:

```http
  GET /statistics
```
Retorna estatísticas de todas as transações que ocorreram nos **últimos 60 segundos (basedo no dateTime)** no seguinte formato JSON:

```json

{
    "count": 10, // Quantidade de transações
    "sum": 1234.56, // Soma das transações
    "avg": 123.456, // Média das transações
    "min": 12.34, // Valor minímo efetuado em uma transação
    "max": 123.56 // Valor máximo efetuado em um transação
}

```
Caso não tenha ocorrido transações nos últimos 60 segundos os valores apareceram zerados.
