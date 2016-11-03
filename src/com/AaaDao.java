package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ruifo on 03.11.2016.
 */
public class AaaDao {
    Connection conn;

    public AaaDao(Connection conn) {
        this.conn = conn;
    }

    public User getUser(String login) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM USER WHERE LOGIN = ?");
        pstm.setString(1, login);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setSalt(rs.getString(5));
            return user;
        } else {
            return null;
        }
    }

    public /*Role*/ArrayList<Role> getRoles(/*int id*/) throws SQLException{
        /*PreparedStatement pstm = conn.prepareStatement("SELECT * FROM ROLE WHERE ID = ?");
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            Role role = new Role();
            role.setName(rs.getString(2));
            role.setResource(rs.getString(3));
            return role;
        } else {
            return null;
        }
    }*/

    PreparedStatement pstm = conn.prepareStatement("SELECT * FROM ROLE");
        ResultSet rs = pstm.executeQuery();
        ArrayList<Role> roles = new ArrayList<>();

        if (rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt(1));
            role.setName(rs.getString(2));
            role.setResource(rs.getString(3));
            roles.add(role);
        }

        return roles;
    }
}
