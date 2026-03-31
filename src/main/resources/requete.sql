CREATE TABLE ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100),
                            category VARCHAR(100),
                            unit INT
);


INSERT INTO ingredient(name, category, unit)
VALUES ('riz', 'food', 1);


CREATE TABLE dish (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(100)
);

CREATE TABLE dish_ingredient (
                                 dish_id INT,
                                 ingredient_id INT,
                                 quantity INT
);

ALTER TABLE ingredient ADD COLUMN price INT;

INSERT INTO dish(name) VALUES ('plat1');

INSERT INTO dish(name) VALUES ('plat2');

INSERT INTO ingredient(name, category, unit, price) VALUES ('riz','food',1,100);
INSERT INTO ingredient(name, category, unit, price) VALUES ('huile','food',1,200);
INSERT INTO ingredient(name, category, unit, price) VALUES ('sel','food',1,50);

CREATE TABLE stock (
                       id SERIAL PRIMARY KEY,
                       ingredient_id INT,
                       quantity INT,
                       date DATE
);

INSERT INTO stock(ingredient_id, quantity, date)
VALUES (1, 500, '2024-01-01');

INSERT INTO stock(ingredient_id, quantity, date)
VALUES (1, 300, '2024-02-01');