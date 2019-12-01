package bg.bugstracker.service;

import java.util.List;


import javax.ejb.Local;

import bg.bugstracker.entity.CommentModel;
import bg.bugstracker.entity.IssueModel;

@Local
public interface IssueServiceLocal {

	List<IssueModel> ListAllIssues();
	
	IssueModel save(IssueModel entity);

	IssueModel update(IssueModel entity);

	void delete(IssueModel entity);

	IssueModel findById(Long id);
	
	List<CommentModel> ListIssueComments(Long id);
	
	List<IssueModel> ListIssuesByReporter(Long id);
	
	List<IssueModel> ListIssuesByAssignto(Long id);
	
	List<IssueModel> ListUnassignedIssues();
	
	List<IssueModel> ListIssuesByStatus(Long id);
	
}
