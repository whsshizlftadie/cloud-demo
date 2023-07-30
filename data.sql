CREATE DATABASE  IF NOT EXISTS `oj_auth` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `oj_auth`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: rm-cn-5yd3ans5c000llro.rwlb.rds.aliyuncs.com    Database: oj_auth
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'cf6b3e07-1d78-11ee-b674-00163e061706:1-116115';

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('normal-app','','$2a$10$BqEbrCe8XPovUcIvJhFLEu6oHOxhcGoR.WUKbKmmEDqTt5wAEHMT2','SCOPE_TRUST','password,refresh_token',NULL,'ROLE_ADMIN',3600,86400,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `name` varchar(180) NOT NULL,
  `options` varchar(500) DEFAULT NULL,
  `level` int NOT NULL DEFAULT '1',
  `status` int NOT NULL DEFAULT '1',
  `sort` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'2020-05-23 04:26:59',NULL,NULL,NULL,NULL,'系统设置',NULL,1,1,35),(2,'2020-05-23 04:27:22',NULL,NULL,NULL,1,'用户管理',NULL,2,1,0),(3,'2020-05-23 04:27:53',NULL,NULL,NULL,1,'权限管理',NULL,2,1,1),(4,'2020-05-23 04:28:09',NULL,NULL,NULL,1,'菜单管理',NULL,2,1,2),(5,'2020-05-23 04:28:35',NULL,NULL,NULL,1,'资源管理',NULL,2,1,3),(6,'2020-05-23 04:28:56',NULL,NULL,NULL,NULL,'商品中心',NULL,1,1,0),(7,'2020-05-23 04:29:22',NULL,NULL,NULL,6,'商品管理',NULL,2,1,0),(8,'2020-05-23 04:29:50',NULL,NULL,NULL,NULL,'店铺中心',NULL,1,1,10),(9,'2020-05-23 04:30:08',NULL,NULL,NULL,8,'店铺管理',NULL,2,1,0),(10,'2020-05-23 04:30:24',NULL,NULL,NULL,NULL,'会员中心',NULL,1,1,15),(11,'2020-05-23 04:30:48',NULL,NULL,NULL,10,'会员管理',NULL,2,1,0),(12,'2020-05-23 04:31:01',NULL,NULL,NULL,NULL,'订单中心',NULL,1,1,20),(13,'2020-05-23 04:31:17',NULL,NULL,NULL,12,'订单管理',NULL,2,1,0),(14,'2020-05-23 04:31:46',NULL,NULL,NULL,NULL,'运营中心',NULL,1,1,25),(15,'2020-05-23 04:32:07',NULL,NULL,NULL,14,'活动设置',NULL,2,1,0),(16,'2020-05-23 04:32:52',NULL,NULL,NULL,14,'优惠券设置',NULL,2,1,1),(17,'2020-05-23 04:34:35',NULL,NULL,NULL,NULL,'内容中心',NULL,1,1,30),(18,'2020-05-23 04:35:16',NULL,NULL,NULL,17,'BANNER管理',NULL,2,1,0),(19,'2020-05-23 04:36:27',NULL,NULL,NULL,NULL,'品牌中心',NULL,1,1,5),(20,'2020-05-23 04:36:38',NULL,NULL,NULL,19,'品牌管理',NULL,2,1,0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `name` varchar(180) NOT NULL,
  `url` varchar(180) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `sort` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES (1,'2020-05-23 06:21:51',NULL,NULL,NULL,'加载菜单树','/api/load/menus',1,1),(2,'2020-05-23 06:25:43',NULL,NULL,NULL,'首页数据','/api/hello',1,0),(3,'2020-05-23 06:25:43',NULL,NULL,NULL,'apitest1','/api/*',1,1),(4,'2020-05-23 06:25:43',NULL,NULL,NULL,'apiAll','/api/**',1,1);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `code` varchar(180) NOT NULL,
  `name` varchar(180) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'2020-05-23 04:02:20',NULL,NULL,NULL,'ADMIN','管理员'),(2,'2020-05-23 04:02:36',NULL,NULL,NULL,'OM','运营经理'),(3,'2020-05-23 04:03:34',NULL,NULL,NULL,'PM','产品经理');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_rid_mid` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,'2020-05-23 04:41:58',NULL,NULL,NULL,1,1),(2,'2020-05-23 04:42:08',NULL,NULL,NULL,1,2),(3,'2020-05-23 04:42:14',NULL,NULL,NULL,1,3),(4,'2020-05-23 04:42:19',NULL,NULL,NULL,1,4),(5,'2020-05-23 04:42:23',NULL,NULL,NULL,1,5),(6,'2020-05-23 04:42:31',NULL,NULL,NULL,1,6),(7,'2020-05-23 04:42:36',NULL,NULL,NULL,1,7),(8,'2020-05-23 04:42:45',NULL,NULL,NULL,1,8),(9,'2020-05-23 04:42:51',NULL,NULL,NULL,1,9),(10,'2020-05-23 04:43:18',NULL,NULL,NULL,1,10),(11,'2020-05-23 04:41:58',NULL,NULL,NULL,1,11),(12,'2020-05-23 04:42:08',NULL,NULL,NULL,1,12),(13,'2020-05-23 04:42:14',NULL,NULL,NULL,1,13),(14,'2020-05-23 04:42:19',NULL,NULL,NULL,1,14),(15,'2020-05-23 04:42:23',NULL,NULL,NULL,1,15),(16,'2020-05-23 04:42:31',NULL,NULL,NULL,1,16),(17,'2020-05-23 04:42:36',NULL,NULL,NULL,1,17),(18,'2020-05-23 04:42:45',NULL,NULL,NULL,1,18),(19,'2020-05-23 04:42:51',NULL,NULL,NULL,1,19),(20,'2020-05-23 04:43:18',NULL,NULL,NULL,1,20),(21,'2020-05-23 04:42:31',NULL,NULL,NULL,2,6),(22,'2020-05-23 04:42:36',NULL,NULL,NULL,2,7),(23,'2020-05-23 04:42:45',NULL,NULL,NULL,2,8),(24,'2020-05-23 04:42:51',NULL,NULL,NULL,2,9),(25,'2020-05-23 04:43:18',NULL,NULL,NULL,2,10),(26,'2020-05-23 04:41:58',NULL,NULL,NULL,2,11),(27,'2020-05-23 04:42:08',NULL,NULL,NULL,2,12),(28,'2020-05-23 04:42:14',NULL,NULL,NULL,2,13),(29,'2020-05-23 04:42:19',NULL,NULL,NULL,2,14),(30,'2020-05-23 04:42:23',NULL,NULL,NULL,2,15),(31,'2020-05-23 04:42:31',NULL,NULL,NULL,2,16),(32,'2020-05-23 04:42:36',NULL,NULL,NULL,2,17),(33,'2020-05-23 04:42:45',NULL,NULL,NULL,2,18),(34,'2020-05-23 04:42:51',NULL,NULL,NULL,2,19),(35,'2020-05-23 04:43:18',NULL,NULL,NULL,2,20),(36,'2020-05-23 04:42:31',NULL,NULL,NULL,3,6),(37,'2020-05-23 04:42:36',NULL,NULL,NULL,3,7),(38,'2020-05-23 04:42:45',NULL,NULL,NULL,3,8),(39,'2020-05-23 04:42:51',NULL,NULL,NULL,3,9),(40,'2020-05-23 04:43:18',NULL,NULL,NULL,3,10),(41,'2020-05-23 04:41:58',NULL,NULL,NULL,3,11),(42,'2020-05-23 04:42:08',NULL,NULL,NULL,3,12),(43,'2020-05-23 04:42:14',NULL,NULL,NULL,3,13),(44,'2020-05-23 04:42:19',NULL,NULL,NULL,3,14),(45,'2020-05-23 04:42:23',NULL,NULL,NULL,3,15),(46,'2020-05-23 04:42:31',NULL,NULL,NULL,3,16),(47,'2020-05-23 04:42:36',NULL,NULL,NULL,3,17),(48,'2020-05-23 04:42:45',NULL,NULL,NULL,3,18),(49,'2020-05-23 04:42:51',NULL,NULL,NULL,3,19);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_resource`
