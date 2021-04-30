package com.library.domain;

import lombok.Getter;

@Getter
public enum Status {
    BORROWED,
    AVAILABLE,
    DESTROYED,
    LOST
}
