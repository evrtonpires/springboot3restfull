package com.evrtonpires.springboot3restfull.domain;

import com.evrtonpires.springboot3restfull.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.util.UUID;
@Entity
@Table(name = "TB_PAYMENT_CARD")
public class PaymentCard extends Payment {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer numberOfInstallments;

    public PaymentCard() {
    }

    public PaymentCard(UUID id, PaymentStatus paymentStatus, Request request, Integer numberOfInstallments) {
        super(id, paymentStatus, request);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
