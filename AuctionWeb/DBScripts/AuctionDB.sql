-- MySQL Script generated by MySQL Workbench
-- 12/19/15 12:38:03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema auction
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema auction
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `auction` DEFAULT CHARACTER SET utf8 ;
USE `auction` ;

-- -----------------------------------------------------
-- Table `auction`.`items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`items` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `ItemCode` VARCHAR(45) NULL DEFAULT NULL,
  `ShortDesc` VARCHAR(45) NULL DEFAULT NULL,
  `Price` VARCHAR(45) NULL DEFAULT NULL,
  `CurrentBid` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `CurrentBidFK_idx` (`CurrentBid` ASC),
  CONSTRAINT `CurrentBidFK`
    FOREIGN KEY (`CurrentBid`)
    REFERENCES `auction`.`bids` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `auction`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`users` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NULL DEFAULT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `ContactNumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `auction`.`bids`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`bids` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `UserId` INT(11) NOT NULL,
  `ItemId` INT(11) NOT NULL,
  `BidValue` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `itemFK_idx` (`ItemId` ASC),
  INDEX `userFK_idx` (`UserId` ASC),
  CONSTRAINT `itemFK`
    FOREIGN KEY (`ItemId`)
    REFERENCES `auction`.`items` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userFK`
    FOREIGN KEY (`UserId`)
    REFERENCES `auction`.`users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
