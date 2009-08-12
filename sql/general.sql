CREATE TABLE generalinfo (
	id     								INT UNSIGNED NOT NULL AUTO_INCREMENT,
	StudyNo								char(255),
	Age										char(3),
	DaysInPatient					char(10),
	HCO3									char(255),
	PH										char(255),
	PHRangeUpper					char(255),
	PHRangeLower					char(255),
	Sex										char(10),
	TypeOfDiabetes				char(20),
	SourceOfReferral			char(20),
	Symptoms							char(10),
	BMGreaterThan15				char(10),
	Ketones								char(20),
	DiagnosisConfirmed		char(10),
	PRIMARY KEY (id)
);