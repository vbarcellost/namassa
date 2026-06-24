package br.com.ebac.dao;

import br.com.ebac.domain.Cliente;

public interface IClienteDAO {

    Boolean cadastrar(Cliente cliente);

    Cliente consultar(String cpf);

    void excluir(String cpf);

    void alterar(Cliente cliente);
}
