package backend.apishoppingcart.dto.order;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
	
	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max=250, message = "message.request.orden.size")
	private String customer;
	
	@DecimalMin(value = "0.00")
    @NotNull
	private BigDecimal total;
	
	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max=50, message = "message.request.paymentorder.size")
	private String orderStatus;
	
	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max=50, message = "message.request.paymentorder.size")
	private String paymentMethod;
	
	@NotBlank(message = "message.request.blank")
	@NotNull(message = "message.request.null")
	@Size(max=50, message = "message.request.paymentorder.size")
	private String paymentStatus;
	
	@Valid
    @NotEmpty(message = "message.request.list.empty")
	private List<DetailsCreateDto> details;
}
