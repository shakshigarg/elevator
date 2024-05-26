package model;

public class ExternalRequest extends Request{
    Direction direction;
    int sourceFloor;

    public ExternalRequest(Direction direction, int sourceFloor) {
        this.direction = direction;
        this.sourceFloor = sourceFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }
}
