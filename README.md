# Library

TECHNICAL TASK

The local library wants to switch to digital book counting. It is needed to implement a web-application. Librarians should be able to register readers,
lend books to them and release books  (after the reader returns the book to the library).

Entities:
Person (fields: name (unique), year of birth)
Book (fields: name, author, year)

One-to-many relationship between entities. A person can have many books. A book can only belong to one person.

SQL code for creating tables is in /resources/db.sql

Functional:
1. Pages for adding, editing and deleting a person
2. Pages for adding, editing and deleting a book
3. All people list page (people are clickable - redirect to the person page)
4. All books list page (books are clickable - redirect to the book page)
5. Page of a person showing the values of fields of the person and the list of books he has borrowed. If the person has not taken any book, the text: "The person has not yet taken a single book."
6. Page of a book showing the values of fields of the book and the person's name, who took this book. If this book has not been taken by anyone, the text "This
the book is available."
7. On the page of the book, if the book is taken by a person, next to his name there is a button "Release the book". This button is pressed by the librarian when the reader returns this book back to the library. After pressing this button the book again becomes loose and disappears from the list of the person's books.
8. On the page of the book, if the book is free, there is a drop-down list (<select>) with all people and the "Assign the book" button. This button is pressed by the librarian when the reader wants to take this book home. After pressing this button, the book must begin to belong to the selected person and must appear in his list books.
9. Sorting books by year using the key "sort_by_year=true"
10. Paganization for books using the keys "page" (requesting page) and "books_per_page" (number of books displayed per page)
11. Book search page by partial title
12. Automatic book overdue check (more than 10 days)