# Projeto 2 - Cadastro de cliente

Projeto Java para cadastro de clientes em memoria, evoluido a partir do Projeto 1 e seguindo a proposta do modulo "Maos na massa: PROJETO 2".

## O que foi pedido

Criar um novo projeto baseado no cadastro de cliente do Projeto 1, mantendo as operacoes de CRUD e reorganizando a estrutura com DAO generico, entidade persistente, anotacao para chave, camada de service e testes automatizados.

- Cadastrar cliente
- Consultar cliente pelo CPF
- Alterar cliente
- Excluir cliente
- Buscar todos os clientes
- Sair da aplicacao

## Estrutura

```text
src/
  main/java/br/com/ebac/
    anotacao/TipoChave.java
    app/App.java
    dao/
      ClienteDAO.java
      IClienteDAO.java
      Persistente.java
      generic/
        GenericDAO.java
        IGenericDAO.java
        SingletonMap.java
    domain/Cliente.java
    exceptions/TipoChaveNaoEncontradaException.java
    services/
      ClienteService.java
      IClienteService.java
      generic/
        GenericService.java
        IGenericService.java
  test/java/br/com/ebac/
    ClienteDAOTest.java
    ClienteServiceTest.java
```

## Como executar

No terminal, dentro da pasta do projeto:

```bash
mvn test
mvn -DskipTests package
java -cp target/classes br.com.ebac.app.App
```
