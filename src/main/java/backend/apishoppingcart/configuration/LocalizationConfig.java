package backend.apishoppingcart.configuration;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**Localization Configuration Clase de configuracion de internacionalizacion del servicio
 * @author Cesar Amaya
 * @version 1.0
 * @since 23/05/2023
*/
@Configuration
public class LocalizationConfig implements WebMvcConfigurer {
    
    /** 
     * Configurando bean de obtencion de los mensajes del archivo properties
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
        = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        return messageSource;
    }

    /** 
     * Configurando bean de obtencion de Local resolver
     * @return MessageSource
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

}
