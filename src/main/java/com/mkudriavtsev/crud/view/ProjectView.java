package main.java.com.mkudriavtsev.crud.view;

import main.java.com.mkudriavtsev.crud.controller.DeveloperController;
import main.java.com.mkudriavtsev.crud.controller.ProjectController;
import main.java.com.mkudriavtsev.crud.exception.*;
import main.java.com.mkudriavtsev.crud.model.Developer;
import main.java.com.mkudriavtsev.crud.model.Project;
import main.java.com.mkudriavtsev.crud.model.ProjectStatus;

import java.util.Scanner;

public class ProjectView {
    ProjectController projectController = new ProjectController();
    DeveloperController developerController = new DeveloperController();
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
                    try {
                        for(Project project : projectController.getProjects()) {
                            System.out.print("id: " + project.getId());
                            System.out.print(" Developers id: ");
                            if (project.getDevelopers().isEmpty()) System.out.print("null ");
                            else {
                                for(Developer developer: project.getDevelopers()) System.out.print(developer.getId() + ", ");
                            }
                            System.out.print("Status: ");
                            if (project.getProjectStatus() == null) System.out.println("null");
                            else System.out.println(project.getProjectStatus());
                        }
                    }
                    catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "add developer":
                    String idProject;
                    String idDeveloper;
                    try {
                        idProject = MainView.getCommand("Введите id проекта:");
                        projectController.getById(idProject);
                        idDeveloper = MainView.getCommand("Введите id разработчика:");
                        developerController.getByID(idDeveloper);
                        projectController.addDeveloper(idProject, idDeveloper);
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
                    catch (ProjectNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (DeveloperNotExistException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "delete developer":
                    String idProject2;
                    String idDeveloper2;
                    try {
                        idProject2 = MainView.getCommand("Введите id проекта:");
                        projectController.getById(idProject2);
                        idDeveloper2 = MainView.getCommand("Введите id разработчика:");
                        developerController.getByID(idDeveloper2);
                        projectController.deleteDeveloper(idProject2, idDeveloper2);
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
                    catch (ProjectNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (DeveloperNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (DeveloperIsNotInProjectException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "set status":
                    String idProject3;
                    String statusString;
                    try {
                        idProject3 = MainView.getCommand("Введите id проекта:");
                        projectController.getById(idProject3);
                        statusString = MainView.getCommand("Введите статус проекта из представленных: ACTIVE, FINISHED, DELETED");
                        ProjectStatus status = ProjectStatus.valueOf(statusString);
                        projectController.setStatus(idProject3, status);

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
                    catch (ProjectNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Введен неправильный статус проекта");
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
