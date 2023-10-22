package backend.apishoppingcart.dto.exception;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Getter;

/**objeto base para la tarnsferencia en controller advice
 * @author Javier Vanegas
 * @author Banco Cuscatlan
 * @version 1.0
 * @since 18/01/2022
*/
@Getter
@Builder
public class ErrorDto implements Serializable {
    private static final long serialVersionUID = 1L;
	private HttpHeaders httpHeaders;
    private HttpStatus httpStatus;
    private String field;
    private List<String> errors;
}
