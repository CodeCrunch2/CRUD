package main.java.com.mkudriavtsev.CRUD.repository;

import main.java.com.mkudriavtsev.CRUD.controller.DeveloperController;
import main.java.com.mkudriavtsev.CRUD.exception.DeveloperNotExistException;
import main.java.com.mkudriavtsev.CRUD.model.Developer;
import main.java.com.mkudriavtsev.CRUD.model.Project;
import main.java.com.mkudriavtsev.CRUD.model.ProjectStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaIOProjectRepositoryImpl implements ProjectRepository {
    private String path = "src\\main\\java\\com\\mkudriavtsev\\CRUD\\storage\\projects.txt";
    @Override
    public void save(Project project) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.print(project.getId() + ",null,null\r\n");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }

    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public void update(Project project) {
        List<Project> projectList = getAll();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            if (!projectList.isEmpty()) {
                for (Project p: projectList) {
                    if (p.getId() != project.getId()) writer.print(toStorageString(p) + "\r\n");
                    else writer.print(toStorageString(project) + "\r\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода\n");
        }

    }

    @Override
    public Long getNextID() {
        List<Project> projectList = getAll();
        if (projectList.isEmpty()) return 0L;
        else return projectList.get(projectList.size()-1).getId() +1L;
    }

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String [] strArr = reader.readLine().split(",");
                Set<Developer> developerSet = getDevelopersSet(strArr);
                ProjectStatus projectStatus;
                Long id = Long.parseLong(strArr[0]);
                if(strArr[strArr.length-1].equals("null")) projectStatus = null;
                else projectStatus = ProjectStatus.valueOf(strArr[strArr.length-1]);
                projectList.add(new Project(id, developerSet, projectStatus));
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
    public Set<Developer> getDevelopersSet(String[] strArr) throws DeveloperNotExistException {
        Set<Developer> developerSet = new HashSet<>();
        for (int i = 1; i < strArr.length - 1; i++) {
            if(!strArr[i].equals("null")) developerSet.add(new DeveloperController().getByID(strArr[i]));
        }
        return developerSet;
    }
    public String toStorageString(Project project){
        StringBuffer sb = new StringBuffer();
        sb.append(project.getId());
        sb.append(",");
        if (project.getDevelopers().isEmpty()) sb.append("null,");
        else {
            for(Developer developer: project.getDevelopers()) sb.append(developer.getId()).append(",");
        }
        if (project.getProjectStatus() == null) sb.append("null");
        else sb.append (project.getProjectStatus());
        return sb.toString();

    }

}
