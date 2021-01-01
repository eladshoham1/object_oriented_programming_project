/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject;

import java.io.File;
import java.io.IOException;

import finalProject.controller.OlympicsController;
import finalProject.model.Olympics;
import finalProject.model.exceptions.CountryException;
import finalProject.model.interfaces.OlympicsModelable;
import finalProject.view.OlympicsJavaFX;
import finalProject.view.OlympicsViewable;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	public OlympicsModelable theModel;
	public OlympicsViewable theView;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		boolean allFilesExist = false;

		do {
			try {
				theModel = new Olympics();
				theView = new OlympicsJavaFX(primaryStage);

				@SuppressWarnings("unused")
				OlympicsController controller = new OlympicsController(theModel, theView);

				allFilesExist = true;
			} catch (IOException e) {
				File file = new File(e.getMessage().substring(0, e.getMessage().indexOf("(")));

				try {
					file.createNewFile();
				} catch (IOException e1) {

				}
			} catch (ClassNotFoundException | CountryException e) {

			}
		} while (!allFilesExist);
	}
}