package com.example.tomcatconfig.service;

import com.example.tomcatconfig.model.Message;
import com.example.tomcatconfig.repository.MessageDao;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageDao messageDao;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void saveMessage(Message message){
        if (Objects.nonNull(message)){
            messageDao.save(message);
        }
    }
}
