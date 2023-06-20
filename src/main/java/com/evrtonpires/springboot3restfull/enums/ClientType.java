package com.evrtonpires.springboot3restfull.enums;

import java.util.Arrays;

public enum ClientType {
    PHYSICALPERSON(1, "Pessoa Física"), LEGALPERSON(2, "Pessoa Jurídica");

    private int cod;
    private String description;

    private ClientType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for( ClientType type : ClientType.values()){
            if(cod.equals(type.getCod())){
                return type;
            }
        }
        throw new IllegalArgumentException("Código Inválido: "+ cod);
    }

}
