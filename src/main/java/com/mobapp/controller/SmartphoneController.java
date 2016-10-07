package com.mobapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobapp.model.Smartphone;
import com.mobapp.service.SmartphoneService;

@Controller
@RequestMapping(value="/smartphones")
public class SmartphoneController {
	
	@Autowired
	private SmartphoneService smartphoneService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createSmartphonePage() {
		ModelAndView mav = new ModelAndView("phones/new-phone");
		mav.addObject("sPhone", new Smartphone());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Smartphone createSmartphone(@RequestBody Smartphone smartphone) {
		return smartphoneService.create(smartphone);
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editSmartphonePage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("phones/edit-phone");
		Smartphone smartphone = smartphoneService.get(id);
		mav.addObject("sPhone", smartphone);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Smartphone editSmartphone(@PathVariable int id, 
			@RequestBody Smartphone smartphone) {
		smartphone.setId(id);
		return smartphoneService.update(smartphone);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Smartphone deleteSmartphone(@PathVariable int id) {
		return smartphoneService.delete(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Smartphone> allPhones() {
		return smartphoneService.getAll();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView allPhonesPage() {
		ModelAndView mav = new ModelAndView("phones/all-phones");
		List<Smartphone> smartphones = new ArrayList<Smartphone>();
		smartphones.addAll(allPhones());
		mav.addObject("smartphones", smartphones);
		return mav;
	}
	
}
