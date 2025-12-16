package ru.rksp.klosep.schedule_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDto {
    private Long id;
    private String eventType;
    private OffsetDateTime eventTime;
}