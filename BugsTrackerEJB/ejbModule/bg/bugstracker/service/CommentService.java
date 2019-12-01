package bg.bugstracker.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bugstracker.entity.CommentModel;

@Stateless
public class CommentService implements CommentServiceLocal {

	@PersistenceContext
	protected EntityManager entityManager;
	
    public CommentService() {
    }
    
    @SuppressWarnings("unchecked")
    @Override
	public List<CommentModel> ListAllComments(){
    	String query = "select model from CommentModel model order by upper(model.create_dt)";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
	
    @Override
	public CommentModel save(CommentModel entity){
    	entityManager.persist(entity);
    	return entity;
    }

    @Override
	public CommentModel update(CommentModel entity){
		entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}

    @Override
	public void delete(CommentModel entity){
    	CommentModel instance = entityManager.find(CommentModel.class, entity.getId());
    	//entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    	entityManager.remove(instance);
    }

    @Override
	public CommentModel findById(Long id){
    	try {
    		CommentModel instance = entityManager.find(CommentModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<CommentModel> findByIssueId(Long id){
    	return entityManager.createQuery(
    		    "SELECT model FROM CommentModel model WHERE model.issue.id = :issueId")
    		    .setParameter("issueId", id)
    		    .getResultList();
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<CommentModel> findByUserId(Long id){
		return entityManager.createQuery(
    		    "SELECT model FROM CommentModel model WHERE model.user.id = :userId")
    		    .setParameter("userId", id)
    		    .getResultList();
	}

}
