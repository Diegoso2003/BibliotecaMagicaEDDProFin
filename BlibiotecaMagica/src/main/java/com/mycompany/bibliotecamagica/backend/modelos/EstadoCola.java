/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.estructuras.cola.Cola;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author rafael-cayax
 */
public class EstadoCola {
    private final Cola<EntradaLibro> cola = new Cola<>();
    private final long tiempo;
    private final Label contador;
    private long auxiliar;
    
    public EstadoCola(long tiempo, Label contador) {
        this.tiempo = tiempo;
        this.auxiliar = tiempo;
        this.contador = contador;
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
        return cola.estaVacia();
    }

    public EntradaLibro obtenerPrimeroEnCola(){
        return cola.obtenerPrimeroCola();
    }
    
    public void encolar(EntradaLibro libro){
        cola.agregar(libro);
    }
    
    public boolean desencolar(long tiempo){
        if(cola.estaVacia()) return false;
        auxiliar -= tiempo;
        if(auxiliar <= 0){
            auxiliar = this.tiempo;
            Platform.runLater(() -> {
                contador.setText(this.tiempo + "");
            });
            return true;
        }
        Platform.runLater(() -> {
            contador.setText(auxiliar + "");
        });
        return false;
    }
}