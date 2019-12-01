package bg.bugstracker.web.beans;

import java.util.Iterator;
import java.util.List;

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
import bg.bugstracker.entity.IssueModel;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.CommentServiceLocal;
import bg.bugstracker.service.IssueServiceLocal;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean (name = "viewIssueBean")
@ViewScoped
public class ViewIssueBean  {

	@Inject
	HttpServletRequest request;
	
	@EJB
	IssueServiceLocal issueService;
	
	@EJB
	CommentServiceLocal commentService;
	
	private List<CommentModel> commentList;	
	private IssueModel issue;
	private CommentModel newComment;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			issue = issueService.findById(Long.valueOf(id));
			commentList = commentService.findByIssueId(Long.valueOf(id));
			newComment = new CommentModel();
			newComment.setIssue(issue);
			newComment.setUser_id(((UserModel) request.getSession().getAttribute("LOGGED_USER")).getId() );
			newComment.setVisitor(((UserModel) request.getSession().getAttribute("LOGGED_USER")).getFullname());
		}
	}
	
	public String listAllIssues() {
		return "/faces/listAllIssues?faces-redirect=true";
	}
	   
	public String createAction() {
		return "/faces/createIssue?faces-redirect=true";
	}
	
	public String addComment() {

		if (!validate()) {
			return null;
		}

		commentService. save(newComment);
		
		return "/faces/viewIssue";
	}
	
	public IssueModel getIssue() {
		return issue;
	}

	public void setIssue(IssueModel issue) {
		this.issue = issue;
	}
	
	
	public List<CommentModel> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentModel> commentList) {
		this.commentList = commentList;
	}

	public CommentModel getNewComment() {
		return newComment;
	}

	public void setNewComment(CommentModel newComment) {
		this.newComment = newComment;
	}
	
	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(newComment.getVisitor())) {
			MessageUtils.addErrorMessage("error.required.commenter");
			hasErrors = true;
		}

		if (StringUtils.isBlank(newComment.getContent())) {
			MessageUtils.addErrorMessage("error.required.content");
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
