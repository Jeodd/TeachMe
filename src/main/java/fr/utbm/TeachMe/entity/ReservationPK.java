package fr.utbm.TeachMe.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ReservationPK implements Serializable {
    private int sessionId;
    private int clientId;

    @Column(name = "SESSION_ID", nullable = false)
    @Id
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "CLIENT_ID", nullable = false)
    @Id
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

        ReservationPK that = (ReservationPK) o;

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

    @Override
    public String toString() {
        return "ReservationPK{" +
                "sessionId=" + sessionId +
                ", clientId=" + clientId +
                '}';
    }
}
