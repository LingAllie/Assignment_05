package com.vti.utils;

import com.vti.entity.Account;
import com.vti.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtils {
    private static HibernateUtils instance;

    private Configuration configuration;
    private SessionFactory sessionFactory;

    public static HibernateUtils getInstance() {
        if (instance == null) {
            instance = new HibernateUtils();
        }
        return instance;
    }

    private HibernateUtils() {
        configure();
    }

    private void configure() {
        // load configuration
        configuration = new Configuration().configure("hibernate.cfg.xml");

        // Add entity
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Account.class);
    }

    private SessionFactory buildSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public void closeFactory() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }

    public Session openSession() {
        buildSessionFactory();
        return sessionFactory.openSession();
    }
}
