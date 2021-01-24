package manager;

import exception.ActiveUserException;
import exception.EnableUserException;
import exception.FutureDataException;
import exception.UpdateLeaveException;
import hibernateConfig.HibernateUtil;
import model.CategoryElement;
import model.Leave;
import systemMessages.Message;
import model.User;
import org.hibernate.Session;

import service.LeaveService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class LeaveManager {
    LeaveService leaveService = new LeaveService();
    CategoryElementManager categoryElementManager = new CategoryElementManager();

    private void checkUserIdValidation(String userId) throws Exception {
        long id = Long.parseLong(userId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User manager = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        if (manager == null) {
            throw new Exception("there is no user with id=" + id + " (check manager id)");
        }
    }

    public List<String> addLeaveRequest(HttpServletRequest request, HttpServletResponse response) {
       // String id = request.getParameter("userId");
        String date = request.getParameter("date");
        String fromTime = request.getParameter("fromTime");
        String toTime = request.getParameter("toTime");
        String checkBox = request.getParameter("checkbox");
        if (checkBox != null) {
            if (checkBox.equals("on")) {
                fromTime = Message.START_WORK_TIME;
                toTime = Message.FINISH_WORK_TIME;
            }
        }
        List<String> errors = new ArrayList<>();
        Leave leave = new Leave();
        UserService userService = new UserService();
        CategoryElement status = categoryElementManager.objectOfName(Message.NOT_SEEN);
        try {
//            checkUserIdValidation(id);
//            checkUserActivation(id);
//            checkUserEnable(id);
            checkDateValidation(date);
 //           User leaveRequester = userService.getUser(Long.parseLong(id));
            leave.setToTime(toTime);
            leave.setDate(date);
            leave.setFromTime(fromTime);
            //leave.setUser(leaveRequester);
            leave.setLeaveStatus(status);
            leave.setActive(true);
            leave.setEnable(true);
            leave.setVersion(0);
            leave.setCreationDate(new Date().toString());
            leave.setModificationDate(Message.NEVER_MODIFIED_MESSAGE);
        } catch (Exception exception) {
            errors.add(exception.getMessage());
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Leave>> violations = validator.validate(leave);
        for (ConstraintViolation<Leave> violation : violations) {
            errors.add(violation.getMessage());
        }
        if (errors.isEmpty()) leaveService.create(leave);
        return errors;
    }

    private void checkUserEnable(String id) throws EnableUserException {
        UserService userService = new UserService();
        User user=userService.getUser(Long.parseLong(id));
        if(!user.isEnable()) throw new EnableUserException(Message.disable(id));
    }
    private void checkUserActivation(String id) throws ActiveUserException {
        UserService userService = new UserService();
        User user=userService.getUser(Long.parseLong(id));
        if(!user.isActive()) throw new ActiveUserException(Message.deActive(id));
    }

    private void checkDateValidation(String date) throws ParseException, FutureDataException {
        Date currentTime = java.sql.Date.valueOf(LocalDate.now());
        Date leaveDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        if (!leaveDate.after(currentTime)) throw new FutureDataException(Message.LEAVE_DATE_EXCEPTION);
    }

    public void read(HttpServletRequest request, HttpServletResponse response) {
        List<Leave> leaveList = leaveService.read();
        request.setAttribute("leaveList", leaveList);
    }

    public void deleteLeave(HttpServletRequest request, HttpServletResponse response) {
        long id = Integer.parseInt(request.getParameter("id"));
        leaveService.delete(id);
    }

    public Leave getLeave(Long id) {
        return leaveService.getLeave(id);
    }

    public List<String> updateLeaveRequest(HttpServletRequest request, HttpServletResponse response)  {
        String enableCheck=request.getParameter("enableCheck");
        String activeCheck=request.getParameter("activeCheck");
        boolean checkActivation=false;
        boolean checkEnable=false;
        if(enableCheck.equals("enable")) checkEnable=true;
        if(activeCheck.equals("active")) checkActivation=true;
        System.out.println("checkEnable="+checkEnable);
        System.out.println("checkActivation="+checkActivation);
        //String id = request.getParameter("userId");
        String leaveId=request.getParameter("leaveId");
        String date = request.getParameter("date");
        String fromTime = request.getParameter("fromTime");
        String toTime = request.getParameter("toTime");
        String checkBox = request.getParameter("checkbox");
        if (checkBox != null) {
            if (checkBox.equals("on")) {
                fromTime = Message.START_WORK_TIME;
                toTime = Message.FINISH_WORK_TIME;
            }
        }
        List<String> errors = new ArrayList<>();
        Leave leave = new Leave();
        UserService userService = new UserService();
        CategoryElement status = categoryElementManager.objectOfName(Message.NOT_SEEN);
        try {
          //  checkUserIdValidation(id);
            checkDateValidation(date);
            leave.setId(Long.parseLong(leaveId));
           // User leaveRequester = userService.getUser(Long.parseLong(id));
            leave.setToTime(toTime);
            leave.setDate(date);
            leave.setCreationDate(leaveService.getLeave(Long.parseLong(leaveId)).getCreationDate());
            leave.setModificationDate(new Date().toString());
            leave.setVersion(leaveService.getLeave(Long.parseLong(leaveId)).getVersion());
            leave.versionIncrement();
            leave.setFromTime(fromTime);
            leave.setEnable(checkEnable);
            leave.setActive(checkActivation);
            //leave.setUser(leaveRequester);
            leave.setLeaveStatus(status);
        } catch (Exception exception) {
            errors.add(exception.getMessage());
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Leave>> violations = validator.validate(leave);
        for (ConstraintViolation<Leave> violation : violations) {
            errors.add(violation.getMessage());
        }
        if (errors.isEmpty()) {
            try {
                leaveService.update(leave);
            } catch (UpdateLeaveException e) {
               errors.add(e.getMessage());
            }
        }
        return errors;
    }
}
