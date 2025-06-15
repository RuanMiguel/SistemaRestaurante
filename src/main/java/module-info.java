module br.edu.uepb.sistemarestaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens br.edu.uepb.sistemarestaurante to javafx.fxml;
    opens br.edu.uepb.sistemarestaurante.controllers to javafx.fxml;

    exports br.edu.uepb.sistemarestaurante;
}
