package backend.apishoppingcart.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Generic Response clase encargada de devolver la respuesta generica.
 * @author Javier Vanegas
 * @author Banco Cuscatlan
 * @version 1.0
 * @since 18/01/2022
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenericResponseDto <T>{
	private T item;
    private String message;
    private String status;
}
