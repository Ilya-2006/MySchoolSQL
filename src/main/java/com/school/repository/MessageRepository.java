package com.school.repository;

import com.school.model.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findByUsername(String username); //выборка объектов по источнику сообщения username
    
    List<Message> findBySendname(String sendname); //выборка объектов по получателю сообщения sendname
}
