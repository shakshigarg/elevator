package model;

import utils.Utils;

import java.util.concurrent.ConcurrentHashMap;

import static model.Direction.DOWN;
import static model.Direction.UP;

public class Elevator {
    String id;
    int currentFloor;
    ConcurrentHashMap<Integer, Boolean> currRequests;
    ConcurrentHashMap<Integer, Boolean> upRequests;
    ConcurrentHashMap<Integer, Boolean> downRequests;
    Direction direction;
    ElevatorState elevatorState;

    public Elevator(String id) {
        this.id = id;
        this.currentFloor = 0;
        this.currRequests = new ConcurrentHashMap<>();
        this.upRequests = new ConcurrentHashMap<>();
        this.downRequests = new ConcurrentHashMap<>();
        elevatorState = ElevatorState.IDLE;
        direction = UP;
    }

    public void start() {
        while (currRequests.size() != 0) {
            System.out.println(this);
            elevatorState = ElevatorState.WORKING;
            if (direction == UP) {
                if (currRequests.get(currentFloor) != null && currRequests.get(currentFloor) == true) {
                    System.out.println("Elevator " + id + " on floor " + currentFloor + " opening ");
                    Utils.threadSleep(3);
                    currRequests.remove(currentFloor);
                } else {
                    System.out.println("Elevator " + id + " on floor " + currentFloor + " going " + direction);
                    Utils.threadSleep(3);
                    currentFloor++;
                }
            } else {
                if (currRequests.get(currentFloor) != null && currRequests.get(currentFloor) == true) {
                    System.out.println("Elevator " + id + " on floor " + currentFloor + " opening ");
                    Utils.threadSleep(3);
                    currRequests.remove(currentFloor);
                } else {
                    System.out.println("Elevator " + id + " on floor " + currentFloor + " going " + direction);
                    Utils.threadSleep(3);
                    currentFloor--;
                }
            }
            if (currRequests.size() == 0) {
                if (upRequests.size() != 0 || downRequests.size() != 0) {
                    if (direction == UP) {
                        if (downRequests.size() != 0) {
                            direction = DOWN;
                            currRequests = downRequests;
                            downRequests = new ConcurrentHashMap<>();
                        } else {
                            direction = DOWN;
                            currRequests.put(0, true);
                        }
                    } else {
                        if (upRequests.size() != 0) {
                            direction = UP;
                            currRequests = upRequests;
                            upRequests = new ConcurrentHashMap<>();
                        } else {
                            direction = UP;
                            currRequests.put(10, true);
                        }

                    }
                }
            }
        }
        elevatorState = ElevatorState.IDLE;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public ConcurrentHashMap<Integer, Boolean> getCurrRequests() {
        return currRequests;
    }

    public void setCurrRequests(ConcurrentHashMap<Integer, Boolean> currRequests) {
        this.currRequests = currRequests;
    }

    public ConcurrentHashMap<Integer, Boolean> getUpRequests() {
        return upRequests;
    }

    public void setUpRequests(ConcurrentHashMap<Integer, Boolean> upRequests) {
        this.upRequests = upRequests;
    }

    public ConcurrentHashMap<Integer, Boolean> getDownRequests() {
        return downRequests;
    }

    public void setDownRequests(ConcurrentHashMap<Integer, Boolean> downRequests) {
        this.downRequests = downRequests;
    }

    public void addRequest(Request request) {

    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id='" + id + '\'' +
                ", currentFloor=" + currentFloor +
                ", currRequests=" + currRequests +
                ", upRequests=" + upRequests +
                ", downRequests=" + downRequests +
                ", direction=" + direction +
                ", elevatorState=" + elevatorState +
                '}';
    }

    public boolean canTakeRequest(ExternalRequest externalRequest) {
        if (this.getElevatorState() == ElevatorState.IDLE) {
            return false;
        } else {
            if (this.getDirection() == UP && externalRequest.getDirection() == UP && externalRequest.getSourceFloor() > this.getCurrentFloor() + 1) {

                return true;
            } else {
                if (externalRequest.getDirection() == DOWN && externalRequest.getSourceFloor() < this.getCurrentFloor() - 1) {

                    return true;

                }
            }
        }
        return false;
    }
}
