package seventeen.capstone3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import seventeen.capstone3.domain.Member;
import seventeen.capstone3.domain.Project;
import seventeen.capstone3.repository.ProjectRepository;
import seventeen.capstone3.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @RequestMapping("/projects/new")
    public String createProjectForm(){
        return "project/createProjectForm";
    }

    @PostMapping("/projects/new")
    public String createProject(@Valid ProjectForm form , BindingResult result) {

        if (result.hasErrors()){
            return "project/createProjectForm";
        }

        Project project = new Project();
        project.setName(form.getName());

        projectService.create(project);
        return "redirect:/";


    }

    @GetMapping("/projects")
    public String list(Model model) {
        List<Project> projects = projectService.findProjects();
        model.addAttribute("projects",projects);
        return "project/projectList";
    }
}
