package br.com.ebac.dao.generic;

import br.com.ebac.dao.Persistente;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class SingletonMap {

    private static SingletonMap instance;

    private final Map<Class<?>, Map<Serializable, Persistente>> map;

    private SingletonMap() {
        this.map = new HashMap<>();
    }

    public static SingletonMap getInstance() {
        if (instance == null) {
            instance = new SingletonMap();
        }
        return instance;
    }

    public Map<Class<?>, Map<Serializable, Persistente>> getMap() {
        return map;
    }
}
