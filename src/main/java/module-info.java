module br.edu.uepb.sistemarestaurante {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.uepb.sistemarestaurante to javafx.fxml;
    exports br.edu.uepb.sistemarestaurante;

    opens br.edu.uepb.sistemarestaurante.controllers to javafx.fxml;
    exports br.edu.uepb.sistemarestaurante.controllers to javafx.fxml;
    exports br.edu.uepb.sistemarestaurante.models;
    opens br.edu.uepb.sistemarestaurante.models to javafx.fxml;
}