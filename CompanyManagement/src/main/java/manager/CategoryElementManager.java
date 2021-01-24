package manager;

import model.CategoryElement;
import service.CategoryElementService;
import service.UserService;

import java.util.List;

public class CategoryElementManager {
    CategoryElementService categoryElementService=new CategoryElementService();
    public List<CategoryElement> read(){
        return categoryElementService.read();
    }
    public CategoryElement objectOfName(String name){
        return categoryElementService.objectOfname(name);
    }

    public List<String> getUserRoles() {
        return categoryElementService.getUserRoles();
    }
}
