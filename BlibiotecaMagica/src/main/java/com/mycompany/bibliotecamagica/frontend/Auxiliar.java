/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.frontend;

import com.mycompany.bibliotecamagica.backend.VarGlobales;
import java.math.BigDecimal;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author rafael-cayax
 */
public class Auxiliar {

    public static void crearSpinnerConFiltro(Spinner<Integer> spinner) {
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
            if (newText.matches("\\d*")) {
                try {
                    Integer.valueOf(newText);
                } catch (NumberFormatException e) {
                    return null;
                }
                return change;
            }
            return null;
        }));
        spinner.getEditor().focusedProperty().addListener((obs, oldFocused, newFocused) -> {
            if (!newFocused) {
                String text = spinner.getEditor().getText();
                if (text == null || text.isBlank()) {
                    spinner.getValueFactory().setValue(VarGlobales.TIEMPO_DEFAULT);
                }
            }
        });
    }
    
    public static void crearSpinnerPrecio(Spinner<BigDecimal> spinner){
        var factory = new BigDecimalSpinnerValueFactory(VarGlobales.MIN_PRECIO, VarGlobales.MAX_PRECIO,
                VarGlobales.PRECIO_DEFAULT, VarGlobales.INCREMENTO_PRECIO);
        spinner.setValueFactory(factory);
        TextField editor = spinner.getEditor();
        editor.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String newText = change.getControlNewText();
            if (newText.isEmpty() || newText.matches("\\d*\\.?\\d{0,2}")) {
                return change;
            }
            return null;
        }));
    }
    
    public static void lanzarAlerta(Alert.AlertType tipo, String titulo, String contenido, Node nodo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.initOwner((Stage) nodo.getScene().getWindow());
        alerta.initModality(Modality.WINDOW_MODAL);
        alerta.showAndWait();
    }
    
    public static <S, T> void configurarSaltoDeLinea(TableColumn<S, T> columna) {
         columna.setCellFactory(col -> {
        return new TableCell<S, T>() {
            private Text text = new Text();
            
            {
                text.setWrappingWidth(0);
                text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                setGraphic(text);
                setText(null);
            }
            
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    text.setText(null);
                    setGraphic(null);
                } else {
                    text.setText(item.toString());
                    setGraphic(text);
                }
            }
            
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                double width = getWidth() - 16;
                if (width > 0) {
                    text.setWrappingWidth(width);
                }
            }
        };
    });
    }
}