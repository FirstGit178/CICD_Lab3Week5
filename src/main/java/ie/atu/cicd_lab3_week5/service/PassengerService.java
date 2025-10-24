package ie.atu.cicd_lab3_week5.service;

import ie.atu.cicd_lab3_week5.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll() {
        return new ArrayList<>(store);  //defensive copy

    }
    // Find Passenger by ID
    public Optional<Passenger> findById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();

    }

    // Create Passenger
    public Passenger create(Passenger p) {
        if (findById(p.getPassengerId()).isPresent()) {
            throw new IllegalArgumentException("passengerId already exists");
        }
        store.add(p);
        return p;
    }


        // Update Passenger
        public Passenger update(Passenger p) throws Exception {
            Optional<Passenger> passengerFound = findById(p.getPassengerId());
            if(passengerFound.isPresent()){
                Passenger updated = passengerFound.get();
                updated.setName(p.getName());
                updated.setEmail(p.getEmail());
                return p;
            }
            throw new Exception();
    }

        // Delete Passenger
        public Passenger deletePassenger(Passenger passengerFound) {
            if (findById(passengerFound.getPassengerId()).isPresent()){
                store.remove(passengerFound);
                return passengerFound;
            }
            throw new IllegalArgumentException();
        }

}



