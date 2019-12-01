package bg.bugstracker.web.beans;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.IssueModel;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.IssueServiceLocal;

@ManagedBean (name = "getIssuesBean")
@ViewScoped
public class GetIssuesBean {

	@Inject
	HttpServletRequest request;
	
	private long statusId;
	
	@EJB
    IssueServiceLocal issueService;

    @PostConstruct
    public void init() {
    }
    
    
    public void preAction(){
    	statusId = 1l;
    }
    
    public String editAction() {
		return "/faces/editIssue";
	}
    
    public String createAction() {
		return "/faces/createIssue?faces-redirect=true";
	}
    
    public String viewAction() {
		return "/faces/viewIssue";
	}
    
	public String deleteAction() {
		return "/faces/deleteIssue";
	}
    
    public String listAllIssues() {
		return "/faces/listAllIssues?faces-redirect=true";
	}
    
    public String editComment() {
		return "/faces/editComment";
	}
    
    public String listMyIssues() {
		return "/faces/listMyIssues?faces-redirect=true";
	}
    
    public String managePanel() {
		return "/faces/managePanel";
	}
    
    public List<IssueModel> getAllIssues() {
    	if (statusId > 0){
            return issueService.ListIssuesByStatus(statusId);
    	}
    	else {
    		return issueService.ListAllIssues();
    	}
    }
    
    public String listIssuesByStatus(Long id) {
    	setStatusId(id);
		return "/faces/listIssuesByStatus?faces-redirect=true";
	}
    
	public long getStatusId() {
		return statusId;
	}
	
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	public List<IssueModel> getReportedIssuesByMe() {
		   long id = ((UserModel) request.getSession().getAttribute("LOGGED_USER")).getId();
           return issueService.ListIssuesByReporter(id);
    }
	
	public List<IssueModel> getAssignedIssuesToMe() {
		   long id = ((UserModel) request.getSession().getAttribute("LOGGED_USER")).getId();
        return issueService.ListIssuesByAssignto(id);
	}
	
	public List<IssueModel> getUnassignedIssues() {
		   return issueService.ListUnassignedIssues();
	}

}
