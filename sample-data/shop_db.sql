DROP TABLE IF EXISTS public.products CASCADE;
DROP TABLE IF EXISTS public.products;
DROP TABLE IF EXISTS public.product_categories;
DROP TABLE IF EXISTS public.suppliers;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.order_items;
DROP TABLE IF EXISTS public.order_items;

CREATE TABLE public.products (
                             id serial NOT NULL PRIMARY KEY,
                             prod_name varchar NOT NULL,
                             description varchar,
                             category_id int NOT NULL,
                             unit_price int NOT NULL,
                             currency varchar DEFAULT 'USD',
                             supplier_id int NOT NULL

);

DROP TABLE IF EXISTS public.product_categories CASCADE;

CREATE TABLE public.product_categories (
                                  id serial NOT NULL PRIMARY KEY,
                                  name text NOT NULL,
                                  description text
);

DROP TABLE IF EXISTS public.suppliers CASCADE;

CREATE TABLE public.suppliers (
                               id serial NOT NULL PRIMARY KEY,
                               name text NOT NULL
);

DROP TABLE IF EXISTS public.orders CASCADE;
CREATE TABLE public.orders (
                              id serial NOT NULL PRIMARY KEY,
                              user_id int NOT NULL,
                              address_id int NOT NULL,
                              total_price int NOT NULL,
                              timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users (
                            id serial NOT NULL PRIMARY KEY ,
                            name text NOT NULL,
                            email text NOT NULL ,
                            password text NOT NULL
);

DROP TABLE IF EXISTS public.order_items CASCADE;
CREATE TABLE public.order_items (
                                product_id    int NOT NULL,
                                amount        int NOT NULL,
                                order_id      int NOT NULL,
                                subtotal_rice int NOT NULL
);

DROP TABLE IF EXISTS public.address CASCADE;
CREATE TABLE public.address(
                               id serial NOT NULL PRIMARY KEY ,
                               first_name text NOT NULL,
                               last_name text NOT NULL,
                               street varchar NOT NULL ,
                               city text NOT NULL ,
                               zipcode varchar NOT NULL,
                               user_id int NOT NULL
);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.product_categories(id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.suppliers(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id),
    ADD CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES public.address(id);


ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES public.products(id),
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public.orders(id);

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);

INSERT INTO public.suppliers (name) VALUES ('Cycleops');
INSERT INTO public.suppliers (name) VALUES ('LooneyTools');

INSERT INTO public.product_categories (name, description) VALUES ('fun', 'Unnecessary fun items for boring days.');
INSERT INTO public.product_categories (name, description) VALUES ('home', 'These utilities might come in handy one day.');
INSERT INTO public.product_categories (name, description) VALUES ('kitchen', 'Everyone needs some kitchen supplies.');

INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Sunglass', 'For sunny days.', 1, 12, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Candle Bulb', 'For candle-lit date nights.', 2, 5, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Bottle Water', 'Definitely a thirst trap.', 3, 3, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Self-watering can', 'Never run out of water.', 2, 20, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Aquarium Hourglass', 'Dont worry about time, but feeding your new pet.', 1, 15, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Table Tennis equipment', 'There is a loophole.', 1, 8, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Couple s mugs', 'For you and your clingy partner.', 3, 30, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Double Champagne Glass', 'Double the fun on New Year s Eve.', 3, 50, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Pocket Ruler', 'To measure every tiny detail.', 2, 6, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Balloon Paint Roller', 'Mix a birthday party with home renovation.', 2, 45, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Spherical dice', 'Test your luck.', 1, 25, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Spork', 'If you can not decide.', 3, 10, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('SOUPer Bowl', 'Can not really hold liquid.', 3, 12, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Rocking ladder', 'Swing by the roof', 2, 52, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Glass', 'For fashion, not prescription.', 1, 22, 2);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Self-hammer', 'You are gonna nail the home reno.', 2, 35, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Feather Knife', 'Chicks love it.', 3, 25, 1);
INSERT INTO products (prod_name, description, category_id, unit_price, supplier_id) VALUES ('Booth trush', 'Clean them from a different angle.', 2, 9, 2);




