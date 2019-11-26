package main.java.com.mkudriavtsev.CRUD.view;

import main.java.com.mkudriavtsev.CRUD.exception.CancelException;
import main.java.com.mkudriavtsev.CRUD.exception.WrongCommandException;

import java.util.Scanner;


public class MainView {
    static Scanner scanner = new Scanner(System.in);
    AccountView accountView = new AccountView();
    DeveloperView developerView = new DeveloperView();
    ProjectView projectView = new ProjectView();

    public void execute(){
        mainView();
    }
    private void mainView() {
        boolean isInterrupted = false;
        String command;
        while (!isInterrupted) {
            System.out.println("Выберите объект для дальнейшей работы: project, developer, account");
            System.out.println("Для выхода из программы введите exit");
            command = scanner.nextLine();
            switch (command) {
                case "project":
                    projectView.projectView();
                    break;
                case "developer":
                    developerView.developerView();
                    break;
                case "account":
                    accountView.accountView();
                    break;
                case "exit":
                    isInterrupted = true;
                    break;
                default:
                    System.out.println("Команда введена неверно. Для получения сведений о работе программы см. файл Readme\n");
                    break;
            }
        }


    }
    public static String getCommand(String message) throws CancelException, WrongCommandException {
        System.out.println(message);
        String command;
        command = scanner.nextLine();
        if (command.equals("cancel")) throw new CancelException("Операция отменена\n");
        else if (command.length() < 1) throw new WrongCommandException("Введена пустая строка\n");
        return command;

    }


}
