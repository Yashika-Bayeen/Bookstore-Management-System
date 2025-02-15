# Bookstore Management System

## Overview
The **Bookstore Management System** is a Java-based desktop application that helps manage a bookstore. It allows users to add books, issue them to students, and track returns efficiently using a MySQL database.

## Features
- **User Authentication**: Secure login system.
- **Add Books**: Store book details such as name, author, description, and price.
- **Issue Books**: Assign books to students with tracking details.
- **Return Books**: Manage book returns and update records.
- **View Records**: Display books, issued books, and returned books in a tabular format.

## Technologies Used
- **Java (AWT, Swing)**: For GUI-based desktop application.
- **MySQL**: For database management.
- **JDBC**: To connect Java with MySQL.

## Database Setup
1. Create a MySQL database:
   ```sql
   CREATE DATABASE bookstore;
   USE bookstore;
   ```
2. Create required tables:
   ```sql
   CREATE TABLE books (
       book_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       description TEXT,
       price DECIMAL(10,2) NOT NULL
   );
   
   CREATE TABLE issued_books (
       issue_id INT AUTO_INCREMENT PRIMARY KEY,
       book_id INT,
       student_id VARCHAR(50),
       student_name VARCHAR(255),
       contact_no VARCHAR(20),
       issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
   );
   
   CREATE TABLE returned_books (
       return_id INT AUTO_INCREMENT PRIMARY KEY,
       book_id INT,
       student_id VARCHAR(50),
       student_name VARCHAR(255),
       return_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
   );
   ```

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/bookstore-management.git
   cd bookstore-management
   ```
2. Install MySQL and configure the database as mentioned above.
3. Update database credentials in the Java code:
   ```java
   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "yourpassword");
   ```
4. Compile and run the Java application:
   ```sh
   javac project.java
   java project
   ```

## Usage
- Run the application.
- Log in using your credentials.
- Add books to the database.
- Issue books to students.
- Manage book returns.
- View stored records.

## Contributing
Feel free to contribute by submitting pull requests. Make sure to follow best coding practices and add proper documentation.

## License
This project is licensed under the MIT License.

