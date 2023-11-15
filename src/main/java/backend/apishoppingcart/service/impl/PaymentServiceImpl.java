package backend.apishoppingcart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import backend.apishoppingcart.dao.IOrderDao;
import backend.apishoppingcart.dao.IPaymentDao;
import backend.apishoppingcart.dto.base.GenericResponseServiceDto;
import backend.apishoppingcart.dto.payment.PaymentDto;
import backend.apishoppingcart.dto.payment.PaymentUpdateDto;
import backend.apishoppingcart.model.Order;
import backend.apishoppingcart.model.Payment;
import backend.apishoppingcart.service.IPaymentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {
	private final IOrderDao orderDao;
	private final ModelMapper modelMapper;
	private final IPaymentDao paymentDao;
	
	@Override
	public GenericResponseServiceDto<Object> create(PaymentDto request) {
		var response = new GenericResponseServiceDto<Object>();
		try {
			Optional<Order> order = orderDao.findById(request.getIdOrder());
					
			if (order.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("Error the order does not exist");
				return response;
			}
			Optional<Payment> paymentOpt = paymentDao.findByPayment(request.getIdOrder());
			if (!paymentOpt.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("The payment was already made");
				return response;
			}
			
			Payment payment = mapperPaymentDtoToPayment(request);

			paymentDao.save(payment);
			response.setSuccess(true);
			response.setMessage("payment saved successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage("An error occurred while saving");
		}
		
		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> update(PaymentUpdateDto request) {
		var response = new GenericResponseServiceDto<Object>();
		try {
			Optional<Order> order = orderDao.findById(request.getIdOrder());
					
			if (order.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("Error the order does not exist");
				return response;
			}
			Optional<Payment> paymentOpt = paymentDao.findByPayment(request.getId());
			if (paymentOpt.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("Payment does not exist");
				return response;
			}
			
			Payment payment = mapperPaymentUpdateDtoToPayment(request);

			paymentDao.save(payment);
			response.setSuccess(true);
			response.setMessage("payment saved successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage("An error occurred while saving");
		}
		
		return response;
	}
	
	private Payment mapperPaymentDtoToPayment(PaymentDto request) {
		Payment payment= modelMapper.map(request, Payment.class);
	    return payment;
	}
	
	private Payment mapperPaymentUpdateDtoToPayment(PaymentUpdateDto request) {
		Payment payment= modelMapper.map(request, Payment.class);
	    return payment;
	}
	
}
