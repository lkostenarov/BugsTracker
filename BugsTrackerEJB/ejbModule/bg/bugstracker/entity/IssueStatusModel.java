package bg.bugstracker.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bg.bugstracker.entity.base.BaseDomainObject;

@Entity
@Table (name="issue_status")
public class IssueStatusModel extends BaseDomainObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String description;
	List<IssueModel> issues;
	
	public IssueStatusModel() {
		super();
	}

	public IssueStatusModel(long id, String description) {
		this.id = id;
		this.description = description;
	}

	@Column (name="description", length=64, nullable=false )
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany (cascade=CascadeType.ALL , mappedBy="issueStatus", fetch=FetchType.LAZY)
	public List<IssueModel> getIssues() {
		return issues;
	}

	public void setIssues(List<IssueModel> issues) {
		this.issues = issues;
	}
	
	
	
}
