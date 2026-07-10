# Projeto 4 - Cadastro de cliente com JPA

Projeto Java da EBAC ajustado para persistir clientes com JPA, substituindo a persistencia em memoria/JDBC por uma camada baseada em `EntityManager`.

## O que foi ajustado

- `Cliente` foi convertido para entidade JPA com `@Entity`, `@Table`, `@Id` e colunas mapeadas.
- A implementacao antiga em memoria foi substituida por `ClienteJpaDAO`.
- O DAO usa `EntityManager` para cadastrar, consultar, alterar, excluir e listar clientes.
- A unidade de persistencia `namassaPU` foi configurada em `persistence.xml`.
- O projeto passou a usar Maven com Hibernate e banco H2 para facilitar execucao e testes.
- Foi adicionado teste automatizado cobrindo o CRUD com JPA.

## Estrutura

```text
src/main/java/br/com/ebac/app/App.java
src/main/java/br/com/ebac/dao/ClienteJpaDAO.java
src/main/java/br/com/ebac/dao/IClienteDAO.java
src/main/java/br/com/ebac/domain/Cliente.java
src/main/java/br/com/ebac/jpa/JpaUtil.java
src/main/resources/META-INF/persistence.xml
src/test/java/br/com/ebac/dao/ClienteJpaDAOTest.java
```

## Como testar

```bash
mvn test
```

## Como executar

```bash
mvn exec:java
```
