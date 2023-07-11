package br.com.mvc.projectListTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mvc.projectListTask.entities.UserDtls;
import br.com.mvc.projectListTask.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("session")
public class HomeController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String showHomePage() {
    	return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "userHtml/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute HttpServletRequest httpServletRequest) {
        return "userHtml/register";
    }

    @PostMapping("/logout")
    public String logout(RedirectAttributes attributes) {
    	attributes.addFlashAttribute("mensagem", "Sucesso de Logout");
        System.out.println("Logout");
        return "redirect:/login";
    }

    @PostMapping("/createUser")
    public String createuser(@ModelAttribute UserDtls user, RedirectAttributes attributes) {
        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
        	attributes.addFlashAttribute("mensagem_erro", "Email já cadastrado.");
        } else {
            UserDtls userDtls = userService.createUser(user);
            if (userDtls != null) {
            	attributes.addFlashAttribute("mensagem", "Sucesso ao registrar usuário.");
            } else {
            	attributes.addFlashAttribute("mensagem", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }

}
