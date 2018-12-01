package fr.utbm.TeachMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ReservationPK.class)
public class Reservation {
    private int sessionId;
    private int clientId;

    @Id
    @Column(name = "SESSION_ID", nullable = false)
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Id
    @Column(name = "CLIENT_ID", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (sessionId != that.sessionId) return false;
        if (clientId != that.clientId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionId;
        result = 31 * result + clientId;
        return result;
    }
}
