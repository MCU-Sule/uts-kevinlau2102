package com.uts.uts_2072030.Dao;

import com.uts.uts_2072030.Model.Movie;
import com.uts.uts_2072030.Model.User;
import com.uts.uts_2072030.Model.WatchList;
import com.uts.uts_2072030.Util.JDBCUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchListDao implements DaoInterface<WatchList> {
    @Override
    public ObservableList<WatchList> getData() {
        ObservableList<WatchList> wList;
        wList = FXCollections.observableArrayList();
        Connection conn = JDBCUtility.getConnection();
        String query = "SELECT * FROM watchlist w JOIN movie m ON w.Movie_idMovie = m.idMovie JOIN user u ON w.User_idUser = u.idUser";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int idWatch = result.getInt("idWatchList");
                int last = result.getInt("LastWatch");
                int fav = result.getInt("Favorite");

                int idMovie = result.getInt("idMovie");
                String title = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");

                int idUser = result.getInt("idUser");
                String username = result.getString("UserName");
                String userpass = result.getString("UserPassword");
                Movie m = new Movie(idMovie, title, genre, durasi);
                User u = new User(idUser, username, userpass);
                WatchList w = new WatchList(idWatch, last, fav, m, u);
                wList.add(w);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wList;
    }
    public ObservableList<WatchList> filterData(User userSelect) {
        ObservableList<WatchList> wList;
        wList = FXCollections.observableArrayList();
        Connection conn = JDBCUtility.getConnection();
        String query = "SELECT * FROM watchlist w JOIN movie m ON w.Movie_idMovie = m.idMovie JOIN user u ON w.User_idUser = u.idUser WHERE w.User_idUser = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, userSelect.getIdUser());
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int idWatch = result.getInt("idWatchList");
                int last = result.getInt("LastWatch");
                int fav = result.getInt("Favorite");

                int idMovie = result.getInt("idMovie");
                String title = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");

                int idUser = result.getInt("idUser");
                String username = result.getString("UserName");
                String userpass = result.getString("UserPassword");
                Movie m = new Movie(idMovie, title, genre, durasi);
                User u = new User(idUser, username, userpass);
                WatchList w = new WatchList(idWatch, last, fav, m, u);
                wList.add(w);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wList;
    }

    @Override
    public int addData(WatchList data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "INSERT INTO WatchList(idWatchList, LastWatch, Favorite, Movie_idMovie, User_idUser) VALUES(?,?,?,?,?)";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdWatchList());
            ps.setInt(2, data.getLastWatch());
            ps.setInt(3, data.getFavorite());
            ps.setInt(4, data.getMovie_idMovie().getIdMovie());
            ps.setInt(5, data.getUser_idUser().getIdUser());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("add watch list successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int delData(WatchList data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "DELETE FROM WatchList WHERE idWatchList = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdWatchList());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("watch list deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int updateData(WatchList data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "UPDATE WatchList SET LastWatch = ?, Favorite = ?, Movie_idMovie = ?, User_idUser = ? WHERE idWatchList = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getLastWatch());
            ps.setInt(2, data.getFavorite());
            ps.setInt(3, data.getMovie_idMovie().getIdMovie());
            ps.setInt(4, data.getUser_idUser().getIdUser());
            ps.setInt(5, data.getIdWatchList());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("watch list updated successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
