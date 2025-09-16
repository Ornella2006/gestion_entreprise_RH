-- ==========================================
-- PostgreSQL Script: HR Management Database (All Tables, English)
-- ==========================================
CREATE DATABASE gde;
\c gde;
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
-- Table Recruitment_Request
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
-- Table Assessment_Answers
-- ----------------------
CREATE TABLE Assessment_Answer (
    idAssessment_Answer SERIAL PRIMARY KEY,
    answer_text TEXT,
    idAssessment_Question INT REFERENCES Assessment_Question(idAssessment_Question),
    is_true BOOLEAN,
    score INT
);

-- ----------------------
-- Table Candidate_Answers_Test
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
