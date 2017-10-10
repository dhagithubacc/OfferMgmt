package com.caveofprogramming.spring.web.controller;

import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.service.SecurityService;
import com.caveofprogramming.spring.web.service.UserService;
import com.caveofprogramming.spring.web.validator.UserValidator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class UserController{
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/newaccount", method = RequestMethod.GET)
	public String registration(Model model) {
		logger.debug("inside new account before add attribute>>>>");
		model.addAttribute("userForm", new User());
		logger.debug("inside new account after addattribute>>>>");
		return "newaccount";
	}

	@RequestMapping(value = "/newaccount", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		logger.debug("inside new account>>>>");
		logger.debug("before password >>>>"+userForm.getPassword());
		logger.debug("before email >>>>"+userForm.getEmail());
		logger.debug("before confirmpassword >>>>"+userForm.getPasswordConfirm());
		
		userValidator.validate(userForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "newaccount";
		}

		userForm.setEnabled(true);

		logger.debug("Before  save new account>>>>");
		userService.save(userForm);
		
		logger.debug("after save new account>>>>");
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		logger.debug("after autologin new account>>>>");

		return "redirect:/accountcreated";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		logger.debug("Inside login>>>>>");
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		logger.debug("outside login method>>>>>");
		return "login";
	}

	@RequestMapping(value = "/accountcreated", method = RequestMethod.GET)
	public String accountCreated(Model model) {
		logger.debug("Inside accountcreated>>>>>");
		return "accountcreated";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		logger.debug("Inside welcome>>>>>");
		// return "accountcreated";
		return "home";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String exit(Model model) {
		logger.debug("Inside logout>>>>>");
		return "login";

	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		List<User> users = userService.getAllUsers();

		model.addAttribute("users", users);

		return "admin";
	}

}
