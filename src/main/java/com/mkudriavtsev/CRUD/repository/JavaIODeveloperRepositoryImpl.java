package main.java.com.mkudriavtsev.CRUD.repository;

import main.java.com.mkudriavtsev.CRUD.controller.AccountController;
import main.java.com.mkudriavtsev.CRUD.exception.AccountNotExistException;
import main.java.com.mkudriavtsev.CRUD.model.Developer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository{
    private String path = "C:\\portapps\\IdeaProjects\\CRUD\\src\\main\\java\\com\\mkudriavtsev\\CRUD\\storage\\developers.txt";
    @Override
    public void save(Developer developer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
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
                else developerList.add(new Developer(Long.parseLong(strArr[0]), new AccountController().getByID(strArr[1])));
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
