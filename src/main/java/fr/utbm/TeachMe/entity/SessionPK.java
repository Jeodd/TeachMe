package fr.utbm.TeachMe.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SessionPK implements Serializable {
    private int id;
    private int locationId;
    private String courseCode;

    @Column(name = "ID", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "LOCATION_ID", nullable = false)
    @Id
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Column(name = "COURSE_CODE", nullable = false, length = 4)
    @Id
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionPK sessionPK = (SessionPK) o;

        if (id != sessionPK.id) return false;
        if (locationId != sessionPK.locationId) return false;
        if (courseCode != null ? !courseCode.equals(sessionPK.courseCode) : sessionPK.courseCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + locationId;
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SessionPK{" +
                "id=" + id +
                ", locationId=" + locationId +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
