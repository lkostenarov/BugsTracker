package bg.bugstracker.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.CommentModel;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.CommentServiceLocal;

@ManagedBean (name = "deleteCommentBean")
@RequestScoped
public class DeleteCommentBean {

	@Inject
	HttpServletRequest request;
	
	@EJB
	CommentServiceLocal commentService;
	
	private CommentModel deleteComment;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("comment_id");
		
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			deleteComment = commentService.findById(Long.valueOf(id));
		}
	}
	
	public String deleteComment() {

		commentService.delete(deleteComment);
		
		//return "/faces/listAllIssues?faces-redirect=true";
		return "/faces/viewIssue";
	}

	public CommentModel getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(CommentModel deleteComment) {
		this.deleteComment = deleteComment;
	}
	
}
