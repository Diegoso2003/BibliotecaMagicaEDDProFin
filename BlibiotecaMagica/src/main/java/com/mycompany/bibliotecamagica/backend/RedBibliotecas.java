/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

import com.mycompany.bibliotecamagica.backend.calculo_ruta.Dijsktra;
import com.mycompany.bibliotecamagica.backend.calculo_ruta.InfoTraslado;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.hilos.ManejadorColas;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public enum RedBibliotecas {
    INSTANCIA;
    
    private final ListaDoble<NodoGrafo> bibliotecas;
    private final ListaDoble<InfoTraslado> traslados;
    private final Dijsktra d;
    private ManejadorColas manejador;

    private RedBibliotecas() {
        bibliotecas = new ListaDoble<>();
        traslados = new ListaDoble<>();
        d = new Dijsktra();
        manejador = new ManejadorColas();
    }
    
    public void agregarNuevoTraslado(Biblioteca biblioteca){
        if(manejador.estaLLeno()){
            manejador = new ManejadorColas();
            manejador.setDaemon(true);
            manejador.agregarBiblioteca(biblioteca);
            manejador.start();
            return;
        }
        manejador.agregarBiblioteca(biblioteca);
        if(!manejador.isAlive()){
            manejador = new ManejadorColas();
            manejador.setDaemon(true);
            manejador.agregarBiblioteca(biblioteca);
            manejador.start();
        }
    }
    
    public boolean agregar(Biblioteca nueva){
        NodoGrafo nodo = new NodoGrafo(nueva);
        return bibliotecas.agregar(nodo);
    }
    
    public Optional<NodoGrafo> buscar(String origen){
        Biblioteca biblioTemporal = new Biblioteca(origen);
        NodoGrafo nodoTemporal = new NodoGrafo(biblioTemporal);
        return bibliotecas.buscar(nodoTemporal);
    }
    
    public String obtenerDot(){
        ObtenerDotGrafo g = new ObtenerDotGrafo(bibliotecas);
        return g.obtenerDotGrafo();
    }
    
    public ListaDoble<NodoGrafo> getBibliotecas() {
        return bibliotecas;
    }

    public ListaDoble<InfoTraslado> getTraslados() {
        return traslados;
    }

    public Dijsktra getD() {
        return d;
    }
    
}