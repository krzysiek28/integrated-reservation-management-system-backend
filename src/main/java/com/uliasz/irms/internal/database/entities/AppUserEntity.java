package com.uliasz.irms.internal.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class AppUserEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @OneToOne
    private PersonalDataEntity personalData;
    @JsonIgnoreProperties
    private String role;
    private Boolean enabled;

}
