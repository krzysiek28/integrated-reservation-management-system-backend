package com.uliasz.irms.internal.database.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column
    @Temporal(TemporalType.TIME)
    private Date timeFrom;
    @Column
    @Temporal(TemporalType.TIME)
    private Date timeTo;
    @Column(nullable = false)
    private String status;
    private String comment;
    @OneToOne
    private AppUserEntity user;
    @OneToOne
    private VisitDetailsEntity visitDetails;
    @OneToOne
    private PersonalDataEntity personalData;
}
