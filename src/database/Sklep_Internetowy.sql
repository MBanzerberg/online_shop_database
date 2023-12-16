

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE SklepS1
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE PracownikS2
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE PlacowkaS3
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE DostawcaS4
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE ProduktS5
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE ZamowienieS6
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE KlientS7
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE AdresS8
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Godz_otwS9
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE Kategoria_pr_jazdyS10
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE WlascicielS11
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table Sklepy_Internetowe

CREATE TABLE Sklepy_Internetowe(
  Id_sklepu Integer NOT NULL,
  Nazwa Varchar2(100 ) NOT NULL,
  Data Date NOT NULL,
  Adres_sklepu_www Varchar2(100 ) NOT NULL,
  Id_adresu Integer NOT NULL
)
/

-- Create indexes for table Sklepy_Internetowe

CREATE INDEX IX_Relationship10 ON Sklepy_Internetowe (Id_adresu)
/

-- Add keys for table Sklepy_Internetowe

ALTER TABLE Sklepy_Internetowe ADD CONSTRAINT Sklep_PK PRIMARY KEY (Id_sklepu)
/

-- Table Pracownicy

CREATE TABLE Pracownicy(
  Id_pracownika Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Drugie_imie Varchar2(20 ),
  Nazwisko Varchar2(20 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  Plec Varchar2(1 ) NOT NULL
        CHECK (Plec IN ('M','K')),
  PESEL Char(11 ),
  Nr_telefonu Varchar2(12 ) NOT NULL,
  Email Varchar2(100 ) NOT NULL,
  Wyksztalcenie Varchar2(1 ) NOT NULL
        CHECK (Wyksztalcenie IN ('P','S','Z','W')),
  Data_zatrudnienia Date NOT NULL,
  Stanowisko Varchar2(20 ) NOT NULL,
  Wynagrodzenie Number(10,2) NOT NULL,
  Id_sklepu Integer NOT NULL,
  Id_Placowki Integer,
  Id_adresu Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Zatrudnia ON Pracownicy (Id_sklepu)
/

CREATE INDEX IX_Daje_miejsce_pracy ON Pracownicy (Id_Placowki)
/

CREATE INDEX IX_Relationship8 ON Pracownicy (Id_adresu)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_PK PRIMARY KEY (Id_pracownika)
/

-- Table Ksiegowi

CREATE TABLE Ksiegowi(
  Zaswiadczenie_o_niekaralnosci Varchar2(20 ) NOT NULL,
  Certyfikat_ukonczenia_kursu Varchar2(20 ) NOT NULL,
  Id_pracownika Integer NOT NULL
)
/

-- Add keys for table Ksiegowi

ALTER TABLE Ksiegowi ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (Id_pracownika)
/

-- Table Magazynierzy

CREATE TABLE Magazynierzy(
  Zas_med_pracy Integer NOT NULL,
  Upr_na_wozek_widlowy Varchar2(20 ) NOT NULL,
  Id_prawa_jazdy Varchar2(20 ) NOT NULL,
  Id_pracownika Integer NOT NULL,
  Id_Placowki Integer
)
/

-- Create indexes for table Magazynierzy

CREATE INDEX IX_Pracuje_na_magazynie ON Magazynierzy (Id_Placowki)
/

-- Add keys for table Magazynierzy

ALTER TABLE Magazynierzy ADD CONSTRAINT Unique_Identifier2 PRIMARY KEY (Id_pracownika)
/

-- Table and Columns comments section

COMMENT ON COLUMN Magazynierzy.Zas_med_pracy IS 'Unikalny numer zaświadczenia od lekarza medycyny pracy'
/
COMMENT ON COLUMN Magazynierzy.Id_prawa_jazdy IS 'Unikalny numer prawa jazdy'
/

-- Table Produkty

CREATE TABLE Produkty(
  Id_produktu Integer NOT NULL,
  Nazwa Varchar2(100 ) NOT NULL,
  Cena Number(10,2) NOT NULL,
  Specyfikacja Varchar2(1000 ) NOT NULL,
  Opis Clob,
  Id_sklepu Integer NOT NULL
)
/

-- Create indexes for table Produkty

CREATE INDEX IX_Zaopatruje ON Produkty (Id_sklepu)
/

-- Add keys for table Produkty

ALTER TABLE Produkty ADD CONSTRAINT Produkt_PK PRIMARY KEY (Id_produktu)
/

-- Table Klienci

CREATE TABLE Klienci(
  Id_klienta Integer NOT NULL,
  Haslo Varchar2(20 ) NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(20 ) NOT NULL,
  Email Varchar2(100 ) NOT NULL,
  Nr_telefonu Varchar2(12 ),
  Plec Varchar2(1 )
        CHECK (Plec IN ('M','K')),
  Id_sklepu Integer NOT NULL,
  Id_adresu Integer NOT NULL
)
/

-- Create indexes for table Klienci

CREATE INDEX IX_Posiada_klienta ON Klienci (Id_sklepu)
/

CREATE INDEX IX_Relationship11 ON Klienci (Id_adresu)
/

-- Add keys for table Klienci

ALTER TABLE Klienci ADD CONSTRAINT Klient_PK PRIMARY KEY (Id_klienta)
/

-- Table Dostawcy

CREATE TABLE Dostawcy(
  Id_dostawcy Integer NOT NULL,
  Nazwa Varchar2(100 ) NOT NULL,
  Nr_telefonu Varchar2(12 ) NOT NULL,
  Email Varchar2(100 ) NOT NULL,
  Id_sklepu Integer NOT NULL,
  Id_adresu Integer NOT NULL
)
/

-- Create indexes for table Dostawcy

CREATE INDEX IX_Korzysta_z_uslug ON Dostawcy (Id_sklepu)
/

CREATE INDEX IX_Relationship12 ON Dostawcy (Id_adresu)
/

-- Add keys for table Dostawcy

ALTER TABLE Dostawcy ADD CONSTRAINT Dostawca_PK PRIMARY KEY (Id_dostawcy)
/

-- Table Placowki

CREATE TABLE Placowki(
  Id_Placowki Integer NOT NULL,
  Nr_telefonu Varchar2(12 ) NOT NULL,
  Email Varchar2(100 ) NOT NULL,
  Id_sklepu Integer NOT NULL,
  Id_adresu Integer NOT NULL
)
/

-- Create indexes for table Placowki

CREATE INDEX IX_Wynajmuje ON Placowki (Id_sklepu)
/

CREATE INDEX IX_Relationship7 ON Placowki (Id_adresu)
/

-- Add keys for table Placowki

ALTER TABLE Placowki ADD CONSTRAINT Placowka_PK PRIMARY KEY (Id_Placowki)
/

-- Table Punkty_obslugi

CREATE TABLE Punkty_obslugi(
  Nazwa_punktu Varchar2(100 ) NOT NULL,
  Ulatwienie_niepelnosprawni Char(1 ) NOT NULL,
  Id_Placowki Integer NOT NULL
)
/

-- Add keys for table Punkty_obslugi

ALTER TABLE Punkty_obslugi ADD CONSTRAINT Unique_Identifier3 PRIMARY KEY (Id_Placowki)
/

-- Table Magazyny

CREATE TABLE Magazyny(
  Rodzaj_magazynu Varchar2(1 ) NOT NULL
        CHECK (Rodzaj_magazynu IN ('C', 'R', 'W')),
  Powierzchnia Integer NOT NULL,
  Id_Placowki Integer NOT NULL
)
/

-- Add keys for table Magazyny

ALTER TABLE Magazyny ADD CONSTRAINT Unique_Identifier4 PRIMARY KEY (Id_Placowki)
/

-- Table Zamowienia

CREATE TABLE Zamowienia(
  Id_zamowienia Integer NOT NULL,
  Data_zamowienia Date NOT NULL,
  Status Varchar2(1 ) NOT NULL
        CHECK (Status IN ('O','P','C','S','D')),
  Id_klienta Integer NOT NULL,
  Id_sklepu Integer NOT NULL
)
/

-- Create indexes for table Zamowienia

CREATE INDEX IX_Zamawia ON Zamowienia (Id_klienta)
/

CREATE INDEX IX_Realizuje ON Zamowienia (Id_sklepu)
/

-- Add keys for table Zamowienia

ALTER TABLE Zamowienia ADD CONSTRAINT Zamowienie_PK PRIMARY KEY (Id_zamowienia)
/

-- Table Produkty_w_placowkach

CREATE TABLE Produkty_w_placowkach(
  Id_produktu Integer NOT NULL,
  Id_Placowki Integer NOT NULL,
  Ilosc Integer NOT NULL
)
/

-- Table and Columns comments section

COMMENT ON COLUMN Produkty_w_placowkach.Ilosc IS 'Ilość danego produktu na magazynie'
/

-- Table Zamowienia_Produkty

CREATE TABLE Zamowienia_Produkty(
  Id_zamowienia Integer NOT NULL,
  Id_produktu Integer NOT NULL
)
/

-- Table Placowki_Dostawcy

CREATE TABLE Placowki_Dostawcy(
  Id_Placowki Integer NOT NULL,
  Id_dostawcy Integer NOT NULL
)
/

-- Table Wlasciciele

CREATE TABLE Wlasciciele(
  Id_Wlasciciela Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(20 ) NOT NULL,
  Id_sklepu Integer NOT NULL
)
/

-- Create indexes for table Wlasciciele

CREATE INDEX IX_Relationship1 ON Wlasciciele (Id_sklepu)
/

-- Add keys for table Wlasciciele

ALTER TABLE Wlasciciele ADD CONSTRAINT PK_Wlasciciele PRIMARY KEY (Id_Wlasciciela)
/

-- Table and Columns comments section

COMMENT ON TABLE Wlasciciele IS 'Właściciele sklepu'
/
COMMENT ON COLUMN Wlasciciele.Id_Wlasciciela IS 'Unikalny numer właściciela'
/
COMMENT ON COLUMN Wlasciciele.Imie IS 'Imię właściciela'
/
COMMENT ON COLUMN Wlasciciele.Nazwisko IS 'Nazwisko właściciela'
/

-- Table Kategorie_prawa_jazdy

CREATE TABLE Kategorie_prawa_jazdy(
  Kategoria Char(20 ) NOT NULL
        CHECK (Kategoria IN ('A1', 'A2', 'A', 'B1', 'B', 'C1', 'C', 'D1', 'D' )),
  Id_pracownika Integer NOT NULL
)
/

-- Create indexes for table Kategorie_prawa_jazdy

CREATE INDEX IX_Relationship2 ON Kategorie_prawa_jazdy (Id_pracownika)
/

-- Add keys for table Kategorie_prawa_jazdy

ALTER TABLE Kategorie_prawa_jazdy ADD CONSTRAINT PK_Kategorie_prawa_jazdy PRIMARY KEY (Kategoria)
/

-- Table and Columns comments section

COMMENT ON TABLE Kategorie_prawa_jazdy IS 'Kategorie prawa jazdy'
/
COMMENT ON COLUMN Kategorie_prawa_jazdy.Kategoria IS 'Kategoria prawa jazdy: A1, A2, A, B1, B, C1, C, D1, D '
/

-- Table Adresy

CREATE TABLE Adresy(
  Id_adresu Integer NOT NULL,
  Miasto Varchar2(20 ) NOT NULL,
  Kod_pocztowy Char(6 ) NOT NULL,
  Ulica Varchar2(20 ) NOT NULL,
  Numer_domu Varchar2(10 ) NOT NULL,
  Numer_mieszkania Varchar2(10 )
)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (Id_adresu)
/

-- Table and Columns comments section

COMMENT ON TABLE Adresy IS 'Adresy'
/
COMMENT ON COLUMN Adresy.Id_adresu IS 'Unikalny identyfikator adresu'
/
COMMENT ON COLUMN Adresy.Miasto IS 'Nazwa miasta'
/
COMMENT ON COLUMN Adresy.Kod_pocztowy IS 'Kod pocztowy: xx-xxx'
/
COMMENT ON COLUMN Adresy.Ulica IS 'Nazwa ulicy'
/
COMMENT ON COLUMN Adresy.Numer_domu IS 'Numer domu'
/
COMMENT ON COLUMN Adresy.Numer_mieszkania IS 'Numer mieszkania'
/

-- Table Godziny_otwarc

CREATE TABLE Godziny_otwarc(
  Id_godzin_otwarcia Integer NOT NULL,
  Godzina_otwarcia Date NOT NULL,
  Godzina_zamkniecia Date NOT NULL,
  Dzien_tygodnia Varchar2(1 ) NOT NULL
        CHECK (Dzien_tygodnia IN ('M','T','W','H','F','S','D'))
)
/

-- Add keys for table Godziny_otwarc

ALTER TABLE Godziny_otwarc ADD CONSTRAINT PK_Godziny_otwarc PRIMARY KEY (Id_godzin_otwarcia)
/

-- Table and Columns comments section

COMMENT ON TABLE Godziny_otwarc IS 'Godziny otwarcia punktu obsługi'
/
COMMENT ON COLUMN Godziny_otwarc.Id_godzin_otwarcia IS 'Unikatowy numer godzin otwarcia punktu obsługi klienta'
/
COMMENT ON COLUMN Godziny_otwarc.Godzina_otwarcia IS 'Godzina otwarcia punktu'
/
COMMENT ON COLUMN Godziny_otwarc.Godzina_zamkniecia IS 'Godzina zamknięcia punktu'
/
COMMENT ON COLUMN Godziny_otwarc.Dzien_tygodnia IS 'Dzień tygodnia: M - Poniedziałek, T - Wtorek, W- Środa, H - Czwartek, F - Czwartek, S - Sobota, D - Niedziela'
/

-- Table Punkt_obslugi_Godziny_otwarcia

CREATE TABLE Punkt_obslugi_Godziny_otwarcia(
  Id_Placowki Integer NOT NULL,
  Id_godzin_otwarcia Integer NOT NULL
)
/

-- Add keys for table Punkt_obslugi_Godziny_otwarcia

ALTER TABLE Punkt_obslugi_Godziny_otwarcia ADD CONSTRAINT PK_Punkt_obslugi_Godziny_otwarcia PRIMARY KEY (Id_Placowki,Id_godzin_otwarcia)
/

-- Table Pojazdy

CREATE TABLE Pojazdy(
  Id_pojazdu Integer NOT NULL,
  Nazwa Varchar2(20 ) NOT NULL,
  Waga Integer NOT NULL,
  Id_Placowki Integer NOT NULL
)
/

-- Create indexes for table Pojazdy

CREATE INDEX IX_Relationship21 ON Pojazdy (Id_Placowki)
/

-- Add keys for table Pojazdy

ALTER TABLE Pojazdy ADD CONSTRAINT PK_Pojazdy PRIMARY KEY (Id_pojazdu)
/

-- Table and Columns comments section

COMMENT ON TABLE Pojazdy IS 'Pojazdy'
/
COMMENT ON COLUMN Pojazdy.Id_pojazdu IS 'Unikalny numer pojazdu należącego do magazynu'
/
COMMENT ON COLUMN Pojazdy.Nazwa IS 'Nazwa pojazdu'
/
COMMENT ON COLUMN Pojazdy.Waga IS 'Waga pojazdu'
/

-- Trigger for sequence SklepS1 for column Id_sklepu in table Sklepy_Internetowe ---------
CREATE OR REPLACE TRIGGER ts_Sklepy_Internetowe_SklepS1 BEFORE INSERT
ON Sklepy_Internetowe FOR EACH ROW
BEGIN
  :new.Id_sklepu := SklepS1.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Sklepy_Internetowe_SklepS1 AFTER UPDATE OF Id_sklepu
ON Sklepy_Internetowe FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_sklepu in table Sklepy_Internetowe as it uses sequence.');
END;
/

-- Trigger for sequence PracownikS2 for column Id_pracownika in table Pracownicy ---------
CREATE OR REPLACE TRIGGER ts_Pracownicy_PracownikS2 BEFORE INSERT
ON Pracownicy FOR EACH ROW
BEGIN
  :new.Id_pracownika := PracownikS2.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Pracownicy_PracownikS2 AFTER UPDATE OF Id_pracownika
ON Pracownicy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_pracownika in table Pracownicy as it uses sequence.');
END;
/

-- Trigger for sequence ProduktS5 for column Id_produktu in table Produkty ---------
CREATE OR REPLACE TRIGGER ts_Produkty_ProduktS5 BEFORE INSERT
ON Produkty FOR EACH ROW
BEGIN
  :new.Id_produktu := ProduktS5.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Produkty_ProduktS5 AFTER UPDATE OF Id_produktu
ON Produkty FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_produktu in table Produkty as it uses sequence.');
END;
/

-- Trigger for sequence KlientS7 for column Id_klienta in table Klienci ---------
CREATE OR REPLACE TRIGGER ts_Klienci_KlientS7 BEFORE INSERT
ON Klienci FOR EACH ROW
BEGIN
  :new.Id_klienta := KlientS7.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Klienci_KlientS7 AFTER UPDATE OF Id_klienta
ON Klienci FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_klienta in table Klienci as it uses sequence.');
END;
/

-- Trigger for sequence DostawcaS4 for column Id_dostawcy in table Dostawcy ---------
CREATE OR REPLACE TRIGGER ts_Dostawcy_DostawcaS4 BEFORE INSERT
ON Dostawcy FOR EACH ROW
BEGIN
  :new.Id_dostawcy := DostawcaS4.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Dostawcy_DostawcaS4 AFTER UPDATE OF Id_dostawcy
ON Dostawcy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_dostawcy in table Dostawcy as it uses sequence.');
END;
/

-- Trigger for sequence PlacowkaS3 for column Id_Placowki in table Placowki ---------
CREATE OR REPLACE TRIGGER ts_Placowki_PlacowkaS3 BEFORE INSERT
ON Placowki FOR EACH ROW
BEGIN
  :new.Id_Placowki := PlacowkaS3.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Placowki_PlacowkaS3 AFTER UPDATE OF Id_Placowki
ON Placowki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_Placowki in table Placowki as it uses sequence.');
END;
/

-- Trigger for sequence ZamowienieS6 for column Id_zamowienia in table Zamowienia ---------
CREATE OR REPLACE TRIGGER ts_Zamowienia_ZamowienieS6 BEFORE INSERT
ON Zamowienia FOR EACH ROW
BEGIN
  :new.Id_zamowienia := ZamowienieS6.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Zamowienia_ZamowienieS6 AFTER UPDATE OF Id_zamowienia
ON Zamowienia FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_zamowienia in table Zamowienia as it uses sequence.');
END;
/

-- Trigger for sequence WlascicielS11 for column Id_Wlasciciela in table Wlasciciele ---------
CREATE OR REPLACE TRIGGER ts_Wlasciciele_WlascicielS11 BEFORE INSERT
ON Wlasciciele FOR EACH ROW
BEGIN
  :new.Id_Wlasciciela := WlascicielS11.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wlasciciele_WlascicielS11 AFTER UPDATE OF Id_Wlasciciela
ON Wlasciciele FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_Wlasciciela in table Wlasciciele as it uses sequence.');
END;
/

-- Trigger for sequence Kategoria_pr_jazdyS10 for column Kategoria in table Kategorie_prawa_jazdy ---------
CREATE OR REPLACE TRIGGER ts_Kategorie_prawa_jazdy_Kategoria_pr_jazdyS10 BEFORE INSERT
ON Kategorie_prawa_jazdy FOR EACH ROW
BEGIN
  :new.Kategoria := Kategoria_pr_jazdyS10.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Kategorie_prawa_jazdy_Kategoria_pr_jazdyS10 AFTER UPDATE OF Kategoria
ON Kategorie_prawa_jazdy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Kategoria in table Kategorie_prawa_jazdy as it uses sequence.');
END;
/

-- Trigger for sequence AdresS8 for column Id_adresu in table Adresy ---------
CREATE OR REPLACE TRIGGER ts_Adresy_AdresS8 BEFORE INSERT
ON Adresy FOR EACH ROW
BEGIN
  :new.Id_adresu := AdresS8.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Adresy_AdresS8 AFTER UPDATE OF Id_adresu
ON Adresy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_adresu in table Adresy as it uses sequence.');
END;
/

-- Trigger for sequence Godz_otwS9 for column Id_godzin_otwarcia in table Godziny_otwarc ---------
CREATE OR REPLACE TRIGGER ts_Godziny_otwarc_Godz_otwS9 BEFORE INSERT
ON Godziny_otwarc FOR EACH ROW
BEGIN
  :new.Id_godzin_otwarcia := Godz_otwS9.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Godziny_otwarc_Godz_otwS9 AFTER UPDATE OF Id_godzin_otwarcia
ON Godziny_otwarc FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_godzin_otwarcia in table Godziny_otwarc as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE Pracownicy ADD CONSTRAINT Zatrudnia FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Dostawcy ADD CONSTRAINT Korzysta_z_uslug FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Daje_miejsce_pracy FOREIGN KEY (Id_Placowki) REFERENCES Placowki (Id_Placowki)
/



ALTER TABLE Magazynierzy ADD CONSTRAINT Pracuje_na_magazynie FOREIGN KEY (Id_Placowki) REFERENCES Magazyny (Id_Placowki)
/



ALTER TABLE Placowki ADD CONSTRAINT Wynajmuje FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Produkty ADD CONSTRAINT Zaopatruje FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Zamowienia ADD CONSTRAINT Zamawia FOREIGN KEY (Id_klienta) REFERENCES Klienci (Id_klienta)
/



ALTER TABLE Klienci ADD CONSTRAINT Posiada_klienta FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Zamowienia ADD CONSTRAINT Realizuje FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Wlasciciele ADD CONSTRAINT Sklep_ma_wlasciciela FOREIGN KEY (Id_sklepu) REFERENCES Sklepy_Internetowe (Id_sklepu)
/



ALTER TABLE Kategorie_prawa_jazdy ADD CONSTRAINT Kierowca_ma_kategorie FOREIGN KEY (Id_pracownika) REFERENCES Magazynierzy (Id_pracownika)
/



ALTER TABLE Placowki ADD CONSTRAINT Placowka_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE Sklepy_Internetowe ADD CONSTRAINT Sklep_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE Klienci ADD CONSTRAINT Klient_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE Dostawcy ADD CONSTRAINT Dostawca_ma_adres FOREIGN KEY (Id_adresu) REFERENCES Adresy (Id_adresu)
/



ALTER TABLE Punkt_obslugi_Godziny_otwarcia ADD CONSTRAINT Punkt_obslugi_posiada FOREIGN KEY (Id_Placowki) REFERENCES Punkty_obslugi (Id_Placowki)
/



ALTER TABLE Punkt_obslugi_Godziny_otwarcia ADD CONSTRAINT Godziny_otwarcia_posiada FOREIGN KEY (Id_godzin_otwarcia) REFERENCES Godziny_otwarc (Id_godzin_otwarcia)
/



ALTER TABLE Pojazdy ADD CONSTRAINT Pojazd_nalezy_do_magazynu FOREIGN KEY (Id_Placowki) REFERENCES Magazyny (Id_Placowki)
/
