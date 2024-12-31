CREATE TABLE IF NOT EXISTS public.portfolio
(
    id integer NOT NULL  GENERATED ALWAYS AS IDENTITY ,
    user_id integer NOT NULL,
    available_funds numeric(15,2) NOT NULL DEFAULT 0.00,
    last_updated timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT portfolio_pkey1 PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.stock
(
    id integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
    portfolio_id integer NOT NULL,
    symbol character varying(10) COLLATE pg_catalog."default" NOT NULL,
    quantity integer NOT NULL,
    price numeric(10,2) NOT NULL,
    last_updated timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    quantity_lock bigint NOT NULL DEFAULT 0,
    CONSTRAINT portfolio_pkey PRIMARY KEY (id),
    CONSTRAINT portfolio_fkey FOREIGN KEY (portfolio_id)
        REFERENCES public.portfolio (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT portfolio_quantity_check CHECK (quantity >= 0),
    CONSTRAINT portfolio_average_price_check CHECK (price >= 0::numeric)
);
