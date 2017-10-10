package com.caveofprogramming.spring.web.validator;


import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	Logger logger = LoggerFactory.getLogger(UserValidator.class);
	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
       
        logger.debug("inside validate>>>>"+user.getUsername());
        logger.debug("inside validate-errors>>>>"+errors);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        logger.debug("outside rejectIfEmptyOrWhitespace");
        if (user.getUsername().length() < 8 || user.getUsername().length() > 15) {
        	
            errors.rejectValue("username", "Size.userForm.username");
        }
        logger.debug("outside username>>>>");
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        logger.debug("outside validate>>>>");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        // errors.rejectValue("email", "ValidEmail.user.email");
        
        logger.debug("before password >>>>"+user.getPassword());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 15) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        logger.debug("outside password>>>>");
        logger.debug("before user.getPasswordConfirm() >>>>"+user.getPasswordConfirm());
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        logger.debug("outside validate>>>>");
    }
}
