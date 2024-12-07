-- Llenar tabla OFFICES
INSERT INTO `OFFICE` (`OFFICE_ID`, `OFFICE_NUMBER`, `FLOOR`)
VALUES 
(UUID(), 101, 1),
(UUID(), 102, 1),
(UUID(), 201, 2),
(UUID(), 202, 2),
(UUID(), 301, 3),
(UUID(), 302, 3);

-- Llenar tabla DOCTORS
INSERT INTO `DOCTORS` (`DOCTOR_ID`, `FIRST_NAME`, `LAST_NAME`, `MIDDLE_NAME`, `SPECIALTY`)
VALUES 
(UUID(), 'John', 'Doe', 'Smith', 'Cardiology'),
(UUID(), 'Jane', 'Brown', 'Taylor', 'Dermatology'),
(UUID(), 'Michael', 'Clark', 'Jones', 'Pediatrics'),
(UUID(), 'Emily', 'Davis', 'Johnson', 'Neurology'),
(UUID(), 'Chris', 'Wilson', 'Thomas', 'Orthopedics'),
(UUID(), 'Laura', 'Martinez', 'Gonzalez', 'Ophthalmology');
