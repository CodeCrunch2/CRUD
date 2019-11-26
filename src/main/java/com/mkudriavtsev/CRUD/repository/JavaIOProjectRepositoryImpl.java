package main.java.com.mkudriavtsev.CRUD.repository;

import main.java.com.mkudriavtsev.CRUD.controller.DeveloperController;
import main.java.com.mkudriavtsev.CRUD.exception.DeveloperNotExistException;
import main.java.com.mkudriavtsev.CRUD.model.Developer;
import main.java.com.mkudriavtsev.CRUD.model.Project;
import main.java.com.mkudriavtsev.CRUD.model.ProjectStatus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaIOProjectRepositoryImpl implements ProjectRepository {
    private String path = "C:\\portapps\\IdeaProjects\\CRUD\\src\\main\\java\\com\\mkudriavtsev\\CRUD\\storage\\projects.txt";
    @Override
    public void save(Project project) {

    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public void update(Project project) {

    }

    @Override
    public Long getNextID() {
        return null;
    }

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        Set<Developer> developerSet = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String [] strArr = reader.readLine().split(",");

                for (int i = 1; i < (strArr.length-1); i++) {
                    if(!strArr[i].equals("null")) developerSet.add(new DeveloperController().getByID(strArr[i]));
                }

                if(developerSet.isEmpty() & strArr[strArr.length-1].equals("null")) {
                    projectList.add(new Project(Long.parseLong(strArr[0]),null, null));
                }
                else if(developerSet.isEmpty()) projectList.add(new Project(Long.parseLong(strArr[0]),null, ProjectStatus.valueOf(strArr[strArr.length-1])));
                else if(strArr[strArr.length-1].equals("null")) projectList.add(new Project(Long.parseLong(strArr[0]),developerSet, null));
                else projectList.add(new Project(Long.parseLong(strArr[0]),null, ProjectStatus.valueOf(strArr[strArr.length-1])));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл projects.txt не найден\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }
        catch (DeveloperNotExistException e) {
            System.out.println(e.getMessage());
        }
        return projectList;

    }
}
