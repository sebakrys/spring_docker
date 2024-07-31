package com.sebastiankrys.test.web;


import com.sebastiankrys.test.model.Message;
import com.sebastiankrys.test.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;




    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> readMessage(@PathVariable Long id){
        return ResponseEntity.ok(messageService.readMessage(id));
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message){
        Message updatedMessage = messageService.updateMessage(message, id);
        if(updatedMessage==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id){
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }
}
