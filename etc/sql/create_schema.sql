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
  creation_date timestamp without time zone NOT NULL,
  record_version_number bigint NOT NULL,
  CONSTRAINT jobs_pk PRIMARY KEY (job_id),
  CONSTRAINT jobs_ck_title CHECK (length(title::text) > 0)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE jobs OWNER TO postgres;
GRANT ALL ON TABLE jobs TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE jobs TO app;


CREATE INDEX idx_jobs_creation_date
  ON jobs
  USING btree
  (creation_date);


CREATE SEQUENCE job_id_seq
  INCREMENT 5
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 100
  CACHE 25;
ALTER TABLE job_id_seq OWNER TO postgres;
GRANT ALL ON TABLE job_id_seq TO postgres;
GRANT ALL ON TABLE job_id_seq TO app;



CREATE TABLE competencies
(
  competency_id bigint NOT NULL,
  title character varying(512) NOT NULL,
  description text,
  creation_date timestamp without time zone NOT NULL,
  record_version_number bigint NOT NULL,
  CONSTRAINT competencies_pk PRIMARY KEY (competency_id),
  CONSTRAINT competencies_uniq_title UNIQUE (title),
  CONSTRAINT competencies_ck_title_not_empty CHECK (length(title::text) > 0)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE competencies OWNER TO postgres;
GRANT ALL ON TABLE competencies TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE competencies TO app;

-- Index: idx_competencies_title

-- DROP INDEX idx_competencies_title;

CREATE INDEX idx_competencies_title
  ON competencies
  USING btree
  (title);




CREATE SEQUENCE competency_id_seq
  INCREMENT 5
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 100
  CACHE 25;
ALTER TABLE competency_id_seq OWNER TO postgres;
GRANT ALL ON TABLE competency_id_seq TO postgres;
GRANT ALL ON TABLE competency_id_seq TO app;


