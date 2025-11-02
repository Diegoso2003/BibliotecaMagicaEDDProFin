/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl.ArbolAvl;
import com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl.GeneradorDotAvl;
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
    private ArbolAvl<InfoLibro> librosPorTitulo;

    public Biblioteca(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        biblioColas = new BibliotecaFrontend(this);
        librosPorTitulo = new ArbolAvl<>(VarGlobales.COM_TITULO);
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
        librosPorTitulo.agregarElemento(libro);
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
    
}