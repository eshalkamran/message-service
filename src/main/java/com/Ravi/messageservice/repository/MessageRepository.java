package com.Ravi.messageservice.repository;

import com.Ravi.messageservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
