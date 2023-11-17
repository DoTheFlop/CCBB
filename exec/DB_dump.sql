CREATE DATABASE  IF NOT EXISTS `ccbb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ccbb`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ccbb
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.20.04.1

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

--
-- Table structure for table `ballot_box`
--

DROP TABLE IF EXISTS `ballot_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ballot_box` (
  `ballot_box_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `vote_id` int NOT NULL,
  `pick` int NOT NULL,
  PRIMARY KEY (`ballot_box_id`),
  KEY `FK_user_TO_ballot_box_1` (`user_id`),
  KEY `FK_vote_TO_ballot_box_1` (`vote_id`),
  CONSTRAINT `FK_user_TO_ballot_box_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_vote_TO_ballot_box_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ballot_box`
--

LOCK TABLES `ballot_box` WRITE;
/*!40000 ALTER TABLE `ballot_box` DISABLE KEYS */;
INSERT INTO `ballot_box` VALUES (1,1,1,1),(2,2,1,2),(3,3,1,1),(8,5,1,1),(11,4,17,1),(12,4,6,2),(13,4,1,1),(14,4,6,1),(15,4,7,1),(16,4,8,1),(17,4,9,1),(18,4,10,1),(19,4,11,1),(20,4,13,1),(21,4,14,1),(22,4,15,1),(23,4,12,1),(24,4,16,1),(25,12,12,1),(27,12,8,2),(29,12,9,2),(30,12,3,2),(31,12,1,1),(32,12,16,1),(33,9,17,2),(35,1,17,1),(36,11,1,2),(37,12,7,2),(38,12,11,2),(39,12,12,2),(40,2,12,2),(41,77,12,1),(42,2,6,1),(43,76,1,1),(47,12,18,2),(48,12,17,2),(49,12,19,1),(50,12,20,2),(51,12,14,2),(52,4,17,1),(53,4,14,2),(54,4,14,2),(55,78,6,1),(56,78,6,2),(57,78,17,2),(58,78,17,2),(59,78,14,2),(60,78,12,1),(61,2,1,1),(62,75,1,1),(63,1,12,1),(64,75,17,1),(65,2,28,2),(66,11,28,2),(67,75,28,1),(68,5,28,1),(69,5,12,2),(70,78,17,2),(71,78,17,2),(72,78,17,2),(73,78,28,2),(74,10,28,1),(76,83,12,2),(77,10,12,1),(78,2,30,1),(79,73,30,2),(80,76,30,2),(83,10,30,1),(84,10,14,1),(85,10,6,2),(86,75,6,1),(88,84,17,1),(89,84,14,1),(91,84,30,1),(92,7,17,1),(93,13,6,2),(95,13,14,1),(96,85,14,1),(97,85,17,1),(100,86,12,1),(101,86,17,1),(102,1,37,1),(103,75,37,1),(104,1,40,2),(105,8,40,2),(106,1,41,1),(107,80,37,1),(108,80,42,2),(109,8,42,2),(110,9,41,2),(111,2,42,1),(112,5,42,1),(113,5,40,2),(114,5,41,2),(117,26,6,1),(118,7,43,1),(119,2,43,1);
/*!40000 ALTER TABLE `ballot_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `content` varchar(1000) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `FK_user_TO_comment_1` (`user_id`),
  KEY `FK_post_TO_comment_1` (`post_id`),
  CONSTRAINT `FK_post_TO_comment_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_user_TO_comment_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2,1,5,'댓글 입니당 ㅎㅎ','2023-11-07 15:47:20'),(3,1,5,'댓글 입니당 ㅎㅎ','2023-11-07 15:48:03'),(4,1,5,'댓글 입니당 ㅎㅎ','2023-11-07 16:16:21'),(5,1,5,'댓글 입니당 ㅎㅎ','2023-11-07 16:16:32'),(14,5,5,'댓글썼음수정도함엔터도침\n개행은?','2023-11-08 13:15:32'),(16,69,1,'종욱이 존ㅈ라러','2023-11-08 14:01:00'),(18,1,9,'테스트','2023-11-08 15:44:34'),(19,4,4,'ㅁㄴㅇㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ','2023-11-08 15:51:58'),(20,5,1,'ㅋㅋㅎㅎ\nㅋㅋㅋㅋㅎㅎㅎㅎ\nㅋㅋㅋㅋㅋㅋㅋㅋㅎㅎㅎㅎㅎㅎㅎㅎ','2023-11-08 16:33:07'),(21,73,1,'아니 클릭이 안되자나 실화야!?','2023-11-09 10:17:57'),(22,73,1,'선생님들! 댓글 작성 시간에 T가 있읍니다! 바까조요!','2023-11-09 10:19:17'),(23,71,1,'그랩 그렇게 하는거 아닌데','2023-11-09 10:24:53'),(26,12,5,'ㅇㅎ','2023-11-10 13:09:27'),(28,12,5,'ㅎㅇ','2023-11-10 17:09:22'),(29,1,14,'아니 근데 우혁이가 잘못한거 당연한거 아닌가요 ?','2023-11-12 15:56:48'),(32,11,1,'','2023-11-12 15:56:59'),(33,11,1,'','2023-11-12 15:56:59'),(34,11,1,'','2023-11-12 15:57:00'),(35,11,1,'','2023-11-12 15:57:00'),(36,11,1,'','2023-11-12 15:57:00'),(37,11,1,'','2023-11-12 15:57:00'),(38,11,6,'','2023-11-12 15:57:00'),(39,12,6,'123','2023-11-13 09:09:37'),(40,1,6,'와 이건 ..','2023-11-13 09:13:42'),(41,1,6,'와 이건 ..','2023-11-13 09:16:18'),(42,1,6,'와 이건 ..','2023-11-13 09:17:14'),(43,1,19,'테스트','2023-11-13 10:01:21'),(44,1,22,'test','2023-11-13 14:52:10'),(45,11,36,'이건 시도는 해도됐을듯','2023-11-13 16:45:51'),(48,1,36,'하 .. 논점을 잘못 잡은 것 같네 ;;','2023-11-13 17:43:25'),(49,5,36,'롤알못이라서 모르겠어요ㅠㅠ','2023-11-13 23:02:25'),(50,71,2,'아 배 그렇게 타는거 아닌데','2023-11-14 11:58:58'),(54,83,22,'안녕 여러분','2023-11-14 13:03:23'),(55,83,36,'안녕 여러분','2023-11-14 13:03:34'),(56,83,1,'페이징 해줘','2023-11-14 13:04:09'),(57,83,1,'정렬도 시간 순으로 좀 해주고','2023-11-14 13:04:21'),(58,83,1,'볼루랄라도 해줘','2023-11-14 13:04:30'),(59,75,36,'아 투표 할라고 했는데, 마감되었네','2023-11-14 15:03:27'),(60,73,1,'창호님 빨리 대구와','2023-11-14 15:14:09'),(61,76,39,'아이언도 발언권이 있나요','2023-11-14 16:01:21'),(62,2,14,'이건 상대가 잘못했다','2023-11-14 16:06:14'),(63,9,6,'test','2023-11-14 16:11:19'),(64,75,39,'댓글 테스트 입니다.','2023-11-14 16:21:28'),(65,75,39,'test one more time','2023-11-14 16:21:43'),(66,75,22,'ASDF','2023-11-15 15:41:57'),(67,75,48,'제이스는 다이아몬드고 이렐리아는 에메랄든데 그래도 에메랄드 치고는 잘한거 아니에요 ?','2023-11-16 09:48:41'),(68,5,53,'애매하군요','2023-11-16 16:07:03'),(69,2,53,'이건 레넥톤 잘못이다.','2023-11-16 20:16:10'),(70,5,39,'Hmm','2023-11-17 09:53:36');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'squareyun@gmail.com','1234','2023-11-07 13:55:59','2023-11-07 02:16:06','2023-12-15 02:16:06');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `goods_id` int DEFAULT NULL,
  `event_id` int DEFAULT NULL,
  `name` varchar(1000) NOT NULL,
  `org_name` varchar(2000) NOT NULL,
  `extension` varchar(10) NOT NULL,
  `type` varchar(50) NOT NULL,
  `is_promise` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`file_id`),
  KEY `FK_post_TO_file_1` (`post_id`),
  KEY `FK_goods_TO_file_1` (`goods_id`),
  KEY `FK_event_TO_file_1` (`event_id`),
  CONSTRAINT `FK_event_TO_file_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  CONSTRAINT `FK_goods_TO_file_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `FK_post_TO_file_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,1,NULL,NULL,'test_video','test_video','mp4','video/mp4',0),(2,1,NULL,NULL,'54ea3e93-a3d2-4ebf-81ba-6ac3040c3cef','LoG_Game_OCE_592346059','bat','replay',0),(3,2,NULL,NULL,'0865e9d4-46d1-4c60-9efc-2cce4c3f9118','물리엔진클라스mp4','mp4','video/mp4',0),(4,2,NULL,NULL,'21b51580-1759-49c3-9ea5-770453a9e97a','LoG_Game_OCE_592346059','bat','replay',0),(5,3,NULL,NULL,'b43d5437-065d-435e-b16f-272ac18960dc','물리엔진클라스mp4','mp4','video/mp4',0),(6,3,NULL,NULL,'6130b5fc-52f5-47a9-8415-4cc92d369de6','LoG_Game_OCE_592346059','bat','replay',0),(7,4,NULL,NULL,'a778d417-278a-42ce-b2ce-2db0c2a2c235','물리엔진클라스mp4','mp4','video/mp4',0),(8,4,NULL,NULL,'cbf22519-fa97-495e-9a1a-05f154da867a','LoG_Game_OCE_592346059','bat','replay',0),(9,5,NULL,NULL,'6128e2dc-f912-4ce3-b455-0355329f2870','183271','mp4','video/mp4',0),(10,5,NULL,NULL,'d49481fe-d602-4f55-a716-c2f6557ea28f','LoG_Game_OCE_592346059','bat','replay',0),(23,NULL,30,NULL,'galaxy_A34','galaxy_A34','png','image/png',0),(24,NULL,31,NULL,'galaxy_buzz2','galaxy_buzz2','png','image/png',0),(25,NULL,32,NULL,'galaxy_S23','galaxy_S23','png','image/png',0),(26,NULL,33,NULL,'galaxy_Z_Fold5','galaxy_Z_Fold5','png','image/png',0),(27,NULL,34,NULL,'galaxy_watch6','galaxy_watch6','png','image/png',0),(28,NULL,35,NULL,'amumudoll','amumudoll','png','image/png',0),(29,NULL,36,NULL,'galaxy_buzzCase','galaxy_buzzCase','png','image/png',0),(30,NULL,37,NULL,'nikefootballhwa','nikefootballhwa','png','image/png',0),(31,NULL,38,NULL,'rockgaedoll','rockgaedoll','png','image/png',0),(32,NULL,39,NULL,'starbucksAmericanoTall','starbucksAmericanoTall','png','image/png',0),(33,NULL,40,NULL,'starbucksLatte10cnt','starbucksLatte10cnt','png','image/png',0),(34,NULL,41,NULL,'starbucksPrapuchinoDolche16cnt','starbucksPrapuchinoDolche16cnt','png','image/png',0),(35,NULL,42,NULL,'starbucksRealAmericano','starbucksRealAmericano','png','image/png',0),(36,NULL,43,NULL,'tarmoShampoo','tarmoShampoo','png','image/png',0),(37,6,NULL,NULL,'ccbb_ucc','ccbb_ucc','mp4','video/mp4',0),(38,6,NULL,NULL,'20cf960c-3283-49cc-afa1-6b91f830b7db','LoG_Game_OCE_592346059','bat','replay',0),(39,7,NULL,NULL,'015e4aa3-6017-41b3-8849-9beb8d81dff0','183271','mp4','video/mp4',0),(40,7,NULL,NULL,'9b0f1faf-849b-4af1-9d5a-d68e961611ce','LoG_Game_OCE_592346059','bat','replay',0),(41,8,NULL,NULL,'a591882c-04d8-4fc6-aa73-8a593727e170','183271','mp4','video/mp4',0),(42,8,NULL,NULL,'59202d22-4bfa-4dbf-83da-f66dcf75289a','LoG_Game_OCE_592346059','bat','replay',0),(43,9,NULL,NULL,'e6edaf5c-7ee9-476c-aedf-d07799b544e2','183271','mp4','video/mp4',0),(44,9,NULL,NULL,'c5935ec7-7e59-4aad-beaf-1c230f5c4343','LoG_Game_OCE_592346059','bat','replay',0),(45,10,NULL,NULL,'e8e14a16-ba6c-4a05-b194-e0ddac932689','183271','mp4','video/mp4',0),(46,10,NULL,NULL,'a6a2259e-e5e5-4554-80ec-af7b44a77695','nginx','conf','replay',0),(47,11,NULL,NULL,'7bce2473-4b4f-4d0f-b741-8564a377d75f','UCC','mp4','video/mp4',0),(48,11,NULL,NULL,'8b50c3fa-eeb0-4730-8d75-b940b1cdfee7','41BroI-mHhL','jpg','image/jpeg',0),(49,12,NULL,NULL,'aa3e4b8e-15a6-4a96-90ba-76bf5cd6ad69','UCC','mp4','video/mp4',0),(50,12,NULL,NULL,'136cd522-20c9-4c47-94cb-7a54977c97e9','41BroI-mHhL','jpg','image/jpeg',0),(51,13,NULL,NULL,'979f1f61-5412-4170-880a-3d30944d9cbd','UCC','mp4','video/mp4',0),(52,13,NULL,NULL,'2b5739c5-c5c8-49b0-b81c-d5472a1a3009','41BroI-mHhL','jpg','image/jpeg',0),(53,14,NULL,NULL,'ccbb_ucc','ccbb_ucc','mp4','video/mp4',0),(54,14,NULL,NULL,'b59fa1e6-cc43-4c06-9761-84fa30470228','LoG_Game_OCE_592346059','bat','replay',0),(55,15,NULL,NULL,'46545db9-c3d3-4226-8673-0c9bd22d26f1','183271','mp4','video/mp4',0),(56,16,NULL,NULL,'5f984ff0-b6c0-4405-8a70-4ed0a4233266','ccbb_ucc','mp4','video/mp4',0),(57,17,NULL,NULL,'0c2dafc8-fcda-47ea-a1b0-16f2cdf8e509','수정본1','mp4','video/mp4',0),(58,17,NULL,NULL,'86d547a8-54c1-49b2-a37e-73f8b67dc254','LoG_Game_OCE_592346059','bat','replay',0),(59,18,NULL,NULL,'051e766c-8ae0-4878-af45-d950140d0fe8','183271','mp4','video/mp4',0),(60,19,NULL,NULL,'954e3d04-a450-4829-9af5-ba96a0b23314','183271','mp4','video/mp4',0),(61,19,NULL,NULL,'ca0ac7d6-8f21-4bd3-9776-45a4af9ca863','NotificationController','java','replay',0),(62,20,NULL,NULL,'539bee0e-79aa-425b-9e42-25702a519daf','183271','mp4','video/mp4',0),(63,20,NULL,NULL,'8a8286c6-5b10-4e71-bb08-9235d93cc9c4','구미_1반_윤우혁 (1)','JPG','image/jpeg',0),(65,21,NULL,NULL,'a34ef716-7148-4b3d-9891-81f649e3d11d','구미_1반_윤우혁 (1)','JPG','image/jpeg',0),(66,22,NULL,NULL,'088dfde2-8389-4f72-ab5d-a13a04cdfadb','183271','mp4','video/mp4',0),(67,22,NULL,NULL,'20598083-7af2-498f-a8c9-41a5cd45994f','chart4','PNG','image/png',0),(68,23,NULL,NULL,'abee555c-56f4-4bc8-8f33-967aeea7473c','183271','mp4','video/mp4',0),(69,24,NULL,NULL,'bf5df36f-540e-4c7c-b09d-f36c823b529a','183271','mp4','video/mp4',0),(70,25,NULL,NULL,'aeea4d4f-19bb-4f66-a44f-0f1c9b7114b5','183271','mp4','video/mp4',0),(71,26,NULL,NULL,'ade8f7f0-602a-4906-9122-f28a5950984b','프제짱 9월 매드무비','mp4','video/mp4',0),(72,26,NULL,NULL,'f061ce4b-27a0-4f5f-a6b8-b9afe48f77eb','LoG_Game_OCE_592346059 (4)','bat','replay',0),(78,29,NULL,NULL,'f6202bc4-70e3-44c1-91a0-b3f6dcfdff35','스크린샷 2023-11-03 오전 2.19.09','png','image/png',0),(79,30,NULL,NULL,'79ddc63a-4451-4354-af6c-603ce9ad3000','화면 기록 2023-11-03 오전 1.18.56 (online-video-cutter.com)','mp4','video/mp4',0),(80,30,NULL,NULL,'5311ed4c-0888-4846-b3fd-61228daad8a5','스크린샷 2023-11-03 오전 12.21.32','png','image/png',0),(81,31,NULL,NULL,'1efa0955-8a7e-49c4-8762-f07098ff3366','화면 기록 2023-11-03 오전 1.18.56 (online-video-cutter.com)','mp4','video/mp4',0),(82,31,NULL,NULL,'a3e6eb2f-d76a-44b6-ad03-7797f729b0f4','스크린샷 2023-11-03 오전 2.19.09','png','image/png',0),(83,32,NULL,NULL,'2473a706-5bd6-43d3-a338-480d214fa2e2','화면 기록 2023-11-03 오전 1.18.56 (online-video-cutter.com)','mp4','video/mp4',0),(84,32,NULL,NULL,'13b0f8b5-fd07-41a8-a7e8-d139a0502063','스크린샷 2023-11-03 오전 2.19.09','png','image/png',0),(87,34,NULL,NULL,'c2dcf7e6-8a93-4e6e-ba70-a29272f3dec6','화면 기록 2023-11-03 오전 1.18.56 (online-video-cutter.com)','mp4','video/mp4',0),(88,34,NULL,NULL,'80498c8f-a7ac-404e-bf1b-a640e0490a82','스크린샷 2023-11-03 오전 2.19.09','png','image/png',0),(91,36,NULL,NULL,'dd040933-51dc-4745-b4fc-aecd52970eb2','바텀 라인전 2대2 상황 봐주세요.','mp4','video/mp4',0),(92,36,NULL,NULL,'1b291fd9-7872-4ebf-8eb7-515ab69e0006','LOL_OPGG_Observer_1699859819859_spectate','bat','replay',0),(93,37,NULL,NULL,'83d204c3-f28e-417f-8954-424a2ed0226e','바텀 라인전 2대2 상황 봐주세요.','mp4','video/mp4',0),(94,37,NULL,NULL,'a24f9cca-8eb4-4d3f-98fb-9518d6c762c5','LOL_OPGG_Observer_1699859819859_spectate','bat','replay',0),(95,38,NULL,NULL,'6f9bacc2-e59c-48f1-b97b-f0fb4d97ab8c','183271','mp4','video/mp4',0),(96,38,NULL,NULL,'a6869d98-47d3-424b-a3ee-962d8e27a081','LOL_OPGG_Observer_1699882167736_spectate','bat','replay',0),(97,39,NULL,NULL,'f28f734b-d18b-4271-86b9-2c29c18b7ec2','3F3F3F3F%3F.webp - Aseprite v1.3-rc6-x64 2023-11-13 15-07-05','mp4','video/mp4',0),(98,39,NULL,NULL,'8dfae57d-4bd4-4dd8-92c0-5a45dd3370a6','차이니즈_메리_포핀스-removebg-preview','png','image/png',0),(100,40,NULL,NULL,'810d0524-deae-437e-b6c9-8c0a95562f72','초안','mp4','video/mp4',0),(101,40,NULL,NULL,'db1ac771-92e9-4f30-a25f-43187b255d7d','LoG_Game_OCE_592346059','bat','replay',0),(102,41,NULL,NULL,'aef6e9d7-e776-49c7-9be8-1351120c38a0','_원딜 필수 시청_ 피지컬의 꽃! 바텀의 왕자들의 캐리쇼 _ 시네마틱 매드무비 #11','mp4','video/mp4',0),(103,41,NULL,NULL,'b4b77841-f710-42ce-8925-3d1cf641f502','LoG_Game_OCE_592346059','bat','replay',0),(104,41,NULL,NULL,'78ae64f6-ba85-4271-b2c7-c455e994fb37','ccbb_ucc','mp4','video/mp4',1),(105,38,NULL,NULL,'f029e0c4-6a1b-44ae-b6c6-a3a9d2054f0b','555 (1)','png','image/png',1),(107,36,NULL,NULL,'ee5196e3-5c74-44ba-b62e-b1092c38e468','1킬 12데스 본인이 더 잘했다고 주장하는 어느 탑 유저 【백문철 TV】','mp4','video/mp4',1),(108,42,NULL,NULL,'15f777fa-cdc8-4205-b309-8a201e2f51d4','183271','mp4','video/mp4',0),(109,43,NULL,NULL,'4976ea8f-06bf-47ce-abc2-9a7886266765','183271','mp4','video/mp4',0),(110,44,NULL,NULL,'3b8aef02-336a-4f6a-b3ba-89232d3d35de','183271','mp4','video/mp4',0),(111,45,NULL,NULL,'a448262f-a9c7-40a0-9d80-61b9658d8412','183271','mp4','video/mp4',0),(112,45,NULL,NULL,'b066bded-eba5-4367-8502-e7f981a89b9a','LoG_Game_OCE_592346059 (1)','bat','replay',0),(113,46,NULL,NULL,'a0e71496-51f1-4e0a-acc7-5e364557f03e','183271','mp4','video/mp4',0),(114,47,NULL,NULL,'0ba00ec8-1772-4908-8bd3-feb0cf229754','183271','mp4','video/mp4',0),(115,48,NULL,NULL,'048bbde8-be3b-493f-bdd9-42122e974568','이렐vs바이','mp4','video/mp4',0),(116,49,NULL,NULL,'29eebcb9-2337-4520-af3e-bcbaaee02345','115','mp4','video/mp4',0),(117,50,NULL,NULL,'de63ecc7-5b8d-4eb1-97c2-f8d1c213f025','115','mp4','video/mp4',0),(118,51,NULL,NULL,'0cf46a8c-5faa-4c97-88bb-35f50f784b08','벨베스 레넥톤','mp4','video/mp4',0),(119,51,NULL,NULL,'eafcce45-7c9f-4b33-94d6-e8fd989664dc','레넥톤 벨베스','bat','replay',0),(120,52,NULL,NULL,'7d2710b5-3601-41c0-978e-80ffef11c76e','녹화_2023_11_16_10_50_37_379','mp4','video/mp4',0),(121,52,NULL,NULL,'b73938c7-1b2f-4990-a36a-e680e051c3ed','Th','bat','replay',0),(122,53,NULL,NULL,'95cefb6b-8db6-4de9-b858-9fed2598b4b5','녹화_2023_11_16_12_37_31_765','mp4','video/mp4',0),(123,53,NULL,NULL,'5148eecd-a42c-4a3b-91a1-d93cf2160efb','LOL_OPGG_Observer_1700100402517_spectate','bat','replay',0),(124,19,NULL,NULL,'09ce3159-4006-407e-b8fc-dac42e577f84','구미_4반_6그룹_장장장(천병찬)','jpg','image/jpeg',1),(127,3,NULL,NULL,'fbb93388-274e-44fa-bdad-e9f7ecd56a52','1116testpng','jpg','image/jpeg',1),(128,54,NULL,NULL,'a497a7f3-14cf-4fa6-9ecd-77ba7fcf6e3e','122','mp4','video/mp4',0),(129,54,NULL,NULL,'da5a6cb9-d550-460f-a49f-491d0e23922d','LOL_OPGG_Observer_1700100402517_spectate','bat','replay',0),(130,14,NULL,NULL,'5dfb3ed0-0233-40fa-a272-0e3fd1b65aac','KakaoTalk_20231104_215033177','mp4','video/mp4',1);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `win_count` int NOT NULL,
  `price` int NOT NULL,
  `version` int DEFAULT NULL,
  `win_percent` int NOT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `FK_event_TO_goods_1` (`event_id`),
  CONSTRAINT `FK_event_TO_goods_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (30,1,'Galaxy_A34',5,1000,0,1),(31,1,'Galaxy_Buds2',5,500,0,1),(32,1,'Galaxy_S23',4,1000,1,1),(33,1,'Galaxy_Z_Fold',5,1500,0,1),(34,1,'Galaxy_watch6',5,500,0,1),(35,1,'아무무 인형',10,100,0,10),(36,1,'버즈 케이스',9,100,1,10),(37,1,'나이키 풋살화',10,200,0,10),(38,1,'바위게 인형',10,100,0,10),(39,1,'스타벅스_아메리카노T',12,150,3,15),(40,1,'스타벅스_라떼10',20,300,0,15),(41,1,'스타벅스_돌체16',18,300,2,15),(42,1,'스타벅스 아메리카노',18,400,2,10),(43,1,'탈모 샴푸',0,700,8,30);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  `re_comment_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `type` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `FK_post_TO_likes_1` (`post_id`),
  KEY `FK_comment_TO_likes_1` (`comment_id`),
  KEY `FK_re_comment_TO_likes_1` (`re_comment_id`),
  KEY `FK_user_TO_likes_1` (`user_id`),
  CONSTRAINT `FK_comment_TO_likes_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FK_post_TO_likes_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_re_comment_TO_likes_1` FOREIGN KEY (`re_comment_id`) REFERENCES `re_comment` (`re_comment_id`),
  CONSTRAINT `FK_user_TO_likes_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  `nt_type` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender_user_id` int NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `FK_user_TO_notification_1` (`user_id`),
  KEY `FK_user_TO_notification_2_idx` (`sender_user_id`),
  CONSTRAINT `FK_user_TO_notification_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_TO_notification_2` FOREIGN KEY (`sender_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=339 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (21,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-08 14:01:00',67),(22,1,'님이 회원님께 \'test 333\'의 투표 진행을 신청하였습니다.','/lolvote/7',1,'VOTE_REQUEST','2023-11-08 14:26:12',4),(23,1,'님이 회원님께 \'test 333\'의 투표 진행을 신청하였습니다.','/lolvote/8',1,'VOTE_REQUEST','2023-11-08 14:26:12',4),(24,1,'님이 회원님께 \'test@@\'의 투표 진행을 신청하였습니다.','/lolvote/9',1,'VOTE_REQUEST','2023-11-08 14:28:35',4),(25,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-08 15:06:54',4),(26,4,'님이 회원님이 작성하신 \'test@@\'에 답변을 달았습니다.','/lolvote/9',0,'COMMENT','2023-11-08 15:44:34',1),(27,4,'님이 회원님이 작성하신 \'test4\'에 답변을 달았습니다.','/lolvote/4',0,'COMMENT','2023-11-08 15:51:58',4),(28,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-08 16:33:07',5),(29,2,'님이 회원님께 \'윤우혁테스트\'의 투표 진행을 신청하였습니다.','/lolvote/10',1,'VOTE_REQUEST','2023-11-09 09:30:39',1),(30,12,'님이 회원님께 \'판단해주세요\'의 투표 진행을 신청하였습니다.','/lolvote/11',0,'VOTE_REQUEST','2023-11-09 10:17:35',5),(31,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-09 10:17:57',73),(32,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-09 10:19:17',73),(33,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-09 10:24:53',71),(34,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 15:30:53',3),(35,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 15:30:53',4),(36,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 15:31:28',3),(37,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 15:31:28',4),(38,1,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-09 23:08:28',2),(39,2,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-09 23:08:28',1),(40,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:09:53',3),(41,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:09:53',4),(42,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:10:37',3),(43,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:10:37',4),(44,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:10:44',4),(45,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:10:44',3),(46,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:11:18',4),(47,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:11:18',3),(48,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:11:25',3),(49,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:11:25',4),(50,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:12:19',3),(51,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:12:19',4),(52,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:12:47',4),(53,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-09 23:12:47',3),(54,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-09 23:22:16',3),(55,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-09 23:22:16',4),(56,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-09 23:22:27',4),(57,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-09 23:22:27',3),(58,12,'님이 회원님께 \'베인 블리츠크랭크 누가...\'의 투표 진행을 신청하였습니다.','/lolvote/14',0,'VOTE_REQUEST','2023-11-10 09:01:21',4),(59,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-10 09:11:26',12),(60,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-10 09:11:26',4),(61,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-10 09:12:07',4),(62,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-10 09:12:07',12),(63,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-10 09:13:04',12),(64,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-10 09:13:04',4),(65,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-10 09:16:18',4),(66,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-10 09:16:18',3),(67,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-10 09:16:29',4),(68,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-10 09:16:29',3),(69,3,'님이 회원님이 참여중인 \'test4\'에 기표하였습니다.','/lolvote/4',0,'VOTE_REJECT','2023-11-10 09:20:03',4),(70,4,'님이 회원님이 참여중인 \'test4\'에 기표하였습니다.','/lolvote/4',0,'VOTE_REJECT','2023-11-10 09:20:03',3),(71,3,'님이 회원님이 참여중인 \'test3\'에 기표하였습니다.','/lolvote/3',0,'VOTE_REJECT','2023-11-10 09:20:37',4),(72,4,'님이 회원님이 참여중인 \'test3\'에 기표하였습니다.','/lolvote/3',0,'VOTE_REJECT','2023-11-10 09:20:37',3),(73,3,'님이 회원님이 참여중인 \'test3\'에 기표하였습니다.','/lolvote/3',0,'VOTE_REJECT','2023-11-10 09:20:46',4),(74,4,'님이 회원님이 참여중인 \'test3\'에 기표하였습니다.','/lolvote/3',0,'VOTE_REJECT','2023-11-10 09:20:46',3),(75,1,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-10 09:25:18',2),(76,2,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-10 09:25:18',1),(77,4,'님이 회원님께 \'동영상 테스트1\'의 투표 진행을 신청하였습니다.','/lolvote/17',0,'VOTE_REQUEST','2023-11-10 09:29:06',12),(78,2,'님이 회원님께 \'저는 잘못이 없습니다.\'의 투표 진행을 신청하였습니다.','/lolvote/19',1,'VOTE_REQUEST','2023-11-10 09:54:34',9),(79,9,'님이 회원님이 작성한 \'저는 잘못이 없습니다.\'에 투표 진행을 승인하였습니다.','/lolvote/19',0,'VOTE_APPROVE','2023-11-10 10:26:55',13),(80,13,'님이 회원님께 \'승인 테스트\'의 투표 진행을 신청하였습니다.','/lolvote/20',0,'VOTE_REQUEST','2023-11-10 10:28:47',9),(81,13,'님이 회원님께 \'승인 테스트\'의 투표 진행을 신청하였습니다.','/lolvote/21',1,'VOTE_REQUEST','2023-11-10 10:29:26',9),(82,9,'님이 회원님이 작성한 \'승인 테스트\'에 투표 진행을 승인하였습니다.','/lolvote/21',1,'VOTE_APPROVE','2023-11-10 10:30:57',13),(83,4,'님이 회원님이 작성하신 \'베인 블리츠크랭크 누가...\'에 답변을 달았습니다.','/lolvote/14',0,'COMMENT','2023-11-10 10:47:11',12),(84,9,'님이 회원님이 참여중인 \'승인 테스트\'에 기표하였습니다.','/lolvote/21',0,'VOTE_REJECT','2023-11-10 10:56:52',13),(85,13,'님이 회원님이 참여중인 \'승인 테스트\'에 기표하였습니다.','/lolvote/21',1,'VOTE_REJECT','2023-11-10 10:56:52',9),(86,4,'님이 회원님이 작성하신 \'베인 블리츠크랭크 누가...\'에 답변을 달았습니다.','/lolvote/14',0,'COMMENT','2023-11-10 13:09:14',12),(87,9,'님이 회원님이 작성하신 \'승인 테스트\'에 답변을 달았습니다.','/lolvote/21',0,'COMMENT','2023-11-10 13:09:27',12),(88,9,'님이 회원님께 \'1110 1420 테스...\'의 투표 진행을 신청하였습니다.','/lolvote/22',1,'VOTE_REQUEST','2023-11-10 14:22:27',13),(89,13,'님이 회원님이 작성한 \'1110 1420 테스...\'에 투표 진행을 승인하였습니다.','/lolvote/22',0,'VOTE_APPROVE','2023-11-10 16:10:49',9),(90,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',1,'VOTE_REJECT','2023-11-10 16:16:18',9),(91,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-10 16:16:18',9),(92,13,'님이 회원님이 작성하신 \'1110 1420 테스...\'에 답변을 달았습니다.','/lolvote/22',0,'COMMENT','2023-11-10 17:05:43',9),(93,4,'님이 회원님이 작성하신 \'베인 블리츠크랭크 누가...\'에 답변을 달았습니다.','/lolvote/14',0,'COMMENT','2023-11-10 17:09:22',12),(94,4,'님이 회원님께 \'server테스트\'의 투표 진행을 신청하였습니다.','/lolvote/26',0,'VOTE_REQUEST','2023-11-10 17:43:40',12),(95,9,'님이 회원님께 \'1111 test1\'의 투표 진행을 신청하였습니다.','/lolvote/27',1,'VOTE_REQUEST','2023-11-11 14:30:30',11),(96,9,'님이 회원님께 \'1111 test2\'의 투표 진행을 신청하였습니다.','/lolvote/28',1,'VOTE_REQUEST','2023-11-11 14:58:30',11),(97,9,'님이 회원님께 \'1111test3\'의 투표 진행을 신청하였습니다.','/lolvote/29',0,'VOTE_REQUEST','2023-11-11 15:13:09',11),(98,9,'님이 회원님께 \'1111 test4\'의 투표 진행을 신청하였습니다.','/lolvote/30',1,'VOTE_REQUEST','2023-11-11 15:15:30',11),(99,9,'님이 회원님께 \'1111 test5\'의 투표 진행을 신청하였습니다.','/lolvote/31',1,'VOTE_REQUEST','2023-11-11 15:56:45',11),(100,9,'님이 회원님께 \'1111test6\'의 투표 진행을 신청하였습니다.','/lolvote/32',1,'VOTE_REQUEST','2023-11-11 17:36:45',11),(101,9,'님이 회원님께 \'1111test7\'의 투표 진행을 신청하였습니다.','/lolvote/33',1,'VOTE_REQUEST','2023-11-11 17:48:26',10),(102,10,'님이 회원님이 작성한 \'1111test7\'에 투표 진행을 거절하였고, 해당 게시글은 삭제되었습니다.','/',0,'VOTE_REJECT','2023-11-11 17:49:12',9),(103,4,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-11 19:50:44',11),(104,3,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-11 19:50:44',11),(105,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-11 20:58:23',1),(106,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',1,'VOTE_REJECT','2023-11-11 20:58:23',1),(107,11,'님이 회원님께 \'1111모달테스트\'의 투표 진행을 신청하였습니다.','/lolvote/34',1,'VOTE_REQUEST','2023-11-11 21:22:00',1),(108,11,'님이 회원님께 \'1111 1000 te...\'의 투표 진행을 신청하였습니다.','/lolvote/35',1,'VOTE_REQUEST','2023-11-11 22:01:25',1),(109,1,'님이 회원님이 작성한 \'1111 1000 te...\'에 투표 진행을 승인하였습니다.','/lolvote/35',1,'VOTE_APPROVE','2023-11-11 22:02:38',11),(110,1,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',0,'VOTE_REJECT','2023-11-12 15:56:33',11),(111,2,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-12 15:56:33',11),(112,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:56:48',11),(113,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:56:51',11),(114,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:56:59',11),(115,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:56:59',11),(116,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:56:59',11),(117,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:57:00',11),(118,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:57:00',11),(119,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:57:00',11),(120,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:57:00',11),(121,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-12 15:57:01',11),(122,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:09:33',12),(123,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:09:33',12),(124,4,'님이 회원님이 작성하신 \'이거 내가 맞나??\'에 답변을 달았습니다.','/lolvote/6',0,'COMMENT','2023-11-13 09:09:37',12),(125,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:09:42',12),(126,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:09:42',12),(127,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:09:56',12),(128,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:09:56',12),(129,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 09:12:50',2),(130,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 09:12:50',2),(131,4,'님이 회원님이 작성하신 \'이거 내가 맞나??\'에 답변을 달았습니다.','/lolvote/6',0,'COMMENT','2023-11-13 09:13:42',1),(132,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 09:15:23',77),(133,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 09:15:23',77),(134,4,'님이 회원님이 작성하신 \'이거 내가 맞나??\'에 답변을 달았습니다.','/lolvote/6',0,'COMMENT','2023-11-13 09:16:18',1),(135,4,'님이 회원님이 작성하신 \'이거 내가 맞나??\'에 답변을 달았습니다.','/lolvote/6',0,'COMMENT','2023-11-13 09:17:14',1),(136,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:36:40',2),(137,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 09:36:40',2),(138,9,'님이 회원님이 작성하신 \'저는 잘못이 없습니다.\'에 답변을 달았습니다.','/lolvote/19',0,'COMMENT','2023-11-13 10:01:21',1),(139,1,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',0,'VOTE_REJECT','2023-11-13 10:10:36',76),(140,2,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-13 10:10:36',76),(141,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-13 10:34:42',4),(142,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-13 10:34:42',4),(143,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-13 10:34:49',4),(144,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-13 10:34:49',4),(145,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-13 10:34:55',4),(146,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-13 10:34:55',4),(147,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:35:52',12),(148,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:35:52',12),(149,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:36:01',12),(150,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:36:01',12),(151,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:38:41',12),(152,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:38:41',12),(153,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:38:55',12),(154,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:38:55',12),(155,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:39:05',12),(156,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:39:05',12),(157,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:42:38',4),(158,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:42:38',4),(159,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:43:08',4),(160,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:43:08',4),(161,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:43:35',4),(162,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:43:35',4),(163,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 10:48:29',78),(164,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 10:48:29',78),(165,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 10:48:36',78),(166,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-13 10:48:36',78),(167,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:48:46',78),(168,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:48:46',78),(169,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:51:50',78),(170,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 10:51:50',78),(171,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:52:45',78),(172,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-13 10:52:45',78),(173,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 10:55:52',78),(174,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 10:55:52',78),(175,2,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-13 13:52:45',2),(176,1,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-13 13:52:45',2),(177,13,'님이 회원님이 작성하신 \'1110 1420 테스...\'에 답변을 달았습니다.','/lolvote/22',0,'COMMENT','2023-11-13 14:52:10',1),(178,2,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-13 15:22:05',75),(179,1,'님이 회원님이 참여중인 \'이거 한번 봐주세요\'에 기표하였습니다.','/lolvote/1',1,'VOTE_REJECT','2023-11-13 15:22:05',75),(180,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 15:59:46',1),(181,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 15:59:46',1),(182,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 16:33:16',75),(183,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 16:33:16',75),(184,12,'님이 회원님께 \'바텀 라인전 2대2 상...\'의 투표 진행을 신청하였습니다.','/lolvote/36',1,'VOTE_REQUEST','2023-11-13 16:37:49',1),(185,1,'님이 회원님이 작성한 \'바텀 라인전 2대2 상...\'에 투표 진행을 승인하였습니다.','/lolvote/36',0,'VOTE_APPROVE','2023-11-13 16:40:55',12),(186,12,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 16:44:13',2),(187,1,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 16:44:13',2),(188,12,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 16:44:32',11),(189,1,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 16:44:32',11),(190,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',0,'COMMENT','2023-11-13 16:45:51',11),(191,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',0,'COMMENT','2023-11-13 16:45:51',11),(192,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',0,'COMMENT','2023-11-13 16:45:52',11),(193,1,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 16:55:59',75),(194,12,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 16:55:59',75),(195,1,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 17:01:42',5),(196,12,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 17:01:42',5),(197,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 17:08:39',5),(198,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-13 17:08:39',5),(199,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 17:40:19',78),(200,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 17:40:19',78),(201,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 17:40:27',78),(202,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 17:40:27',78),(203,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 17:41:09',78),(204,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-13 17:41:09',78),(205,12,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 17:41:31',78),(206,1,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-13 17:41:31',78),(207,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',1,'COMMENT','2023-11-13 17:43:25',1),(208,10,'님이 회원님께 \'ㅋ별거없노\'의 투표 진행을 신청하였습니다.','/lolvote/38',0,'VOTE_REQUEST','2023-11-13 22:30:41',75),(209,75,'님이 회원님이 작성한 \'ㅋ별거없노\'에 투표 진행을 승인하였습니다.','/lolvote/38',1,'VOTE_APPROVE','2023-11-13 22:32:24',10),(210,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',0,'COMMENT','2023-11-13 23:02:25',5),(211,1,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-14 10:15:05',10),(212,12,'님이 회원님이 참여중인 \'바텀 라인전 2대2 상...\'에 기표하였습니다.','/lolvote/36',0,'VOTE_REJECT','2023-11-14 10:15:05',10),(213,4,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-14 11:58:22',71),(214,3,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-14 11:58:22',71),(215,4,'님이 회원님이 작성하신 \'test2\'에 답변을 달았습니다.','/lolvote/2',0,'COMMENT','2023-11-14 11:58:58',71),(216,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 13:00:49',83),(217,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 13:00:54',83),(218,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-14 13:02:29',83),(219,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-14 13:02:29',83),(220,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 13:03:06',83),(221,13,'님이 회원님이 작성하신 \'1110 1420 테스...\'에 답변을 달았습니다.','/lolvote/22',0,'COMMENT','2023-11-14 13:03:23',83),(222,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',0,'COMMENT','2023-11-14 13:03:34',83),(223,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 13:04:09',83),(224,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 13:04:21',83),(225,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 13:04:30',83),(226,1,'님이 회원님이 작성하신 \'바텀 라인전 2대2 상...\'에 답변을 달았습니다.','/lolvote/36',0,'COMMENT','2023-11-14 15:03:27',75),(227,2,'님이 회원님이 작성하신 \'이거 한번 봐주세요\'에 답변을 달았습니다.','/lolvote/1',1,'COMMENT','2023-11-14 15:14:09',73),(228,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-14 15:18:42',10),(229,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-14 15:18:42',10),(230,73,'님이 회원님께 \'임포스터\'의 투표 진행을 신청하였습니다.','/lolvote/39',1,'VOTE_REQUEST','2023-11-14 15:25:58',71),(231,71,'님이 회원님이 작성한 \'임포스터\'에 투표 진행을 승인하였습니다.','/lolvote/39',0,'VOTE_APPROVE','2023-11-14 15:56:41',73),(232,71,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 15:57:13',2),(233,73,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 15:57:13',2),(234,73,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 15:57:44',73),(235,71,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 15:57:44',73),(236,73,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 16:01:18',76),(237,71,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 16:01:18',76),(238,71,'님이 회원님이 작성하신 \'임포스터\'에 답변을 달았습니다.','/lolvote/39',0,'COMMENT','2023-11-14 16:01:21',76),(239,4,'님이 회원님이 작성하신 \'베인 블리츠크랭크 누가...\'에 답변을 달았습니다.','/lolvote/14',0,'COMMENT','2023-11-14 16:06:14',2),(240,4,'님이 회원님이 작성하신 \'이거 내가 맞나??\'에 답변을 달았습니다.','/lolvote/6',0,'COMMENT','2023-11-14 16:11:19',9),(241,3,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-14 16:20:33',10),(242,4,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-14 16:20:33',10),(243,71,'님이 회원님이 작성하신 \'임포스터\'에 답변을 달았습니다.','/lolvote/39',0,'COMMENT','2023-11-14 16:21:28',75),(244,71,'님이 회원님이 작성하신 \'임포스터\'에 답변을 달았습니다.','/lolvote/39',1,'COMMENT','2023-11-14 16:21:43',75),(245,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-14 16:23:38',10),(246,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-14 16:23:38',10),(247,71,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',1,'VOTE_REJECT','2023-11-14 16:24:37',10),(248,73,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 16:24:37',10),(249,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-14 16:24:45',10),(250,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-14 16:24:45',10),(251,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-14 16:31:52',10),(252,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-14 16:31:52',10),(253,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-14 17:01:57',75),(254,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-14 17:01:57',75),(255,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-14 17:11:15',75),(256,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-14 17:11:15',75),(257,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-14 17:31:32',84),(258,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-14 17:31:32',84),(259,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-14 17:32:32',84),(260,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-14 17:32:32',84),(261,3,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-14 17:32:46',84),(262,4,'님이 회원님이 참여중인 \'테스트테스트테스트테스트...\'에 기표하였습니다.','/lolvote/5',0,'VOTE_REJECT','2023-11-14 17:32:46',84),(263,73,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 17:42:21',84),(264,71,'님이 회원님이 참여중인 \'임포스터\'에 기표하였습니다.','/lolvote/39',0,'VOTE_REJECT','2023-11-14 17:42:21',84),(265,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-14 17:47:53',7),(266,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-14 17:47:53',7),(267,4,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-14 17:48:44',13),(268,3,'님이 회원님이 참여중인 \'이거 내가 맞나??\'에 기표하였습니다.','/lolvote/6',0,'VOTE_REJECT','2023-11-14 17:48:44',13),(269,3,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-14 17:48:56',13),(270,4,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-14 17:48:56',13),(271,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-14 17:49:27',13),(272,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-14 17:49:27',13),(273,9,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-15 08:59:32',85),(274,13,'님이 회원님이 참여중인 \'저는 잘못이 없습니다.\'에 기표하였습니다.','/lolvote/19',0,'VOTE_REJECT','2023-11-15 08:59:32',85),(275,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-15 09:01:57',85),(276,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-15 09:01:57',85),(277,4,'님이 회원님께 \'글작성 테스트\'의 투표 진행을 신청하였습니다.','/lolvote/40',0,'VOTE_REQUEST','2023-11-15 13:47:13',12),(278,4,'님이 회원님께 \'http://local...\'의 투표 진행을 신청하였습니다.','/lolvote/41',0,'VOTE_REQUEST','2023-11-15 13:57:24',12),(279,4,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-15 14:27:25',2),(280,3,'님이 회원님이 참여중인 \'test2\'에 기표하였습니다.','/lolvote/2',0,'VOTE_REJECT','2023-11-15 14:27:25',2),(281,4,'님이 회원님이 참여중인 \'test4\'에 기표하였습니다.','/lolvote/4',0,'VOTE_REJECT','2023-11-15 15:11:36',86),(282,3,'님이 회원님이 참여중인 \'test4\'에 기표하였습니다.','/lolvote/4',0,'VOTE_REJECT','2023-11-15 15:11:36',86),(283,12,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',0,'VOTE_REJECT','2023-11-15 15:13:58',86),(284,4,'님이 회원님이 참여중인 \'베인 블리츠크랭크 누가...\'에 기표하였습니다.','/lolvote/14',1,'VOTE_REJECT','2023-11-15 15:13:58',86),(285,13,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-15 15:16:06',86),(286,9,'님이 회원님이 참여중인 \'1110 1420 테스...\'에 기표하였습니다.','/lolvote/22',0,'VOTE_REJECT','2023-11-15 15:16:06',86),(287,13,'님이 회원님이 작성하신 \'1110 1420 테스...\'에 답변을 달았습니다.','/lolvote/22',0,'COMMENT','2023-11-15 15:41:57',75),(288,75,'님이 회원님께 \'1115test\'의 투표 진행을 신청하였습니다.','/lolvote/43',0,'VOTE_REQUEST','2023-11-15 16:01:18',9),(289,75,'님이 회원님께 \'1115test2\'의 투표 진행을 신청하였습니다.','/lolvote/44',0,'VOTE_REQUEST','2023-11-15 16:14:13',9),(290,75,'님이 회원님께 \'test1414\'의 투표 진행을 신청하였습니다.','/lolvote/45',1,'VOTE_REQUEST','2023-11-15 16:24:27',9),(291,75,'님이 회원님께 \'1115test3\'의 투표 진행을 신청하였습니다.','/lolvote/47',1,'VOTE_REQUEST','2023-11-15 16:44:54',9),(292,9,'님이 회원님이 작성한 \'1115test3\'에 투표 진행을 승인하였습니다.','/lolvote/47',0,'VOTE_APPROVE','2023-11-15 16:45:45',75),(293,12,'님이 회원님께 \'억울해서 잠이안와요\'의 투표 진행을 신청하였습니다.','/lolvote/48',1,'VOTE_REQUEST','2023-11-15 17:39:14',11),(294,11,'님이 회원님이 작성한 \'억울해서 잠이안와요\'에 투표 진행을 승인하였습니다.','/lolvote/48',0,'VOTE_APPROVE','2023-11-15 17:41:10',12),(295,7,'님이 회원님께 \'캐싱 업로드 제외 테스...\'의 투표 진행을 신청하였습니다.','/lolvote/49',0,'VOTE_REQUEST','2023-11-15 17:48:09',2),(296,7,'님이 회원님께 \'캐싱 업로드 제외 테스...\'의 투표 진행을 신청하였습니다.','/lolvote/50',0,'VOTE_REQUEST','2023-11-15 17:50:50',2),(297,11,'님이 회원님이 참여중인 \'억울해서 잠이안와요\'에 기표하였습니다.','/lolvote/48',0,'VOTE_REJECT','2023-11-16 09:23:21',1),(298,12,'님이 회원님이 참여중인 \'억울해서 잠이안와요\'에 기표하였습니다.','/lolvote/48',0,'VOTE_REJECT','2023-11-16 09:23:21',1),(299,11,'님이 회원님이 작성하신 \'억울해서 잠이안와요\'에 답변을 달았습니다.','/lolvote/48',0,'COMMENT','2023-11-16 09:48:41',75),(300,11,'님이 회원님이 참여중인 \'억울해서 잠이안와요\'에 기표하였습니다.','/lolvote/48',0,'VOTE_REJECT','2023-11-16 09:49:17',75),(301,12,'님이 회원님이 참여중인 \'억울해서 잠이안와요\'에 기표하였습니다.','/lolvote/48',0,'VOTE_REJECT','2023-11-16 09:49:17',75),(302,8,'님이 회원님께 \'다이브 상황 봐주세요\'의 투표 진행을 신청하였습니다.','/lolvote/51',0,'VOTE_REQUEST','2023-11-16 10:45:00',11),(303,11,'님이 회원님이 작성한 \'다이브 상황 봐주세요\'에 투표 진행을 승인하였습니다.','/lolvote/51',0,'VOTE_APPROVE','2023-11-16 10:47:30',8),(304,11,'님이 회원님이 참여중인 \'다이브 상황 봐주세요\'에 기표하였습니다.','/lolvote/51',0,'VOTE_REJECT','2023-11-16 10:48:31',1),(305,8,'님이 회원님이 참여중인 \'다이브 상황 봐주세요\'에 기표하였습니다.','/lolvote/51',0,'VOTE_REJECT','2023-11-16 10:48:31',1),(306,11,'님이 회원님이 참여중인 \'다이브 상황 봐주세요\'에 기표하였습니다.','/lolvote/51',0,'VOTE_REJECT','2023-11-16 10:50:07',8),(307,8,'님이 회원님이 참여중인 \'다이브 상황 봐주세요\'에 기표하였습니다.','/lolvote/51',0,'VOTE_REJECT','2023-11-16 10:50:07',8),(308,88,'님이 회원님께 \'1렙 라인 밀어야 했는...\'의 투표 진행을 신청하였습니다.','/lolvote/52',1,'VOTE_REQUEST','2023-11-16 11:01:19',1),(309,1,'님이 회원님이 작성한 \'1렙 라인 밀어야 했는...\'에 투표 진행을 승인하였습니다.','/lolvote/52',0,'VOTE_APPROVE','2023-11-16 11:02:29',88),(310,1,'님이 회원님이 참여중인 \'1렙 라인 밀어야 했는...\'에 기표하였습니다.','/lolvote/52',0,'VOTE_REJECT','2023-11-16 11:02:37',1),(311,88,'님이 회원님이 참여중인 \'1렙 라인 밀어야 했는...\'에 기표하였습니다.','/lolvote/52',1,'VOTE_REJECT','2023-11-16 11:02:37',1),(312,8,'님이 회원님께 \'조금 애매한 상황\'의 투표 진행을 신청하였습니다.','/lolvote/53',1,'VOTE_REQUEST','2023-11-16 12:51:55',12),(313,12,'님이 회원님이 참여중인 \'억울해서 잠이안와요\'에 기표하였습니다.','/lolvote/48',0,'VOTE_REJECT','2023-11-16 13:07:10',80),(314,11,'님이 회원님이 참여중인 \'억울해서 잠이안와요\'에 기표하였습니다.','/lolvote/48',0,'VOTE_REJECT','2023-11-16 13:07:10',80),(315,12,'님이 회원님이 작성한 \'조금 애매한 상황\'에 투표 진행을 승인하였습니다.','/lolvote/53',0,'VOTE_APPROVE','2023-11-16 13:08:40',8),(316,8,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 13:08:59',80),(317,12,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 13:08:59',80),(318,12,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 13:09:26',8),(319,8,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 13:09:26',8),(320,1,'님이 회원님이 참여중인 \'1렙 라인 밀어야 했는...\'에 기표하였습니다.','/lolvote/52',0,'VOTE_REJECT','2023-11-16 13:29:40',9),(321,88,'님이 회원님이 참여중인 \'1렙 라인 밀어야 했는...\'에 기표하였습니다.','/lolvote/52',0,'VOTE_REJECT','2023-11-16 13:29:40',9),(322,12,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 13:35:09',2),(323,8,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',1,'VOTE_REJECT','2023-11-16 13:35:09',2),(324,12,'님이 회원님이 작성하신 \'조금 애매한 상황\'에 답변을 달았습니다.','/lolvote/53',0,'COMMENT','2023-11-16 16:07:03',5),(325,12,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 16:07:06',5),(326,8,'님이 회원님이 참여중인 \'조금 애매한 상황\'에 기표하였습니다.','/lolvote/53',0,'VOTE_REJECT','2023-11-16 16:07:06',5),(327,8,'님이 회원님이 참여중인 \'다이브 상황 봐주세요\'에 기표하였습니다.','/lolvote/51',0,'VOTE_REJECT','2023-11-16 16:17:12',5),(328,11,'님이 회원님이 참여중인 \'다이브 상황 봐주세요\'에 기표하였습니다.','/lolvote/51',0,'VOTE_REJECT','2023-11-16 16:17:12',5),(329,88,'님이 회원님이 참여중인 \'1렙 라인 밀어야 했는...\'에 기표하였습니다.','/lolvote/52',0,'VOTE_REJECT','2023-11-16 16:50:44',5),(330,1,'님이 회원님이 참여중인 \'1렙 라인 밀어야 했는...\'에 기표하였습니다.','/lolvote/52',0,'VOTE_REJECT','2023-11-16 16:50:44',5),(331,12,'님이 회원님이 작성하신 \'조금 애매한 상황\'에 답변을 달았습니다.','/lolvote/53',0,'COMMENT','2023-11-16 20:16:11',2),(332,7,'님이 회원님께 \'레넥톤은 살 수 있었나\'의 투표 진행을 신청하였습니다.','/lolvote/54',1,'VOTE_REQUEST','2023-11-16 20:39:42',2),(333,2,'님이 회원님이 작성한 \'레넥톤은 살 수 있었나\'에 투표 진행을 승인하였습니다.','/lolvote/54',0,'VOTE_APPROVE','2023-11-16 20:41:15',7),(334,2,'님이 회원님이 참여중인 \'레넥톤은 살 수 있었나\'에 기표하였습니다.','/lolvote/54',0,'VOTE_REJECT','2023-11-16 20:41:44',7),(335,7,'님이 회원님이 참여중인 \'레넥톤은 살 수 있었나\'에 기표하였습니다.','/lolvote/54',0,'VOTE_REJECT','2023-11-16 20:41:44',7),(336,2,'님이 회원님이 참여중인 \'레넥톤은 살 수 있었나\'에 기표하였습니다.','/lolvote/54',0,'VOTE_REJECT','2023-11-16 20:41:54',2),(337,7,'님이 회원님이 참여중인 \'레넥톤은 살 수 있었나\'에 기표하였습니다.','/lolvote/54',0,'VOTE_REJECT','2023-11-16 20:41:54',2),(338,71,'님이 회원님이 작성하신 \'임포스터\'에 답변을 달았습니다.','/lolvote/39',0,'COMMENT','2023-11-17 09:53:36',5);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participants` (
  `participants_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `user_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`participants_id`),
  KEY `FK_event_TO_participants_1` (`event_id`),
  KEY `FK_user_TO_participants_1` (`user_id`),
  KEY `FK_goods_TO_participants_1` (`goods_id`),
  CONSTRAINT `FK_event_TO_participants_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  CONSTRAINT `FK_goods_TO_participants_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `FK_user_TO_participants_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
INSERT INTO `participants` VALUES (1,1,2,42,'2023-11-13 09:24:20'),(2,1,75,36,'2023-11-13 21:37:19'),(3,1,75,41,'2023-11-14 16:23:32'),(4,1,86,39,'2023-11-15 15:14:52');
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_history`
--

DROP TABLE IF EXISTS `payment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_history` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `vote_id` int DEFAULT NULL,
  `amount` int NOT NULL,
  `is_returned` tinyint(1) NOT NULL,
  `pay_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tid` varchar(100) NOT NULL,
  `is_approve` tinyint(1) NOT NULL DEFAULT '0',
  `partner_order_id` varchar(100) NOT NULL,
  PRIMARY KEY (`history_id`),
  KEY `FK_user_TO_payment_history_1` (`user_id`),
  KEY `FK_vote_TO_payment_history_1` (`vote_id`),
  CONSTRAINT `FK_user_TO_payment_history_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_vote_TO_payment_history_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_history`
--

LOCK TABLES `payment_history` WRITE;
/*!40000 ALTER TABLE `payment_history` DISABLE KEYS */;
INSERT INTO `payment_history` VALUES (1,2,1,10000,0,'2023-11-07 14:12:06','T549c72651b66fd4a5c0',1,'e3f9ae53-0ab9-4322-b8d2-4b812e173f3b'),(2,4,2,10000,0,'2023-11-07 14:14:30','T549c7b62b8b1abbab4e',0,'27e501e3-ca3a-446f-bab8-c3b894415804'),(3,4,NULL,10000,1,'2023-11-07 14:15:17','T549c7e52b8b1abbab54',1,'8d7d3dfb-0560-4fcb-8029-ac50e08dad45'),(4,4,4,10000,0,'2023-11-07 14:18:16','T549c89851b66fd4a5dd',1,'fa3fb0a7-ace0-47dd-81dd-dccc7043a3d0'),(5,4,5,10000,0,'2023-11-07 15:26:16','T549d8872b8b1abbac5f',0,'a2bb7fe4-c213-4277-a6f3-3a99e3af55b3'),(6,4,6,10000,0,'2023-11-08 10:45:40','T54ae84451b66fd4afb4',0,'586d4e7b-1d27-4f72-825d-13627d4b9038'),(7,4,7,10000,0,'2023-11-08 14:26:12','T54b1bf42b8b1abbb91b',0,'2008d722-fb03-4ae8-a004-b8ca56549701'),(8,4,NULL,10000,1,'2023-11-08 14:26:13','T54b1bf42b8b1abbb91c',1,'16b5c18c-13ae-46c9-b858-3a46547fb0b3'),(9,4,NULL,10000,1,'2023-11-08 14:28:35','T54b1c832b8b1abbb921',1,'55661bfd-bd5e-4f59-8b7b-661244f5a819'),(10,1,10,10000,0,'2023-11-09 09:30:39','T54c282f51b66fd4bd19',1,'0f12e790-b1c8-486e-8258-9a19620728a6'),(11,5,11,10000,0,'2023-11-09 10:17:35','T54c332f51b66fd4bd91',1,'7dab09fa-c7d8-4758-b779-db6347cf03d8'),(12,1,10,10000,0,'2023-11-09 15:16:00','T54c792051b66fd4c224',0,'3a02b526-fe02-441f-afaa-0b4e44dae149'),(13,1,10,10000,0,'2023-11-09 15:16:01','T54c79202b8b1abbc794',0,'cad14017-057d-4c0e-ab94-4a977794ba65'),(14,1,10,10000,0,'2023-11-09 15:16:01','T54c792051b66fd4c225',0,'6731a471-4b68-4e4f-9514-65dc0bf0ca43'),(15,1,10,10000,0,'2023-11-09 15:16:01','T54c79212b8b1abbc795',0,'1da3ac48-9cbb-478c-9f55-d7b083b01b26'),(16,1,10,10000,0,'2023-11-09 15:16:01','T54c792151b66fd4c226',0,'473e632f-21a6-41c3-a769-79f158351660'),(17,1,10,10000,0,'2023-11-09 15:16:02','T54c792151b66fd4c227',0,'3932fdad-93c7-4a01-bccc-04e403dbb7df'),(18,1,10,10000,0,'2023-11-09 15:16:02','T54c792151b66fd4c228',0,'b0e10d8c-752a-4c93-a65c-bfcdbd620c50'),(19,1,10,10000,0,'2023-11-09 15:16:02','T54c79212b8b1abbc796',0,'cb22a076-f731-4598-9d5d-aa67ee2b80fb'),(20,1,10,10000,0,'2023-11-09 15:16:02','T54c79212b8b1abbc797',0,'f06ee411-ae3e-4061-9a01-4040b4151be4'),(21,1,10,10000,0,'2023-11-09 15:16:02','T54c79222b8b1abbc798',0,'3dfa0c80-a09a-41c2-9a25-b4678f964ae9'),(22,1,10,10000,0,'2023-11-09 15:16:02','T54c79222b8b1abbc799',0,'05402cf1-35e6-4748-b58b-babc7b1c3a55'),(23,1,10,10000,0,'2023-11-09 15:16:02','T54c792251b66fd4c229',0,'d1b16b29-c742-4eb0-88cd-0a3ec845ce49'),(24,1,10,10000,0,'2023-11-09 15:16:02','T54c792251b66fd4c22a',0,'5082b320-d4f6-4581-be28-518c5545cf57'),(25,1,10,10000,0,'2023-11-09 15:16:03','T54c792251b66fd4c22b',0,'fb343d32-92f2-414b-9fe8-104885462300'),(26,1,10,10000,0,'2023-11-09 15:16:03','T54c792251b66fd4c22c',0,'e5a78c2b-288a-4b9b-96f6-e60b0ee60b7d'),(27,1,10,10000,0,'2023-11-09 15:16:03','T54c792251b66fd4c22d',0,'4b449ff7-318f-440f-93d3-c0a590180c6a'),(28,1,10,10000,0,'2023-11-09 15:16:03','T54c792251b66fd4c22e',0,'d59e3003-de09-40c0-82e6-79fdc285f84c'),(29,1,10,10000,0,'2023-11-09 15:16:03','T54c79222b8b1abbc79b',0,'9e20c4b6-58d1-47e3-ae87-6645df50fed9'),(30,1,10,10000,0,'2023-11-09 15:16:03','T54c79232b8b1abbc79c',0,'399a49be-0e88-4154-ae37-59a15f2bc1f4'),(31,1,10,10000,0,'2023-11-09 15:16:03','T54c79232b8b1abbc79d',0,'d29d5781-17e1-4db8-901a-ab3207c26ee3'),(32,1,10,10000,0,'2023-11-09 15:16:03','T54c79232b8b1abbc79e',0,'63e83ad4-8f51-41d9-9aff-e28849318377'),(33,1,10,10000,0,'2023-11-09 15:16:04','T54c79232b8b1abbc79f',0,'c2afb3b9-f32a-45f9-9a07-e37ab6911b72'),(34,1,10,10000,0,'2023-11-09 15:16:04','T54c792351b66fd4c22f',0,'927d3c12-191f-4eae-bc38-704f2df45c1d'),(35,1,10,10000,0,'2023-11-09 15:16:04','T54c792351b66fd4c230',0,'322f6329-7e00-482f-926a-8b974ba9fdab'),(36,1,10,10000,0,'2023-11-09 15:16:04','T54c792351b66fd4c231',0,'9d4e6dc9-16e9-4c91-a191-577dd1bcbcec'),(37,1,10,10000,0,'2023-11-09 15:16:04','T54c792351b66fd4c232',0,'d0ff1093-2170-439b-a3e8-e7d6326dbe8f'),(38,1,10,10000,0,'2023-11-09 15:16:04','T54c79242b8b1abbc7a0',0,'fd84789a-c96e-456f-b68c-b1f5b41699e0'),(39,1,10,10000,0,'2023-11-09 15:16:04','T54c792451b66fd4c233',0,'4f3fc91d-a122-4006-822a-38e2bd7d76e9'),(40,1,10,10000,0,'2023-11-09 15:16:04','T54c792451b66fd4c234',0,'f4f0fd77-2f2b-4aaf-b996-f9dfb46d2612'),(41,1,10,10000,0,'2023-11-09 15:16:05','T54c79242b8b1abbc7a1',0,'68fbaf75-afb3-4663-8b42-9a308ea7737d'),(42,1,10,10000,0,'2023-11-09 15:16:05','T54c79242b8b1abbc7a2',0,'ea207c15-b75d-4fc0-8a84-55baba0b02fe'),(43,1,10,10000,0,'2023-11-09 15:16:05','T54c79242b8b1abbc7a3',0,'a45e1022-d3b9-4bcd-9a6c-75b94227ca45'),(44,1,10,10000,0,'2023-11-09 15:16:08','T54c79282b8b1abbc7a4',0,'13cec87c-f073-4a7f-a72a-ed8073b815d8'),(45,1,10,10000,0,'2023-11-09 15:16:09','T54c792851b66fd4c235',0,'47f7fed8-4684-46b0-9660-748be446d0ee'),(46,1,10,10000,0,'2023-11-09 15:16:09','T54c79282b8b1abbc7a5',0,'014ccff4-1d3b-457c-bf4f-333d2080bc65'),(47,1,10,10000,0,'2023-11-09 15:16:09','T54c792951b66fd4c236',0,'ae0f0999-20ee-4c61-b38a-312b1d7e8077'),(48,1,10,10000,0,'2023-11-09 15:16:10','T54c792951b66fd4c237',0,'3948502d-9c72-4ef5-9c85-362a35db3179'),(49,1,10,10000,0,'2023-11-09 15:16:10','T54c79292b8b1abbc7a6',0,'32d8380b-0e33-489a-a9a3-934aa89237d9'),(50,1,10,10000,0,'2023-11-09 15:16:10','T54c792a51b66fd4c238',0,'e1291c30-df7e-4426-a213-9c9cc3200f81'),(51,1,10,10000,0,'2023-11-09 15:16:11','T54c792a51b66fd4c239',0,'c94bd5c8-cd28-48dc-ac51-c6f109375d4f'),(52,1,10,10000,0,'2023-11-09 15:16:11','T54c792b2b8b1abbc7a7',0,'6eb0143e-7a1b-4aea-8bb5-6c73f32870a2'),(53,1,10,10000,0,'2023-11-09 15:16:11','T54c792b51b66fd4c23a',0,'65beba21-b588-444a-8db4-5fd9ae423dd5'),(54,1,10,10000,0,'2023-11-09 15:16:12','T54c792b2b8b1abbc7a9',0,'851691b3-63c6-4285-a153-ce0823cfe339'),(55,1,10,10000,0,'2023-11-09 15:16:12','T54c792c2b8b1abbc7aa',0,'18d179da-119b-42c0-bc23-d080cb69906e'),(56,1,10,10000,0,'2023-11-09 15:16:12','T54c792c51b66fd4c23b',0,'e754d569-a494-43cc-addc-07b90b245a81'),(57,1,10,10000,0,'2023-11-09 15:16:12','T54c792c2b8b1abbc7ab',0,'430b495c-2012-4d5b-9fff-df2893f7f21b'),(58,1,10,10000,0,'2023-11-09 15:16:13','T54c792c2b8b1abbc7ac',0,'015603d9-b55b-481d-8468-ab963f6da9a2'),(59,1,10,10000,0,'2023-11-09 15:16:13','T54c792c2b8b1abbc7ad',0,'4e156281-f284-45ca-8421-1b33492b0579'),(60,1,10,10000,0,'2023-11-09 15:16:13','T54c792d51b66fd4c23c',0,'daf313de-ba27-4610-938f-4974a693f9e9'),(61,1,10,10000,0,'2023-11-09 15:16:13','T54c792d2b8b1abbc7ae',0,'0184cbbc-2cdc-42e0-87e6-1bcce1127ac8'),(62,1,10,10000,0,'2023-11-09 15:16:14','T54c792d51b66fd4c23d',0,'52c0f4af-6224-43f9-8159-0effb760e644'),(63,1,10,10000,0,'2023-11-09 15:16:14','T54c792d51b66fd4c23e',0,'06862fb8-082b-493c-ae94-0c416f938b1f'),(64,1,10,10000,0,'2023-11-09 15:16:14','T54c792d51b66fd4c23f',0,'1eca3bc4-3456-4876-9612-e16924090702'),(65,1,10,10000,0,'2023-11-09 15:16:14','T54c792e51b66fd4c240',0,'24d6a287-c683-4467-8409-ac580b70fee7'),(66,1,10,10000,0,'2023-11-09 15:16:14','T54c792e2b8b1abbc7af',0,'6dba4429-1cf0-45d7-95c1-d26964b7ff22'),(67,1,10,10000,0,'2023-11-09 15:16:14','T54c792e2b8b1abbc7b0',0,'524b1207-504e-4e6c-99da-7181f3679975'),(68,1,10,10000,0,'2023-11-09 15:16:14','T54c792e2b8b1abbc7b1',0,'2106bb60-0930-4b38-a456-7ec8261a5b68'),(69,1,10,10000,0,'2023-11-09 15:16:14','T54c792e51b66fd4c241',0,'92e3870e-119e-4ddb-9fc7-a8252f89d312'),(70,1,10,10000,0,'2023-11-09 15:16:14','T54c792e51b66fd4c242',0,'1a253a2c-4b03-4c0f-85ca-34c686a1e1f7'),(71,1,10,10000,0,'2023-11-09 15:16:44','T54c794b51b66fd4c243',0,'69e71269-0793-4956-a60a-ca0129f07f08'),(72,1,10,10000,0,'2023-11-09 15:16:44','T54c794b2b8b1abbc7b4',0,'5c6b94b1-400d-4c32-9f96-1e1d738eef97'),(73,1,10,10000,0,'2023-11-09 15:16:44','T54c794c2b8b1abbc7b5',0,'66fa4157-50b2-4cef-9bd1-475b0ec7e672'),(74,1,10,10000,0,'2023-11-09 15:16:45','T54c794c51b66fd4c244',0,'7371debb-ab9c-4074-9625-276845e446a2'),(75,1,10,10000,0,'2023-11-09 15:16:45','T54c794c51b66fd4c245',0,'35dbfde6-bb53-4c20-9df0-40ae437918ab'),(76,1,10,10000,0,'2023-11-09 15:16:45','T54c794d51b66fd4c246',0,'ecd0ee47-44d7-4c27-9bdd-10d6dd17a1d8'),(77,1,10,10000,0,'2023-11-09 15:16:46','T54c794d51b66fd4c247',0,'ca7b7012-71ae-4f02-8010-53a23b720bc9'),(78,1,10,10000,0,'2023-11-09 15:16:46','T54c794e2b8b1abbc7b6',0,'5228ee89-cbf3-4dd0-af84-ab871c94f4ea'),(79,1,10,10000,0,'2023-11-09 15:16:46','T54c794e2b8b1abbc7b7',0,'49619e34-bdeb-48d5-b18e-a72aea1d2c05'),(80,1,10,10000,0,'2023-11-09 15:16:47','T54c794e2b8b1abbc7b8',0,'d7fc716f-92dd-4197-b86c-c49c34100c58'),(81,1,10,10000,0,'2023-11-09 15:16:47','T54c794f2b8b1abbc7b9',0,'ee609522-ffd1-4f12-bb72-5293512a52cb'),(82,1,10,10000,0,'2023-11-09 15:16:47','T54c794f51b66fd4c248',0,'4db2d6d6-b5d3-4cd7-9776-4c5a14a37dbe'),(83,1,10,10000,0,'2023-11-09 15:16:48','T54c794f51b66fd4c249',0,'48ffc07e-78f1-4c29-8752-6c0f1962487d'),(84,1,10,10000,0,'2023-11-09 15:16:48','T54c795051b66fd4c24a',0,'61c5e74c-4b1a-4a6b-acac-ad810b011db7'),(85,1,10,10000,0,'2023-11-09 15:16:48','T54c79502b8b1abbc7ba',0,'e1a7f088-13dc-457b-84e8-2d002678f847'),(86,1,10,10000,0,'2023-11-09 15:16:49','T54c795151b66fd4c24b',0,'23a4bff4-a6df-4001-8a39-9e13fe0ae35a'),(87,1,10,10000,0,'2023-11-09 15:16:50','T54c79512b8b1abbc7bb',0,'38c05693-826e-4eb5-a32c-263b75238883'),(88,1,10,10000,0,'2023-11-09 15:16:50','T54c795151b66fd4c24c',0,'b4a05500-4b7c-422c-91b3-ccea0ec359d2'),(89,1,10,10000,0,'2023-11-09 15:16:50','T54c795251b66fd4c24d',0,'42d3c06a-a4df-47c2-9d5e-c97b541329b6'),(90,1,10,10000,0,'2023-11-09 15:16:51','T54c79522b8b1abbc7bc',0,'7433bf6b-79dc-444a-89cb-bd08f29b4737'),(91,1,10,10000,0,'2023-11-09 15:16:51','T54c79522b8b1abbc7bd',0,'7e497487-61bd-4942-9d0d-a94634b65fef'),(92,1,10,10000,0,'2023-11-09 15:16:51','T54c795251b66fd4c24e',0,'998d2510-32b7-42cd-94bd-7d46282aa2d0'),(93,1,10,10000,0,'2023-11-09 15:16:51','T54c795351b66fd4c24f',0,'51b311fa-d624-4dc2-b660-5eb35adb8e73'),(94,1,10,10000,0,'2023-11-09 15:16:51','T54c79532b8b1abbc7be',0,'acc809e2-a0a0-40d8-a135-4cecddc6c21b'),(95,1,10,10000,0,'2023-11-09 15:16:52','T54c795351b66fd4c250',0,'d38ac0ad-43c6-4acb-9932-52a99bf78b8a'),(96,1,10,10000,0,'2023-11-09 15:16:52','T54c79532b8b1abbc7bf',0,'7b59d255-5f27-4d50-a672-0a2bd845290d'),(97,1,10,10000,0,'2023-11-09 15:16:52','T54c79532b8b1abbc7c0',0,'44a1149e-bf0d-4c62-baff-26eb4b963a86'),(98,1,10,10000,0,'2023-11-09 15:16:52','T54c795351b66fd4c251',0,'35500698-3b7b-477f-90b3-e1cfc81c7a36'),(99,1,10,10000,0,'2023-11-09 15:16:52','T54c795451b66fd4c252',0,'d13f68f0-4456-4083-948b-eaefed6f0e04'),(100,1,10,10000,0,'2023-11-09 15:16:52','T54c795451b66fd4c253',0,'676af87c-3cbf-484c-b7f2-99abb52d4f50'),(101,1,10,10000,0,'2023-11-09 15:16:53','T54c795451b66fd4c254',0,'854f98d3-93c1-47bf-aa4c-5ea7ae40e71b'),(102,1,10,10000,0,'2023-11-09 15:16:53','T54c795451b66fd4c255',0,'b78d449c-36e6-4e35-ab01-0858290f0877'),(103,1,10,10000,0,'2023-11-09 15:16:53','T54c79542b8b1abbc7c1',0,'9bcb3b90-8355-4182-a5f9-8a9ae8121492'),(104,1,10,10000,0,'2023-11-09 15:16:53','T54c79542b8b1abbc7c2',0,'f37ab4fa-028b-4b09-a5dd-282be2615d3c'),(105,1,10,10000,0,'2023-11-09 15:16:53','T54c79542b8b1abbc7c3',0,'70ab22e9-abe8-45ca-86fe-d2f201f19f32'),(106,1,10,10000,0,'2023-11-09 15:16:53','T54c79552b8b1abbc7c4',0,'a83d9417-6b65-43c3-9b96-6c2b4ace77ad'),(107,1,10,10000,0,'2023-11-09 15:16:53','T54c79552b8b1abbc7c5',0,'f4eb18fd-1f43-496d-8a25-0cc0b7a610ae'),(108,1,10,10000,0,'2023-11-09 15:16:53','T54c795551b66fd4c256',0,'453bc969-9742-408b-bf80-4ce7c53286f6'),(109,1,10,10000,0,'2023-11-09 15:16:53','T54c795551b66fd4c257',0,'d727f98a-26de-491b-9843-bb902c5b98e0'),(110,1,10,10000,0,'2023-11-09 15:16:54','T54c79552b8b1abbc7c6',0,'d829abf7-ff2f-4b03-a801-6d42edcf3cbf'),(111,1,10,10000,0,'2023-11-09 15:16:54','T54c795551b66fd4c258',0,'c87ddfcd-1eff-42c2-a78a-e134d4fffc54'),(112,1,10,10000,0,'2023-11-09 15:20:36','T54c7a3351b66fd4c261',0,'c014c2a1-b307-4188-acdc-122c4e8caf71'),(113,1,10,10000,0,'2023-11-09 15:22:52','T54c7abc51b66fd4c26b',0,'8c012838-c9af-491b-b609-f36a3aae03b8'),(114,1,10,10000,0,'2023-11-09 15:24:17','T54c7b102b8b1abbc7df',0,'72300946-ea14-4899-89e1-e5bbf19ac4e1'),(115,1,10,10000,0,'2023-11-09 15:26:54','T54c7bae2b8b1abbc7ee',0,'e76bf36e-b4bc-4d36-b600-9a1546d45713'),(116,1,10,10000,0,'2023-11-09 15:30:22','T54c7c7e2b8b1abbc802',0,'020373e8-3e0c-40c2-9715-e1c7c0f84640'),(117,2,NULL,10000,1,'2023-11-09 15:32:37','T54c7d0551b66fd4c29e',1,'315ad0e3-7382-4015-8c91-09fa307f36c3'),(118,2,9,10000,0,'2023-11-09 15:35:46','T54c7dc151b66fd4c2ab',0,'ea6b54a5-b2e0-495b-94a4-b80114949ad3'),(119,2,9,10000,0,'2023-11-09 15:37:35','T54c7e2f2b8b1abbc81a',0,'ddcfce20-f883-4d23-ac2a-48f3791602c2'),(120,2,9,10000,0,'2023-11-09 15:37:57','T54c7e4551b66fd4c2b1',0,'40f0f025-d534-4923-a31f-451ac2eee00b'),(121,2,9,10000,0,'2023-11-09 15:42:43','T54c7f632b8b1abbc829',0,'db65856e-166c-4ea8-9ab8-e977b9b161aa'),(122,4,12,10000,0,'2023-11-10 09:01:22','T54d72d151b66fd4ca25',1,'85b0f306-c8fa-4f74-a184-eb8f0ab0b0fd'),(123,12,13,10000,0,'2023-11-10 09:29:07','T54d795351b66fd4ca60',0,'d3447327-ccad-4508-b565-23f15684bc95'),(124,9,14,10000,0,'2023-11-10 09:54:35','T54d7f4a2b8b1abbd07c',1,'f47a85be-cff7-4495-bfe9-b113e137e415'),(129,13,NULL,10000,1,'2023-11-10 10:26:36','T54d86cc51b66fd4cafc',1,'8764c513-cbd0-4f17-98f4-8cf0c7599f1d'),(130,9,15,10000,0,'2023-11-10 10:28:47','T54d874f51b66fd4cb04',0,'9bf831c9-4966-4651-ba80-98da1e6a69d0'),(137,13,16,10000,0,'2023-11-10 14:17:48','T54dbcfc2b8b1abbd425',0,'3e4c6c79-fc24-4fee-b9a0-7bc07aeaaba0'),(138,13,16,10000,0,'2023-11-10 14:17:59','T54dbd072b8b1abbd426',0,'dc4a8c47-c0ad-48e7-a4d2-6800298b8488'),(139,13,17,10000,0,'2023-11-10 14:22:27','T54dbe132b8b1abbd43d',1,'287ba35f-2fbc-4529-855d-0a78377a6162'),(140,1,17,10000,0,'2023-11-10 14:50:26','T54dc4a22b8b1abbd497',0,'fca7b3f8-cfae-4bad-8616-6ad48416c09c'),(141,1,9,10000,0,'2023-11-10 14:52:01','T54dc50051b66fd4cea3',0,'00cef746-463d-4bd1-be78-d6f688da0833'),(142,1,17,10000,0,'2023-11-10 15:08:34','T54dc8e151b66fd4cee7',0,'a0b23e8a-c335-4f37-9712-42afa3f5c274'),(146,9,17,10000,0,'2023-11-10 16:10:14','T54dd7552b8b1abbd5a5',1,'e809967e-b9f4-4388-8061-938948b6b0f8'),(147,12,18,10000,0,'2023-11-10 17:43:40','T54ded3c51b66fd4d0ca',1,'38e28427-4a48-4b1b-9186-e9976dd6f2ba'),(148,11,19,10000,0,'2023-11-11 14:30:31','T54f11762b8b1abbdfa0',1,'b630289e-cfb6-4b11-9e20-37a5da14cd81'),(149,11,20,10000,1,'2023-11-11 14:58:31','T54f18062b8b1abbdfc8',1,'1be77672-3e11-4933-aee1-42e426685619'),(150,11,21,10000,1,'2023-11-11 15:13:09','T54f1b742b8b1abbdfe5',1,'aed6d2e7-13df-4dc7-b42b-868fedb323a3'),(151,11,22,10000,0,'2023-11-11 15:15:30','T54f1c012b8b1abbdfe9',1,'d8e4cc9a-7963-429f-8344-ddf9495da1df'),(152,11,23,10000,0,'2023-11-11 15:56:46','T54f25ad2b8b1abbe020',1,'93d176ef-78ee-459b-88c3-597511565bda'),(153,11,24,10000,0,'2023-11-11 17:36:45','T54f3d1d51b66fd4dacb',1,'50746cbf-3098-47a8-a803-d213dd9a7a46'),(154,10,NULL,10000,1,'2023-11-11 17:48:26','T54f3fda2b8b1abbe0e5',1,'9f0bc428-6b9c-4a9e-bf54-89c2353e2656'),(155,70,24,10000,0,'2023-11-11 21:06:47','T54f6e5751b66fd4dc1c',0,'f6f2c64d-83d4-4a5f-9efa-323c6f6965a7'),(156,70,24,10000,0,'2023-11-11 21:16:59','T54f70ba51b66fd4dc2d',0,'bb27d472-388d-4b15-a770-3ccd051d404a'),(157,70,24,10000,0,'2023-11-11 21:18:08','T54f710051b66fd4dc31',0,'82d17793-fb3d-4769-9576-dd08e087aac4'),(158,1,26,10000,0,'2023-11-11 21:22:01','T54f71e82b8b1abbe24a',1,'8b184236-a0f6-4b83-8321-39144e4574d6'),(159,70,26,10000,0,'2023-11-11 21:27:59','T54f734e51b66fd4dc3d',0,'b3036e23-4b75-48ea-bc78-fad6476a2a1e'),(160,70,26,10000,0,'2023-11-11 21:28:05','T54f735551b66fd4dc3f',0,'a963011a-a456-42a9-9b8d-69000214b749'),(161,70,26,10000,0,'2023-11-11 21:29:33','T54f73ad51b66fd4dc41',0,'d13775f9-5f58-4e2e-9a99-1199e93b6850'),(162,70,26,10000,0,'2023-11-11 21:29:50','T54f73bd51b66fd4dc42',0,'24be64d3-adab-417e-a0e9-1df5dc8c3640'),(163,70,26,10000,0,'2023-11-11 21:31:29','T54f74202b8b1abbe257',0,'bc556c43-d58a-47b2-94aa-e87c8d8e35ac'),(164,11,26,10000,0,'2023-11-11 21:42:55','T54f76cf2b8b1abbe266',0,'eec805ac-9675-489b-93cb-bbf51579a522'),(165,11,26,10000,0,'2023-11-11 21:58:50','T54f7a8951b66fd4dc6b',0,'4d7bdce1-d3f6-4948-8326-22391d2b5859'),(166,11,26,10000,0,'2023-11-11 22:00:15','T54f7adf2b8b1abbe281',0,'622e1c7a-cd30-4034-bf97-1a3aa27f3e28'),(167,11,26,10000,0,'2023-11-11 22:00:37','T54f7af451b66fd4dc6f',0,'3752e6a2-6c01-4aff-a848-245b906eef6f'),(170,1,NULL,10000,1,'2023-11-13 16:37:49','T551d24d130e5349c287',1,'9760b55e-a77f-4b23-bec2-d21cea359618'),(171,12,NULL,10000,1,'2023-11-13 16:40:36','T551d2f4130e5349c294',1,'4941e0be-7bcb-4a69-9be6-3367fc3646b4'),(172,75,29,10000,0,'2023-11-13 22:30:42','T5522501130e5349c5a3',1,'f6e643c6-4346-41ea-b597-c8621b1a9df4'),(173,10,29,10000,0,'2023-11-13 22:32:06','T552255571bf11589c16',1,'79a91e9c-65d0-4e1d-9c41-c487bd7da880'),(174,71,30,10000,0,'2023-11-14 15:25:59','T55312f6130e5349cf3f',1,'5bd604c8-df18-4a34-975f-0644429e3c1c'),(175,73,30,10000,0,'2023-11-14 15:56:18','T5531a1171bf1158a663',1,'6575220e-779f-4ba2-90b9-e07f0e3e7b16'),(176,12,31,10000,0,'2023-11-15 13:47:14','T5544d51130e5349dbec',1,'5da28cab-7b55-44f6-acb4-98f5c4dba19a'),(177,12,32,10000,0,'2023-11-15 13:57:25','T5544fb4130e5349dc10',0,'8c97e3fe-daa4-4188-aa4f-5bcb0ac12576'),(178,9,33,1000000,0,'2023-11-15 16:01:19','T5546cbe71bf1158b48b',1,'cfbd07ed-6aaa-4dc4-8e75-54d9a94f4d4b'),(179,9,34,1000000,0,'2023-11-15 16:14:13','T5546fc571bf1158b4b7',1,'5b5d1848-4094-488d-ac30-50313b9e026e'),(180,9,35,1000000,0,'2023-11-15 16:24:27','T554722b130e5349ddeb',1,'86a5b6d1-baae-4677-b283-785f11bf58b4'),(181,75,35,1000000,0,'2023-11-15 16:25:11','T554725771bf1158b4cd',0,'14866aa2-356b-4c6e-9076-107628219bee'),(182,75,35,1000000,0,'2023-11-15 16:25:18','T554725e130e5349ddf1',0,'49b8677c-91cd-4590-856a-e5863be333ea'),(183,9,36,10000,0,'2023-11-15 16:44:54','T55476f671bf1158b517',1,'9afc3b6e-e5c7-4ad7-8b84-6c306bcdfedd'),(184,75,36,10000,0,'2023-11-15 16:45:24','T554771371bf1158b518',1,'654b8629-d52d-4ab2-99fe-e60cf1f132bd'),(185,11,37,30000,0,'2023-11-15 17:39:14','T55483b2130e5349deee',1,'31bed3d6-62e8-44cb-8a74-8830b9e705cd'),(186,12,37,30000,0,'2023-11-15 17:40:44','T554840c130e5349def4',1,'a728d00b-46de-4f5f-8b59-407af2ffee24'),(187,2,38,10000,0,'2023-11-15 17:48:09','T55485c9130e5349df09',0,'c1c6dfb0-6562-4159-8e0a-e7e10b13dcfe'),(188,2,39,10000,0,'2023-11-15 17:50:50','T554866a130e5349df0d',0,'a49fbdee-3e29-41ed-b3d0-cce63e476856'),(189,11,40,10000,0,'2023-11-16 10:45:00','T555741c130e5349e596',1,'7cdaeb2e-6a40-423f-ab5b-1dd029ecd27e'),(190,8,40,10000,0,'2023-11-16 10:47:17','T55574a5130e5349e59f',1,'1ac189d2-512f-45d9-abab-15115561e651'),(191,1,41,5000,0,'2023-11-16 11:01:20','T55577ef71bf1158bc7a',1,'6b8818aa-c1e6-4e6b-a836-c2ec9111700b'),(192,88,41,5000,0,'2023-11-16 11:02:07','T555781f71bf1158bc7f',1,'234d6744-b587-4951-8cec-384c6d15f2ae'),(193,12,42,30000,0,'2023-11-16 12:51:56','T55591db130e5349e6f1',1,'1ff510b9-55cb-4eac-b938-4a05910350bb'),(194,8,42,30000,0,'2023-11-16 13:08:12','T55595ac130e5349e70e',1,'5ae03066-5bec-4401-b531-5150ce7bb464'),(195,2,43,10000,0,'2023-11-16 20:39:42','T555ff7e130e5349ee64',1,'6071b6cf-9d04-4524-a38b-99e91fecfab0'),(196,7,43,10000,0,'2023-11-16 20:41:00','T555ffcb71bf1158c4e2',1,'216ac1a7-084e-42f3-82be-1f7e5e0d88d4');
/*!40000 ALTER TABLE `payment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_history`
--

DROP TABLE IF EXISTS `point_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `value` int NOT NULL DEFAULT '0',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `detail` varchar(100) NOT NULL DEFAULT '활동',
  PRIMARY KEY (`id`),
  KEY `FK_user_TO_point_history_1` (`user_id`),
  CONSTRAINT `FK_user_TO_point_history_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_history`
--

LOCK TABLES `point_history` WRITE;
/*!40000 ALTER TABLE `point_history` DISABLE KEYS */;
INSERT INTO `point_history` VALUES (1,2,10,'2023-11-13 13:52:45','투표 참여'),(2,75,10,'2023-11-13 15:22:05','투표 참여'),(3,1,10,'2023-11-13 15:59:46','투표 참여'),(4,75,10,'2023-11-13 16:33:16','투표 참여'),(5,1,100,'2023-11-13 16:37:48','게시글 작성'),(6,1,100,'2023-11-13 16:38:08','게시글 작성'),(7,2,10,'2023-11-13 16:44:13','투표 참여'),(8,11,10,'2023-11-13 16:44:32','투표 참여'),(9,75,10,'2023-11-13 16:55:59','투표 참여'),(10,5,10,'2023-11-13 17:01:42','투표 참여'),(11,5,10,'2023-11-13 17:08:39','투표 참여'),(12,78,10,'2023-11-13 17:40:19','투표 참여'),(13,78,10,'2023-11-13 17:40:27','투표 참여'),(14,78,10,'2023-11-13 17:41:09','투표 참여'),(15,78,10,'2023-11-13 17:41:31','투표 참여'),(16,75,-1500,'2023-11-13 21:36:59','상품 응모'),(17,75,-100,'2023-11-13 21:37:02','상품 응모'),(18,75,-100,'2023-11-13 21:37:03','상품 응모'),(19,75,-100,'2023-11-13 21:37:04','상품 응모'),(20,75,-100,'2023-11-13 21:37:05','상품 응모'),(21,75,-100,'2023-11-13 21:37:06','상품 응모'),(22,75,-100,'2023-11-13 21:37:07','상품 응모'),(23,75,-100,'2023-11-13 21:37:08','상품 응모'),(24,75,-100,'2023-11-13 21:37:08','상품 응모'),(25,75,-100,'2023-11-13 21:37:10','상품 응모'),(26,75,-100,'2023-11-13 21:37:12','상품 응모'),(27,75,-100,'2023-11-13 21:37:13','상품 응모'),(28,75,-100,'2023-11-13 21:37:16','상품 응모'),(29,75,-100,'2023-11-13 21:37:18','상품 응모'),(30,75,-100,'2023-11-13 21:37:19','상품 응모'),(31,75,100,'2023-11-13 22:30:41','게시글 작성'),(32,10,10,'2023-11-14 10:15:04','투표 참여'),(33,71,10,'2023-11-14 11:58:22','투표 참여'),(34,83,10,'2023-11-14 13:02:29','투표 참여'),(35,10,10,'2023-11-14 15:18:42','투표 참여'),(36,9,-1500,'2023-11-14 15:25:29','상품 응모'),(37,9,-1500,'2023-11-14 15:25:31','상품 응모'),(38,9,-1500,'2023-11-14 15:25:32','상품 응모'),(39,9,-1500,'2023-11-14 15:25:34','상품 응모'),(40,9,-1500,'2023-11-14 15:25:35','상품 응모'),(41,9,-1500,'2023-11-14 15:25:36','상품 응모'),(42,71,100,'2023-11-14 15:25:58','게시글 작성'),(43,2,10,'2023-11-14 15:57:13','투표 참여'),(44,73,10,'2023-11-14 15:57:44','투표 참여'),(45,76,10,'2023-11-14 16:01:18','투표 참여'),(46,10,10,'2023-11-14 16:20:33','투표 참여'),(47,75,-300,'2023-11-14 16:23:21','상품 응모'),(48,75,-300,'2023-11-14 16:23:25','상품 응모'),(49,75,-300,'2023-11-14 16:23:28','상품 응모'),(50,75,-300,'2023-11-14 16:23:30','상품 응모'),(51,75,-300,'2023-11-14 16:23:32','상품 응모'),(52,10,10,'2023-11-14 16:23:38','투표 참여'),(53,10,10,'2023-11-14 16:24:37','투표 참여'),(54,10,10,'2023-11-14 16:24:45','투표 참여'),(55,10,10,'2023-11-14 16:31:52','투표 참여'),(56,75,10,'2023-11-14 17:01:57','투표 참여'),(57,75,10,'2023-11-14 17:11:15','투표 참여'),(58,2,-1000,'2023-11-14 17:19:21','상품 응모'),(59,84,10,'2023-11-14 17:31:32','투표 참여'),(60,84,10,'2023-11-14 17:32:32','투표 참여'),(61,84,10,'2023-11-14 17:32:46','투표 참여'),(62,84,10,'2023-11-14 17:42:21','투표 참여'),(63,7,10,'2023-11-14 17:47:53','투표 참여'),(64,13,10,'2023-11-14 17:48:44','투표 참여'),(65,13,10,'2023-11-14 17:48:56','투표 참여'),(66,13,10,'2023-11-14 17:49:27','투표 참여'),(67,85,10,'2023-11-15 08:59:32','투표 참여'),(68,85,10,'2023-11-15 09:01:57','투표 참여'),(69,12,100,'2023-11-15 13:47:11','게시글 작성'),(70,12,100,'2023-11-15 13:57:23','게시글 작성'),(71,2,10,'2023-11-15 14:27:25','투표 참여'),(72,86,10,'2023-11-15 15:11:36','투표 참여'),(73,86,10,'2023-11-15 15:13:58','투표 참여'),(74,86,-1500,'2023-11-15 15:14:36','상품 응모'),(75,86,-1000,'2023-11-15 15:14:38','상품 응모'),(76,86,-150,'2023-11-15 15:14:41','상품 응모'),(77,86,-150,'2023-11-15 15:14:42','상품 응모'),(78,86,-150,'2023-11-15 15:14:48','상품 응모'),(79,86,-150,'2023-11-15 15:14:50','상품 응모'),(80,86,-150,'2023-11-15 15:14:51','상품 응모'),(81,86,-150,'2023-11-15 15:14:52','상품 응모'),(82,86,-1500,'2023-11-15 15:15:50','상품 응모'),(83,86,10,'2023-11-15 15:16:06','투표 참여'),(84,9,100,'2023-11-15 15:59:06','게시글 작성'),(85,9,100,'2023-11-15 16:01:17','게시글 작성'),(86,9,100,'2023-11-15 16:14:12','게시글 작성'),(87,9,100,'2023-11-15 16:24:26','게시글 작성'),(88,9,100,'2023-11-15 16:36:29','게시글 작성'),(89,9,100,'2023-11-15 16:44:53','게시글 작성'),(90,11,100,'2023-11-15 17:39:13','게시글 작성'),(91,2,100,'2023-11-15 17:48:09','게시글 작성'),(92,2,100,'2023-11-15 17:50:50','게시글 작성'),(93,1,10,'2023-11-16 09:23:21','투표 참여'),(94,75,10,'2023-11-16 09:49:17','투표 참여'),(95,11,100,'2023-11-16 10:45:00','게시글 작성'),(96,1,10,'2023-11-16 10:48:31','투표 참여'),(97,8,10,'2023-11-16 10:50:07','투표 참여'),(98,1,100,'2023-11-16 11:01:19','게시글 작성'),(99,1,10,'2023-11-16 11:02:37','투표 참여'),(100,12,100,'2023-11-16 12:51:54','게시글 작성'),(101,80,10,'2023-11-16 13:07:10','투표 참여'),(102,80,10,'2023-11-16 13:08:59','투표 참여'),(103,8,10,'2023-11-16 13:09:26','투표 참여'),(104,9,10,'2023-11-16 13:29:40','투표 참여'),(105,2,10,'2023-11-16 13:35:09','투표 참여'),(106,5,10,'2023-11-16 16:07:06','투표 참여'),(107,5,10,'2023-11-16 16:17:12','투표 참여'),(108,5,10,'2023-11-16 16:50:44','투표 참여'),(109,2,-1000,'2023-11-16 20:16:55','상품 응모'),(110,2,-1000,'2023-11-16 20:16:57','상품 응모'),(111,2,-1000,'2023-11-16 20:16:59','상품 응모'),(112,2,-1000,'2023-11-16 20:17:01','상품 응모'),(113,2,-1000,'2023-11-16 20:17:03','상품 응모'),(114,2,-1000,'2023-11-16 20:17:05','상품 응모'),(115,2,-1000,'2023-11-16 20:17:07','상품 응모'),(116,2,-1000,'2023-11-16 20:17:08','상품 응모'),(117,2,-1000,'2023-11-16 20:17:10','상품 응모'),(118,2,100,'2023-11-16 20:39:42','게시글 작성'),(119,7,10,'2023-11-16 20:41:44','투표 참여'),(120,2,10,'2023-11-16 20:41:54','투표 참여');
/*!40000 ALTER TABLE `point_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `FK_user_TO_post_1` (`user_id`),
  CONSTRAINT `FK_user_TO_post_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,2,'이거 한번 봐주세요','궁금하네요','2023-11-07 14:12:05',1),(2,4,'test2','test2','2023-11-07 14:14:29',1),(3,4,'test3','test2=3','2023-11-07 14:15:16',1),(4,4,'test4','test4','2023-11-07 14:18:15',1),(5,2,'테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트','테스트','2023-11-07 15:26:15',1),(6,4,'이거 내가 맞나??','이거 내가 맞나??','2023-11-08 10:45:38',1),(7,4,'test 333','test 333','2023-11-08 14:26:11',1),(8,4,'test 333','test 333','2023-11-08 14:26:12',1),(9,4,'test@@','test@@','2023-11-08 14:28:34',1),(10,1,'윤우혁테스트','내용 테스트\n\n엔터했음\nㅎㅎㅎ\nㅋㅋㅋㅋㅋㅋㅋㅋ\n\n\nㅋ\nㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2023-11-09 09:30:38',1),(11,5,'판단해주세요','판단해달라고','2023-11-09 10:17:34',1),(12,5,'판단해주세요','판단해달라고','2023-11-09 10:17:40',1),(13,5,'판단해주세요','판단해달라고','2023-11-09 10:17:56',1),(14,4,'베인 블리츠크랭크 누가 맞는말인가요....','저는 베인을 플레이한 alal라는 닉네임의 유저입니다.\n둘다 초반에 잘못을 하였고 저는 피가 많이 없는상황이었습니다. \n블리츠크랭크가 제가 피가 없는상황이었는데 상대 원딜을 끌었고 이것이 제가 이기는상황이 아니라고 판단되어서 저는 도망을 갔습니다.\n이것이 발단이 되어서 글을 쓰게 됩니다.\n제가 맞는거라면 저에게 투표를 해주시고 블리츠크랭크가 옳은판단을 한것이라면 블리츠크랭크에게 투표를 해주세요','2023-11-10 09:01:20',1),(15,2,'test','test','2023-11-10 09:15:44',1),(16,2,'test','test','2023-11-10 09:16:07',1),(17,12,'동영상 테스트1','동영상 테스트1','2023-11-10 09:29:05',1),(18,9,'저는 잘못이 없습니다.','내용\n내용내용내용\n내용내용내용내용내용\n내용내용내용내용내용내용내용','2023-11-10 09:53:38',1),(19,9,'저는 잘못이 없습니다.','내용\n내용내용내용\n내용내용내용내용내용\n내용내용내용내용내용내용내용','2023-11-10 09:54:33',1),(20,9,'승인 테스트','승인 테스트','2023-11-10 10:28:46',1),(21,9,'승인 테스트','승인 테스트','2023-11-10 10:29:25',1),(22,13,'1110 1420 테스트','1110 1420 테스트','2023-11-10 14:22:26',1),(23,13,'1110 1737 test','1110 1737 test','2023-11-10 17:38:08',1),(24,13,'1110 1737 test','1110 1737 test','2023-11-10 17:38:33',1),(25,13,'1110 1737 test','1110 1737 test','2023-11-10 17:38:59',1),(26,9,'server테스트','server테스트','2023-11-10 17:43:38',1),(27,11,'1111 test1','1111 test1','2023-11-11 14:30:29',1),(28,11,'1111 test2','1111 test2','2023-11-11 14:58:30',1),(29,11,'1111test3','1111test3','2023-11-11 15:13:08',1),(30,11,'1111 test4','1111 test4','2023-11-11 15:15:29',1),(31,11,'1111 test5','1111 test5','2023-11-11 15:56:45',1),(32,11,'1111test6','1111test6','2023-11-11 17:36:45',1),(34,1,'1111모달테스트','1111모달테스트','2023-11-11 21:22:00',1),(36,1,'바텀 라인전 2대2 상황 봐주세요.','바텀 라인전 2대2 싸움을 걸어도 되냐 안되냐로 싸우고 있습니다.\n\n- 저희 팀 정글은 위쪽에 있는 상황이고, 상대 팀 정글의 위치를 모르는 상황에서 저는 싸움을 하기 싫었습니다.\n- 그런데 파이크가 갑자기 Q로 라칸을 끓었고 결국 손해를 본 것 같습니다.\n\n[사미라 vs 파이크]\n싸움을 했으면 안됐다고 생각하면 왼쪽, 했어도 됐다고 생각하면 오른쪽에 투표해주세요.\n\n다이아 이상만 투표해주세요 ㅋㅋ.','2023-11-13 16:37:48',1),(37,1,'바텀 라인전 2대2 상황 봐주세요.','바텀 라인전 2대2 싸움을 걸어도 되냐 안되냐로 싸우고 있습니다.\n\n- 저희 팀 정글은 위쪽에 있는 상황이고, 상대 팀 정글의 위치를 모르는 상황에서 저는 싸움을 하기 싫었습니다.\n- 그런데 파이크가 갑자기 Q로 라칸을 끓었고 결국 손해를 본 것 같습니다.\n\n[사미라 vs 파이크]\n싸움을 했으면 안됐다고 생각하면 왼쪽, 했어도 됐다고 생각하면 오른쪽에 투표해주세요.\n\n다이아 이상만 투표해주세요 ㅋㅋ.','2023-11-13 16:38:08',1),(38,75,'ㅋ별거없노','ㅋ별거없노','2023-11-13 22:30:41',1),(39,71,'임포스터','임','2023-11-14 15:25:58',1),(40,12,'글작성 테스트','글작성 테스트','2023-11-15 13:47:11',1),(41,12,'http://localhost:3000/lolvote/36','http://localhost:3000/lolvote/36','2023-11-15 13:57:23',1),(42,9,'1115test','1115test','2023-11-15 15:59:06',1),(43,9,'1115test','1115test','2023-11-15 16:01:17',1),(44,9,'1115test2','1115test2','2023-11-15 16:14:12',1),(45,9,'test1414','test1414','2023-11-15 16:24:26',1),(46,9,'1115test','1115test','2023-11-15 16:36:29',1),(47,9,'1115test3','1115test3','2023-11-15 16:44:53',1),(48,11,'억울해서 잠이안와요','초반 2분30초경 첫 데스는 갱을 당한거라 어쩔 수 없었고 두번 째 데스는 라인이 박히는 상황에서 집가는 제이스를 잘 캐치해서 잡은 후 집을 가다가 또 갱을 온 오공한테 죽었습니다.\n이렐: 오공이 갱을 온 것이 어떻게 내 잘못이냐\n바이: 충분히 예상이 가능한 상황이였고, 예상을 못했더라도 두번 째 데스는 안전한 곳 에서 집에 갔다면 사는 것 상황이였다.\n\n정확한 판단 부탁드립니다!!!','2023-11-15 17:39:13',1),(49,2,'캐싱 업로드 제외 테스트','캐싱 업로드 제외 테스트','2023-11-15 17:48:09',1),(50,2,'캐싱 업로드 제외 테스트','캐싱 업로드 제외 테스트','2023-11-15 17:50:50',1),(51,11,'다이브 상황 봐주세요','다이브 상황입니다.\n\n저는 A(벨베스)이고, 상대방은 B(레넥톤)입니다.\n\n저는 레넥톤이 스턴만 걸고 나가면 잡으려고 했는데 서로 생각이 안맞아서 둘 다 죽었습니다.\n\n레넥톤이 스킬을 먼저 썼어야 했다고 생각하면 [왼쪽]에 투표해주세요.\n\n제가 먼저 썼어야 했다고 생각하면 [오른쪽]에 투표해주세요.','2023-11-16 10:45:00',1),(52,1,'1렙 라인 밀어야 했는가?','적 정글이 우리 블루 스타트를 했는 것을 확인했는 상황이였습니다.\n\n천천히 밀면서 라인 쌓거나 태웠어야했다고 생각했는데 자야가 갑자기 라인을 밀어버렸습니다.\n\n누구의 잘못이 클까요 ?\n\n저는 쓰레쉬이고 상대방은 자야입니다.\n\n투표 부탁드립니다.','2023-11-16 11:01:19',1),(53,12,'조금 애매한 상황','안녕하세요. 저는 니코입니다.\n\n해당 상황에서 어떻게 생각하시는지 투표바랍니다.','2023-11-16 12:51:54',1),(54,2,'레넥톤은 살 수 있었나','레넥톤이 적을 잡은 후 살아서 나올 수 있었는지 판별해주세요','2023-11-16 20:39:42',1);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_img`
--

DROP TABLE IF EXISTS `profile_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_img` (
  `profile_img_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(1000) NOT NULL,
  `org_name` varchar(2000) NOT NULL,
  `extension` varchar(3) NOT NULL,
  PRIMARY KEY (`profile_img_id`),
  KEY `FK_user_TO_profile_img_1` (`user_id`),
  CONSTRAINT `FK_user_TO_profile_img_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_img`
--

LOCK TABLES `profile_img` WRITE;
/*!40000 ALTER TABLE `profile_img` DISABLE KEYS */;
INSERT INTO `profile_img` VALUES (1,5,'676334eb-524d-4729-8e1e-62ed43077945','41BroI-mHhL.jpg','JPG'),(2,2,'2cb10e03-c328-4718-9d7e-89884d8bbc7a','제목 없음.png','png'),(3,1,'2f756ee6-a5ae-41e9-9da0-6807f5e48e0b','네모 프로필 (5).jpg','jpg'),(4,69,'d1d346b6-052d-4efe-a068-e8b9af7644f0','cap','PNG'),(5,71,'f640dca6-52ea-458a-9e21-f3e58c67bf3a','i3872756907','jpg'),(6,70,'1d793d86-a03a-4a0b-9ea4-ebd0ea38176f','꼰대.PNG','PNG'),(7,4,'e78b8077-5d91-49ea-a815-272285d60247','다운로드.jpg','png'),(8,12,'9d2279fa-124b-4ed9-91dd-62bef701ebd4','다운로드','jpg'),(9,9,'29f44cc3-e448-4e14-818b-b70fcc69d440','네모 프로필 (5)','jpg'),(10,75,'ecdf015e-1632-4ca0-8c30-03fa75f7b346','꼰대','PNG'),(11,83,'62e5a511-016e-4c1c-a42c-a9d3eafdf433','진창호 취업사진','jpg'),(12,73,'4084ff0d-5585-40bd-b89f-d4abadbe3e1c','프사','jpg'),(13,8,'bd2c08d1-21df-4b86-ba28-68683bf37dcc','cap.PNG','JPG');
/*!40000 ALTER TABLE `profile_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `re_comment`
--

DROP TABLE IF EXISTS `re_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `re_comment` (
  `re_comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `comment_id` int NOT NULL,
  `content` varchar(1000) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`re_comment_id`),
  KEY `FK_user_TO_re_comment_1` (`user_id`),
  KEY `FK_comment_TO_re_comment_1` (`comment_id`),
  CONSTRAINT `FK_comment_TO_re_comment_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_TO_re_comment_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `re_comment`
--

LOCK TABLES `re_comment` WRITE;
/*!40000 ALTER TABLE `re_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `re_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  `re_comment_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FK_post_TO_report_1` (`post_id`),
  KEY `FK_comment_TO_report_1` (`comment_id`),
  KEY `FK_re_comment_TO_report_1` (`re_comment_id`),
  KEY `FK_user_TO_report_1` (`user_id`),
  CONSTRAINT `FK_comment_TO_report_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FK_post_TO_report_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_re_comment_TO_report_1` FOREIGN KEY (`re_comment_id`) REFERENCES `re_comment` (`re_comment_id`),
  CONSTRAINT `FK_user_TO_report_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `birthyear` date DEFAULT NULL,
  `riot_token` varchar(1000) DEFAULT NULL,
  `blizzard_token` varchar(1000) DEFAULT NULL,
  `point` int NOT NULL DEFAULT '0',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lol_tier` varchar(50) DEFAULT NULL,
  `val_tier` varchar(50) DEFAULT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'GUEST',
  `state` tinyint NOT NULL DEFAULT '1',
  `main_position` tinyint DEFAULT NULL,
  `sub_position` tinyint DEFAULT NULL,
  `vote_count` int NOT NULL DEFAULT '0',
  `vote_victory` int NOT NULL DEFAULT '0',
  `social` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'윤우혁','윤우혁','squareyun@kakao.com','6f5a512e-2b46-4f31-8a6c-d9c19d4de89e',NULL,NULL,NULL,NULL,340,'2023-11-07 14:08:04','DIAMOND',NULL,'GUEST',1,NULL,NULL,1185,1185,'Kakao'),(2,'최영창','최영창','casden@naver.com','b1faec46-f21e-4c2c-bcfa-6eb43d3ef74a',NULL,NULL,NULL,NULL,360,'2023-11-07 14:08:43','CHALLENGER',NULL,'GUEST',1,NULL,NULL,2534,1650,'Kakao'),(3,'whd','qkr','ssafy0@naver.com','{bcrypt}$2a$10$EcddkrCRYpWO94ZuKgY32uWCXGHpPU4EQUi2obugtucefy30Uw98i',1,NULL,NULL,NULL,10000,'2023-11-07 14:11:52','GOLD',NULL,'GUEST',1,NULL,NULL,694,694,NULL),(4,'alal','dmd','ss0@naver.com','{bcrypt}$2a$10$AIk0W5uaV5CTefyeQP9hp.7QeV39Ae.7CnEjd9tNKrcpd33H3s71C',0,NULL,NULL,NULL,10000,'2023-11-07 14:12:55','EMERALD',NULL,'GUEST',1,NULL,NULL,6585,6160,NULL),(5,'최대로긴닉네임길이입니다','신경희','tabbycat98@naver.com','{bcrypt}$2a$10$x.9AVpt3R9EnMYJQsg8US.NeeaRvvdGA471E4BSopT/DNKhsS2Vt.',NULL,NULL,NULL,NULL,50,'2023-11-07 14:32:59','EMERALD',NULL,'GUEST',1,NULL,NULL,1227,1035,'Kakao'),(6,'lorneblow0','Lauralee','lwheelhouse0@technorati.com','{bcrypt}$2a$10$R7YdZLb.LMa.VCJcbd4RFO0BmV8LdJkyxzVhrtsYtdk.Cy5K7uQk6',0,'1996-06-05',NULL,NULL,72761,'2015-10-20 05:51:05','EMERALD',NULL,'GUEST',1,0,NULL,0,0,NULL),(7,'더머','덤','ccbb@ccbb.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,NULL,NULL,NULL,10020,'2023-11-07 14:36:09','GOLD',NULL,'GUEST',1,NULL,NULL,299,299,NULL),(8,'으 쑤','천병찬','cbchan644@naver.com','f9e547db-5176-401c-943b-0831bc9eb946',NULL,NULL,NULL,NULL,20,'2023-11-07 15:14:20','DIAMOND',NULL,'GUEST',1,NULL,NULL,0,0,'Kakao'),(9,'d59','윤우혁','squareyun@gmail.com','{bcrypt}$2a$10$aO3fDT3D3X7K9sHpMPBhhu7xn0FIFgIz5ELUfklEqSsl7TMQ1C1ta',1,NULL,NULL,NULL,1610,'2023-11-07 13:42:57','DIAMOND',NULL,'GUEST',1,NULL,NULL,299,0,NULL),(10,'prettySin','신경희','skh1615@gmail.com','{bcrypt}$2a$10$53NjKjQFXt/nYCHRslnGcOn8zGPf36FVy9.erWzd3fNPbSYxh6ht2',0,NULL,NULL,NULL,10070,'2023-11-07 13:43:23','IRON',NULL,'GUEST',1,NULL,NULL,1338,1284,NULL),(11,'최자','채경호','corudgh123@gmail.com','{bcrypt}$2a$10$aO3fDT3D3X7K9sHpMPBhhu7xn0FIFgIz5ELUfklEqSsl7TMQ1C1ta',NULL,NULL,NULL,NULL,410,'2023-11-07 13:43:51','MASTER',NULL,'GUEST',1,NULL,NULL,1035,341,NULL),(12,'애매한넘','박종욱','pju7429@naver.com','{bcrypt}$2a$10$R7YdZLb.LMa.VCJcbd4RFO0BmV8LdJkyxzVhrtsYtdk.Cy5K7uQk6',1,NULL,NULL,NULL,10300,'2023-11-07 13:44:09','DIAMOND',NULL,'GUEST',1,NULL,NULL,4924,4248,NULL),(13,'귀여운넘','최영창','choiyc1446@gmail.com','{bcrypt}$2a$10$JE.O.aRRwLM2lnyGKcmHVOLA56E786ck66r/5cTlnNBapqEkDTCIG',1,NULL,NULL,NULL,10030,'2023-11-07 13:44:25','GOLD',NULL,'GUEST',1,NULL,NULL,618,564,NULL),(26,'jdobney0','Jedediah','jbirkbeck0@java.com','{bcrypt}$2a$10$aO3fDT3D3X7K9sHpMPBhhu7xn0FIFgIz5ELUfklEqSsl7TMQ1C1ta',1,'1997-03-06',NULL,NULL,28977,'2021-02-09 22:09:28','EMERALD',NULL,'GUEST',1,2,NULL,774,774,NULL),(27,'amurrell1','Anett','araspin1@storify.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1981-12-23',NULL,NULL,74309,'2017-09-16 21:47:08','GOLD',NULL,'GUEST',1,4,NULL,150,150,NULL),(28,'mlamdin2','Morton','mbrinsden2@google.com.br','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1994-12-10',NULL,NULL,31186,'2015-02-03 13:16:07','MASTER',NULL,'GUEST',1,1,NULL,200,220,NULL),(29,'tdelazenne3','Tadio','tlemin3@newyorker.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1989-04-29',NULL,NULL,17819,'2022-12-12 13:08:57','DIAMOND',NULL,'GUEST',1,1,NULL,340,340,NULL),(30,'ucammacke4','Urbano','uokane4@com.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2001-06-16',NULL,NULL,40739,'2017-07-02 13:13:05','DIAMOND',NULL,'GUEST',1,2,NULL,0,0,NULL),(31,'krzehor5','Kriste','krichardot5@bing.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2006-04-05',NULL,NULL,73127,'2014-03-21 18:51:57','MASTER',NULL,'GUEST',1,2,NULL,0,0,NULL),(32,'lmartelet6','Lucas','lbissiker6@bravesites.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2006-10-13',NULL,NULL,46473,'2020-03-28 03:19:10','DIAMOND',NULL,'GUEST',1,1,NULL,0,0,NULL),(33,'lelstob7','Lyman','lallsep7@amazon.co.uk','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1982-01-30',NULL,NULL,62545,'2015-01-07 01:24:51','SILVER',NULL,'GUEST',1,1,NULL,0,0,NULL),(34,'rbrayne8','Ransell','rgawke8@epa.gov','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2004-10-09',NULL,NULL,52696,'2021-05-06 23:47:33','CHALLENGER',NULL,'GUEST',1,1,NULL,0,0,NULL),(35,'jlaxon9','Jerrie','jalbury9@cafepress.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1983-12-07',NULL,NULL,30452,'2022-08-28 17:59:52','SILVER',NULL,'GUEST',1,0,NULL,0,0,NULL),(36,'mdirkina','Margret','mdominettia@whitehouse.gov','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1991-02-23',NULL,NULL,40605,'2017-06-30 05:23:31','CHALLENGER',NULL,'GUEST',1,1,NULL,0,0,NULL),(37,'hlabeuilb','Hedwig','hknellb@sun.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2005-10-28',NULL,NULL,82688,'2019-06-04 20:22:00','CHALLENGER',NULL,'GUEST',1,0,NULL,0,0,NULL),(38,'hfyshc','Hymie','hklynerc@macromedia.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1984-06-06',NULL,NULL,71165,'2014-08-19 07:48:57','GOLD',NULL,'GUEST',1,1,NULL,0,0,NULL),(39,'screavend','Sheela','scescod@e-recht24.de','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1998-08-02',NULL,NULL,43037,'2014-02-15 20:05:31','GOLD',NULL,'GUEST',1,1,NULL,0,0,NULL),(40,'cbaggotte','Cortie','ccockline@seesaa.net','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2010-01-17',NULL,NULL,80868,'2018-04-23 04:13:59','MASTER',NULL,'GUEST',1,2,NULL,0,0,NULL),(41,'aforrestillf','Ardelle','acrosselandf@cbslocal.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2013-07-26',NULL,NULL,33031,'2016-03-27 17:02:05','SILVER',NULL,'GUEST',1,3,NULL,270,270,NULL),(42,'aarthursg','Andreana','amouldeng@intel.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2003-01-24',NULL,NULL,21843,'2016-05-05 07:42:29','GOLD',NULL,'GUEST',1,4,NULL,0,0,NULL),(43,'acholoninh','Anastassia','acubinh@ed.gov','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1986-10-20',NULL,NULL,54786,'2015-09-27 03:10:28','PLATINUM',NULL,'GUEST',1,1,NULL,0,0,NULL),(44,'sgronoui','Stormie','smozzettii@jimdo.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1992-03-01',NULL,NULL,62024,'2019-09-29 05:35:08','PLATINUM',NULL,'GUEST',1,2,NULL,0,0,NULL),(45,'ericketj','Emelen','efinniganj@youtube.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1994-03-28',NULL,NULL,52062,'2020-09-24 18:02:14','GOLD',NULL,'GUEST',1,1,NULL,0,0,NULL),(46,'lwallagek','Lotte','llicquorishk@europa.eu','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2007-04-26',NULL,NULL,89421,'2019-11-10 07:08:00','PLATINUM',NULL,'GUEST',1,0,NULL,0,0,NULL),(47,'jdobney0','Jedediah','jbirkbeck0@java.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1997-03-06',NULL,NULL,28977,'2021-02-09 22:09:28','GOLD',NULL,'GUEST',1,2,NULL,0,0,NULL),(48,'amurrell1','Anett','araspin1@storify.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1981-12-23',NULL,NULL,74309,'2017-09-16 21:47:08','SILVER',NULL,'GUEST',1,4,NULL,0,0,NULL),(49,'mlamdin2','Morton','mbrinsden2@google.com.br','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1994-12-10',NULL,NULL,31186,'2015-02-03 13:16:07','GOLD',NULL,'GUEST',1,1,NULL,0,0,NULL),(50,'tdelazenne3','Tadio','tlemin3@newyorker.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1989-04-29',NULL,NULL,17819,'2022-12-12 13:08:57','BRONZE',NULL,'GUEST',1,1,NULL,0,0,NULL),(51,'ucammacke4','Urbano','uokane4@com.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2001-06-16',NULL,NULL,40739,'2017-07-02 13:13:05','GOLD',NULL,'GUEST',1,2,NULL,0,0,NULL),(52,'krzehor5','Kriste','krichardot5@bing.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2006-04-05',NULL,NULL,73127,'2014-03-21 18:51:57','BRONZE',NULL,'GUEST',1,2,NULL,0,0,NULL),(53,'lmartelet6','Lucas','lbissiker6@bravesites.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2006-10-13',NULL,NULL,46473,'2020-03-28 03:19:10','GOLD',NULL,'GUEST',1,1,NULL,0,0,NULL),(54,'lelstob7','Lyman','lallsep7@amazon.co.uk','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1982-01-30',NULL,NULL,62545,'2015-01-07 01:24:51','GOLD',NULL,'GUEST',1,1,NULL,0,0,NULL),(55,'rbrayne8','Ransell','rgawke8@epa.gov','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2004-10-09',NULL,NULL,52696,'2021-05-06 23:47:33','MASTER',NULL,'GUEST',1,1,NULL,0,0,NULL),(56,'jlaxon9','Jerrie','jalbury9@cafepress.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1983-12-07',NULL,NULL,30452,'2022-08-28 17:59:52','GOLD',NULL,'GUEST',1,0,NULL,0,0,NULL),(57,'mdirkina','Margret','mdominettia@whitehouse.gov','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1991-02-23',NULL,NULL,40605,'2017-06-30 05:23:31','BRONZE',NULL,'GUEST',1,1,NULL,0,0,NULL),(58,'hlabeuilb','Hedwig','hknellb@sun.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2005-10-28',NULL,NULL,82688,'2019-06-04 20:22:00','GRANDMASTER',NULL,'GUEST',1,0,NULL,0,0,NULL),(59,'hfyshc','Hymie','hklynerc@macromedia.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1984-06-06',NULL,NULL,71165,'2014-08-19 07:48:57','BRONZE',NULL,'GUEST',1,1,NULL,0,0,NULL),(60,'screavend','Sheela','scescod@e-recht24.de','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1998-08-02',NULL,NULL,43037,'2014-02-15 20:05:31','SILVER',NULL,'GUEST',1,1,NULL,0,0,NULL),(61,'cbaggotte','Cortie','ccockline@seesaa.net','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2010-01-17',NULL,NULL,80868,'2018-04-23 04:13:59','BRONZE',NULL,'GUEST',1,2,NULL,0,0,NULL),(62,'aforrestillf','Ardelle','acrosselandf@cbslocal.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2013-07-26',NULL,NULL,33031,'2016-03-27 17:02:05','BRONZE',NULL,'GUEST',1,3,NULL,2,0,NULL),(63,'aarthursg','Andreana','amouldeng@intel.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'2003-01-24',NULL,NULL,21843,'2016-05-05 07:42:29','SILVER',NULL,'GUEST',1,4,NULL,6,0,NULL),(64,'acholoninh','Anastassia','acubinh@ed.gov','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1986-10-20',NULL,NULL,54786,'2015-09-27 03:10:28','PLATINUM',NULL,'GUEST',1,1,NULL,0,0,NULL),(65,'sgronoui','Stormie','smozzettii@jimdo.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'1992-03-01',NULL,NULL,62024,'2019-09-29 05:35:08','BRONZE',NULL,'GUEST',1,2,NULL,4,0,NULL),(66,'ericketj','Emelen','efinniganj@youtube.com','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',1,'1994-03-28',NULL,NULL,52062,'2020-09-24 18:02:14','SILVER',NULL,'GUEST',1,1,NULL,0,0,NULL),(67,'lwallagek','Lotte','llicquorishk@europa.eu','{bcrypt}$2a$10$hEyqv2U4XWVJVinuckqbQeZ7U1HxO8ICBfVF7TQ/tP75CMMG3vN0u',0,'2007-04-26',NULL,NULL,89421,'2019-11-10 07:08:00','BRONZE',NULL,'GUEST',1,0,NULL,0,0,NULL),(68,NULL,'yn','testtest','{bcrypt}$2a$10$RD2sz2/bBL2iRZ.SlYCa3e3sG1GC6ttbUTAmDoHr7wUSCJ46vSIqS',1,NULL,NULL,NULL,10000,'2023-11-07 15:44:30','BRONZE',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(69,NULL,'asd','asd','',NULL,NULL,NULL,NULL,10000,'2023-11-08 13:57:54','BRONZE',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(70,'두산멋쟁이','asdf','asdf','',NULL,NULL,NULL,NULL,961799,'2023-11-08 14:31:42','DIAMOND',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(71,'중급 쓰레쉬 봇','ww','di09876@naver.com','{bcrypt}$2a$10$BN6LQ18JWvKx2/x6kbhKDePnSIgTVyb2JMAey.CfurcsJdbVDn1oe',1,NULL,NULL,NULL,10110,'2023-11-09 10:12:24','PLATINUM',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(72,'회사생활진짜노잼','진창호','ckdgh8204@naver.com','{bcrypt}$2a$10$gNrqU/18J2a./YBbhCanwed18IBItaE.4pMjtvi3X6J.uvg/NAxIG',1,NULL,NULL,NULL,10000,'2023-11-09 10:16:13','BRONZE',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(73,'말랑콩떡밀크티','김경숙','lovablekks@naver.com','2325a3ac-2897-45f8-a5bc-f5db7941904b',NULL,NULL,NULL,NULL,10,'2023-11-09 10:16:55','SILVER',NULL,'GUEST',1,NULL,NULL,188,0,'Kakao'),(74,'채경호','채경호','corudgh123@naver.com','10f93b43-a867-4017-99e8-a051e3f9b3db',NULL,NULL,NULL,NULL,600,'2023-11-09 15:23:23','BRONZE',NULL,'GUEST',1,NULL,NULL,0,0,'Kakao'),(75,'선영이누나','윤우혁2','test@naver.com','{bcrypt}$2a$10$lMXOo2Iy0QO./mq.Jf98P.2VxS.dGwmdn6m74vlm4j9BAuxbnfUhC',NULL,NULL,NULL,NULL,5760,'2023-11-11 18:23:27','DIAMOND',NULL,'GUEST',1,NULL,NULL,1761,504,NULL),(76,'hihi','hihi','hihi','{bcrypt}$2a$10$Z.1zgA.Y9TNL27ovl0Rd/emMPZMvWjpBkn6cTxoTBtVMvksAVou.O',0,NULL,NULL,NULL,10010,'2023-11-11 22:33:47','IRON',NULL,'GUEST',1,0,NULL,349,168,NULL),(77,'1234','1234','qwe','{bcrypt}$2a$10$EmFp9kJlFE/VzOpHDsfgeO8mriH/cH9Lttl6yAvXj9vqW/sM/jyJC',1,NULL,NULL,NULL,10000,'2023-11-12 17:13:47','GRANDMASTER',NULL,'GUEST',1,NULL,NULL,192,168,NULL),(78,'skdk','dksk','ssss0@naver.com','{bcrypt}$2a$10$Do5OYIs6S3BeJyqFFG5aSOmKM7/hH5LljDRmRyyjOnkyhmbguyh8a',1,NULL,NULL,NULL,10040,'2023-11-13 10:45:25','BRONZE',NULL,'GUEST',1,0,NULL,1522,450,NULL),(79,'브론즈누누혁','누누혁','asdf@naver.com','{bcrypt}$2a$10$1y5cmwA90Cy5bTSYu7naPeIQ1Pty8GCsYN1ocYCbn9uBMZLAbEZKa',0,NULL,NULL,NULL,10000,'2023-11-13 20:35:37','DIAMOND',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(80,'1234','1234','asdffa@naver.com','{bcrypt}$2a$10$EBA3NWSqDUJE5IWVLpRPJ.sOhMYksTbW3wpcRLn116G2kw3qTaHri',1,NULL,NULL,NULL,10020,'2023-11-13 21:40:59','BRONZE',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(81,'1234','1234','test@naver.co','{bcrypt}$2a$10$mxCFmSywKtQw8TNxVFl6OeoqWSNsHud9Qs45HBDJ52ej82Vbn/tBG',1,NULL,NULL,NULL,10000,'2023-11-13 22:26:55','GRANDMASTER',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(82,'Hi^^','진창호','ssafy','{bcrypt}$2a$10$ymIh7ZBw3AyoNQyDkS5EpuY9PshbptY8Z/c.jwwIzpROmWsKA/uuq',1,NULL,NULL,NULL,10000,'2023-11-14 12:58:16','SILVER',NULL,'GUEST',1,NULL,NULL,0,0,NULL),(83,'진창호','진창호','ckdgh8205@naver.com','45798168-96f4-4e08-833a-e49a4d673fb5',NULL,NULL,NULL,NULL,10,'2023-11-14 12:58:32','SILVER',NULL,'GUEST',1,NULL,NULL,192,0,'Kakao'),(84,'1234','1234','test1@naver.com','{bcrypt}$2a$10$Jfo.GadLlqG/GHkkLSan6us3q/Jf55/E7lS5yhlogcs3NCxzXmYO.',1,NULL,NULL,NULL,10040,'2023-11-14 17:24:56','GRANDMASTER',NULL,'GUEST',1,NULL,NULL,675,504,NULL),(85,'1234','1234','aaa@naver.com','{bcrypt}$2a$10$ei.Mdmzcx2vAWw3eYOOpFOdj3G8rzH3AXpR9WcdWeiTu8OZgoFvo.',1,NULL,NULL,NULL,10020,'2023-11-14 17:50:26','DIAMOND',NULL,'GUEST',1,NULL,NULL,487,336,NULL),(86,'1234','1234','tlqkf@naver.com','{bcrypt}$2a$10$p3DgpR41zWSCV7rVoMt6juoHg4eJ3rD8sFvgoYnaubbGlRePaho/i',1,NULL,NULL,NULL,5130,'2023-11-15 15:09:51','GOLD',NULL,'GUEST',1,NULL,NULL,491,336,NULL),(87,'Alphano','Alphano','topaz_13@naver.com','{bcrypt}$2a$10$IhO9GocYA2p9W.KLhN1xJ.Co.Jw4TcrOfI042JjIYlmsj/EsjZVhi',0,NULL,NULL,NULL,10000,'2023-11-16 09:44:16',NULL,NULL,'GUEST',1,NULL,NULL,0,0,NULL),(88,'싸피존잘근우','김근우','twnkjsfagnji@gmail.com','{bcrypt}$2a$10$gqGu9GZPucfER3DtVIDeQepLWHCnaZQJsafRwfxzy60tytIMJqRV.',1,NULL,NULL,NULL,10000,'2023-11-16 10:58:54','GOLD',NULL,'GUEST',1,NULL,NULL,0,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vote` (
  `vote_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id1` int NOT NULL,
  `user_id2` int NOT NULL,
  `argument` varchar(200) NOT NULL,
  `user1_accept` tinyint(1) NOT NULL DEFAULT '0',
  `user2_accept` tinyint(1) NOT NULL DEFAULT '0',
  `vote_start` datetime DEFAULT NULL,
  `vote_deadline` datetime NOT NULL,
  `tier_limit` varchar(20) DEFAULT NULL,
  `promise` varchar(100) DEFAULT NULL,
  `deposit` int DEFAULT NULL,
  `select_line` int NOT NULL,
  `do_promise` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `FK_post_TO_vote_1` (`post_id`),
  KEY `FK_user_TO_vote_1` (`user_id1`),
  KEY `FK_user_TO_vote_2` (`user_id2`),
  CONSTRAINT `FK_post_TO_vote_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_user_TO_vote_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_TO_vote_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (1,1,2,1,'누구 잘못인가요',1,1,'2023-11-07 05:03:14','2023-11-10 15:00:00','IRON','지면 한숨 자고 올게요',10000,0,1),(2,2,4,3,'test2',1,1,'2023-11-07 05:13:13','2023-11-22 15:00:00','IRON','test2',10000,11100,0),(3,3,4,3,'test3',1,1,'2023-11-07 05:13:13','2023-11-14 15:00:00','IRON','test3',10000,11100,1),(4,4,4,3,'test4',1,1,'2023-11-07 05:17:25','2023-11-22 15:00:00','IRON','test4',10000,101,0),(5,5,4,3,'테스트',1,1,'2023-11-07 06:24:31','2023-11-24 15:00:00','MASTER','테스트',10000,1010,0),(6,6,4,3,'이거 내가 맞나??',1,1,'2023-11-08 01:44:48','2023-11-15 15:00:00','CHALLENGER','이거 내가 맞나??',10000,101,1),(7,7,4,1,'test 333',0,0,'2023-11-08 05:21:24','2023-11-06 15:00:00','IRON','test 333',10000,110,1),(8,8,4,1,'test 333',1,1,'2023-11-08 05:21:24','2023-11-06 15:00:00','IRON','test 333',10000,110,1),(9,9,4,1,'test@@',1,0,'2023-11-08 05:27:27','2023-11-06 15:00:00','PLATINUM','test@@',10000,100,1),(10,10,1,2,'이 장면에서 이게 맞나요?',1,0,'2023-11-09 00:29:26','2023-11-14 15:00:00','IRON','우싱싱',10000,11111,0),(11,11,5,12,'아무튼니잘못',1,0,'2023-11-09 01:13:43','2023-11-29 15:00:00','IRON','공강',10000,11111,0),(12,14,4,12,'블리츠크랭크가 칼리스타를 끄는것이 맞는지 아닌지',1,1,'2023-11-09 15:27:56','2023-11-16 12:00:00','IRON','지는사람 번지점프하고 오겠습니다...',10000,11,1),(13,17,12,4,'동영상 테스트1',0,0,'2023-11-10 00:27:33','2023-11-16 15:00:00','EMERALD','동영상 테스트1',10000,101,0),(14,19,9,13,'블리츠크랭크가 그랩을 하지 않았어야 했다.',1,1,'2023-11-10 00:51:54','2023-11-16 11:00:00','BRONZE','춤춤',10000,11111,1),(15,20,9,13,'승인 테스트',0,0,'2023-11-10 01:27:30','2023-11-15 15:00:00','CHALLENGER','승인 테스트',10000,10000,0),(16,21,9,13,'승인 테스트',1,0,'2023-11-10 01:27:30','2023-11-15 15:00:00','CHALLENGER','승인 테스트',10000,10000,0),(17,22,13,9,'1110 1420 테스트',1,1,'2023-11-10 05:21:30','2023-11-13 15:00:00','IRON','1110 1420 테스트',10000,10000,0),(18,26,12,9,'server테스트',1,0,'2023-11-10 08:42:49','2023-11-17 15:00:00','MASTER','server테스트',10000,10,0),(19,27,11,9,'1111 test1',1,0,'2023-11-11 05:29:39','2023-11-11 15:00:00','DIAMOND','1111 test1',10000,11111,0),(20,28,11,9,'1111 test2',1,0,'2023-11-11 05:58:03','2023-11-16 15:00:00','IRON','1111 test2',10000,1000,0),(21,29,11,9,'1111test3',1,0,'2023-11-11 06:12:46','2023-11-16 15:00:00','EMERALD','1111test3',10000,0,0),(22,30,11,9,'1111 test4',1,0,'2023-11-11 06:14:53','2023-11-15 15:00:00','EMERALD','1111 test4',10000,10000,0),(23,31,11,9,'1111 test5',1,0,'2023-11-11 06:56:09','2023-11-17 15:00:00','DIAMOND','1111 test5',10000,10,0),(24,32,11,9,'1111test6',1,0,'2023-11-11 08:36:14','2023-11-11 15:00:00','PLATINUM','1111test6',10000,100,0),(26,34,1,11,'1111모달테스트',1,0,'2023-11-11 12:21:23','2023-11-12 15:00:00','IRON','1111모달테스트',10000,0,0),(28,36,1,12,'싸움을 걸어도 됐는가 ?',1,1,'2023-11-13 07:34:23','2023-11-19 23:59:00','DIAMOND','닉네임을 상대방이 원하는 것으로 변경하겠습니다.',10000,11111,1),(29,38,75,10,'ㅋ별거없노',1,1,'2023-11-13 13:28:16','2023-11-13 05:00:00','IRON','ㅋ별거없노',10000,11000,0),(30,39,71,73,'흐리기',1,1,'2023-11-14 06:20:40','2023-11-16 00:00:00','IRON','나는!',10000,0,0),(31,40,12,4,'글작성 테스트',1,0,'2023-11-15 04:46:19','2023-11-22 15:00:00','IRON','글작성 테스트',10000,10,0),(32,41,12,4,'http://localhost:3000/lolvote/36',0,0,'2023-11-15 04:56:45','2023-11-22 15:00:00','IRON','http://localhost:3000/lolvote/36',10000,0,0),(33,43,9,75,'1115test\n1115test\n1115test',1,0,'2023-11-15 07:00:23','2023-11-15 15:00:00','GRANDMASTER','1115test',1000000,100,0),(34,44,9,75,'1115test2',1,0,'2023-11-15 07:13:33','2023-11-15 14:59:59','GRANDMASTER','1115test2',1000000,0,0),(35,45,9,75,'test1414',1,0,'2023-11-15 07:23:10','2023-11-16 15:00:00','GRANDMASTER','test1414',1000000,10000,0),(36,47,9,75,'1115test31115test3',1,1,'2023-11-15 07:44:10','2023-11-15 23:59:59','GRANDMASTER','1115test3',10000,10,0),(37,48,11,12,'이렐 초반 2데스에 이렐 잘못은 없거나 아주 미미하다',1,1,'2023-11-15 08:35:30','2023-11-19 23:59:59','IRON','지는 사람이 닉네임을 상대방 노예로 바꾸겠습니다',30000,11111,0),(38,49,2,7,'캐싱 업로드 제외 테스트',0,0,'2023-11-15 08:46:36','2023-11-16 23:59:59','IRON','캐싱 업로드 제외 테스트',10000,10000,0),(39,50,2,7,'캐싱 업로드 제외 테스트',0,0,'2023-11-15 08:46:36','2023-11-16 23:59:59','IRON','캐싱 업로드 제외 테스트',10000,10000,0),(40,51,11,8,'레넥톤이 스킬을 먼저 썼어야 했는가?',1,1,'2023-11-16 01:39:48','2023-11-19 23:59:59','GOLD','샹들리에 부르는 영상을 녹화하겠습니다.',10000,11111,0),(41,52,1,88,'1렙 때 라인을 밀지 말았어야했으면 왼쪽, 라인을 밀었어야 했으면 오른쪽에 투표',1,1,'2023-11-16 01:51:43','2023-11-21 23:59:59','EMERALD','슬릭백 추겠습니다.',5000,11111,0),(42,53,12,8,'레넥톤은 아래가 아니라 위로 빠졌어야 하는가?\n그렇다는 [왼쪽] 아니다는 [오른쪽]에 투표하세요.',1,1,'2023-11-16 03:45:43','2023-11-17 23:59:59','BRONZE','상대방에게 형이라고 부르겠습니다.',30000,11111,0),(43,54,2,7,'레넥톤이 다이브 후 살 수 있었나요? 살았다면 왼쪽, 아니라면 오른쪽',1,1,'2023-11-16 11:37:32','2023-11-18 23:59:59','IRON','그랜절하겠습니다.',10000,11111,0);
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wod`
--

DROP TABLE IF EXISTS `wod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wod` (
  `wod_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`wod_id`),
  KEY `FK_user_TO_wod_1` (`user_id`),
  KEY `FK_post_TO_wod_1_idx` (`post_id`),
  CONSTRAINT `FK_post_TO_wod_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_user_TO_wod_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wod`
--

LOCK TABLES `wod` WRITE;
/*!40000 ALTER TABLE `wod` DISABLE KEYS */;
INSERT INTO `wod` VALUES (2,11,27),(6,2,36),(8,12,48),(9,12,39),(10,12,6),(11,12,5),(13,12,52),(14,8,36),(16,9,19),(17,5,1);
/*!40000 ALTER TABLE `wod` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-17  9:56:52
