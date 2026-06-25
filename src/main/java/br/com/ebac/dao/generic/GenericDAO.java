package br.com.ebac.dao.generic;

import br.com.ebac.anotacao.TipoChave;
import br.com.ebac.dao.Persistente;
import br.com.ebac.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T, E> {

    private final SingletonMap singletonMap;

    protected GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();
    }

    public abstract Class<T> getTipoClasse();

    public abstract void atualizarDados(T entity, T entityCadastrado);

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entity);

        if (mapaInterno.containsKey(chave)) {
            return false;
        }

        mapaInterno.put(chave, entity);
        return true;
    }

    @Override
    public void excluir(E valor) {
        Map<E, T> mapaInterno = getMapa();
        mapaInterno.remove(valor);
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entity);
        T objetoCadastrado = mapaInterno.get(chave);

        if (objetoCadastrado != null) {
            atualizarDados(entity, objetoCadastrado);
        }
    }

    @Override
    public T consultar(E valor) {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.get(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        return getMapa().values();
    }

    protected void limpar() {
        getMapa().clear();
    }

    private E getChave(T entity) throws TipoChaveNaoEncontradaException {
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(TipoChave.class)) {
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();

                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    return (E) method.invoke(entity);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new TipoChaveNaoEncontradaException(
                            "Chave principal do objeto " + entity.getClass() + " nao encontrada", e);
                }
            }
        }

        throw new TipoChaveNaoEncontradaException(
                "Chave principal do objeto " + entity.getClass() + " nao encontrada");
    }

    private Map<E, T> getMapa() {
        Map<Class<?>, Map<Serializable, Persistente>> mapa = singletonMap.getMap();
        Map<Serializable, Persistente> mapaInterno = mapa.get(getTipoClasse());

        if (mapaInterno == null) {
            mapaInterno = new HashMap<>();
            mapa.put(getTipoClasse(), mapaInterno);
        }

        return (Map<E, T>) (Map<?, ?>) mapaInterno;
    }
}
