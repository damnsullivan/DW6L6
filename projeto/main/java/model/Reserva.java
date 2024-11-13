package model;

import java.sql.Time;
import java.sql.Date;

public class Reserva {
	private Integer id;
	private Integer salaId;
	private Date dataReserva;
	private Time horaInicio;
	private Time horaFim;
	private String reservadoPor;

	public Reserva() {

	}

	public Reserva(Integer id, Integer salaId, Date dataReserva, Time horaInicio, Time horaFim, String reservadoPor) {
		this.id = id;
		this.salaId = salaId;
		this.dataReserva = dataReserva;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.reservadoPor = reservadoPor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public String getReservadoPor() {
		return reservadoPor;
	}

	public void setReservadoPor(String reservadoPor) {
		this.reservadoPor = reservadoPor;
	}
}
