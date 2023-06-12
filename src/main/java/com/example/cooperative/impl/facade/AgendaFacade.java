package com.example.cooperative.impl.facade;

import com.example.cooperative.impl.model.AgendaDTO;

public interface AgendaFacade {

    String create(AgendaDTO dto);

    AgendaDTO findById(String id);

    void update(AgendaDTO dto);

}
