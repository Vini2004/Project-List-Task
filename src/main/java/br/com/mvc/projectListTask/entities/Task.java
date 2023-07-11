package br.com.mvc.projectListTask.entities;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.mvc.projectListTask.entities.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Define o formato esperado para data e hora
    private String dataInicial;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Define o formato esperado para data e hora
    private String dataFinal;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    private UserDtls user;
    

	
	public String getStatusText() {
	    return status.toString();
	}

    
    
}