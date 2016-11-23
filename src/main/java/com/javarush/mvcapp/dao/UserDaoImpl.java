package com.javarush.mvcapp.dao;

import com.javarush.mvcapp.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Andriana on 14.11.2016.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;
    private static final int limitResultsPerPage = 5;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUser(int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .setFirstResult(page * limitResultsPerPage)
                .setMaxResults(limitResultsPerPage)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUser() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();

    }
    @SuppressWarnings("unchecked")
    @Override
    public List<User> searchUser(String searchText, Integer offset, Integer maxResults) {

            FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(User.class).get();
            org.apache.lucene.search.Query query = qb
                    .keyword().onFields("name")
                    .matching(searchText)
                    .createQuery();

            org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, User.class);

            List<User> results = hibQuery.setFirstResult(offset!=null?offset:0)
                    .setMaxResults(maxResults!=null?maxResults:10).list();

            return results;

    }



    @Override
    public void removeUser(String id) {
        int intId = Integer.parseInt(id);
        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, intId);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getUserById(String id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, Integer.parseInt(id));
        return user;
    }

    @Override
    public User getNewUser() {
        return new User();
    }

    @Override
    public Long count() {
        return (Long) sessionFactory.openSession()
                .createCriteria(User.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();

    }



//    @Override
//    public void save() {
//        for(int itr=1;itr <= 100 ; itr++){
//            User user = new User("User_" + itr, Math.max(25, (itr%2)*35));
//            sessionFactory.openSession()
//                    .save(user);
//        }
//    }
}
