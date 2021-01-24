package dao;

import hibernateConfig.HibernateUtil;
import model.Email;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmailDao {
    public void create(Email email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(email);
        session.getTransaction().commit();
        session.close();
    }

    public List<Email> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Email> emailList = session.createQuery("FROM Email").list();
            session.getTransaction().commit();
            session.close();
            return emailList;
    }

//    public void update(Email email) {
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Email old = getEmail(email.getId());
//            System.out.println("old=" + old.toString());
//            old.setSubject(email.getSubject());
//            old.setAttachments(email.getAttachments());
//            old.setText(email.getText());
//            old.setUserEmailRecievedList(email.getUserEmailRecievedList());
//            old.setUsersSentEmails(email.getUsersSentEmails());
//            session.update(old);
//            session.getTransaction().commit();
//            session.close();
//        }
//
//    }
//
//    public void delete(Integer id) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            Email email = (Email) session.get(Email.class, id);
//            session.delete(email);
//            session.getTransaction().commit();
//            session.close();
//        }
//
//    }
//
//    public Email getEmail(Integer id) {
//        Transaction transaction = null;
//        Email email = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            email = session.get(Email.class, id);
//            transaction.commit();
//        }
//        return email;
//    }

}
