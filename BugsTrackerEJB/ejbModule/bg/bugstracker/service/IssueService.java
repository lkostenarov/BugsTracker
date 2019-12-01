package bg.bugstracker.service;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bugstracker.entity.CommentModel;
import bg.bugstracker.entity.IssueModel;

@Stateless
public class IssueService implements IssueServiceLocal {

	@PersistenceContext
	protected EntityManager entityManager;
	
    public IssueService() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<IssueModel> ListAllIssues(){
    	String query = "select model from IssueModel model order by upper(model.id) asc";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
	
    @Override
	public IssueModel save(IssueModel entity){
    	entityManager.persist(entity);
    	return entity;
    }
    
    @Override
	public IssueModel update(IssueModel entity){
    	entityManager.merge(entity);
    	entityManager.flush();
    	return entity;
    }

    @Override
	public void delete(IssueModel entity){
    	IssueModel instance = entityManager.find(IssueModel.class, entity.getId());
    	entityManager.remove(instance);
    }

    @Override
	public IssueModel findById(Long id){
    	 try {
    		 IssueModel instance = entityManager.find(IssueModel.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<CommentModel> ListIssueComments(Long id) {
    	return entityManager.createQuery(
    		    "SELECT model FROM CommentModel model WHERE model.issue.id = :issueId")
    		    .setParameter("issueId", id)
    		    .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<IssueModel> ListIssuesByReporter(Long id){
    	return entityManager.createQuery(
    		    "SELECT model FROM IssueModel model Where model.reporter.id = :reporterId order by upper(model.id) asc")
    		    .setParameter("reporterId", id)
    		    .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<IssueModel> ListIssuesByAssignto(Long id){
    	return entityManager.createQuery(
    		    "SELECT model FROM IssueModel model Where model.assignto.id = :assigntoId order by upper(model.id) asc")
    		    .setParameter("assigntoId", id)
    		    .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<IssueModel> ListUnassignedIssues(){
    	String query = "select model from IssueModel model WHERE coalesce(model.assignto.id,0) = 0 order by upper(model.id) asc";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<IssueModel> ListIssuesByStatus(Long id){
    	
    	return entityManager.createQuery(
    		    "SELECT model FROM IssueModel model Where model.issueStatus.id = :statusId order by upper(model.id) asc")
    		    .setParameter("statusId", id)
    		    .getResultList();
    }
    
}
