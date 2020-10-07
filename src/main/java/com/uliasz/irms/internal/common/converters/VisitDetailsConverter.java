package com.uliasz.irms.internal.common.converters;

import com.uliasz.irms.internal.common.enums.VisitStatus;
import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import com.uliasz.irms.internal.database.entities.VisitDetailsEntity;

public final class VisitDetailsConverter {

    public static VisitDetailsModel convertToModel(VisitDetailsEntity visitDetailsEntity) {
        return VisitDetailsModel.builder()
                .id(visitDetailsEntity.getId())
                .note(visitDetailsEntity.getNote())
                .visitStatus(VisitStatus.valueOf(visitDetailsEntity.getVisitStatus()))
                .cost(visitDetailsEntity.getCost())
                .build();
    }

    public static VisitDetailsEntity convertToEntity(VisitDetailsModel visitDetailsModel) {
        return VisitDetailsEntity.builder()
                .id(visitDetailsModel.getId())
                .note(visitDetailsModel.getNote())
                .visitStatus(visitDetailsModel.getVisitStatus().name())
                .cost(visitDetailsModel.getCost())
                .build();
    }
}
