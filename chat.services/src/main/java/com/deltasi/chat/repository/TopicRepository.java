package com.deltasi.chat.repository;


import com.deltasi.chat.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TopicRepository extends JpaRepository<Topic, Long> {


    List<Topic> findAllBy();

    Topic findAllById (int id);

}
