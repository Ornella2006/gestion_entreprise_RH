INSERT INTO Status (name_status) VALUES
('approved'),
('pending');
INSERT INTO Role (role_name, description) VALUES
('Admin', 'Administrateur système'),
('RH', 'Ressources Humaines'),
('Manager', 'Gestionnaire d équipe');
INSERT INTO Person (first_name, last_name, birth_date, phone, city, linkedin, gender, email, driver_license, marital_status)
VALUES
('Alice', 'Randria', '2000-05-12', '+261320000001', 'Antananarivo', 'https://linkedin.com/in/alice', 'F', 'alice@example.com', TRUE, 'single'),
('Bob', 'Rakoto', '1998-09-30', '+261320000002', 'Antsirabe', NULL, 'M', 'bob@example.com', FALSE, 'single');
INSERT INTO Employee (idPerson, idRole, hire_date, start_probation_date, end_probation_date, probation_status)
VALUES
(1, 1, '2024-01-10', '2024-01-10', '2024-07-10', 'approved'),
(2, 2, '2023-03-05', '2023-03-05', '2023-09-05', 'approved');
INSERT INTO "User" (idEmployee, username, password)
VALUES
(1, 'alice_admin', 'admin123'),
(2, 'bob_rh', 'rh123');
