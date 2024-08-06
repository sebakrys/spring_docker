package com.sebastiankrys.test.repository;


import com.sebastiankrys.test.model.Message;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long>{
    Optional<Message> findByUuid(String uuid);
    boolean existsByUuid(String uuid);
    Message getReferenceByUuid(String uuid);

    List<Message> findAllByOrderByIdAsc();
}
