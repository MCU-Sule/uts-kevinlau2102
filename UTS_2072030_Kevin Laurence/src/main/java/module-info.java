module com.uts.uts_2072030 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires java.sql;

    exports com.uts.uts_2072030;
    opens com.uts.uts_2072030 to javafx.fxml;
    exports com.uts.uts_2072030.Util;
    opens com.uts.uts_2072030.Util to javafx.fxml;
    exports com.uts.uts_2072030.Model;
    opens com.uts.uts_2072030.Model to javafx.fxml;
    exports com.uts.uts_2072030.Controller;
    opens com.uts.uts_2072030.Controller to javafx.fxml;
    exports com.uts.uts_2072030.Dao;
    opens com.uts.uts_2072030.Dao to javafx.fxml;
}