package com.deltasi.chat.controllers;

import java.security.Principal;
import java.util.Base64;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.deltasi.chat.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {


    private static final Logger logger = LogManager.getLogger(UsersController.class);

    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;

    @PostMapping(value = "/do")
    public void login(@RequestParam("username") final String username, @RequestParam("password") final String password, final HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq =
                new UsernamePasswordAuthenticationToken(username, password);
       authenticate(authReq,request);
    }


    private void authenticate(UsernamePasswordAuthenticationToken token,final HttpServletRequest request)
    {
        Authentication auth = authManager.authenticate(token);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
    }

    @PostMapping(value = "/act")
    public boolean login(@RequestBody User user,final HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authReq =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticate(authReq, request);
        }
        catch (Exception ex)
        {
            logger.error((ex.getMessage()));
            return  false;
        }
        return true;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
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
