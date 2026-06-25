package br.com.ebac;

import br.com.ebac.dao.IClienteDAO;
import br.com.ebac.domain.Cliente;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;
import br.com.ebac.services.ClienteService;
import br.com.ebac.services.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteServiceTest {

    private IClienteService clienteService;

    @Before
    public void init() {
        clienteService = new ClienteService(new ClienteDAOMock());
    }

    @Test
    public void deveCadastrarClientePeloService() throws TipoChaveNaoEncontradaException {
        Cliente cliente = criarCliente("12345678901", "Vitoria");

        Boolean retorno = clienteService.cadastrar(cliente);

        Assert.assertTrue(retorno);
        Assert.assertEquals(cliente, clienteService.consultar("12345678901"));
    }

    @Test
    public void deveExcluirClientePeloService() throws TipoChaveNaoEncontradaException {
        clienteService.cadastrar(criarCliente("12345678901", "Vitoria"));

        clienteService.excluir("12345678901");

        Assert.assertNull(clienteService.consultar("12345678901"));
    }

    @Test
    public void deveAlterarClientePeloService() throws TipoChaveNaoEncontradaException {
        clienteService.cadastrar(criarCliente("12345678901", "Vitoria"));

        clienteService.alterar(criarCliente("12345678901", "Vitoria Barcellos"));

        Assert.assertEquals("Vitoria Barcellos", clienteService.consultar("12345678901").getNome());
    }

    private Cliente criarCliente(String cpf, String nome) {
        return new Cliente(nome, cpf, "11999999999", "Rua Java", "10", "Sao Paulo", "SP");
    }

    private static class ClienteDAOMock implements IClienteDAO {

        private final Map<String, Cliente> clientes = new HashMap<>();

        @Override
        public Boolean cadastrar(Cliente entity) {
            if (clientes.containsKey(entity.getCpf())) {
                return false;
            }
            clientes.put(entity.getCpf(), entity);
            return true;
        }

        @Override
        public void excluir(String valor) {
            clientes.remove(valor);
        }

        @Override
        public void alterar(Cliente entity) {
            clientes.put(entity.getCpf(), entity);
        }

        @Override
        public Cliente consultar(String valor) {
            return clientes.get(valor);
        }

        @Override
        public Collection<Cliente> buscarTodos() {
            return clientes.values();
        }
    }
}
