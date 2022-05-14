package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDaoJPA<T> implements GenericDao<T> {
    
	private Class<T> type;
    protected EntityManager entityManager;

    public GenericDaoJPA(Class<T> type) {
        super();
        this.type = type;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public T get(Long id) {
        T entity = this.entityManager.find(this.type, id);
        return entity;
    }

    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT entity FROM " + this.type.getName() + " entity").getResultList();
    }

    @Override
    public void insert(T object) {
        entityManager.persist(object);
    }

    @Override
    public void delete(T object) {
        entityManager.remove(entityManager.merge(object));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(Long id) {
        T entity = this.entityManager.find(this.type, id);
        return entity != null;
    }

    @Override
    public T update(T object) {
        return entityManager.merge(object);
    }
}
