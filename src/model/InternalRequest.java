package model;

public class InternalRequest extends Request{
    int destFloor;
    String elevatorId;

    public InternalRequest(int destFloor, String elevatorId) {
        this.destFloor = destFloor;
        this.elevatorId = elevatorId;
    }

    public int getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(int destFloor) {
        this.destFloor = destFloor;
    }

    public String getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(String elevatorId) {
        this.elevatorId = elevatorId;
    }
}
