CREATE TABLE educationandfollowup (
	id     																	INT UNSIGNED NOT NULL AUTO_INCREMENT,
	StudyNo																	char(255),
	Other																		char(255),
	AttendedDiabetesCourse									char(255),
	NoEducationProvided											char(10),
	CommercialLeaflets											char(10),
	VerbalUpdate														char(10),
	Ketocard																char(10),
	DiabetesWebsite													char(10),
	AfterPreviousDKA												char(10),
	InformationLeaflet											char(10),
	OnlineDiabetesResources									char(10),
	AtTimeOfInitialDiagnosis								char(10),
	PatientHasAppropriateFollowUpInPlace		char(10),
	PRIMARY KEY (id)
);