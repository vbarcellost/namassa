package br.com.ebac.services;

import br.com.ebac.dao.IClienteDAO;
import br.com.ebac.domain.Cliente;
import br.com.ebac.services.generic.GenericService;

public class ClienteService extends GenericService<Cliente, String> implements IClienteService {

    public ClienteService(IClienteDAO dao) {
        super(dao);
    }
}
