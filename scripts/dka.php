<?php
define(DBSERVER, "localhost");
define(DBUSER, "dkauser");
define(DBPASSWD, "hohoho");
define(DATABASE, "DKA");

$logit = false;

// The GWT client is POSTing the data itself, rather than in a form
parse_str(file_get_contents("php://input"), $_POST);

// -----------------------------------------------------------------------------
// Loading study nos
if ($_GET[mode] == "getstudynos") {
	if ($logit) {
	  logit("");
	  logit("---------------------------------------------");
	  logit("getstudynos : ".$_GET[date]);
	  logit("---------------------------------------------");
	  logit("");
	}

	$db = mysql_connect(DBSERVER, DBUSER, DBPASSWD);
	mysql_select_db(DATABASE);
	
	$results = mysql_query("select StudyNo from ".$_GET[TableName]);
	if (mysql_num_rows($results) != 0) {
		$first = true;
		while ($row = mysql_fetch_assoc($results)) {
			if (!$first)
				echo ",";
    	echo $row['StudyNo'];
    	$first = false;
		}
	}
	else {
		echo "No previous data";
	}
	
	mysql_close($db);
	
	return;
}
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------
// Loading previous data
if ($_GET[mode] == "loaddata") {
	if ($logit) {
	  logit("");
	  logit("---------------------------------------------");
	  logit("loaddata : ".$_GET[date]);
	  logit("---------------------------------------------");
	  logit("");
	}

	$db = mysql_connect(DBSERVER, DBUSER, DBPASSWD);
	mysql_select_db(DATABASE);
	$results = mysql_query("select * from ".$_GET[TableName]." where StudyNo = '".$_GET[StudyNo]."'");
	if (mysql_num_rows($results) != 0) {
		$row = mysql_fetch_assoc($results);
		foreach ($row as $key => $value) {
			if ($key != "id") {
				echo "$key-------$value@@@@@@@";
			}
		}
	}
	else {
		echo "0";
	}
	mysql_close($db);
	
	return;
}
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------
// Raw SQL coming in
if ($_POST[mode] == "rawsql") {
	$sql = str_replace("\'", "'", $_POST[sql]);

	if ($logit) {
	  logit("");
	  logit("---------------------------------------------");
	  logit("rawsql : ".$_GET[date]." : ");
	  logit($sql);
	  logit("---------------------------------------------");
	  logit("");
	}

	$db = mysql_connect(DBSERVER, DBUSER, DBPASSWD);
	mysql_select_db(DATABASE);
	mysql_query($sql);
	mysql_close($db);
	return;
}
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------
// Multiple row data coming in
if ($_POST[mode] == "putmultirows") {
	$db = mysql_connect(DBSERVER, DBUSER, DBPASSWD);
	mysql_select_db(DATABASE);

	// Complete line of name-------value@@@@@@@name-------value sets
	if (!stristr($_POST[data], "VVVVVVVVVVVVVVVV")) {
		$rows[0] = $_POST[data];
	}
	else {
		$rows = explode("VVVVVVVVVVVVVVVV", $_POST[data]);
	}
	$noOfRows = count($rows);
	
	// Sets of name-------value pairs
	for ($rowCount=0; $rowCount < $noOfRows; $rowCount++) {
		$sqlInsert = "insert into ".$_POST[TableName]." ";
		$columns = "(";
		$values = "values (";

		$dataParts = explode("@@@@@@@", $rows[$rowCount]);
		$noOfDataParts = count($dataParts);
		
		for ($dataPartsCount=0; $dataPartsCount < $noOfDataParts; $dataPartsCount++) {
			$parts = explode("-------", $dataParts[$dataPartsCount]);
			
			$columns .= $parts[0].",";
			$values .= "'".$parts[1]."',";
		}
		
		$columns .= ")";
		$columns = str_replace(",)", ")", $columns);
		$values .= ")";
		$values = str_replace(",)", ")", $values);
		$sqlInsert .= $columns." ".$values;

		mysql_query($sqlInsert);

		if ($logit) {
		  logit("");
		  logit("---------------------------------------------");
		  logit("putmultirows : ".$_GET[date]." : ");
		  logit($sqlInsert);
		  logit("---------------------------------------------");
		  logit("");
		}
	}
	
	mysql_close($db);
	
	return;
}
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------
// Multiple row data going out
if ($_GET[mode] == "getmultirows") {
	if ($logit) {
	  logit("");
	  logit("---------------------------------------------");
	  logit("getmultirows : ".$_GET[date]);
	  logit("---------------------------------------------");
	  logit("");
	}
	
	$sql = "select * from ".$_GET[TableName]." where StudyNo = '".$_GET[StudyNo]."' order by id";
	
	$db = mysql_connect(DBSERVER, DBUSER, DBPASSWD);
	mysql_select_db(DATABASE, $db);
	
	$result = mysql_query($sql);
	$noOfRows = mysql_num_rows($result);
	
	if ($noOfRows != 0) {
		$rowCount = 0;
		while ($row = mysql_fetch_assoc($result)) {
			foreach ($row as $key => $value) {
				echo "$key-------$value@@@@@@@";
			}
			
			$rowCount++;

			if ($rowCount < $noOfRows) {
				echo "VVVVVVVVVVVVVVVV";
			}
		}
	}
	
	return;
}
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------
// Inserting data
$sqlInsert = "insert into ".$_POST[TableName]." ";
$columns = "(";
$values = "values (";
foreach ($_POST as $key => $value) {
	if (($key != "TableName") && ($key != "date")) {
		$columns .= "$key,";
		$values .= "'$value',";
	}
}
$columns .= ")";
$columns = str_replace(",)", ")", $columns);
$values .= ")";
$values = str_replace(",)", ")", $values);
$sqlInsert .= $columns." ".$values;
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------
// Updating data
$sqlUpdate = "update ".$_POST[TableName]." set";
foreach ($_POST as $key => $value) {
	if (($key != "TableName") && ($key != "date")) {
		$sqlUpdate .= " $key = '$value',";
	}
}
$sqlUpdate .= " where StudyNo = '".$_POST[StudyNo]."'";
$sqlUpdate = str_replace(", where", " where", $sqlUpdate);
// -----------------------------------------------------------------------------

$db = mysql_connect(DBSERVER, DBUSER, DBPASSWD);
mysql_select_db(DATABASE, $db);

// See if the row already exists in the table for a particular study no
$rowExistsQuery = "select StudyNo from ".$_POST[TableName]." where StudyNo = '".$_POST[StudyNo]."'";
$result = mysql_query($rowExistsQuery);
if (mysql_num_rows($result) == 0) {
	$sql = $sqlInsert;
}
else {
	$sql = $sqlUpdate;
}

mysql_query($sql);

if ($logit) {
  logit("");
  logit("---------------------------------------------");
  logit("outwith : ".$_GET[date]." : ");
  logit($sql);
  logit("---------------------------------------------");
  logit("");
}

mysql_close($db);

function logit($message) {
  $fd = fopen("../log/dka.log", "a+");
  fwrite($fd, $message."\n");
  fclose($fd);
}
?>
