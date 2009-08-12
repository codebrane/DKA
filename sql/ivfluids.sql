CREATE TABLE ivfluids (
	id     								INT UNSIGNED NOT NULL AUTO_INCREMENT,
	StudyNo								char(255),
	Hour									char(255),
	NaCl									char(255),
	NaClRateCorrect				char(255),
	BMLessThan14					char(255),
	TenPercentDextrose		char(255),
	DextroseRateCorrect		char(255),
	KPlus									char(255),
	KPlusReplacement			char(255),
	InsulinUnitsPerHour		char(255),
	PRIMARY KEY (id)
);