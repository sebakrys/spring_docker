package com.sebastiankrys.test.service;


import com.sebastiankrys.test.model.Message;
import com.sebastiankrys.test.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;



    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public Message readMessage(long id){
        return messageRepository.getReferenceById(id);
    }

    public Message updateMessage(Message message, long id){
        return messageRepository.findById(id).map(
                existingMessage -> {
                    existingMessage.setMessage(message.getMessage());
                    return messageRepository.save(existingMessage);
                }).orElse(null);

    }
    public Message deleteMessage(@PathVariable long id){
        if(messageRepository.existsById(id)){
            Message deletedMessage = messageRepository.getReferenceById(id);
            messageRepository.deleteById(id);
            return deletedMessage;
        }
        return null;
    }

}
