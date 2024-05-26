package service;

import db.InMemoryDb;
import model.Elevator;
import model.ElevatorState;
import model.ExternalRequest;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static java.lang.Math.*;

public class NearestLifSelectionService implements LiftSelectionService {
    InMemoryDb inMemoryDb = InMemoryDb.getInstance();

    @Override
    public String chooseLift(ExternalRequest externalRequest) {
        Map<String, Elevator> elevatorMap = inMemoryDb.getElevatorMap();
        Optional<String> id = inFloorInPath(elevatorMap,externalRequest);
        if(id.isPresent()){
            return id.get();
        }
        id= getIdle(elevatorMap);
        if(id.isPresent()){
            return id.get();
        }
        return String.valueOf(new Random().nextInt(elevatorMap.size()));
    }

    private Optional<String> getIdle(Map<String, Elevator> elevatorMap) {
        for (Elevator elevator:elevatorMap.values()){
            if(elevator.getElevatorState()== ElevatorState.IDLE){
                return Optional.of(elevator.getId());
            }
        }
        return Optional.empty();
    }

    private Optional<String> inFloorInPath(Map<String, Elevator> elevatorMap, ExternalRequest externalRequest) {
        Optional<String> optionalId=Optional.empty();
        for (Elevator elevator:elevatorMap.values()){
            if(elevator.canTakeRequest(externalRequest)){
                return Optional.of(elevator.getId());
            }
        }
        return Optional.empty();
    }
}
