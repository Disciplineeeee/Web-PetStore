package csu.web.petstore.persistence.impl;

import csu.web.petstore.domain.Record;
import csu.web.petstore.persistence.DBUtil;
import csu.web.petstore.persistence.RecordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao {

    private static final String GetRecordList = "SELECT * FROM record WHERE userid = ? AND isitem = 1";


    private static final String AddRecord = "insert INTO record(userid,records,isItem) VALUES(?,?,?)";

    private static final String DeleteRecord = "DELETE FROM record WHERE userid=?";


    @Override
    public List<Record> getRecordList(String userid) {
        List<Record> recordList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GetRecordList);
            pStatement.setString(1, userid);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Record record = new Record();
                record.setUserid(resultSet.getString(1));
                record.setRecords(resultSet.getString(2));
                recordList.add(record);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recordList;
    }

    @Override
    public void InsertToRecord(String userid, String records,int isItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(AddRecord);
            pStatement.setString(1, userid);
            pStatement.setString(2, records);
            pStatement.setInt(3,isItem);

            pStatement.execute();

            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteRecord(String userid) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DeleteRecord);
            pStatement.setString(1, userid);

            pStatement.execute();
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/*    public static void main(String[] args) {
        RecordDao recordDao = new RecordDaoImpl();
        List<Record> recordList = recordDao.getRecordList("j2ee");
        System.out.println();
    }

}*/
