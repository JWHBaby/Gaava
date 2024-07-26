-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Июл 26 2024 г., 18:07
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `music1`
--

-- --------------------------------------------------------

--
-- Структура таблицы `music_playon`
--

CREATE TABLE `music_playon` (
  `id_track` int NOT NULL,
  `photo` varchar(255) NOT NULL,
  `nameTrack` varchar(255) NOT NULL,
  `artistName` varchar(255) NOT NULL,
  `audio` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `music_playon`
--

INSERT INTO `music_playon` (`id_track`, `photo`, `nameTrack`, `artistName`, `audio`) VALUES
(1, 'kishlak.jpg', 'Она одна (кавер)', 'Кишлак', 'Кишлак - Она одна (Ранетки cover).mp3'),
(2, 'photo.jpg', 'FE!N', 'Travis Scott', 'fe!n.wav');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
