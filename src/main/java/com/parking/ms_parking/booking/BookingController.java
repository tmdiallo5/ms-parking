package com.parking.ms_parking.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("booking")
public class BookingController {
    private final BookingService bookingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBooking(@RequestBody Booking booking) {
        this.bookingService.createBooking(booking);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getAllBookings() {
        return this.bookingService.getAllBookings();
    }
}
