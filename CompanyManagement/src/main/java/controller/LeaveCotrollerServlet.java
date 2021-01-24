package controller;

import dao.LeaveDao;
import dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import lombok.SneakyThrows;
import manager.CategoryElementManager;
import manager.LeaveManager;
import manager.UserManager;
import model.CategoryElement;
import model.Leave;
import model.User;
import service.LeaveService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LeaveCotrollerServlet extends HttpServlet {
    private LeaveService leaveService;
    private LeaveManager leaveManager;
    private UserManager userManager;

    public void init() {
        leaveService = new LeaveService();
        leaveManager = new LeaveManager();
        userManager = new UserManager();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/leave/add":
                addLeaveRequest(request, response);
                break;
            case "/leave/delete":
                deleteLeave(request, response);
                break;
            case "/leave/update":
                updateLeave(request, response);
                break;
            case "/leave/edit":
                editFormPage(request, response);
                break;
            case "/leave/list":
                listLeave(request, response);
                break;
        }
    }

    private void addLeaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = null;
        try {
            errors = leaveManager.addLeaveRequest(request, response);
            if (errors.isEmpty()) {
                response.sendRedirect("list");
            } else {
                request.setAttribute("errorMessages", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errors.jsp");
                dispatcher.forward(request, response);

            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteLeave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        leaveManager.deleteLeave(request, response);
        response.sendRedirect("list");
    }

    private void editFormPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Leave leave = leaveService.getLeave(id);
        request.setAttribute("leave", leave);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editLeave.jsp");
        dispatcher.forward(request, response);
    }

    private void updateLeave(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> errors = null;
        try {
            errors = leaveManager.updateLeaveRequest(request, response);
            if (errors.isEmpty()) {
                response.sendRedirect("list");
            } else {
                request.setAttribute("errorMessages", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errors.jsp");
                dispatcher.forward(request, response);

            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void listLeave(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        leaveManager.read(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LeaveList.jsp");
        dispatcher.forward(request, response);
    }
}
