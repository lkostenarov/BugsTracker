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

import bg.bugstracker.entity.IssueModel;
import bg.bugstracker.service.IssueServiceLocal;
import bg.bugstracker.web.utils.MessageUtils;


@ManagedBean (name = "editIssueBean")
@ViewScoped
public class EditIssueBean {

	@Inject
	HttpServletRequest request;
	
	@EJB
	IssueServiceLocal issueService;
	
	private IssueModel issue;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			issue = issueService.findById(Long.valueOf(id));
		}
	}
	
	public String updateAction() {

		if (!validate()) {
			return null;
		}
		
        issueService.update(issue);
		
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
	
}
