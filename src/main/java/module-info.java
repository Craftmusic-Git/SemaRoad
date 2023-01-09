module fr.uha.ensisa.divemiller.semaroad {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.uha.ensisa.divemiller.semaroad to javafx.fxml;
    exports fr.uha.ensisa.divemiller.semaroad;
}
