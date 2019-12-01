package bg.bugstracker.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bg.bugstracker.entity.base.BaseDomainObject;
import bg.bugstracker.entity.IssueModel;

@Entity
@Table (name="users")
public class UserModel extends BaseDomainObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String	username; 
	private String	password; 
	private String	fullname; 
	private String	email;
	private int	isactive; 
	
	private Date create_dt;
	private Date modify_dt; 

	private List <IssueModel> reportedIssues; // List all reported issues
	private List <IssueModel> assignedIssues; // List all assigned issues
	
	public UserModel(){
		super();
	}
	
	public UserModel(long id, String username, String password, String fullname, String email, int isactive){
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.isactive = isactive;
	}

	@Column (name="username", length=15, nullable=false )
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column (name="password", length=400, nullable=false )
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column (name="fullname", length=64, nullable=false )
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column (name="email", length=64, nullable=false )
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (name="isactive", nullable=false )
	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	// Connect the user with the reported issues 
	@OneToMany (cascade=CascadeType.ALL , mappedBy="reporter", fetch=FetchType.LAZY)
	public List<IssueModel> getReportedIssues() {
		return reportedIssues;
	}

	public void setReportedIssues(List<IssueModel> issues) {
		reportedIssues = issues;
	}

	// Connect the user with the assigned issues 
	@OneToMany (cascade=CascadeType.ALL , mappedBy="assignto", fetch=FetchType.LAZY)
	public List<IssueModel> getAssignedIssues() {
		return assignedIssues;
	}

	public void setAssignedIssues(List<IssueModel> assignedIssues) {
		this.assignedIssues = assignedIssues;
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
}
