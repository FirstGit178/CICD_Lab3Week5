package ie.atu.cicd_lab3_week5.controller;


import ie.atu.cicd_lab3_week5.model.Passenger;
import ie.atu.cicd_lab3_week5.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    // Constructor Based Dependency Injection
    private final PassengerService service;             //constructor DI

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    //get request for a list of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // get request for id of passenger
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getOne(@PathVariable String id) {
        Optional<Passenger> maybe = service.findById(id);
        if (maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // post method to create new passenger
    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p) {
        Passenger created = service.create(p);
        return ResponseEntity
                .created(URI.create("/api/passengers/" + created.getPassengerId()))
                .body(created);
    }

    // put method to update passengers details
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Passenger>> updatePassenger(@Valid @RequestBody Passenger update, @PathVariable String id)
               throws Exception {
        update.setPassengerId(id);
        Optional<Passenger> passengerFound = service.findById(id);

        if (passengerFound.isPresent()) {
            Passenger passengerUpdated = service.update(update);
            return ResponseEntity.ok(passengerFound);     // Passenger update success
        } else {
            return ResponseEntity.notFound().build();   // Passenger not found
        }

    }

    // delete method to delete passenger details
    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> deletePassenger(@PathVariable String id) {
        service.findById(id);
        Optional<Passenger> passengerFound = service.findById(id);

        if(passengerFound.isPresent()) {
            Passenger passengerDeleted = service.deletePassenger(passengerFound.get());
            return ResponseEntity.ok(passengerDeleted);      // Passenger deleted success
        }
        else{
            return ResponseEntity.notFound().build();       // passenger not found
        }
    }
}

