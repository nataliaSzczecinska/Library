package com.library.domain;

import com.library.domain.enums.Role;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "READER_ID")
    private Long id;

    @NotNull
    @Column(name = "READER_NAME")
    private String name;

    @NotNull
    @UniqueElements
    @Column(name = "READER_LOGIN")
    private String login;

    @NotNull
    @UniqueElements
    @Column(name = "READER_E-MAIL_ADDRESS")
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
    @Column(name = "READER_ROLE")
    private Role role;

    @OneToMany(targetEntity = Borrow.class,
            mappedBy = "reader",
            fetch = FetchType.LAZY)
    private List<Borrow> borrows;

    public void clearSensitive() {
        Arrays.fill(password, '\0');
    }
}
