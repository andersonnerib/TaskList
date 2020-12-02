package br.com.abneri.service;

import java.util.List;

import br.com.abneri.domain.Task;

public interface TaskService {
	void save(Task pTask, long pTaskListId);
	List<Task> findAll();
	List<Task> findByTaskList(long pTaskListId);
	Task findByID(long pId);
	void update(Task pTask, long pTaskListId);
	void delete(long pId);
}
