-- setup script for initial data
-- 5f4dcc3b5aa765d61d8327deb882cf99 = 'password'
insert into users(user_id, email, password_digest, name, creation_date, record_version_number, last_updated_date) VALUES (nextval('user_id_seq'), 'pbourke@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'Patrick Bourke', now(), 0, now());
