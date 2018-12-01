package fr.utbm.TeachMe.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(SessionPK.class)
public class Session {
    private int id;
    private Date startDate;
    private Date endDate;
    private Integer max;
    private int locationId;
    private String courseCode;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "START_DATE", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "MAX", nullable = true)
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Id
    @Column(name = "LOCATION_ID", nullable = false)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Id
    @Column(name = "COURSE_CODE", nullable = false, length = 4)
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

        Session session = (Session) o;

        if (id != session.id) return false;
        if (locationId != session.locationId) return false;
        if (startDate != null ? !startDate.equals(session.startDate) : session.startDate != null) return false;
        if (endDate != null ? !endDate.equals(session.endDate) : session.endDate != null) return false;
        if (max != null ? !max.equals(session.max) : session.max != null) return false;
        if (courseCode != null ? !courseCode.equals(session.courseCode) : session.courseCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + locationId;
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        return result;
    }
}
