package csu.web.petstore.persistence;

import csu.web.petstore.domain.Record;

import java.util.List;

public interface RecordDao {
    List<Record> getRecordList(String userid);

    void InsertToRecord(String userid, String records,int isItem);

    void DeleteRecord(String userid);


}

