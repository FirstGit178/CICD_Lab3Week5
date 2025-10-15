package ie.atu.cicd_lab3_week5.controller;


import ie.atu.cicd_lab3_week5.model.Passenger;
import ie.atu.cicd_lab3_week5.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service;             //constructor DI

    public PassengerController(PassengerService service) { this.service = service; }

    @GetMapping("/{id}")
    public ResponseEntity<List<Passenger>> getAll() { return ResponseEntity.ok(service.findAll()) {

    }
}
