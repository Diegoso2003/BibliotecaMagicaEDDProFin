/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.EntradaLibro;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorLibro extends Validador<EntradaLibro>{
    private StringBuilder titulo;
    private StringBuilder isbn;
    private StringBuilder genero;
    private StringBuilder año;
    private StringBuilder autor;
    private StringBuilder estado;
    private StringBuilder origen;
    private StringBuilder destino;
    private StringBuilder prioridad;

    @Override
    protected void extraerDatos() throws EntradaException {
        int inicio = buscarCampo(titulo, 0, false);
        inicio = buscarCampo(isbn, inicio, false);
        inicio = buscarCampo(genero, inicio, false);
        inicio = buscarCampo(año, inicio, false);
        inicio = buscarCampo(autor, inicio, false);
        inicio = buscarCampo(estado, inicio, false);
        inicio = buscarCampo(origen, inicio, false);
        inicio = buscarCampo(origen, inicio, false);
        inicio = buscarCampo(destino, inicio, false);
        inicio = buscarCampo(prioridad, inicio, true);
        validarFinal(inicio);
    }

    @Override
    protected void limpiarCampos() {
        titulo = new StringBuilder();
        isbn = new StringBuilder();
        genero = new StringBuilder();
        año = new StringBuilder();
        autor = new StringBuilder();
        estado = new StringBuilder();
        origen = new StringBuilder();
        destino = new StringBuilder();
        prioridad = new StringBuilder();
    }

    @Override
    protected boolean camposValidos() {
        return !titulo.toString().isBlank() && !isbn.toString().isBlank() && !genero.toString().isBlank()
                && !año.toString().isBlank() && !autor.toString().isBlank() && !estado.toString().isBlank()
                && !origen.toString().isBlank() && !destino.toString().isBlank() && !prioridad.toString().isBlank();
    }

    @Override
    protected EntradaLibro validarYObtenerRegistro() throws EntradaException {
        if(!esIsbnValido()) throw new EntradaException("isbn invalido: \"" + isbn + "\"");
        int añoLibro = obtenerAño();
        validarIDBiblio(origen);
        validarIDBiblio(destino);
        return new EntradaLibro();
    }

    @Override
    protected void agregarRegistro(EntradaLibro nuevo) {
        
    }
    
    private boolean esIsbnValido(){
        if(isbn.length() < 13 || !isbn.subSequence(0, 3).equals("978") || !isbn.subSequence(0, 3).equals("979")){
            return false;
        }
        int contador = 3;
        for(int i = 3; i < isbn.length(); i++){
            char c = isbn.charAt(i);
            if(!esNumero(c) && c != '-'){
                return false;
            }
            if(esNumero(c)) contador++;
        }
        return contador == 13;
    }
    
    private int obtenerAño() throws EntradaException{
        try {
            if(año.charAt(0) == '0') throw new NumberFormatException();
            return Integer.parseInt(año.toString());
        } catch(NumberFormatException e){
            throw new EntradaException("Año invalido: \"" + año + "\"");
        }
    }
}