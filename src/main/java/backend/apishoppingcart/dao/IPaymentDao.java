package backend.apishoppingcart.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.apishoppingcart.model.Payment;

@Repository
public interface IPaymentDao extends JpaRepository<Payment, Long>{

	@Query("SELECT p FROM Payment p WHERE p.id = :id")
	Optional<Payment> findByPayment(@Param("id") Long id);
}
