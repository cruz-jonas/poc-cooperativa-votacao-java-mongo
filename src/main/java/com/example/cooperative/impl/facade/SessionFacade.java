package com.example.cooperative.impl.facade;

import com.example.cooperative.impl.model.SessionDTO;

public interface SessionFacade {

    String openSession(SessionDTO dto);
    void registerVoto();
    String generateResult();

}
