package com.uts.uts_2072030.Controller;

import com.uts.uts_2072030.Dao.UserDao;
import com.uts.uts_2072030.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class UserController {
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    public void submit(ActionEvent actionEvent) {
        UserDao dao = new UserDao();
        int res = dao.addData(new User(0, txtUserName.getText(), txtPassword.getText()));
        if (res > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully", ButtonType.OK);
            alert.showAndWait();
            txtUserName.getScene().getWindow().hide();
        }
    }
}
