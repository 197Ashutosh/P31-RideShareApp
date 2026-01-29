import java.sql.*;

public class RideShareService {

    public void register(User user) throws Exception {

        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Invalid Email Format!");
            return;
        }

        Connection con = DBConnection.getConnection();

        PreparedStatement checkEmail = con.prepareStatement("SELECT * FROM users WHERE email=?");
        checkEmail.setString(1, user.getEmail());
        ResultSet rs = checkEmail.executeQuery();
        if (rs.next()) {
            System.out.println("Email already registered!");
            con.close();
            return;
        }

        if (user.getRole().equalsIgnoreCase("driver")) {
            if (user.getLicense() == null || !user.getLicense().matches("[A-Z]{2}[0-9]{5}")) {
                System.out.println("Invalid License Format! Example: DL12345");
                con.close();
                return;
            }
        }

        String sql = "INSERT INTO users(name,email,password,role,license) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        ps.setString(5, user.getLicense());
        ps.executeUpdate();

        System.out.println("Registration successful");
        con.close();
    }

    public User login(String email, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User u = new User(
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getString("license")
            );
            u.setUser_id(rs.getInt("user_id"));
            con.close();
            return u;
        }
        con.close();
        return null;
    }

    public void createRide(Ride ride) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO rides(source,destination,total_seat,available_seats,fare,driver_id) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ride.getSource());
        ps.setString(2, ride.getDestination());
        ps.setInt(3, ride.getTotal_seat());
        ps.setInt(4, ride.getAvailable_seats());
        ps.setDouble(5, ride.getFare());
        ps.setInt(6, ride.getUser_id());
        ps.executeUpdate();
        System.out.println("Ride created successfully");
        con.close();
    }

    public void viewRides() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM rides");

        while (rs.next()) {
            System.out.println("RideID: " + rs.getInt("ride_id") +
                " | " + rs.getString("source") + " -> " + rs.getString("destination") +
                " | Seats: " + rs.getInt("available_seats") +
                " | Fare: " + rs.getDouble("fare"));
        }
        con.close();
    }

    public void bookRide(int userId, int rideId, int seats) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT available_seats FROM rides WHERE ride_id=?");
        ps.setInt(1, rideId);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt("available_seats") >= seats) {
            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO bookings(user_id,ride_id,seats_booked) VALUES(?,?,?)");
            ps2.setInt(1, userId);
            ps2.setInt(2, rideId);
            ps2.setInt(3, seats);
            ps2.executeUpdate();

            PreparedStatement ps3 = con.prepareStatement(
                "UPDATE rides SET available_seats=available_seats-? WHERE ride_id=?");
            ps3.setInt(1, seats);
            ps3.setInt(2, rideId);
            ps3.executeUpdate();

            System.out.println("Booking successful");
        } else {
            System.out.println("Seats not available");
        }
        con.close();
    }
}
