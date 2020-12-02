package br.com.abneri.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.abneri.domain.Task;
import br.com.abneri.service.TaskService;

@Controller
@RequestMapping("tasklist/{tasklistId}/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/list")
	public ModelAndView list(@PathVariable("tasklistId") long pTaskListId, ModelMap pModel){
		pModel.addAttribute("tasks", taskService.findByTaskList(pTaskListId));
		pModel.addAttribute("tasklistId", pTaskListId);
		return new ModelAndView("/task/list", pModel);
	}
	
	@GetMapping("/add")
	public String add(@ModelAttribute("task") Task pTask, @PathVariable("tasklistId") long pTaskListId){
		return "/task/add";
	}

	@PostMapping("/save")
	public String save(@PathVariable("tasklistId") long pTaskListId, @Valid @ModelAttribute("task") Task pTask, BindingResult pResult, RedirectAttributes pAttr){
		if(pResult.hasErrors()){
			return "/task/add";
		}
		
		taskService.save(pTask, pTaskListId);
		pAttr.addFlashAttribute("message", "Task saved.");
		return "redirect:/tasklist/" + pTaskListId + "/task/list";
	}

	@GetMapping("/{taskId}/update")
	public ModelAndView update(@PathVariable("tasklistId") long pTaskListId, @PathVariable("taskId") long pTaskId, ModelMap pModel){
		Task task = taskService.findByID(pTaskId);
		pModel.addAttribute("task", task);
		pModel.addAttribute("tasklistId", pTaskListId);
		return new ModelAndView("/task/add", pModel);
	}
	
	@PutMapping("/save")
	public String confirmUpdate(@PathVariable("tasklistId") long pTaskListId, @Valid @ModelAttribute("task") Task pTask, BindingResult pResult, RedirectAttributes pAttr){
		if(pResult.hasErrors()){
			return "/task/add";
		}
		
		taskService.update(pTask, pTaskListId);
		pAttr.addFlashAttribute("message", "Successfully updated task.");
		return "redirect:/tasklist/" + pTaskListId + "/task/list";
	}
	
	@GetMapping("/{taskId}/delete")
	public String delete(@PathVariable("tasklistId") long pTaskListId, @PathVariable("taskId") long pTaskId,  RedirectAttributes pAttr){
		taskService.delete(pTaskId);
		pAttr.addFlashAttribute("message", "Successfully removed task list.");
		return "redirect:/tasklist/" + pTaskListId + "/task/list";
	}

}
