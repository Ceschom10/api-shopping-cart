package backend.apishoppingcart.dto.exception;

import java.io.Serializable;
import java.util.List;


import backend.apishoppingcart.component.util.DateUtils;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

/**Respuesta generica de exepciones
 * @author Javier Vanegas
 * @author Banco Cuscatlan
 * @version 1.0
 * @since 18/01/2022
*/
@Builder
@Getter
public class ResponseErrorTwoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String object;
    private HttpStatus status;
    private String field;
    private String message;
    private String errorCode;
    private List<String> errors;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss:SSS", timezone = "America/El_Salvador")
    private String timestamp = DateUtils.formaterDate();
}
