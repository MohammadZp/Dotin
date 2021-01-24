package dao;

import exception.UpdateException;
import hibernateConfig.HibernateUtil;
import model.User;
import org.hibernate.Session;
import systemMessages.Message;

import java.util.List;

public class UserDao {
    public void create(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    public void update(User user) throws UpdateException {
        User old = getUser(user.getId());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setEmailAddress(user.getEmailAddress());
        old.setManager(user.getManager());
        old.setNationalCode(user.getNationalCode());
        old.setVersion(user.getVersion());
        old.setRole(user.getRole());
        old.setCreationDate(user.getCreationDate());
        old.setLeaveList(user.getLeaveList());
        old.setModificationDate(user.getModificationDate());
        old.setActive(user.isActive());
        old.setEnable(user.isEnable());
        old.setLeaveList(user.getLeaveList());
        old.setSentEmails(user.getSentEmails());
        if(old.checkEquality(old)) {
            session.update(old);
        }else{
            throw new UpdateException(Message.UPDATE_EXCEPTION_MESSAGE);
        }
        session.getTransaction().commit();
        session.close();
    }


    public void delete(Long id) {
        User user = getUser(id);
        System.out.println("///////////////////"+user.toString());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user.setEnable(false);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }
}