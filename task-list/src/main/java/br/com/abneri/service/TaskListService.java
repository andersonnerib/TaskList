package br.com.abneri.service;

import java.util.List;

import br.com.abneri.domain.TaskList;

public interface TaskListService {
	void save(TaskList pTaskList);
	List<TaskList> findAll();
	TaskList findByID(long pId);
	void update(TaskList pTaskList);
	void delete(long pId);
}
