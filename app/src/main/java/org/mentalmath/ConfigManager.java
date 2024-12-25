package org.mentalmath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
  private static final String CONFIG_FILE = "config.properties";
  private static final Properties configProperties = new Properties();
  private static final Properties currentLangProperties = new Properties();
  private ConfigManager() {}

  /**
   * load default configuration
   */
  public static void loadDefaultConfiguration(){
    try (InputStream inputStream = getResourceAsStream("/lang/lang_en.properties")) {
      currentLangProperties.load(inputStream);
      configProperties.setProperty("operator", "+"); //fast
    } catch (IOException e) {
      System.out.println("error in loadDefaultProperties");
      e.printStackTrace();
    }
  }

  public static Properties getCurrentLangProperties() {
    return currentLangProperties;
  }

  public static void loadCustomConfiguration() {
    boolean isPresent = loadCustomConfigFile();
    if (!isPresent) {
      loadDefaultConfiguration();
      return;
    }
    boolean isPresentLang = loadCustomLangConfigFile();
    if (!isPresentLang) {
      loadDefaultConfiguration();
    }
  }

  /**
   * load custom lang file
   * @return true if the file is loaded successfully
   */
  public static boolean loadCustomLangConfigFile() {
    String langCode = configProperties.getProperty("language");
    if (langCode == null) return false;

    String configDirPath = getConfigDirectoryPath();

    if (configDirPath != null) {
      File configDir = new File(configDirPath);
      File configFile = new File(configDir, "lang/lang_" + langCode + ".properties");
      
      if (configDir.exists() && configDir.isDirectory() && configFile.exists()) {
          try (FileInputStream inputStream = new FileInputStream(configFile)) {
            currentLangProperties.load(inputStream);
            return true;
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
    }
    return false;
  }
  
  /**
   * load custom config file
   */
  public static boolean loadCustomConfigFile() {
    String configDirPath = getConfigDirectoryPath();
    System.out.println("configDirPath: " + configDirPath);
    
    if (configDirPath != null) {
      File configDir = new File(configDirPath);
      File configFile = new File(configDir, CONFIG_FILE);
      
      if (configDir.exists() && configDir.isDirectory() && configFile.exists()) {
        try (FileInputStream inputStream = new FileInputStream(configFile)) {
            configProperties.load(inputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    }
    return false;
  }

  // Méthode pour récupérer le chemin du dossier de configuration externe
  private static String getConfigDirectoryPath() {
    String configDirPath = null;

    // Vérifier si on est en mode développement (vérifier l'existence du dossier externe)
    // Si on est en développement, on suppose que le dossier de configuration est à côté du répertoire de projet
    File projectDir = new File(System.getProperty("user.dir"));
    File configDir = new File(projectDir, "SimpleMentalMathAppConfig");

    if (configDir.exists() && configDir.isDirectory()) {
        configDirPath = configDir.getAbsolutePath();
    }

    return configDirPath;
  }

  private static InputStream getResourceAsStream(String resourceName) {
    return ConfigManager.class.getClassLoader().getResourceAsStream(resourceName);
  }

  public static void saveConfig() {
    try (FileOutputStream outputStream = new FileOutputStream(CONFIG_FILE)) {
      configProperties.store(outputStream, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getProperty(String key) {
    return configProperties.getProperty(key);
  }
  public static void setProperty(String key, String value) {
    configProperties.setProperty(key, value);
  }

}
