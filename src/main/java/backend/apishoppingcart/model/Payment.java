package backend.apishoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "payment",schema = "sch_orders")
@Data
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_payment")
	private Long id;
	
	@Column(name = "id_order")
    private Long idOrder;
	
	@Column(name = "names")
	private String names;
	
	@Column(name = "surnames")
	private String surnames;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "number_card")
	private String number_card;
}
