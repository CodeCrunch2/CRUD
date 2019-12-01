package main.java.com.mkudriavtsev.crud.view;

import main.java.com.mkudriavtsev.crud.controller.AccountController;
import main.java.com.mkudriavtsev.crud.exception.*;
import main.java.com.mkudriavtsev.crud.model.Account;

import java.util.Scanner;

public class AccountView {
    AccountController accountController = new AccountController();
    Scanner scanner = new Scanner(System.in);
    public void accountView() {
        String action;
        boolean isCanceled = false;
        while (!isCanceled) {
            System.out.println("Выберите одно из доступных действий:");
            System.out.println("create account, show accounts, change username, delete account");
            action = scanner.nextLine();
            switch (action) {
                case "create account":
                    try {
                        accountController.createAccount(MainView.getCommand("Введите имя пользователя:"));
                    }
                    catch (CancelException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (AccountExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show accounts":
                    try {
                        for(Account account: accountController.getAccounts()) {
                            System.out.println("id: " + account.getId() + " username: " + account.getUserName());
                        }
                    }
                    catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "change username":
                    String idStr;
                    String username;
                    try{
                        idStr = MainView.getCommand("Введите id аккаунта:");
                        accountController.getByID(idStr);
                        username = MainView.getCommand("Введите новое имя пользователя:");
                        accountController.changeUsername(idStr, username);
                    }
                    catch (AccountNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (CancelException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (NumberFormatException e) {
                        System.out.println("id должен быть числом\n");
                    }

                    break;
                case "delete account":
                    try {
                        accountController.deleteAccount(MainView.getCommand("Введите id аккаунта:"));
                    }
                    catch (AccountNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (CancelException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (NumberFormatException e) {
                        System.out.println("id должен быть числом\n");
                    }
                    break;
                case "cancel":
                    System.out.println("Операция отменена\n");
                    isCanceled = true;
                    break;
                default:
                    System.out.println("Команда введена неверно. Для получения сведений о работе программы см. файл Readme\n");

            }
        }


    }
}
