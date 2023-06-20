package com.evrtonpires.springboot3restfull.domain;

import com.evrtonpires.springboot3restfull.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_CLIENT")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String cpfOurCnpj;

    private Integer type;
    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    public Client() {
    }

    public Client(UUID id, String name, String email, String cpfOurCnpj, ClientType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOurCnpj = cpfOurCnpj;
        this.type = type.getCod();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOurCnpj() {
        return cpfOurCnpj;
    }

    public void setCpfOurCnpj(String cpfOurCnpj) {
        this.cpfOurCnpj = cpfOurCnpj;
    }

    public ClientType getType() {
        return ClientType.toEnum(this.type);
    }

    public void setType(ClientType type) {
        this.type = type.getCod();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpfOurCnpj='" + cpfOurCnpj + '\'' +
                ", type=" + type +
                ", addresses=" + addresses +
                ", phones=" + phones +
                '}';
    }
}
