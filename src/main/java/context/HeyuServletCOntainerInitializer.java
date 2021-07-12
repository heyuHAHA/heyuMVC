package context;

import context.init.WebApplicationInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@HandlesTypes(WebApplicationInitializer.class)
public class HeyuServletCOntainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {

        List<WebApplicationInitializer>initializers = new LinkedList<>();
        if (webAppInitializerClasses != null ) {
            for (Class<?> waiClass : webAppInitializerClasses) {
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                        WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
                    try{
                        Constructor<WebApplicationInitializer> constructor = (Constructor<WebApplicationInitializer>) waiClass.getDeclaredConstructor();
                        if ((!Modifier.isPublic(constructor.getModifiers()) ||
                                !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) && !constructor.isAccessible()) {
                            constructor.setAccessible(true);
                        }
                        initializers.add(constructor.newInstance());
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                    }
                }
            }
        }


        if(initializers.isEmpty()) {
            servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
            return;
        }

        servletContext.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
//        if (initializers.size() > 1) {
//            initializers.sort(INSTANCE);
//        }
        for (WebApplicationInitializer initializer : initializers) {
            initializer.onStartup(servletContext);
        }
    }
}
