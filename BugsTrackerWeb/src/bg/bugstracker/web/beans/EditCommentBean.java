package bg.bugstracker.web.beans;

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

import bg.bugstracker.entity.CommentModel;
import bg.bugstracker.service.CommentServiceLocal;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean (name = "editCommentBean")
@ViewScoped
public class EditCommentBean {
	
	@Inject
	HttpServletRequest request;
	
	@EJB
	CommentServiceLocal commentService;
	
	private CommentModel editComment;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			editComment = commentService.findById(Long.valueOf(id));
		}
	}
	
	public String updateAction() {

		if (!validate()) {
			return null;
		}
		
		commentService.update(editComment);
		
		//return "/faces/listAllIssues?faces-redirect=true";
		return "/faces/viewIssue";
	}

	public CommentModel getEditComment() {
		return editComment;
	}

	public void setEditComment(CommentModel editComment) {
		this.editComment = editComment;
	}
	
	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(editComment.getContent() )) {
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
