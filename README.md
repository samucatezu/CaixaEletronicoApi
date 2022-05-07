# GFT TDD ATM challenge
<ul>
    <summary><Font Size = "10"><strong>PT-BR Read.me</strong></Font></summary>
  <ul>
      
Desenvolva um programa que simule a entrega de notas quando um
cliente efetuar um saque em um caixa eletrônico. Os requisitos básicos
são os seguintes:
- Entregar o menor número de notas;
- É possível sacar o valor solicitado com as notas disponíveis;
- Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00
  Exemplos:
- Valor do Saque: R$ 30,00 – Resultado Esperado: Entregar 1 nota
  de R$ 20,00 e 1 nota de R$ 10,00.
- Valor do Saque: R$ 80,00 – Resultado Esperado: Entregar 1 nota
  de R$ 50,00 ; 1 nota de R$ 20,00 e 1 nota de R$ 10,00
      
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
- Defina o username e a password do seu User Mysql no application.properties
- Rode o projeto
- digite o endereco do swagger no seu navegador http://localhost:8080/swagger-ui.html#
- Crie um usuario(recomendo que defina admin como false para testar os saques)
- Faca login e receba o token de autenticacao
- Faca a autenticacao na parte superior direito da seguinte forma: Digite "Bearer" + "{Token gerado}"
- Teste as rotas dadas suas funcionalidades
      
## Rodando os testes unitarios

Para rodar os testes, abra a aba **Test** no projeto!
      
```
  Src > Test > gft.com > CedulasServiceTest
  ###Testar o CedulasService
  Src > Test > gft.com > SaqueServiceTest
  ###Testar o SaqueService
```


> :warning: **Lembre-se de baixar todas as dependencias do maven**: Recomendo o IntelliJ para rodar o projeto

  
  
