package csu.web.petstore.persistence;

import csu.web.petstore.domain.Account;

public interface UserLogDao {
    void insert_userlog(Account account, String viewpage);
}
