-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Muzeji
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Muzeji
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Muzeji` DEFAULT CHARACTER SET utf8 ;
USE `Muzeji` ;

-- -----------------------------------------------------
-- Table `Muzeji`.`lokacija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`lokacija` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `drzava` VARCHAR(45) NOT NULL,
  `grad` VARCHAR(45) NOT NULL,
  `geolokacija_duzina` FLOAT(10,6) NOT NULL,
  `geolokacija_sirina` FLOAT(10,6) NOT NULL,
  `adresa` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`tip_muzeja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`tip_muzeja` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`muzej`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`muzej` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `broj_telefona` VARCHAR(45) NOT NULL,
  `lokacija_id` INT NOT NULL,
  `tip_muzeja_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_muzej_lokacija1_idx` (`lokacija_id` ASC) VISIBLE,
  INDEX `fk_muzej_tip_muzeja1_idx` (`tip_muzeja_id` ASC) VISIBLE,
  CONSTRAINT `fk_muzej_lokacija1`
    FOREIGN KEY (`lokacija_id`)
    REFERENCES `Muzeji`.`lokacija` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_muzej_tip_muzeja1`
    FOREIGN KEY (`tip_muzeja_id`)
    REFERENCES `Muzeji`.`tip_muzeja` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`korisnik` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `korisnicko_ime` VARCHAR(45) NOT NULL,
  `lozinka` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NOT NULL,
  `aktivan` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `korisnicko_ime_UNIQUE` (`korisnicko_ime` ASC) VISIBLE,
  UNIQUE INDEX `token_UNIQUE` (`token` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`admin` (
  `korisnik_id` INT NOT NULL,
  INDEX `fk_admin_korisnik1_idx` (`korisnik_id` ASC) VISIBLE,
  PRIMARY KEY (`korisnik_id`),
  CONSTRAINT `fk_admin_korisnik1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `Muzeji`.`korisnik` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`stanje_naloga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`stanje_naloga` (
  `id` INT NOT NULL,
  `stanje` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`registrovani_korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`registrovani_korisnik` (
  `korisnik_id` INT NOT NULL,
  `stanje_naloga_id` INT NOT NULL,
  PRIMARY KEY (`korisnik_id`),
  INDEX `fk_registrovaniKorisnik_stanje_naloga1_idx` (`stanje_naloga_id` ASC) VISIBLE,
  CONSTRAINT `fk_registrovaniKorisnik_korisnik1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `Muzeji`.`korisnik` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registrovaniKorisnik_stanje_naloga1`
    FOREIGN KEY (`stanje_naloga_id`)
    REFERENCES `Muzeji`.`stanje_naloga` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`prezentacija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`prezentacija` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `video` VARCHAR(100) NULL,
  `muzej_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_prezentacija_muzej1_idx` (`muzej_id` ASC) VISIBLE,
  CONSTRAINT `fk_prezentacija_muzej1`
    FOREIGN KEY (`muzej_id`)
    REFERENCES `Muzeji`.`muzej` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`slika`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`slika` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `slika` VARCHAR(100) NOT NULL,
  `prezentacija_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_slika_prezentacija1_idx` (`prezentacija_id` ASC) VISIBLE,
  CONSTRAINT `fk_slika_prezentacija1`
    FOREIGN KEY (`prezentacija_id`)
    REFERENCES `Muzeji`.`prezentacija` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`posjeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`posjeta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datum` DATE NOT NULL,
  `vrijeme` TIME NOT NULL,
  `trajanje` DOUBLE NOT NULL,
  `cijena` DOUBLE NOT NULL,
  `prezentacija_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_posjeta_prezentacija1_idx` (`prezentacija_id` ASC) VISIBLE,
  CONSTRAINT `fk_posjeta_prezentacija1`
    FOREIGN KEY (`prezentacija_id`)
    REFERENCES `Muzeji`.`prezentacija` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`karta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`karta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `posjeta_id` INT NOT NULL,
  `registrovani_korisnik_korisnik_id` INT NOT NULL,
  `broj_karte` INT NOT NULL,
  `placena` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_karta_posjeta1_idx` (`posjeta_id` ASC) VISIBLE,
  INDEX `fk_karta_registrovani_korisnik1_idx` (`registrovani_korisnik_korisnik_id` ASC) VISIBLE,
  CONSTRAINT `fk_karta_posjeta1`
    FOREIGN KEY (`posjeta_id`)
    REFERENCES `Muzeji`.`posjeta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_karta_registrovani_korisnik1`
    FOREIGN KEY (`registrovani_korisnik_korisnik_id`)
    REFERENCES `Muzeji`.`registrovani_korisnik` (`korisnik_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`aktivnost`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`aktivnost` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vrijeme` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`logovanje_aktivnosti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`logovanje_aktivnosti` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aktivnost` VARCHAR(150) NOT NULL,
  `korisnik_id` INT NOT NULL,
  `vrijeme` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_logovanje_aktivnosti_korisnik1_idx` (`korisnik_id` ASC) VISIBLE,
  CONSTRAINT `fk_logovanje_aktivnosti_korisnik1`
    FOREIGN KEY (`korisnik_id`)
    REFERENCES `Muzeji`.`korisnik` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`tip_kartice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`tip_kartice` (
  `id` INT NOT NULL,
  `tip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`kartica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`kartica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `broj_kartice` VARCHAR(16) NOT NULL,
  `pin` VARCHAR(4) NOT NULL,
  `stanje_racuna` FLOAT NOT NULL,
  `omoguceno_placanje` TINYINT NOT NULL,
  `datum_isticanja` DATE NOT NULL,
  `registrovani_korisnik_korisnik_id` INT NOT NULL,
  `tip_kartice_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_kartica_registrovani_korisnik1_idx` (`registrovani_korisnik_korisnik_id` ASC) VISIBLE,
  INDEX `fk_kartica_tip_kartice1_idx` (`tip_kartice_id` ASC) VISIBLE,
  UNIQUE INDEX `broj_kartice_UNIQUE` (`broj_kartice` ASC) VISIBLE,
  CONSTRAINT `fk_kartica_registrovani_korisnik1`
    FOREIGN KEY (`registrovani_korisnik_korisnik_id`)
    REFERENCES `Muzeji`.`registrovani_korisnik` (`korisnik_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_kartica_tip_kartice1`
    FOREIGN KEY (`tip_kartice_id`)
    REFERENCES `Muzeji`.`tip_kartice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`transakcija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`transakcija` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `iznos` FLOAT NOT NULL,
  `datum` DATETIME NOT NULL,
  `kartica_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transakcija_kartica1_idx` (`kartica_id` ASC) VISIBLE,
  CONSTRAINT `fk_transakcija_kartica1`
    FOREIGN KEY (`kartica_id`)
    REFERENCES `Muzeji`.`kartica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`video_app`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`video_app` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `video` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Muzeji`.`slika_app`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Muzeji`.`slika_app` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `slika` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
