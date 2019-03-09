package com.deltasi.chat.contracts;

import com.deltasi.chat.model.Topic;

import java.util.List;

public interface ITopicService {


    void addTopic(Topic topic);

    List<Topic> getAlTopics();

    Topic getTopic(int id);

    Topic updateTopic(Topic topic);

}