--

DROP TABLE IF EXISTS `sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `resource_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uq_rid_sid` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_resource`
--

LOCK TABLES `sys_role_resource` WRITE;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;
INSERT INTO `sys_role_resource` VALUES (1,'2020-05-23 06:26:23',NULL,NULL,NULL,1,2),(2,'2020-05-23 06:26:23',NULL,NULL,NULL,1,1),(3,'2020-05-23 06:26:23',NULL,NULL,NULL,1,3),(4,'2020-05-23 06:26:23',NULL,NULL,NULL,1,4);
/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `username` varchar(180) NOT NULL,
  `password` varchar(180) NOT NULL,
  `nickname` varchar(180) DEFAULT NULL,
  `avatar` varchar(180) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'2020-05-23 02:49:48',NULL,NULL,NULL,'18969553171','$2a$10$BqEbrCe8XPovUcIvJhFLEu6oHOxhcGoR.WUKbKmmEDqTt5wAEHMT2','baicai',NULL,1),(2,'2020-05-23 04:49:04',NULL,NULL,NULL,'18912345678','$2a$10$BqEbrCe8XPovUcIvJhFLEu6oHOxhcGoR.WUKbKmmEDqTt5wAEHMT2','zhanglin',NULL,1),(3,'2020-05-23 04:49:26',NULL,NULL,NULL,'18987654321','$2a$10$BqEbrCe8XPovUcIvJhFLEu6oHOxhcGoR.WUKbKmmEDqTt5wAEHMT2','lupeng',NULL,1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_uid_rid` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,'2020-05-23 04:04:58',NULL,NULL,NULL,1,1),(2,'2020-05-23 04:50:09',NULL,NULL,NULL,2,2),(3,'2020-05-23 04:50:20',NULL,NULL,NULL,3,3);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-28 19:04:54
