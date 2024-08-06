package com.sebastiankrys.test.controller;


import com.sebastiankrys.test.model.Message;
import com.sebastiankrys.test.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;




    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @GetMapping("/message/{uuid}")
    public ResponseEntity<Object> readMessage(@PathVariable String uuid){
        Message readedMessage = messageService.readMessage(uuid);
        if(readedMessage==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No message found with the provided UUID");
        return ResponseEntity.ok(readedMessage);
    }

    @GetMapping("/message/page/{pageNr}/{pageSize}")
    public ResponseEntity<Object> readPageMessages(@PathVariable int pageNr, @PathVariable int pageSize){
        Pageable pageable = PageRequest.of(pageNr, pageSize, Sort.by("id"));
        Page<Message> pageMessage = messageService.readPageMessages(pageable);
        if(pageMessage.getNumberOfElements()<1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No messages");
        return ResponseEntity.ok(pageMessage.stream().toList());
    }

    @PutMapping("/message/{uuid}")
    public ResponseEntity<Object> updateMessage(@PathVariable String uuid, @RequestBody Message message){
        Message updatedMessage = messageService.updateMessage(message, uuid);
        if(updatedMessage==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No message found with the provided UUID");
        }
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/message/{uuid}")
    public ResponseEntity<Object> deleteMessage(@PathVariable String uuid){
        Message deletedMessage = messageService.deleteMessage(uuid);
        if(deletedMessage==null) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No message deleted with the provided UUID");
        
        return ResponseEntity.ok(deletedMessage);
    }


    @GetMapping("/test")
    public ResponseEntity<String> readTest(){
        return ResponseEntity.ok("Test API 0.1.5");
    }
}
