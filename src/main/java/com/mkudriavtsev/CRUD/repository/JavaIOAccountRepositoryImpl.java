package main.java.com.mkudriavtsev.CRUD.repository;

import main.java.com.mkudriavtsev.CRUD.model.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements AccountRepository{
    private String path = "src\\main\\java\\com\\mkudriavtsev\\CRUD\\storage\\accounts.txt";
    @Override
    public void save(Account account) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            //writer.print("\r");
            writer.print(account.getId() + "," + account.getUserName() + "\r\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
    }

    @Override
    public void delete(Account account) {
        List<Account> accountsList = getAll();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            if (!accountsList.isEmpty()) {
                for (Account a: accountsList) {
                    if(a.getId() != account.getId()) writer.print(a.getId() + "," + a.getUserName() + "\r\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
    }

    @Override
    public void update(Account account) {
        List<Account> accountsList = getAll();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            if (!accountsList.isEmpty()) {
                for (Account a: accountsList) {
                    if(a.getId() != account.getId()) writer.print(a.getId() + "," + a.getUserName() + "\r\n");
                    else writer.print(account.getId() + "," + account.getUserName() + "\r\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
    }


    @Override
    public Long getNextID() {
        List<Account> accountsList = getAll();
        if (accountsList.isEmpty()) return 0L;
        else return accountsList.get(accountsList.size()-1).getId() + 1L;

    }

    @Override
    public List<Account> getAll()  {
        ArrayList<Account> accountsList= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String [] strArr = reader.readLine().split(",");
                accountsList.add(new Account(Long.parseLong(strArr[0]),strArr[1]));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл accounts.txt не найден\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
        return accountsList;
    }


}
