DROP TABLE IF EXISTS public.products;
DROP TABLE IF EXISTS public.product_categories;
DROP TABLE IF EXISTS public.suppliers;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.order_items;
DROP TABLE IF EXISTS public.order_items;

CREATE TABLE public.products (
                             id serial NOT NULL PRIMARY KEY,
                             name text NOT NULL,
                             category_id int NOT NULL,
                             unit_price int NOT NULL,
                             supplier_id int NOT NULL
);


CREATE TABLE public.product_categories (
                                  id serial NOT NULL PRIMARY KEY,
                                  name text NOT NULL
);


CREATE TABLE public.suppliers (
                               id serial NOT NULL PRIMARY KEY,
                               name text NOT NULL
);

DROP TABLE IF EXISTS public.orders;
CREATE TABLE public.orders (
                              id serial NOT NULL PRIMARY KEY,
                              user_id int NOT NULL,
                              total_price int NOT NULL,
                              timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE public.users (
                            id serial NOT NULL PRIMARY KEY ,
                            name text NOT NULL,
                            address text NOT NULL ,
                            email text NOT NULL ,
                            password text NOT NULL
);


CREATE TABLE public.order_items (
                                product_id    int NOT NULL,
                                amount        int NOT NULL,
                                order_id      int NOT NULL,
                                subtotal_rice int NOT NULL
);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.product_categories(id) on DELETE CASCADE,
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.suppliers(id) on DELETE CASCADE;

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id) on DELETE CASCADE;

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES public.products(id) on DELETE CASCADE,
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public.orders(id) on DELETE CASCADE;

