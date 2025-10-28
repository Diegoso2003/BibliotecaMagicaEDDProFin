/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.Conexion;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class FormConexionController implements Initializable {
    @FXML private ComboBox<NodoGrafo> bibliotecaOrigen;
    @FXML private ComboBox<NodoGrafo> bibliotecaDestino;
    @FXML private Spinner<Integer> tiempo;
    @FXML private Spinner<BigDecimal> costo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<NodoGrafo> lista1 = FXCollections.observableArrayList();
        ObservableList<NodoGrafo> lista2 = FXCollections.observableArrayList();
        IteradorLista<NodoGrafo> iterador = RedBibliotecas.INSTANCIA.getBibliotecas().getIterador();
        while(iterador.haySiguiente()){
            lista1.add(iterador.getActual());
            lista2.add(iterador.getActual());
        }
        bibliotecaOrigen.setItems(lista1);
        bibliotecaDestino.setItems(lista2);
        Auxiliar.crearSpinnerConFiltro(tiempo);
        Auxiliar.crearSpinnerPrecio(costo);
    }    
    
    @FXML
    private void agregarConexion(){
        try {
            validarDatos();
            agregarNuevaConexion();
            limpiarCampos();
            Auxiliar.lanzarAlerta(Alert.AlertType.INFORMATION, "Exito", "Conexion agregada", costo);
        } catch (EntradaException ex) {
            Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), costo);
        }
    }

    private void validarDatos() throws EntradaException {
        if(bibliotecaOrigen.getValue() == null || bibliotecaDestino.getValue() == null
                || tiempo.getValue() == null || costo.getValue() == null){
            throw new EntradaException("Llenar correctamente todos los campos");
        }
        NodoGrafo origen = bibliotecaOrigen.getValue();
        NodoGrafo destino = bibliotecaDestino.getValue();
        if(origen.getBiblioteca().equals(destino)){
            throw new EntradaException("Una biblioteca no puede conectarse con sigo misma.");
        }
    }

    private void agregarNuevaConexion() throws EntradaException {
        Conexion conexion = new Conexion();
        conexion.setBiblioAdyascente(bibliotecaDestino.getValue());
        conexion.setPrecio(costo.getValue());
        conexion.setTiempo(tiempo.getValue());
        if(!bibliotecaOrigen.getValue().agregarConexion(conexion)){
            throw new EntradaException("Ya existe una conexion entre ambas bibliotecas");
        }
    }

    private void limpiarCampos() {
        costo.getValueFactory().setValue(VarGlobales.PRECIO_DEFAULT);
        tiempo.getValueFactory().setValue(VarGlobales.TIEMPO_DEFAULT);
    }
    
}