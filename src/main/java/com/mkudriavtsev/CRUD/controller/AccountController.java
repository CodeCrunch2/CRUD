package main.java.com.mkudriavtsev.CRUD.controller;

import main.java.com.mkudriavtsev.CRUD.exception.AccountExistException;
import main.java.com.mkudriavtsev.CRUD.exception.AccountNotExistException;
import main.java.com.mkudriavtsev.CRUD.exception.EmptyListException;
import main.java.com.mkudriavtsev.CRUD.model.Account;
import main.java.com.mkudriavtsev.CRUD.repository.JavaIOAccountRepositoryImpl;

import java.util.List;

public class AccountController {
    JavaIOAccountRepositoryImpl javaIOAccountRepository = new JavaIOAccountRepositoryImpl();
    public void createAccount(String userName) throws AccountExistException {
        List<Account> accountList = javaIOAccountRepository.getAll();
        if (!accountList.isEmpty()) {
            for (Account account: accountList) {
                if (account.getUserName().equals(userName)) throw new AccountExistException("Аккаунт с таким именем существует\n");
            }
        }
        Account account = new Account(javaIOAccountRepository.getNextID(), userName);
        javaIOAccountRepository.save(account);
    }
    public List<Account> getAccounts() throws EmptyListException {
        List<Account> accountsList = javaIOAccountRepository.getAll();
        if (accountsList.isEmpty()) throw new EmptyListException("Список аккаунтов пуст\n");
        return accountsList;

    }
    public void deleteAccount(String idString) throws AccountNotExistException, NumberFormatException {
        javaIOAccountRepository.delete(getByID(idString));
    }

    public void changeUsername(String idString, String username) throws AccountNotExistException, NumberFormatException {
        Account account = getByID(idString);
        account.setUserName(username);
        javaIOAccountRepository.update(account);
    }
    public Account getByID(String idString) throws AccountNotExistException, NumberFormatException {
        List<Account> accountList = javaIOAccountRepository.getAll();
        long id = Long.parseLong(idString);
        int count = 0;
        int index = 0;
        if (!accountList.isEmpty()) {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getId() == id) {
                    count++;
                    index = i;
                }
            }
        }
        if (count < 1) throw new AccountNotExistException("Аккаунта с таким id не существует\n");
        return accountList.get(index);
    }



}
