package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(path = "/api2", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String api2() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = userService.getAllUser();
		return mapper.writeValueAsString(users);
	}

//	@GetMapping(path ="/api", produces = "application/json;)
//	@RequestBody
	@RequestMapping(path = "/api", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String api(Model model, @Param("keyword") String keyword) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = null;
		if (keyword != null && !keyword.isEmpty()) {
			users = userService.findAllUser(keyword);
			model.addAttribute("users", mapper.writeValueAsString(users));
			model.addAttribute("keyword", keyword);
		} else {
			users = userService.getAllUser();
			model.addAttribute("users", mapper.writeValueAsString(users));
			System.out.println("all");
		}

		return mapper.writeValueAsString(users);
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "add";
	}

	@PostMapping("/save")
	public String save(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Optional<User> userEdit = userService.findUserById(id);
		userEdit.ifPresent(user -> model.addAttribute("user", user));
		return "edit";
	}
}
