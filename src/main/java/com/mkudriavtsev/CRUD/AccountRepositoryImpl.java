package main.java.com.mkudriavtsev.CRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryImpl implements AccountRepository {
    String path = "src/main/java/com/mkudriavtsev/CRUD/accounts.txt";


    @Override
    public void save(Account account) throws IDExistException {
        try(PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            if (!isIDExist(account.getId())) {
                writer.println(account.getId() + "," + account.getData());
            }
            else throw new IDExistException();
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
        }

    }


    @Override
    public Account get(long id) throws IDNotExistException {
        if (!isIDExist(id)) throw new IDNotExistException();
        Account account = null;
        HashMap<Long, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()){
                String [] s = reader.readLine().split(",");
                map.put(Long.parseLong(s[0]), s[1]);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл accounts.txt не найден" + e);
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
        }
        for(Map.Entry<Long, String> entry: map.entrySet()) {
            if (entry.getKey().equals(id)) account = new Account(entry.getKey(), entry.getValue());
        }
        return account;

    }

    @Override
    public void delete(long id) throws IDNotExistException {
        if (!isIDExist(id)) throw new IDNotExistException();
        HashMap<Long, String> mapSrc = new HashMap<>();
        HashMap<Long, String > mapDest = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()){
                String [] s = reader.readLine().split(",");
                mapSrc.put(Long.parseLong(s[0]), s[1]);
            }
            for(Map.Entry<Long, String> entry: mapSrc.entrySet()) {
                if (!entry.getKey().equals(id)) mapDest.put(entry.getKey(), entry.getValue());
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Файл accounts.txt не найден" + e);
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
        }
        try(PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (Map.Entry<Long, String> entry: mapDest.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
        }



    }

    @Override
    public boolean isIDExist(long id) {
        boolean result = false;
        ArrayList<Long> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            while (reader.ready()){
                String [] s = reader.readLine().split(",");
                list.add(Long.parseLong(s[0]));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Файл accounts.txt не найден" + e);
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
        }
        for(long l: list){
            if (l == id) result = true;
        }
        return result;

    }


}
