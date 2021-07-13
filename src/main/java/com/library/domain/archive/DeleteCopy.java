package com.library.domain.archive;

import com.library.domain.Title;
import com.library.domain.enums.DeleteCopyReason;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DELETE_COPIES")
public class DeleteCopy {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "DELETE_COPY_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "PREVIOUS_COPY_ID", unique = true)
    private Long previousId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "DELETE_REASON")
    private DeleteCopyReason deleteCopyReason;

    @NotNull
    @Column(name = "DELETE_DATE")
    private LocalDate deleteDate;
}
