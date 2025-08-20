package com.groupeisi.soap_springboot_jpa.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapWebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "sectors")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema sectorSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("SectorsPort");
        wsdl.setLocationUri("/ws");
        // 🔧 CORRECTION: Namespace unifié avec votre XSD et endpoint
        wsdl.setTargetNamespace("http://groupeisi.com/soap");
        wsdl.setSchema(sectorSchema);
        return wsdl;
    }

    @Bean
    public XsdSchema sectorSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/sector.xsd"));
    }
}