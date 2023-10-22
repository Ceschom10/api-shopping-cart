package backend.apishoppingcart.service;

import backend.apishoppingcart.dto.base.GenericResponseServiceDto;
import backend.apishoppingcart.dto.payment.PaymentDto;
import backend.apishoppingcart.dto.payment.PaymentUpdateDto;

public interface IPaymentService {
	public GenericResponseServiceDto<Object> create(PaymentDto rquest);
	public GenericResponseServiceDto<Object> update(PaymentUpdateDto rquest);
}
