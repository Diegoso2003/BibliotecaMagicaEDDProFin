/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;
import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.cola.Cola;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.InfoLibro;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorLibro extends Validador<InfoLibro>{
    private StringBuilder titulo;
    private StringBuilder isbn;
    private StringBuilder genero;
    private StringBuilder año;
    private StringBuilder autor;
    private StringBuilder estado;
    private StringBuilder origen;
    private StringBuilder destino;
    private StringBuilder prioridad;
    private PrioridadEnum actual;

    @Override
    protected void extraerDatos() throws EntradaException {
        int inicio = buscarCampo(titulo, 0, false);
        inicio = buscarCampo(isbn, inicio, false);
        inicio = buscarCampo(genero, inicio, false);
        inicio = buscarCampo(año, inicio, false);
        inicio = buscarCampo(autor, inicio, false);
        inicio = buscarCampo(estado, inicio, false);
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
    protected InfoLibro validarYObtenerRegistro() throws EntradaException {
        if(!esIsbnValido()) throw new EntradaException("isbn invalido: \"" + isbn + "\"");
        Libro libro = obtenerLibro();
        Optional<PrioridadEnum> prioridad2 = PrioridadEnum.obtenerPrioridad(prioridad.toString());
        actual = prioridad2.orElseThrow(() -> new EntradaException("Prioridad ingresada invalida: \"" + prioridad + "\""));
        InfoLibro info = new InfoLibro(libro ,
        EstadoLibroEnum.obtenerEstado(estado.toString()).orElseThrow(() -> new EntradaException("Estado de libro no valido")));
        return info;
    }

    @Override
    protected void agregarRegistro(InfoLibro nuevo) throws EntradaException {
        Cola<Biblioteca> ruta = RedBibliotecas.INSTANCIA.getD().
                calcularRuta(origen.toString(), destino.toString(), nuevo.getLibro(), actual);
    }
    
    private boolean esIsbnValido(){
        if(isbn.length() < 13 || (!isbn.subSequence(0, 3).equals("978") && !isbn.subSequence(0, 3).equals("979"))){
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
            int añoint =  Integer.parseInt(año.toString());
            if(añoint < VarGlobales.MIN_AÑO){
                throw new EntradaException(String.format("El año debe ser mayor a: %,d, año ingresado: %,d",VarGlobales.MIN_AÑO,año));
            }
            return añoint;
        } catch(NumberFormatException e){
            throw new EntradaException("Año ingresado invalido: \"" + año + "\"");
        }
    }

    private void validarLibro() {
        //metodo para buscar en el catalogo de libros para verificar que el libro tengo un isbn
        //que corresponda a los datos que tiene
    }

    private Libro obtenerLibro() throws EntradaException {
        //agregar logica para buscar el libro y validad que tenga los mismos datos si no existe crear otro
        validarLibro();
        Libro libro = new Libro(isbn.toString());
        libro.setAutor(autor.toString());
        libro.setAño(obtenerAño());
        libro.setGenero(genero.toString());
        libro.setTitulo(titulo.toString());
        validarIDBiblio(origen);
        validarIDBiblio(destino);
        return libro;
    }
}