-- data.sql
INSERT INTO education_level (ideducation_level, level_name) VALUES (1, 'Baccalauréat'), (2, 'Bac+2'), (3, 'Bac+3'), (4, 'Bac+4'), (5, 'Bac+5'), (6, 'Doctorat');
INSERT INTO degree (iddegree, degree_name) VALUES (1, 'Baccalauréat'), (2, 'BTS'), (3, 'DUT'), (4, 'Licence'), (5, 'Master'), (6, 'Doctorat'), (7, 'Autre');
INSERT INTO skill (idskill, skill_name) VALUES (1, 'Java'), (2, 'Python'), (3, 'JavaScript'), (4, 'HTML/CSS'), (5, 'SQL'), (6, 'Spring Boot'), (7, 'React'), (8, 'Angular'), (9, 'Gestion de projet'), (10, 'Communication');
INSERT INTO language (idlanguage, language_name) VALUES (1, 'Français'), (2, 'Anglais'), (3, 'Espagnol'), (4, 'Allemand'), (5, 'Italien'), (6, 'Chinois'), (7, 'Arabe');
INSERT INTO level (idlevel, level_name, score_level) VALUES (1, 'Débutant', 1), (2, 'Intermédiaire', 2), (3, 'Avancé', 3), (4, 'Expert', 4);