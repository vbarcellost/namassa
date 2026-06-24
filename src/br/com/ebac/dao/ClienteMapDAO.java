package br.com.ebac.dao;

import br.com.ebac.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    private final Map<String, Cliente> clientes;

    public ClienteMapDAO() {
        this.clientes = new HashMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (clientes.containsKey(cliente.getCpf())) {
            return false;
        }

        clientes.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public Cliente consultar(String cpf) {
        return clientes.get(cpf);
    }

    @Override
    public void excluir(String cpf) {
        clientes.remove(cpf);
    }

    @Override
    public void alterar(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
    }

    public Collection<Cliente> buscarTodos() {
        return clientes.values();
    }
}
