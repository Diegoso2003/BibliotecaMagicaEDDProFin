/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.validadores.ValidadorBiblioteca;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class FormBibliotecaController implements Initializable {
    @FXML private TextField idBiblioteca;
    @FXML private TextField nombreBiblioteca;
    @FXML private TextField ubicacionBiblioteca;
    @FXML private Spinner<Integer> tIngreso;
    @FXML private Spinner<Integer> tTransporte;
    @FXML private Spinner<Integer> intDespacho;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Auxiliar.crearSpinnerConFiltro(tIngreso);
        Auxiliar.crearSpinnerConFiltro(tTransporte);
        Auxiliar.crearSpinnerConFiltro(intDespacho);
    }    
    
    @FXML
    private void agregarBiblioteca(){
        try {
            String texto = crearEntrada();
            ValidadorBiblioteca v = new ValidadorBiblioteca();
            v.iniciarAnalisis(texto);
            limpiarCampos();
            Auxiliar.lanzarAlerta(Alert.AlertType.INFORMATION, "Exito", "Biblioteca agregada correctamente", idBiblioteca);
        } catch (EntradaException ex) {
            Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), idBiblioteca);
        }
    }

    private void limpiarCampos() {
        idBiblioteca.clear();
        nombreBiblioteca.clear();
        ubicacionBiblioteca.clear();
        tIngreso.getValueFactory().setValue(VarGlobales.TIEMPO_DEFAULT);
        tTransporte.getValueFactory().setValue(VarGlobales.TIEMPO_DEFAULT);
        intDespacho.getValueFactory().setValue(VarGlobales.TIEMPO_DEFAULT);
    }

    private String crearEntrada() throws EntradaException {
        if(tIngreso.getValue() == null || tTransporte.getValue() == null || intDespacho.getValue() == null){
            throw new EntradaException("LLenar todos los campos");
        }
        StringBuilder st = new StringBuilder();
        st.append('"').append(idBiblioteca.getText()).append("\",")
                .append('"').append(nombreBiblioteca.getText()).append("\",")
                .append('"').append(ubicacionBiblioteca.getText()).append("\",")
                .append(tIngreso.getValue()).append(",")
                .append(tTransporte.getValue()).append(",")
                .append(intDespacho.getValue());
        return st.toString();
    }
    
}
