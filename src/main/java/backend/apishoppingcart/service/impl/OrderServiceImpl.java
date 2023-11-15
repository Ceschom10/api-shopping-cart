package backend.apishoppingcart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import backend.apishoppingcart.dao.IOrderDao;
import backend.apishoppingcart.dto.base.GenericResponseServiceDto;
import backend.apishoppingcart.dto.fakes.RequestGetOneDto;
import backend.apishoppingcart.dto.fakes.ResponseGetKafestoreapiDto;
import backend.apishoppingcart.dto.order.DetailsCreateDto;
import backend.apishoppingcart.dto.order.DetailsUpdateDto;
import backend.apishoppingcart.dto.order.OrderCreateDto;
import backend.apishoppingcart.dto.order.OrderDto;
import backend.apishoppingcart.model.Order;
import backend.apishoppingcart.model.OrderDetails;
import backend.apishoppingcart.service.IFakesService;
import backend.apishoppingcart.service.IOrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
	private final IOrderDao orderDao;
	private final IFakesService fakesService;
	private final ModelMapper modelMapper;

	@Override
	public GenericResponseServiceDto<List<OrderDto>> getAll() {
		List<OrderDto> listDtoOrder = new ArrayList<>();
		var response = new GenericResponseServiceDto<List<OrderDto>>();

		List<Order> listOrder = orderDao.findAllWithOrderDetails();
		if (listOrder.isEmpty()) {
			response.setMessage("The order list is empty");
			response.setSuccess(false);
			response.setItem(listDtoOrder);
		} else {
			listDtoOrder = mapperListOrderToListOrderDto(listOrder);

			response.setMessage("Successful response");
			response.setSuccess(true);
			response.setItem(listDtoOrder);
		}
		return response;
	}

	@Override
	public GenericResponseServiceDto<OrderDto> get(Long id) {
		var response = new GenericResponseServiceDto<OrderDto>();

		Optional<Order> orderOpt = orderDao.findByIdWithOrderDetails(id);
		if (orderOpt.isEmpty()) {
			response.setMessage("The order was not found");
			response.setSuccess(false);
		} else {
			
			OrderDto orderDto = mapperOrderToOrderDto(orderOpt.get());
					
			response.setMessage("Successful response");
			response.setSuccess(true);
			response.setItem(orderDto);
		}
		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> create(OrderCreateDto request) {
		var response = new GenericResponseServiceDto<Object>();
		try {
			Order order = new Order();		
			int index = 0; 
			for (DetailsCreateDto detailsOrderDto : request.getDetails()) {
				var responseCheckProduct = new GenericResponseServiceDto<ResponseGetKafestoreapiDto>();
				RequestGetOneDto requestCheck = new RequestGetOneDto();
				requestCheck.setIdProducto(detailsOrderDto.getIdProduct());
				
				//Se obtienen los productos de la api por medio del id
				responseCheckProduct = fakesService.getProductOne(detailsOrderDto.getIdProduct());
				
				if (!responseCheckProduct.getSuccess()) {
					response.setSuccess(false);
					response.setMessage("The product does not exist "+requestCheck.getIdProducto());
					return response;
				}
				if (!detailsOrderDto.getPrice().equals(responseCheckProduct.getItem().getPrice())) {
					request.getDetails().get(index).setPrice((responseCheckProduct.getItem().getPrice()));
				}
				index++;
			}
			
			order = mapperOrderCreateDtoToOrder(request);

			orderDao.save(order);
			response.setSuccess(true);
			response.setMessage("Order saved successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage("An error occurred while saving");
		}
		
		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> update(OrderDto request) {
		var response = new GenericResponseServiceDto<Object>();
		try {
			Optional<Order> orderOpt = orderDao.findById(request.getId());
			
			if (orderOpt.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("The order does not exist "+request.getId());
				return response;
			}
			
			Order order = orderOpt.get();
			int index = 0;
			for (DetailsUpdateDto detailsUpdateDto : request.getDetails()) {
				var responseCheckProduct = new GenericResponseServiceDto<ResponseGetKafestoreapiDto>();
				RequestGetOneDto requestCheck = new RequestGetOneDto();
				
				//Se obtienen los productos de la api por medio del id
				responseCheckProduct = fakesService.getProductOne(detailsUpdateDto.getIdProduct());
				
				if (!responseCheckProduct.getSuccess()) {
					response.setSuccess(false);
					response.setMessage("The product does not exist "+requestCheck.getIdProducto());
					return response;
				}
				if (!detailsUpdateDto.getPrice().equals(responseCheckProduct.getItem().getPrice())) {
					request.getDetails().get(index).setPrice((responseCheckProduct.getItem().getPrice()));
				}
				index++;
			}
			order = mapperOrderUpdateDtoToOrder(request);
			orderDao.save(order);
			response.setSuccess(true);
			response.setMessage("Order updated successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage("Ocurrio un error al guardar");
		}
		
		return response;
	}

	@Override
	public GenericResponseServiceDto<Object> delete(Long id) {
		var response = new GenericResponseServiceDto<Object>();

		Optional<Order> orderOpt = orderDao.findById(id);
		if (orderOpt.isEmpty()) {
			response.setMessage("The order does not exist");
			response.setSuccess(false);
		} else {
			
			Order order = orderOpt.get();
			orderDao.delete(order);
					
			response.setMessage("Successful response");
			response.setSuccess(true);
		}
		return response;
	}
	
	private OrderDto mapperOrderToOrderDto(Order request) {
		OrderDto order= modelMapper.map(request, OrderDto.class);
		order.setDetails(modelMapper.map(request.getOrderDetails(), new TypeToken<List<DetailsUpdateDto>>() {}.getType()));
	    return order;
	}
	
	private List<OrderDto> mapperListOrderToListOrderDto(List<Order> request) {
		List<OrderDto> listOrder = new ArrayList<>();
		for (Order order : request) {
			OrderDto orderUpd = modelMapper.map(order, OrderDto.class);
			orderUpd.setDetails(modelMapper.map(order.getOrderDetails(), new TypeToken<List<DetailsUpdateDto>>() {}.getType()));
			listOrder.add(orderUpd);
		}
	    return listOrder;
	}

	private Order mapperOrderCreateDtoToOrder(OrderCreateDto request) {
	    Order order = modelMapper.map(request, Order.class);
	    List<OrderDetails> detailsList = new ArrayList<>();
	    BigDecimal total = BigDecimal.ZERO;

	    for (DetailsCreateDto detailDto : request.getDetails()) {
	        OrderDetails orderDetail = new OrderDetails();
	        orderDetail.setOrder(order); // Asocia el detalle con la orden
	        orderDetail.setQuantity(detailDto.getQuantity());
	        orderDetail.setPrice(detailDto.getPrice());
	        BigDecimal quantityBigDecimal = BigDecimal.valueOf(detailDto.getQuantity());
	        BigDecimal subtotal = quantityBigDecimal.multiply(detailDto.getPrice());
	        orderDetail.setSubtotal(subtotal);
	        total = total.add(subtotal);
	        detailsList.add(orderDetail);
	    }
	    order.setOrderDetails(detailsList);
	    order.setTotal(total);
	    return order;
	}
	
	
	private Order mapperOrderUpdateDtoToOrder(OrderDto request) {
	    Order order = modelMapper.map(request, Order.class);
	    List<OrderDetails> detailsList = new ArrayList<>();
	    BigDecimal total = BigDecimal.ZERO;

	    for (DetailsUpdateDto detailDto : request.getDetails()) {
	        OrderDetails orderDetail = new OrderDetails();
	        orderDetail.setId(detailDto.getId());
	        orderDetail.setOrder(order); // Asocia el detalle con la orden
	        orderDetail.setQuantity(detailDto.getQuantity());
	        orderDetail.setPrice(detailDto.getPrice());
	        BigDecimal quantityBigDecimal = BigDecimal.valueOf(detailDto.getQuantity());
	        BigDecimal subtotal = quantityBigDecimal.multiply(detailDto.getPrice());
	        orderDetail.setSubtotal(subtotal);
	        total = total.add(subtotal);
	        detailsList.add(orderDetail);
	    }
	    order.setOrderDetails(detailsList);
	    order.setTotal(total);
	    return order;
	}
}
