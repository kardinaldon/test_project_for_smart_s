INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Телевизор', 3, 14750, 1, '2019-05-02');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Смартфон', 1, 600, 2, '2019-08-15');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Смартфон', 2, 1200, 3, '2019-01-10');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Соковыжималка', 2, 1000, 4, '2019-07-25');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Наушники', 4, 1000, 5, '2019-09-11');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Клавиатура', 1, 100, 6, '2019-01-10');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Соковыжималка', 1, 100, 6, '2019-08-12');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Наушники', 1, 250, 1, '2019-07-12');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Наушники', 2, 500, 2, '2019-11-25');
INSERT INTO public.APP_PURCHASE (PURCHASE_NAME, COUNT_OF_PURCHASE, PURCHASE_AMOUNT, BUYERS_ID, PURCHASE_DATE) VALUES ('Наушники', 3, 750, 2, '2019-12-09');

INSERT INTO public.APP_USER (USER_NAME, USER_LAST_NAME, USER_AGE) VALUES ('Иван','Иванов', 10);
INSERT INTO public.APP_USER (USER_NAME, USER_LAST_NAME, USER_AGE) VALUES ('Пётр','Андреев', 18);
INSERT INTO public.APP_USER (USER_NAME, USER_LAST_NAME, USER_AGE) VALUES ('Елена','Юсупова', 18);
INSERT INTO public.APP_USER (USER_NAME, USER_LAST_NAME, USER_AGE) VALUES ('Егор','Зуев', 12);
INSERT INTO public.APP_USER (USER_NAME, USER_LAST_NAME, USER_AGE) VALUES ('Ольга','Шевченко', 30);
INSERT INTO public.APP_USER (USER_NAME, USER_LAST_NAME, USER_AGE) VALUES ('Сергей','Тимуров', 40);


INSERT INTO public.APP_CREDENTIALS (USER_ID, USERNAME, USER_PASS, ACTIVE, ROLES) VALUES (1,'user', '$2a$10$5e3dB36HeRcozRgp8xQfw.tfD3Qsut8xu/NT9g/DSpVKg9Kzuitrq', true, 'ADMIN');


