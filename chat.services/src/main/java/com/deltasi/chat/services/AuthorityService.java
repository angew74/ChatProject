package com.deltasi.chat.services;

import java.util.List;
import javax.transaction.Transactional;

import com.deltasi.chat.Contracts.IAuthorityService;
import com.deltasi.chat.model.Authority;
import com.deltasi.chat.model.User;
import com.deltasi.chat.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nick
 */
@Service
@Transactional
public class AuthorityService implements IAuthorityService {


    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void Save(Authority authority) {
        authorityRepository.Save(authority);
    }

    @Override
    public List<Authority> findAll(String authority) {
        return authorityRepository.findAll();
    }

    @Override
    public void Delete(Authority authority) {
      authorityRepository.delete(authority);
    }

    @Override
    public List<Authority> getAuthorityByUser(User user) {
        return authorityRepository.findAllByUser(user);
    }

}
