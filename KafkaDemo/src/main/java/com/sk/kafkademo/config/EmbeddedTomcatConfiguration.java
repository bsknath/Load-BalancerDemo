//package com.sk.kafkademo.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.catalina.connector.Connector;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
//
//@Configuration
//public class EmbeddedTomcatConfiguration  {
//	
//	@Value("${server.otherports}")
//	private String ports;
//	
//	@Bean
//    public TomcatServletWebServerFactory  servletContainer() {
//		TomcatServletWebServerFactory  tomcat = new TomcatServletWebServerFactory ();
//        Connector[] additionalConnectors = this.additionalConnector();
//        if (additionalConnectors != null && additionalConnectors.length > 0) {
//            tomcat.addAdditionalTomcatConnectors(additionalConnectors);
//        }
//        return tomcat;
//    }
//	
//	private Connector[] additionalConnector() {
//        if (StringUtils.isEmpty(this.ports)) {
//            return null;
//        }
//        String[] ports = this.ports.split(",");
//        List<Connector> result = new ArrayList<>();
//        for (String port : ports) {
//            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//            connector.setScheme("http");
//            connector.setPort(Integer.valueOf(port));
//            result.add(connector);
//        }
//        return result.toArray(new Connector[] {});
//    }
//	
//}
