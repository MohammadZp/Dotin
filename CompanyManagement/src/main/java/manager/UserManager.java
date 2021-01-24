package manager;

import exception.ManagerNotFoundException;
import exception.UpdateException;
import hibernateConfig.HibernateUtil;
import model.CategoryElement;
import model.User;
import org.hibernate.Session;
import service.UserService;
import systemMessages.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserManager {
    private UserService userService = new UserService();
    private CategoryElementManager categoryElementManager=new CategoryElementManager();
    public List<String> addUser(HttpServletRequest request, HttpServletResponse response) {
        long nationalcode = Integer.parseInt(request.getParameter("nationalcode"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String managerId = request.getParameter("managerId");
        String role = request.getParameter("categoryElement");
        CategoryElement categoryElement=categoryElementManager.objectOfName(role);
        List<String> errors = new ArrayList<>();
        User user = new User();
        user.setNationalCode(nationalcode);
        user.setEmailAddress(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(categoryElement);
        user.setEnable(true);
        user.setVersion(0);
        user.setCreationDate(new Date().toString());
        user.setModificationDate(Message.NEVER_MODIFIED_MESSAGE);
        user.setActive(true);
        if (!managerId.equals("")) {
            try {
                checkManagerIdValidation(managerId);
                User manager = userService.getUser(Long.parseLong(managerId));
//                user.setNationalCode(nationalcode);
//                user.setEmailAddress(email);
                  user.setManager(manager);
//                user.setPassword(password);
//                user.setUsername(username);
//                user.setRole(categoryElement);
//                user.setEnable(true);
//                user.setVersion(0);
//                user.setCreationDate(new Date().toString());
//                user.setModificationDate(Message.NEVER_MODIFIED_MESSAGE);
//                user.setActive(true);
            } catch (Exception exception) {
                errors.add(exception.getMessage());
            }
           }//      else {
//            user.setNationalCode(nationalcode);
//            user.setEmailAddress(email);
//            user.setPassword(password);
//            user.setUsername(username);
//            user.setRole(categoryElement);
//            user.setActive(true);
//            user.setCreationDate(new Date().toString());
//            user.setModificationDate(new Date().toString());
//            user.setVersion(0);
//            user.setEnable(true);
   //     }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            errors.add(violation.getMessage());
        }
        if (errors.isEmpty()) userService.create(user);
        return errors;
    }
    private void checkManagerIdValidation(String managerId) throws Exception {
        Long id = Long.parseLong(managerId);
        User manager = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            manager = session.get(User.class, id);
            session.getTransaction().commit();
            session.close();
        if (manager == null) {
            throw new ManagerNotFoundException("there is no user with id=" + id + " (check manager id)");
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
    }
    public List<String> updateUser(HttpServletRequest request, HttpServletResponse response)  {
        String enableCheck=request.getParameter("enableCheck");
        String activeCheck=request.getParameter("activeCheck");
        boolean checkActivation=false;
        boolean checkEnable=false;
        if(enableCheck.equals("enable")) checkEnable=true;
        if(activeCheck.equals("active")) checkActivation=true;
        long id = Long.parseLong(request.getParameter("id"));
        long nationalcode = Integer.parseInt(request.getParameter("nationalcode"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String managerId = request.getParameter("managerId");
        String role = request.getParameter("categoryElement");
        CategoryElement categoryElement=categoryElementManager.objectOfName(role);
        List<String> errors = new ArrayList<>();
        User user = new User();
        user.setId(id);
        user.setNationalCode(nationalcode);
        user.setEmailAddress(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(categoryElement);
        user.setEnable(checkEnable);
        user.setCreationDate(userService.getUser(id).getCreationDate());
        user.setVersion(userService.getUser(id).getVersion());
        user.versionIncrement();
        user.setModificationDate(new Date().toString());
        user.setActive(checkActivation);
        if (!managerId.equals("")) {
            try {
                checkManagerIdValidation(managerId);
                User manager = userService.getUser(Long.parseLong(managerId));
                user.setManager(manager);
            } catch (Exception exception) {
                errors.add(exception.getMessage());
            }
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            errors.add(violation.getMessage());
        }
        if (errors.isEmpty()) {
            try {
                userService.update(user);
            } catch (UpdateException e) {
                errors.add(e.getMessage());
            }
        }
        return errors;
    }

    public void readUsers(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userService.read();
        request.setAttribute("userList", userList);
    }
}


