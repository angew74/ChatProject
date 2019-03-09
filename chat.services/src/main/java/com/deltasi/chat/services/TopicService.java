package com.deltasi.chat.services;

import com.deltasi.chat.contracts.ITopicService;
import com.deltasi.chat.model.Topic;
import com.deltasi.chat.repository.TopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class TopicService implements ITopicService {

    private static final Logger logger = LogManager.getLogger(ChatMessageService.class);

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void addTopic(Topic topic)
    {
        topicRepository.save(topic);
    }

    @Override
    public List<Topic> getAlTopics()
    {
        return  topicRepository.findAllBy();
    }
    @Override
    public Topic getTopic(int id)
    {
        return  topicRepository.findAllById(id);
    }

    @Override
    public Topic updateTopic(Topic topic)
    {
        return  null;
    }
}
