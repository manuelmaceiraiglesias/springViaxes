package dam.proba;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ViaxeRepository extends JpaRepository<Viaje, LocalDate> {
List<Viaje> findAll();
Long deleteByFecha(LocalDate data);
Viaje findByFecha(LocalDate data);
}
