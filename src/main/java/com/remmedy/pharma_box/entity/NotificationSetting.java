package com.remmedy.pharma_box.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notification_settings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationSetting {

    @Id
    @GeneratedValue
    private UUID id;

    private boolean notifyByEmail;

    private boolean notifyByPush;

    private int daysBefore;

    @OneToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationSetting that = (NotificationSetting) o;
        return notifyByEmail == that.notifyByEmail
                && notifyByPush == that.notifyByPush
                && daysBefore == that.daysBefore
                && Objects.equals(id, that.id)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notifyByEmail, notifyByPush, daysBefore, user);
    }

    @Override
    public String toString() {
        return "NotificationSetting{" +
                "id=" + id +
                ", notifyByEmail=" + notifyByEmail +
                ", notifyByPush=" + notifyByPush +
                ", daysBefore=" + daysBefore +
                ", user=" + user +
                '}';
    }
}
