DROP DATABASE menuve;
CREATE DATABASE menuve;

USE menuve;

CREATE TABLE `resto` (
 `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `name` varchar(45) NOT NULL,
 `address` varchar(45) NOT NULL,
 `created_on` datetime NOT NULL,
 `logged_on` datetime NOT NULL,
 `user_id` INT not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `menu` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `id_resto` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(45) NOT NULL,
    `created_on` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
 `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `name` varchar(45) NOT NULL,
 `created_on` datetime NOT NULL,
 `logged_on` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `comment` (
 `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `user_id` INT not null,
 `description` varchar(45) NOT NULL,
  `id_menu` int,
  `created_on` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

