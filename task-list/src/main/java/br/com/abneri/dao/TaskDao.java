package br.com.abneri.dao;

import java.util.List;

import br.com.abneri.domain.Task;

public interface TaskDao {
	void save(Task pTask);
	List<Task> findAll();
	List<Task> findByTaskList(long pTaskListId);
	Task findByID(long pId);
	void update(Task pTask);
	void deleteById(long pId);
}
