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

--
-- Name: candidate_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE candidate_id_seq
    START WITH 200
    INCREMENT BY 5
    NO MAXVALUE
    NO MINVALUE
    CACHE 25;


ALTER TABLE public.candidate_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: candidates; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE candidates (
    candidate_id bigint NOT NULL,
    name character varying(512) NOT NULL,
    email character varying(512),
    creation_date timestamp without time zone DEFAULT now() NOT NULL,
    record_version_number bigint NOT NULL,
    CONSTRAINT jobs_ck_name CHECK ((length((name)::text) > 0))
);


ALTER TABLE public.candidates OWNER TO postgres;

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
-- Name: jobs_skills; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE jobs_skills (
    job_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.jobs_skills OWNER TO postgres;

--
-- Name: skill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE skill_id_seq
    START WITH 100
    INCREMENT BY 5
    NO MAXVALUE
    NO MINVALUE
    CACHE 25;


ALTER TABLE public.skill_id_seq OWNER TO postgres;

--
-- Name: skills; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE skills (
    skill_id bigint NOT NULL,
    title character varying(512) NOT NULL,
    description text,
    creation_date timestamp without time zone NOT NULL,
    record_version_number bigint NOT NULL,
    CONSTRAINT skills_ck_title_not_empty CHECK ((length((title)::text) > 0))
);


ALTER TABLE public.skills OWNER TO postgres;

--
-- Name: candidates_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY candidates
    ADD CONSTRAINT candidates_pk PRIMARY KEY (candidate_id);


--
-- Name: jobs_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jobs
    ADD CONSTRAINT jobs_pk PRIMARY KEY (job_id);


--
-- Name: jobs_skills_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jobs_skills
    ADD CONSTRAINT jobs_skills_pk PRIMARY KEY (job_id, skill_id);


--
-- Name: skills_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY skills
    ADD CONSTRAINT skills_pk PRIMARY KEY (skill_id);


--
-- Name: skills_uniq_title; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY skills
    ADD CONSTRAINT skills_uniq_title UNIQUE (title);


--
-- Name: fki_jobs_skills_fk_skills; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_jobs_skills_fk_skills ON jobs_skills USING btree (skill_id);


--
-- Name: idx_jobs_creation_date; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_jobs_creation_date ON jobs USING btree (creation_date);


--
-- Name: idx_skills_title_case_insensitive_uniq; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX idx_skills_title_case_insensitive_uniq ON skills USING btree (lower((title)::text));


--
-- Name: jobs_skills_fk_jobs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jobs_skills
    ADD CONSTRAINT jobs_skills_fk_jobs FOREIGN KEY (job_id) REFERENCES jobs(job_id);


--
-- Name: jobs_skills_fk_skills; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jobs_skills
    ADD CONSTRAINT jobs_skills_fk_skills FOREIGN KEY (skill_id) REFERENCES skills(skill_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT USAGE ON SCHEMA public TO app;


--
-- Name: candidate_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE candidate_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE candidate_id_seq FROM postgres;
GRANT ALL ON SEQUENCE candidate_id_seq TO postgres;
GRANT ALL ON SEQUENCE candidate_id_seq TO app;


--
-- Name: candidates; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE candidates FROM PUBLIC;
REVOKE ALL ON TABLE candidates FROM postgres;
GRANT ALL ON TABLE candidates TO postgres;
GRANT SELECT,INSERT,UPDATE ON TABLE candidates TO app;


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
-- Name: jobs_skills; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE jobs_skills FROM PUBLIC;
REVOKE ALL ON TABLE jobs_skills FROM postgres;
GRANT ALL ON TABLE jobs_skills TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE jobs_skills TO app;


--
-- Name: skill_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE skill_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE skill_id_seq FROM postgres;
GRANT ALL ON SEQUENCE skill_id_seq TO postgres;
GRANT ALL ON SEQUENCE skill_id_seq TO app;


--
-- Name: skills; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE skills FROM PUBLIC;
REVOKE ALL ON TABLE skills FROM postgres;
GRANT ALL ON TABLE skills TO postgres;
GRANT SELECT,INSERT,UPDATE ON TABLE skills TO app;


--
-- PostgreSQL database dump complete
--

