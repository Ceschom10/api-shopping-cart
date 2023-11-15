package backend.apishoppingcart.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Value("${openapi.project-title}")
	private String projectTitle;
	@Value("${openapi.project-description}")
	private String projectDescription;
	@Value("${openapi.project-version}")
	private  String projectVersion;
	private static final String SCHEME_NAME = "JWT"; // Cambiamos el nombre del esquema a "JWT"
    private static final String SCHEME = "bearer"; // Usamos el esquema "bearer" para JWT

 
    @Bean
    public OpenAPI customOpenAPI() {
        OpenApiProperties properties = infoGet();
        var openApi = new OpenAPI()
                .info(getInfo(properties));
        addSecurity(openApi);
        return openApi;
    }

    private OpenApiProperties infoGet() {
        OpenApiProperties prop = new OpenApiProperties();
        prop.setProjectTitle(projectTitle);
        prop.setProjectVersion(projectVersion);
        prop.setProjectDescription(projectDescription);
        return prop;
    }

    private Info getInfo(OpenApiProperties properties) {
        return new Info()
                .title(properties.getProjectTitle())
                .description(properties.getProjectDescription())
                .version(properties.getProjectVersion())
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("Unlicense")
                .url("https://unlicense.org/");
    }

    private void addSecurity(OpenAPI openApi) {
        var components = createComponents();
        var securityItem = new SecurityRequirement().addList(SCHEME_NAME);

        openApi.components(components)
                .addSecurityItem(securityItem);
    }

    private Components createComponents() {
        var components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());
        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

}
