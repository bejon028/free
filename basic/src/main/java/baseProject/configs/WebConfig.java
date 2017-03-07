package baseProject.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] resourceLocations = {
                "classpath:/META-INF/resources/", "classpath:/resources/",
                "classpath:/static/", "classpath:/public/"};

        if (!registry.hasMappingForPattern("/static/**")) {
            if (!registry.hasMappingForPattern("/**")) {
                registry.addResourceHandler("/**").addResourceLocations(
                        resourceLocations);
            }
        }
    }
}
