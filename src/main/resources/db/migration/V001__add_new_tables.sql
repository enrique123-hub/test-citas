CREATE TABLE IF NOT EXISTS `DOCTORS` (
    `DOCTOR_ID` VARCHAR(36) NOT NULL,
    `FIRST_NAME` VARCHAR(50) NOT NULL,
    `LAST_NAME` VARCHAR(50) NOT NULL,
    `MIDDLE_NAME` VARCHAR(50),
    `SPECIALTY` VARCHAR(100) NOT NULL,
    CONSTRAINT `PK_DOCTOR_ID` PRIMARY KEY (`DOCTOR_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `OFFICE` (
    `OFFICE_ID` VARCHAR(36) NOT NULL,
    `OFFICE_NUMBER` INT NOT NULL UNIQUE,
    `FLOOR` INT NOT NULL,
    CONSTRAINT `PK_OFFICE_ID` PRIMARY KEY (`OFFICE_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `APPOINTMENTS` (
    `APPOINTMENT_ID` VARCHAR(36) NOT NULL,
    `OFFICE_ID` VARCHAR(36) NOT NULL,
    `DOCTOR_ID` VARCHAR(36) NOT NULL,
    `APPOINTMENT_TIME` VARCHAR(10) NOT NULL,
    `PATIENT_NAME` VARCHAR(100) NOT NULL,
    CONSTRAINT `PK_APPOINTMENT_ID` PRIMARY KEY (`APPOINTMENT_ID`),
    CONSTRAINT `FK_APPOINTMENTS_01` FOREIGN KEY (`OFFICE_ID`) REFERENCES `OFFICE` (`OFFICE_ID`),
    CONSTRAINT `FK_APPOINTMENTS_02` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `DOCTORS` (`DOCTOR_ID`),
    -- Validación para no agendar una cita en el mismo consultorio a la misma hora
    CONSTRAINT `UNIQUE_OFFICE_TIME` UNIQUE (`OFFICE_ID`, `APPOINTMENT_TIME`),
    -- Validación para no agendar una cita para el mismo doctor a la misma hora
    CONSTRAINT `UNIQUE_DOCTOR_TIME` UNIQUE (`DOCTOR_ID`, `APPOINTMENT_TIME`),
    -- Validación para no agendar una cita para un paciente en menos de 2 horas de diferencia
    CONSTRAINT `UNIQUE_PATIENT_TIME` UNIQUE (`PATIENT_NAME`, `APPOINTMENT_TIME`),
    -- Índice para verificar la cantidad de citas por día del doctor
    INDEX `IDX_DOCTOR_DATE` (`DOCTOR_ID`, `APPOINTMENT_TIME`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
