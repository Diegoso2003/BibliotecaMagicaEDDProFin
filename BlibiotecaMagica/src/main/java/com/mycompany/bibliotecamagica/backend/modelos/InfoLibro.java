/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;
import java.util.TreeMap;

/**
 *
 * @author rafael-cayax
 */
public class InfoLibro implements Comparable<InfoLibro>{
    private final Libro libro;
    private final TreeMap<EstadoLibroEnum, Integer> estados;

    public InfoLibro(Libro libro, EstadoLibroEnum estado) {
        this.libro = libro;
        estados = new TreeMap<>();
        estados.put(EstadoLibroEnum.AGOTADO, 0);
        estados.put(EstadoLibroEnum.DISPONIBLE, 0);
        estados.put(EstadoLibroEnum.EN_TRANSITO, 0);
        estados.put(EstadoLibroEnum.PRESTADO, 0);
        estados.put(estado, 1);
    }

    public Libro getLibro() {
        return libro;
    }
    
    public boolean tieneDisponible(){
        Integer valor = estados.get(EstadoLibroEnum.DISPONIBLE);
        return valor > 0;
    }
    
    public boolean tieneAgotado(){
        Integer valor = estados.get(EstadoLibroEnum.AGOTADO);
        return valor > 0;
    }
    
    public boolean tieneEnTransito(){
        Integer valor = estados.get(EstadoLibroEnum.EN_TRANSITO);
        return valor > 0;
    }

    public boolean tieneEnPrestamo(){
        Integer valor = estados.get(EstadoLibroEnum.PRESTADO);
        return valor > 0;
    }
    
    public void disminuirAgotado(){
        Integer valor = estados.get(EstadoLibroEnum.AGOTADO);
        valor--;
        if(valor >= 0){
            estados.put(EstadoLibroEnum.AGOTADO, valor);
        }
    }
    
    public void disminuirPrestado(){
        Integer valor = estados.get(EstadoLibroEnum.PRESTADO);
        valor--;
        if(valor >= 0){
            estados.put(EstadoLibroEnum.PRESTADO, valor);
        }
    }
    
    public void disminuirEnTransito(){
        Integer valor = estados.get(EstadoLibroEnum.EN_TRANSITO);
        valor--;
        if(valor >= 0){
            estados.put(EstadoLibroEnum.EN_TRANSITO, valor);
        }
    }
    
    public void disminuirDisponible(){
        Integer valor = estados.get(EstadoLibroEnum.DISPONIBLE);
        valor--;
        if(valor >= 0){
            estados.put(EstadoLibroEnum.DISPONIBLE, valor);
        }
    }
    
    public void aumentarDisponible(){
        Integer valor = estados.get(EstadoLibroEnum.DISPONIBLE);
        valor++;
        estados.put(EstadoLibroEnum.DISPONIBLE, valor);
    }
    
    public void aumentarPrestado(){
        Integer valor = estados.get(EstadoLibroEnum.PRESTADO);
        valor++;
        estados.put(EstadoLibroEnum.PRESTADO, valor);
    }
    
    public void aumentarAgotado(){
        Integer valor = estados.get(EstadoLibroEnum.AGOTADO);
        valor++;
        estados.put(EstadoLibroEnum.AGOTADO, valor);
    }
    
    public void aumentarEnTraslado(){
        Integer valor = estados.get(EstadoLibroEnum.EN_TRANSITO);
        valor++;
        estados.put(EstadoLibroEnum.EN_TRANSITO, valor);
    }

    @Override
    public int compareTo(InfoLibro o) {
        return libro.compareTo(o.libro);
    }
    
}