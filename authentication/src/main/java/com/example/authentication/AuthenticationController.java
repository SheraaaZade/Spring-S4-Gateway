package com.example.authentication;

import com.example.authentication.model.UnsafeCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @GetMapping("/authentication")
  public ResponseEntity<String> connect(@RequestBody UnsafeCredentials unsafeCredentials) {
    if(unsafeCredentials.invalid())
      return ResponseEntity.badRequest().build();

    String token = authenticationService.connect(unsafeCredentials);
    if (token == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(token);
  }

  @PostMapping("/authentication")
  public ResponseEntity<Void> createUser(@RequestBody UnsafeCredentials unsafeCredentials) {
    if(unsafeCredentials.invalid())
      return ResponseEntity.badRequest().build();

    boolean created = authenticationService.createOne(unsafeCredentials);
    if (!created) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok().build();
  }
}
