
drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (

DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (

    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)

);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) DEFAULT NULL,
    `price` decimal(6,2) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

drop table ims.items;

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` bigint DEFAULT NULL unique,
    `name` VARCHAR(40) DEFAULT NULL,
    `date_ordered` date DEFAULT NULL,
    PRIMARY KEY (`id`)
);

drop table ims.orders;

);
