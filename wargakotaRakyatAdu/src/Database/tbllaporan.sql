/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.32-MariaDB 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

CREATE TABLE `tbllaporan` (
	`id` INT (11),
	`username` VARCHAR (150),
	`judulLaporan` VARCHAR (300),
	`isiLaporan` TEXT ,
	`lokasi` VARCHAR (2997),
	`tanggal` DATE ,
	`instansi` VARCHAR (300),
	`kategori` VARCHAR (300),
	`file_name` VARCHAR (675),
	`file_size` BIGINT (20),
	`file_path` VARCHAR (675),
	`timestamp` TIMESTAMP ,
	`status` VARCHAR (600)
); 
INSERT INTO `tbllaporan` (`id`, `username`, `judulLaporan`, `isiLaporan`, `lokasi`, `tanggal`, `instansi`, `kategori`, `file_name`, `file_size`, `file_path`, `timestamp`, `status`) VALUES('19','ali','bbbb','bbbbb','Jl. Padjajaran Jl. Ring Road Utara No.104, Ngropoh, Condongcatur, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55283','2024-06-07','Gunung Kidul','Bencana Alam','Home-WargaKota.png','1179667','C:\\Users\\LOQ\\Pictures\\Home-WargaKota.png','2024-06-27 14:31:15','Proses Verifikasi');
insert into `tbllaporan` (`id`, `username`, `judulLaporan`, `isiLaporan`, `lokasi`, `tanggal`, `instansi`, `kategori`, `file_name`, `file_size`, `file_path`, `timestamp`, `status`) values('20','Ali','Jalanan Rusak di depan UII','deskripsi','saya ingin melapor','2024-06-13','Gunung Kidul','Infrastruktur','bar-graph.png','97294','C:\\Users\\LOQ\\Downloads\\bar-graph.png','2024-06-27 14:35:15','Proses Verifikasi');
insert into `tbllaporan` (`id`, `username`, `judulLaporan`, `isiLaporan`, `lokasi`, `tanggal`, `instansi`, `kategori`, `file_name`, `file_size`, `file_path`, `timestamp`, `status`) values('21','ali','aaaa','aaaa','aaaa','2024-06-06','Sleman','Infrastruktur','line-graph (1).png','132454','C:\\Users\\LOQ\\Downloads\\line-graph (1).png','2024-06-27 18:26:30','Proses Verifikasi');
insert into `tbllaporan` (`id`, `username`, `judulLaporan`, `isiLaporan`, `lokasi`, `tanggal`, `instansi`, `kategori`, `file_name`, `file_size`, `file_path`, `timestamp`, `status`) values('22','Haikal','meletus','aduh Habdil','jackal','2024-06-30','Sleman','Bencana Alam','bar-graph.png','97294','C:\\Users\\LOQ\\Downloads\\bar-graph.png','2024-06-27 19:08:49','Proses Verifikasi');
