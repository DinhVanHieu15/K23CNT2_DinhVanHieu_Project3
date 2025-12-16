package K23CNT2_DinhVanHieu_2310900122.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DvhWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry dvhRegistry) {
        dvhRegistry
                .addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
        dvhRegistry
                .addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
    }
}
