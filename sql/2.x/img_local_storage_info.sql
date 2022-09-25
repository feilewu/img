DROP TABLE IF EXISTS `img_local_storage_info`;
CREATE TABLE `img_local_storage_info`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `img_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `prefix_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `suffix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;
