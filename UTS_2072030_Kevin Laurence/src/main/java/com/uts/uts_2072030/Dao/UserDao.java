package com.uts.uts_2072030.Dao;

import com.uts.uts_2072030.Model.User;
import com.uts.uts_2072030.Util.JDBCUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements DaoInterface<User>{
    @Override
    public ObservableList<User> getData() {
        ObservableList<User> uList;
        uList = FXCollections.observableArrayList();
        Connection conn = JDBCUtility.getConnection();
        String query = "SELECT * FROM User";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idUser");
                String nama = result.getString("UserName");
                String pass = result.getString("UserPassword");
                User u = new User(id, nama, pass);
                uList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uList;
    }

    @Override
    public int addData(User data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "INSERT INTO User(idUser, UserName, UserPassword) VALUES(?,?,?)";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdUser());
            ps.setString(2, data.getUserName());
            ps.setString(3, data.getUserPassword());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("add user successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int delData(User data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "DELETE FROM User WHERE idUser = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,data.getIdUser());
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
    public int updateData(User data) {
        Connection conn = JDBCUtility.getConnection();
        String query = "UPDATE User set UserName = ?, UserPassword = ? WHERE idUser = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, data.getUserName());
            ps.setString(2, data.getUserPassword());
            ps.setInt(3, data.getIdUser());
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
