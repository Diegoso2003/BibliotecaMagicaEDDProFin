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
    private final Cola<InfoLibro> cola = new Cola<>();
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
    
    public boolean verificarTiempo(){
        return !cola.estaVacia();
    }
    
    public InfoLibro obtenerSiguiente(){
        return cola.obtenerPrimeroCola();
    }
    
    public boolean desencolar(long tiempo){
        auxiliar -= tiempo;
        if(auxiliar <= 0){
            auxiliar = this.tiempo;
            return true;
        }
        return false;
    }
    
    public void encolar(InfoLibro libro){
        cola.agregar(libro);
    }
}