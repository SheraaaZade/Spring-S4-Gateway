package com.example.gateway.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
  private int id;
  private String pseudo;
  private int productId;
}
