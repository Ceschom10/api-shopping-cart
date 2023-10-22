package backend.apishoppingcart.service;

import backend.apishoppingcart.dto.base.GenericResponseServiceDto;
import backend.apishoppingcart.dto.fakes.ResponseGetKafestoreapiDto;
import backend.apishoppingcart.dto.fakes.ResponseServiceKafestoreapi;

public interface IFakesService {

	public ResponseServiceKafestoreapi getProducts();
	public GenericResponseServiceDto<ResponseGetKafestoreapiDto> getProductOne(Long id);
}
