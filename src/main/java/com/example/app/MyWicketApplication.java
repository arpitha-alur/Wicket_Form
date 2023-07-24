package com.example.app;

//import org.apache.wicket.Page;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.http.WicketServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MyWicketApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    public static void main(String[] args) {
        // Start your Wicket application using Jetty as the embedded servlet container
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        FilterHolder filterHolder = new FilterHolder(WicketFilter.class);
        filterHolder.setInitParameter("applicationClassName", MyWicketApplication.class.getName());
        context.addFilter(filterHolder, "/*", null);

        ServletHolder servletHolder = new ServletHolder(WicketServlet.class);
        context.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
