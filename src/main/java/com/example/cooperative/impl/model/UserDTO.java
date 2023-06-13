package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private List<String> votedAgendas;

}
