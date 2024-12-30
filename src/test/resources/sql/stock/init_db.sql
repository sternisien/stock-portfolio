CREATE TABLE IF NOT EXISTS stock
(
    id integer NOT NULL  GENERATED ALWAYS AS IDENTITY ,
    user_id integer NOT NULL,
    symbol character varying(10) COLLATE pg_catalog."default" NOT NULL,
    quantity integer NOT NULL,
    price numeric(10,2) NOT NULL,
    last_updated timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT portfolio_pkey PRIMARY KEY (id),
    CONSTRAINT portfolio_quantity_check CHECK (quantity >= 0),
    CONSTRAINT portfolio_average_price_check CHECK (price >= 0::numeric)
);