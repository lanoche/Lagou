package com.lagou.springMvc.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lagou.service.PositionNumService;

@Controller
public class PositionNumController {
	PositionNumService service;
	
	@Autowired
	public void setService(PositionNumService service) {
		this.service = service;
	}
	
	@RequestMapping("/positionNum")
	public String showPositionNumPage(Map<String ,String> model){
		String collection = "lagou";
		String positionNum = service.get(collection);
		model.put("position",positionNum);
		return "positionNum";
	}
}
