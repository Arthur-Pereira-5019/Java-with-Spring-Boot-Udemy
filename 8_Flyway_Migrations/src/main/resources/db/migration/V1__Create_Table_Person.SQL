CREATE TABLE IF NOT EXISTS `person` (
  `gender` varchar(2) NOT NULL,
  `id` bigint NOT NULL,
  `address` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);