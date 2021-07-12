package context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class ContextLoaderListener extends ContextLoader implements ServletContextListener{
    public ContextLoaderListener(WebApplicationContext rootAppContext) {
        super();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initWebApplication(servletContextEvent.getServletContext());
    }




}
