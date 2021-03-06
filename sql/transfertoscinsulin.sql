CREATE TABLE transfertoscinsulin (
	id     								INT UNSIGNED NOT NULL AUTO_INCREMENT,
	StudyNo								char(255),
	HoursOfIVInsulin			char(255),
	OralHypoglycemic_1		char(255),
	OralHypoglycemic_2		char(255),
	OralHypoglycemic_3		char(255),
	TotalDailyDose_1			char(255),
	TotalDailyDose_2			char(255),
	TotalDailyDose_3			char(255),
	Insulin_1							char(255),
	Insulin_2							char(255),
	Insulin_3							char(255),
	Insulin_4							char(255),
	InsulinDose_1					char(255),
	InsulinDose_2					char(255),
	InsulinDose_3					char(255),
	InsulinDose_4					char(255),
	InsulinRegime_1				char(255),
	InsulinRegime_2				char(255),
	InsulinRegime_3				char(255),
	InsulinRegime_4				char(255),
	Device_1							char(255),
	Device_2							char(255),
	Device_3							char(255),
	Device_4							char(255),
	IVInsulinStopped			char(255),
	BicarbonateNormalised	char(255),
	UrineKetonesCleared		char(255),
	TwoMeals							char(255),
	RestartingLongActingInsulinAnalogue	char(255),
	PatientOnLongActingInsulinAnalogue	char(255),
	LongActingInsulinAnalogueContinued	char(255),
	PRIMARY KEY (id)
);