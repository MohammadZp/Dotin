//package controller;
//
//import lombok.SneakyThrows;
//import model.Email;
//import model.Leave;
//import model.User;
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.util.http.fileupload.RequestContext;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//import org.hibernate.Hibernate;
//import service.LeaveService;
//import service.UserService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import javax.servlet.http.Part;
//import javax.sql.rowset.serial.SerialBlob;
//import java.sql.Blob;
//import java.sql.Clob;
//import java.sql.SQLException;
//import java.util.List;
//
//public class EmailControllerServlet extends HttpServlet {
//   // private EmailService emailService;
//
//    public void init() {
//       // emailService = new EmailService();
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    @SneakyThrows
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getServletPath();
//        switch (action) {
//            case "/email/add":
//                addEmail(request, response);
//                break;
////            case "/email/delete":
////                deleteEmail(request, response);
////                break;
////            case "/email/update":
////                updateEmail(request, response);
////                break;
////            case "/email/edit":
////                editFormPage(request, response);
////                break;
////            case "/leave/list":
////                listEmail(request, response);
////                break;
//        }
//    }
//
//    private void updateEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
//        String subject = request.getParameter("subject");
//        String text = request.getParameter("text");
//        Integer userSenderId=Integer.valueOf(request.getParameter("userSenderId"));
//        Part filePart = request.getPart("attachments");
//        InputStream inputStream = filePart.getInputStream();
//        byte[] targetArray = new byte[inputStream.available()];
//        Email email = new Email();
//       // email.setText(text);
//        email.setSubject(subject);
//        email.setAttachments(new SerialBlob(targetArray));
//        //emailService.update(email);
//    }
//
//    private void listEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //List<Email> emailList = emailService.read();
//       // request.setAttribute("emailList", emailList);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("EmailList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void deleteEmail(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        //emailService.delete(id);
//    }
//
//    private void addEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //String subject = request.getParameter("subject");
//       // Part text = request.getPart("text");
//        //Part attachment = request.getPart("attachments");
//        ServletFileUpload servletFileUpload=new ServletFileUpload(new DiskFileItemFactory());
//        List<FileItem> fileItems= servletFileUpload.parseRequest((RequestContext) request);
//       for(FileItem fileItem:fileItems)
//       {
//           fileItem.write(new File("C:/Users/mohammad/Desktop/QUUZ/data.txt"));
//       }
//
//
//    }
//}
