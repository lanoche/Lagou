package com.lagou.springMvc.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lagou.service.SalaryService;

@Controller
public class SalaryControl {
	SalaryService salaryService;
	@Autowired
	public void setSalaryService(SalaryService salaryService){
		this.salaryService=salaryService;
	}
	@RequestMapping("salary")
	public String showSalary(HashMap<String,String> model){
		String collection = "lagou";
		String salary = salaryService.get(collection);
		model.put("salary",salary);
		return "salary";
	}
}
