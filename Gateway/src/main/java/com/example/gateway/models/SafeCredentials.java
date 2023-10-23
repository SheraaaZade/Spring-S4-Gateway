package com.example.gateway.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SafeCredentials {
  private String pseudo;
  private String hashedPassword;
}