Digital Library API
========
Web-service that transfers the library to electronic accounting of books.

Librarians have the ability to register new readers, lend them books, and release books (after the reader returns the book back to the library). Also, librarians can add new books to the system, edit existing ones or delete them.

Readers can access all available books sorted by release year. They can also search for the desired book by the initial letters of the book name.
It also takes into account the delay of the book by the reader. If more than 10 days have passed since the book was accepted by the reader, it will automatically be highlighted in red on the user's page.

Running the application
========
System requirments: you must have docker and docker compose installed on your computer, ports 5432 and 8080 must be expoded.

Open Terminal.

Change the current working directory to the location where you want the cloned the project directory.
 
Enter the following command to clone the project to the selected directory:

    git clone https://github.com/AglayaSukhobskaya/digital_library.git

Go to the project root directory 'digital_library'.

Run the application with the following command:

    sudo docker compose up -d

Now the application is running.

Open one of these links in any brouser you have:

http://localhost:8080/books - list of books

http://localhost:8080/people - list of people

Technology Stack
========
Java 17, Spring Boot 3.3.2, Maven, Spring MVC, Spring Data JPA, PostgreSQL, Thymeleaf
