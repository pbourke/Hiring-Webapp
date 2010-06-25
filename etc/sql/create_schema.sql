--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: competencies; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE competencies (
    competency_id bigint NOT NULL,
    title character varying(512) NOT NULL,
    description text,
    creation_date timestamp without time zone NOT NULL,
    record_version_number bigint NOT NULL,
    CONSTRAINT competencies_ck_title_not_empty CHECK ((length((title)::text) > 0))
);


ALTER TABLE public.competencies OWNER TO postgres;

--
-- Name: competency_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE competency_id_seq
    START WITH 100
    INCREMENT BY 5
    NO MAXVALUE
    NO MINVALUE
    CACHE 25;


ALTER TABLE public.competency_id_seq OWNER TO postgres;

--
-- Name: job_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE job_id_seq
    START WITH 100
    INCREMENT BY 5
    NO MAXVALUE
    NO MINVALUE
    CACHE 25;


ALTER TABLE public.job_id_seq OWNER TO postgres;

--
-- Name: jobs; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE jobs (
    job_id bigint NOT NULL,
    title character varying(512) NOT NULL,
    description text,
    creation_date timestamp without time zone NOT NULL,
    record_version_number bigint NOT NULL,
    CONSTRAINT jobs_ck_title CHECK ((length((title)::text) > 0))
);


ALTER TABLE public.jobs OWNER TO postgres;

--
-- Name: competencies_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY competencies
    ADD CONSTRAINT competencies_pk PRIMARY KEY (competency_id);


--
-- Name: competencies_uniq_title; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY competencies
    ADD CONSTRAINT competencies_uniq_title UNIQUE (title);


--
-- Name: jobs_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jobs
    ADD CONSTRAINT jobs_pk PRIMARY KEY (job_id);


--
-- Name: idx_competencies_title; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_competencies_title ON competencies USING btree (title);


--
-- Name: idx_jobs_creation_date; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_jobs_creation_date ON jobs USING btree (creation_date);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT USAGE ON SCHEMA public TO app;


--
-- Name: competencies; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE competencies FROM PUBLIC;
REVOKE ALL ON TABLE competencies FROM postgres;
GRANT ALL ON TABLE competencies TO postgres;
GRANT SELECT,INSERT,UPDATE ON TABLE competencies TO app;


--
-- Name: competency_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE competency_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE competency_id_seq FROM postgres;
GRANT ALL ON SEQUENCE competency_id_seq TO postgres;
GRANT ALL ON SEQUENCE competency_id_seq TO app;


--
-- Name: job_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE job_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_id_seq FROM postgres;
GRANT ALL ON SEQUENCE job_id_seq TO postgres;
GRANT ALL ON SEQUENCE job_id_seq TO app;


--
-- Name: jobs; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE jobs FROM PUBLIC;
REVOKE ALL ON TABLE jobs FROM postgres;
GRANT ALL ON TABLE jobs TO postgres;
GRANT SELECT,INSERT,UPDATE ON TABLE jobs TO app;


--
-- PostgreSQL database dump complete
--

