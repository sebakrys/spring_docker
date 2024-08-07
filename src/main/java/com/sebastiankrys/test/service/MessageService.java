package com.sebastiankrys.test.service;


import com.sebastiankrys.test.model.Message;
import com.sebastiankrys.test.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;



    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public Message readMessage(String uuid){
        return messageRepository.getReferenceByUuid(uuid);
    }

    public Page<Message> readPageMessages(Pageable pageable){
        return messageRepository.findAll(pageable);
    }

    public Message updateMessage(Message message, String uuid){
        return messageRepository.findByUuid(uuid).map(
                existingMessage -> {
                    existingMessage.setMessage(message.getMessage());
                    return messageRepository.save(existingMessage);
                }).orElse(null);

    }
    public Message deleteMessage(@PathVariable String uuid){
        if(messageRepository.existsByUuid(uuid)){
            Message deletedMessage = messageRepository.getReferenceByUuid(uuid);
            messageRepository.deleteById(deletedMessage.getId());
            return deletedMessage;
        }
        return null;
    }

    public ResponseEntity emptyOrNotExistingMessageResponse(Object object){
        if(object instanceof Page){
            if(((Page)object).getNumberOfElements()<1)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No messages");
            else return ResponseEntity.ok(((Page)object).stream().toList());
        } else {
            if(object==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No message found with the provided UUID");
            }else return ResponseEntity.ok(object);
        }
    }

}
