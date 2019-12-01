package main.java.com.mkudriavtsev.crud.controller;

import main.java.com.mkudriavtsev.crud.exception.AccountNotExistException;
import main.java.com.mkudriavtsev.crud.exception.DeveloperNotExistException;
import main.java.com.mkudriavtsev.crud.exception.EmptyListException;
import main.java.com.mkudriavtsev.crud.model.Account;
import main.java.com.mkudriavtsev.crud.model.Developer;
import main.java.com.mkudriavtsev.crud.repository.JavaIODeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    JavaIODeveloperRepositoryImpl javaIODeveloperRepository = new JavaIODeveloperRepositoryImpl();
    AccountController accountController = new AccountController();
    public void createDeveloper() {
       Developer developer = new Developer(javaIODeveloperRepository.getNextID(), null);
       javaIODeveloperRepository.save(developer);
    }
    public List<Developer> getDevelopers() throws EmptyListException {
        List<Developer> developerList = javaIODeveloperRepository.getAll();
        if (developerList.isEmpty()) throw new EmptyListException("Список разработчиков пуст\n");
        return developerList;

    }
    public Developer getByID(String idString) throws DeveloperNotExistException, NumberFormatException {
        List<Developer> developerList = javaIODeveloperRepository.getAll();
        long id = Long.parseLong(idString);
        int count = 0;
        int index = 0;
        if (!developerList.isEmpty()) {
            for (int i = 0; i < developerList.size(); i++) {
                if (developerList.get(i).getId() == id){
                    count++;
                    index = i;
                }

            }
        }
        if (count < 1) throw new DeveloperNotExistException("Разработчика с таким id не существует\n");
        return developerList.get(index);
    }
    public void deleteDeveloper(String idString) throws DeveloperNotExistException, NumberFormatException {
        javaIODeveloperRepository.delete(getByID(idString));
    }
    public void setAccount(String idDeveloper, String idAccount) throws AccountNotExistException,
            NumberFormatException, DeveloperNotExistException {
        Account account = accountController.getByID(idAccount);
        Developer developer = getByID(idDeveloper);
        developer.setAccount(account);
        javaIODeveloperRepository.update(developer);
    }
}
