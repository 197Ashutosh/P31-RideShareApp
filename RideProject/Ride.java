public class Ride {
    private int ride_id;
    private String source;
    private String destination;
    private int total_seat;
    private int available_seats;
    private double fare;
    private int user_id;

    public Ride(int ride_id, String source, String destination, int total_seat, int available_seats, double fare, int user_id) {
        this.ride_id = ride_id;
        this.source = source;
        this.destination = destination;
        this.total_seat = total_seat;
        this.available_seats = available_seats;
        this.fare = fare;
        this.user_id = user_id;
    }

    public int getRide_id() { return ride_id; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getTotal_seat() { return total_seat; }
    public int getAvailable_seats() { return available_seats; }
    public double getFare() { return fare; }
    public int getUser_id() { return user_id; }
}
