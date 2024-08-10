package com.jpa.test.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jpa.test.entities.user;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (Session session = sessionFactory.openSession()) {
            // Start a transaction (optional depending on your transaction management setup)
            Transaction tx = session.beginTransaction();
            
            // Query to find the user by email
            Query<user> query = session.createQuery("from user where email = :email", user.class);
            query.setParameter("email", username);
            user u = query.uniqueResult();

            // Commit the transaction (optional depending on your transaction management setup)
            tx.commit();

            // Handle user not found
            if (u == null) {
                throw new UsernameNotFoundException("Email not found");
            } else {
                // Return the custom UserDetails implementation
                return new UserConfig(u);
            }
        } catch (Exception e) {
            // Handle exceptions properly (log it, rethrow, etc.)
            throw new RuntimeException("Error occurred while loading user by username", e);
        }
    }
}
