package backend.apishoppingcart;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
* @GenericException
*
* @1.0
*
* 18/01/2022
*
* Copyright Javier Vanegas
*/
/** Metodo main
 * @author Cesar Amaya
 * @version 1.0
 * @since 23/05/2023
*/
@SpringBootApplication
@Configuration
//@ComponentScan(basePackages = "backend.apishoppingcart.configuration.datasource")
public class ShoppingCart {

	/** 
	 * @param args main args
	 */
	public static void main(String[] args) {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT-6:00"));
		SpringApplication.run(ShoppingCart.class, args);
	}
}
