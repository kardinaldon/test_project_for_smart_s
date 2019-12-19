package front.config;

import dto.PurchaseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main_page.html").setViewName("main_page");
    }

    @Bean
    public MarshallingHttpMessageConverter marshallingMessageConverter() throws Exception {
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller(jaxb2Marshaller());
        marshallingHttpMessageConverter.setUnmarshaller(jaxb2Marshaller());
        return marshallingHttpMessageConverter;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(PurchaseDto.class);
        marshaller.setSchema(new ClassPathResource("schema1.xsd"));
        marshaller.setValidationEventHandler(new ValidationEventHandlerImpl());
        marshaller.afterPropertiesSet();
        return marshaller;
    }
}