-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: edm_db
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1','lifei','classpath:/db/changelog/db.changelog-master.yaml','2019-07-30 13:27:21',1,'EXECUTED','8:5fad9bda5eb58f0b29fc586ceccf1c57','createTable tableName=edmers; createTable tableName=edm_roles; createTable tableName=edmer_role_relation; createTable tableName=edm_usable_magnitude; createTable tableName=edm_target_description; createTable tableName=qunfa_business; createTable t...','',NULL,'3.6.3',NULL,NULL,'4464441007');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_apply_examine_order`
--

DROP TABLE IF EXISTS `edm_apply_examine_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_apply_examine_order` (
  `oid` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `order_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `eid` bigint(20) NOT NULL COMMENT '流转单的发起人id',
  `apply_date` date DEFAULT NULL COMMENT '流转单的发起时间',
  `qunfa_type_description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '群发类型',
  `qunfa_subject_and_context` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '群发主题和短信内容',
  `paiqi_yixiang` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排期意向',
  `target_send_province` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '群发省份',
  `user_conditions` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '群发的用户要求',
  `send_num` int(11) NOT NULL COMMENT '群发的数据量',
  `channel_sends` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '投递通道',
  `how_supplement` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标用户不足时处理方案',
  `message_context` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '短信内容',
  `order_state` int(11) NOT NULL COMMENT '流转单的状态',
  `ocid` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流转单结果的id， UUID',
  PRIMARY KEY (`oid`,`order_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='edm群发流转单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_apply_examine_order`
--

LOCK TABLES `edm_apply_examine_order` WRITE;
/*!40000 ALTER TABLE `edm_apply_examine_order` DISABLE KEYS */;
INSERT INTO `edm_apply_examine_order` VALUES ('0e59c2bc3a2b4e7785039774cbdd647b','测试004',1,'2019-08-03','收入','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户一（近三个月活跃用户，涵盖各个用户层), 提取 1000;\r\n',1000,'462','不补充','今天心情很好，点这里~',9,'572fbebe5e0943abba51d86daf316a6b'),('214d6505628748fcba82f765b16877b1','测试003',1,'2019-08-03','维系','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户三（近三个月有1-3次邮短阅读（点击URL），涵盖各个用户层), 提取 1000;\r\n',1000,'516','不补充','今天心情真好，点这里~',2,'44bd6ba0226748a698324cd2045f339f'),('4f9b1ebfa64441a582067c0819bf55da','沃油料10期',1,'2019-07-31','收入','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户二（近六个月活跃用户，涵盖各个用户层), 提取 1000;\r\n',1000,'462','不补充','今天心情真好，点这里~',6,'5f63532cf9a748e0aea7571989e08439'),('afe3a83f7e6b4765b7c3f0b8843aca17','沃油料01期',1,'2019-08-03','账单-邮短','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户一（近三个月活跃用户，涵盖各个用户层), 提取 1000;\r\n',1000,'462','不补充','今天心情真好，点这里~',9,'cd82a3f5008d4088a69701551e27f085'),('c909753ba81a4977944912299be44503','沃油料01期',1,'2019-08-02','收入','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 中高级活跃用户一（连续六个月有邮短阅读（点击URL）行为用户）, 提取 1000;\r\n',1000,'516','不补充','今天心情真好，点这里~',9,'37b0a2ff683e469a9a3e9c2241bfa158'),('d4a55fe84fdf407888b3c126bc30bd08','沃油料01期',1,'2019-08-02','收入','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户二（近六个月活跃用户，涵盖各个用户层), 提取 1000;\r\n',1000,'516','不补充','今天心情很好，点这里~',6,'504beeb9f34d4d2eb86998c3d548781e'),('e71120bec1e349f19dee11d953eaa869','沃油料05期',1,'2019-07-30','收入','节日，活动','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户一（近三个月活跃用户，涵盖各个用户层), 提取 1000;\r\n',1000,'462','不补充','今天心情真好，点这里~',6,'396fd9c88dcf4c1fb5d566a37f481884'),('f1b384c59d3147608398d2e6ff4df4d5','测试002',1,'2019-08-03','收入','节日','2019年11月13日','目标用户1: 全国;\r\n','目标用户1: 一般活跃用户一（近三个月活跃用户，涵盖各个用户层), 提取 1000;\r\n',1000,'462','不补充','今天心情真好，点这里~',0,'18904ab16bfb4e3891c04fcadb0875b9');
/*!40000 ALTER TABLE `edm_apply_examine_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_apply_files`
--

DROP TABLE IF EXISTS `edm_apply_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_apply_files` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件名称',
  `filepath` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件存储路径',
  `originalfilename` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件原始名称',
  `flag` int(11) NOT NULL COMMENT '附件的标识, 0 代表其他附件， 1 代表 流转单生成的excel',
  `oid` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '转发流转单的id',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='群发流转单的其他附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_apply_files`
--

LOCK TABLES `edm_apply_files` WRITE;
/*!40000 ALTER TABLE `edm_apply_files` DISABLE KEYS */;
INSERT INTO `edm_apply_files` VALUES (1,'《沃油料05期》群发流转单-20190730.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0730135433936','《沃油料05期》群发流转单-20190730.xlsx',1,'e71120bec1e349f19dee11d953eaa869'),(2,'测试发邮件01.md','/Users/lifei/Documents/servers/edm_upload_files/2019/0730135433936','测试发邮件01.md',0,'e71120bec1e349f19dee11d953eaa869'),(3,'《沃油料10期》群发流转单-20190731.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0731131539250','《沃油料10期》群发流转单-20190731.xlsx',1,'4f9b1ebfa64441a582067c0819bf55da'),(4,'测试发邮件02.md','/Users/lifei/Documents/servers/edm_upload_files/2019/0731131539250','测试发邮件02.md',0,'4f9b1ebfa64441a582067c0819bf55da'),(5,'《沃油料01期》群发流转单-20190802.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0802132752764','《沃油料01期》群发流转单-20190802.xlsx',1,'d4a55fe84fdf407888b3c126bc30bd08'),(6,'《沃油料01期》群发流转单-20190803.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0803104910951','《沃油料01期》群发流转单-20190803.xlsx',1,'c909753ba81a4977944912299be44503'),(7,'《沃油料01期》群发流转单-20190803.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0803164104248','《沃油料01期》群发流转单-20190803.xlsx',1,'afe3a83f7e6b4765b7c3f0b8843aca17'),(8,'《测试002》群发流转单-20190803.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0803164350753','《测试002》群发流转单-20190803.xlsx',1,'f1b384c59d3147608398d2e6ff4df4d5'),(9,'《测试003》群发流转单-20190803.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0803170954516','《测试003》群发流转单-20190803.xlsx',1,'214d6505628748fcba82f765b16877b1'),(10,'《测试004》群发流转单-20190803.xlsx','/Users/lifei/Documents/servers/edm_upload_files/2019/0803200024242','《测试004》群发流转单-20190803.xlsx',1,'0e59c2bc3a2b4e7785039774cbdd647b');
/*!40000 ALTER TABLE `edm_apply_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_conditions`
--

DROP TABLE IF EXISTS `edm_conditions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_conditions` (
  `conid` int(11) NOT NULL AUTO_INCREMENT,
  `bus_type` int(11) DEFAULT NULL COMMENT '群发业务类型标识',
  `dimensions` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '维度名称',
  `province_if` int(11) DEFAULT NULL COMMENT '是否包含省份条件（0 不包含，1 包含）',
  `provincecodes` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '省份代码',
  `province_opt` int(11) DEFAULT NULL COMMENT '对省份条件进行包含或排除操作（0 排除，1 包含）',
  `city_if` int(11) DEFAULT NULL COMMENT '是否包含城市条件（0 不包含，1 包含）',
  `citycodes` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '城市代码',
  `city_opt` int(11) DEFAULT NULL COMMENT '对城市条件进行包含或排除操作（0 排除，1 包含）',
  `data_codes` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '要排除的数据编码',
  `limitnum` int(11) NOT NULL COMMENT '提取的数据量',
  `create_date` date DEFAULT NULL COMMENT '提数项创建的时间',
  `oid` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '群发流转单的id',
  `eid` bigint(20) NOT NULL COMMENT '申请人的id',
  PRIMARY KEY (`conid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='edm提数申请项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_conditions`
--

LOCK TABLES `edm_conditions` WRITE;
/*!40000 ALTER TABLE `edm_conditions` DISABLE KEYS */;
INSERT INTO `edm_conditions` VALUES (1,1,'df01',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-07-30','e71120bec1e349f19dee11d953eaa869',1),(2,1,'df02',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-07-31','4f9b1ebfa64441a582067c0819bf55da',1),(3,1,'df02',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-08-02','d4a55fe84fdf407888b3c126bc30bd08',1),(4,1,'lg01',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-08-02','c909753ba81a4977944912299be44503',1),(5,1,'df01',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-08-03','afe3a83f7e6b4765b7c3f0b8843aca17',1),(6,1,'df01',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-08-03','f1b384c59d3147608398d2e6ff4df4d5',1),(7,1,'df03',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-08-03','214d6505628748fcba82f765b16877b1',1),(8,1,'df01',0,NULL,NULL,0,NULL,NULL,NULL,1000,'2019-08-03','0e59c2bc3a2b4e7785039774cbdd647b',1);
/*!40000 ALTER TABLE `edm_conditions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_order_result`
--

DROP TABLE IF EXISTS `edm_order_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_order_result` (
  `ocid` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `first_checker` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '初审人的姓名',
  `first_checker_email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '初审人的邮箱',
  `firstcheck_status` int(11) DEFAULT NULL COMMENT '第一次审核的状态：0 为通过， 1 为不通过',
  `second_checker` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '二次审核，能力组，审核人姓名',
  `second_checker_email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '二次审核，能力组，审核人邮箱',
  `secondcheck_status` int(11) DEFAULT NULL COMMENT '二次审核，结果： 0 为通过， 1 为不通过',
  `paiqi_result` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排期结果',
  `third_checker` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三次审核人姓名，客服组审核',
  `third_checker_email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三次审核人邮箱，客服组审核',
  `paiqicheck_status` int(11) DEFAULT NULL COMMENT '排期的确认结果,0 为通过， 1 为不通过',
  `fangancheck_status` int(11) DEFAULT NULL COMMENT '第三次审核， 群发方案的确认结果 0 为通过， 1 为不通过',
  `third_beizhu` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三次审核，备注',
  `shuju_username` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据组姓名',
  `shuju_email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据组的邮箱',
  `datacodes` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据编码',
  `datausers_des` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户数据说明',
  `actual_usernum` int(11) DEFAULT NULL COMMENT '实际的用户量',
  `end_beizhu` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `oid` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '转发流转单的id',
  PRIMARY KEY (`ocid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='群发流转单结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_order_result`
--

LOCK TABLES `edm_order_result` WRITE;
/*!40000 ALTER TABLE `edm_order_result` DISABLE KEYS */;
INSERT INTO `edm_order_result` VALUES ('18904ab16bfb4e3891c04fcadb0875b9','丽申请',NULL,NULL,'葛兴羽',NULL,NULL,'2019年11月13日','梁南',NULL,NULL,NULL,'请剔除黑名单用户和省分','数据','shuju@wo.cn','','',NULL,'','2019-08-03','f1b384c59d3147608398d2e6ff4df4d5'),('37b0a2ff683e469a9a3e9c2241bfa158','丽申请','frankeleyn@163.com',0,'丽能力','hefrankeleyn@163.com',0,'2019年11月13日','丽客服','1246874542@qq.com',2,2,'请剔除黑名单用户和省分','数据','shuju@wo.cn','1:201908032209056763','目标用户1 提取的数据量与预期的一致;\r\n',12500,'','2019-08-03','c909753ba81a4977944912299be44503'),('396fd9c88dcf4c1fb5d566a37f481884','丽申请','frankeleyn@163.com',0,'丽能力','hefrankeleyn@163.com',0,'2019年11月13日','丽客服','1246874542@qq.com',2,2,'请剔除黑名单用户和省分','数据','shuju@wo.cn','','',NULL,'','2019-07-30','e71120bec1e349f19dee11d953eaa869'),('44bd6ba0226748a698324cd2045f339f','丽申请','frankeleyn@163.com',0,'葛兴羽',NULL,NULL,'2019年11月13日','梁南',NULL,NULL,NULL,'请剔除黑名单用户和省分','数据','shuju@wo.cn','','',NULL,'','2019-08-03','214d6505628748fcba82f765b16877b1'),('504beeb9f34d4d2eb86998c3d548781e','丽申请','frankeleyn@163.com',0,'丽能力','hefrankeleyn@163.com',0,'2019年11月13日','丽客服','1246874542@qq.com',2,2,'请剔除黑名单用户和省分','数据','shuju@wo.cn','','',NULL,'','2019-08-02','d4a55fe84fdf407888b3c126bc30bd08'),('572fbebe5e0943abba51d86daf316a6b','丽申请','frankeleyn@163.com',0,'丽能力','hefrankeleyn@163.com',0,'2019年11月13日','丽客服','1246874542@qq.com',2,2,'请剔除黑名单用户和省分','数据','shuju@wo.cn','1:201908032002201811','目标用户1 提取的数据量与预期的一致;\r\n',12500,'','2019-08-03','0e59c2bc3a2b4e7785039774cbdd647b'),('5f63532cf9a748e0aea7571989e08439','丽申请','frankeleyn@163.com',0,'丽能力','hefrankeleyn@163.com',0,'2019年11月13日','丽客服','1246874542@qq.com',2,2,'请剔除黑名单用户和省分','数据','shuju@wo.cn','','',NULL,'','2019-08-02','4f9b1ebfa64441a582067c0819bf55da'),('cd82a3f5008d4088a69701551e27f085','丽申请','frankeleyn@163.com',0,'丽能力','hefrankeleyn@163.com',0,'2019年11月13日','丽客服','1246874542@qq.com',2,2,'请剔除黑名单用户和省分','数据','shuju@wo.cn','1:201908032203175623','目标用户1 提取的数据量与预期的一致;\r\n',12500,'','2019-08-03','afe3a83f7e6b4765b7c3f0b8843aca17');
/*!40000 ALTER TABLE `edm_order_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_roles`
--

DROP TABLE IF EXISTS `edm_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_roles` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_roles`
--

LOCK TABLES `edm_roles` WRITE;
/*!40000 ALTER TABLE `edm_roles` DISABLE KEYS */;
INSERT INTO `edm_roles` VALUES (1,'ROLE_EDM','开发者权限，能够进行所有操作'),(2,'ROLE_OPERATION','运营者组权限，能够进行EDM提数申请'),(3,'ROLE_APPLY','申请组权限，对edm提数申请进行初步审核'),(4,'ROLE_CAPACITY','能力组权限，对edm提数申请进行二次审核'),(5,'ROLE_CUSTOMER_SERVICE','客服组权限，对edm提数申请进行三次审核'),(6,'ROLE_SHUJU','数据组权限，对edm提数申请进行处理');
/*!40000 ALTER TABLE `edm_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_target_description`
--

DROP TABLE IF EXISTS `edm_target_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_target_description` (
  `target` varchar(300) COLLATE utf8mb4_general_ci NOT NULL COMMENT '可发维度名称',
  `description` varchar(300) COLLATE utf8mb4_general_ci NOT NULL COMMENT '可发维度描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='各个可发维度的描述';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_target_description`
--

LOCK TABLES `edm_target_description` WRITE;
/*!40000 ALTER TABLE `edm_target_description` DISABLE KEYS */;
INSERT INTO `edm_target_description` VALUES ('lg01','中高级活跃用户一（连续六个月有邮短阅读（点击URL）行为用户）'),('lg02','中高级活跃用户二（连续三个月有邮短阅读（点击URL）行为用户）'),('zc','忠诚用户（近三个月有1次及以上邮箱登录行为的用户）'),('df01','一般活跃用户一（近三个月活跃用户，涵盖各个用户层)'),('df02','一般活跃用户二（近六个月活跃用户，涵盖各个用户层)'),('df03','一般活跃用户三（近三个月有1-3次邮短阅读（点击URL），涵盖各个用户层)'),('new','新用户：近三个月注册邮箱账号的用户'),('sl01','沉默用户三（九个月以上没有活跃行为的用户，涵盖各用户层)'),('sl02','沉默用户二（近九个月没有活跃行为的用户，涵盖各用户层)'),('sl03','沉默用户一（近六个月没有活跃行为的用户，涵盖各用户层）');
/*!40000 ALTER TABLE `edm_target_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_task_result`
--

DROP TABLE IF EXISTS `edm_task_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_task_result` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `conid` int(11) DEFAULT NULL COMMENT 'edm_conditions 的id',
  `ocid` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'edm_order_result 的id',
  `user_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务发起人的姓名',
  `status` int(11) NOT NULL COMMENT '数据编码是否可用， 1 为不可用， 2 为可用',
  `submit_time` date DEFAULT NULL COMMENT '创建时间',
  `finish_time` date DEFAULT NULL COMMENT '任务提取完成时间',
  `file_path` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名称或文件绝对路径',
  `data_code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据编码',
  `filelinenum` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件的行数',
  `topic` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '该编码对应的活动名称',
  `business_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务类型，EDM，账单',
  `provincenumsinfo` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '各个省份数据量信息',
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `data_code` (`data_code`),
  UNIQUE KEY `conid` (`conid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='群发流转单的其他附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_task_result`
--

LOCK TABLES `edm_task_result` WRITE;
/*!40000 ALTER TABLE `edm_task_result` DISABLE KEYS */;
INSERT INTO `edm_task_result` VALUES (1,8,'572fbebe5e0943abba51d86daf316a6b','edm',2,'2019-08-03','2019-08-03','temp_qunfa_tishu_20190803_466_20190803.txt','1:201908032002201811','12500','测试004','EDM群发','北京:1000、广东:1500、河北:10000'),(2,5,'cd82a3f5008d4088a69701551e27f085','edm',2,'2019-08-03','2019-08-03','tmp.temp_qunfa_tishu_20190803_926_20190803.txt','1:201908032203175623','12500','沃油料01期','EDM群发','北京:1000、广东:1500、河北:10000'),(3,4,'37b0a2ff683e469a9a3e9c2241bfa158','edm',2,'2019-08-03','2019-08-03','tmp.temp_qunfa_tishu_20190803_220_20190803.txt','1:201908032209056763','12500','沃油料01期','EDM群发','北京:1000、广东:1500、河北:10000');
/*!40000 ALTER TABLE `edm_task_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_usable_magnitude`
--

DROP TABLE IF EXISTS `edm_usable_magnitude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_usable_magnitude` (
  `target` varchar(300) COLLATE utf8mb4_general_ci NOT NULL COMMENT '维度名称',
  `magnitude` mediumtext COLLATE utf8mb4_general_ci NOT NULL COMMENT '可发的数据量级',
  `dt` date NOT NULL COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='edm 可发的量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_usable_magnitude`
--

LOCK TABLES `edm_usable_magnitude` WRITE;
/*!40000 ALTER TABLE `edm_usable_magnitude` DISABLE KEYS */;
/*!40000 ALTER TABLE `edm_usable_magnitude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edm_zone`
--

DROP TABLE IF EXISTS `edm_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edm_zone` (
  `zoneid` bigint(20) NOT NULL AUTO_INCREMENT,
  `provincecode` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '省份代码',
  `provincename` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '省份名称',
  `citycode` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市代码',
  `cityname` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市名称',
  PRIMARY KEY (`zoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='省份和城市代码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edm_zone`
--

LOCK TABLES `edm_zone` WRITE;
/*!40000 ALTER TABLE `edm_zone` DISABLE KEYS */;
INSERT INTO `edm_zone` VALUES (1,'100','北京','1312','北京'),(2,'200','广东','1283','珠海'),(3,'200','广东','1165','汕尾'),(4,'200','广东','1170','韶关'),(5,'200','广东','1248','阳江'),(6,'200','广东','1328','潮阳'),(7,'200','广东','1329','潮州'),(8,'200','广东','1006','东莞'),(9,'200','广东','1013','佛山'),(10,'200','广东','1026','广州'),(11,'200','广东','1044','河源'),(12,'200','广东','1065','惠州'),(13,'200','广东','1075','江门'),(14,'200','广东','1077','揭阳'),(15,'200','广东','1125','茂名'),(16,'200','广东','1127','梅州'),(17,'200','广东','1154','清远'),(18,'200','广东','1164','汕头'),(19,'200','广东','1173','深圳'),(20,'200','广东','1180','顺德'),(21,'200','广东','1265','云浮'),(22,'200','广东','1268','湛江'),(23,'200','广东','1274','肇庆'),(24,'200','广东','1277','中山'),(25,'210','上海','1168','上海'),(26,'220','天津','1197','天津'),(27,'230','重庆','1279','重庆'),(28,'240','辽宁','1298','鞍山'),(29,'240','辽宁','1000','丹东'),(30,'240','辽宁','1199','铁岭'),(31,'240','辽宁','1258','营口'),(32,'240','辽宁','1313','本溪'),(33,'240','辽宁','1327','朝阳'),(34,'240','辽宁','1174','沈阳'),(35,'240','辽宁','1015','抚顺'),(36,'240','辽宁','1017','阜新'),(37,'240','辽宁','1056','葫芦岛'),(38,'240','辽宁','1080','锦州'),(39,'240','辽宁','1105','辽阳'),(40,'240','辽宁','1143','盘锦'),(41,'240','辽宁','1339','大连'),(42,'250','江苏','1185','苏州'),(43,'250','江苏','1239','徐州'),(44,'250','江苏','1325','常州'),(45,'250','江苏','1060','淮安'),(46,'250','江苏','1104','连云港'),(47,'250','江苏','1134','南京'),(48,'250','江苏','1275','镇江'),(49,'250','江苏','1186','宿迁'),(50,'250','江苏','1195','泰州'),(51,'250','江苏','1215','无锡'),(52,'250','江苏','1246','盐城'),(53,'250','江苏','1247','扬州'),(54,'250','江苏','1137','南通'),(55,'270','湖北','1010','鄂州'),(56,'270','湖北','1175','十堰'),(57,'270','湖北','1189','随州'),(58,'270','湖北','1253','宜昌'),(59,'270','湖北','1011','恩施'),(60,'270','湖北','1061','黄冈'),(61,'270','湖北','1231','孝感'),(62,'270','湖北','1083','荆门'),(63,'270','湖北','1084','荆州'),(64,'270','湖北','1219','武汉'),(65,'270','湖北','1226','江汉'),(66,'270','湖北','1227','咸宁'),(67,'270','湖北','1230','襄樊'),(68,'270','湖北','1064','黄石'),(69,'280','四川','1285','资阳'),(70,'280','四川','1252','宜宾'),(71,'280','四川','1289','阿坝'),(72,'280','四川','1300','巴中'),(73,'280','四川','1024','广安'),(74,'280','四川','1101','乐山'),(75,'280','四川','1126','眉山'),(76,'280','四川','1331','成都'),(77,'280','四川','1337','达州'),(78,'280','四川','1002','德阳'),(79,'280','四川','1020','甘孜'),(80,'280','四川','1025','广元'),(81,'280','四川','1120','泸州'),(82,'280','四川','1128','绵阳'),(83,'280','四川','1130','内江'),(84,'280','四川','1133','南充'),(85,'280','四川','1142','攀枝花'),(86,'280','四川','1190','遂宁'),(87,'280','四川','1222','西昌'),(88,'280','四川','1242','雅安'),(89,'280','四川','1287','自贡'),(90,'290','陕西','1294','安康'),(91,'290','陕西','1308','宝鸡'),(92,'290','陕西','1208','渭南'),(93,'290','陕西','1244','延安'),(94,'290','陕西','1260','榆林'),(95,'290','陕西','1166','商洛'),(96,'290','陕西','1202','铜川'),(97,'290','陕西','1221','西安'),(98,'290','陕西','1228','咸阳'),(99,'290','陕西','1039','汉中'),(100,'311','河北','1196','唐山'),(101,'311','河北','1332','承德'),(102,'311','河北','1318','沧州'),(103,'311','河北','1309','保定'),(104,'311','河北','1038','邯郸'),(105,'311','河北','1270','张家口'),(106,'311','河北','1238','邢台'),(107,'311','河北','1177','石家庄'),(108,'311','河北','1152','秦皇岛'),(109,'311','河北','1100','廊坊'),(110,'311','河北','1050','衡水'),(111,'351','山西','1266','运城'),(112,'351','山西','1249','阳泉'),(113,'351','山西','1323','长治'),(114,'351','山西','1082','晋中'),(115,'351','山西','1193','太原'),(116,'351','山西','1341','大同'),(117,'351','山西','1110','临汾'),(118,'351','山西','1121','吕梁'),(119,'351','山西','1081','晋城'),(120,'351','山西','1232','忻州'),(121,'351','山西','1181','朔州'),(122,'371','河南','1122','洛阳'),(123,'371','河南','1076','焦作'),(124,'371','河南','1089','开封'),(125,'371','河南','1047','鹤壁'),(126,'371','河南','1281','周口'),(127,'371','河南','1123','漯河'),(128,'371','河南','1138','南阳'),(129,'371','河南','1297','安阳'),(130,'371','河南','1240','许昌'),(131,'371','河南','1284','驻马店'),(132,'371','河南','1235','信阳'),(133,'371','河南','1233','新乡'),(134,'371','河南','1167','商丘'),(135,'371','河南','1161','三门峡'),(136,'371','河南','1276','郑州'),(137,'371','河南','1144','平顶山'),(138,'371','河南','1148','濮阳'),(139,'431','吉林','1301','白城'),(140,'431','吉林','1068','吉林'),(141,'431','吉林','1113','柳河'),(142,'431','吉林','1183','四平'),(143,'431','吉林','1321','长春'),(144,'431','吉林','1200','通化'),(145,'431','吉林','1245','延边'),(146,'431','吉林','1302','白山'),(147,'431','吉林','1106','辽源'),(148,'431','吉林','1184','松原'),(149,'451','黑龙江','1031','哈尔滨'),(150,'451','黑龙江','1049','黑河'),(151,'451','黑龙江','1066','鸡西'),(152,'451','黑龙江','1072','佳木斯'),(153,'451','黑龙江','1129','牡丹江'),(154,'451','黑龙江','1149','七台河'),(155,'451','黑龙江','1188','绥化'),(156,'451','黑龙江','1250','伊春'),(157,'451','黑龙江','1048','鹤岗'),(158,'451','黑龙江','1150','齐齐哈尔'),(159,'451','黑龙江','1179','双鸭山'),(160,'451','黑龙江','1340','大庆'),(161,'451','黑龙江','1342','大兴安岭'),(162,'471','内蒙古','1009','鄂尔多斯'),(163,'471','内蒙古','1053','呼和浩特'),(164,'471','内蒙古','1054','呼伦贝尔'),(165,'471','内蒙古','1201','通辽'),(166,'471','内蒙古','1211','乌海'),(167,'471','内蒙古','1334','赤峰'),(168,'471','内蒙古','1224','锡林郭勒盟'),(169,'471','内蒙古','1236','兴安盟'),(170,'471','内蒙古','1293','阿拉善盟'),(171,'471','内蒙古','1299','巴彦淖尔'),(172,'471','内蒙古','1307','包头'),(173,'471','内蒙古','1212','乌兰察布'),(174,'531','山东','1206','威海'),(175,'531','山东','1071','济宁'),(176,'531','山东','1207','潍坊'),(177,'531','山东','1243','烟台'),(178,'531','山东','1045','菏泽'),(179,'531','山东','1003','德州'),(180,'531','山东','1112','临沂'),(181,'531','山东','1107','聊城'),(182,'531','山东','1267','枣庄'),(183,'531','山东','1007','东营'),(184,'531','山东','1286','淄博'),(185,'531','山东','1315','滨州'),(186,'531','山东','1070','济南'),(187,'531','山东','1194','泰安'),(188,'531','山东','1160','日照'),(189,'531','山东','1098','莱芜'),(190,'531','山东','1153','青岛'),(191,'551','安徽','1124','马鞍山'),(192,'551','安徽','1115','六安'),(193,'551','安徽','1063','黄山'),(194,'551','安徽','1059','淮南'),(195,'551','安徽','1058','淮北'),(196,'551','安徽','1041','合肥'),(197,'551','安徽','1018','阜阳'),(198,'551','安徽','1335','滁州'),(199,'551','安徽','1306','蚌埠'),(200,'551','安徽','1333','池州'),(201,'551','安徽','1326','巢湖'),(202,'551','安徽','1316','亳州'),(203,'551','安徽','1295','安庆'),(204,'551','安徽','1241','宣城'),(205,'551','安徽','1217','芜湖'),(206,'551','安徽','1203','铜陵'),(207,'551','安徽','1187','宿州'),(208,'571','浙江','1139','宁波'),(209,'571','浙江','1172','绍兴'),(210,'571','浙江','1073','嘉兴'),(211,'571','浙江','1192','台州'),(212,'571','浙江','1103','丽水'),(213,'571','浙江','1079','金华'),(214,'571','浙江','1055','湖州'),(215,'571','浙江','1209','温州'),(216,'571','浙江','1280','舟山'),(217,'571','浙江','1040','杭州'),(218,'571','浙江','1157','衢州'),(219,'591','福建','1162','三明'),(220,'591','福建','1225','厦门'),(221,'591','福建','1147','莆田'),(222,'591','福建','1140','宁德'),(223,'591','福建','1136','南平'),(224,'591','福建','1117','龙岩'),(225,'591','福建','1014','福州'),(226,'591','福建','1158','泉州'),(227,'591','福建','1272','漳州'),(228,'731','湖南','1324','常德'),(229,'731','湖南','1322','长沙'),(230,'731','湖南','1282','株洲'),(231,'731','湖南','1269','张家界'),(232,'731','湖南','1264','岳阳'),(233,'731','湖南','1259','永州'),(234,'731','湖南','1069','吉首'),(235,'731','湖南','1255','益阳'),(236,'731','湖南','1051','衡阳'),(237,'731','湖南','1171','邵阳'),(238,'731','湖南','1119','娄底'),(239,'731','湖南','1057','怀化'),(240,'731','湖南','1330','郴州'),(241,'731','湖南','1229','湘潭'),(242,'771','广西','1027','贵港'),(243,'771','广西','1135','南宁'),(244,'771','广西','1012','防城港'),(245,'771','广西','1218','梧州'),(246,'771','广西','1029','桂林'),(247,'771','广西','1043','河池'),(248,'771','广西','1304','百色'),(249,'771','广西','1046','贺州'),(250,'771','广西','1097','来宾'),(251,'771','广西','1151','钦州'),(252,'771','广西','1114','柳州'),(253,'771','广西','1311','北海'),(254,'771','广西','1261','玉林'),(255,'791','江西','1234','新余'),(256,'791','江西','1086','九江'),(257,'791','江西','1067','吉安'),(258,'791','江西','1257','鹰潭'),(259,'791','江西','1254','宜春'),(260,'791','江西','1169','上饶'),(261,'791','江西','1146','萍乡'),(262,'791','江西','1132','南昌'),(263,'791','江西','1085','景德镇'),(264,'791','江西','1021','赣州'),(265,'791','江西','1016','抚州'),(266,'851','贵州','1204','铜仁'),(267,'851','贵州','1237','黔西南'),(268,'851','贵州','1008','黔南'),(269,'851','贵州','1296','安顺'),(270,'851','贵州','1028','贵阳'),(271,'851','贵州','1090','黔东南'),(272,'851','贵州','1314','毕节'),(273,'851','贵州','1116','六盘水'),(274,'851','贵州','1288','遵义'),(275,'871','云南','1273','昭通'),(276,'871','云南','1210','文山'),(277,'871','云南','1182','普洱'),(278,'871','云南','1141','怒江'),(279,'871','云南','1109','临沧'),(280,'871','云南','1102','丽江'),(281,'871','云南','1095','昆明'),(282,'871','云南','1004','迪庆'),(283,'871','云南','1338','大理'),(284,'871','云南','1001','德宏'),(285,'871','云南','1336','楚雄'),(286,'871','云南','1263','玉溪'),(287,'871','云南','1156','曲靖'),(288,'871','云南','1052','红河'),(289,'871','云南','1310','保山'),(290,'871','云南','1305','版纳'),(291,'891','西藏','1131','那曲'),(292,'891','西藏','1096','拉萨'),(293,'891','西藏','1159','日喀则'),(294,'891','西藏','1319','昌都'),(295,'891','西藏','1108','林芝'),(296,'891','西藏','1292','阿里'),(297,'891','西藏','1163','山南'),(298,'898','海南','1035','海口'),(299,'931','甘肃','1198','天水'),(300,'931','甘肃','1074','嘉峪关'),(301,'931','甘肃','1099','兰州'),(302,'931','甘肃','1271','张掖'),(303,'931','甘肃','1005','定西'),(304,'931','甘肃','1019','甘南'),(305,'931','甘肃','1155','庆阳'),(306,'931','甘肃','1220','武威'),(307,'931','甘肃','1303','白银'),(308,'931','甘肃','1145','平凉'),(309,'931','甘肃','1118','陇南'),(310,'931','甘肃','1078','金昌'),(311,'931','甘肃','1087','酒泉'),(312,'931','甘肃','1111','临夏'),(313,'951','宁夏','1278','中卫'),(314,'951','宁夏','1256','银川'),(315,'951','宁夏','1023','固原'),(316,'951','宁夏','1216','吴忠'),(317,'951','宁夏','1178','石嘴山'),(318,'971','青海','1037','海西'),(319,'971','青海','1036','海南'),(320,'971','青海','1034','海东'),(321,'971','青海','1030','果洛'),(322,'971','青海','1022','格尔木'),(323,'971','青海','1062','黄南'),(324,'971','青海','1033','海北'),(325,'971','青海','1262','玉树'),(326,'971','青海','1223','西宁'),(327,'991','新疆','1094','奎屯'),(328,'991','新疆','1191','塔城'),(329,'991','新疆','1093','库尔勒'),(330,'991','新疆','1092','克州'),(331,'991','新疆','1091','克拉玛依'),(332,'991','新疆','1291','阿勒泰'),(333,'991','新疆','1317','博乐'),(334,'991','新疆','1088','喀什'),(335,'991','新疆','1042','和田'),(336,'991','新疆','1213','乌鲁木齐'),(337,'991','新疆','1032','哈密'),(338,'991','新疆','1251','伊犁'),(339,'991','新疆','1320','昌吉'),(340,'991','新疆','1176','石河子'),(341,'991','新疆','1205','吐鲁番'),(342,'991','新疆','1290','阿克苏');
/*!40000 ALTER TABLE `edm_zone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edmer_role_relation`
--

DROP TABLE IF EXISTS `edmer_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edmer_role_relation` (
  `eid` int(11) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户-角色 关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edmer_role_relation`
--

LOCK TABLES `edmer_role_relation` WRITE;
/*!40000 ALTER TABLE `edmer_role_relation` DISABLE KEYS */;
INSERT INTO `edmer_role_relation` VALUES (1,1),(1,2),(1,6),(3,3),(4,4),(5,5);
/*!40000 ALTER TABLE `edmer_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edmers`
--

DROP TABLE IF EXISTS `edmers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `edmers` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `department` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门，组别',
  `level` int(11) NOT NULL COMMENT '级别，职责（1 执行； 2 监督）,与邮件发送有关',
  PRIMARY KEY (`eid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edmers`
--

LOCK TABLES `edmers` WRITE;
/*!40000 ALTER TABLE `edmers` DISABLE KEYS */;
INSERT INTO `edmers` VALUES (1,'edm','$2a$10$z0WVGwrlOiZ1WuFxpqa2T.byuiP2zylh2KCz1wGL9DeHdZUOPhVL2','shuju@wo.cn','数据组',1),(2,'丽运营','$2a$10$/nHl83AqBMuc5.stHf1fduV.Gf.LUScNZmazRWJLTBMcCSDZ8PmNC','lifei6@asiainfo.com','运营组',1),(3,'丽申请','$2a$10$URJ1nWCG8A1PYmrBANbe6.bOz25TOMvdSqod5kEEKbLOuSG60BRri','frankeleyn@163.com','申请组',1),(4,'丽能力','$2a$10$5T3lkhnLlC38KQ4xk4GJOe.lVKWX5SBVqsRFifaoJKVeerQUsywLS','hefrankeleyn@163.com','能力组',1),(5,'丽客服','$2a$10$GhO9K/OFg32xVpxd3kpvN.fynKCG.zAiOpzKKIu5fzzj/sWjkR8Ly','1246874542@qq.com','客服组',1);
/*!40000 ALTER TABLE `edmers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qunfa_business`
--

DROP TABLE IF EXISTS `qunfa_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qunfa_business` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bus_type` int(11) NOT NULL COMMENT '群发业务类型的标识',
  `bus_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '群发业务类型名称',
  `status` int(11) NOT NULL COMMENT '当前业务是否可用： 0 可用， 1 不可用',
  `hive_table` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '使用的hive表明',
  PRIMARY KEY (`bid`),
  UNIQUE KEY `bus_type` (`bus_type`),
  UNIQUE KEY `bus_name` (`bus_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='群发业务的描述';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qunfa_business`
--

LOCK TABLES `qunfa_business` WRITE;
/*!40000 ALTER TABLE `qunfa_business` DISABLE KEYS */;
INSERT INTO `qunfa_business` VALUES (1,1,'EDM群发',0,'dm_kf_usable_users_dimension_current_month'),(2,2,'账单',1,NULL);
/*!40000 ALTER TABLE `qunfa_business` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-04 22:17:06
