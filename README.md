Input File:
id;brand;model;os;price;stock
P01;Samsung;GalaxyS23;Android;800.0;10
P02;Apple;iPhone15;iOS;1000.0;5
P03;Google;Pixel8;Android;700.0;8
P04;Apple;iPhone14;iOS;850.0;12

Expected Output File:
Total unique models: 4
Total inventory value: 28800.0
Top 2 most expensive phones:
P02;Apple;iPhone15;1000.0
P04;Apple;iPhone14;850.0

Requirements & Point Distribution (Total: 70p + 10p default = 80p)
1. OOP Modeling (10p)
Create an interface Valuable with the method double totalValue() (Formula: price * stock).

Create an abstract class Phone that implements Valuable and contains the base fields (id, brand, model, os, price, stock).

Create minimum 2 concrete classes extending Phone (e.g., AndroidPhone and IOSPhone).

2. Text File I/O (5p)
Read the smartphone data from phones.txt into memory.

3. Collections & Data Structures (5p)
Store the parsed objects in a List<Phone>.

4. Lambda & Stream API (10p)
Use Streams exclusively for filtering, sorting, and mapping data in the application. Traditional for loops should be avoided for data manipulation.

5. ExecutorService & Multithreading (15p)
Use an ExecutorService with Callables/Runnables to execute the following tasks concurrently. Use synchronized blocks where necessary to avoid race conditions.

Task 1: Calculate the total inventory value.

Task 2: Find the Top 2 most expensive phones (sorted descending by base price).

Task 3: Wait for Task 1 and Task 2 to finish, then safely write the results into report.txt.

6. TCP Socket Client/Server (15p)
Implement a Server that listens for connections and a Client that sends commands.

Commands:

TOP 2 -> Returns the top 2 most expensive phones.

FILTER <OS> -> Returns all phones matching that exact OS (e.g., FILTER Android).

INVENTORY -> Returns the total inventory value.

Formatting Rule: The Server must return the data on a single line, separating multiple phones with | and properties with ;.

Example:

Client: FILTER iOS

Server: P02;Apple;iPhone15;1000.0 | P04;Apple;iPhone14;850.0

7. SQLite Integration (10p)
Connect to a local inventory.db database.

Create a table named phones if it does not exist.

Insert all parsed phones into the database using PreparedStatement.

