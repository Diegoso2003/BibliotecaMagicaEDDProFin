/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.buscadores;

import com.mycompany.bibliotecamagica.backend.estructuras.arbol_b.AuxiliarBusquedaB;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class BuscadorAño extends Buscador {

    private AuxiliarBusquedaB aux;

    public BuscadorAño() {
        super("Buscar por rango de fecha");
    }

    @Override
    protected void validarEntrada(String texto) throws EntradaException {
        String año1 = "";
        String año2 = "";
        int estado = 1;
        for (char c : texto.toCharArray()) {
            switch (estado) {
                case 1:
                    if (esNumero(c) && c != '0') {
                        año1 += c;
                        estado = 2;
                    } else {
                        estado = 20;
                    }
                    break;
                case 2:
                    if (esNumero(c)) {
                        año1 += c;
                    } else if (c == '-') {
                        estado = 3;
                    } else {
                        estado = 20;
                    }
                    break;
                case 3:
                    if (esNumero(c) && c != '0') {
                        año2 += c;
                        estado = 4;
                    } else {
                        estado = 20;
                    }
                    break;
                case 4:
                    if (esNumero(c)) {
                        año2 += c;
                    } else {
                        estado = 20;
                    }
                    break;
                default:
                    throw new EntradaException("Formato de busqueda de año invalido");
            }
        }
        if (estado != 2 && estado != 4) {
            throw new EntradaException("Formato de busqueda de año invalido");
        }
        int fechaInicio = Integer.parseInt(año1);
        int fechaFin = año2.isEmpty() ? fechaInicio : Integer.parseInt(año2);
        aux = new AuxiliarBusquedaB(fechaFin, fechaInicio);
    }

    @Override
    protected ListaSimple<LibroBiblioteca> obtenerBusqueda(String entrada) throws EntradaException {
        return biblioteca.getLibrosPorAño().getListaPorRango(aux);
    }

    @Override
    public boolean obtenerMensaje() {
        return false;
    }

    private boolean esNumero(char c) {
        return c >= '0' && c <= '9';
    }

}
