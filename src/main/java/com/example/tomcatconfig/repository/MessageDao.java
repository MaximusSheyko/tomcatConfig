package com.example.tomcatconfig.repository;

import com.example.tomcatconfig.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Integer> {

}
