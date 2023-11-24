module com.example.akalo_chomsky {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.akalo_chomsky to javafx.fxml;
    exports com.example.akalo_chomsky;
}