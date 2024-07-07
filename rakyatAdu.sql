/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - rakyatadu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rakyatadu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `rakyatadu`;

/*Table structure for table `tbllogin` */

DROP TABLE IF EXISTS `tbllogin`;

CREATE TABLE `tbllogin` (
  `namaLengkap` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `confirmPassword` varchar(200) NOT NULL,
  PRIMARY KEY (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbllogin` */

/*Table structure for table `tblloginadmin` */

DROP TABLE IF EXISTS `tblloginadmin`;

CREATE TABLE `tblloginadmin` (
  `id` int(60) NOT NULL AUTO_INCREMENT,
  `namaLengkap` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tblloginadmin` */

insert  into `tblloginadmin`(`id`,`namaLengkap`,`username`,`password`) values 
(1,'HABDIL','admin@admin','123'),
(2,'KEPALA DAERAH','kepala@pemerintah','567');

/*Table structure for table `tbllogindinas` */

DROP TABLE IF EXISTS `tbllogindinas`;

CREATE TABLE `tbllogindinas` (
  `id` int(60) NOT NULL AUTO_INCREMENT,
  `namaLengkap` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `nomorKontak` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbllogindinas` */

insert  into `tbllogindinas`(`id`,`namaLengkap`,`username`,`password`,`alamat`,`nomorKontak`) values 
(9,'dinas','dinas@dinas','pass123','Sleman','085975360990');

/*Table structure for table `tblmessage` */

DROP TABLE IF EXISTS `tblmessage`;

CREATE TABLE `tblmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `namaLengkap` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tblmessage` */

insert  into `tblmessage`(`id`,`namaLengkap`,`message`,`timestamp`) values 
(147,'ADMIN','Haloo ini ada pemberitahuan','2024-07-06 06:43:17'),
(148,'DINAS','Ada apa tuh','2024-07-06 06:43:23'),
(149,'ABDUL','Kenapa nih Mas','2024-07-06 06:43:30'),
(150,'ADMIN','Haii teman-teman','2024-07-06 15:44:24'),
(151,'ABDUL','kenapa bang','2024-07-06 15:44:30'),
(152,'jeki','lagi makan','2024-07-06 15:44:37');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
