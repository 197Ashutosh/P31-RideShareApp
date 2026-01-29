public class Booking {
    private int user_id;
    private int ride_id;
    private int seats_booked;
    private String license;
    private String vehicle;

    public Booking(int user_id, int ride_id, int seats_booked, String license, String vehicle) {
        this.user_id = user_id;
        this.ride_id = ride_id;
        this.seats_booked = seats_booked;
        this.license = license;
        this.vehicle = vehicle;
    }

    public int getUser_id() { return user_id; }
    public int getRide_id() { return ride_id; }
    public int getSeats_booked() { return seats_booked; }
    public String getLicense() { return license; }
    public String getVehicle() { return vehicle; }
}
