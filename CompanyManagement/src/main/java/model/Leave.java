package model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "t_leaves")
public class Leave extends model.Entity{
    @Column(name="c_date")
    private String date;
    @Column(name="c_from")
    private String fromTime;
    @Column(name="c_to")
    private String toTime;
    @ManyToOne
    @JoinColumn(name="c_leave_status")
    private CategoryElement leaveStatus;
    public Leave() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CategoryElement getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(CategoryElement leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }
}
