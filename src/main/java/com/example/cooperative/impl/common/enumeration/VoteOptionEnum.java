package com.example.cooperative.impl.common.enumeration;

import com.example.cooperative.impl.exception.TipoVotoInvalidoException;

import java.util.Arrays;

public enum VoteOptionEnum {

    SIM("Sim"),
    NAO("Não");

    private final String description;

    VoteOptionEnum(String vote) {
        this.description = vote;
    }

    public String getDescription() {
        return this.description;
    }

    public static VoteOptionEnum findByDescription(String voteDescription) {
        return Arrays
                .stream(values())
                .filter(it -> voteDescription.contains(it.getDescription()))
                .findFirst()
                .orElseThrow(() -> new TipoVotoInvalidoException("Tipo de voto inválido. Aceito apenas 'sim' ou 'não'"));
    }
}
