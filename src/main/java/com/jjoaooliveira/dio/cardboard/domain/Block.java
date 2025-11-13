package com.jjoaooliveira.dio.cardboard.domain;

import java.time.OffsetDateTime;

// Value Object
public record Block(OffsetDateTime issueOn, String reason, BlockStatus status) {
}
