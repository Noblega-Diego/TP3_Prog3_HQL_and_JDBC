package com.tp3.progIII_TP3.hql.persistencia;

import com.tp3.progIII_TP3.hql.modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author diego
 */
public class ConfigHibernate {

    private static String HOST = "localhost";
    private static String PORT = "3306";
    private static String DB = "orm_Computadora";
    private static SessionFactory sesionFactory;
    public void load() {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.url", "jdbc:mysql://"+HOST+":"+PORT+"/"+DB);
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "mysql");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.hbm2ddl.auto", "update" );
        config.setProperty("hibernate.c3p0.min_size","0");
        config.setProperty("hibernate.c3p0.max_size","7");
        config.setProperty("hibernate.c3p0.timeout","100");
        config.setProperty("hibernate.c3p0.max_elements","100");
        config.setProperty("hibernate.c3p0.idle_test_period","100");
        config.setProperty("hibernate.c3p0.autoCommitOnClose","true");
        config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
        config.addPackage("Entidades");
        config.addAnnotatedClass(Componente.class);
        config.addAnnotatedClass(Computadora.class);
        sesionFactory = config.buildSessionFactory();
    }
    
    public SessionFactory getSessionFactory(){
        if(sesionFactory == null)
            load();
        return sesionFactory;
    }
    
}
