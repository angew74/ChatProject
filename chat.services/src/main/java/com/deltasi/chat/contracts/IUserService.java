package com.deltasi.chat.contracts;

import com.deltasi.chat.model.User;

import java.util.List;

/**
 *
 * @author AdminDSI
 */
public interface IUserService {

    void addUser(User utente);

    List<User> getAllUsers();

    void deleteUser(Integer id);

    User getUser(int id);

    User updateUser(User utente);

    User getByUsername(String username);

    List<User> getUserByMailAziendale(String email);


}
