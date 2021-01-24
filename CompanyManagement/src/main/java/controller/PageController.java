package controller;

import manager.CategoryElementManager;
import model.CategoryElement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PageController extends HttpServlet {
    CategoryElementManager categoryElementManager=new CategoryElementManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/register":
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/register.jsp");
                List<CategoryElement> categoryElementList=categoryElementManager.read();
                List<String> roles=categoryElementManager.getUserRoles();
                request.setAttribute("roles",roles);
                requestDispatcher.forward(request,response);
                break;
            case "/leaveRequest":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/leaveRequest.jsp");
                dispatcher.forward(request, response);
                break;
            case "/composeEmail":
                RequestDispatcher rd = request.getRequestDispatcher("/composeEmail.jsp");
                rd.forward(request, response);
                break;

        }
    }
}



