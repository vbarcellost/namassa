package br.com.ebac.services.generic;

import br.com.ebac.dao.Persistente;
import br.com.ebac.dao.generic.IGenericDAO;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistente, E extends Serializable> implements IGenericService<T, E> {

    protected final IGenericDAO<T, E> dao;

    protected GenericService(IGenericDAO<T, E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        return dao.cadastrar(entity);
    }

    @Override
    public void excluir(E valor) {
        dao.excluir(valor);
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        dao.alterar(entity);
    }

    @Override
    public T consultar(E valor) {
        return dao.consultar(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        return dao.buscarTodos();
    }
}
