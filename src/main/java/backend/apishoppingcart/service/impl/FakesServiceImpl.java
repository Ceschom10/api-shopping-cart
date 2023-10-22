package backend.apishoppingcart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import backend.apishoppingcart.component.util.constant.ConstantesGeneral;
import backend.apishoppingcart.dto.base.GenericResponseServiceDto;
import backend.apishoppingcart.dto.fakes.ResponseGetKafestoreapiDto;
import backend.apishoppingcart.dto.fakes.ResponseServiceKafestoreapi;
import backend.apishoppingcart.service.IFakesService;
import backend.apishoppingcart.service.consumer.Consumer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FakesServiceImpl implements IFakesService{
	
	private final Consumer consumer;
	private final List<ResponseGetKafestoreapiDto> servicioGetKafestoreapi() {

		Map<String, String> headers = new HashMap<>();
		headers.put(ConstantesGeneral.NCONTENTTYPE, ConstantesGeneral.APPLICATIONJSON);
		headers.put(ConstantesGeneral.ACCEPTLANGUAGE, ConstantesGeneral.LANGUAGE);

		consumer.setDefaultHeaders(headers);

		return consumer.getOneByQueryStringList(ConstantesGeneral.URL_BASE_FAKESTOREAPI, "/products")
				.block();
	}
	
	private ResponseGetKafestoreapiDto servicioGetKafestoreapiOne(Long id) {

		Map<String, String> headers = new HashMap<>();
		headers.put(ConstantesGeneral.NCONTENTTYPE, ConstantesGeneral.APPLICATIONJSON);
		headers.put(ConstantesGeneral.ACCEPTLANGUAGE, ConstantesGeneral.LANGUAGE);

		consumer.setDefaultHeaders(headers);

		return consumer.getOneByQueryStringOne(ConstantesGeneral.URL_BASE_FAKESTOREAPI, "/products/"+id, ResponseGetKafestoreapiDto.class)
				.block();
	}

	@Override
	public ResponseServiceKafestoreapi getProducts() {
		ResponseServiceKafestoreapi respuesta = new ResponseServiceKafestoreapi();
		List<ResponseGetKafestoreapiDto> responseGetKafes = servicioGetKafestoreapi();
		if (responseGetKafes == null) {
			respuesta.setSuccess(false);
			respuesta.setMessage("No products found");
		}else {
			respuesta.setItem(responseGetKafes);
			respuesta.setMessage("Successful process");
			respuesta.setSuccess(true);
		}
		return respuesta;
	}

	@Override
	public GenericResponseServiceDto<ResponseGetKafestoreapiDto> getProductOne(Long id) {
		var response = new GenericResponseServiceDto<ResponseGetKafestoreapiDto>();
		ResponseGetKafestoreapiDto responseGetKafes = servicioGetKafestoreapiOne(id);
		if (responseGetKafes == null) {
			response.setSuccess(false);
			response.setMessage("No products found");
		}else {
			response.setItem(responseGetKafes);
			response.setMessage("Successful process");
			response.setSuccess(true);
		}
		return response;
	}
}
