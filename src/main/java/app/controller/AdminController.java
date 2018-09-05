package app.controller;

import app.dto.UserDTO;
import app.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adminPanel",method = RequestMethod.GET)
    public String adminPanel(){
        return "adminPanel";
    }

    @RequestMapping(value = "/usersList" , method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<UserDTO> users = userService.getAll();
        model.addAttribute("users", users);
        return "usersList";
    }

    @RequestMapping(value = "/productsList", method = RequestMethod.GET)
    public String productsList(ModelMap model) {
        List<UserDTO> users = userService.getAll();
        model.addAttribute("cars", users);
        return "productsList";
    }
}