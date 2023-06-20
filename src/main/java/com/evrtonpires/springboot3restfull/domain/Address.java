package com.evrtonpires.springboot3restfull.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_ADDRESS")
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String publicPlace;
    private String number;
    private String complement;
    private String district;
    private String cep;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Address() {
    }

    public Address(UUID id, String publicPlace, String number, String complement, String district, String cep, Client client,City city) {
        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.cep = cep;
        this.client = client;
        this.city = city;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", publicPlace='" + publicPlace + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", district='" + district + '\'' +
                ", cep='" + cep + '\'' +
                ", city=" + city +
                ", client=" + client +
                '}';
    }
}
