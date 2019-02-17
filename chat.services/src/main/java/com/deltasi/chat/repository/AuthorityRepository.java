package com.deltasi.chat.repository;

import com.deltasi.chat.model.Authority;

import com.deltasi.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Override
    List<Authority> findAll();

    List<Authority> findAllByUser(User user);



}
