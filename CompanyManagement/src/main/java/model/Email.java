package model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Blob;
import java.sql.Clob;
import java.util.List;

@Entity
@Table(name = "t_emails")
public class Email extends model.Entity {
    @Column(name="c_subject")
    private String subject;
    @Lob
    @Nationalized
    @Column(name="c_email_text")
    private Clob text;
    @Lob
    @Column(name="c_email_attachments")
    private List<Blob> attachments;
    @ManyToOne
    @JoinColumn(name="c_email_status")
    private CategoryElement emailStatus;

    public Email() {
    }

    public List<Blob> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Blob> attachments) {
        this.attachments = attachments;
    }

    public CategoryElement getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(CategoryElement emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Clob getText() {
        return text;
    }

    public void setText(Clob text) {
        this.text = text;
    }
}
