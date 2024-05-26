package db;



import model.Elevator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDb {
    private static InMemoryDb inMemoryDb = new InMemoryDb();
    Map<String, Elevator> elevatorMap;

    private InMemoryDb() {
       elevatorMap=new HashMap<>();
    }

    public static InMemoryDb getInstance() {
        return inMemoryDb;
    }

    public Map<String, Elevator> getElevatorMap() {
        return elevatorMap;
    }
}
