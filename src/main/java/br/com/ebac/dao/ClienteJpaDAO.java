package br.com.ebac.dao;

import br.com.ebac.domain.Cliente;
import br.com.ebac.jpa.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ClienteJpaDAO implements IClienteDAO {

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (consultar(cliente.getCpf()) != null) {
            return false;
        }

        executarEmTransacao(entityManager -> entityManager.persist(cliente));
        return true;
    }

    @Override
    public Cliente consultar(String cpf) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.find(Cliente.class, cpf);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void excluir(String cpf) {
        executarEmTransacao(entityManager -> {
            Cliente cliente = entityManager.find(Cliente.class, cpf);
            if (cliente != null) {
                entityManager.remove(cliente);
            }
        });
    }

    @Override
    public void alterar(Cliente cliente) {
        executarEmTransacao(entityManager -> entityManager.merge(cliente));
    }

    @Override
    public List<Cliente> buscarTodos() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager
                    .createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    private void executarEmTransacao(OperacaoJpa operacao) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            operacao.executar(entityManager);
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    @FunctionalInterface
    private interface OperacaoJpa {
        void executar(EntityManager entityManager);
    }
}
