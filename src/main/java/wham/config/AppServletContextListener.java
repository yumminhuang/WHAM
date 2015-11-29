package wham.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	AppEntityManager.initEntityManagerFactory();
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	AppEntityManager.closeEntityManagerFactory();
    }
}
