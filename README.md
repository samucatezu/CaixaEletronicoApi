# GFT TDD ATM challenge
<ul>
    <summary><Font Size = "10"><strong>PT-BR Read.me</strong></Font></summary>
  <ul>
Desafio feito pelos starters da GFT.

## funcionalidades

- Sistema de cadastro, login e autenticacao com senha criptografada
- Hierarquia de acessos definida
- Somente o USER consegue realizar o saque
- TDD utilizado em todos os services
- Documentacao em Swagger funcionando


## Techs

Ferramentas utilizadas:

- [Swagger] - Documentacao da API
- [Spring Boot] - Estrutura em REST
- [MySql] - Armazenamento dos dados
- [Postman e Insomnia] - Testes de endpoints

## Para rodar o projeto:

- Clone-o
- Abra em uma IDE que suporte Spring Boot
- Defina o username e a password do seu User Mysql
- Rode o projeto
- digite o endereco do swagger no seu navegador http://localhost:8080/swagger-ui.html#
- Crie um usuario(recomendo que defina admin como false para testar os saques)
- Faca login e receba o token de autenticacao
- Faca a autenticacao na parte superior direito da seguinte forma: Digite "Bearer" + "{Token gerado}"
- Teste as rotas dada as suas funcionalidades


> :warning: **Lembre-se de baixar todas as dependencias do maven**: Recomendo o IntelliJ para rodar o projeto

  
  
