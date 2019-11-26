package main.java.com.mkudriavtsev.CRUD.controller;

import main.java.com.mkudriavtsev.CRUD.model.Project;
import main.java.com.mkudriavtsev.CRUD.repository.JavaIOProjectRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ProjectController {
    JavaIOProjectRepositoryImpl javaIOProjectRepository = new JavaIOProjectRepositoryImpl();
    public void createProject() {
        javaIOProjectRepository.getAll();

    }
}
