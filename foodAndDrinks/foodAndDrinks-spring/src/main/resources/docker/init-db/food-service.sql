CREATE DATABASE  IF NOT EXISTS `service_food` CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'yokudlela'@'%' IDENTIFIED BY 'yokudlela';
GRANT ALL PRIVILEGES ON `service_food`.* TO `yokudlela`@`%`;