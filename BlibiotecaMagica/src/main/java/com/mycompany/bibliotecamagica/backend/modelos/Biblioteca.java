/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

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
    private EstadoCola dIntervalo;

    public Biblioteca(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        biblioColas = new BibliotecaFrontend(this);
    }
    
    /**
     * constructor hecho solo para busquedas
     * @param id 
     */
    public Biblioteca(String id){
        this.id = id;
        biblioColas = null;
    }

    
    public synchronized void agregarLibro(InfoLibro libro){
        
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
        this.tIngreso = new EstadoCola(tIngreso);
    }

    public void settTraspaso(long tTraspaso) {
        this.tTraspaso = new EstadoCola(tTraspaso);
    }
    
    public void setdIntervalo(long dIntervalo){
        this.dIntervalo = new EstadoCola(dIntervalo);
    }

    public boolean verificarEstadoCola(){
        return tIngreso.verificarTiempo() || dIntervalo.verificarTiempo() || dIntervalo.verificarTiempo();
    }
    
    public EstadoCola gettIngreso() {
        return tIngreso;
    }

    public EstadoCola gettTraspaso() {
        return tTraspaso;
    }

    public EstadoCola getdIntervalo() {
        return dIntervalo;
    }

    public BibliotecaFrontend getBiblioColas() {
        return biblioColas;
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