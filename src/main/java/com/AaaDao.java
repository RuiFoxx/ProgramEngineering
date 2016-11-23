package com;

import com.domain.Accounting;
import com.domain.Role;
import com.domain.User;

import java.sql.*;
import java.util.ArrayList;

public class AaaDao {
    Connection conn;

    public AaaDao(Connection conn) {
        this.conn = conn;
    }

    public User getUsers(String login) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM USER WHERE LOGIN = ?");
        pstm.setString(1, login);
        ResultSet rs = pstm.executeQuery();
        if(!rs.next()) {
            return null;
        } else {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setSalt(rs.getString(5));
            return user;
        }
    }

    public ArrayList<Role> getRoles(int user_id) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM ROLE WHERE USER_ID = ?");
        pstm.setInt(1, user_id);
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

    public void setAcc(Accounting a) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO ACCOUNTING(volume, role_id, date_start, date_end) VALUES(?, ?, ?, ?)");
        pstm.setInt(1, a.getVolume());
        pstm.setInt(2, a.getRoleId());
        pstm.setDate(3, new Date(a.getDateStart().getTime()));
        pstm.setDate(4, new Date(a.getDateEnd().getTime()));
        pstm.execute();
    }
}
