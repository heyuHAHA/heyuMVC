package context.init;

import context.ContextLoaderListener;
import context.WebApplicationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public abstract class AbstractContextLoaderInitializer implements WebApplicationInitializer {

    protected final Log logger = LogFactory.getLog(getClass());
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerContextLoaderListener(servletContext);
    }

    protected  void registerContextLoaderListener(ServletContext servletContext) {
        WebApplicationContext rootAppContext = createWebApplicationContext();
        if (rootAppContext != null) {
            ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
            //listener.setContextInitializers(getRootApplicationContextInitializers());
            servletContext.addListener(listener);
        } else {
            logger.debug("No ContextLoaderListener registered, as " +
                    "createRootApplicationContext() did not return an application context");
        }
    }

    protected abstract WebApplicationContext createWebApplicationContext();

}
