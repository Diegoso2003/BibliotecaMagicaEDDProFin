/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

/**
 *
 * @author rafael-cayax
 */
public class Biblioteca implements Comparable<Biblioteca>{
    private String id;
    private String nombre;
    private String ubicacion;
    private long tIngreso;
    private long tTraspaso;
    private long dIntervalo;

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

    public void setId(String id) {
        this.id = id;
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

}
