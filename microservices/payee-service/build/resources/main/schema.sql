CREATE TABLE IF NOT EXISTS payee  (
    id SERIAL PRIMARY KEY,
    name TEXT ,
    state TEXT ,
    city TEXT ,
    status TEXT,
    address TEXT,
    charge_surcharge boolean,
    country TEXT,
    indian_citizen boolean,
    is_employee boolean,
    pan_number TEXT,
    pin_code TEXT,
    organization TEXT
);

--ALTER TABLE IF EXISTS sc_auth.payee
--   OWNER to postgres;

-- Table: sc_auth.payee

-- DROP TABLE IF EXISTS sc_auth.payee;

--CREATE TABLE IF NOT EXISTS sc_auth.payee
--(
--    id integer NOT NULL DEFAULT payee('sc_auth.payee_id_seq'::regclass),
--    name text COLLATE pg_catalog."default" NOT NULL,
--    state text COLLATE pg_catalog."default" NOT NULL,
--    city text COLLATE pg_catalog."default" NOT NULL,
--    status text COLLATE pg_catalog."default" NOT NULL,
--    CONSTRAINT payee_pkey PRIMARY KEY (id)
--)
--
--TABLESPACE pg_default;
--
--ALTER TABLE IF EXISTS sc_auth.payee
--    OWNER to postgres;


--CREATE TABLE IF NOT EXISTS sc_auth.payee
--(
--     id SERIAL PRIMARY KEY,--id bigint NOT NULL,
--    address TEXT --character varying(255) COLLATE pg_catalog."default",
--    charge_surcharge boolean,
--    city TEXT,--character varying(255) COLLATE pg_catalog."default",
--    country TEXT, --character varying(255) COLLATE pg_catalog."default",
--    indian_citizen boolean,
--    is_employee boolean,
--    name TEXT, --character varying(255) COLLATE pg_catalog."default",
--    organization TEXT,--character varying(255) COLLATE pg_catalog."default",
--   -- pan_available bytea,
--    pan_number TEXT,--character varying(255) COLLATE pg_catalog."default",
--    pin_code TEXT,--character varying(255) COLLATE pg_catalog."default",
--   -- residential_status bytea,
--    state TEXT,--character varying(255) COLLATE pg_catalog."default",
--    status TEXT,-- character varying(255) COLLATE pg_catalog."default",
--   -- title bytea,
--    CONSTRAINT payee_pkey PRIMARY KEY (id)
--)
--
----TABLESPACE pg_default;
--
--ALTER TABLE IF EXISTS sc_auth.payee
--    OWNER to postgres;