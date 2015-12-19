CREATE TABLE `bids` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `ItemId` int(11) NOT NULL,
  `BidValue` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `itemFK_idx` (`ItemId`),
  KEY `userFK_idx` (`UserId`),
  CONSTRAINT `itemFK` FOREIGN KEY (`ItemId`) REFERENCES `items` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userFK` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `items` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ItemCode` varchar(45) DEFAULT NULL,
  `ShortDesc` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `CurrentBid` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) DEFAULT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `ContactNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `auction`.`items` 
ADD INDEX `CurrentBidFK_idx` (`CurrentBid` ASC);
ALTER TABLE `auction`.`items` 
ADD CONSTRAINT `CurrentBidFK`
  FOREIGN KEY (`CurrentBid`)
  REFERENCES `auction`.`bids` (`Id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


