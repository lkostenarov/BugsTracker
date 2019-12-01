package bg.bugstracker.web.beans;

import java.util.Iterator;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.ProjectModel;
import bg.bugstracker.service.ProjectServiceLocal;
import bg.bugstracker.web.utils.GeneralUtils;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean (name="createProjectBean")
@ViewScoped
public class CreateProjectBean {

	@Inject
	HttpServletRequest request;

	@EJB
	ProjectServiceLocal projectService;

	private ProjectModel project;


	@PostConstruct
	public void init() {
		project = new ProjectModel();
	}

	public String createAction() {

		if (!validate()) {
			return null;
		}

		projectService.save(project);

		return "/faces/managePanel?faces-redirect=true";
	}
	
	protected boolean validate() {
		boolean hasErrors = false;
		
	
		if (hasErrors) {
			return false;
		}

		return true;
	}

	public boolean hasErrors() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		for (; messages.hasNext();) {
			FacesMessage message = messages.next();
			if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
				return true;
			}
		}

		return false;
	}

	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}
			

}
