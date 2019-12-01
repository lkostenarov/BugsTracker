package bg.bugstracker.web.beans;

import java.time.LocalDateTime;

import java.util.Iterator;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import bg.bugstracker.entity.IssueModel;
import bg.bugstracker.entity.IssueStatusModel;
import bg.bugstracker.entity.ProjectModel;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.IssueService;
import bg.bugstracker.service.IssueServiceLocal;
import bg.bugstracker.service.ProjectServiceLocal;
import bg.bugstracker.service.StatusServiceLocal;
import bg.bugstracker.service.UserServiceLocal;
import bg.bugstracker.web.utils.GeneralUtils;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean(name = "createIssueBean")
@ViewScoped
public class CreateIssueBean {
	
	@Inject
	HttpServletRequest request;

	@EJB
	IssueServiceLocal issueService;
	
	private IssueModel issue;
	
	@PostConstruct
	public void init() {
		issue = new IssueModel();
		issue.setProject(new ProjectModel(0,""));
		issue.setIssueStatus(new IssueStatusModel(0,""));
		issue.setReporter(new UserModel(((UserModel) request.getSession().getAttribute("LOGGED_USER")).getId() ,"","","","",1));
		issue.setAssignto(new UserModel(0,"","","","",1));
	}

	public String saveAction() {

		if (!validate()) {
			return null;
		}

		issueService.save(issue);
		
		return "/faces/listAllIssues?faces-redirect=true";
	}

	public String listAllIssues() {
		return "/faces/listAllIssues?faces-redirect=true";
	}
	   
	public String createAction() {
		return "/faces/createIssue?faces-redirect=true";
	}
	
	public IssueModel getIssue() {
		return issue;
	}

	public void setIssue(IssueModel issue) {
		this.issue = issue;
	}

	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(issue.getTitle())) {
			MessageUtils.addErrorMessage("error.required.title");
			hasErrors = true;
		}

		if (StringUtils.isBlank(issue.getDescription())) {
			MessageUtils.addErrorMessage("error.required.description");
			hasErrors = true;
		}

		if (issue.getIssueStatus()==null) {
			MessageUtils.addErrorMessage("error.required.status");
			hasErrors = true;
		}


		if (issue.getReporter()==null) {
			MessageUtils.addErrorMessage("error.required.reporter");
			hasErrors = true;
		}
		
		if (issue.getProject()==null) {
			MessageUtils.addErrorMessage("error.required.project");
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

	 public void reset() {
	        RequestContext.getCurrentInstance().reset("createIssueForm");
	    }
}
