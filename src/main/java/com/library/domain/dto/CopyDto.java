package com.library.domain.dto;

import com.library.domain.enums.CopyStatus;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CopyDto {
    private Long id;
    private Long titleId;
    private CopyStatus copyStatus;
}
