package controller;

import lombok.SneakyThrows;
import manager.CategoryElementManager;
import manager.UserManager;
import model.CategoryElement;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCotrollerServlet extends HttpServlet {
    private UserService userService;
    private UserManager userManager;
    private CategoryElementManager categoryElementManager;
    public void init() {
        userService = new UserService();
        userManager = new UserManager();
        categoryElementManager=new CategoryElementManager();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case "/user/add":
                addUser(request, response);
                break;
            case "/user/delete":
                deleteUser(request, response);
                break;
            case "/user/update":
                updateUser(request, response);
                break;
            case "/user/edit":
                editFormPage(request, response);
                break;
            case "/user/list":
                listUser(request, response);
                break;
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = null;
        try {
            errors = userManager.addUser(request, response);
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

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userManager.delete(request, response);
        response.sendRedirect("list");
    }

    private void editFormPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        System.out.println("id==="+id);
        User user = userService.getUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        request.setAttribute("user", user);
        List<String> roles=categoryElementManager.getUserRoles();
        request.setAttribute("roles",roles);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors =new ArrayList<>();
        try {
            errors = userManager.updateUser(request, response);
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

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userManager.readUsers(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }
}
