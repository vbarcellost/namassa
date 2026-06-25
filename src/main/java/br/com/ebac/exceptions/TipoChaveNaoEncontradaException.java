package br.com.ebac.exceptions;

public class TipoChaveNaoEncontradaException extends Exception {

    public TipoChaveNaoEncontradaException(String message) {
        super(message);
    }

    public TipoChaveNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
