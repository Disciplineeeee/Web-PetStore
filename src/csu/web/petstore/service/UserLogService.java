package csu.web.petstore.service;

import com.mysql.cj.log.Log;
import csu.web.petstore.domain.Account;
import csu.web.petstore.persistence.UserLogDao;
import csu.web.petstore.persistence.impl.UserLogDaoImpl;
public class UserLogService {

    private UserLogDao userLogDao;
    public UserLogService(){
        userLogDao = new UserLogDaoImpl();
    }
    public void insertUserLog(Account account, String viewpage){
        userLogDao.insert_userlog(account,viewpage);
    }

}
