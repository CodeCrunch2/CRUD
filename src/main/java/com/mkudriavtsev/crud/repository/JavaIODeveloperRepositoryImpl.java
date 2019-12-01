package main.java.com.mkudriavtsev.crud.repository;

import main.java.com.mkudriavtsev.crud.controller.AccountController;
import main.java.com.mkudriavtsev.crud.exception.AccountNotExistException;
import main.java.com.mkudriavtsev.crud.model.Account;
import main.java.com.mkudriavtsev.crud.model.Developer;
import main.java.com.mkudriavtsev.crud.view.MainView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository{
    private static String path;
    static {
        if (MainView.isWindows()) path = "src\\main\\resources\\developers.txt";
        else path = "src/main/resources/developers.txt";
    }
    AccountController accountController = new AccountController();
    @Override
    public void save(Developer developer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            //writer.print("\r");
            if (developer.getAccount() == null) writer.print(developer.getId() + ",null\r\n");
            else writer.print(developer.getId() + "," + developer.getAccount().getId() + "\r\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
    }

    @Override
    public void delete(Developer developer) {
        List<Developer> developerList = getAll();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            if (!developerList.isEmpty()) {
                for(Developer d: developerList) {
                    if (d.getId() != developer.getId()) {
                        if (d.getAccount() == null) writer.print(d.getId() + ",null\r\n");
                        else writer.print(d.getId() + "," + d.getAccount().getId() + "\r\n");
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
    }

    @Override
    public void update(Developer developer) {
       List<Developer> developerList = getAll();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            if(!developerList.isEmpty()) {
                for(Developer d: developerList) {
                    if (d.getId() != developer.getId()) {
                        if (d.getAccount() == null) writer.print(d.getId() + ",null\r\n");
                        else writer.print(d.getId() + "," + d.getAccount().getId() + "\r\n");
                    }
                    else writer.print(developer.getId() + "," + developer.getAccount().getId() + "\r\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
    }

    @Override
    public Long getNextID() {
        List<Developer> developerList = getAll();
        if (developerList.isEmpty()) return 0L;
        else return developerList.get(developerList.size()-1).getId() + 1L;
    }


    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String [] strArr = reader.readLine().split(",");
                if(strArr[1].equals("null")) developerList.add(new Developer(Long.parseLong(strArr[0]), null));
                else developerList.add(new Developer(Long.parseLong(strArr[0]), accountController.getByID(strArr[1])));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл developers.txt не найден\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
        catch (AccountNotExistException e){
            System.out.println(e.getMessage());
        }
        return developerList;
    }
}
