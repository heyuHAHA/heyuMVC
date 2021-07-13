package context;

import context.init.AbstractContextLoaderInitializer;
import servlet.DispatcherServlet;
import servlet.FrameServlet;

import javax.servlet.*;
import java.util.EnumSet;

public abstract class AbstractDispatcherServletInitializer extends AbstractContextLoaderInitializer {

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

    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        String filterName = getFilterName(filter.getClass());
        FilterRegistration.Dynamic registration =  servletContext.addFilter(filterName,filter);

        //重试机制
        if (registration == null ) {
            int counter = 0;
            while (registration == null) {
                if (counter == 100) {
                    throw new IllegalArgumentException("Failed to register filter with name '" + filterName + "'." +
                            "Check if there is another filter registered under the same name.");
                }
                registration = servletContext.addFilter(filterName + "#" + counter, filter);
                counter++;
            }
        }

        registration.setAsyncSupported(isAsyncSupported());
        registration.addMappingForServletNames(getDispatcherTypes(),false, getServletName());
        return registration;
    }

    private EnumSet<DispatcherType> getDispatcherTypes() {
        return (isAsyncSupported() ?
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC) :
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE));
    }

    protected String getFilterName(Class<? extends Filter> aClass) {
        String name = aClass.getName();
        int dotIndex = name.lastIndexOf(".");
        name =(dotIndex != -1 ? name.substring(dotIndex+1) : name);
        char chars[] = name.toCharArray();
        chars[0] =Character.toLowerCase(chars[0]);
        return new String(chars);
}

    protected Filter[] getServletFilters() {
        return null;
    }

    protected boolean isAsyncSupported() {
        return true;
    }

    protected String getServletMappings() {
        return null;
    }


    protected FrameServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        return new DispatcherServlet(servletAppContext);
    }

    protected abstract WebApplicationContext createServletApplicationContext();


    protected String getServletName() {
        return DEFAULT_SERVLET_NAME;
    }

    @Override
    protected WebApplicationContext createWebApplicationContext() {
        return null;
    }
}
