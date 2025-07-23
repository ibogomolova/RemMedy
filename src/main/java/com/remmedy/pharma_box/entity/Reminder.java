package com.remmedy.pharma_box.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reminders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reminder {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime notifyAt;

    private boolean sent;

    @ManyToOne
    private Medicine medicine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return sent == reminder.sent
                && Objects.equals(id, reminder.id)
                && Objects.equals(notifyAt, reminder.notifyAt)
                && Objects.equals(medicine, reminder.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notifyAt, sent, medicine);
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", notifyAt=" + notifyAt +
                ", sent=" + sent +
                ", medicine=" + medicine +
                '}';
    }
}
