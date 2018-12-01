-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema TeachMeDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TeachMeDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TeachMeDB` DEFAULT CHARACTER SET utf8 ;
USE `TeachMeDB` ;

-- -----------------------------------------------------
-- Table `TeachMeDB`.`LOCATION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDB`.`LOCATION` ;

CREATE TABLE IF NOT EXISTS `TeachMeDB`.`LOCATION` (
  `ID` INT NOT NULL,
  `CITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TeachMeDB`.`COURSE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDB`.`COURSE` ;

CREATE TABLE IF NOT EXISTS `TeachMeDB`.`COURSE` (
  `CODE` VARCHAR(4) NOT NULL,
  `TITLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CODE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TeachMeDB`.`SESSION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDB`.`SESSION` ;

CREATE TABLE IF NOT EXISTS `TeachMeDB`.`SESSION` (
  `ID` INT NOT NULL,
  `START_DATE` DATE NOT NULL,
  `END_DATE` DATE NOT NULL,
  `MAX` INT NULL,
  `LOCATION_ID` INT NOT NULL,
  `COURSE_CODE` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ID`, `LOCATION_ID`, `COURSE_CODE`),
  CONSTRAINT `fk_SESSION_LOCATION1`
    FOREIGN KEY (`LOCATION_ID`)
    REFERENCES `TeachMeDB`.`LOCATION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SESSION_COURSE1`
    FOREIGN KEY (`COURSE_CODE`)
    REFERENCES `TeachMeDB`.`COURSE` (`CODE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_SESSION_LOCATION1_idx` ON `TeachMeDB`.`SESSION` (`LOCATION_ID` ASC);

CREATE INDEX `fk_SESSION_COURSE1_idx` ON `TeachMeDB`.`SESSION` (`COURSE_CODE` ASC);


-- -----------------------------------------------------
-- Table `TeachMeDB`.`CLIENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDB`.`CLIENT` ;

CREATE TABLE IF NOT EXISTS `TeachMeDB`.`CLIENT` (
  `ID` INT NOT NULL,
  `LASTNAME` VARCHAR(45) NOT NULL,
  `FIRSTNAME` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(45) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TeachMeDB`.`RESERVATION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDB`.`RESERVATION` ;

CREATE TABLE IF NOT EXISTS `TeachMeDB`.`RESERVATION` (
  `SESSION_ID` INT NOT NULL,
  `CLIENT_ID` INT NOT NULL,
  PRIMARY KEY (`SESSION_ID`, `CLIENT_ID`),
  CONSTRAINT `fk_SESSION_has_CLIENT_SESSION1`
    FOREIGN KEY (`SESSION_ID`)
    REFERENCES `TeachMeDB`.`SESSION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SESSION_has_CLIENT_CLIENT1`
    FOREIGN KEY (`CLIENT_ID`)
    REFERENCES `TeachMeDB`.`CLIENT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_SESSION_has_CLIENT_CLIENT1_idx` ON `TeachMeDB`.`RESERVATION` (`CLIENT_ID` ASC);

CREATE INDEX `fk_SESSION_has_CLIENT_SESSION1_idx` ON `TeachMeDB`.`RESERVATION` (`SESSION_ID` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;