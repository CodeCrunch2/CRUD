package main.java.com.mkudriavtsev.crud.controller;

import main.java.com.mkudriavtsev.crud.exception.DeveloperIsNotInProjectException;
import main.java.com.mkudriavtsev.crud.exception.DeveloperNotExistException;
import main.java.com.mkudriavtsev.crud.exception.EmptyListException;
import main.java.com.mkudriavtsev.crud.exception.ProjectNotExistException;
import main.java.com.mkudriavtsev.crud.model.Developer;
import main.java.com.mkudriavtsev.crud.model.Project;
import main.java.com.mkudriavtsev.crud.model.ProjectStatus;
import main.java.com.mkudriavtsev.crud.repository.JavaIOProjectRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectController {
    JavaIOProjectRepositoryImpl javaIOProjectRepository = new JavaIOProjectRepositoryImpl();
    DeveloperController developerController = new DeveloperController();
    public void createProject() {
        Project project = new Project(javaIOProjectRepository.getNextID(), null, null);
        javaIOProjectRepository.save(project);
    }
    public List<Project> getProjects() throws EmptyListException {
        List<Project> projectList = javaIOProjectRepository.getAll();
        if (projectList.isEmpty()) throw new EmptyListException("Список проектов пуст\n");
        return projectList;
    }
    public Project getById(String idString) throws ProjectNotExistException, NumberFormatException {
        List<Project> projectList = javaIOProjectRepository.getAll();
        long id = Long.parseLong(idString);
        int count = 0;
        int index = 0;
        if (!projectList.isEmpty()) {
            for (int i = 0; i < projectList.size(); i++) {
                if (projectList.get(i).getId() == id){
                    count++;
                    index = i;
                }

            }
        }
        if (count < 1) throw new ProjectNotExistException("Проекта с таким id не существует\n");
        return projectList.get(index);

    }
    public void addDeveloper(String idProject, String idDeveloper) throws NumberFormatException, DeveloperNotExistException, ProjectNotExistException {
        Developer developer = developerController.getByID(idDeveloper);
        Project project = getById(idProject);
        project.getDevelopers().add(developer);
        javaIOProjectRepository.update(project);
    }
    public void deleteDeveloper(String idProject, String idDeveloper) throws NumberFormatException,
            DeveloperNotExistException, ProjectNotExistException, DeveloperIsNotInProjectException {
        Developer developer = developerController.getByID(idDeveloper);
        Project project = getById(idProject);
        Set<Developer> newDeveloperSet = new HashSet<>();
        int count = 0;
        for (Developer d: project.getDevelopers()) {
            if(d.getId() == developer.getId()) count++;
            else newDeveloperSet.add(d);
        }
        if(count < 1) throw new DeveloperIsNotInProjectException("Разработчик не принадлежит этому проекту");
        project.setDevelopers(newDeveloperSet);
        javaIOProjectRepository.update(project);
    }
    public void setStatus(String idProject, ProjectStatus status) throws NumberFormatException, ProjectNotExistException {
        Project project = getById(idProject);
        project.setProjectStatus(status);
        javaIOProjectRepository.update(project);
    }

}
