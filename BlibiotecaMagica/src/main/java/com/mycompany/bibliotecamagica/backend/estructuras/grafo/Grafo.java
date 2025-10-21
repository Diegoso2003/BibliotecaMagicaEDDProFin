/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.grafo;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class Grafo {
    private final ListaDoble<NodoGrafo> bibliotecas;

    public Grafo() {
        bibliotecas = new ListaDoble<>();
    }
    
    public boolean agregar(Biblioteca nueva){
        NodoGrafo nodo = new NodoGrafo(nueva);
        return bibliotecas.agregar(nodo);
    }
    
    public Optional<NodoGrafo> buscar(String origen){
        Biblioteca biblioTemporal = new Biblioteca();
        biblioTemporal.setId(origen);
        NodoGrafo nodoTemporal = new NodoGrafo(biblioTemporal);
        return bibliotecas.buscar(nodoTemporal);
    }

    public ListaDoble<NodoGrafo> getBibliotecas() {
        return bibliotecas;
    }
    
}
