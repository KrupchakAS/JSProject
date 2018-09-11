package app.controller;


import app.dto.CartDTO;
import app.dto.UserDTO;
import app.exception.MyRuntimeException;
import app.service.api.UserService;
import app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("cartDTO")
@RequestMapping(value = "/jsDonut")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String createModel(ModelMap modelMap, CartDTO cartDTO, HttpSession session){
        modelMap.addAttribute("userForm",new UserDTO());
        return "welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "welcome";
        }
        userService.create(userForm);
        return "redirect:/jsDonut/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid UserDTO userDTO,BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        return "redirect:/jsDonut/welcome";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/jsDonut/welcome";
    }

}
