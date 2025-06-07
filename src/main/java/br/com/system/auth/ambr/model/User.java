package br.com.system.auth.ambr.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;
}
