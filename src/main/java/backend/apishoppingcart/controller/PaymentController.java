package backend.apishoppingcart.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.apishoppingcart.component.util.ServiceFactory;
import backend.apishoppingcart.component.util.constant.ConstantesGeneral;
import backend.apishoppingcart.component.util.log.LogUtil;
import backend.apishoppingcart.component.util.log.LogUtil.TYPELOG;
import backend.apishoppingcart.dto.base.GenericResponseDto;
import backend.apishoppingcart.dto.payment.PaymentDto;
import backend.apishoppingcart.dto.payment.PaymentUpdateDto;
import backend.apishoppingcart.service.IPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final LogUtil logs;
	
	private final IPaymentService paymentService;
	
	@PostMapping("")
	@Operation(summary = "Service create a order", description = "Create the order")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> create(@RequestBody @Valid PaymentDto request) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrderController / create() backend.shoppingcart.controller, ");

		var respuesta = paymentService.create(request);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "No se encontraron productos");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}
	
	@PutMapping("")
	@Operation(summary = "Service create a order", description = "Create the order")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request has succeeded"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public ResponseEntity<GenericResponseDto<Object>> create(@RequestBody @Valid PaymentUpdateDto request) {
		logs.write(TYPELOG.INFO, ConstantesGeneral.LOG_INFO,
				"Inicio controller OrderController / create() backend.shoppingcart.controller, ");

		var respuesta = paymentService.update(request);

		if (Boolean.FALSE.equals(respuesta.getSuccess())) {
			logs.write(TYPELOG.INFO, respuesta.getMessage(), "No se encontraron productos");

			return ServiceFactory.notFoundResponse(respuesta.getItem(), respuesta.getMessage());
		}

		return ServiceFactory.createResponse(respuesta.getItem());
	}
	
}
