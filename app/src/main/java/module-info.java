module SimpleMentalMathApp {
  requires javafx.controls;
  requires javafx.fxml;
  requires transitive javafx.graphics;
  
  opens org.mentalmath to javafx.fxml;
  opens org.mentalmath.controller to javafx.fxml;
  exports org.mentalmath;
  exports org.mentalmath.controller;
}