package com.library.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class Mail {
    private final String mailAddress;
    private final String subject;
    private final String message;
}
