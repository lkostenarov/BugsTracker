package bg.bugstracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bg.bugstracker.entity.base.BaseDomainObject;

@Entity
@Table (name="comments")
public class CommentModel extends BaseDomainObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String content; 
	private long user_id; 
	private String visitor; 
	private Date create_dt;
	private Date modify_dt; 
	private IssueModel issue;
	
	public CommentModel() {
		super();
	}
		
	public CommentModel(long id, String content, long user_id, String visitor, Date create_dt, IssueModel issue) {
		super();
		this.id = id;
		this.content = content;
		this.user_id = user_id;
		this.visitor = visitor;
		this.create_dt = create_dt;
		this.issue = issue;
	}


	@Column (name="content", length=4000, nullable=false )
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column (name="user_id", nullable=false )
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	@Column (name="visitor", length=255, nullable=false )
	public String getVisitor() {
		return visitor;
	}
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	
	@Column (name="create_dt", nullable=false )
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	
	@Column (name="modify_dt", nullable=false )
	public Date getModify_dt() {
		return modify_dt;
	}

	public void setModify_dt(Date modify_dt) {
		this.modify_dt = modify_dt;
	}

	@ManyToOne
	@JoinColumn (name="issue_id", referencedColumnName = "id")
	public IssueModel getIssue() {
		return issue;
	}
	public void setIssue(IssueModel issue) {
		this.issue = issue;
	}
	
}
