package com.library.domain;

import com.library.domain.enums.CopyStatus;
import lombok.*;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COPIES")
public class Copy {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "COPY_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "COPY_STATUS")
    private CopyStatus copyStatus;

    @OneToMany(targetEntity = Borrow.class,
            mappedBy = "copy",
            fetch = FetchType.LAZY)
    private List<Borrow> borrows;
}
