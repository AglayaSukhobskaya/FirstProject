Digital Library API
========
A web application that simulates the work of a digital library.

The library contains a collection of books. You can get a list of all registered books or perform a simple CRUD operation with each of the books.

Users are registered in the library. You can also add your own user, delete or edit an existing one.

The page of a specific book contains information about whether this book is currently available or is taken by someone. If the book is available, you can assign it to one of the users.
The user's page shows the books he has borrowed. If a book was borrowed more than 10 days ago, it will be highlighted in red.

Running the application
========
System requirments: you must have docker and docker compose installed on your computer, port 8080 must be expoded.

Open Terminal.

Change the current working directory to the location where you want the cloned the project directory.
 
Enter the following command to clone the project to the selected directory:

    git clone https://github.com/AglayaSukhobskaya/digital_library.git

Go to the project root directory 'digital_library'.

Run the application with the following command:

    docker compose up -d

Now the application is running.

Open one of these links in any brouser you have:

http://localhost:8080/books - list of books

http://localhost:8080/people - list of people

Technology Stack
========
Java 17, Spring Boot 3.3.2, Maven, Spring MVC, Spring Data JPA, PostgreSQL, Thymeleaf
