-- ==========================================
-- PostgreSQL Script: Simplified HR Management Database
-- ==========================================
CREATE DATABASE gestion_entreprise;
\c gestion_entreprise;
-- ----------------------
-- Table Status (Use ENUM for simplicity)
-- ----------------------
CREATE TYPE status_type AS ENUM ('pending', 'approved', 'rejected', 'modify', 'in_progress', 'passed', 'failed', 'published', 'closed');
CREATE TABLE Status (
    idStatus SERIAL PRIMARY KEY,
    name_status status_type NOT NULL
);

-- ----------------------
-- Table Person
-- ----------------------
CREATE TYPE marital_status_type AS ENUM ('single', 'married', 'divorced', 'widowed');
CREATE TABLE Person (
    idPerson SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    phone VARCHAR(50),
    city VARCHAR(100),
    linkedin VARCHAR(255),
    gender VARCHAR(10),
    email VARCHAR(100) UNIQUE,
    driver_license BOOLEAN DEFAULT FALSE,
    marital_status marital_status_type
);

-- ----------------------
-- Table Role
-- ----------------------
CREATE TABLE Role (
    idRole SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,  -- Ex. Manager, RH, Finance
    description TEXT
);

-- ----------------------
-- Table Employee
-- ----------------------
CREATE TABLE Employee (
    idEmployee SERIAL PRIMARY KEY,
    idPerson INT REFERENCES Person(idPerson),
    idRole INT REFERENCES Role(idRole),
    hire_date DATE,
    start_probation_date DATE,
    end_probation_date DATE,
    probation_status status_type  -- Simplified: Use ENUM directly
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
-- Table Department
-- ----------------------
CREATE TABLE Department (
    idDepartment SERIAL PRIMARY KEY,
    name_department VARCHAR(100) NOT NULL
);

-- ----------------------
-- Table Job_Position
-- ----------------------
CREATE TABLE Job_Position (
    idJob_Position SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL  -- Simplified: Replaces Profile
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
-- Table Language
-- ----------------------
CREATE TABLE Language (
    idLanguage SERIAL PRIMARY KEY,
    language_name VARCHAR(100) NOT NULL
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
-- Table Candidate
-- ----------------------
CREATE TYPE candidate_status_type AS ENUM (
    'employed',
    'seeking',
    'student',
    'freelancer',
    'other'
);

CREATE TABLE Candidate (
    idCandidate SERIAL PRIMARY KEY,
    idPerson INT REFERENCES Person(idPerson),
    current_status candidate_status_type,  -- Uses ENUM for selection
    idEducation_Level INT REFERENCES Education_Level(idEducation_Level),
    idLast_Degree INT REFERENCES Degree(idDegree),
    expected_salary_min NUMERIC,
    expected_salary_max NUMERIC,
    additional_info TEXT
);

-- ----------------------
-- Table Candidate_Skill (New: Many-to-many for skills)
-- ----------------------
CREATE TABLE Candidate_Skill (
    idCandidate_Skill SERIAL PRIMARY KEY,
    idCandidate INT REFERENCES Candidate(idCandidate),
    idSkill INT REFERENCES Skill(idSkill),
    idLevel INT REFERENCES Level(idLevel)
);

-- ----------------------
-- Table Candidate_Language (New: Many-to-many for languages)
-- ----------------------
CREATE TABLE Candidate_Language (
    idCandidate_Language SERIAL PRIMARY KEY,
    idCandidate INT REFERENCES Candidate(idCandidate),
    idLanguage INT REFERENCES Language(idLanguage),
    idLevel INT REFERENCES Level(idLevel)
);

-- ----------------------
-- Table Experience
-- ----------------------
CREATE TABLE Experience (
    idExperience SERIAL PRIMARY KEY,
    idCandidate INT REFERENCES Candidate(idCandidate),
    company_name VARCHAR(100),
    position VARCHAR(100),
    start_date DATE,
    end_date DATE,
    description TEXT
);

-- ----------------------
-- Table Recruitment_Request
-- ----------------------
CREATE TABLE Recruitment_Request (
    idRecruitment_Request SERIAL PRIMARY KEY,
    idUser INT REFERENCES "User"(idUser),
    idJob_Position INT REFERENCES Job_Position(idJob_Position),
    idDepartment INT REFERENCES Department(idDepartment),
    need_type VARCHAR(50),  -- Simplified: Ex. creation, remplacement, cdd, stage
    justification TEXT,
    main_mission TEXT,
    contract_type VARCHAR(50),  -- Simplified: Ex. CDI, CDD, stage
    working_time VARCHAR(50),
    city VARCHAR(100),
    salary_min NUMERIC,  -- Numeric for comparisons
    salary_max NUMERIC,
    status status_type
);

-- ----------------------
-- Table Criteria_Mapping
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
-- Table Recruitment_Criteria_Priority
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
-- Table Job_Announcement
-- ----------------------
CREATE TABLE Job_Announcement (
    idJob_Announcement SERIAL PRIMARY KEY,
    idRecruitment_Request INT REFERENCES Recruitment_Request(idRecruitment_Request),
    description TEXT,  -- Added: For recruiter to embellish announcement
    publication_date DATE,
    closing_date DATE,
    status status_type,
    preview_link VARCHAR(255)  -- Added: For pre-visualization
);

-- ----------------------
-- Table Validation
-- ----------------------
CREATE TABLE Validation (
    idValidation SERIAL PRIMARY KEY,
    idRecruitment_Request INT REFERENCES Recruitment_Request(idRecruitment_Request),
    idJob_Announcement INT REFERENCES Job_Announcement(idJob_Announcement),  -- NULL for request validation
    idUser INT REFERENCES "User"(idUser),
    decision status_type,  -- Ex. approved, rejected, modify
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
    status status_type,
    application_date DATE
);

-- ----------------------
-- Table Test
-- ----------------------
CREATE TYPE test_type_enum AS ENUM (
    'logic',
    'technical',
    'psychometric',
    'language',
    'other'
);

CREATE TABLE Test (
    idTest SERIAL PRIMARY KEY,
    test_type test_type_enum,  -- Uses ENUM for selection
    max_score INT,
    test_date DATE
);

-- ----------------------
-- Table Test_Question
-- ----------------------
CREATE TABLE Test_Question (
    idTest_Question SERIAL PRIMARY KEY,
    idTest INT REFERENCES Test(idTest),
    question_text TEXT
);

-- ----------------------
-- Table Test_Answer
-- ----------------------
CREATE TABLE Test_Answer (
    idTest_Answer SERIAL PRIMARY KEY,
    idTest_Question INT REFERENCES Test_Question(idTest_Question),
    answer_text TEXT,
    is_correct BOOLEAN,
    score INT
);

-- ----------------------
-- Table Candidate_Test
-- ----------------------
CREATE TABLE Candidate_Test (
    idCandidate_Test SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    idTest_Question INT REFERENCES Test_Question(idTest_Question),
    idTest_Answer INT REFERENCES Test_Answer(idTest_Answer),
    candidate_score INT
);


CREATE TABLE Qualitative_Score (
    qualitative_score VARCHAR(20) PRIMARY KEY,
    numeric_score INT NOT NULL
);

-- ----------------------
-- Table Interview
-- ----------------------
CREATE TABLE Interview (
    idInterview SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    interview_type VARCHAR(50),
    interview_date DATE,
    interview_time TIME,
    qualitative_score VARCHAR(20)
    -- On ne stocke plus numeric_score ici
);

-- ----------------------
-- Table Final_Score
-- ----------------------
CREATE TABLE Final_Score (
    idFinal_Score SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    test_score INT,
    interview_score INT,
    final_score Numeric(5,2)
);

-- ----------------------
-- Table Final_Selection
-- ----------------------
CREATE TABLE Final_Selection (
    idFinal_Selection SERIAL PRIMARY KEY,
    idJob_Application INT REFERENCES Job_Application(idJob_Application),
    decision_date DATE,
    notification_sent BOOLEAN DEFAULT FALSE
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
        IF rec.data_type = 'DATE' AND rec.criteria_type = 'min_age' THEN
            condition_text := condition_text || 'DATE_PART(''year'', AGE(CURRENT_DATE, p.' || quote_ident(rec.column_name) || ')) ' || rec.operator || ' ' || quote_literal(rec.criteria_value)::TEXT;
        ELSE
            condition_text := condition_text || 
                CASE rec.table_name
                    WHEN 'Person' THEN 'p.' || quote_ident(rec.column_name)
                    WHEN 'Candidate' THEN 'c.' || quote_ident(rec.column_name)
                    WHEN 'Candidate_Skill' THEN 'cs.' || quote_ident(rec.column_name)
                    WHEN 'Candidate_Language' THEN 'cl.' || quote_ident(rec.column_name)
                    ELSE 'FALSE'
                END || ' ' || rec.operator || ' ' ||
                CASE rec.data_type
                    WHEN 'INT' THEN quote_literal(rec.criteria_value)::TEXT
                    WHEN 'NUMERIC' THEN quote_literal(rec.criteria_value)::TEXT
                    WHEN 'TEXT' THEN quote_literal(rec.criteria_value)
                    WHEN 'BOOLEAN' THEN quote_literal(rec.criteria_value)::TEXT
                    ELSE quote_literal(rec.criteria_value)
                END;
        END IF;
    END LOOP;
    IF condition_text = '' THEN
        condition_text := 'TRUE';
    END IF;
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
        LEFT JOIN Candidate_Skill cs ON c.idCandidate = cs.idCandidate
        LEFT JOIN Candidate_Language cl ON c.idCandidate = cl.idCandidate
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
    ('skill', 'Candidate_Skill', 'idSkill', 'INT', '='),
    ('language', 'Candidate_Language', 'idLanguage', 'INT', '='),
    ('driver_license', 'Person', 'driver_license', 'BOOLEAN', '='),
    ('salary_min', 'Candidate', 'expected_salary_min', 'NUMERIC', '>='),
    ('salary_max', 'Candidate', 'expected_salary_max', 'NUMERIC', '<=');