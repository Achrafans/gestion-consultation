module net.achraf.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens net.achraf.demo to javafx.fxml;
    opens net.achraf.demo.controllers to javafx.fxml;
    opens net.achraf.demo.entities to javafx.base;
    opens net.achraf.demo.service to javafx.fxml;
    opens net.achraf.demo.dao to javafx.fxml;
    exports net.achraf.demo;
    exports net.achraf.demo.service;

}