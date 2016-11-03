package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            user.setLogin(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setName(rs.getString(2));
            user.setSalt(rs.getString(5));
            return user;
        } else {
            return null;
        }
    }
}
