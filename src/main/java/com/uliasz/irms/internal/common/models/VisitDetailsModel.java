package com.uliasz.irms.internal.common.models;

import com.uliasz.irms.internal.common.enums.VisitStatus;
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
    private VisitStatus visitStatus;
    private BigDecimal cost;
}
