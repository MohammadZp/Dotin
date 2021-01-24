import dao.CategoryElementDao;
import hibernateConfig.HibernateUtil;
import model.Category;
import model.CategoryElement;
import org.hibernate.Session;

import dao.UserDao;
import hibernateConfig.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class test {
        public static void main(String[] args) {
        Category category1=new Category("Email");
        Category category2=new Category("user");
        Category category3=new Category("leave");
        CategoryElementDao categoryElementDao=new CategoryElementDao();
        CategoryElement categoryElement1=new CategoryElement("tester","tester",category2);
        CategoryElement categoryElement2=new CategoryElement("Programmer","Programmer",category2);
        CategoryElement categoryElement3=new CategoryElement("Data miner","Data miner",category2);
        CategoryElement categoryElement4=new CategoryElement("Not Seen","Not Seen",category3);
        CategoryElement categoryElement5=new CategoryElement("Accepted","Accepted",category3);
        CategoryElement categoryElement6=new CategoryElement("Rejected","Rejected",category3);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(category2);
        session.save(category3);
        session.save(categoryElement4);
        session.save(categoryElement5);
        session.save(categoryElement6);
        session.save(categoryElement1);
        session.save(categoryElement2);
        session.save(categoryElement3);
        session.getTransaction().commit();
        session.close();
    }
//    public static void main(String[] args) {
//          //  CategoryElementDao categoryElementDao=new CategoryElementDao();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//           /// System.out.println(categoryElementDao.getUserRoles());
//        session.getTransaction().commit();
//        session.close();
//    }
}
