package com.digishopkart.customer_service.entity;

import com.digishopkart.customer_service.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String email;
    private String username;
    private String password;
    private String profilePicture;
    private String googleId;

    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private boolean email_verified;

    @Enumerated(value = EnumType.STRING)
    private List<Roles> roles;
}
