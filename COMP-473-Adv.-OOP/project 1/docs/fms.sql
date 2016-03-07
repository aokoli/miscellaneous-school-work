-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 09, 2014 at 12:25 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

--
-- Database: `fms`
--
CREATE DATABASE IF NOT EXISTS `fms` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


-- --------------------------------------------------------

--
-- Table structure for table `facility`
--

USE `fms`;
CREATE TABLE IF NOT EXISTS `user` (
  `Id`          int(11)         NOT NULL AUTO_INCREMENT,
  `firstName`   varchar(20)     NOT NULL,
  `lastName`    varchar(20)     NOT NULL,
  `address`     varchar(60)     ,
  `phone`       varchar(20)     ,
  `email`       varchar(60)     ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `tenant` (
  `Id`                  int(11)         NOT NULL AUTO_INCREMENT,
  `userId`              int(11)         NOT NULL,
  `income`              decimal(10,2)   ,
  `previousLocation`    varchar(50)     ,
  `currentEmployer`     varchar(50)     ,
  PRIMARY KEY (`Id`),
  Foreign Key (userId) references `user`(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `employee` (
  `Id`          int(11)         NOT NULL AUTO_INCREMENT,
  `userId`      int(11)         NOT NULL,
  `joinDate`    varchar(20)     ,
  `isActive`    varchar(10)     ,
  `isManager`   varchar(10)     ,
  `salary`      decimal(10,2)   ,
  PRIMARY KEY (`Id`),
  Foreign Key (userId) references `user`(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `facility` (
  `Id` 				int(11) 		NOT NULL AUTO_INCREMENT,
  name				varchar(50) 	NOT NULL,
  `managerId` 		int(11)         NOT NULL,
  `officeaddress` 	varchar(100),
  `size` 			int(10),
  `numParkingSpaces` int(10),
  PRIMARY KEY (`Id`),
  Foreign Key (managerId) references employee(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `unit` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  facilityId 		int(10)			not null,
  isVacant			varchar(5),
  PRIMARY KEY (`Id`),
  Foreign Key (facilityId) references facility(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `unitusage` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  unitId	 		int(10)			not null,
  ownerId				int(10),
  startDate			varchar(10),
  endDate			varchar(10),
  PRIMARY KEY (`Id`),
  Foreign Key (unitId) references unit(Id),
  Foreign Key (ownerId) references tenant(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `request` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  unitId	 		int(10)			not null,
  requestDate		varchar(10),
  maintenanceReqd  varchar(5),
  probDesc			varchar(50),
  requestType		varchar(50),
  requestedByTenant		int(10)     null default 1,
  requestedByEmployee		int(10) null default 1,
  PRIMARY KEY (`Id`),
  Foreign Key (unitId) references unit(Id),
  Foreign Key (requestedByTenant) references Tenant(Id),
  Foreign Key (requestedByEmployee) references Employee(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `Schedule` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  unitId	 		int(10)			not null,
  requestId	 		int(10)			not null,
  technicianId		int(10)         null default 1,
  startDate			varchar(10),
  endDate			varchar(10),
  PRIMARY KEY (`Id`),
  Foreign Key (unitId) references unit(Id),
  Foreign Key (requestId) references request(Id),
  Foreign Key (technicianId) references Employee(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `Maintenance` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  unitId	 		int(10)			not null,
  scheduleId 		int(10)			not null,
  actualStartDate	varchar(10),
  actualEndDate		varchar(10),
  resolveDesc		varchar(50),
  technicianId		int(11)         null default 1,
  isCostPaid        varchar(10),
  serviceCost		decimal(10,2),
  PRIMARY KEY (`Id`),
  Foreign Key (unitId) references unit(Id),
  Foreign Key (scheduleId) references schedule(Id),
  Foreign Key (technicianId) references Employee(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `Inspection` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  unitId	 		int(10)			not null,
  scheduleId 		int(10)			not null,
  actualStartDate	varchar(10),
  actualEndDate		varchar(10),
  technicianId		int(11)         null default 1,
  maintenanceReqd  varchar(5),
  probDesc			varchar(50),
  PRIMARY KEY (`Id`),
  Foreign Key (unitId) references unit(Id),
  Foreign Key (scheduleId) references schedule(Id),
  Foreign Key (technicianId) references Employee(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `Problem` (
  `Id` 				int(11) 		NOT NULL 	AUTO_INCREMENT,
  unitId	 		int(10)			not null,
  probStartDate	DATE,
  resolveDate		Date,
  probDesc		varchar(50),
  IsResolved  varchar(5),
  PRIMARY KEY (`Id`),
  Foreign Key (unitId) references unit(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;





/*
 use heroku_f52445d22fb6fd0;
 
    Set Foreign_Key_Checks = 0;
    truncate Inspection;
    truncate Maintenance;
    truncate UnitUsage;
    truncate Schedule;
    truncate Request;
    truncate Unit;
    truncate Facility;
	truncate Unitusage;
	truncate Problem;
	truncate Employee;
	truncate Tenant;
	truncate User;

    Set Foreign_Key_Checks = 1;

    INSERT INTO `fms`.`user` (`Id`, `firstName`, `lastName`, `address`, `phone`, `email`) VALUES
    							(1, 'DefaultNull', 'DefaultNull', 'DefaultNull', 'DefaultNull', 'DefaultNull');

    INSERT INTO `fms`.`tenant` (`Id`, `userId`, `income`, `previousLocation`, `currentEmployer`) VALUES
    						(1, 1, '0.00', 'DefaultNull', 'DefaultNull');

    INSERT INTO `fms`.`employee` (`Id`, `userId`, `joinDate`, `isActive`, `isManager`, `salary`) VALUES
    								('1', '1', 'DefaultNull', 'false', 'false', '0.00');


    
    Set Foreign_Key_Checks = 0;
    drop table if exists User;
    drop table Tenant;
    drop table Employee;
    drop table Inspection;
    drop table Maintenance;
    drop table Schedule;
    drop table Request;
    drop table Unit;
    drop table Facility;
	drop table Unitusage;
	drop table Problem;
    Set Foreign_Key_Checks = 1;

 */

 use heroku_f52445d22fb6fd0;


 SELECT * FROM heroku_f52445d22fb6fd0.facility;

  mysql://b92af8aa0ea2f0:78e167f6@

  username:  b92af8aa0ea2f0
  password:  78e167f6
  host:   us-cdbr-east-05.cleardb.net



































