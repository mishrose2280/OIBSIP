
# OIBSIP Java Development - Task 3
## ATM Interface

### Project Description
A console-based ATM simulation developed using Core Java and Object-Oriented Programming concepts.

### Features
- User authentication using User ID and PIN
- Maximum three login attempts
- Balance inquiry
- Cash deposit
- Cash withdrawal with balance validation
- Money transfer between accounts
- Transaction history
- Exit option

### Technologies Used
- Java
- OOP Concepts
- ArrayList
- HashMap
- LocalDateTime
- Scanner

### Sample Login Credentials

| User ID | PIN | Initial Balance |
|---------|-----|-----------------|
| 1001 | 1234 | Rs. 10,000 |
| 1002 | 5678 | Rs. 5,000 |

### How to Run

1. Install JDK.
2. Open the project in VS Code.
3. Open the terminal inside the project folder.
4. Compile the Java files:

javac -d out src/*.java

5. Run the application:

java -cp out Main

### Concepts Demonstrated
- Classes and Objects
- Encapsulation
- Constructors
- ArrayList
- HashMap
- Conditional Statements
- Loops
- Methods
- Exception Handling

### Note
This is an educational simulation. Account data and
transaction history are stored in memory and reset
when the application is restarted.

