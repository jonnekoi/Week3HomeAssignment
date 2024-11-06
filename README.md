1. Database Tables:
  Three tables (employee_en, employee_fa, employee_ja) are created in the MySQL database to store employee data in different languages.

3. JavaFX Application:  
  The Application class initializes the JavaFX application and loads the hello-view.fxml file to set up the user interface.
  The Controller class handles the logic for the application, including language selection and saving employee data.

5. Language Bundles:  
  Properties files (langSelection.properties) are created for each language (English, Farsi, Japanese) to store localized strings.
  The updateLanguage method in the Controller class loads the appropriate resource bundle based on the selected language and updates the UI labels.

7. Saving Employee Data:
  The saveEmployee method in the Controller class retrieves the input data from the text fields and inserts it into the appropriate table based on the selected language.
