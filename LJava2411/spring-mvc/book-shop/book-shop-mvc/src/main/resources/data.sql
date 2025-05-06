create database shop_book;
use shop_book;
CREATE TABLE products (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          book_title VARCHAR(255),
                          author VARCHAR(255),
                          page_count INT,
                          publisher VARCHAR(255),
                          publication_year INT,
                          genre VARCHAR(100),
                          price DOUBLE,
                          discount DOUBLE,
                          stock_quantity INT,
                          description TEXT
);


INSERT INTO products (book_title, author, page_count, publisher, publication_year, genre, price, discount, stock_quantity, description)
VALUES
    ('Clean Code', 'Robert C. Martin', 464, 'Prentice Hall', 2008, 'Programming', 45.99, 0.10, 100, 'A Handbook of Agile Software Craftsmanship'),
    ('Effective Java', 'Joshua Bloch', 416, 'Addison-Wesley', 2018, 'Programming', 54.99, 0.15, 50, 'Best practices for the Java platform'),
    ('The Pragmatic Programmer', 'Andrew Hunt', 352, 'Addison-Wesley', 1999, 'Programming', 39.99, 0.05, 75, 'Journey to mastery in software development');
