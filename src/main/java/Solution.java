import java.util.*;


/**
 * In this class is the solution of the test
 */
class Solution {

    /**
     * You can run 
     * @param args
     */
    public static void main(String[] args) {

        NotificationServiceImpl service = new NotificationServiceImpl(new Gateway());

        service.send(TIME_RULE.STATE, "user1", "news 1");

        service.send(TIME_RULE.STATE, "user2", "news 2");

        service.send(TIME_RULE.MARKETING, "user3", "news 3");


    }

}


interface NotificationService {

    void send(TIME_RULE type, String userId, String message);

}



class NotificationServiceImpl implements NotificationService {

    private Gateway gateway;
    private Map<String, Map<TIME_RULE, Long>> lastNotifications = new HashMap<>();
    private Map<String,Map<TIME_RULE, Integer>> limitsPerUser=new HashMap<>();

    public NotificationServiceImpl(Gateway gateway) {

        this.gateway = gateway;
        // this.lastNotificationSent = new HashMap<>();

    }


    @Override
    public void send(TIME_RULE type, String userId, String message) {

        //init the limits through maps
        Map<TIME_RULE, Integer> limits=limitsPerUser.get(userId);
        if(limits==null){
            limits=new HashMap<>();
            limits.put(TIME_RULE.STATE, 0);
            limits.put(TIME_RULE.MARKETING, 0);
            limits.put(TIME_RULE.NEWS, 0);
            limitsPerUser.put(userId,limits);
        }

        //Get actual time
        long currentTime = System.currentTimeMillis();

        //This map is in charged to save the las time that the user send some notification
        Map<TIME_RULE, Long> userLastNotification = lastNotifications.get(userId);

        //In this is init the notificactions in the map
        if (userLastNotification == null) {
            userLastNotification = new HashMap<>();
            userLastNotification.put(type, currentTime);
            lastNotifications.put(userId, userLastNotification);
        }

        //add quantity to new sending per user
        limits.put(type, limits.get(type) + 1);

        //get the offset between the current time and the next iteration of sending per user
        Long offSetCurrentTime = currentTime - lastNotifications.get(userId).get(type);

        //If the time pass the offset , it is restarting to queantity
        if (offSetCurrentTime > type.getTime()) {
            limits.put(type, 0);
        } else {
            //after the time is verified , the idea is check the quantity based in the rules of the enum
            if (limits.get(type) > type.getQuantity()) {
                System.out.println("Notification limit reached for user: " + userId + " and type: " + type);
                return;
            }
        }
        // userLastNotificationSent.put(type, currentTime);
        gateway.send(userId, message);
    }


}


/**
 * GGateway to send the message
 */
class Gateway {

    /* already implemented */

    void send(String userId, String message) {

        System.out.println("sending message to user " + userId + " with message:" + message);

    }

}