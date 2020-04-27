package com.it.contrader.service.mapper;

import com.it.contrader.domain.*;
import com.it.contrader.service.dto.ChangesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Changes and its DTO ChangesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChangesMapper extends EntityMapper<ChangesDTO, Changes> {



    default Changes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Changes changes = new Changes();
        changes.setId(id);
        return changes;
    }
}
