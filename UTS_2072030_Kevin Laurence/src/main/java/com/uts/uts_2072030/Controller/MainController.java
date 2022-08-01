package com.uts.uts_2072030.Controller;

import com.uts.uts_2072030.Dao.MovieDao;
import com.uts.uts_2072030.Dao.UserDao;
import com.uts.uts_2072030.Dao.WatchListDao;
import com.uts.uts_2072030.MainApplication;
import com.uts.uts_2072030.Model.Movie;
import com.uts.uts_2072030.Model.User;
import com.uts.uts_2072030.Model.WatchList;
import com.uts.uts_2072030.Util.JDBCUtility;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    @FXML
    private ComboBox<String> cmbGenre;
    @FXML
    private ListView<User> lvUser;
    @FXML
    private TableView<Movie> table1;
    @FXML
    private TableColumn<String, Movie> titleCol;
    @FXML
    private TableColumn<String, Movie> genreCol;
    @FXML
    private TableColumn<Integer, Movie> durasiCol;
    @FXML
    private TableView<WatchList> table2;
    @FXML
    private TableColumn<Movie, WatchList> lastTitleCol;
    @FXML
    private TableColumn<Integer, WatchList> lastWatchCol;
    @FXML
    private TableColumn<Integer, WatchList> isFavoriteCol;
    private UserDao uDao;
    private MovieDao mDao;
    private WatchListDao wDao;
    private ObservableList<String> genre;

    public void initialize() {
        uDao = new UserDao();
        mDao = new MovieDao();
        wDao = new WatchListDao();
        genre = FXCollections.observableArrayList(
                "All",
                "Action",
                "Musical",
                "Comedy",
                "Animated",
                "Fantasy",
                "Drama",
                "Mystery",
                "Thriller",
                "Horror"
        );
        cmbGenre.setItems(genre);
        cmbGenre.getSelectionModel().select(0);
        table2.setItems(wDao.getData());
        lastTitleCol.setCellValueFactory(new PropertyValueFactory<>("Movie_idMovie"));
        lastWatchCol.setCellValueFactory(new PropertyValueFactory<>("DurasiWatch"));
        isFavoriteCol.setCellValueFactory(new PropertyValueFactory<>("BoolFavorite"));
        showData();
    }

    public void showData() {
        lvUser.setItems(uDao.getData());
        table1.setItems(mDao.getData());
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        durasiCol.setCellValueFactory(new PropertyValueFactory<>("Durasi"));
    }

    public void changeCombo(ActionEvent actionEvent) {
        if (cmbGenre.getValue().equals("All")) {
            table1.setItems(mDao.getData());
        } else {
            table1.setItems(mDao.filterMovie(cmbGenre.getValue()));
        }
    }

    public void filterWatchList() {
        table2.setItems(wDao.filterData(lvUser.getSelectionModel().getSelectedItem()));
        lastTitleCol.setCellValueFactory(new PropertyValueFactory<>("Movie_idMovie"));
        lastWatchCol.setCellValueFactory(new PropertyValueFactory<>("DurasiWatch"));
        isFavoriteCol.setCellValueFactory(new PropertyValueFactory<>("BoolFavorite"));
    }

    public void AddUserAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("UTSSecondPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 200);
        Stage stage = new Stage();
        stage.setTitle("modal");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        showData();
    }

    public void DelUserAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this data?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            uDao.delData(lvUser.getSelectionModel().getSelectedItem());
        }
        showData();
    }

    public void printReport(ActionEvent actionEvent) {
        JasperPrint jp;
        Connection conn = JDBCUtility.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/Movie_Report_2072030.jasper", param, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("laporan movies");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}