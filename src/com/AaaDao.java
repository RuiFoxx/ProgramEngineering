package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AaaDao {
    Connection conn;

    public AaaDao(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<User> getUsers() throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM USER");
        ResultSet rs = pstm.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setSalt(rs.getString(5));
            users.add(user);
        }
        return users;
    }

    public ArrayList<Role> getRoles() throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM ROLE");
        ResultSet rs = pstm.executeQuery();
        ArrayList<Role> roles = new ArrayList<>();

        while(rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt(1));
            role.setName(rs.getString(2));
            role.setResource(rs.getString(3));
            roles.add(role);
        }

        return roles;
    }
}
