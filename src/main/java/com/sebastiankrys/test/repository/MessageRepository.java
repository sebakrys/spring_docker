package com.sebastiankrys.test.repository;


import com.sebastiankrys.test.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long>{
}
