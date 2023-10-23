package com.example.authentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "credentials")
public class SafeCredentials {
  @Id
  @Column(nullable = false)
  private String pseudo;

  @Column(name = "password", nullable = false)
  @JsonIgnore
  private String hashedPassword;
}
