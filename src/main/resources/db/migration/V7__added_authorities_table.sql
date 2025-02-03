DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`user_id` int,
`authority` varchar(50),
CONSTRAINT `authorities_ibfk_1`
FOREIGN KEY (`user_id`)
REFERENCES `users` (`id`)
);