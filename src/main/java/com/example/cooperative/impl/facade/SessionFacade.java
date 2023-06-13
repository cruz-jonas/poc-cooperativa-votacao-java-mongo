package com.example.cooperative.impl.facade;

import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.VoteDTO;

public interface SessionFacade {

    String create(SessionDTO dto);
    void registerVoto(VoteDTO dto);
    SessionDTO findById(String id);

}
