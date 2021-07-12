package context;

import context.init.AbstractContextLoaderInitializer;
import servlet.FrameServlet;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AbstractDispatcherServletInitializer extends AbstractContextLoaderInitializer {

    public static final String DEFAULT_SERVLET_NAME = "dispatcher";


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerDispatcherServlet(servletContext);
    }

    protected void registerDispatcherServlet(ServletContext servletContext) {
        String servletName = getServletName();
        //TODO 判断servlet不能为空


        //DispatchServlet持有的WebApplicationContext就是这里
        WebApplicationContext servletAppContext = createServletApplicationContext();

        //这里初始化DispatchServlet
        FrameServlet dispatchServlet = createDispatcherServlet(servletAppContext);

        //TODO
//        dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());

        //TODO :Learn ServletRegistration
       ServletRegistration.Dynamic registration = servletContext.addServlet(servletName,dispatchServlet);
       if (registration == null) {
           throw new IllegalArgumentException("Failed to register servlet with name '" + servletName + "'. " +
                   "Check if there is another servlet registered under the same name.");
       }

       registration.setLoadOnStartup(1);

       registration.addMapping(getServletMappings());

       registration.setAsyncSupported(isAsyncSupported());

       Filter[] filters = getServletFilters();

       if ( filters != null && filters.length != 0) {
            for (Filter filter :filters) {
                registerServletFilter(servletContext, filter);
            }
       }

       //扩展点
        customizeRegistration(registration);

    }

    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    }

    protected void registerServletFilter(ServletContext servletContext, Filter filter) {
    }

    protected Filter[] getServletFilters() {
        return null;
    }

    protected boolean isAsyncSupported() {
        return false;
    }

    protected String getServletMappings() {
        return null;
    }


    protected FrameServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        return null;
    }

    protected WebApplicationContext createServletApplicationContext() {
        return null;

    }

    protected String getServletName() {
        return DEFAULT_SERVLET_NAME;
    }

    @Override
    protected WebApplicationContext createWebApplicationContext() {
        return null;
    }
}
