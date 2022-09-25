DROP TABLE IF EXISTS `img_meta`;
CREATE TABLE `img_meta`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `img_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `suffix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime(0) NULL DEFAULT NULL,
    `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;
