package com.deltasi.chat.repository;

import com.deltasi.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.executable.ValidateOnExecution;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


   void deleteById(int id);


   User findByEmail(String email);
   User findUserBy(int id);
   List<User> getAllByAuthorities(String autority);
   User findUserByUsername(String username);
   List<User> getAll();
   void Save(User user);
   int countAll();
   User updateUser(User user);
}

