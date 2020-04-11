DROP TABLE `orders` if exists;

CREATE TABLE `orders` (
  `id` int(11)  AUTO_INCREMENT NOT NULL COMMENT '唯一不重复',
  `fn` varchar(50) COLLATE utf8_estonian_ci NOT NULL DEFAULT '',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci ROW_FORMAT=DYNAMIC;

ALTER TABLE `orders` `id` int(11)  AUTO_INCREMENT;

INSERT INTO `orders` VALUES (29, 'alpha29');

UPDATE `orders` SET `id` = '30', `fn` = 'alpha30' WHERE `id` = '30';

DELETE from `orders` WHERE `id` = '30';
