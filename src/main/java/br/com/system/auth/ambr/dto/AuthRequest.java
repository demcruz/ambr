package br.com.system.auth.ambr.dto;

import lombok.Data;


@Data
public class AuthRequest {
        private String username;
        private String password;
}
