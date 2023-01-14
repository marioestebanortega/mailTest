import org.junit.Test;
import java.time.Duration;

public class SolutionTest {

    private void sendPerType(TIME_RULE type, String user, NotificationService service) {
        try {
            System.out.println("Test With rejects-----------------------------------------------------------------------");

            // Send 2 messages
            service.send(type, user, "state 1, " + user);
            service.send(type, user, "state 2, " + user);

            // try to send other message , the idea is this is rejected
            service.send(type, user, "state 3, " + user);
        } catch (Exception e) {
            assert false;
        }
    }

    //test with diferents users and diferents messages
    //The Idea is when this is sending the 3 message the system should be reject this
    @Test
    public void testSend_exceedsLimit() {
        try {
            NotificationServiceImpl service = new NotificationServiceImpl(new Gateway());
            sendPerType(TIME_RULE .STATE, "user1", service);
            sendPerType(TIME_RULE .STATE, "user2", service);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testSend_withinLimit() {
        try {
            System.out.println("Test Waiting a minute to send the next message-----------------------------------------------------------------------");
            NotificationServiceImpl service = new NotificationServiceImpl(new Gateway());

            //  Send 2 messages
            service.send(TIME_RULE .STATE, "user", "state 1");
            service.send(TIME_RULE .STATE, "user", "state 2");

            // Waiting a minute
            System.out.println("---Waiting a minute to send the next message");
            Thread.sleep(Duration.ofMinutes(1).toMillis());

            // try to send a new message after a minute
            service.send(TIME_RULE .STATE, "user", "state 3");
        } catch (Exception e) {
            assert false;
        }
    }
}