package main.java.com.mkudriavtsev.crud.view;

import main.java.com.mkudriavtsev.crud.controller.AccountController;
import main.java.com.mkudriavtsev.crud.controller.DeveloperController;
import main.java.com.mkudriavtsev.crud.exception.*;
import main.java.com.mkudriavtsev.crud.model.Developer;

import java.util.Scanner;

public class DeveloperView {
    DeveloperController developerController = new DeveloperController();
    AccountController accountController = new AccountController();
    Scanner scanner = new Scanner(System.in);
    public void developerView() {
        String action;
        boolean isCanceled = false;
        while (!isCanceled) {
            System.out.println("Выберите одно из доступных действий:");
            System.out.println("create developer, show developers, set account, delete developer");
            action = scanner.nextLine();
            switch (action) {
                case "create developer":
                    developerController.createDeveloper();
                    break;
                case "show developers":
                    try {
                        for(Developer developer: developerController.getDevelopers()){
                            if (developer.getAccount() == null) System.out.println("id: " + developer.getId() + " account username: null");
                            else System.out.println("id: " + developer.getId() + " account username: " + developer.getAccount().getUserName());

                        }
                    }
                    catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "set account":
                    String idAccount;
                    String idDeveloper;
                    try {
                        idDeveloper = MainView.getCommand("Введите id разработчика:");
                        developerController.getByID(idDeveloper);
                        idAccount = MainView.getCommand("Введите id аккаунта:");
                        accountController.getByID(idAccount);
                        developerController.setAccount(idDeveloper, idAccount);
                    }
                    catch (AccountNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (DeveloperNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (CancelException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (NumberFormatException e) {
                        System.out.println("id должен быть числом");
                    }

                    break;
                case "delete developer":
                    try {
                        developerController.deleteDeveloper(MainView.getCommand("Введите id разработчика:"));
                    }
                    catch (DeveloperNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (CancelException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (NumberFormatException e) {
                        System.out.println("id должен быть числом");
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
