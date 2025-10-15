module com.mycompany.blibiotecamagica {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.blibiotecamagica to javafx.fxml;
    exports com.mycompany.blibiotecamagica;
}
