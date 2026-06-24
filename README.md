# Projeto 1 - Cadastro de cliente

Projeto Java simples para cadastro de clientes em memoria, seguindo a proposta do modulo "Maos na massa: PROJETO 1 - Cadastro de cliente".

## O que foi pedido

Criar um cadastro de cliente com operacoes de CRUD em memoria:

- Cadastrar cliente
- Consultar cliente pelo CPF
- Alterar cliente
- Excluir cliente
- Sair da aplicacao

## Estrutura

```text
src/
  br/com/ebac/app/App.java
  br/com/ebac/dao/IClienteDAO.java
  br/com/ebac/dao/ClienteMapDAO.java
  br/com/ebac/domain/Cliente.java
```

## Como executar

No terminal, dentro da pasta do projeto:

```bash
javac -d out src/br/com/ebac/domain/Cliente.java src/br/com/ebac/dao/IClienteDAO.java src/br/com/ebac/dao/ClienteMapDAO.java src/br/com/ebac/app/App.java
java -cp out br.com.ebac.app.App
```
