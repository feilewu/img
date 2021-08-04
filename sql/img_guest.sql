DROP TABLE IF EXISTS `img_guest`;
CREATE TABLE `img_guest`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `guest_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;


