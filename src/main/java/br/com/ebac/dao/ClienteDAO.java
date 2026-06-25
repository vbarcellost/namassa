package br.com.ebac.dao;

import br.com.ebac.dao.generic.GenericDAO;
import br.com.ebac.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente, String> implements IClienteDAO {

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setCpf(entity.getCpf());
        entityCadastrado.setTelefone(entity.getTelefone());
        entityCadastrado.setEndereco(entity.getEndereco());
        entityCadastrado.setNumero(entity.getNumero());
        entityCadastrado.setCidade(entity.getCidade());
        entityCadastrado.setEstado(entity.getEstado());
    }
}
