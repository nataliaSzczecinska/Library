package com.library.domain;

import com.library.domain.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "READERS")
public class Reader {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "READER_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "READER_NAME")
    private String name;

    @NotNull
    @Column(name = "READER_LOGIN", unique = true)
    private String login;

    @NotNull
    @Email
    @Column(name = "READER_EMAIL_ADDRESS", unique = true)
    private String mailAddress;

    @NotNull
    @Column(name = "READER_PASSWORD")
    private char[] password;

    @NotNull
    @Column(name = "CREATE_ACCOUNT_DATE")
    private LocalDate createAccountDate;

    @NotNull
    @Column(name = "IS_BLOCKED")
    private boolean blocked;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "READER_ROLE")
    private Role role;

    @OneToMany(targetEntity = Borrow.class,
            mappedBy = "reader",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Borrow> borrows;

    public void clearSensitive() {
        Arrays.fill(password, '\0');
    }
}
