package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Project;
import com.kindsonthegenius.inventoryms_springboot_api.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {


    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<Project> getProjectes(){
        return projectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }

    @PutMapping("/project/{id}")
    public Project updateProject(@RequestBody() Project project, @PathVariable("id") Long id){
        return projectService.save(project);
    }

    @PostMapping("/projects")
    public Project addNew(@RequestBody() Project project){
        return projectService.save(project);
    }

    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        projectService.deleteProject(id);
    }
}
