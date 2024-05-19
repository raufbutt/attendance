failed to get console mode for stdout: The handle is invalid.
--
-- PostgreSQL database dump
--

-- Dumped from database version 13.5 (Debian 13.5-1.pgdg110+1)
-- Dumped by pg_dump version 13.5 (Debian 13.5-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.activity (activity_code, admin_id, cr_reference, description, status) VALUES ('ACT01', NULL, '88a4b94f-569d-48df-95aa-9badf69cb6ef', 'Activity 1', 'ACTIVE');
INSERT INTO public.activity (activity_code, admin_id, cr_reference, description, status) VALUES ('ACT02', NULL, '01358835-8b8c-4ead-b93b-57f2f4494453', 'Activity 2', 'ACTIVE');


--
-- Data for Name: checkin; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: classroom; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.classroom (description, location, name, reference) VALUES (NULL, 'South-Floor1', 'room101', '01358835-8b8c-4ead-b93b-57f2f4494453');
INSERT INTO public.classroom (description, location, name, reference) VALUES (NULL, 'East-GroundFloor', 'room010', '88a4b94f-569d-48df-95aa-9badf69cb6ef');


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: staff; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: timeslot; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.timeslot (from_time, timeslot_id, to_time, activity_code, classroom, day_of_week) VALUES ('2024-05-18 14:00:00', 1, '2024-05-18 15:00:00', 'ACT02', '01358835-8b8c-4ead-b93b-57f2f4494453', 'Monday');
INSERT INTO public.timeslot (from_time, timeslot_id, to_time, activity_code, classroom, day_of_week) VALUES ('2024-05-18 10:45:00', 2, '2024-05-18 13:00:00', 'ACT01', '88a4b94f-569d-48df-95aa-9badf69cb6ef', 'Tuesday');


--
-- Name: checkin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.checkin_id_seq', 1, false);


--
-- Name: timeslot_timeslot_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.timeslot_timeslot_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

