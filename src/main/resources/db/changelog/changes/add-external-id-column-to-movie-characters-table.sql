--liquibase formatted sql
--changeset <postgres>:<add-external-id-column-to-movie-characters-table>

ALTER TABLE public.movie_characters ADD external_id bigint;

--rollback ALTER TABLE DROP COLUMN external_id;