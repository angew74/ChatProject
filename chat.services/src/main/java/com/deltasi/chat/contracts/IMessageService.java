package com.deltasi.chat.contracts;

import com.deltasi.chat.model.ChatMessage;
import com.deltasi.chat.model.Topic;
import com.deltasi.chat.model.User;

import java.util.List;

public interface IMessageService {

    void addMessage(ChatMessage message);

    List<ChatMessage> getAllMessages();

    ChatMessage getChatMessage(int id);

    ChatMessage updateMessage(ChatMessage message);

    List<ChatMessage> getChatMessagesBySender(String username);

    List<ChatMessage> getChatMessagesByUser(User user);

    Topic getTopicByIdMessage(int id);

    List<ChatMessage> findAllByTopic(Topic topic);

    List<Topic> getTopicsBySender(String sender);
}
