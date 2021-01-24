package model;

import com.sun.istack.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_users")
public class User extends model.Entity {
    @Range(min = 10000, max = 9999999999L, message = "National code should be between 4 to 10 digits")
    @NotNull(message = "National code can not be empty!")
    @Column(name = "c_national_code")
    private Long nationalCode;
    @Length(max = 20, message = "username should be less than or equal 20 character")
    @NotNull(message = "username can not be empty!")
    @Column(name = "c_username")
    private String username;
    @Length(min = 3, message = "short password is not secure! password should be at least 3 characters")
    @NotNull(message = "please choose a password!")
    @Column(name = "c_password")
    private String password;
    @javax.validation.constraints.Email(message = "invalid email! example:abc@gmail.com")
    @NotNull(message = "email address can not be empty!")
    @Column(name="c_email_address")
    private String emailAddress;
    @Nullable
    @ManyToOne
    @JoinColumn(name="c_manager_id")
    private User manager;
    @ManyToOne
    @JoinColumn(name="c_role")
    private CategoryElement role;
    @OneToMany
    @JoinColumn(name = "c_user_sender_email_id")
    private List<Email> sentEmails;
    @ManyToMany(targetEntity = Email.class)
    @JoinTable(
            name = "t_user_recieved_emails",
            joinColumns = {@JoinColumn(name = "c_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "c_email_id")}
    )
    private List<Email> recievedEmails;

    @OneToMany
    @JoinColumn(name="c_user_leave_requster_id")
    private List<Leave> leaveList;

    public User() {
    }

    public List<Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(List<Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public List<Email> getRecievedEmails() {
        return recievedEmails;
    }

    public void setRecievedEmails(List<Email> recievedEmails) {
        this.recievedEmails = recievedEmails;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public CategoryElement getRole() {
        return role;
    }

    public void setRole(CategoryElement role) {
        this.role = role;
    }

    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
