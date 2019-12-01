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

import bg.bugstracker.entity.CommentModel;
import bg.bugstracker.entity.ProjectModel;
import bg.bugstracker.service.CommentServiceLocal;
import bg.bugstracker.service.ProjectServiceLocal;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean (name="editProjectBean")
@ViewScoped
public class EditProjectBean {

	@Inject
	HttpServletRequest request;
	
	@EJB
	ProjectServiceLocal projectService;
	
	private ProjectModel editProject;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			editProject = projectService.findById(Long.valueOf(id));
		}
	}
	
	public String updateAction() {

		if (!validate()) {
			return null;
		}
		
		projectService.update(editProject);
		
		return "/faces/managePanel?faces-redirect=true";
	}

	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(editProject.getName() )) {
			MessageUtils.addErrorMessage("error.required.project_name");
			hasErrors = true;
		}

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

	public ProjectModel getEditProject() {
		return editProject;
	}

	public void setEditProject(ProjectModel editProject) {
		this.editProject = editProject;
	}
			
}
