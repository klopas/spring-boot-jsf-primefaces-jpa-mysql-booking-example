package com.franciscogarrido.web;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.franciscogarrido.entities.Reserva;
import com.franciscogarrido.repositories.ReservaRepository;

@Component("formController")
@Scope("request")
public class FormController {

	@Autowired
	ReservaRepository reservaRepository;

	private Reserva reserva;

	@PostConstruct
	public void init() {
		reserva = new Reserva();
	}

	public void submit() {
		if (reserva.getNombre() != null && reserva.getEmail() != null && reserva.getFechaEntrada() != null
				&& reserva.getFechaSalida() != null) {
			reserva.setToken(UUID.randomUUID().toString());

			reservaRepository.save(reserva);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Debe rellenar todos los campos."));
		}
	}

	public void reset() {
		reserva = new Reserva();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
