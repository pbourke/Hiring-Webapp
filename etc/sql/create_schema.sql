-- Setup script for postgres database

CREATE SCHEMA public
  AUTHORIZATION postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT USAGE ON SCHEMA public TO app;
COMMENT ON SCHEMA public IS 'standard public schema';


CREATE DATABASE hiring_online
  WITH ENCODING='UTF8'
       OWNER=pbourke
       TEMPLATE=template0
       CONNECTION LIMIT=-1;


CREATE TABLE jobs
(
  job_id bigint NOT NULL,
  title character varying(512) NOT NULL,
  description text,
  CONSTRAINT jobs_pk PRIMARY KEY (job_id),
  CONSTRAINT jobs_ck_title CHECK (length(title::text) > 0)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE jobs OWNER TO postgres;
GRANT ALL ON TABLE jobs TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE jobs TO app;


CREATE SEQUENCE job_id_seq
  INCREMENT 5
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 100
  CACHE 25;
ALTER TABLE job_id_seq OWNER TO postgres;
GRANT ALL ON TABLE job_id_seq TO postgres;
GRANT ALL ON TABLE job_id_seq TO app;
