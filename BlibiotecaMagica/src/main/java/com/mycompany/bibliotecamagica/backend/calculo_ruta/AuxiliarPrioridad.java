/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.calculo_ruta;

import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import java.math.BigDecimal;

/**
 *
 * @author rafael-cayax
 */
public class AuxiliarPrioridad implements Comparable<AuxiliarPrioridad>{
    private final PrioridadEnum prioridad;
    private final NodoGrafo nodo;
    private final Long tiempo;
    private final BigDecimal costo;

    public AuxiliarPrioridad(PrioridadEnum prioridad, NodoGrafo nodo, Long tiempo, BigDecimal costo) {
        this.prioridad = prioridad;
        this.nodo = nodo;
        this.tiempo = tiempo;
        this.costo = costo;
    }

    @Override
    public int compareTo(AuxiliarPrioridad o) {
        if(prioridad == PrioridadEnum.TIEMPO){
            return tiempo.compareTo(o.tiempo);
        } else {
            return costo.compareTo(costo);
        }
    }

    public NodoGrafo getNodo() {
        return nodo;
    }

    public Long getTiempo() {
        return tiempo;
    }

    public BigDecimal getCosto() {
        return costo;
    }
    
}