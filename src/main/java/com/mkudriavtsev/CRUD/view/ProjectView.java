package main.java.com.mkudriavtsev.CRUD.view;

import main.java.com.mkudriavtsev.CRUD.controller.ProjectController;

import java.util.Scanner;

public class ProjectView {
    ProjectController projectController = new ProjectController();
    Scanner scanner = new Scanner(System.in);
    public void projectView() {
        String action;
        boolean isCanceled = false;
        while (!isCanceled) {
            System.out.println("Выберите одно из доступных действий:");
            System.out.println("create project, show projects, add developer, delete developer, set status");
            action = scanner.nextLine();
            switch (action) {
                case "create project":
                    projectController.createProject();
                    break;
                case "show projects":
                    break;
                case "add developer":
                    break;
                case "delete developer":
                    break;
                case "set status":
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
