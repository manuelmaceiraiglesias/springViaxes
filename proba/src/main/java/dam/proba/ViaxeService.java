package dam.proba;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViaxeService {
    private final ViaxeRepository viaxeRepository;

    public ViaxeService(ViaxeRepository viaxeRepository) {
        this.viaxeRepository = viaxeRepository;
    }

    public List<Viaje> verTodos() {
        return viaxeRepository.findAll();
    }

    public Viaje verUn(LocalDate data) {
        return viaxeRepository.findByFecha(data);
    }

    public void gardarUn(Viaje v) {
        viaxeRepository.save(v);
    }

    public void modificarUn(Viaje v) {
        viaxeRepository.save(v);
    }
    @Transactional
    public Long borrarUn(LocalDate data) {
        return viaxeRepository.deleteByFecha(data);
    }
}
