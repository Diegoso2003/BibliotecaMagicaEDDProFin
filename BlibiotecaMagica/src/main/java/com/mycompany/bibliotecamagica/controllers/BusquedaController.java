/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.buscadores.*;
import com.mycompany.bibliotecamagica.backend.comparadores.*;
import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;
import com.mycompany.bibliotecamagica.backend.ordenador.*;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class BusquedaController implements Initializable {

    public record LibroFrontend(String isbn, String titulo, String genero
            ,String autor, Integer año, int copias, String estado){}
    
    @FXML private ComboBox<Biblioteca> biblioSeleccionada;
    @FXML private ComboBox<Buscador> buscadorSeleccionado;
    @FXML private ComboBox<Comparador> ordenCampo;
    @FXML private ComboBox<Ordenador> algoritmoOrden;
    @FXML private TextField campo;
    @FXML private Button botonBusqueda;
    @FXML private Button botonOrdenar;
    
    @FXML private TableView<LibroFrontend> tablaResultados;
    @FXML private TableColumn<LibroFrontend, String> numero;
    @FXML private TableColumn<LibroFrontend, String> isbn;
    @FXML private TableColumn<LibroFrontend, String> titulo;
    @FXML private TableColumn<LibroFrontend, String> autor;
    @FXML private TableColumn<LibroFrontend, String> genero;
    @FXML private TableColumn<LibroFrontend, String> año;
    @FXML private TableColumn<LibroFrontend, String> estado;
    @FXML private TableColumn<LibroFrontend, String> copias;
    private final ObservableList<LibroFrontend> datos = FXCollections.observableArrayList();
    private ListaSimple<LibroBiblioteca> actual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            botonBusqueda.setDisable(newValue.isBlank());
        });
        ObservableList<Biblioteca> bibliotecas = FXCollections.observableArrayList();
        ObservableList<Buscador> buscadores = FXCollections.observableArrayList(new BuscadorTitulo(), 
                new BuscadorGenero(), new BuscadorAño(), new BuscadorAutor(), new BuscadorIsbn(), new ListarAlfabeticamente());
        IteradorLista<NodoGrafo> lista = RedBibliotecas.INSTANCIA.getBibliotecas().getIterador();
        ObservableList<Comparador> comparadores = FXCollections.observableArrayList(new ComparadorTitulo(), new ComparadorGenero()
        ,new ComparadorAño(), new ComparadorIsbn(), new ComparadorAutor());
        ObservableList<Ordenador> ordenadores = FXCollections.observableArrayList(new OrdenacionShell(), new InsercionDirecta()
        , new SeleccionDirecta(), new QuickSort(), new Intercambio());
        while(lista.haySiguiente()){
            bibliotecas.add(lista.getActual().getBiblioteca());
        }
        biblioSeleccionada.setItems(bibliotecas);
        algoritmoOrden.setItems(ordenadores);
        buscadorSeleccionado.setItems(buscadores);
        ordenCampo.setItems(comparadores);
        configurarColumnas();
    }
    
    @FXML
    private void verificarEntrada(){
        boolean valor = biblioSeleccionada.getValue() == null || buscadorSeleccionado.getValue() == null;
        if(!valor && buscadorSeleccionado.getValue() instanceof ListarAlfabeticamente){
            campo.setDisable(false);
            realizarBusqueda();
        } else {
            campo.setDisable(valor);
        }
    }
    
    @FXML
    private void realizarBusqueda(){
        try {
            Biblioteca biblioteca = biblioSeleccionada.getValue();
            Buscador buscador = buscadorSeleccionado.getValue();
            String valor = campo.getText();
            ListaSimple<LibroBiblioteca> libros = buscador.realizarBusqueda(valor, biblioteca);
            datos.clear();
            actual = libros;
            if(libros != null && !libros.estaVacia()){
                ordenCampo.setDisable(false);
                algoritmoOrden.setDisable(false);
                IteradorLista<LibroBiblioteca> l = libros.getIterador();
                while(l.haySiguiente()){
                    agregarLibro(l.getActual());
                }
                if (buscador.obtenerMensaje()) {
                    Auxiliar.lanzarAlerta(Alert.AlertType.INFORMATION, "Tiempos", buscador.getMensaje(), campo);
                }
            } else {
                ordenCampo.setDisable(true);
                algoritmoOrden.setDisable(true);
                botonOrdenar.setDisable(true);
                Auxiliar.lanzarAlerta(Alert.AlertType.WARNING, "Sin resultados", "No se encontro ninguna coincidencia", campo);
            }
        } catch (EntradaException ex) {
            Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), campo);
        }
    }
    
    private void agregarLibro(LibroBiblioteca actual) {
        Libro libro = actual.getLibro();
        if(actual.getAgotado() > 0){
            datos.add(new LibroFrontend(libro.getIsbn(), libro.getTitulo(), libro.getGenero(), libro.getAutor()
            , libro.getAño(), actual.getAgotado(), EstadoLibroEnum.AGOTADO.getDescripcion()));
        } 
        if(actual.getDisponible() > 0){
            datos.add(new LibroFrontend(libro.getIsbn(), libro.getTitulo(), libro.getGenero(), libro.getAutor()
            , libro.getAño(), actual.getDisponible(), EstadoLibroEnum.DISPONIBLE.getDescripcion()));
        }
        if(actual.getEnTraspaso() > 0){
            datos.add(new LibroFrontend(libro.getIsbn(), libro.getTitulo(), libro.getGenero(), libro.getAutor()
            , libro.getAño(), actual.getEnTraspaso(), EstadoLibroEnum.EN_TRANSITO.getDescripcion()));
        }
        if(actual.getPrestado() > 0){
            datos.add(new LibroFrontend(libro.getIsbn(), libro.getTitulo(), libro.getGenero(), libro.getAutor()
            , libro.getAño(), actual.getPrestado(), EstadoLibroEnum.PRESTADO.getDescripcion()));
        }
    }

    private void configurarColumnas() {
        numero.setCellValueFactory(cellData -> {
            int index = tablaResultados.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });
        isbn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isbn()));
        titulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().titulo()));
        autor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().autor()));
        genero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().genero()));
        año.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().año().toString()));
        estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        copias.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().copias() + ""));
        Auxiliar.configurarSaltoDeLinea(titulo);
        Auxiliar.configurarSaltoDeLinea(autor);
        Auxiliar.configurarSaltoDeLinea(genero);
        tablaResultados.setItems(datos);
    }
    
    @FXML
    private void verificarSeleccion(){
        if(actual != null && ordenCampo.getValue() != null && algoritmoOrden.getValue() != null){
            botonOrdenar.setDisable(false);
        }
    }
    
    @FXML
    private void realizarOrdenacion(){
        Ordenador orden = algoritmoOrden.getValue();
        Comparador comparador = ordenCampo.getValue();
        if(orden == null || comparador == null) return;
        LibroBiblioteca[] ordenados = orden.ordenar(comparador, actual);
        datos.clear();
        for(LibroBiblioteca biblio : ordenados){
            agregarLibro(biblio);
        }
        Auxiliar.lanzarAlerta(Alert.AlertType.INFORMATION, "Comparacion", orden.getMensaje(), campo);
    }
    
}
