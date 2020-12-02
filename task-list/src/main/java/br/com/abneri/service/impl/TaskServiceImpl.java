package br.com.abneri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.abneri.dao.TaskDao;
import br.com.abneri.domain.Task;
import br.com.abneri.service.TaskListService;
import br.com.abneri.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private TaskListService taskListService;
	
	@Override
	public void save(Task pTask, long pTaskListId) {
		pTask.setTasklist(taskListService.findByID(pTaskListId));
		taskDao.save(pTask);
	}

	@Override
	public List<Task> findAll() {
		return taskDao.findAll();
	}

	@Override
	public List<Task> findByTaskList(long pTaskListId) {
		return taskDao.findByTaskList(pTaskListId);
	}

	@Override
	public Task findByID(long pId) {
		return taskDao.findByID(pId);
	}

	@Override
	public void update(Task pTask, long pTaskListId) {
		pTask.setTasklist(taskListService.findByID(pTaskListId));
		taskDao.update(pTask);
	}

	@Override
	public void delete(long pId) {
		taskDao.deleteById(pId);
	}

}
