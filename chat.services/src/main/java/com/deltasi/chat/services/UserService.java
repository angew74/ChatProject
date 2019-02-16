package com.deltasi.chat.services;

import java.util.List;

import com.deltasi.chat.Contracts.IUserService;
import com.deltasi.chat.model.User;
import com.deltasi.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service("userDetailsService")
public class UserService implements UserDetailsService , IUserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.Save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> l = userRepository.getAll();
        return l;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        User user = userRepository.findUserBy(id);
        user.getAuthorities();
        return user;

    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Transactional
    public User getByUsername(String username)
    {return userRepository.findUserByUsername(username);}



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        UserBuilder builder;
        builder = null;
        logger.debug("Ho trovato user" + username);
        //logs debug message
        if(logger.isDebugEnabled()){
            logger.debug("Sono in userdetails");
        }

        try
        {
            if (user != null) {

                builder = org.springframework.security.core.userdetails.User.withUsername(username);
                builder.disabled(!user.isEnabled());
                builder.password(user.getPassword());
                String[] authorities = user.getAuthorities()
                        .stream().map(a -> a.getAuthority()).toArray(String[]::new);

                builder.authorities(authorities);
                builder.roles(authorities);
            } else {
                logger.error("Utente non trovato");
                throw new UsernameNotFoundException("Utente non trovato");
            }
        }
        catch(Exception ex)
        {
            logger.error(ex.getMessage());
        }
        return builder.build();
    }

}
