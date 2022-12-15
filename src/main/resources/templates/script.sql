-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	id int4 NOT NULL DEFAULT nextval('user_performer_id_seq'::regclass),
	email varchar NOT NULL,
	name varchar NOT NULL,
	surname varchar NOT NULL,
	"password" varchar NOT NULL,
	iban varchar NULL,
	expiration_date varchar NULL,
	name_on_card varchar NULL,
	profile_picture bytea NULL,
	"role" varchar NULL,
	CONSTRAINT user_email_uq_perf UNIQUE (email),
	CONSTRAINT user_performer_pk PRIMARY KEY (id)
);


-- public.song definition

-- Drop table

-- DROP TABLE public.song;

CREATE TABLE public.song (
	id serial4 NOT NULL,
	song_name varchar NOT NULL,
	release_date varchar NULL,
	explicit_content bool NOT NULL,
	audio_file varchar NOT NULL,
	icon_file varchar NOT NULL,
	CONSTRAINT song_pk PRIMARY KEY (id)
);

-- public.song_category definition

-- Drop table

-- DROP TABLE public.song_category;

CREATE TABLE public.song_category (
	id serial4 NOT NULL,
	category_name varchar NOT NULL,
	CONSTRAINT song_category_pk PRIMARY KEY (id)
);


-- public.custom_playlist definition

-- Drop table

-- DROP TABLE public.custom_playlist;

CREATE TABLE public.custom_playlist (
	id serial4 NOT NULL,
	"name" varchar NOT NULL,
	user_id int4 NOT NULL,
	CONSTRAINT custom_playlist_pk PRIMARY KEY (id)
);


-- public.custom_playlist foreign keys

ALTER TABLE public.custom_playlist ADD CONSTRAINT custom_playlist_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE ON UPDATE CASCADE;


- public.liked_song definition

-- Drop table

-- DROP TABLE public.liked_song;

CREATE TABLE public.liked_song (
	id serial4 NOT NULL,
	song_id int4 NOT NULL,
	user_id int4 NOT NULL,
	CONSTRAINT liked_song_pk PRIMARY KEY (id)
);


-- public.liked_song foreign keys

ALTER TABLE public.liked_song ADD CONSTRAINT liked_song_fk FOREIGN KEY (song_id) REFERENCES public.song(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.liked_song ADD CONSTRAINT liked_song_fk_1 FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE ON UPDATE CASCADE;

-- public.playlist_join_song definition

-- Drop table

-- DROP TABLE public.playlist_join_song;

CREATE TABLE public.playlist_join_song (
	id serial4 NOT NULL,
	playlist_id int4 NOT NULL,
	song_id int4 NOT NULL,
	CONSTRAINT playlist_join_song_pk PRIMARY KEY (id)
);


-- public.playlist_join_song foreign keys

ALTER TABLE public.playlist_join_song ADD CONSTRAINT playlist_join_song_fk FOREIGN KEY (song_id) REFERENCES public.song(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.playlist_join_song ADD CONSTRAINT playlist_join_song_fk_2 FOREIGN KEY (playlist_id) REFERENCES public.custom_playlist(id) ON UPDATE CASCADE;


-- public.song_join_cat definition

-- Drop table

-- DROP TABLE public.song_join_cat;

CREATE TABLE public.song_join_cat (
	id serial4 NOT NULL,
	song_id int4 NOT NULL,
	category_id int4 NOT NULL,
	CONSTRAINT song_join_cat_pk PRIMARY KEY (id)
);


-- public.song_join_cat foreign keys

ALTER TABLE public.song_join_cat ADD CONSTRAINT join_cat_fk FOREIGN KEY (song_id) REFERENCES public.song(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.song_join_cat ADD CONSTRAINT join_song_fk FOREIGN KEY (category_id) REFERENCES public.song_category(id) ON UPDATE CASCADE;

-- public.song_join_performer definition

-- Drop table

-- DROP TABLE public.song_join_performer;

CREATE TABLE public.song_join_performer (
	id serial4 NOT NULL,
	song_id int4 NOT NULL,
	performer_id int4 NOT NULL,
	CONSTRAINT song_join_performer_pk PRIMARY KEY (id)
);


-- public.song_join_performer foreign keys

ALTER TABLE public.song_join_performer ADD CONSTRAINT song_join_performer_fk FOREIGN KEY (song_id) REFERENCES public.song(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.song_join_performer ADD CONSTRAINT song_join_performer_fk_1 FOREIGN KEY (performer_id) REFERENCES public."user"(id) ON DELETE CASCADE ON UPDATE CASCADE;