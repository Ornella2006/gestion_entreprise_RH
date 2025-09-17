-- ==========================================
-- PostgreSQL Script: HR Management Database (Updated with Dynamic Criteria)
-- ==========================================
CREATE DATABASE gestion_entreprise;
\c gestion_entreprise;
-- ----------------------
-- Table Status
-- ----------------------
CREATE TABLE Status (
    idStatus SERIAL PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL
);

-- ----------------------
-- Table Decision_Type
-- ----------------------
CREATE TABLE Decision_Type (
    idDecision SERIAL PRIMARY KEY,
    name_decision VARCHAR(50) NOT NULL -- approved, rejected, modify
);

-- ----------------------
-- Table Probation_Status
-- ----------------------
CREATE TABLE Probation_Status (
    idProbation_Status SERIAL PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL -- in_progress, passed, failed
);

-- ----------------------
-- Table Situation
-- ----------------------
CREATE TABLE Situation (
    idSituation SERIAL PRIMARY KEY,
    name_situation VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Person
-- ----------------------
CREATE TABLE Person (
    idPerson SERIAL PRIMARY KEY,
    idSituation INT REFERENCES Situation(idSituation),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    phone VARCHAR(50),
    city VARCHAR(100),
    linkedin VARCHAR(255),
    gender VARCHAR(10),
    email VARCHAR(100) UNIQUE,
    driver_license BOOLEAN DEFAULT FALSE
);

-- ----------------------
-- Table Actor
-- ----------------------
CREATE TABLE Actor (
    idActor SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- ----------------------
-- Table Employee
-- ----------------------
CREATE TABLE Employee (
    idEmployee SERIAL PRIMARY KEY,
    idCandidate INT REFERENCES Candidate(idCandidate),
    idActor INT REFERENCES Actor(idActor),
    hire_date DATE,
    start_probation_date DATE,
    end_probation_date DATE,
    idProbation_Status INT REFERENCES Probation_Status(idProbation_Status)
);

-- ----------------------
-- Table User
-- ----------------------
CREATE TABLE "User" (
    idUser SERIAL PRIMARY KEY,
    idEmployee INT REFERENCES Employee(idEmployee),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- ----------------------
-- Table City
-- ----------------------
CREATE TABLE City (
    idCity SERIAL PRIMARY KEY,
    name_city VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Need_Type
-- ----------------------
CREATE TABLE Need_Type (
    idNeed_Type SERIAL PRIMARY KEY,
    name_need VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Contract_Type
-- ----------------------
CREATE TABLE Contract_Type (
    idContract_Type SERIAL PRIMARY KEY,
    name_contract VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Department
-- ----------------------
CREATE TABLE Department (
    idDepartment SERIAL PRIMARY KEY,
    name_department VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Profile
-- ----------------------
CREATE TABLE Profile (
    idProfile SERIAL PRIMARY KEY,
    name_profile VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Education_Level
-- ----------------------
CREATE TABLE Education_Level (
    idEducation_Level SERIAL PRIMARY KEY,
    level_name VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Degree
-- ----------------------
CREATE TABLE Degree (
    idDegree SERIAL PRIMARY KEY,
    degree_name VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Skill
-- ----------------------
CREATE TABLE Skill (
    idSkill SERIAL PRIMARY KEY,
    skill_name VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Level
-- ----------------------
CREATE TABLE Level (
    idLevel SERIAL PRIMARY KEY,
    level_name VARCHAR(100) NOT NULL,
    score_level INT
);

-- ----------------------
-- Table Skill_Level
-- ----------------------
CREATE TABLE Skill_Level (
    idSkill_Level SERIAL PRIMARY KEY,
    idSkill INT REFERENCES Skill(idSkill),
    idLevel INT REFERENCES Level(idLevel)
);

-- ----------------------
-- Table Language
-- ----------------------
CREATE TABLE Language (
    idLanguage SERIAL PRIMARY KEY,
    language_name VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Language_Level
-- ----------------------
CREATE TABLE Language_Level (
    idLanguage_Level SERIAL PRIMARY KEY,
    idLanguage INT REFERENCES Language(idLanguage),
    idLevel INT REFERENCES Level(idLevel)
);

-- ----------------------
-- Table Formation_Experience
-- ----------------------
CREATE TABLE Formation_Experience (
    idFormation_Experience SERIAL PRIMARY KEY,
    company_name VARCHAR(100),
    start_date DATE,
    end_date DATE,
    is_experience BOOLEAN DEFAULT TRUE
);

-- ----------------------
-- Table Candidate
-- ----------------------
CREATE TABLE Candidate (
    idCandidate SERIAL PRIMARY KEY,
    idPerson INT REFERENCES Person(idPerson),
    current_status VARCHAR(50),
    idEducation_Level INT REFERENCES Education_Level(idEducation_Level),
    idLast_Degree INT REFERENCES Degree(idDegree),
    idSkill_Level INT REFERENCES Skill_Level(idSkill_Level),
    idLanguage_Level INT REFERENCES Language_Level(idLanguage_Level),
    expected_salary VARCHAR(50),
    additional_info TEXT
);

-- ----------------------
-- Table Recruitment_Request (Updated: Removed static criteria fields)
-- ----------------------
CREATE TABLE Recruitment_Request (
    idRecruitment_Request SERIAL PRIMARY KEY,
    idUser INT REFERENCES "User"(idUser),
    idProfile INT REFERENCES Profile(idProfile),
    idDepartment INT REFERENCES Department(idDepartment),
    idNeed_Type INT REFERENCES Need_Type(idNeed_Type),
    justification TEXT,
    main_mission TEXT,
    idFormation_Experience INT REFERENCES Formation_Experience(idFormation_Experience),
    idSkill_Level INT REFERENCES Skill_Level(idSkill_Level),
    idLanguage_Level INT REFERENCES Language_Level(idLanguage_Level),
    idContract_Type INT REFERENCES Contract_Type(idContract_Type),
    working_time VARCHAR(50),
    idCity INT REFERENCES City(idCity),
    salary_range VARCHAR(50),
    idStatus INT REFERENCES Status(idStatus)
);

-- ----------------------
-- Table Criteria_Mapping (New: Maps criteria to table columns dynamically)
-- ----------------------
CREATE TABLE Criteria_Mapping (
    idCriteria_Mapping SERIAL PRIMARY KEY,
    criteria_type VARCHAR(50) UNIQUE NOT NULL,
    table_name VARCHAR(50) NOT NULL,
    column_name VARCHAR(50) NOT NULL,
    data_type VARCHAR(20) NOT NULL,
    default_operator VARCHAR(10) DEFAULT '='
);

-- ----------------------
-- Table Recruitment_Criteria_Priority (New: Stores priority criteria for each request)
-- ----------------------
CREATE TABLE Recruitment_Criteria_Priority (
    idCriteria_Priority SERIAL PRIMARY KEY,
    idRecruitment_Request INT REFERENCES Recruitment_Request(idRecruitment_Request),
    criteria_type VARCHAR(50) NOT NULL,
    criteria_value VARCHAR(255),
    operator VARCHAR(10) DEFAULT '=',
    is_mandatory BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_criteria_type FOREIGN KEY (criteria_type) REFERENCES Criteria_Mapping(criteria_type)
);

-- ----------------------
-- Table Validation_Request
-- ----------------------
CREATE TABLE Validation_Request (
    idValidation SERIAL PRIMARY KEY,
    idRecruitment_Request INT REFERENCES Recruitment_Request(idRecruitment_Request),
    idUser INT REFERENCES "User"(idUser),
    idDecision INT REFERENCES Decision_Type(idDecision),
    comment TEXT,
    validation_date TIMESTAMP DEFAULT NOW()
);

-- ----------------------
-- Table Job_Announcement
-- ----------------------
CREATE TABLE Job_Announcement (
    idJob_Announcement SERIAL PRIMARY KEY,
    idRecruitment_Request INT REFERENCES Recruitment_Request(idRecruitment_Request),
    publication_date DATE,
    closing_date DATE,
    idStatus INT REFERENCES Status(idStatus)
);

-- ----------------------
-- Table Announcement_Validation
-- ----------------------
CREATE TABLE Announcement_Validation (
    idAnnouncement_Validation SERIAL PRIMARY KEY,
    idJob_Announcement INT REFERENCES Job_Announcement(idJob_Announcement),
    idUser INT REFERENCES "User"(idUser),
    idDecision INT REFERENCES Decision_Type(idDecision),
    comment TEXT,
    validation_date TIMESTAMP DEFAULT NOW()
);

-- ----------------------
-- Table Job_Application
-- ----------------------
CREATE TABLE Job_Application (
    idJob_Application SERIAL PRIMARY KEY,
    idCandidate INT REFERENCES Candidate(idCandidate),
    idJob_Announcement INT REFERENCES Job_Announcement(idJob_Announcement),
    idStatus INT REFERENCES Status(idStatus),
    application_date DATE
);

-- ----------------------
-- Table Assessment_Settings
-- ----------------------
CREATE TABLE Assessment_Settings (
    idAssessment_Settings SERIAL PRIMARY KEY,
    max_score INT,
    assessment_date DATE
);

-- ----------------------
-- Table Assessment
-- ----------------------
CREATE TABLE Assessment (
    idAssessment SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    idAssessment_Settings INT REFERENCES Assessment_Settings(idAssessment_Settings)
);

-- ----------------------
-- Table Assessment_Question
-- ----------------------
CREATE TABLE Assessment_Question (
    idAssessment_Question SERIAL PRIMARY KEY,
    idTest_Type INT REFERENCES Test_Type(idTest_Type),
    question_text TEXT
);

-- ----------------------
-- Table Assessment_Answer
-- ----------------------
CREATE TABLE Assessment_Answer (
    idAssessment_Answer SERIAL PRIMARY KEY,
    answer_text TEXT,
    idAssessment_Question INT REFERENCES Assessment_Question(idAssessment_Question),
    is_true BOOLEAN,
    score INT
);

-- ----------------------
-- Table Candidate_Answer_Test
-- ----------------------
CREATE TABLE Candidate_Answer_Test (
    idCandidate_Answer_Test SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    idAssessment_Question INT REFERENCES Assessment_Question(idAssessment_Question),
    idAssessment_Result INT REFERENCES Assessment_Result(idAssessment_Result),
    idAnswer_Candidate INT REFERENCES Assessment_Answer(idAssessment_Answer),
    candidate_score INT
);

-- ----------------------
-- Table Assessment_Result
-- ----------------------
CREATE TABLE Assessment_Result (
    idAssessment_Result SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    result_score INT
);

-- ----------------------
-- Table Test_Type
-- ----------------------
CREATE TABLE Test_Type (
    idTest_Type SERIAL PRIMARY KEY,
    test_type_name VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Interview_Type
-- ----------------------
CREATE TABLE Interview_Type (
    idInterview_Type SERIAL PRIMARY KEY,
    interview_type_name VARCHAR(100),
    idActor INT REFERENCES Actor(idActor)
);

-- ----------------------
-- Table Interview_Settings
-- ----------------------
CREATE TABLE Interview_Settings (
    idInterview_Settings SERIAL PRIMARY KEY,
    interval_minutes INT,
    max_duration_minutes INT
);

-- ----------------------
-- Table Interview
-- ----------------------
CREATE TABLE Interview (
    idInterview SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    idInterview_Type INT REFERENCES Interview_Type(idInterview_Type)
);

-- ----------------------
-- Table Interview_Schedule
-- ----------------------
CREATE TABLE Interview_Schedule (
    idInterview_Schedule SERIAL PRIMARY KEY,
    idInterview INT REFERENCES Interview(idInterview),
    interview_date DATE,
    interview_time TIME
);

-- ----------------------
-- Table Interview_Result
-- ----------------------
CREATE TABLE Interview_Result (
    idInterview_Result SERIAL PRIMARY KEY,
    idInterview INT REFERENCES Interview(idInterview),
    idLevel INT REFERENCES Level(idLevel)
);

-- ----------------------
-- Table Final_Score
-- ----------------------
CREATE TABLE Final_Score (
    idFinal_Score SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    final_score NUMERIC(5,2)
);

-- ----------------------
-- Table Final_Selection
-- ----------------------
CREATE TABLE Final_Selection (
    idFinal_Selection SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application)
);

-- ----------------------
-- Function: get_matching_candidates_dynamic
-- ----------------------
CREATE OR REPLACE FUNCTION get_matching_candidates_dynamic(p_idRecruitment_Request INT)
RETURNS TABLE (
    idCandidate INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    gender VARCHAR(10),
    level_name VARCHAR(100)
) AS $$
DECLARE
    condition_text TEXT := '';
    rec RECORD;
BEGIN
    -- Construire la clause WHERE dynamiquement
    FOR rec IN 
        SELECT 
            rcp.criteria_type,
            rcp.criteria_value,
            COALESCE(rcp.operator, cm.default_operator) AS operator,
            cm.table_name,
            cm.column_name,
            cm.data_type
        FROM Recruitment_Criteria_Priority rcp
        JOIN Criteria_Mapping cm ON rcp.criteria_type = cm.criteria_type
        WHERE rcp.idRecruitment_Request = p_idRecruitment_Request
        AND rcp.is_mandatory = TRUE
    LOOP
        IF condition_text != '' THEN
            condition_text := condition_text || ' AND ';
        END IF;

        -- Gérer les différents types de données et opérateurs
        IF rec.data_type = 'DATE' AND rec.criteria_type = 'min_age' THEN
            condition_text := condition_text || 'DATE_PART(''year'', AGE(CURRENT_DATE, p.' || quote_ident(rec.column_name) || ')) ' || rec.operator || ' ' || quote_literal(rec.criteria_value)::TEXT;
        ELSE
            condition_text := condition_text || 
                CASE rec.table_name
                    WHEN 'Person' THEN 'p.' || quote_ident(rec.column_name)
                    WHEN 'Candidate' THEN 'c.' || quote_ident(rec.column_name)
                    ELSE 'FALSE'
                END || ' ' || rec.operator || ' ' ||
                CASE rec.data_type
                    WHEN 'INT' THEN quote_literal(rec.criteria_value)::TEXT
                    WHEN 'TEXT' THEN quote_literal(rec.criteria_value)
                    WHEN 'BOOLEAN' THEN quote_literal(rec.criteria_value)::TEXT
                    ELSE quote_literal(rec.criteria_value)
                END;
        END IF;
    END LOOP;

    -- Si aucun critère, retourner TRUE (tous les candidats)
    IF condition_text = '' THEN
        condition_text := 'TRUE';
    END IF;

    -- Exécuter la requête dynamique
    RETURN QUERY EXECUTE '
        SELECT 
            c.idCandidate,
            p.first_name,
            p.last_name,
            p.gender,
            el.level_name
        FROM Candidate c
        JOIN Person p ON c.idPerson = p.idPerson
        JOIN Education_Level el ON c.idEducation_Level = el.idEducation_Level
        WHERE ' || condition_text;
END;
$$ LANGUAGE plpgsql;

-- ----------------------
-- Initial Data for Criteria_Mapping
-- ----------------------
INSERT INTO Criteria_Mapping (criteria_type, table_name, column_name, data_type, default_operator) VALUES
    ('gender', 'Person', 'gender', 'TEXT', '='),
    ('education_level', 'Candidate', 'idEducation_Level', 'INT', '='),
    ('min_age', 'Person', 'birth_date', 'DATE', '>='),
    ('city', 'Person', 'city', 'TEXT', 'ILIKE'),
    ('skill_level', 'Candidate', 'idSkill_Level', 'INT', '='),
    ('language_level', 'Candidate', 'idLanguage_Level', 'INT', '='),
    ('driver_license', 'Person', 'driver_license', 'BOOLEAN', '='),
    ('expected_salary', 'Candidate', 'expected_salary', 'TEXT', '=');