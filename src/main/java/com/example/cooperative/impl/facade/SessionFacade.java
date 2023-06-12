package com.example.cooperative.impl.facade;

import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.VoteDTO;

public interface SessionFacade {

    String openSession(SessionDTO dto);
    void registerVoto(VoteDTO dto);
    String generateResult();
    SessionDTO findById(String id);

}
