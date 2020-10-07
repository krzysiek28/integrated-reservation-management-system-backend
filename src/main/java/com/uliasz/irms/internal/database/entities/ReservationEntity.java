package com.uliasz.irms.internal.database.entities;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "reservations")
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
    private String status;
    /****/
    @OneToOne
    private AppUserEntity user;
    @OneToOne
    private VisitDetailsEntity visitDetails;
    @OneToOne
    private PersonalDataEntity personalData;

}
