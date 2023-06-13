package com.example.cooperative.impl.facade;

import com.example.cooperative.impl.model.UserDTO;

public interface UserFacade {

    UserDTO findById(String idUser);
    String create(UserDTO dto);
    void update(UserDTO dto);

}
