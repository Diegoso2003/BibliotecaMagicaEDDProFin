/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorClaves;
import com.mycompany.bibliotecamagica.backend.comparadores.IdentificadorAutor;
import com.mycompany.bibliotecamagica.backend.comparadores.IdentificadorGenero;
import com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl.ArbolAvl;
import com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl.GeneradorDotAvl;
import com.mycompany.bibliotecamagica.backend.estructuras.arbol_b.ArbolB;
import com.mycompany.bibliotecamagica.backend.estructuras.arbol_b_mas.ArbolBMas;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.estructuras.tabla_hash.TablaHash;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.frontend.BibliotecaFrontend;
import java.util.Objects;

/**
 *
 * @author rafael-cayax
 */
public class Biblioteca implements Comparable<Biblioteca>{
    private final String id;
    private final BibliotecaFrontend biblioColas;
    private String nombre;
    private String ubicacion;
    private EstadoCola tIngreso;
    private EstadoCola tTraspaso;
    private EstadoCola despacho;
    private ArbolAvl<LibroBiblioteca> librosPorTitulo;
    private ArbolBMas<LibroBiblioteca> librosPorGenero;
    private ArbolBMas<LibroBiblioteca> librosPorAutor;
    private ListaSimple<LibroBiblioteca> librosLista;
    private ArbolB librosPorAño;
    private TablaHash tabla;

    public Biblioteca(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        biblioColas = new BibliotecaFrontend(this);
        librosPorAño = new ArbolB();
        librosLista = new ListaSimple();
        librosPorGenero = new ArbolBMas<>(new IdentificadorGenero());
        librosPorAutor = new ArbolBMas<>(new IdentificadorAutor());
        librosPorTitulo = new ArbolAvl<>(VarGlobales.COM_TITULO);
        tabla = new TablaHash();
    }
    
    /**
     * constructor hecho solo para busquedas
     * @param id 
     */
    public Biblioteca(String id){
        this.id = id;
        biblioColas = null;
    }

    
    public void agregarLibro(InfoLibro libro){
        if(tabla.agregar(libro)){
            LibroBiblioteca nuevo = tabla.buscar(libro.getLibro());
            librosPorTitulo.agregarElemento(nuevo);
            librosPorGenero.agregarElemento(nuevo);
            librosPorAutor.agregarElemento(nuevo);
            librosPorAño.agregarLibro(nuevo);
        }
    }
    
    public String getId() {
        return id;
    }
    
    public String getIDNumerico(){
        StringBuilder aux = new StringBuilder();
        for(int i = 0; i < id.length(); i++){
            char c = id.charAt(i);
            if(c != '-') aux.append(c);
        }
        return aux.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void settIngreso(long tIngreso) {
        biblioColas.getTiempoIngreso().setText(tIngreso + "");
        this.tIngreso = new EstadoCola(tIngreso, biblioColas.getTiempoIngreso());
    }

    public void settTraspaso(long tTraspaso) {
        biblioColas.getTiempoTraspaso().setText(tTraspaso + "");
        this.tTraspaso = new EstadoCola(tTraspaso, biblioColas.getTiempoTraspaso());
    }
    
    public void setdIntervalo(long dIntervalo){
        biblioColas.getTiempoDespacho().setText(dIntervalo + "");
        this.despacho = new EstadoCola(dIntervalo, biblioColas.getTiempoDespacho());
    }

    public boolean verificarEstadoColas(){
        return !tIngreso.estaVacia() || !tTraspaso.estaVacia() || !despacho.estaVacia();
    }
    
    public synchronized void colocarEnEntrada(EntradaLibro libro){
        tIngreso.encolar(libro);
        biblioColas.colocarEnEntrada(libro.getLibro().getLibro());
    }
    
    public synchronized void colocarEnTraspaso(EntradaLibro libro){
        tTraspaso.encolar(libro);
        biblioColas.colocarEnTraspaso(libro.getLibro().getLibro());
    }
    
    public void colocarEnDespacho(EntradaLibro libro){
        despacho.encolar(libro);
        biblioColas.colocarEnDespacho(libro.getLibro().getLibro());
    }

    public EstadoCola gettIngreso() {
        return tIngreso;
    }

    public EstadoCola gettTraspaso() {
        return tTraspaso;
    }

    public EstadoCola getDespacho() {
        return despacho;
    }

    public BibliotecaFrontend getBiblioColas() {
        return biblioColas;
    }

    public String obtenerDotAvl(){
        GeneradorDotAvl generador = new GeneradorDotAvl();
        return generador.obtenerDot(librosPorTitulo.getRaiz());
    }
    
    public String obtenerDotBMasGenero(){
        return librosPorGenero.obtenerDotArbolBMas();
    }
    
    public String obtenerDotBMasAutor(){
        return librosPorAutor.obtenerDotArbolBMas();
    }
    
    public String obtenerDotBAño(){
        return librosPorAño.obtenerDotAño();
    }

    public ListaSimple<LibroBiblioteca> getLibrosLista() {
        return librosLista;
    }
    
    public String obtenerDotTabla(){
        return tabla.obtenerDotTabla();
    }
    
    public ListaDoble<LibroBiblioteca> buscarPorTitulo(String titulo) throws EntradaException{
        if(librosPorTitulo.estaVacia()){
            throw new EntradaException("biblioteca vacia ingresar libros para busqueda");
        }
        Libro libroAux = new Libro(null);
        libroAux.setTitulo(titulo);
        return librosPorTitulo.buscar(new LibroBiblioteca(libroAux));
    }
    
    public ListaDoble<LibroBiblioteca> buscarPorGenero(String genero) throws EntradaException{
        if(librosPorGenero.estaVacia()){
            throw new EntradaException("biblioteca vacia ingresar libros para busqueda");
        }
        Libro libroAux = new Libro(null);
        libroAux.setGenero(genero);
        return librosPorGenero.buscar(new LibroBiblioteca(libroAux));
    }
    
    public ListaDoble<LibroBiblioteca> buscarPorAutor(String autor) throws EntradaException{
        if(librosPorAutor.estaVacia()){
            throw new EntradaException("biblioteca vacia ingresar libros para busqueda");
        }
        Libro libroAux = new Libro(null);
        libroAux.setAutor(autor);
        return librosPorAutor.buscar(new LibroBiblioteca(libroAux));
    }
    
    public LibroBiblioteca buscarPorIsbn(String isbn) throws EntradaException{
        if(tabla.estaVacia()){
            throw new EntradaException("biblioteca vacia ingresar libros para busqueda");
        }
        Libro libroAux = new Libro(isbn);
        return tabla.buscar(libroAux);
    }

    public ArbolAvl<LibroBiblioteca> getLibrosPorTitulo() {
        return librosPorTitulo;
    }
    
    @Override
    public int compareTo(Biblioteca o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Biblioteca other = (Biblioteca) obj;
        return Objects.equals(this.id, other.id);
    }

    public void buscarPorTituloLista(String entrada) {
        ComparadorClaves comparador = new ComparadorClaves();
        Libro libroAux = new Libro(null);
        libroAux.setTitulo(entrada);
        LibroBiblioteca libro = new LibroBiblioteca(libroAux);
        IteradorLista<LibroBiblioteca> l = this.librosLista.getIterador();
        while(l.haySiguiente()){
            if(comparador.compare(libro.getLibro().getTitulo(), l.getActual().getLibro().getTitulo()) == 0){
                break;
            }
        }
    }
    
}