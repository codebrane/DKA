CREATE TABLE treatmentandinvestigation (
	id     								INT UNSIGNED NOT NULL AUTO_INCREMENT,
	StudyNo								char(255),
	CauseDetails					char(255),
	MSSU									char(20),
	FBC										char(20),
	ECG										char(20),
	CauseIdentified				char(10),
	CXR										char(20),
	ViralTitres						char(20),
	Antibiotics						char(10),
	FluidBalanceChart			char(10),
	NGTube								char(10),
	CentralLine						char(10),
	IntensiveCare					char(10),
	Catheter							char(10),
	FollowUp							char(10),
	PRIMARY KEY (id)
);