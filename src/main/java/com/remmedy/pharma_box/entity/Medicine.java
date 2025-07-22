package com.remmedy.pharma_box.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "medicines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private LocalDate addedAt;

    private LocalDate expirationDate;

    private String barcode;

    private String frontImageUrl;

    @ManyToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id)
                && Objects.equals(name, medicine.name)
                && Objects.equals(addedAt, medicine.addedAt)
                && Objects.equals(expirationDate, medicine.expirationDate)
                && Objects.equals(barcode, medicine.barcode)
                && Objects.equals(frontImageUrl, medicine.frontImageUrl)
                && Objects.equals(user, medicine.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addedAt, expirationDate, barcode, frontImageUrl, user);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addedAt=" + addedAt +
                ", expirationDate=" + expirationDate +
                ", barcode='" + barcode + '\'' +
                ", frontImageUrl='" + frontImageUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
