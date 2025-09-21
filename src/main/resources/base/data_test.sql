-- Niveaux d'éducation
INSERT INTO Education_Level (level_name) VALUES 
('Baccalauréat'),
('Bac+2 (BTS, DUT)'),
('Bac+3 (Licence)'),
('Bac+4 (Master 1)'),
('Bac+5 (Master 2)'),
('Doctorat');

-- Diplômes
INSERT INTO Degree (degree_name) VALUES 
('Baccalauréat'),
('BTS'),
('DUT'),
('Licence'),
('Master'),
('Doctorat'),
('Autre');

-- Compétences
INSERT INTO Skill (skill_name) VALUES 
('Java'),
('Python'),
('JavaScript'),
('HTML/CSS'),
('SQL'),
('Spring Boot'),
('React'),
('Angular'),
('Gestion de projet'),
('Communication');

-- Langues
INSERT INTO Language (language_name) VALUES 
('Français'),
('Anglais'),
('Espagnol'),
('Allemand'),
('Italien'),
('Chinois'),
('Arabe');

-- Niveaux
INSERT INTO Level (level_name, score_level) VALUES 
('Débutant', 1),
('Intermédiaire', 2),
('Avancé', 3),
('Expert', 4);