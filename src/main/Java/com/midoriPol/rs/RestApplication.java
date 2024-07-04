package com.midoriPol.rs;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/midoripol")
public class RestApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(JacksonConfiguration.class);
        classes.add(PersonResource.class);
        classes.add(HeadersFilter.class);
        return classes;
    }
}