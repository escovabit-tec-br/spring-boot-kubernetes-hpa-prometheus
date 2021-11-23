package br.tec.escovabit.apphpa.controller.model.response;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class ThreadWaitModel {
    OffsetDateTime startDate;
    OffsetDateTime endDate;
}
