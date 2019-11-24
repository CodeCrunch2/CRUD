package main.java.com.mkudriavtsev.CRUD;

import java.io.IOException;

public class AccountController {
    private boolean isCancelled = false;
    private String command;
    private long id;
    private String data;
    AccountRepository repository = new AccountRepositoryImpl();
    AccountView view = new AccountViewImpl();
    void execute() {
        while (!isCancelled){
            try {
                command = view.getCommand();
            }
            catch (IOException e) {
                view.print("Ошибка ввода-вывода" + e);
            }
            String [] arg = command.split(" ");
            switch (arg[0]) {
                case "create":
                    if (arg.length != 3) {
                        view.print("Указан неверный формат команды");
                        continue;
                    }
                    try {
                        id = Long.parseLong(arg[1]);
                    }
                    catch (NumberFormatException e) {
                        view.print("id должен быть числом");
                    }
                    data = arg[2];
                    Account accountCreate = new Account(id, data);
                    try {
                        repository.save(accountCreate);
                    }
                    catch (IDExistException e) {
                        view.print("Аккаунт с таким ID существует");
                    }

                    break;
                case "read":
                    if (arg.length != 2) {
                        view.print("Указан неверный формат команды");
                        continue;
                    }
                    try {
                        id = Long.parseLong(arg[1]);
                    }
                    catch (NumberFormatException e) {
                        view.print("id должен быть числом");
                    }
                    try {
                        Account accountRead = repository.get(id);
                        view.print("Account id: " + accountRead.getId() + " data: " + accountRead.getData());
                    }
                    catch (IDNotExistException e) {
                        view.print("Аккаунта с таким ID не существует");
                    }
                    break;
                case "update":
                    if (arg.length != 3) {
                        view.print("Указан неверный формат команды");
                        continue;
                    }
                    try {
                        id = Long.parseLong(arg[1]);
                    }
                    catch (NumberFormatException e) {
                        view.print("id должен быть числом");
                    }
                    data = arg[2];
                    Account accountUpdate = new Account(id, data);
                    try {
                        repository.delete(id);
                        repository.save(accountUpdate);
                    }
                    catch (IDNotExistException e) {
                        view.print("Аккаунта с таким ID не существует");
                    }
                    catch (IDExistException e) {
                        view.print("Ошибка обновления аккаунта");
                    }
                    break;
                case "delete":
                    if (arg.length != 2) {
                        view.print("Указан неверный формат команды");
                        continue;
                    }
                    try {
                        id = Long.parseLong(arg[1]);
                    }
                    catch (NumberFormatException e) {
                        view.print("id должен быть числом");
                    }
                    try {
                        repository.delete(id);
                    }
                    catch (IDNotExistException e) {
                        view.print("Аккаунта с таким ID не существует");
                    }
                    break;
                case "exit":
                    isCancelled = true;
                    break;
                default:
                    view.print("Указан неверный формат команды");
                    break;
            }

        }
    }


}
