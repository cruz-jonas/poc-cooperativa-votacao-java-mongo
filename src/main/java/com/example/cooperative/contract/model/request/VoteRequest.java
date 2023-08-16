package com.example.cooperative.contract.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequest {

    private String idAgenda;
    private String idSession;
    private String idUser;
    private String choose;

}
