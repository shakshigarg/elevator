package handler;

import db.InMemoryDb;
import model.Elevator;
import model.ElevatorState;
import model.ExternalRequest;
import model.InternalRequest;

import static model.Direction.DOWN;
import static model.Direction.UP;

public class ElevatorHandler implements Runnable {
    String elevatorId;
    InMemoryDb inMemoryDb = InMemoryDb.getInstance();

    public ElevatorHandler(String elevatorId) {
        this.elevatorId = elevatorId;
    }

    @Override
    public void run() {
        Elevator elevator = inMemoryDb.getElevatorMap().get(elevatorId);
        synchronized (elevator) {
            do {
                while (elevator.getCurrRequests().size() == 0) {
                    try {
                        elevator.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                elevator.start();
            } while (true);
        }
    }

    public void wakeUpIfNeeded() {
        synchronized (inMemoryDb.getElevatorMap().get(elevatorId)) {
            inMemoryDb.getElevatorMap().get(elevatorId).notify();
        }
    }

    public void handleExternalRequest(ExternalRequest externalRequest) {
        System.out.println("Got request : floor " + externalRequest.getSourceFloor() + " direction " + externalRequest.getDirection());
        //System.out.println("thread :"+Thread.currentThread().getName());
        Elevator elevator = inMemoryDb.getElevatorMap().get(elevatorId);

        if (elevator.getElevatorState() == ElevatorState.IDLE) {
            elevator.getCurrRequests().put(externalRequest.getSourceFloor(), true);
            elevator.setDirection(elevator.getCurrentFloor() - externalRequest.getSourceFloor() > 0 ? DOWN : UP);
            elevator.setElevatorState(ElevatorState.WORKING);
        } else {
            if (elevator.getDirection() == UP) {
                if (externalRequest.getDirection() == UP) {
                    if (externalRequest.getSourceFloor() > elevator.getCurrentFloor()) {
                        elevator.getCurrRequests().put(externalRequest.getSourceFloor(), true);
                    } else {
                        elevator.getUpRequests().put(externalRequest.getSourceFloor(), true);
                    }
                } else {
                    elevator.getDownRequests().put(externalRequest.getSourceFloor(), true);
                }
            } else {
                if (externalRequest.getDirection() == DOWN) {
                    if (externalRequest.getSourceFloor() < elevator.getCurrentFloor()) {
                        elevator.getCurrRequests().put(externalRequest.getSourceFloor(), true);
                    } else {
                        elevator.getDownRequests().put(externalRequest.getSourceFloor(), true);
                    }
                } else {
                    elevator.getUpRequests().put(externalRequest.getSourceFloor(), true);
                }
            }
        }
        System.out.println(elevator);
        wakeUpIfNeeded();

    }

    public void handleInternalRequest(InternalRequest internalRequest) {
        System.out.println("Got request : floor " + internalRequest.getDestFloor() + " for elevator " + internalRequest.getElevatorId());
        //System.out.println("thread :"+Thread.currentThread().getName());
        Elevator elevator = inMemoryDb.getElevatorMap().get(elevatorId);

        if (elevator.getElevatorState() == ElevatorState.IDLE) {
            elevator.getCurrRequests().put(internalRequest.getDestFloor(), true);
            elevator.setDirection(elevator.getCurrentFloor() - internalRequest.getDestFloor() > 0 ? DOWN : UP);
            elevator.setElevatorState(ElevatorState.WORKING);
        } else {
            if (elevator.getDirection() == UP) {
                if (internalRequest.getDestFloor() > elevator.getCurrentFloor()) {
                    elevator.getCurrRequests().put(internalRequest.getDestFloor(), true);
                } else {
                    elevator.getDownRequests().put(internalRequest.getDestFloor(), true);
                }
            } else {
                if (internalRequest.getDestFloor() < elevator.getCurrentFloor()) {
                    elevator.getCurrRequests().put(internalRequest.getDestFloor(), true);
                } else {
                    elevator.getUpRequests().put(internalRequest.getDestFloor(), true);
                }
            }
        }
        System.out.println(elevator);
        wakeUpIfNeeded();
    }
}
