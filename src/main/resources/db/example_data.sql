DELETE FROM public.configuration;
DELETE FROM public.positions;

SELECT setval('public.positions_id_seq', 1, true);

INSERT INTO public.configuration(name, description, value)
VALUES  ('PARAM_1', 'Parameter konfiguracyjny pierwszy', 'VALUE_1'),
	    ('PARAM_2', 'Parameter konfiguracyjny drugi', 'VALUE_2'),
	    ('PARAM_3', 'Parameter konfiguracyjny trzeci', 'VALUE_3'),
	    ('PARAM_4', 'Parameter konfiguracyjny czwarty', 'VALUE_4');

INSERT INTO public.positions(id, name, description, base)
VALUES  ( 1, 'Starszy Programista', 'Minimum 5 lata doświadczenia', 6000.00),
        ( default, 'Programista', 'Minimum 3 lata doświadczenia', 4000.00),
        ( default, 'Młodszy Programista', 'Minimum 1 lata doświadczenia', 3000.00),
        ( default, 'Stażysta', 'Brak wymaganego doświadczenia', 2000.00),
        ( default, 'Analityk', 'Minimum 2 lata doświadczenia w analizie', 5000.00),
        ( default, 'Starszy Analityk', 'Minimum 4 lata doświadczenia w analizie', 7000.00),
        ( default, 'Architekt', 'Minimum 2 lata doświadczenia w projektowaniu', 8000.00),
        ( default, 'Starszy Architekt', 'Minimum 4 lata doświadczenia w projektowaniu', 10000.00),
        ( default, 'Kierownik', 'Zna się na wszystkim', 12000.00);


DELETE FROM public.userroles;
DELETE FROM public.roles;
DELETE FROM public.users;

SELECT setval('public.users_id_seq', 1, true);

INSERT INTO public.roles(id, name)
VALUES  (1,'DEFAULT'),
        (2,'USER'),
        (3,'ADMIN'),
        (4,'INACTIVE'),
        (5,'MANAGER');

INSERT INTO public.users(id, username, password, email, active, generated)
VALUES  (1, 'admin', '$2a$10$6YgDbyTn0HueCmQq.WHRiO3qMNtamoN9NMRuSOrMTjUIo8yWJin56', 'admin@admin.pl', true, null),
        (default, 'manager', '$2a$10$aSfjLyfIUZqovimQDJ2QhOChTlRWTUsMuu.EMjq7EAJ6ilffbWxGC', 'manager@manager.pl', true, null),
        (default, 'user1', '$2a$10$aSfjLyfIUZqovimQDJ2QhOChTlRWTUsMuu.EMjq7EAJ6ilffbWxGC', 'user1@user.pl', true, null),
        (default, 'user2', '$2a$10$aSfjLyfIUZqovimQDJ2QhOChTlRWTUsMuu.EMjq7EAJ6ilffbWxGC', 'user2@user.pl', true, null),
        (default, 'user3', '$2a$10$aSfjLyfIUZqovimQDJ2QhOChTlRWTUsMuu.EMjq7EAJ6ilffbWxGC', 'user3@user.pl', true, null),
        (default, 'user4', '$2a$10$aSfjLyfIUZqovimQDJ2QhOChTlRWTUsMuu.EMjq7EAJ6ilffbWxGC', 'user4@user.pl', true, null);

INSERT INTO public.userroles(userid, roleid)
VALUES  (1,1),
        (1,3),
        (2,1),
        (2,2),
        (3,1),
        (3,5),
        (4,1),
        (4,5),
        (5,1),
        (5,5),
        (6,1),
        (6,5);

DELETE FROM public.workers;

SELECT setval('public.workers_id_seq', 1, true);

INSERT INTO public.workers(id, userid, firstname, lastname, birthday, address, phonenumber)
VALUES  (1, 2, 'Adam', 'Manager', '1990-06-16', 'Poznań', '+48999999999'),
        (default, 3, 'Bartek', 'Worker', '1991-06-16', 'Katowice', '+48777777777'),
        (default, 4, 'Cezary', 'Worker', '1990-07-19', 'Kraków', '+48555555555'),
        (default, 5, 'Dawid', 'Worker', '1987-01-26', 'Wrocław', '+48333333333'),
        (default, 6, 'Eugeniusz', 'Worker', '1995-10-06', 'Warszawa', '+48111111111');

DELETE FROM public.workerpositions;

INSERT INTO public.workerpositions(workerid, positionid)
VALUES  (1, 9),
        (1, 8),
        (2, 1),
        (3, 3),
        (4, 4),
        (5, 6);

DELETE FROM public.salarytargets;

SELECT setval('public.salarytargets_id_seq', 1, true);

INSERT INTO public.salarytargets(id, workerid, name, bankaccount, selected)
VALUES  (1, 1, 'Bank ING', '690000111122223333444455556666', true),
        (default, 2, 'Bank ING', '691111222233334444555566667777', true),
        (default, 2, 'Bank PKO BP', '031234123400000000555588889999', false),
        (default, 3, 'Millenium', '041234123400000000555588889999', true),
        (default, 4, 'Rejestr Rozliczeniowy', '150000123400000000555500009999', true),
        (default, 5, 'mBANK', '905678876500000000980967561342', true);