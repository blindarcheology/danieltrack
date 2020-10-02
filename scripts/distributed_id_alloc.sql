CREATE DATABASE distributed;
DROP TABLE IF EXISTS `distributed_id_alloc`;

CREATE TABLE `distributed_id_alloc` (
  `biz_tag` varchar(128)  NOT NULL DEFAULT '',
  `max_id_seq` bigint(20) NOT NULL DEFAULT '1',
  `step` int(11) NOT NULL,
  `description` varchar(256)  DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB;