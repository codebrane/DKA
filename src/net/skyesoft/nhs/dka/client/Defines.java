/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

public class Defines {
  public static final String SERVER = "DKA_SERVER_ADDRESS";

  public static final String DKA_SITE_URL = SERVER + "/dka/site";

  public static final String SCRIPT_ROOT = "/dka/scripts/";
  public static final String FORM_HANDLER = SERVER + SCRIPT_ROOT + "dka.php";
  public static final String STUDY_NO_KEY = "StudyNo";
  public static final String TABLE_NAME_KEY = "TableName";

  public static final String USERNAME = "USERNAME";
  public static final String PASSWORD = "PASSWORD";

  // The time to wait for a request to timeout : 60s
  public static final int TIMEOUT = 60000;
}
