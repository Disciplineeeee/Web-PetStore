package csu.web.petstore.service;

import csu.web.petstore.domain.Account;
import csu.web.petstore.persistence.AccountDao;
import csu.web.petstore.persistence.impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(){
        this.accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }
    public Account getAccountByUsername(String username){
        return accountDao.getAccountByUsername(username);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

}