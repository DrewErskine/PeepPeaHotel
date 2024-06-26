package com.peep.pea.hotel.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peep.pea.hotel.data.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}