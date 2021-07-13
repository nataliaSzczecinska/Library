package com.library.domain.dto;

import com.library.domain.enums.DeleteCopyReason;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeleteCopyDto {
    private Long id;
    private Long previousId;
    private Long titleId;
    private DeleteCopyReason deleteCopyReason;
    private LocalDate deleteDate;
}
