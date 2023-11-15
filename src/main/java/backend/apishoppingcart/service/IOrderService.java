package backend.apishoppingcart.service;

import java.util.List;

import backend.apishoppingcart.dto.base.GenericResponseServiceDto;
import backend.apishoppingcart.dto.order.OrderCreateDto;
import backend.apishoppingcart.dto.order.OrderDto;

public interface IOrderService {
	public GenericResponseServiceDto<OrderDto> get(Long id);
	public GenericResponseServiceDto<List<OrderDto>> getAll();
	public GenericResponseServiceDto<Object> create(OrderCreateDto rquest);
	public GenericResponseServiceDto<Object> update(OrderDto request);
	public GenericResponseServiceDto<Object> delete(Long id);
}
