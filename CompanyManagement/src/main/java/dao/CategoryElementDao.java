package dao;

import hibernateConfig.HibernateUtil;

import model.Category;
import model.CategoryElement;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryElementDao {

    public List<CategoryElement> read(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<CategoryElement> categoryElementList = session.createQuery("FROM CategoryElement").list();
        session.getTransaction().commit();
        session.close();
        return categoryElementList;
    }
    public CategoryElement ObjectOfname(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(CategoryElement.class);
        cr.add(Restrictions.eq("code", name));
        List results = cr.list();
        session.getTransaction().commit();
        session.close();
        return (CategoryElement) results.get(0);
    }
    public List<String> getUserRoles() {
        Set<String> roles=new HashSet<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select c_code from t_category_elements inner join t_categories  on c_category_id=1");
        roles = new HashSet<String>(query.list());
        session.getTransaction().commit();
        session.close();
        List<String> changeToList=new ArrayList<>();
        changeToList.addAll(roles);
        return changeToList;
    }
}
