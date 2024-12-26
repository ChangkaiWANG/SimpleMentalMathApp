package org.mentalmath;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
  
  private static final Properties configProperties = new Properties();
  private static final Properties currentLangProperties = new Properties();
  private ConfigManager() {}

  //default configuration
  static{
    configProperties.setProperty("operator", "+");
    configProperties.setProperty("language", "en");
  }

  /**
   * For loading and reloading configuration
   */
  public static void loadConfiguration(){
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("lang/lang_"+configProperties.getProperty("language")+".properties")) {
      if (inputStream == null) throw new IOException("File not found in src/main/resources/lang");

      currentLangProperties.load(inputStream);
     
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Properties getCurrentLangProperties() {
    return currentLangProperties;
  }

  public static void reloadConfiguration() {
    loadConfiguration();
  }

  public static String getProperty(String key) {
    return configProperties.getProperty(key);
  }
  public static void setProperty(String key, String value) {
    configProperties.setProperty(key, value);
  }

}
