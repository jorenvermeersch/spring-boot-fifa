INSERT INTO users (username, password, enabled)
	VALUES ('user', '$2a$10$dQWVYE4toHnlDWATM3J2ouFZoSTBR8T/LkfVp1bp3SYid3C3oR4AS', 1),
		   ('admin', '$2a$10$KERTp6SBUIuDAYujKZr.3uhpKUlWWNuI3JD/aOa3CGCKWdNje3C2S', 1);

INSERT INTO authorities (username, authority)
	VALUES ('user', 'ROLE_USER'),
		   ('admin', 'ROLE_USER'),
		   ('admin', 'ROLE_ADMIN');