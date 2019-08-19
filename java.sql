/*
SQLyog Community v13.1.2 (64 bit)
MySQL - 5.5.62 : Database - java
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`java` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `java`;

/*Table structure for table `rank` */

DROP TABLE IF EXISTS `rank`;

CREATE TABLE `rank` (
  `user_ID` varchar(10) NOT NULL,
  `jumsu` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rank` */

insert  into `rank`(`user_ID`,`jumsu`) values 
('b',200),
('c',400),
('d',300),
('a',3168),
('a',3248);

/*Table structure for table `save` */

DROP TABLE IF EXISTS `save`;

CREATE TABLE `save` (
  `user_ID` varchar(10) NOT NULL,
  `jumsu` varchar(10) NOT NULL,
  `b1` varchar(10) DEFAULT NULL,
  `b2` varchar(10) DEFAULT NULL,
  `b3` varchar(10) DEFAULT NULL,
  `b4` varchar(10) DEFAULT NULL,
  `b5` varchar(10) DEFAULT NULL,
  `b6` varchar(10) DEFAULT NULL,
  `b7` varchar(10) DEFAULT NULL,
  `b8` varchar(10) DEFAULT NULL,
  `b9` varchar(10) DEFAULT NULL,
  `b10` varchar(10) DEFAULT NULL,
  `b11` varchar(10) DEFAULT NULL,
  `b12` varchar(10) DEFAULT NULL,
  `b13` varchar(10) DEFAULT NULL,
  `b14` varchar(10) DEFAULT NULL,
  `b15` varchar(10) DEFAULT NULL,
  `b16` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `save` */

insert  into `save`(`user_ID`,`jumsu`,`b1`,`b2`,`b3`,`b4`,`b5`,`b6`,`b7`,`b8`,`b9`,`b10`,`b11`,`b12`,`b13`,`b14`,`b15`,`b16`) values 
('159159','save?','2','0','0','0','2','2','2','2','2','2','2','2','2','2','2','2'),
('e','0','0','0','0','0','0','0','0','0','2','0','0','2','0','0','0','0');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_ID` varchar(10) NOT NULL,
  `user_PW` varchar(10) NOT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_ID`,`user_PW`) values 
('159159','159159'),
('a','a'),
('asdf','asdf'),
('b','123'),
('e','e'),
('qwer','qwer'),
('test','test'),
('uchangmin','1234'),
('zz','zz');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
