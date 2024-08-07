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
        return messageService.emptyOrNotExistingMessageResponse(readedMessage);
    }

    @GetMapping("/message/page/{pageNr}/{pageSize}")
    public ResponseEntity<Object> readPageMessages(@PathVariable int pageNr, @PathVariable int pageSize){
        Pageable pageable = PageRequest.of(pageNr, pageSize, Sort.by("id"));
        Page<Message> pageMessage = messageService.readPageMessages(pageable);
        return messageService.emptyOrNotExistingMessageResponse(pageMessage);
    }

    @PutMapping("/message/{uuid}")
    public ResponseEntity<Object> updateMessage(@PathVariable String uuid, @RequestBody Message message){
        Message updatedMessage = messageService.updateMessage(message, uuid);
        return messageService.emptyOrNotExistingMessageResponse(updatedMessage);
    }

    @DeleteMapping("/message/{uuid}")
    public ResponseEntity<Object> deleteMessage(@PathVariable String uuid){
        Message deletedMessage = messageService.deleteMessage(uuid);
        return messageService.emptyOrNotExistingMessageResponse(deletedMessage);
    }


    @GetMapping("/test")
    public ResponseEntity<String> readTest(){
        return ResponseEntity.ok("Test API 0.1.7");
    }
}
