package dam.proba;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="viajes")
public class Viaje {
    @Id
    @NotNull(message = "Fecha debe tener contenido")
    private LocalDate fecha;
    @NotNull(message = "Distancia debe tener un valor")
    @DecimalMin(value = "1.00",message = "El valor es menor de 1")
    @DecimalMax(value = "300.00", message = "El valor es superior a 300")
    private Double distancia;
    @NotNull(message = "Desnivel debe tener un valor")
    @Min(value = 0,message = "El desnivel no puede ser menor de 0")
    @Max(value=5000,message = "El desnivel no puede ser mayor de 5000")
    @Column(columnDefinition = "smallint")
    private Integer desnivel;
    @NotEmpty(message = "Comentario debe tener contenido")
    private String comentario;

    @Override
    public String toString() {
        return "Viaje{" +
                "fecha=" + fecha +
                ", distancia=" + distancia +
                ", desnivel=" + desnivel +
                ", comentario='" + comentario + '\'' +
                '}';
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Integer getDesnivel() {
        return desnivel;
    }

    public void setDesnivel(Integer desnivel) {
        this.desnivel = desnivel;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
