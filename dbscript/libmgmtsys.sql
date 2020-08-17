CREATE DATABASE IF NOT EXISTS `libmgmtsys`;

USE `libmgmtsys`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `author_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `author` */

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` varchar(1000) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`book_id`,`title`,`publisher`,`available`) 
values ('113','Kalil','kstoeckie@hhs.gov',1),('114','Dion','dreynaif@dion.ne.jp',0),
('111','Fiona','funittih@zimbio.com',1),('112','Yank','yleggattii@foxnews.com',1),
('118','Cherye','cjirekij@army.mil',1),('120','Lowrance','lcronein@reference.com',1),
('123','Robina','rpogosianio@usda.gov',1),('124','Gardener','gtinseyip@adobe.com',1),
('125','Philippine','prentoniq@prweb.com',1),('121','Lyman','lscarlanir@hhs.gov',1),
('126','Ruthann','rfernleyis@state.gov',1),('180','Bruce','bradleyiv@google.com',1);

/*Table structure for table `book_author` */

DROP TABLE IF EXISTS `book_author`;

CREATE TABLE `book_author` (
  `author_id` int(10) NOT NULL,
  `book_id` varchar(200) NOT NULL,
  PRIMARY KEY (`author_id`,`book_id`),
  KEY `book_author_id_fk` (`book_id`),
  CONSTRAINT `author_author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `book_author_id_fk` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book_author` */

/*Table structure for table `borrower` */

DROP TABLE IF EXISTS `borrower`;

CREATE TABLE `borrower` (
  `lib_card_id` int(10) NOT NULL AUTO_INCREMENT,
  `bname` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`lib_card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrower` */

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1);

/*Table structure for table `order_details` */

DROP TABLE IF EXISTS `borrow_details`;

CREATE TABLE `borrow_details` (
  `borrow_id` varchar(80) NOT NULL,
  `book_id` varchar(10) NOT NULL,
  `booking_date` date NOT NULL,
  `quantity` varchar(20) NOT NULL,
  PRIMARY KEY (`borrow_id`,`book_id`,`booking_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_details` */

insert  into `borrow_details`(`borrow_id`,`book_id`,`booking_date`,`quantity`) values 
('borrow_114','114','2020-06-18','1'),('borrow_112','112','2020-01-13','4'),
('borrow_215','215','2020-05-23','3');
