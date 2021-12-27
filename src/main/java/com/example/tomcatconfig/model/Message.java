package com.example.tomcatconfig.model;

import lombok.*;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(name = "message")
    private String message;

    @Getter
    @Setter
    @Column(name = "sender_id")
    private int sender;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "recipient_id")
    private User recipientId;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}


