package com.evrtonpires.springboot3restfull.domain;

import com.evrtonpires.springboot3restfull.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "TB_PAYMENT_WITH_TICKET")
public class PaymentWithTicket extends Payment{

    @Serial
    private static final long serialVersionUID = 1L;
    private Date dueDate;
    private Date paymentDate;

    public PaymentWithTicket() {
    }

    public PaymentWithTicket(UUID id, PaymentStatus paymentStatus, Request request, Date dueDate, Date paymentDate) {
        super(id, paymentStatus, request);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}
