package com.vratislav.hais.learningspring.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESERVATION_ID")
	private long reservationId;
	@Column(name = "ROOM_ID")
	private long roomId;
	@Column(name = "GUEST_ID")
	private long guestId;
	@Column(name = "RES_DATE")
	private Date resDate;

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getGuestId() {
		return guestId;
	}

	public void setGuestId(long guestId) {
		this.guestId = guestId;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
}
