package com.uliasz.irms.internal.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDetailsModel {
    private Long id;
    private String note;
    private BigDecimal cost;
}
