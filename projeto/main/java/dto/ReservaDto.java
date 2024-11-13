package dto;

import model.Reserva;

import java.sql.Date;
import java.sql.Time;

public class ReservaDto {
	private Integer id;
	private Integer salaId;
	private String nomeDaSala;
	private Date dataReserva;
	private Time horaInicio;
	private Time horaFim;
	private String reservadoPor;

	public static ReservaDto fromReserva(Reserva reserva) {
		return new ReservaDto(reserva.getId(), reserva.getSalaId(), "", reserva.getDataReserva(),
				reserva.getHoraInicio(), reserva.getHoraFim(), reserva.getReservadoPor());
	}

	public static ReservaDto fromReserva(Reserva reserva, String nomeDaSala) {
		return new ReservaDto(reserva.getId(), reserva.getSalaId(), nomeDaSala, reserva.getDataReserva(),
				reserva.getHoraInicio(), reserva.getHoraFim(), reserva.getReservadoPor());
	}

	public ReservaDto() {
	}

	public ReservaDto(Integer id, Integer salaId, String nomeDaSala, Date dataReserva, Time horaInicio, Time horaFim,
			String reservadoPor) {
		this.id = id;
		this.salaId = salaId;
		this.nomeDaSala = nomeDaSala;
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

	public String getNomeDaSala() {
		return nomeDaSala;
	}

	public void setNomeDaSala(String nomeDaSala) {
		this.nomeDaSala = nomeDaSala;
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
