CREATE DATABASE IF NOT EXISTS JollyRogerDB DEFAULT CHARACTER SET UTF8;
USE JollyRogerDB;

CREATE TABLE IF NOT EXISTS Users (ID int NOT NULL AUTO_INCREMENT, First_Name varchar(20) NOT NULL, Last_Name varchar(20) NOT NULL, Birth_Day datetime NOT NULL, Phone_Number int NOT NULL, PRIMARY KEY (ID));

CREATE TABLE IF NOT EXISTS News (ID int NOT NULL AUTO_INCREMENT, Author_ID int NOT NULL, Title varchar(100) NOT NULL, Description varchar(300) NOT NULL, Text varchar(2000) NOT NULL, Date_Time datetime NOT NULL, primary key (ID), foreign key (Author_ID) references users(ID));