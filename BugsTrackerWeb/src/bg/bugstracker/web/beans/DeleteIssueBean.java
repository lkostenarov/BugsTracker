package bg.bugstracker.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.IssueModel;
import bg.bugstracker.service.IssueServiceLocal;


@ManagedBean (name = "deleteIssueBean")
@ViewScoped
public class DeleteIssueBean {

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
	
	public String deleteAction() {

		if (!validate()) {
			return null;
		}
		
        issueService.delete(issue);
		
		return "/faces/listAllIssues?faces-redirect=true";
	}
		
   public String listAllIssues() {
		return "/faces/listAllIssues?faces-redirect=true";
	}
   
   public String createAction() {
		return "/faces/createIssue?faces-redirect=true";
	}
	
	protected boolean validate() {
		return true;
	}

	public IssueModel getIssue() {
		return issue;
	}

	public void setIssue(IssueModel issue) {
		this.issue = issue;
	}
	
}
