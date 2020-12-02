package br.com.abneri.dao;

import java.util.List;

import br.com.abneri.domain.TaskList;

public interface TaskListDao {
	void save(TaskList pTaskList);
	List<TaskList> findAll();
	TaskList findByID(long pId);
	void update(TaskList pTaskList);
	void delete(long pId);
}