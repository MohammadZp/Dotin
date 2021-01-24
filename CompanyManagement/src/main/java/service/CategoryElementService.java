package service;

import dao.CategoryElementDao;
import model.CategoryElement;

import java.util.List;

public class CategoryElementService {
    CategoryElementDao categoryElementDao = new CategoryElementDao();

    public List<CategoryElement> read() {
        return categoryElementDao.read();
    }
    public CategoryElement objectOfname(String name){
        return categoryElementDao.ObjectOfname(name);
    }

    public List<String> getUserRoles() {
        return categoryElementDao.getUserRoles();
    }
}
