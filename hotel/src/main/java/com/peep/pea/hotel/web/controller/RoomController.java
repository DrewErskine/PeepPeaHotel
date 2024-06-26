package com.peep.pea.hotel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peep.pea.hotel.data.repository.RoomRepository;
import com.peep.pea.hotel.service.RoomReservationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final RoomReservationService roomReservationService;

    public RoomController(RoomRepository roomRepository, RoomReservationService roomReservationService) {
        this.roomRepository = roomRepository;
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public String getRooms(Model model) {
        model.addAttribute("rooms", this.roomRepository.findAll());
        return "room-list";
    }

    @GetMapping("/reserved")
    public String getReservedRooms(@RequestParam(required = false) String date, Model model) {
        model.addAttribute("reservations", roomReservationService.getRoomReservationsForDate(date));
        return "reserved-room-list";
    }
}
