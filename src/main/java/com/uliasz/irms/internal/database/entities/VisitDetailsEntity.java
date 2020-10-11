package com.uliasz.irms.internal.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "visitDetails")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String note;
    private String visitStatus;
    private BigDecimal cost;
}
