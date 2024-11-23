module com.alatoo.employeemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alatoo.employeemanagementsystem to javafx.fxml;
    exports com.alatoo.employeemanagementsystem;
}