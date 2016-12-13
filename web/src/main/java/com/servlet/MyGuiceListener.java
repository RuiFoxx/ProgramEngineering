package com.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class MyGuiceListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {
                serve("/ajax/user").with(MyUserServlet.class);
                serve("/ajax/role").with(MyRoleServlet.class);
                serve("/ajax/accounting").with(MyAccountingServlet.class);
            }
        });
    }
}

class Log4JTypeListener implements TypeListener {
    public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == Logger.class &&
                        field.isAnnotationPresent(InjectLogger.class))
                    typeEncounter.register(new Log4JMembersInjector<T>(field));
            }
            clazz = clazz.getSuperclass();
        }
    }
}

class Log4JMembersInjector<T> implements MembersInjector<T> {
    private final Field field;
    private final Logger logger;

    Log4JMembersInjector(Field field) {
        this.field = field;
        this.logger = Logger.getLogger(field.getDeclaringClass().getCanonicalName());
        field.setAccessible(true);
    }

    public void injectMembers(T t) {
        try {
            field.set(t, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}