package com.deltasi.chat.controllers;

import java.security.Principal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {


    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;

    @PostMapping(value = "/do")
    public void login(@RequestParam("username") final String username, @RequestParam("password") final String password, final HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
    }


    @GetMapping(value = "/index")
    public String index(Model model, Principal principal) {
        model.addAttribute("titlepage", "Homepage");
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }


    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        String loggedInUserName = principal.getName();
        model.addAttribute("user", loggedInUserName);
        model.addAttribute("titlepage", "Amministrazione Home");
        return "admin";
    }



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        model.addAttribute("message",
                "You have successfully logged off from application !");
        return "logout";

    }

    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";

    }
}
