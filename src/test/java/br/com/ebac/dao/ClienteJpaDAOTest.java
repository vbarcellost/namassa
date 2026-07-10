package br.com.ebac.dao;

import br.com.ebac.domain.Cliente;
import br.com.ebac.jpa.JpaUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteJpaDAOTest {

    private final IClienteDAO dao = new ClienteJpaDAO();

    @BeforeEach
    void limparBase() {
        dao.buscarTodos().forEach(cliente -> dao.excluir(cliente.getCpf()));
    }

    @AfterAll
    static void fecharJpa() {
        JpaUtil.fechar();
    }

    @Test
    void deveExecutarCrudComJpa() {
        Cliente cliente = new Cliente("Vitoria", "12345678900", "11999999999", "Rua A", "10", "Sao Paulo", "SP");

        assertTrue(dao.cadastrar(cliente));
        assertFalse(dao.cadastrar(cliente));

        Cliente clienteConsultado = dao.consultar("12345678900");
        assertNotNull(clienteConsultado);
        assertEquals("Vitoria", clienteConsultado.getNome());

        Cliente clienteAlterado = new Cliente("Vitoria Torres", "12345678900", "11888888888", "Rua B", "20", "Osasco", "SP");
        dao.alterar(clienteAlterado);

        Cliente clienteAtualizado = dao.consultar("12345678900");
        assertEquals("Vitoria Torres", clienteAtualizado.getNome());
        assertEquals("Rua B", clienteAtualizado.getEndereco());

        dao.excluir("12345678900");
        assertNull(dao.consultar("12345678900"));
    }
}
