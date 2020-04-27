package com.it.contrader.service.mapper;

import com.it.contrader.domain.*;
import com.it.contrader.service.dto.ConvsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Convs and its DTO ConvsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConvsMapper extends EntityMapper<ConvsDTO, Convs> {



    default Convs fromId(Long id) {
        if (id == null) {
            return null;
        }
        Convs convs = new Convs();
        convs.setId(id);
        return convs;
    }
}
