package br.com.abneri.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.abneri.dao.TaskListDao;
import br.com.abneri.domain.TaskList;

@Repository
public class TaskListDaoImpl implements TaskListDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(TaskList pTaskList) {
		em.persist(pTaskList);
	}

	@Override
	public List<TaskList> findAll() {
		return em.createQuery("select t from TaskList t", TaskList.class).getResultList(); 
	}
	
	@Override
	public TaskList findByID(long pId) {
		return em.find(TaskList.class, pId);
	}

	@Override
	public void update(TaskList pTaskList) {
		em.merge(pTaskList);
	}

	@Override
	public void delete(long pId) {
		em.remove(em.getReference(TaskList.class, pId));
	}

}
