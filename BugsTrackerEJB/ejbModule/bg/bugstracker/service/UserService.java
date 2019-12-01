package bg.bugstracker.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bugstracker.entity.UserModel;

@Stateless
public class UserService implements UserServiceLocal {

	@PersistenceContext
    protected EntityManager entityManager;

    public UserService() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> findAllUsers() {
        String query = "select model from UserModel model order by upper(model.username) asc";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

    @Override
    public UserModel save(UserModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public UserModel update(UserModel entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(UserModel entity) {
        entityManager.remove(entity);
    }

    @Override
    public UserModel findById(Long id) {
        try {
            UserModel instance = entityManager.find(UserModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public UserModel loginUser(String aUsername, String aPassword) {
        StringBuffer query = new StringBuffer(
                "select model from UserModel model where model.username = :em and model.password = :p and model.isactive=1");

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", aUsername);
        q.setParameter("p", aPassword);

        try {
            return (UserModel) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public UserModel checkUserExists(String username, Long id) {
        StringBuffer query = new StringBuffer(
                "select model from UserModel model where upper(model.username) = upper(:em)");

        if (id != null) {
            query.append(" and model.id <> ").append(id);
        }

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", username);

        try {
            return (UserModel) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
