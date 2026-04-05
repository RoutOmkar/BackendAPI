## Project Overview

This is a backend project built using Spring Boot
It manages financial records like income and expenses
It includes user login and role-based access system

## Features

User Features
User Registration
User Login
Email-based authentication
User status management


Role-Based Access
ADMIN → Full access
ANALYST → Can add and view records
VIEWER → Can only view (cannot add records)


Financial Features
Add financial records
View records of a user
Categorize transactions
Track by date


Category Features
Categories like Food, Salary, Shopping
Each category linked to INCOME or EXPENSE


## Important Note (Role Setup)
You must insert roles manually in database before running the project
INSERT INTO role (role_name) VALUES ('ADMIN');
INSERT INTO role (role_name) VALUES ('ANALYST');
INSERT INTO role (role_name) VALUES ('VIEWER');


## Technologies Used
Java
Spring Boot
Spring Data JPA
MySQL
Hibernate

## API Endpoints

Register User

POST /auth/register

Example Body:
{
"name": "Omkar",
"email": "omkar@gmail.com",
"password": "1234",
"role": "ADMIN"
}

Login User

POST /auth/login

Example Body:
{
"email": "omkar@gmail.com
",
"password": "1234"
}

 Financial APIs

Add Financial Record

POST /finance/add/{userId}

Example:

POST /finance/add/1

Body:
{
"type": "INCOME",
"amount": 5000,
"description": "Salary",
"transactionDate": "2026-04-05",
"categoryId": 1
}

Get User Records
GET /finance/user/{userId}
## Role Testing 

ADMIN → Can add records
ANALYST → Can add records
VIEWER → Cannot add records

## Database Tables

user
role
user_role
category
financial_records

## Sample Category Data

INSERT INTO category (name, type, created_at) VALUES ('Salary', 'INCOME', NOW());
INSERT INTO category (name, type, created_at) VALUES ('Food', 'EXPENSE', NOW());

## Important Points

Password is stored in plain text (can be improved using BCrypt)
Role-based access is implemented manually
Category must exist before adding records

