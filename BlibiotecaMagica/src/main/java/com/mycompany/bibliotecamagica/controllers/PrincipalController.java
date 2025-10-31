/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.App;
import com.mycompany.bibliotecamagica.backend.enums.VistasEnum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class PrincipalController implements Initializable {

    private VistasEnum actual = null;
    
    @FXML private BorderPane principal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarVista(VistasEnum.CARGAR_ARCHIVO);
    }
    
    private void cargarVista(VistasEnum nuevaVista){
        try{
            if(actual != null && actual == nuevaVista) return;
            actual = nuevaVista;
            FXMLLoader fxml = new FXMLLoader(App.class.getResource(nuevaVista.getVentana() + ".fxml"));
            Node vista = fxml.load();
            principal.setCenter(null);
            principal.setCenter(vista);
        } catch(IOException e){
            System.out.println(e);
            //e.printStackTrace();
        }
    }
    
    @FXML
    private void cargarArchivo(){
        cargarVista(VistasEnum.CARGAR_ARCHIVO);
    }
    
    @FXML
    private void mostrarGrafo(){
        cargarVista(VistasEnum.GRAFICA_GRAFO);
    }
    
    @FXML
    private void cargarFormBiblioteca(){
        cargarVista(VistasEnum.FORM_BIBLIOTECA);
    }
    
    @FXML
    private void cargarFormConexion(){
        cargarVista(VistasEnum.FORM_CONEXION);
    }
    
    @FXML
    private void cargarRegistrosTraslados(){
        cargarVista(VistasEnum.REGISTRO_TRASLADO);
    }
    
    @FXML
    private void cargarColas(){
        cargarVista(VistasEnum.COLAS_DESPACHO);
    }
}