//In this enum you can add the rules
public enum TIME_RULE {

    STATE(1L * 60 * 1000, 2),// 2 notifications per minute
    NEWS(24L * 60 * 60 * 1000, 1),// 1 notifications per day
    MARKETING(1L * 60 * 60 * 1000, 3); // 3 per hour

    private long time;

    private int quantity;

    public long getTime() {
        return time;
    }

    public int getQuantity() {
        return quantity;

    }


    TIME_RULE(long time, int quantity) {
        this.time = time;
        this.quantity = quantity;
    }
}