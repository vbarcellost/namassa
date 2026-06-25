package br.com.ebac.services.generic;

import br.com.ebac.dao.Persistente;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService<T extends Persistente, E extends Serializable> {

    Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    void excluir(E valor);

    void alterar(T entity) throws TipoChaveNaoEncontradaException;

    T consultar(E valor);

    Collection<T> buscarTodos();
}
