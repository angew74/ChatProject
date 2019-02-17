package com.deltasi.chat.repository;

import com.deltasi.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.transaction.Transactional;



@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


   void deleteById(int id);


   List<User> findByMailaziendale(String email);
   User findUserById(int id);
   List<User> getAllByAuthorities(String autority);
   User findUserByUsername(String username);
   List<User> findAllBy();
   int countAllBy();

}

