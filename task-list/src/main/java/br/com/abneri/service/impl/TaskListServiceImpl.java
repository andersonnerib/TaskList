package br.com.abneri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.abneri.dao.TaskListDao;
import br.com.abneri.domain.TaskList;
import br.com.abneri.service.TaskListService;

@Service
@Transactional
public class TaskListServiceImpl implements TaskListService {
	
	@Autowired
	private TaskListDao taskListDao;
	
	@Override
	public void save(TaskList pTaskList) {
		taskListDao.save(pTaskList);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TaskList> findAll() {
		return taskListDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TaskList findByID(long pId) {
		return taskListDao.findByID(pId);
	}

	@Override
	public void update(TaskList pTaskList) {
		taskListDao.update(pTaskList);
	}

	@Override
	public void delete(long pId) {
		taskListDao.delete(pId);
	}

}
