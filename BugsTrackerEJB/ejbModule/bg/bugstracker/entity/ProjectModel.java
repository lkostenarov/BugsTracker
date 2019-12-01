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


@Entity
@Table (name="projects")
public class ProjectModel  extends BaseDomainObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private long company_id;
	
	private Date create_dt;
	private Date modify_dt; 
	List<IssueModel> Issues;
	
	public ProjectModel() {
		super();
	}
	
	public ProjectModel(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Column (name="name", length=64, nullable=false )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column (name="company_id", nullable=false )
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	
	@OneToMany (cascade=CascadeType.ALL , mappedBy="project", fetch=FetchType.LAZY)
	public List<IssueModel> getIssues() {
		return Issues;
	}
	public void setIssues(List<IssueModel> issues) {
		Issues = issues;
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
