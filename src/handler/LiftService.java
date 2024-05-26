package handler;

import db.InMemoryDb;
import model.*;
import service.LiftSelectionService;
import service.NearestLifSelectionService;

import java.util.HashMap;
import java.util.Map;

public class LiftService {
    Map<String, ElevatorHandler> elevatorHandlerMap;
    InMemoryDb inMemoryDb = InMemoryDb.getInstance();
    LiftSelectionService nearestLifSelectionService = new NearestLifSelectionService();

    public LiftService() {
        this.elevatorHandlerMap = new HashMap<>();
    }

    public void init(int no_of_lifts) {
        for (int i = 0; i < no_of_lifts; i++) {
            String id = String.valueOf(i);
            Elevator elevator = new Elevator(id);
            inMemoryDb.getElevatorMap().put(id, elevator);
            elevatorHandlerMap.put(id, new ElevatorHandler(id));
            new Thread(elevatorHandlerMap.get(id)).start();
        }
    }


    public void getLift(Request request) {
        if (request instanceof ExternalRequest) {
            ExternalRequest externalRequest = (ExternalRequest) request;
            String id = nearestLifSelectionService.chooseLift(externalRequest);
            new Thread(()->elevatorHandlerMap.get(id).handleExternalRequest(externalRequest)).start();
        } else {
            //System.out.println("here");
            InternalRequest internalRequest = (InternalRequest) request;
            new Thread(()->elevatorHandlerMap.get(internalRequest.getElevatorId()).handleInternalRequest(internalRequest)).start();
        }
    }
}
