package servlet;

import context.WebApplicationContext;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class DispatcherServlet extends FrameServlet {


    private MultipartResolver multipartResolver;

    private LocaleResolver localeResolver;

    private ThemeResolver themeResolver;

    public static final String MULTIPART_RESOLVER_BEAN_NAME = "multipartResolver";


    public static final String LOCALE_RESOLVER_BEAN_NAME = "localeResolver";


    public static final String THEME_RESOLVER_BEAN_NAME = "themeResolver";


    public static final String HANDLER_MAPPING_BEAN_NAME = "handlerMapping";


    public static final String HANDLER_ADAPTER_BEAN_NAME = "handlerAdapter";


    public static final String HANDLER_EXCEPTION_RESOLVER_BEAN_NAME = "handlerExceptionResolver";


    public static final String REQUEST_TO_VIEW_NAME_TRANSLATOR_BEAN_NAME = "viewNameTranslator";


    public static final String VIEW_RESOLVER_BEAN_NAME = "viewResolver";


    static {
        try {
            //这里可以静态加载默认组件的类
        } catch(IOException ex) {
            throw new IllegalStateException("Could not load 'DispatcherServlet.properties': " +ex.getMessage())
        }
    }

    public DispatcherServlet(WebApplicationContext servletAppContext) {
    }

    protected void initStrategies(WebApplicationContext context) {
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        initHandlerMappings(context);
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    private void initFlashMapManager(WebApplicationContext context) {
    }

    private void initViewResolvers(WebApplicationContext context) {
    }

    private void initRequestToViewNameTranslator(WebApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(WebApplicationContext context) {
    }

    private void initHandlerAdapters(WebApplicationContext context) {
    }

    private void initHandlerMappings(WebApplicationContext context) {
    }

    private void initThemeResolver(WebApplicationContext context) {
        try {
            this.themeResolver = context.getBean(THEME_RESOLVER_BEAN_NAME, ThemeResolver.class);
        } catch (Exception e) {
            this.themeResolver = null;
        }
    }

    private void initLocaleResolver(WebApplicationContext context) {
        try {
            this.localeResolver = context.getBean(LOCALE_RESOLVER_BEAN_NAME,LocaleResolver.class);
        } catch(Exception e) {
            this.localeResolver = null;
        }
    }

    private void initMultipartResolver(WebApplicationContext context) {
        try {
            this.multipartResolver = context.getBean(MULTIPART_RESOLVER_BEAN_NAME, MultipartResolver.class);
        } catch (Exception e) {
            this.multipartResolver = null;

        }
    }

}
