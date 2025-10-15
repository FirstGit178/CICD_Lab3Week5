package ie.atu.cicd_lab3_week5.service;

import ie.atu.cicd_lab3_week5.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceTest {

    private PassengerService service;

    @BeforeEach
    void setUp() { service = new PassengerService(); }

    @Test
    void createThenFindById() {
        Passenger p = Passenger.builder()
                .passengerId("P1")
                .name("Tom")
                .email("tom@atu.ie")
                .build();

        service.create(p);

        Optional<Passenger> found = service.findById("P1");
        assertTrue(found.isPresent());
        assertEquals("Tom", found.get().getName());
    }

    @Test
    void duplicatedThrows() {
        service.create(Passenger.builder()
                .passengerId("P2")
                .name("Jim")
                .email("jim@atu,ie")
                .build());

        assertThrows(IllegalArgumentException.class, () ->
                service.create(Passenger.builder()
                        .passengerId("P2")
                        .name("Jimmy")
                        .email("jim@ex.com")
                        .build()));
    }
}

