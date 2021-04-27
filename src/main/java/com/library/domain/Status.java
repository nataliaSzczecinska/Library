package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    BORROWED("borrowed"),
    AVAILABLE("available"),
    DESTROYED("destroyed"),
    LOST("lost");

    private String status;
}
