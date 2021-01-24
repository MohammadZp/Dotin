//package controller;
//
//import model.User;
//import service.UserService;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
////@WebServlet("/user/add")
//public class UserLoginServlet implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        PrintWriter printWriter = servletResponse.getWriter();
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (checkLogin(username, password)) {
//            filterChain.doFilter(request, response);
//        } else {
//            printWriter.print("Inavalid username or password!");
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    public boolean checkLogin(String username, String password) {
//        UserService userService = new UserService();
//        List<User> userList = userService.read();
//        for (User user : userList) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) return true;
//        }
//        return false;
//    }
//}
