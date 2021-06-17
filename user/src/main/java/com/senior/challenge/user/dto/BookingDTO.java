package com.senior.challenge.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senior.challenge.user.persistence.DateTreatment;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO extends DateTreatment {
    private UUID userId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Sao_Paulo")
    private Date begin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Sao_Paulo")
    private Date end;

    private String status;
    private Boolean garage;

}
