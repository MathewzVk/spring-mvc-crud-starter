package com.luv2code.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

import jakarta.annotation.PostConstruct;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		// TODO Auto-generated constructor stub
		this.employeeService = theEmployeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model thModel) {
		
		List<Employee> list = employeeService.findAll();
		
		thModel.addAttribute("list", list);
		
		return "list-employees";
	}
	
	@GetMapping("/showform")
	public String addEmployee(Model theModel) {
		theModel.addAttribute("employee", new Employee());
		
		return "add-form";
	}
	
	@GetMapping("/showformForUpdate")
	public String update(@RequestParam("employeeId") int theId, Model theModel) {
		Employee employee = employeeService.findById(theId);
		theModel.addAttribute("employee", employee);
		
		return "add-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee thEmployee) {
		employeeService.save(thEmployee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
	
}
