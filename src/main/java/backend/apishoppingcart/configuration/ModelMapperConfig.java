package backend.apishoppingcart.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuracion del model mapper para mapeos por nombre
  * @author Cesar Amaya
 * @version 1.0
 * @since 23/05/2023
*/
@Configuration
public class ModelMapperConfig {

    /** 
     * @return ModelMapper elemento de configuracion de model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        return customMapping(mapper);
    }

    
    /** 
     * @param modelMapper
     * @return ModelMapper
     */
    private ModelMapper customMapping(ModelMapper modelMapper){
    	
        return modelMapper;      
    }
}
