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

import br.com.abneri.domain.TaskList;
import br.com.abneri.service.TaskListService;

@Controller
@RequestMapping("tasklist")
public class TaskListController {
	
	@Autowired
	private TaskListService taskListService;
	
	@GetMapping("/list")
	public ModelAndView list(ModelMap pModel){
		pModel.addAttribute("tasklists", taskListService.findAll());
		return new ModelAndView("/tasklist/list", pModel);
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("tasklist") TaskList pTaskList){
		return "/tasklist/add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("tasklist") TaskList pTaskList, BindingResult pResult, RedirectAttributes pAttr){
		if(pResult.hasErrors()){
			return "/tasklist/add";
		}
		
		taskListService.save(pTaskList);
		pAttr.addFlashAttribute("message", "Task list created.");
		return "redirect:/tasklist/list";
	}

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable("id") long pId, ModelMap pModel){
		TaskList taskList = taskListService.findByID(pId);
		pModel.addAttribute("tasklist", taskList);
		return new ModelAndView("/tasklist/add", pModel);
	}
	
	@PutMapping("/save")
	public String confirmUpdate(@Valid @ModelAttribute("tasklist") TaskList pTaskList, BindingResult pResult, RedirectAttributes pAttr){
		if(pResult.hasErrors()){
			return "/tasklist/add";
		}
		
		taskListService.update(pTaskList);
		pAttr.addFlashAttribute("message", "Successfully updated task list.");
		return "redirect:/tasklist/list";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") long pId,  RedirectAttributes pAttr){
		taskListService.delete(pId);
		pAttr.addFlashAttribute("message", "Successfully removed task list.");
		return "redirect:/tasklist/list";
	}
}
