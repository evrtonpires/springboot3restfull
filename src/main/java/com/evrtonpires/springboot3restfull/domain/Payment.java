package com.evrtonpires.springboot3restfull.domain;

import com.evrtonpires.springboot3restfull.enums.PaymentStatus;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_PAYMENT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment extends RepresentationModel<Payment> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private UUID id;
    private Integer paymentStatus;

    @OneToOne
    @JoinColumn(name = "request_id")
    @MapsId
    private Request request;

    public Payment() {
    }

    public Payment(UUID id, PaymentStatus paymentStatus, Request request) {
        this.id = id;
        this.paymentStatus = paymentStatus.getCod();
        this.request = request;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.toEnum(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCod();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
