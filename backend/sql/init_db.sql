CREATE DATABASE java_web;
USE java_web;
CREATE TABLE departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_code VARCHAR(10) NOT NULL UNIQUE,
    department_name VARCHAR(100)
);
CREATE TABLE staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    staff_code VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(50),  -- 'ADMIN', 'DIRECTOR', 'PROFESSOR', 'ASSOCIATE_PROFESSOR', 'ASSISTANT_PROFESSOR'
    department_code VARCHAR(10),
    FOREIGN KEY (department_code) REFERENCES departments(department_code)
);
CREATE TABLE admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    staff_code VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    FOREIGN KEY (staff_code) REFERENCES staff(staff_code)
);
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    joining_year SMALLINT CHECK (joining_year BETWEEN 2000 AND 3000),
    department_code VARCHAR(10),
    FOREIGN KEY (department_code) REFERENCES departments(department_code)
);
