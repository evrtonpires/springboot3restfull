package com.evrtonpires.springboot3restfull.enums;

public enum PaymentStatus {
    PENDING(1, "Pendente"),
    SETTLED(2, "Quitado"),
    CANCELED(2, "Cancelado");

    private int cod;
    private String description;

    private PaymentStatus(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for( PaymentStatus type : PaymentStatus.values()){
            if(cod.equals(type.getCod())){
                return type;
            }
        }
        throw new IllegalArgumentException("Código Inválido: "+ cod);
    }

}
