package com.library.domain.dto;

import com.library.domain.enums.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReaderDto {
    private Long id;
    private String name;
    private String login;
    private String mailAddress;
    private char[] password;
    private LocalDate createAccountDate;
    private boolean blocked;
    private Role role;

    public void clearSensitive() {
        Arrays.fill(password, '\0');
    }
}
