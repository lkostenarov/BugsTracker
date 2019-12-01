package bg.bugstracker.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.bugstracker.entity.ProjectModel;
import bg.bugstracker.service.ProjectServiceLocal;

@ManagedBean(name = "listProjectsBean")
@ViewScoped
public class ListProjectsBean {
		
	@EJB
	ProjectServiceLocal projectService;
	
	@PostConstruct
	public void init() {
	}
	
	public String editAction() {
		return "/faces/editProject";
	}
	
	public String createAction() {
		return "/faces/createProject";
	}
	
	public List<ProjectModel> getAllProjects() {
		return projectService.findAllProjects();
	}
}
