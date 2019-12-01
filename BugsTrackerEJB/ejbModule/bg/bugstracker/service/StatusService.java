package bg.bugstracker.service;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bugstracker.entity.IssueStatusModel;

@Stateless
public class StatusService implements StatusServiceLocal {

	@PersistenceContext
    protected EntityManager entityManager;

    public StatusService() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<IssueStatusModel> findAllStatus() {
        String query = "select model from IssueStatusModel model order by upper(model.id) asc";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
    
    @Override
    public IssueStatusModel findById(Long id) {
        try {
        	IssueStatusModel instance = entityManager.find(IssueStatusModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
