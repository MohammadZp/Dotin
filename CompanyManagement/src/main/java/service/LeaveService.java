package service;

import dao.LeaveDao;
import dao.UserDao;
import exception.UpdateLeaveException;
import model.Leave;
import model.User;

import java.util.List;

public class LeaveService {
        private LeaveDao leaveDao = new LeaveDao();

        public void create(Leave leave) {
            leaveDao.create(leave);
        }

        public List<Leave> read() {
            return leaveDao.read();
        }

        public void update(Leave leave) throws UpdateLeaveException {
            leaveDao.update(leave);
        }

        public void delete(Long id) {
            leaveDao.delete(id);
        }

        public Leave getLeave(Long id) {
            return leaveDao.getLeave(id);
        }
}
