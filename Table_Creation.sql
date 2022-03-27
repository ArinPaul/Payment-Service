CREATE DATABASE PAYMENTSERVICE;

USE PAYMENTSERVICE;

CREATE TABLE loan_payment_schedule
(
    id INT PRIMARY KEY,
    loan_application_id BIGINT NOT NULL,
    emi FLOAT(10),
    due_date DATE,
    payment_status  VARCHAR(20),
    due_amount FLOAT(10),
    principal_amount FLOAT(10),
    intrest_amount FLOAT(10)
);


CREATE TABLE loan_payment
(
    id INT PRIMARY KEY,
    loan_application_id BIGINT NOT NULL,
    paid_amount INT,  
    payment_utr_id VARCHAR(15),
    payment_mode VARCHAR(10),
    payment_date TIMESTAMP, 
    payment_status  VARCHAR(10),
    failure_reason VARCHAR(10)
);

CREATE TABLE loan_disbursal
(
    id INT PRIMARY KEY,
    loan_application_id BIGINT NOT NULL,
    partner_id INT,  
    bank_account_number BIGINT,
    amount DOUBLE PRECISION,
    disbursal_date TIMESTAMP, 
    disbursal_utr_id  VARCHAR(20) 
);

