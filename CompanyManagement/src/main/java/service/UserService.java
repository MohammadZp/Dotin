package service;

import dao.UserDao;
import exception.UpdateException;
import model.User;
import password.MD5Password;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public void create(User user) {
        user.setPassword(MD5Password.getMd5(user.getPassword()));
        userDao.create(user);
    }

    public List<User> read() {
        return userDao.read();
    }

    public void update(User user) throws UpdateException {
        user.setPassword(MD5Password.getMd5(user.getPassword()));
        userDao.update(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }
}
