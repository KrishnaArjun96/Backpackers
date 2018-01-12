-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: backpackers
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `best_seller`
--

DROP TABLE IF EXISTS `best_seller`;
/*!50001 DROP VIEW IF EXISTS `best_seller`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `best_seller` AS SELECT 
 1 AS `Flight`,
 1 AS `Count`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `current_reservations_customer`
--

DROP TABLE IF EXISTS `current_reservations_customer`;
/*!50001 DROP VIEW IF EXISTS `current_reservations_customer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `current_reservations_customer` AS SELECT 
 1 AS `Customer`,
 1 AS `UserId`,
 1 AS `Reservation`,
 1 AS `Booking`,
 1 AS `Flight`,
 1 AS `TravelDate`,
 1 AS `Date of Booking`,
 1 AS `Fare`,
 1 AS `Departure`,
 1 AS `Arrival`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `customer_reservations`
--

DROP TABLE IF EXISTS `customer_reservations`;
/*!50001 DROP VIEW IF EXISTS `customer_reservations`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `customer_reservations` AS SELECT 
 1 AS `Airline`,
 1 AS `Flight`,
 1 AS `Reservation`,
 1 AS `BookingId`,
 1 AS `TravelDate`,
 1 AS `BookingDate`,
 1 AS `Customer`,
 1 AS `Passenger`,
 1 AS `Class`,
 1 AS `MealPref`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `itinerary`
--

DROP TABLE IF EXISTS `itinerary`;
/*!50001 DROP VIEW IF EXISTS `itinerary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `itinerary` AS SELECT 
 1 AS `Reservation`,
 1 AS `Booking`,
 1 AS `Flight`,
 1 AS `TravelDate`,
 1 AS `BookingDate`,
 1 AS `Fare`,
 1 AS `Customer`,
 1 AS `UserId`,
 1 AS `Passenger`,
 1 AS `SeatPref`,
 1 AS `MealPref`,
 1 AS `Class`,
 1 AS `Departs`,
 1 AS `Arrives`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `personalised`
--

DROP TABLE IF EXISTS `personalised`;
/*!50001 DROP VIEW IF EXISTS `personalised`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `personalised` AS SELECT 
 1 AS `UserId`,
 1 AS `Customer`,
 1 AS `flight`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `personalised_customer`
--

DROP TABLE IF EXISTS `personalised_customer`;
/*!50001 DROP VIEW IF EXISTS `personalised_customer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `personalised_customer` AS SELECT 
 1 AS `UserId`,
 1 AS `Customer`,
 1 AS `flight`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `rep_revenue`
--

DROP TABLE IF EXISTS `rep_revenue`;
/*!50001 DROP VIEW IF EXISTS `rep_revenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `rep_revenue` AS SELECT 
 1 AS `Representative`,
 1 AS `SSN`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `reservations_customer`
--

DROP TABLE IF EXISTS `reservations_customer`;
/*!50001 DROP VIEW IF EXISTS `reservations_customer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `reservations_customer` AS SELECT 
 1 AS `Customer`,
 1 AS `Flight`,
 1 AS `Reservation#`,
 1 AS `Booking Date`,
 1 AS `Representative`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `reservations_flight`
--

DROP TABLE IF EXISTS `reservations_flight`;
/*!50001 DROP VIEW IF EXISTS `reservations_flight`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `reservations_flight` AS SELECT 
 1 AS `Flight`,
 1 AS `Reservation`,
 1 AS `Booking`,
 1 AS `BookingDate`,
 1 AS `TravelDate`,
 1 AS `Representative`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `revenue_city`
--

DROP TABLE IF EXISTS `revenue_city`;
/*!50001 DROP VIEW IF EXISTS `revenue_city`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `revenue_city` AS SELECT 
 1 AS `City`,
 1 AS `Total revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `revenue_customer`
--

DROP TABLE IF EXISTS `revenue_customer`;
/*!50001 DROP VIEW IF EXISTS `revenue_customer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `revenue_customer` AS SELECT 
 1 AS `Customer`,
 1 AS `UserId`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `revenue_flight`
--

DROP TABLE IF EXISTS `revenue_flight`;
/*!50001 DROP VIEW IF EXISTS `revenue_flight`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `revenue_flight` AS SELECT 
 1 AS `Flight`,
 1 AS `Flight Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `sales_report`
--

DROP TABLE IF EXISTS `sales_report`;
/*!50001 DROP VIEW IF EXISTS `sales_report`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `sales_report` AS SELECT 
 1 AS `Reservation #`,
 1 AS `Month`,
 1 AS `Year`,
 1 AS `Sale`,
 1 AS `Representative`,
 1 AS `BookingDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `best_seller`
--

/*!50001 DROP VIEW IF EXISTS `best_seller`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `best_seller` AS select concat(`f`.`AirlineId`,' ',`f`.`FlightNo`) AS `Flight`,(select count(0) from `booking` `i` where ((`i`.`AirlineId` = `f`.`AirlineId`) and (`i`.`FlightNo` = `f`.`FlightNo`))) AS `Count` from `flight` `f` order by `Count` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `current_reservations_customer`
--

/*!50001 DROP VIEW IF EXISTS `current_reservations_customer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `current_reservations_customer` AS select distinct (select distinct concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `customer` `c`) where ((`c`.`PersonId` = `p`.`Id`) and (`c`.`UserId` = `r`.`UserId`))) AS `Customer`,`r`.`UserId` AS `UserId`,`i`.`ResrNo` AS `Reservation`,`i`.`Id` AS `Booking`,concat(`i`.`AirlineId`,' ',`i`.`FlightNo`) AS `Flight`,`i`.`TravelDate` AS `TravelDate`,`r`.`BookingDate` AS `Date of Booking`,(`r`.`BookingFee` + `r`.`Fare`) AS `Fare`,concat((select distinct `a`.`City` from `airport` `a` where (`a`.`Id` = `l`.`Origin`)),' at ',`l`.`Departure`) AS `Departure`,concat((select distinct `a`.`City` from `airport` `a` where (`a`.`Id` = `l`.`Destination`)),' at ',`l`.`Arrival`) AS `Arrival` from (((`booking` `i` join `reservation` `r` on((`r`.`ResrNo` = `i`.`ResrNo`))) join `flight` `f` on(((`f`.`AirlineId` = `i`.`AirlineId`) and (`f`.`FlightNo` = `i`.`FlightNo`)))) join `leg` `l` on(((`i`.`LegId` = `l`.`LegId`) and (`l`.`AirlineId` = `i`.`AirlineId`) and (`l`.`FlightNo` = `i`.`FlightNo`)))) order by `r`.`ResrNo`,`i`.`Id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `customer_reservations`
--

/*!50001 DROP VIEW IF EXISTS `customer_reservations`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_reservations` AS select distinct (select `airline`.`Name` from `airline` where (`airline`.`Id` = `i`.`AirlineId`)) AS `Airline`,concat(`i`.`AirlineId`,' ',`i`.`FlightNo`) AS `Flight`,`i`.`ResrNo` AS `Reservation`,`i`.`Id` AS `BookingId`,`i`.`TravelDate` AS `TravelDate`,`r`.`BookingDate` AS `BookingDate`,(select distinct concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `customer` `c`) where ((`c`.`PersonId` = `p`.`Id`) and (`c`.`UserId` = `r`.`UserId`))) AS `Customer`,`rp`.`Name` AS `Passenger`,(select `c`.`Name` from `class` `c` where (`c`.`Id` = `i`.`ClassId`)) AS `Class`,`rp`.`MealPref` AS `MealPref` from ((((`booking` `i` join `reservation` `r` on((`r`.`ResrNo` = `i`.`ResrNo`))) join `passenger` `rp` on((`rp`.`ResrNo` = `i`.`ResrNo`))) join `flight` `f` on(((`f`.`AirlineId` = `i`.`AirlineId`) and (`f`.`FlightNo` = `i`.`FlightNo`)))) join `leg` `l` on(((`i`.`LegId` = `l`.`LegId`) and (`l`.`AirlineId` = `i`.`AirlineId`) and (`l`.`FlightNo` = `i`.`FlightNo`)))) order by `i`.`ResrNo`,`i`.`Id`,`rp`.`Name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `itinerary`
--

/*!50001 DROP VIEW IF EXISTS `itinerary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `itinerary` AS select distinct `i`.`ResrNo` AS `Reservation`,`i`.`Id` AS `Booking`,concat(`i`.`AirlineId`,' ',`i`.`FlightNo`) AS `Flight`,`i`.`TravelDate` AS `TravelDate`,`r`.`BookingDate` AS `BookingDate`,(`r`.`BookingFee` + `r`.`Fare`) AS `Fare`,(select distinct concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `customer` `c`) where ((`c`.`PersonId` = `p`.`Id`) and (`c`.`UserId` = `r`.`UserId`))) AS `Customer`,`r`.`UserId` AS `UserId`,`rp`.`Name` AS `Passenger`,`rp`.`SeatPref` AS `SeatPref`,`rp`.`MealPref` AS `MealPref`,(select `class`.`Name` from `class` where (`class`.`Id` = `i`.`ClassId`)) AS `Class`,(select distinct `a`.`City` from `airport` `a` where (`a`.`Id` = `l`.`Origin`)) AS `Departs`,(select distinct `a`.`City` from `airport` `a` where (`a`.`Id` = `l`.`Destination`)) AS `Arrives` from ((((`booking` `i` join `reservation` `r` on((`r`.`ResrNo` = `i`.`ResrNo`))) join `passenger` `rp` on((`rp`.`ResrNo` = `i`.`ResrNo`))) join `flight` `f` on(((`f`.`AirlineId` = `i`.`AirlineId`) and (`f`.`FlightNo` = `i`.`FlightNo`)))) join `leg` `l` on(((`i`.`LegId` = `l`.`LegId`) and (`l`.`AirlineId` = `i`.`AirlineId`) and (`l`.`FlightNo` = `i`.`FlightNo`)))) order by `r`.`ResrNo`,`i`.`Id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `personalised`
--

/*!50001 DROP VIEW IF EXISTS `personalised`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `personalised` AS select distinct `c`.`UserId` AS `UserId`,(select concat(`person`.`FirstName`,' ',`person`.`LastName`) from `person` where (`c`.`PersonId` = `person`.`Id`)) AS `Customer`,concat(`l`.`AirlineId`,' ',`l`.`FlightNo`) AS `flight` from (`leg` `l` join `customer` `c`) where `l`.`Destination` in (select `l`.`Destination` from (`reservation` `r` join `booking` `i`) where ((`r`.`UserId` = `c`.`UserId`) and (`r`.`ResrNo` = `i`.`ResrNo`) and (`i`.`AirlineId` = `l`.`AirlineId`) and (`i`.`FlightNo` = `l`.`FlightNo`) and (`i`.`LegId` = `l`.`LegId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `personalised_customer`
--

/*!50001 DROP VIEW IF EXISTS `personalised_customer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `personalised_customer` AS select distinct `c`.`UserId` AS `UserId`,(select concat(`person`.`FirstName`,' ',`person`.`LastName`) from `person` where (`c`.`PersonId` = `person`.`Id`)) AS `Customer`,concat(`l`.`AirlineId`,' ',`l`.`FlightNo`) AS `flight` from (`leg` `l` join `customer` `c`) where `l`.`Destination` in (select `l`.`Destination` from (`reservation` `r` join `booking` `i`) where ((`r`.`UserId` = `c`.`UserId`) and (`r`.`ResrNo` = `i`.`ResrNo`) and (`i`.`AirlineId` = `l`.`AirlineId`) and (`i`.`FlightNo` = `l`.`FlightNo`) and (`i`.`LegId` = `l`.`LegId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rep_revenue`
--

/*!50001 DROP VIEW IF EXISTS `rep_revenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rep_revenue` AS select (select concat(`person`.`FirstName`,' ',`person`.`LastName`) from `person` where (`person`.`Id` = `e`.`PersonId`)) AS `Representative`,`e`.`SSN` AS `SSN`,sum((`r`.`BookingFee` + `r`.`Fare`)) AS `Revenue` from (`employee` `e` join `reservation` `r`) where ((`e`.`PersonId` = `r`.`EmployeeId`) and (`e`.`Role` = 'employee')) group by `r`.`EmployeeId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `reservations_customer`
--

/*!50001 DROP VIEW IF EXISTS `reservations_customer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reservations_customer` AS select distinct (select concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `customer` `c`) where ((`p`.`Id` = `c`.`PersonId`) and (`r`.`UserId` = `c`.`UserId`) and (`c`.`PersonId` = `r`.`CustomerId`))) AS `Customer`,concat(`f`.`AirlineId`,' ',`f`.`FlightNo`) AS `Flight`,`r`.`ResrNo` AS `Reservation#`,`r`.`BookingDate` AS `Booking Date`,(select concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `employee` `e`) where ((`p`.`Id` = `e`.`PersonId`) and (`e`.`SSN` = `r`.`EmployeeSSN`))) AS `Representative` from ((((`flight` `f` join `booking` `i`) join `reservation` `r`) join `person` `p`) join `customer` `c`) where ((`r`.`CustomerId` = `c`.`PersonId`) and (`r`.`UserId` = `c`.`UserId`) and (`i`.`AirlineId` = `f`.`AirlineId`) and (`i`.`FlightNo` = `f`.`FlightNo`) and (`i`.`ResrNo` = `r`.`ResrNo`)) order by `r`.`ResrNo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `reservations_flight`
--

/*!50001 DROP VIEW IF EXISTS `reservations_flight`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reservations_flight` AS select concat(`f`.`AirlineId`,' ',`f`.`FlightNo`) AS `Flight`,`r`.`ResrNo` AS `Reservation`,`b`.`Id` AS `Booking`,`r`.`BookingDate` AS `BookingDate`,`b`.`TravelDate` AS `TravelDate`,(select concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `employee` `e`) where ((`p`.`Id` = `e`.`PersonId`) and (`e`.`SSN` = `r`.`EmployeeSSN`))) AS `Representative` from ((`reservation` `r` join `flight` `f`) join `booking` `b`) where ((`f`.`AirlineId` = `b`.`AirlineId`) and (`f`.`FlightNo` = `b`.`FlightNo`) and (`b`.`ResrNo` = `r`.`ResrNo`)) order by `Reservation`,`Booking` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `revenue_city`
--

/*!50001 DROP VIEW IF EXISTS `revenue_city`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `revenue_city` AS select `a`.`City` AS `City`,sum((`r`.`BookingFee` + `r`.`Fare`)) AS `Total revenue` from (((`airport` `a` join `reservation` `r`) join `booking` `i`) join `leg` `l`) where ((`r`.`ResrNo` = `i`.`ResrNo`) and (`i`.`AirlineId` = `l`.`AirlineId`) and (`i`.`FlightNo` = `l`.`FlightNo`) and (`a`.`Id` = `l`.`Destination`)) group by `a`.`City` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `revenue_customer`
--

/*!50001 DROP VIEW IF EXISTS `revenue_customer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `revenue_customer` AS select (select concat(`person`.`FirstName`,' ',`person`.`LastName`) from `person` where (`person`.`Id` = `c`.`PersonId`)) AS `Customer`,`c`.`UserId` AS `UserId`,sum((`r`.`BookingFee` + `r`.`Fare`)) AS `Revenue` from (`customer` `c` join `reservation` `r`) where (`c`.`PersonId` = `r`.`CustomerId`) group by `r`.`CustomerId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `revenue_flight`
--

/*!50001 DROP VIEW IF EXISTS `revenue_flight`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `revenue_flight` AS select distinct concat(`i`.`AirlineId`,' ',`i`.`FlightNo`) AS `Flight`,((select count(0) from `booking` `i` where ((`i`.`AirlineId` = `f`.`AirlineId`) and (`i`.`FlightNo` = `f`.`FlightNo`)) group by (`i`.`AirlineId` and `i`.`FlightNo`)) * (`r`.`BookingFee` + `r`.`Fare`)) AS `Flight Revenue` from ((`flight` `f` join `booking` `i`) join `reservation` `r`) where ((`f`.`FlightNo` = `i`.`FlightNo`) and (`f`.`AirlineId` = `i`.`AirlineId`) and (`i`.`ResrNo` = `r`.`ResrNo`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `sales_report`
--

/*!50001 DROP VIEW IF EXISTS `sales_report`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `sales_report` AS select `reservation`.`ResrNo` AS `Reservation #`,month(`reservation`.`BookingDate`) AS `Month`,year(`reservation`.`BookingDate`) AS `Year`,(`reservation`.`BookingFee` + `reservation`.`Fare`) AS `Sale`,(select concat(`p`.`FirstName`,' ',`p`.`LastName`) from (`person` `p` join `employee` `e`) where ((`p`.`Id` = `e`.`PersonId`) and (`reservation`.`EmployeeSSN` = `e`.`SSN`))) AS `Representative`,`reservation`.`BookingDate` AS `BookingDate` from `reservation` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-05 16:14:01
