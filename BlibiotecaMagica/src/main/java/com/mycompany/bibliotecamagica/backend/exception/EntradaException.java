/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.exception;

/**
 *
 * @author rafael-cayax
 */
public class EntradaException extends Exception {

    /**
     * Creates a new instance of <code>EntradaException</code> without detail
     * message.
     */
    public EntradaException() {
    }

    /**
     * Constructs an instance of <code>EntradaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EntradaException(String msg) {
        super(msg);
    }
}
