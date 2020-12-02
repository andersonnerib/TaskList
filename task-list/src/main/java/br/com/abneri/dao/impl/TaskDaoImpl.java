package br.com.abneri.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.abneri.dao.TaskDao;
import br.com.abneri.domain.Task;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Task pTask) {
		em.persist(pTask);
	}

	@Override
	public List<Task> findAll() {
		return em.createQuery("select t from task t", Task.class).getResultList();
	}
	
	@Override
	public List<Task> findByTaskList(long pIdTaskList) {
		return em.createQuery("select t from Task t where t.tasklist.id = :taskListId", Task.class)
				.setParameter("taskListId", pIdTaskList)
				.getResultList();
	}

	@Override
	public Task findByID(long pId) {
		return em.find(Task.class, pId);
	}

	@Override
	public void update(Task pTask) {
		em.merge(pTask);
	}

	@Override
	public void deleteById(long pId) {
		em.remove(em.getReference(Task.class, pId));
	}

}
