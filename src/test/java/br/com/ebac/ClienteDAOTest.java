package br.com.ebac;

import br.com.ebac.dao.ClienteDAO;
import br.com.ebac.dao.IClienteDAO;
import br.com.ebac.domain.Cliente;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ClienteDAOTest {

    private IClienteDAO clienteDAO;

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        clienteDAO = new ClienteDAO();
        clienteDAO.excluir("12345678901");
        clienteDAO.excluir("98765432100");
        clienteDAO.cadastrar(criarCliente("12345678901", "Vitoria"));
    }

    @Test
    public void deveCadastrarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteDAO.cadastrar(criarCliente("98765432100", "Maria"));

        Assert.assertTrue(retorno);
        Assert.assertNotNull(clienteDAO.consultar("98765432100"));
    }

    @Test
    public void naoDeveCadastrarClienteDuplicado() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteDAO.cadastrar(criarCliente("12345678901", "Cliente duplicado"));

        Assert.assertFalse(retorno);
    }

    @Test
    public void deveConsultarCliente() {
        Cliente cliente = clienteDAO.consultar("12345678901");

        Assert.assertNotNull(cliente);
        Assert.assertEquals("Vitoria", cliente.getNome());
    }

    @Test
    public void deveExcluirCliente() {
        clienteDAO.excluir("12345678901");

        Assert.assertNull(clienteDAO.consultar("12345678901"));
    }

    @Test
    public void deveAlterarCliente() throws TipoChaveNaoEncontradaException {
        Cliente clienteAlterado = criarCliente("12345678901", "Vitoria Barcellos");
        clienteDAO.alterar(clienteAlterado);

        Assert.assertEquals("Vitoria Barcellos", clienteDAO.consultar("12345678901").getNome());
    }

    @Test
    public void deveBuscarTodos() {
        Collection<Cliente> clientes = clienteDAO.buscarTodos();

        Assert.assertNotNull(clientes);
        Assert.assertEquals(1, clientes.size());
    }

    private Cliente criarCliente(String cpf, String nome) {
        return new Cliente(nome, cpf, "11999999999", "Rua Java", "10", "Sao Paulo", "SP");
    }
}
