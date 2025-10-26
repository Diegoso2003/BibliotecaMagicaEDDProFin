/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class FormConexionController implements Initializable {
    @FXML private ComboBox<NodoGrafo> bibliotecaOrigen;
    @FXML private ComboBox<Biblioteca> bibliotecaDestino;
    @FXML private Spinner<Integer> tiempo;
    @FXML private Spinner<Double> costo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<NodoGrafo> lista1 = FXCollections.observableArrayList();
        ObservableList<Biblioteca> lista2 = FXCollections.observableArrayList();
        IteradorLista<NodoGrafo> iterador = RedBibliotecas.INSTANCIA.getBibliotecas().getIterador();
        while(iterador.haySiguiente()){
            lista1.add(iterador.getActual());
            lista2.add(iterador.getActual().getBiblioteca());
        }
        bibliotecaOrigen.setItems(lista1);
        bibliotecaDestino.setItems(lista2);
        crearSpinnerConFiltro(tiempo);
    }    
    
    @FXML
    private void agregarConexion(){
        
    }
    
    private void crearSpinnerConFiltro(Spinner spinner) {
        var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(VarGlobales.MIN_TIEMPO, 
                VarGlobales.MAX_TIEMPO, VarGlobales.TIEMPO_DEFAULT, VarGlobales.INCREMENTO);
        spinner.setValueFactory(factory);
        TextField editor = spinner.getEditor();
        editor.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String newText = change.getControlNewText();            
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d*")){
                try{
                    Integer.valueOf(newText);
                } catch(NumberFormatException e){
                    return null;
                }
                return change;
            }
            return null;
        }));

    }
    
}
