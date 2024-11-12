package csu.web.petstore.persistence.impl;

import csu.web.petstore.domain.Account;
import csu.web.petstore.persistence.DBUtil;
import csu.web.petstore.persistence.UserLogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserLogDaoImpl implements UserLogDao {
    private static final String INSERT_USERLOG = "INSERT INTO userlog (username,viewpage,time) VALUES (?,?,?)";
    public void insert_userlog(Account account, String viewpage){
        int result;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_USERLOG);
            pStatement.setString(1,account.getUsername());
            pStatement.setString(2,viewpage);
            pStatement.setTimestamp(3,new java.sql.Timestamp(new java.util.Date().getTime()));
            System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));
            result = pStatement.executeUpdate();

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
