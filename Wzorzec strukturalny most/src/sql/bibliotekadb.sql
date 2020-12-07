-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 08 Gru 2020, 00:21
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `bibliotekadb`
--
CREATE DATABASE IF NOT EXISTS `bibliotekadb` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
USE `bibliotekadb`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `bibliotekarz`
--

CREATE TABLE `bibliotekarz` (
  `ID_bibliotekarza` int(9) NOT NULL,
  `Imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `bibliotekarz`
--

INSERT INTO `bibliotekarz` (`ID_bibliotekarza`, `Imie`, `Nazwisko`, `Pesel`) VALUES
(1, 'Anna', 'Kowalska', '86010280654');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ksiazka`
--

CREATE TABLE `ksiazka` (
  `ID_ksiazki` int(9) NOT NULL,
  `Autor` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `Tytul` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `ISBN` varchar(10) COLLATE utf8_polish_ci NOT NULL,
  `Wydawnictwo` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `Status` int(1) NOT NULL,
  `ID_uzytkownika` int(9) DEFAULT NULL,
  `Data_wypozyczenia` date DEFAULT NULL,
  `Jezyk` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `fragment` varchar(500) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `ksiazka`
--

INSERT INTO `ksiazka` (`ID_ksiazki`, `Autor`, `Tytul`, `ISBN`, `Wydawnictwo`, `Status`, `ID_uzytkownika`, `Data_wypozyczenia`, `Jezyk`, `fragment`) VALUES
(1, 'Henryk Sienkiewicz', 'Krzyzacy', '78654', 'Bellona', 0, 1, '2021-05-18', 'polski', 'bitwa pod Grunwaldem'),
(2, 'Boleslaw Prus', 'Lalka', '54786', 'Greg', 1, NULL, NULL, 'polski', NULL),
(3, 'Fiodor Dostojewski', 'Zbrodnia i kara', '89321', 'Greg', 0, 1, '2021-01-01', 'polski', NULL),
(4, 'Osamu Dazai', 'Zatracenie', '75908', 'Czytelnik', 1, NULL, NULL, 'polski', NULL),
(5, 'Arthur Conan Doyle', 'Sherlock Holmes', '76590', 'Zysk', 0, 1, '2021-03-01', 'angielski', NULL),
(6, NULL, 'Telegazeta', '82909', 'tv', 1, NULL, NULL, NULL, 'gazeta');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `system`
--

CREATE TABLE `system` (
  `ID_systemu` int(9) NOT NULL,
  `Login` varchar(15) COLLATE utf8_polish_ci NOT NULL,
  `Haslo` varchar(15) COLLATE utf8_polish_ci NOT NULL,
  `Typ_konta` int(1) NOT NULL,
  `ID_uzytkownika` int(9) DEFAULT NULL,
  `ID_bibliotekarza` int(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `system`
--

INSERT INTO `system` (`ID_systemu`, `Login`, `Haslo`, `Typ_konta`, `ID_uzytkownika`, `ID_bibliotekarza`) VALUES
(1, 'anna', 'haslo123', 0, NULL, 1),
(2, 'maja', 'haslo123', 1, 1, NULL),
(3, 'piotr', 'haslo123', 1, 2, NULL),
(4, 'jan', 'haslo123', 1, 3, NULL),
(8, 'karol', 'haslo123', 1, 8, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `ID_uzytkownika` int(9) NOT NULL,
  `Imie` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Nazwisko` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `Pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL,
  `Status_konta` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`ID_uzytkownika`, `Imie`, `Nazwisko`, `Pesel`, `Status_konta`) VALUES
(1, 'Maja', 'Malinowska', '99080203405', 1),
(2, 'Piotr', 'Szewczyk', '80010289453', 1),
(3, 'Jan', 'Jankowski', '75091085476', 1),
(8, 'Karol', 'Kawalec', '123456789', 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `bibliotekarz`
--
ALTER TABLE `bibliotekarz`
  ADD PRIMARY KEY (`ID_bibliotekarza`);

--
-- Indeksy dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  ADD PRIMARY KEY (`ID_ksiazki`),
  ADD UNIQUE KEY `ISBN` (`ISBN`),
  ADD KEY `ID_uzytkownika` (`ID_uzytkownika`);

--
-- Indeksy dla tabeli `system`
--
ALTER TABLE `system`
  ADD PRIMARY KEY (`ID_systemu`),
  ADD KEY `ID_uzytkownika` (`ID_uzytkownika`),
  ADD KEY `ID_bibliotekarza` (`ID_bibliotekarza`);

--
-- Indeksy dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`ID_uzytkownika`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `bibliotekarz`
--
ALTER TABLE `bibliotekarz`
  MODIFY `ID_bibliotekarza` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  MODIFY `ID_ksiazki` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `system`
--
ALTER TABLE `system`
  MODIFY `ID_systemu` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `ID_uzytkownika` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  ADD CONSTRAINT `ksiazka_ibfk_1` FOREIGN KEY (`ID_uzytkownika`) REFERENCES `uzytkownik` (`ID_uzytkownika`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `system`
--
ALTER TABLE `system`
  ADD CONSTRAINT `system_ibfk_1` FOREIGN KEY (`ID_uzytkownika`) REFERENCES `uzytkownik` (`ID_uzytkownika`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `system_ibfk_2` FOREIGN KEY (`ID_bibliotekarza`) REFERENCES `bibliotekarz` (`ID_bibliotekarza`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
