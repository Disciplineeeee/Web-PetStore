package csu.web.petstore.persistence.impl;

import csu.web.petstore.domain.Sequence;
import csu.web.petstore.persistence.DBUtil;
import csu.web.petstore.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {
    private static final String GETSEQUENCE="SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String UPDATESEQUENCE="UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";
    public Sequence getSequence(Sequence sequence){
        System.out.println(sequence);
        Sequence sequence1=null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GETSEQUENCE);
            pStatement.setString(1,sequence.getName());
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                sequence1=new Sequence();
                sequence1.setName(resultSet.getString(1));
                sequence1.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sequence1;
    }

    public void updateSequence(Sequence sequence){
        int result;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATESEQUENCE);
            pStatement.setInt(1,sequence.getNextId());
            pStatement.setString(2,sequence.getName());
            result=pStatement.executeUpdate();

            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
