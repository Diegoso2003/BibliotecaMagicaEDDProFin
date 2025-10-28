/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import java.util.Objects;

/**
 *
 * @author rafael-cayax
 */
public class Biblioteca implements Comparable<Biblioteca>{
    private final String id;
    private String nombre;
    private String ubicacion;
    private long tIngreso;
    private long tTraspaso;
    private long dIntervalo;

    public Biblioteca(String id) {
        this.id = id;
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

    public long gettIngreso() {
        return tIngreso;
    }

    public void settIngreso(long tIngreso) {
        this.tIngreso = tIngreso;
    }

    public long gettTraspaso() {
        return tTraspaso;
    }

    public void settTraspaso(long tTraspaso) {
        this.tTraspaso = tTraspaso;
    }

    public long getdIntervalo() {
        return dIntervalo;
    }

    public void setdIntervalo(long dIntervalo) {
        this.dIntervalo = dIntervalo;
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