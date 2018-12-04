-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TeachMeDb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `TeachMeDb` ;

-- -----------------------------------------------------
-- Schema TeachMeDb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TeachMeDb` ;
USE `TeachMeDb` ;

-- -----------------------------------------------------
-- Table `TeachMeDb`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDb`.`Location` ;

CREATE TABLE IF NOT EXISTS `TeachMeDb`.`Location` (
  `idLocation` INT NOT NULL AUTO_INCREMENT,
  `City` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLocation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TeachMeDb`.`Course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDb`.`Course` ;

CREATE TABLE IF NOT EXISTS `TeachMeDb`.`Course` (
  `CODE` VARCHAR(4) NOT NULL,
  `TITLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CODE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TeachMeDb`.`Course_Session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDb`.`Course_Session` ;

CREATE TABLE IF NOT EXISTS `TeachMeDb`.`Course_Session` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Start_date` DATE NOT NULL,
  `End_date` DATE NOT NULL,
  `Max` INT NULL,
  `Location_idLocation` INT NOT NULL,
  `Course_CODE` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_Course_Session_Location`
    FOREIGN KEY (`Location_idLocation`)
    REFERENCES `TeachMeDb`.`Location` (`idLocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Course_Session_Course1`
    FOREIGN KEY (`Course_CODE`)
    REFERENCES `TeachMeDb`.`Course` (`CODE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Course_Session_Location_idx` ON `TeachMeDb`.`Course_Session` (`Location_idLocation` ASC) VISIBLE;

CREATE INDEX `fk_Course_Session_Course1_idx` ON `TeachMeDb`.`Course_Session` (`Course_CODE` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `TeachMeDb`.`CLIENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TeachMeDb`.`CLIENT` ;

CREATE TABLE IF NOT EXISTS `TeachMeDb`.`CLIENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Lastname` VARCHAR(45) NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Course_Session_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_CLIENT_Course_Session1`
    FOREIGN KEY (`Course_Session_ID`)
    REFERENCES `TeachMeDb`.`Course_Session` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CLIENT_Course_Session1_idx` ON `TeachMeDb`.`CLIENT` (`Course_Session_ID` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `TeachMeDb`.`Location`
-- -----------------------------------------------------
START TRANSACTION;
USE `TeachMeDb`;
INSERT INTO `TeachMeDb`.`Location` (`idLocation`, `City`) VALUES (1, 'Belfort');
INSERT INTO `TeachMeDb`.`Location` (`idLocation`, `City`) VALUES (2, 'Montbeliard');
INSERT INTO `TeachMeDb`.`Location` (`idLocation`, `City`) VALUES (3, 'Hericourt');
INSERT INTO `TeachMeDb`.`Location` (`idLocation`, `City`) VALUES (4, 'Valdoie');
INSERT INTO `TeachMeDb`.`Location` (`idLocation`, `City`) VALUES (5, 'Paris');
INSERT INTO `TeachMeDb`.`Location` (`idLocation`, `City`) VALUES (6, 'Marseille');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TeachMeDb`.`Course`
-- -----------------------------------------------------
START TRANSACTION;
USE `TeachMeDb`;
INSERT INTO `TeachMeDb`.`Course` (`CODE`, `TITLE`) VALUES ('INF1', 'Informatique Debutant');
INSERT INTO `TeachMeDb`.`Course` (`CODE`, `TITLE`) VALUES ('INF2', 'Informatique Confirme');
INSERT INTO `TeachMeDb`.`Course` (`CODE`, `TITLE`) VALUES ('INF3', 'Informatique expert');
INSERT INTO `TeachMeDb`.`Course` (`CODE`, `TITLE`) VALUES ('MAT1', 'Mathématiques bases');
INSERT INTO `TeachMeDb`.`Course` (`CODE`, `TITLE`) VALUES ('MAT2', 'Mathématique confirme');
INSERT INTO `TeachMeDb`.`Course` (`CODE`, `TITLE`) VALUES ('MAT3', 'Mathématique Expert');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TeachMeDb`.`Course_Session`
-- -----------------------------------------------------
START TRANSACTION;
USE `TeachMeDb`;
INSERT INTO `TeachMeDb`.`Course_Session` (`ID`, `Start_date`, `End_date`, `Max`, `Location_idLocation`, `Course_CODE`) VALUES (1, '2019-01-07', '2019-01-11', 15, 1, 'INF1');
INSERT INTO `TeachMeDb`.`Course_Session` (`ID`, `Start_date`, `End_date`, `Max`, `Location_idLocation`, `Course_CODE`) VALUES (2, '2019-01-14', '2019-01-18', 10, 1, 'INF2');
INSERT INTO `TeachMeDb`.`Course_Session` (`ID`, `Start_date`, `End_date`, `Max`, `Location_idLocation`, `Course_CODE`) VALUES (3, '2019-01-21', '2019-01-25', 5, 1, 'INF3');
INSERT INTO `TeachMeDb`.`Course_Session` (`ID`, `Start_date`, `End_date`, `Max`, `Location_idLocation`, `Course_CODE`) VALUES (4, '2019-01-08', '2019-01-11', 15, 2, 'MAT1');
INSERT INTO `TeachMeDb`.`Course_Session` (`ID`, `Start_date`, `End_date`, `Max`, `Location_idLocation`, `Course_CODE`) VALUES (5, '2019-01-14', '2019-01-18', 10, 2, 'MAT2');
INSERT INTO `TeachMeDb`.`Course_Session` (`ID`, `Start_date`, `End_date`, `Max`, `Location_idLocation`, `Course_CODE`) VALUES (6, '2019-01-21', '2019-01-25', 5, 2, 'MAT3');

COMMIT;