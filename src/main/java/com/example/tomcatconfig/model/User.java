package com.example.tomcatconfig.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"roles"})
@Table(name = "users")
public class User {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "login")
    public String login;

    @Setter
    @Getter
    @Column(name = "password")
    private String password;

    @Setter
    @Getter
    @Column(name = "email")
    private String email;

    @Setter
    @Getter
    @Column(name = "data_registration")
    private LocalDateTime dateRegistration;

    @Setter
    @Getter
    @Column(name = "data_last")
    private LocalDateTime dateLastActivity;

    @Setter
    @Getter
    @Transient
    private String confirmPassword;


    @Setter
    @Getter
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Setter
    @Getter
    @ManyToMany()
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Setter
    @Getter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipientId")
    private List<Message> messages;
}
