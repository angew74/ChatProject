package com.deltasi.chat.services;


import com.deltasi.chat.contracts.IMessageService;
import com.deltasi.chat.model.ChatMessage;
import com.deltasi.chat.model.Topic;
import com.deltasi.chat.model.User;
import com.deltasi.chat.repository.ChatMessageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChatMessageService implements IMessageService {

    private static final Logger logger = LogManager.getLogger(ChatMessageService.class);

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void addMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    @Override
    public List<ChatMessage> getAllMessages() {
       return chatMessageRepository.findAllBy();
    }

    @Override
    public ChatMessage getChatMessage(int id) {
        return  chatMessageRepository.findChatMessageById(id);
    }

    @Override
    public ChatMessage updateMessage(ChatMessage message) {
        return null;
    }

    @Override
    public List<ChatMessage> getChatMessagesBySender(String username) {
       return chatMessageRepository.findAllBySender(username);
    }

    @Override
    public List<ChatMessage> getChatMessagesByUser(User user) {
      return chatMessageRepository.findAllByUser(user);
    }


    @Override
    public Topic getTopicByIdMessage(int id) {
       return  chatMessageRepository.findTopicById(id);
    }

    @Override
    public List<Topic> getTopicsBySender(String sender) {
      return  chatMessageRepository.findTopicsBySender(sender);
    }

    @Override
    public  List<ChatMessage> findAllByTopic(Topic topic){
        return chatMessageRepository.findAllByTopic(topic);
    }
}
