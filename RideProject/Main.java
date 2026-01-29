import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        RideShareService service = new RideShareService();

        while (true) {
            System.out.println("\n=== Ride Sharing App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();
                System.out.print("Role (driver/passenger): ");
                String role = sc.nextLine();
                System.out.print("License (only driver): ");
                String lic = sc.nextLine();

                service.register(new User(name,email,pass,role,lic));
            }

            else if (choice == 2) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                User user = service.login(email, pass);

                if (user == null) {
                    System.out.println("Invalid login!");
                    continue;
                }

                while (true) {
                    System.out.println("\n=== Home ===");
                    if (user.getRole().equalsIgnoreCase("driver"))
                        System.out.println("1. Create Ride");
                    else
                        System.out.println("1. Book Ride");

                    System.out.println("2. View Rides");
                    System.out.println("3. Logout");

                    int op = sc.nextInt();

                    if (op == 1 && user.getRole().equalsIgnoreCase("driver")) {
                        System.out.print("Source: "); sc.nextLine();
                        String s = sc.nextLine();
                        System.out.print("Destination: ");
                        String d = sc.nextLine();
                        System.out.print("Seats: ");
                        int seats = sc.nextInt();
                        System.out.print("Fare: ");
                        double fare = sc.nextDouble();

                        service.createRide(new Ride(0,s,d,seats,seats,fare,user.getUser_id()));
                    }
                    else if (op == 1) {
                        System.out.print("Ride ID: ");
                        int rideId = sc.nextInt();
                        System.out.print("Seats: ");
                        int seats = sc.nextInt();
                        service.bookRide(user.getUser_id(), rideId, seats);
                    }
                    else if (op == 2) {
                        service.viewRides();
                    }
                    else if (op == 3) {
                        System.out.println("Logged out");
                        break;
                    }
                }
            }
            else {
                System.out.println("Exiting App");
                break;
            }
        }
    }
}
