-- data.sql (insère des données de test pour éviter les exceptions "non trouvé")
INSERT INTO education_level (ideducation_level, level_name) VALUES 
(1, 'Baccalauréat'), 
(2, 'Bac+2 (BTS, DUT)'), 
(3, 'Bac+3 (Licence)'), 
(4, 'Bac+4 (Master 1)'), 
(5, 'Bac+5 (Master 2)'), 
(6, 'Doctorat');

INSERT INTO degree (iddegree, degree_name) VALUES 
(1, 'Baccalauréat'), 
(2, 'BTS'), 
(3, 'DUT'), 
(4, 'Licence'), 
(5, 'Master'), 
(6, 'Doctorat'), 
(7, 'Autre');

INSERT INTO skill (idskill, skill_name) VALUES 
(1, 'Java'), 
(2, 'Python'), 
(3, 'JavaScript'), 
(4, 'HTML/CSS'), 
(5, 'SQL'), 
(6, 'Spring Boot'), 
(7, 'React'), 
(8, 'Angular'), 
(9, 'Gestion de projet'), 
(10, 'Communication');

INSERT INTO language (idlanguage, language_name) VALUES 
(1, 'Français'), 
(2, 'Anglais'), 
(3, 'Espagnol'), 
(4, 'Allemand'), 
(5, 'Italien'), 
(6, 'Chinois'), 
(7, 'Arabe');

INSERT INTO level (idlevel, level_name, score_level) VALUES 
(1, 'Débutant', 1), 
(2, 'Intermédiaire', 2), 
(3, 'Avancé', 3), 
(4, 'Expert', 4);

insert into job_position (title) values ('dev');

insert into department(name_department) values ('informatique');
-- Ajoutez un Recruitment_Request de test pour le triage (id=1, pour un poste dev Java avec critères)
INSERT INTO recruitment_request (idrecruitment_request, iduser, idjob_position, iddepartment, need_type, justification, main_mission, contract_type, working_time, city, salary_min, salary_max, status) VALUES 
(1, 1, 1, 1, 'creation', 'Besoin d''un dev', 'Développer apps', 'CDI', '35h', 'Paris', 30000, 50000, 'pending');  -- Assumer iduser/position/dept existent, sinon ajoutez-les aussi

-- Critères pour ce request (ex: education >=3, skill Java, anglais >=2, etc.)
INSERT INTO recruitment_criteria_priority (idcriteria_priority, idrecruitment_request, criteria_type, criteria_value, operator, is_mandatory) VALUES 
(1, 1, 'education_level', '3', '>=', TRUE), 
(2, 1, 'skill', '1', '=', TRUE),  -- 1=Java
(3, 1, 'language', '2', '=', TRUE),  -- 2=Anglais
(4, 1, 'min_age', '25', '>=', TRUE);  -- Exemple âge min


-- Vérifier et insérer seulement si IDs libres
DO $$
BEGIN
    -- Role
    IF NOT EXISTS (SELECT 1 FROM role WHERE idrole = 1) THEN
        INSERT INTO role (idrole, role_name) VALUES (1, 'Admin');
    END IF;

    -- Person
    IF NOT EXISTS (SELECT 1 FROM person WHERE idperson = 1) THEN
        INSERT INTO person (idperson, first_name, last_name, email) VALUES (1, 'Admin', 'User', 'admin@email.com');
    END IF;

    -- Employee
    IF NOT EXISTS (SELECT 1 FROM employee WHERE idemployee = 1) THEN
        INSERT INTO employee (idemployee, idperson, idrole, probation_status) VALUES (1, 1, 1, 'approved');
    END IF;

    -- User
    IF NOT EXISTS (SELECT 1 FROM "user" WHERE iduser = 1) THEN
        INSERT INTO "user" (iduser, idemployee, username, password) VALUES (1, 1, 'admin', 'password');
    END IF;

    -- Department
    IF NOT EXISTS (SELECT 1 FROM department WHERE iddepartment = 1) THEN
        INSERT INTO department (iddepartment, name_department) VALUES (1, 'IT');
    END IF;

    -- Job Position
    IF NOT EXISTS (SELECT 1 FROM job_position WHERE idjob_position = 1) THEN
        INSERT INTO job_position (idjob_position, title) VALUES (1, 'Developer');
    END IF;

    -- Recruitment Request
    IF NOT EXISTS (SELECT 1 FROM recruitment_request WHERE idrecruitment_request = 1) THEN
        INSERT INTO recruitment_request (idrecruitment_request, iduser, idjob_position, iddepartment, need_type, justification, main_mission, contract_type, working_time, city, salary_min, salary_max, status)
        VALUES (1, 1, 1, 1, 'creation', 'Besoin d''un dev', 'Développer apps', 'CDI', '35h', 'Paris', 30000, 50000, 'pending');
    END IF;

    -- Criteria Mapping
    IF NOT EXISTS (SELECT 1 FROM criteria_mapping WHERE criteria_type = 'education_level') THEN
        INSERT INTO criteria_mapping (criteria_type, table_name, column_name, data_type, default_operator) VALUES
        ('education_level', 'Candidate', 'idEducation_Level', 'INT', '>='),
        ('skill', 'Candidate_Skill', 'idSkill', 'INT', '='),
        ('language', 'Candidate_Language', 'idLanguage', 'INT', '='),
        ('min_age', 'Person', 'birth_date', 'DATE', '>=');
    END IF;

    -- Recruitment Criteria Priority
    IF NOT EXISTS (SELECT 1 FROM recruitment_criteria_priority WHERE idcriteria_priority = 1) THEN
        INSERT INTO recruitment_criteria_priority (idcriteria_priority, idrecruitment_request, criteria_type, criteria_value, operator, is_mandatory) VALUES
        (1, 1, 'education_level', '3', '>=', TRUE),
        (2, 1, 'skill', '1', '=', TRUE),
        (3, 1, 'language', '2', '=', TRUE),
        (4, 1, 'min_age', '25', '>=', TRUE);
    END IF;
END $$;

-- Test la fonction
SELECT * FROM get_matching_candidates_dynamic(1);