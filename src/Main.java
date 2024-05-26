import handler.LiftService;
import model.ExternalRequest;
import model.InternalRequest;
import static model.Direction.*;

public class Main {
    public static void main(String[] args) {
        try {
            LiftService liftService = new LiftService();
            liftService.init(5);
            liftService.getLift(new ExternalRequest(UP,2));
            liftService.getLift(new InternalRequest(0,"0"));
            //Thread.sleep(10);
            liftService.getLift(new ExternalRequest(UP,5));
            liftService.getLift(new ExternalRequest(UP,2));
            //Thread.sleep(7);
            liftService.getLift(new ExternalRequest(UP,9));
            //Thread.sleep(8);
            liftService.getLift(new ExternalRequest(UP,1));
            Thread.sleep(4);
            liftService.getLift(new InternalRequest(9,"0"));
            //Thread.sleep(2);
            liftService.getLift(new InternalRequest(1,"0"));
            Thread.sleep(10);
            liftService.getLift(new InternalRequest(10,"0"));
            Thread.sleep(3);
            liftService.getLift(new InternalRequest(5,"0"));
            liftService.getLift(new ExternalRequest(UP,2));
            liftService.getLift(new InternalRequest(0,"0"));
            //Thread.sleep(10);
            liftService.getLift(new ExternalRequest(UP,5));
            liftService.getLift(new ExternalRequest(UP,2));
            //Thread.sleep(7);
            liftService.getLift(new ExternalRequest(UP,9));
            Thread.sleep(8);
            liftService.getLift(new ExternalRequest(UP,1));
            Thread.sleep(4);
            liftService.getLift(new InternalRequest(9,"0"));
            //Thread.sleep(2);
            liftService.getLift(new InternalRequest(1,"0"));
            Thread.sleep(10);
            liftService.getLift(new InternalRequest(10,"0"));
            //Thread.sleep(3);
            liftService.getLift(new InternalRequest(5,"0"));
            liftService.getLift(new ExternalRequest(UP,2));
            liftService.getLift(new InternalRequest(0,"0"));
            Thread.sleep(10);
            liftService.getLift(new ExternalRequest(UP,5));
            liftService.getLift(new ExternalRequest(UP,2));
            Thread.sleep(7);
            liftService.getLift(new ExternalRequest(UP,9));
            Thread.sleep(8);
            liftService.getLift(new ExternalRequest(UP,1));
            Thread.sleep(4);
            liftService.getLift(new InternalRequest(9,"0"));
            Thread.sleep(2);
            liftService.getLift(new InternalRequest(1,"0"));
            Thread.sleep(10);
            liftService.getLift(new InternalRequest(10,"0"));
            Thread.sleep(3);
            liftService.getLift(new InternalRequest(5,"0"));
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}