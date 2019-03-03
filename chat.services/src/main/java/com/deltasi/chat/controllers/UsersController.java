package com.deltasi.chat.controllers;


import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.deltasi.chat.contracts.IAuthorityService;
import com.deltasi.chat.contracts.IUserService;
import com.deltasi.chat.model.Authority;
import com.deltasi.chat.model.User;
import com.deltasi.chat.model.UserJsonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping(value = "/chat.services/users")
@CrossOrigin(origins="*", maxAge=3600)
public class UsersController {


    private static final Logger logger = LogManager.getLogger(UsersController.class);

    @Autowired
    IUserService userservice;

    @Autowired
    IAuthorityService authorityservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/All")
    public List<User> List() {

        Map<String, String> errors = null;
        List<User> list = null;
        try {
            list = userservice.getAllUsers();
        } catch (Exception ex) {
            String error = ex.getMessage();
            logger.error(error);
        }

        return list;
    }

    @GetMapping(value = "/view/{id}")
    public UserJsonResponse viewUser(@PathVariable("id") String id) {
        Integer idf = Integer.parseInt(id);
        Map<String, String> errors = null;
        UserJsonResponse response = new UserJsonResponse();
        try {
            User user = userservice.getUser(idf);
            response.setValidated(true);
            response.setUser(user);
        } catch (Exception ex) {
            errors = new HashMap<String, String>();
            errors.put("Errrore in banca dati", ex.getMessage());
            response.setValidated(false);
            response.setErrorMessages(errors);
        }
        return response;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/register")
    public User register(Model model, Principal principal) {
       return new User();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/add")
    public UserJsonResponse AddUser(@ModelAttribute("user") User user,
                             BindingResult result) {

        UserJsonResponse response = new UserJsonResponse();
        Map<String, String> errors = null;
        try {
            if (result.hasErrors()) {
                errors = result.getFieldErrors().stream()
                        .collect(
                                Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                        );

                response.setValidated(false);
                response.setErrorMessages(errors);
            }
            User useripo = userservice.getByUsername(user.getUsername().toLowerCase());
            if (useripo != null && useripo.getUsername() != null) {
                errors = new HashMap<String, String>();
                errors.put("Errore in archivio", "Username già esistente");
                response.setValidated(false);
                response.setErrorMessages(errors);
            } else {
                String passwordhash = user.getPassword();
                user.setPassword(passwordEncoder.encode(passwordhash));
                user.setUsername(user.getUsername().toLowerCase());
                user.setEnabled(true);
                userservice.addUser(user);
                Authority authority = new Authority();
                authority.setAuthority("USER");
                authority.setUser(user);
                authorityservice.Save(authority);
                response.setValidated(true);
                response.setUser(user);
            }
        } catch (Exception ex) {
            errors = new HashMap<String, String>();
            errors.put("Errrore in banca dati", ex.getMessage());
            response.setValidated(false);
            response.setErrorMessages(errors);
        }
        return response;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/modify")
    public UserJsonResponse ModifyUser(@RequestBody User user,
                                BindingResult result) {

        UserJsonResponse response = new UserJsonResponse();
        Map<String, String> errors = null;
        try {
            if (result.hasErrors()) {
                errors = result.getFieldErrors().stream()
                        .collect(
                                Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                        );

                response.setValidated(false);
                response.setErrorMessages(errors);
                return response;
            }
            User useripo = userservice.getByUsername(user.getUsername().toLowerCase());
            Boolean ok = true;
            if (useripo.getId() == user.getId()) {
                ok = true;
            } else if (useripo.getUsername() == user.getUsername().toLowerCase()) {
                ok = false;
                errors = new HashMap<String, String>();
                errors.put("Errore in archivio", "Username già esistente");
                response.setValidated(false);
                response.setErrorMessages(errors);
            }
            if (ok == true) {
                String passwordhash = user.getPassword();
                user.setPassword(passwordEncoder.encode(passwordhash));
                user.setUsername(user.getUsername().toLowerCase());
                userservice.updateUser(user);
                response.setValidated(true);
                response.setUser(user);
            }


        } catch (Exception ex) {
            errors = new HashMap<String, String>();
            errors.put("Errrore in banca dati", ex.getMessage());
            response.setValidated(false);
            response.setErrorMessages(errors);
        }
        return response;
    }
}
