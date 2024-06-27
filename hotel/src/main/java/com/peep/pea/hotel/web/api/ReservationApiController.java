package com.peep.pea.hotel.web.api;

import com.peep.pea.hotel.data.entity.Reservation;
import com.peep.pea.hotel.data.repository.ReservationRepository;
import com.peep.pea.hotel.web.exception.BadRequestException;
import com.peep.pea.hotel.web.exception.NotFoundException;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import io.micrometer.common.util.StringUtils;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

  private final ReservationRepository reservationRepository;

  public ReservationApiController(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @GetMapping
  public List<Reservation> getAllReservations(@RequestParam(value="date", required = false)String dateString){
    if(StringUtils.isNotBlank(dateString)){
      Date date = new Date(new java.util.Date().getTime());
      return this.reservationRepository.findAllByReservationDate(date);
    }
    return this.reservationRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Reservation addReservation(@RequestBody Reservation reservation){
    return this.reservationRepository.save(reservation);
  }

  @GetMapping("/{id}")
  public Reservation getReservation(@PathVariable("id")long id){
    Optional<Reservation> reservation = this.reservationRepository.findById(id);
    if (reservation.isEmpty()){
      throw new NotFoundException("reservation not found with id: " + id);
    }
    return reservation.get();
  }

  @PutMapping("/{id}")
  public Reservation updateReservation(@PathVariable("id")long id, @RequestBody Reservation reservation){
    if (id != reservation.getId()){
      throw new BadRequestException("id on path must match body");
    }
    return reservationRepository.save(reservation);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteReservation(@PathVariable("id")long id){
    this.reservationRepository.deleteById(id);
  }
}