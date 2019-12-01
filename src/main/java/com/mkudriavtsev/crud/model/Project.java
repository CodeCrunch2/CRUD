package main.java.com.mkudriavtsev.crud.model;

import java.util.Set;

public class Project {
    private long id;
    private Set<Developer> developers;
    private ProjectStatus projectStatus;
    public Project(long id, Set<Developer> developers, ProjectStatus projectStatus) {
        this.id = id;
        this.developers = developers;
        this.projectStatus = projectStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
