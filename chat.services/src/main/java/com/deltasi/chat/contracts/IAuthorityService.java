package com.deltasi.chat.contracts;



import com.deltasi.chat.model.Authority;
import com.deltasi.chat.model.User;

import java.util.List;

/**
 *
 * @author Nick
 */
public interface IAuthorityService
{
    void Save(Authority authority);

    List<Authority> findAll(String authority);

    void Delete(Authority authority);

    void Update(Authority authority);

    List<Authority> getAuthorityByUser(User user);




}
