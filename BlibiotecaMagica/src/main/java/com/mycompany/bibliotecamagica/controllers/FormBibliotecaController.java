/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.VarGlobales;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.validadores.ValidadorBiblioteca;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class FormBibliotecaController implements Initializable {
    @FXML private TextField idBiblioteca;
    @FXML private TextField nombreBiblioteca;
    @FXML private TextField ubicacionBiblioteca;
    @FXML private Spinner tIngreso;
    @FXML private Spinner tTransporte;
    @FXML private Spinner intDespacho;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        crearSpinnerConFiltro(tIngreso);
        crearSpinnerConFiltro(tTransporte);
        crearSpinnerConFiltro(intDespacho);
    }    
    
    @FXML
    private void agregarBiblioteca(){
        Alert alerta = null;
        try {
            String texto = crearEntrada();
            ValidadorBiblioteca v = new ValidadorBiblioteca();
            v.iniciarAnalisis(texto);
            limpiarCampos();
            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setContentText("Biblioteca agregada correctamente.");
        } catch (EntradaException ex) {
            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText(ex.getMessage());
        }
        Stage stage = (Stage) idBiblioteca.getScene().getWindow();
        alerta.initOwner(stage);
        alerta.initModality(Modality.WINDOW_MODAL);
        alerta.showAndWait();
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
