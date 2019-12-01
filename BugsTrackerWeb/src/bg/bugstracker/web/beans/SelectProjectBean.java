package bg.bugstracker.web.beans;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.IssueStatusModel;
import bg.bugstracker.entity.ProjectModel;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.IssueServiceLocal;
import bg.bugstracker.service.ProjectServiceLocal;
import bg.bugstracker.service.StatusServiceLocal;

@ManagedBean (name="selectProjectBean")
@ViewScoped
public class SelectProjectBean {
	
	private ProjectModel selectedProject;
	
	@Inject
	HttpServletRequest request;
		
	@EJB
    ProjectServiceLocal ProjectService;
	
	@EJB
    IssueServiceLocal issueService;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			selectedProject = issueService.findById(Long.valueOf(id)).getProject() ;
		}
	}
	 
	public List<ProjectModel> findAllProjects() {
			return ProjectService.findAllProjects();
	    }
	
	public List<ProjectModel> getAllProjects() {
		List<ProjectModel> temp = ProjectService.findAllProjects();
		if (selectedProject!=null){
			temp.remove(selectedProject);
		}
		return temp;
    }

	public ProjectModel getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(ProjectModel selectedProject) {
		this.selectedProject = selectedProject;
	}
	
}
