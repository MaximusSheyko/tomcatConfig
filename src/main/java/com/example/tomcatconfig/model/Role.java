package com.example.tomcatconfig.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"users"})
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(name = "role")
    private String role;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
