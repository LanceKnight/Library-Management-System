create database library;
use library;
create table book(
book_id char(10) not null,
title char(255),
primary key (book_id)
);
create table book_authors(
book_id char(10) not null,
author_name char(255) not null,
type int(1),
primary key (book_id, author_name),
foreign key (book_id) references book(book_id)
);
create table library_branch(
branch_id char(2) not null,
branch_name char(255),
address char(255),
primary key (branch_id)
);
create table book_copies(
book_id char(10) not null,
branch_id char(255) not null,
no_of_copies int,
primary key (book_id, branch_id),
foreign key (book_id) references book(book_id),
foreign key (branch_id) references library_branch(branch_id)
);
create table borrower(
card_no int(10) not null auto_increment,
fname char(255),
lname char(255),
address char(255),
phone char(15),
primary key (card_no)
);
create table book_loans(
loan_id int(10) not null auto_increment,
book_id char(10),
branch_id char(2),
card_no int(255),
date_out date,
due_date date,
date_in date default null,
primary key (loan_id),
foreign key (book_id) references book(book_id),
foreign key (branch_id) references library_branch(branch_id),
foreign key (card_no) references borrower(card_no)
);

create table fines(
loan_id int(10) not null,
fine_amt decimal(5,2),
paid boolean,
primary key (loan_id),
foreign key(loan_id) references book_loans(loan_id)
);
show databases;