package com.parking.ms_parking.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public Set<BookingDto> getAllBookings() {
        return this.bookingService.getAllBookings();
    }
    @GetMapping(path = "{id}")
    public BookingDto rechercheByID(@PathVariable int id) {
       return this.bookingService.rechercheByID(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}")
    public Booking updateBooking(@PathVariable int id, @RequestBody Booking booking) {
           return this.bookingService.updateBooking(id, booking);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void deleteBooking(@PathVariable int id) {
        this.bookingService.deleteBooking(id);
    }
}
