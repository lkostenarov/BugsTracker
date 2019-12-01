package bg.bugstracker.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bg.bugstracker.entity.base.BaseDomainObject;

@Entity
@Table (name="issues")
public class IssueModel extends BaseDomainObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;


	private Date create_dt;
	
	private Date modify_dt; 
	
	private UserModel reporter; 
	private UserModel assignto; 
	private ProjectModel project;
	private IssueStatusModel issueStatus;
	
	List<CommentModel> comments;
	
	public IssueModel() {

	}
	
	public IssueModel(long id, String title, String description, Date create_dt, Date modify_dt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.create_dt = create_dt;
		this.modify_dt = modify_dt;
	}
	
	
	@Column(name = "title", length = 255, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 4000, nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "create_dt", nullable = false)
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	
	@Column(name = "modify_dt", nullable = false)
	public Date getModify_dt() {
		return modify_dt;
	}
	public void setModify_dt(Date modify_dt) {
		this.modify_dt = modify_dt;
	}
	
	@ManyToOne
	@JoinColumn (name="reporter_id", referencedColumnName = "id")
	public UserModel getReporter() {
		return reporter;
	}
	
	public void setReporter(UserModel reporter) {
		this.reporter = reporter;
	}

	@ManyToOne
	@JoinColumn (name="assignto_id", referencedColumnName = "id")
	public UserModel getAssignto() {
		return assignto;
	}
	
	public void setAssignto(UserModel assignto) {
		this.assignto = assignto;
	}

	@ManyToOne
	@JoinColumn (name="project_id", referencedColumnName = "id")
	public ProjectModel getProject() {		
		return project;
	}

	public void setProject(ProjectModel project) {
			this.project = project;
	}	
	
	@OneToMany (cascade=CascadeType.ALL , mappedBy="issue", fetch=FetchType.LAZY)
	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	@ManyToOne
	@JoinColumn (name="status_id", referencedColumnName = "id")
	public IssueStatusModel getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(IssueStatusModel issueStatus) {
		this.issueStatus = issueStatus;

	}

}
