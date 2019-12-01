package bg.bugstracker.service;

import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bugstracker.entity.ProjectModel;

@Stateless
public class ProjectService implements ProjectServiceLocal {
	
	@PersistenceContext
    protected EntityManager entityManager;

    public ProjectService() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectModel> findAllProjects() {
        String query = "select model from ProjectModel model order by upper(model.id) asc";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
    
    @Override
    public ProjectModel findById(Long id) {
        try {
        	ProjectModel instance = entityManager.find(ProjectModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    @Override
   	public ProjectModel save(ProjectModel entity){
       	entityManager.persist(entity);
       	return entity;
       }
    
    @Override
   	public ProjectModel update(ProjectModel entity){
   		entityManager.merge(entity);
   		entityManager.flush();
   		return entity;
   	}
}
