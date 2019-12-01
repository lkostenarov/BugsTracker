package bg.bugstracker.service;

import java.util.List;


import javax.ejb.Local;

import bg.bugstracker.entity.CommentModel;


@Local
public interface CommentServiceLocal {

	List<CommentModel> ListAllComments();
	
	CommentModel save(CommentModel entity);

	CommentModel update(CommentModel entity);

	void delete(CommentModel entity);

	CommentModel findById(Long id);
	List<CommentModel> findByIssueId(Long id);
	List<CommentModel> findByUserId(Long id);
}
