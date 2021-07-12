package context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;

public class ContextLoader {
    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    public static final String CONTEXT_CLASS_PARAM = "contextClass";

    private WebApplicationContext context;

    /**
     * 初始化web application 上下文根据给定的servlet上下文
     * 使用由xx提供的,或者根据 "{@link #CONTEXT_CLASS_PARAM contextClass}" 和
     * “{@link #CONFIG_LOCATION_PARAM contextConfigLocation}” context-params.
     *
     */
    public void initWebApplication(ServletContext servletContext) {
        //TODO：判断当前启动的容器是否是重复启动的
//        if(servletContext.getAttribute()) {
//
//        }
        servletContext.log("Initializing Spring root WebApplicationContext");
        Log logger = LogFactory.getLog(ContextLoader.class);
        if (logger.isInfoEnabled()) {
            logger.info("Root WebApplicationContext: initialization started");
        }
        long startTime = System.currentTimeMillis();
        try {
            if (this.context == null) {
                this.context = createWebApplicationContext(servletContext);
            }
        } catch (RuntimeException ex) {

        }
    }

    protected WebApplicationContext createWebApplicationContext(ServletContext servletContext) {
        Class<?> contextClass = determineContextClass(servletContext);

        //TODO
        return null;
    }

    protected Class<?> determineContextClass(ServletContext servletContext) {
        String contextClassName = servletContext.getInitParameter(CONTEXT_CLASS_PARAM);
        if (contextClassName != null) {

        }
        //TODO
        return null;
    }
}
