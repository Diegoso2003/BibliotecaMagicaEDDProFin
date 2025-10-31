/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class ColasDespachoController implements Initializable {

    @FXML private VBox colasDeDespacho;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IteradorLista<NodoGrafo> bibliotecas = RedBibliotecas.INSTANCIA.getBibliotecas().getIterador();
        while(bibliotecas.haySiguiente()){
            colasDeDespacho.getChildren().add(bibliotecas.getActual().getBiblioteca().getBiblioColas().getVista());
        }
    }    
    
}
