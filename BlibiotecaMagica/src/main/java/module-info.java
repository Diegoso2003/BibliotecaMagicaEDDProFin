module com.mycompany.bibliotecamagica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.bibliotecamagica to javafx.fxml;
    opens com.mycompany.bibliotecamagica.controllers to javafx.fxml;
    exports com.mycompany.bibliotecamagica;
    exports com.mycompany.bibliotecamagica.controllers;
}
