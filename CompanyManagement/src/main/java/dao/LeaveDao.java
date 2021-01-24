package dao;

import exception.UpdateLeaveException;
import hibernateConfig.HibernateUtil;
import model.Leave;
import org.hibernate.Session;
import systemMessages.Message;

import java.util.List;

public class LeaveDao {
    public void create(Leave leave) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(leave);
        session.getTransaction().commit();
        session.close();
    }

    public List<Leave> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Leave> leaveList = session.createQuery("FROM Leave").list();
        session.getTransaction().commit();
        session.close();
        return leaveList;
    }

    public void update(Leave leave) throws UpdateLeaveException {
        Leave old = getLeave(leave.getId());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       // old.setUser(leave.getUser());
        old.setLeaveStatus(leave.getLeaveStatus());
        old.setFromTime(leave.getFromTime());
        old.setToTime(leave.getToTime());
        old.setActive(leave.isActive());
        old.setEnable(leave.isEnable());
        old.setModificationDate(leave.getModificationDate());
        old.setCreationDate(leave.getCreationDate());
        if(!old.checkEquality(old)){
            throw new UpdateLeaveException(Message.UPDATE_EXCEPTION_MESSAGE);
        }else {
            session.update(old);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Long id) {
        Leave leave=getLeave(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        leave.setEnable(false);
        session.update(leave);
        session.getTransaction().commit();
        session.close();
    }

    public Leave getLeave(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Leave leave = session.get(Leave.class, id);
        session.getTransaction().commit();
        session.close();
        return leave;
    }
}
