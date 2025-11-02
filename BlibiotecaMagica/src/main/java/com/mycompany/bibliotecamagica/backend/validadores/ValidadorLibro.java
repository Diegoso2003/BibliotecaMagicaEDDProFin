/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;
import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.EntradaLibro;
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
        if(!esIsbnValido(isbn.toString())) throw new EntradaException("isbn invalido: \"" + isbn + "\"");
        Libro libro = obtenerLibro();
        validarIDBiblio(origen);
        validarIDBiblio(destino);
        Optional<PrioridadEnum> prioridad2 = PrioridadEnum.obtenerPrioridad(prioridad.toString());
        actual = prioridad2.orElseThrow(() -> new EntradaException("Prioridad ingresada invalida: \"" + prioridad + "\""));
        InfoLibro info = new InfoLibro(libro ,
        EstadoLibroEnum.obtenerEstado(estado.toString()).orElseThrow(() -> new EntradaException("Estado de libro no valido: " + estado)));
        return info;
    }

    @Override
    protected void agregarRegistro(InfoLibro nuevo) throws EntradaException {
        EntradaLibro entrada = RedBibliotecas.INSTANCIA.getD().
                calcularRuta(origen.toString(), destino.toString(), nuevo, actual);
        Biblioteca primera = entrada.obtenerSiguiente();
        primera.colocarEnEntrada(entrada);
        RedBibliotecas.INSTANCIA.agregarNuevoTraslado(primera);
    }
    
    public boolean esIsbnValido(String isbn){
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
                throw new EntradaException(String.format("El año debe ser mayor a: %,d, año ingresado: %,d",VarGlobales.MIN_AÑO,añoint));
            }
            return añoint;
        } catch(NumberFormatException e){
            throw new EntradaException("Año ingresado invalido: \"" + año + "\"");
        }
    }

    public Libro obtenerLibro() throws EntradaException {
        Libro libro = new Libro(isbn.toString());
        Libro libro2 = RedBibliotecas.INSTANCIA.getLibros().get(libro.getSinGuiones());
        libro.setAutor(autor.toString());
        libro.setAño(obtenerAño());
        libro.setGenero(genero.toString());
        libro.setTitulo(titulo.toString());
        if(libro2 != null){
            if(!libro2.getAutor().equalsIgnoreCase(libro.getAutor()) ||
                    !libro2.getAño().equals(libro.getAño()) || 
                    !libro2.getGenero().equalsIgnoreCase(libro.getGenero()) ||
                    !libro2.getTitulo().equalsIgnoreCase(libro.getTitulo())){
                throw new EntradaException("Ya hay un libro registrado con el mismo isbn y diferentes datos");
            }
            libro = libro2;
        } else {
            RedBibliotecas.INSTANCIA.getLibros().put(libro.getSinGuiones(), libro);
        }
        return libro;
    }

    public void setTitulo(StringBuilder titulo) {
        this.titulo = titulo;
    }

    public void setIsbn(StringBuilder isbn) {
        this.isbn = isbn;
    }

    public void setGenero(StringBuilder genero) {
        this.genero = genero;
    }

    public void setAño(StringBuilder año) {
        this.año = año;
    }

    public void setAutor(StringBuilder autor) {
        this.autor = autor;
    }

}