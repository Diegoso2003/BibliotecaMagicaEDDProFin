/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.estructuras.cola.Cola;

/**
 *
 * @author rafael-cayax
 */
public class EstadoCola {
    private final Cola<EntradaLibro> cola = new Cola<>();
    private final long tiempo;
    private long auxiliar;
    
    public EstadoCola(long tiempo) {
        this.tiempo = tiempo;
        this.auxiliar = tiempo;
    }

    public long getTiempo() {
        return tiempo;
    }

    public long getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(long auxiliar) {
        this.auxiliar = auxiliar;
    }
    
    public boolean estaVacia(){
        return !cola.estaVacia();
    }
    
    public boolean desencolar(long tiempo){
        if(cola.estaVacia()) return false;
        auxiliar -= tiempo;
        if(auxiliar <= 0){
            auxiliar = this.tiempo;
            return true;
        }
        return false;
    }

    public EntradaLibro obtenerPrimeroEnCola(){
        return cola.obtenerPrimeroCola();
    }
    
    public void encolar(EntradaLibro libro){
        cola.agregar(libro);
    }
}