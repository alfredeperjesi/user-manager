package org.alfredeperjesi.usermanager.system.app;

import org.alfredeperjesi.usermanager.system.config.RootApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootApplicationConfig.class);
        context.registerShutdownHook();
    }
}
