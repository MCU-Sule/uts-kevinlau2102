package com.uts.uts_2072030.Dao;

import com.uts.uts_2072030.Model.Movie;
import com.uts.uts_2072030.Model.User;
import com.uts.uts_2072030.Util.JDBCUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDao implements DaoInterface<Movie>{
    @Override
    public ObservableList<Movie> getData() {
        ObservableList<Movie> mList;
        mList = FXCollections.observableArrayList();
        Connection conn = JDBCUtility.getConnection();
        String query = "SELECT * FROM Movie";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idMovie");
                String title = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");
                Movie m = new Movie(id, title, genre, durasi);
                mList.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mList;
    }
    public ObservableList<Movie> filterMovie(String selectedGenre) {
        ObservableList<Movie> mList;
        mList = FXCollections.observableArrayList();
        Connection conn = JDBCUtility.getConnection();
        String query = "SELECT * FROM Movie WHERE Genre LIKE '%' ? '%'";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, selectedGenre);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idMovie");
                String title = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");
                Movie m = new Movie(id, title, genre, durasi);
                mList.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mList;
    }

    @Override
    public int addData(Movie data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "INSERT INTO Movie(idMovie, Title, Genre, Durasi) VALUES(?,?,?,?)";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdMovie());
            ps.setString(2, data.getTitle());
            ps.setString(3, data.getGenre());
            ps.setInt(4, data.getDurasi());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("add movie successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int delData(Movie data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "DELETE FROM Movie WHERE idMovie = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,data.getIdMovie());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("berhasil delete");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int updateData(Movie data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "UPDATE Movie set Title = ?, Genre = ?, Durasi = ? WHERE idMovie = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, data.getTitle());
            ps.setString(2, data.getGenre());
            ps.setInt(3, data.getDurasi());
            ps.setInt(4, data.getIdMovie());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("user updated successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
