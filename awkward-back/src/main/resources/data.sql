/*                  ########## ADDRESSES ##########                 */

INSERT INTO address(city, country) VALUES
('Polska', 'Rzeszów'),
('Polska', 'Jarosław'),
('Polska', 'Tarnobrzeg'),
('Polska', 'Przemyśl'),
('Polska', 'Warszawa'),
('Polska', 'Poznań'),
('Polska', 'Tarnów'),
('Polska', 'Łańcut'),
('Polska', 'Dębica'),
('Polska', 'Kraków'),
('Niemcy', 'Berlin'),
('Polska', 'Wrocław');



/*                  ########## GENDERS ##########                 */

INSERT INTO gender(gender) VALUES
('Męska'),
('Żeńska');



/*                  ########## INTERESTS ##########                 */

INSERT INTO interest(name) VALUES
('pływanie'),
('piłka nożna'),
('jazda na nartach'),
('jazda na dekorolce'),
('przejażdżki rowerowe'),
('jazda autem'),
('gotowanie'),
('czytanie książek'),
('skakanie na bungee'),
('tenis stołowy'),
('tenis ziemny'),
('gra w szachy');



/*                  ########## ROLES ##########                 */

INSERT INTO role(name, status) VALUES
('OWNER', 0),
('ADMIN', 1),
('MODERATOR', 2),
('USER', 3);



/*                  ########## UNIVERSITIES ##########                 */

INSERT INTO university(name, address_id) VALUES
('Politechnika Rzeszowska im. Ignacego Łukasiewicza', 1),
('Państwowa Wyższa Szkoła Techniczno-Ekonomiczna', 2),
('Politechnika Wrocławska', 12),
('Państwowa Wyższa Szkoła Zawodowa', 3),
('Akademia Górniczo-Hutnicza im. Stanisława Staszica', 10),
('Politechnika Krakowska im. Tadeusza Kościuszki', 10),
('Szkoła Główna Handlowa', 5),
('Uniwersytet Medyczny im. Karola Marcinkowskiego', 6);



/*                  ########## USERS ##########                 */

INSERT INTO user(EMAIL, LOGIN, NAME, SURNAME, DATE_OF_BIRTH, AGE, DESCRIPTION, PASSWORD, GENDER_ID, ROLE_ID, UNIVERSITY_ID, active)
VALUES
('kadrian13@o2.pl', 'AdiRey', 'Adrian', 'Kowal', '1999/01/06', 22, 'Pogramuje w Javie od dawna.', 'TODO123', 1, 1, 1, true),
('kassa1@gmail.com', 'AdiRey3', 'Adrian1', 'Kowal5', '1999/01/04', 21, 'Pogramuje w C# od dawna', 'TODO123', 1, 2, 1, true),
('kassa2@gmail.com', 'AdiRey1', 'Adrianna2', 'Kowals2', '1999/01/02', 231, 'Pogramuje w JS od dawna', 'TODO123', 2, 4, 1, true),
('kassa3@gmail.com', 'AdiRey5', 'Adrian5', 'Kowal1', '1999/01/05', 22, 'Pogramuje w RoR od dawna', 'TODO123', 1, 4, 1, true),
('kassa4@gmail.com', 'AdiRey4', 'Adrian4', 'Kowal3', '1999/01/07', 21, 'Pogramuje w Django od dawna', 'TODO123', 1, 3, 1, false),
('kassa5@gmail.com', 'AdiRey2', 'Adriana3', 'Kowala6', '1999/02/01', 11, 'Pogramuje w AngularCLI od dawna', 'TODO123', 2, 4, 1, true),
('kassa6@gmail.com', 'AdiRey6', 'Adrian4', 'Kowal4', '2000/01/06', 20, 'Pogramuje w TypeScript od dawna', 'TODO123', 1, 4, 3, true);



/*                  ########## PHOTOS ##########                 */

INSERT INTO photo(path, add_date, archive, user_id) VALUES
('user_images/1/52d5f9102c1a45afb882b71f4f0e5949.png', '2020-07-15 12:27:50.637901000', 0, 1),
('user_images/1/9a17438195074be19752ed2883191581.png', '2020-07-15 12:28:15.694505000', 0, 1),
('user_images/2/1b9ebcf62a1d4d6cadc898b62fe76ebf.png', '2020-07-15 12:28:31.536395000', 0, 2),
('user_images/3/105aadeff6bd4a13a0207485b9f2df00.png','2020-07-15 12:28:46.960154000', 0, 3),
('user_images/5/49cecfba5a454981bead2e391c12d14e.png','2020-07-15 12:28:49.520284000', 0, 5);

/*                  ########## USER_INTERESTS ##########                 */

INSERT INTO user_interest(user_id, interest_id) VALUES
(1, 3),
(1, 11),
(2, 5),
(3, 6),
(4, 2),
(7, 12),
(7, 10),
(6, 1);

/*                  ########## USER_ADDRESSES ##########                 */

INSERT INTO user_address(user_id, address_id) VALUES
(1, 3),
(1, 11),
(2, 5),
(3, 6),
(4, 2),
(7, 12),
(7, 10),
(6, 1);
