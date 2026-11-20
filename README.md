# FlyXpert: Flight Booking System

A desktop flight booking app built in Java with JavaFX. Passengers search flights, pick seats on a seat map, add passenger details and pay by card or PayPal. Admins add, edit and delete flights and manage seat maps.

## Features

**Passengers**
- Sign up and sign in; passwords are stored encoded, not as plain text
- Search flights by airport and date, then choose economy, business or first class (priced at 1×, 2× and 5× the economy fare)
- Pick seats on an interactive seat map and enter passenger details
- Pay by card (3% fee) or PayPal (4% + 5 fixed fee) and get a booking confirmation
- View booking history

**Admins** (demo login `admin` / `admin`)
- Add, edit and delete flights: schedule, airports, price and availability
- Manage the seat map for each flight

## Object-oriented design

| Concept | Where it's used |
|---|---|
| Abstraction and polymorphism | `Validator` is an abstract class; `ValidateName`, `ValidateNumber`, `ValidateAirports` and `ValidateTime` each implement `validateData()` |
| Interfaces | `PaymentMethod` defines `calculateTotalPriceWithFees()`; `Card` and `Paypal` implement their own fee rules |
| Encapsulation | Domain classes (`Flight`, `Seat`, `Passenger`, `Ticket`, `Price`) keep fields private behind getters and setters |
| Separation of concerns | FXML views, controller classes per screen, and file I/O isolated in the `File` package |

Data is stored in plain text files (`Users.txt`, `FlightInformation.txt`, `BookingRecords.txt`), and UI colours and icons come from `FlyXpert.properties`.

## Run it

Requires Java 21 and Maven.

```bash
git clone https://github.com/shaurya123saxena/FlyXpert-Project.git
cd FlyXpert-Project
mvn clean javafx:run
```

## Tests

19 JUnit 5 tests cover input validation, payment fees, class pricing, password encoding and time formatting.

```bash
mvn test
```

Writing them caught a bug: `ValidateTime` crashed on an empty value instead of rejecting it. It now returns `false`.

## Project structure

```
src/main/java/flyxpert/flyxpert/
├── Admin/          admin screens: add, edit, delete flights, seat maps
├── Booking/        booking confirmation
├── File/           reading and writing data files, password encoding
├── Flight/         Flight and Airport models, flight search
├── Passenger/      passenger details
├── Payment/        PaymentMethod, Card, Paypal, Price, Ticket
├── Seat/           seat map and seat types
├── User/           home page, sign in and sign up, booking history
└── Validators/     input validation
src/main/resources/ FXML layouts, CSS and images
src/test/java/      JUnit 5 tests
```

## Limitations

- Password "encryption" is a character shift, fine for a demo but not secure. A real system would hash passwords with bcrypt or Argon2.
- Text files aren't safe for multiple users at once; a database such as SQLite would fix that.

## Contributors

- [Rishi Soni](https://github.com/RishiSoni252004): original repository
- [Shaurya Saxena](https://github.com/shaurya123saxena): unit tests, validation fix, build and documentation updates
