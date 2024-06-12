CREATE TABLE BOOKS (
id varchar(10) PRIMARY KEY,
title TEXT,
subtitle TEXT,
description TEXT,
authors TEXT,
publisher varchar(50),
pages INT,
published_year INT,
image TEXT,
price DOUBLE,
categories TEXT
);

CREATE TABLE USERS (
username varchar(30) primary key,
password varchar(200),
role varchar(30)
);
-------------------------------------------------
--              IMPORTING SOME DATA
-------------------------------------------------

INSERT INTO BOOKS (
ID,
TITLE,
SUBTITLE,
DESCRIPTION,
AUTHORS,
PUBLISHER,
PAGES,
PUBLISHED_YEAR,
IMAGE,
PRICE,
CATEGORIES) VALUES (
3030168778,
'Programming for Computations - Python',
'A Gentle Introduction to Numerical Simulations with Python 3.6',
'This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...',
'Svein Linge, Hans Petter Langtangen',
'Springer',
350,
2020,
'https://www.dbooks.org/img/books/3030168778s.jpg',
30.99,
'Computational science and engineering,Computer science--mathematics,Computer software,Mathematical software,Mathematics,Numeric computing,Numerical analysis'
);

INSERT INTO USERS (username,password,role)values('admin','$2y$10$66W108YB5e2eHHn5Ekl.UOv53uYg5IgRH/NpTlGt2TJWAayIQmFdG','ADMIN');
INSERT INTO USERS (username,password,role)values('customer','$2y$10$R.OmgADsN4Ati8Worj8OpeQttjmFw5cOoTdbhU7wtd5dwvZVktAEe','CUSTOMER');