package com.lagou.springMvc.control;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lagou.service.CityService;

@Controller
public class CityControl {
	CityService cityService;
	@Autowired
	public void setSalaryService(CityService cityService){
		this.cityService=cityService;
	}
	@RequestMapping("city")
	public String showCity(HashMap<String,String> model){
		String collection = "city";
		String city = cityService.get(collection);
		model.put("city",city);
		return "city";
	}
}
