/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import finalProject.controller.OlympicsController;
import finalProject.listeners.OlympicsUIEventsListener;
import finalProject.model.Referee;
import finalProject.model.NationalTeam;
import finalProject.model.Stadium;
import finalProject.model.enums.Countries;
import finalProject.model.enums.Fields;
import finalProject.model.enums.Medals;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.Jumpable;
import finalProject.model.interfaces.OlympicsModelable;
import finalProject.model.interfaces.Runable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OlympicsJavaFX implements OlympicsViewable {
	private ArrayList<OlympicsUIEventsListener> allListeners = new ArrayList<OlympicsUIEventsListener>();

	private GridPane gpRoot = new GridPane();

	private HBox hbRoot = new HBox();
	private Button btnAthletes = new Button("Athletes");
	private Button btnReferees = new Button("Referees");
	private Button btnStadiums = new Button("Stadiums");
	private Button btnOlympics = new Button("Olympics");

	private StackPane spRoot = new StackPane();

	private GridPane gpAthletesRoot = new GridPane();
	private TableView<Athleteable> tableAthletes = new TableView<Athleteable>();
	private TableColumn<Athleteable, String> athleteNameCol = new TableColumn<Athleteable, String>("Name");
	private TableColumn<Athleteable, String> athleteCountryCol = new TableColumn<Athleteable, String>("Country");
	private TableColumn<Athleteable, String> athleteTypeCol = new TableColumn<Athleteable, String>("Athlete Type");
	private TableColumn<Athleteable, Float> maxSpeedCol = new TableColumn<Athleteable, Float>("Max Speed");
	private TableColumn<Athleteable, Float> maxJumpCol = new TableColumn<Athleteable, Float>("Max Jump");
	private Label lblAthleteName = new Label("Name: ");
	private TextField tfAthleteName = new TextField();
	private Label errAthleteName = new Label("*Athlete must have a name");
	private Label lblAthleteNationalTeam = new Label("National team: ");
	private ComboBox<String> cmbAthleteCountry = new ComboBox<String>();
	private Label errAthleteCountry = new Label("*Athlete must have a country");
	private Label lblAthleteAbility = new Label("Athlete Ability: ");
	private CheckBox chkRunner = new CheckBox("Runner");
	private CheckBox chkJumper = new CheckBox("Jumper");
	private Label errAthleteAbility = new Label("*Athlete must have ability");
	private Label lblMaxSpeed = new Label("Max speed: ");
	private TextField tfMaxSpeed = new TextField();
	private Label errMaxSpeed = new Label("*Runner must have max speed");
	private Label errMaxSpeedFloat = new Label("*max speed must be a float number!");
	private Label lblMaxJump = new Label("Max jump: ");
	private TextField tfMaxJump = new TextField();
	private Label errMaxJump = new Label("*Jumper must have max jump");
	private Label errMaxJumpFloat = new Label("*max jump must be a float number!");
	private Button btnAddAthlete = new Button("Add Athlete");
	private Button btnUpdateAthlete = new Button("Update Athlete");
	private Button btnDeleteAthlete = new Button("Delete Athlete");
	private Button btnClearAthlete = new Button("Clear All");
	private Athleteable currentAthlete;

	private GridPane gpRefereesRoot = new GridPane();
	private TableView<Referee> tableReferees = new TableView<Referee>();
	private TableColumn<Referee, String> refereeNameCol = new TableColumn<Referee, String>("Name");
	private TableColumn<Referee, String> refereeCountryCol = new TableColumn<Referee, String>("Country");
	private TableColumn<Referee, String> refereeFieldCol = new TableColumn<Referee, String>("Field");
	private Label lblRefereeName = new Label("Name: ");
	private TextField tfRefereeName = new TextField();
	private Label errRefereeName = new Label("*Referee must have a name");
	private Label lblRefereeCountry = new Label("Country: ");
	private ComboBox<String> cmbRefereeCountry = new ComboBox<String>();
	private Label errRefereeCountry = new Label("*Referee must have a Country");
	private Label lblRefereeField = new Label("Field: ");
	private ComboBox<String> cmbRefereeField = new ComboBox<String>();
	private Label errRefereeField = new Label("*Referee must have a Field");
	private Button btnAddReferee = new Button("Add Referee");
	private Button btnUpdateReferee = new Button("Update Referee");
	private Button btnDeleteReferee = new Button("Delete Referee");
	private Button btnClearReferee = new Button("Clear All");
	private Referee currentReferee;

	private GridPane gpStadiumsRoot = new GridPane();
	private TableView<Stadium> tableStadiums = new TableView<Stadium>();
	private TableColumn<Stadium, String> stadiumNameCol = new TableColumn<Stadium, String>("Name");
	private TableColumn<Stadium, String> stadiumLocationCol = new TableColumn<Stadium, String>("Location");
	private TableColumn<Stadium, Integer> stadiumCapacityCol = new TableColumn<Stadium, Integer>("Capacity");
	private Label lblStadiumName = new Label("Name: ");
	private TextField tfStadiumName = new TextField();
	private Label errStadiumName = new Label("*Stadium must have a name");
	private Label lblStadiumLocation = new Label("Location: ");
	private ComboBox<String> cmbStadiumLocation = new ComboBox<String>();
	private Label errStadiumLocation = new Label("*Stadium must have location");
	private Label lblStadiumCapacity = new Label("Capacity: ");
	private TextField tfStadiumCapacity = new TextField();
	private Label errStadiumCapacity = new Label("*Stadium must have capacity");
	private Label errStadiumCapacityInteger = new Label("*Capacity must be a integer number!");
	private Button btnAddStadium = new Button("Add Stadium");
	private Button btnUpdateStadium = new Button("Update Stadium");
	private Button btnDeleteStadium = new Button("Delete Stadium");
	private Button btnClearStadium = new Button("Clear All");
	private Stadium currentStadium;

	private GridPane gpMakeOlympicsRoot = new GridPane();
	private Label lblRunningIndividualTournaments = new Label("Number of running individual tournaments: ");
	private TextField tfRunningIndividualTournaments = new TextField();
	private Label errRunningIndividualTournaments = new Label(
			"*Number of running individual tournaments must be a integer number");
	private Label lblJumpingIndividualTournaments = new Label("Number of jumping individual tournaments: ");
	private TextField tfJumpingIndividualTournaments = new TextField();
	private Label errJumpingIndividualTournaments = new Label(
			"*Number of jumping individual tournaments must be a integer number");
	private Label lblRunningTeamTournaments = new Label("Number of running team tournaments: ");
	private TextField tfRunningTeamTournaments = new TextField();
	private Label errRunningTeamTournaments = new Label("*Number of running team tournaments must be a integer number");
	private Label lblJumpingTeamTournaments = new Label("Number of jumping team tournaments: ");
	private TextField tfJumpingTeamTournaments = new TextField();
	private Label errJumpingTeamTournaments = new Label("*Number of jumping team tournaments must be a integer number");
	private Button btnMakeOlympics = new Button("Make Olympics");
	private Label errMakeOlympics = new Label("*Olympics must have tournaments");

	private GridPane gpOlympicsRoot = new GridPane();
	private Label lblStartDateOlympics = new Label();
	private Label lblEndDateOlympics = new Label();
	private Label lblNumOfTournaments = new Label();
	private Label lblNumOfAthletes = new Label();
	private Label lblNumOfReferees = new Label();
	private Label lblNumOfStadiums = new Label();
	private TableView<NationalTeam> tableOlympicsRank = new TableView<NationalTeam>();
	private TableColumn<NationalTeam, Integer> rankCol = new TableColumn<NationalTeam, Integer>("Rank");
	private TableColumn<NationalTeam, String> OlympicsNationalTeamCol = new TableColumn<NationalTeam, String>(
			"National Team");
	private TableColumn<NationalTeam, String> numAthletesCol = new TableColumn<NationalTeam, String>(
			"Number Of Athletes");
	private TableColumn<NationalTeam, String> OlympicsRankMedalsCol = new TableColumn<NationalTeam, String>("Medals");
	private TableColumn<NationalTeam, String> OlympicsGoldCol = new TableColumn<NationalTeam, String>("Gold");
	private TableColumn<NationalTeam, String> OlympicsSilverCol = new TableColumn<NationalTeam, String>("Silver");
	private TableColumn<NationalTeam, String> OlympicsBronzeCol = new TableColumn<NationalTeam, String>("Bronze");

	@SuppressWarnings("unchecked")
	public OlympicsJavaFX(Stage theStage) {
		theStage.setTitle("Olympics");

		gpRoot.setPadding(new Insets(15));
		gpRoot.setHgap(15);
		gpRoot.setVgap(15);

		hbRoot.setSpacing(180);
		hbRoot.setAlignment(Pos.CENTER);
		btnAthletes.setMinWidth(200);
		btnReferees.setMinWidth(200);
		btnStadiums.setMinWidth(200);
		btnOlympics.setMinWidth(200);
		hbRoot.getChildren().addAll(btnAthletes, btnReferees, btnStadiums, btnOlympics);

		hbRoot.setPadding(new Insets(10));
		hbRoot.setAlignment(Pos.CENTER);

		gpAthletesRoot.setVisible(false);
		gpRefereesRoot.setVisible(false);
		gpStadiumsRoot.setVisible(false);
		gpOlympicsRoot.setVisible(false);
		gpMakeOlympicsRoot.setVisible(false);

		for (Countries country : Countries.values()) {
			cmbAthleteCountry.getItems().add(country.toString());
			cmbRefereeCountry.getItems().add(country.toString());
			cmbStadiumLocation.getItems().add(country.toString());
		}

		gpAthletesRoot.setPadding(new Insets(20));
		gpAthletesRoot.setHgap(40);
		gpAthletesRoot.setVgap(20);

		tableAthletes.setMinHeight(600);
		athleteNameCol.setMinWidth(150);
		athleteCountryCol.setMinWidth(150);
		athleteTypeCol.setMinWidth(100);
		maxSpeedCol.setMinWidth(100);
		maxJumpCol.setMinWidth(100);
		athleteNameCol.setCellValueFactory(new PropertyValueFactory<Athleteable, String>("name"));
		athleteCountryCol.setCellValueFactory(new PropertyValueFactory<Athleteable, String>("country"));
		athleteTypeCol
				.setCellValueFactory(new PropertyValueFactory<Athleteable, String>(Athleteable.class.getSimpleName()));
		athleteTypeCol.setCellValueFactory(new PropertyValueFactory<Athleteable, String>("athleteType"));
		maxSpeedCol.setCellValueFactory(new PropertyValueFactory<Athleteable, Float>("maxSpeed"));
		maxJumpCol.setCellValueFactory(new PropertyValueFactory<Athleteable, Float>("maxJumpHeight"));
		tableAthletes.getColumns().addAll(athleteNameCol, athleteCountryCol, athleteTypeCol, maxSpeedCol, maxJumpCol);

		clearAthleteErrors();
		lblMaxSpeed.setVisible(false);
		tfMaxSpeed.setVisible(false);
		lblMaxJump.setVisible(false);
		tfMaxJump.setVisible(false);
		btnUpdateAthlete.setVisible(false);
		btnDeleteAthlete.setVisible(false);

		gpAthletesRoot.add(tableAthletes, 0, 0, 1, 10);
		gpAthletesRoot.add(lblAthleteName, 1, 0);
		gpAthletesRoot.add(tfAthleteName, 2, 0);
		gpAthletesRoot.add(errAthleteName, 3, 0);
		gpAthletesRoot.add(lblAthleteNationalTeam, 1, 1);
		gpAthletesRoot.add(cmbAthleteCountry, 2, 1);
		gpAthletesRoot.add(errAthleteCountry, 3, 1);
		gpAthletesRoot.add(lblAthleteAbility, 1, 2);
		gpAthletesRoot.add(errAthleteAbility, 2, 2);
		gpAthletesRoot.add(chkRunner, 1, 3);
		gpAthletesRoot.add(chkJumper, 1, 4);
		gpAthletesRoot.add(lblMaxSpeed, 1, 5);
		gpAthletesRoot.add(tfMaxSpeed, 2, 5);
		gpAthletesRoot.add(errMaxSpeed, 3, 5);
		gpAthletesRoot.add(errMaxSpeedFloat, 3, 5);
		gpAthletesRoot.add(lblMaxJump, 1, 6);
		gpAthletesRoot.add(tfMaxJump, 2, 6);
		gpAthletesRoot.add(errMaxJump, 3, 6);
		gpAthletesRoot.add(errMaxJumpFloat, 3, 6);
		gpAthletesRoot.add(btnAddAthlete, 1, 7);
		gpAthletesRoot.add(btnUpdateAthlete, 1, 7);
		gpAthletesRoot.add(btnDeleteAthlete, 2, 7);
		gpAthletesRoot.add(btnClearAthlete, 1, 8);

		gpRefereesRoot.setPadding(new Insets(20));
		gpRefereesRoot.setHgap(40);
		gpRefereesRoot.setVgap(20);

		tableReferees.setMinHeight(600);
		refereeNameCol.setMinWidth(200);
		refereeCountryCol.setMinWidth(200);
		refereeFieldCol.setMinWidth(200);
		refereeNameCol.setCellValueFactory(new PropertyValueFactory<Referee, String>("name"));
		refereeCountryCol.setCellValueFactory(new PropertyValueFactory<Referee, String>("country"));
		refereeFieldCol.setCellValueFactory(new PropertyValueFactory<Referee, String>("field"));
		tableReferees.getColumns().addAll(refereeNameCol, refereeCountryCol, refereeFieldCol);

		clearRefereeErrors();
		btnUpdateReferee.setVisible(false);
		btnDeleteReferee.setVisible(false);

		for (Fields field : Fields.values())
			cmbRefereeField.getItems().add(field.toString());

		gpRefereesRoot.add(tableReferees, 0, 0, 1, 7);
		gpRefereesRoot.add(lblRefereeName, 1, 0);
		gpRefereesRoot.add(tfRefereeName, 2, 0);
		gpRefereesRoot.add(errRefereeName, 3, 0);
		gpRefereesRoot.add(lblRefereeCountry, 1, 1);
		gpRefereesRoot.add(cmbRefereeCountry, 2, 1);
		gpRefereesRoot.add(errRefereeCountry, 3, 1);
		gpRefereesRoot.add(lblRefereeField, 1, 2);
		gpRefereesRoot.add(cmbRefereeField, 2, 2);
		gpRefereesRoot.add(errRefereeField, 3, 2);
		gpRefereesRoot.add(btnAddReferee, 1, 3);
		gpRefereesRoot.add(btnUpdateReferee, 1, 3);
		gpRefereesRoot.add(btnDeleteReferee, 2, 3);
		gpRefereesRoot.add(btnClearReferee, 1, 4);

		gpStadiumsRoot.setPadding(new Insets(20));
		gpStadiumsRoot.setHgap(40);
		gpStadiumsRoot.setVgap(20);

		tableStadiums.setMinHeight(600);
		stadiumNameCol.setMinWidth(200);
		stadiumLocationCol.setMinWidth(200);
		stadiumCapacityCol.setMinWidth(200);
		stadiumNameCol.setCellValueFactory(new PropertyValueFactory<Stadium, String>("name"));
		stadiumLocationCol.setCellValueFactory(new PropertyValueFactory<Stadium, String>("Location"));
		stadiumCapacityCol.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>("Capacity"));
		tableStadiums.getColumns().addAll(stadiumNameCol, stadiumLocationCol, stadiumCapacityCol);

		clearStadiumErrors();
		btnUpdateStadium.setVisible(false);
		btnDeleteStadium.setVisible(false);

		gpStadiumsRoot.add(tableStadiums, 0, 0, 1, 7);
		gpStadiumsRoot.add(lblStadiumName, 1, 0);
		gpStadiumsRoot.add(tfStadiumName, 2, 0);
		gpStadiumsRoot.add(errStadiumName, 3, 0);
		gpStadiumsRoot.add(lblStadiumLocation, 1, 1);
		gpStadiumsRoot.add(cmbStadiumLocation, 2, 1);
		gpStadiumsRoot.add(errStadiumLocation, 3, 1);
		gpStadiumsRoot.add(lblStadiumCapacity, 1, 2);
		gpStadiumsRoot.add(tfStadiumCapacity, 2, 2);
		gpStadiumsRoot.add(errStadiumCapacity, 3, 2);
		gpStadiumsRoot.add(errStadiumCapacityInteger, 3, 2);
		gpStadiumsRoot.add(btnAddStadium, 1, 3);
		gpStadiumsRoot.add(btnUpdateStadium, 1, 3);
		gpStadiumsRoot.add(btnDeleteStadium, 2, 3);
		gpStadiumsRoot.add(btnClearStadium, 1, 4);

		gpOlympicsRoot.setPadding(new Insets(20));
		gpOlympicsRoot.setHgap(40);
		gpOlympicsRoot.setVgap(20);

		clearOlympicsErrors();

		gpOlympicsRoot.add(lblRunningIndividualTournaments, 0, 0);
		gpOlympicsRoot.add(tfRunningIndividualTournaments, 1, 0);
		gpOlympicsRoot.add(errRunningIndividualTournaments, 2, 0);
		gpOlympicsRoot.add(lblJumpingIndividualTournaments, 0, 1);
		gpOlympicsRoot.add(tfJumpingIndividualTournaments, 1, 1);
		gpOlympicsRoot.add(errJumpingIndividualTournaments, 2, 1);
		gpOlympicsRoot.add(lblRunningTeamTournaments, 0, 2);
		gpOlympicsRoot.add(tfRunningTeamTournaments, 1, 2);
		gpOlympicsRoot.add(errRunningTeamTournaments, 2, 2);
		gpOlympicsRoot.add(lblJumpingTeamTournaments, 0, 3);
		gpOlympicsRoot.add(tfJumpingTeamTournaments, 1, 3);
		gpOlympicsRoot.add(errJumpingTeamTournaments, 2, 3);
		gpOlympicsRoot.add(btnMakeOlympics, 0, 4);
		gpOlympicsRoot.add(errMakeOlympics, 1, 4);

		gpMakeOlympicsRoot.setPadding(new Insets(20));
		gpMakeOlympicsRoot.setHgap(40);
		gpMakeOlympicsRoot.setVgap(20);

		tableOlympicsRank.setMinHeight(600);
		rankCol.setMinWidth(50);
		OlympicsNationalTeamCol.setMinWidth(250);
		numAthletesCol.setMinWidth(150);
		OlympicsGoldCol.setMinWidth(150);
		OlympicsSilverCol.setMinWidth(150);
		OlympicsBronzeCol.setMinWidth(150);
		OlympicsNationalTeamCol.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("name"));
		numAthletesCol.setCellValueFactory(new PropertyValueFactory<NationalTeam, String>("numOfAthletes"));
		OlympicsRankMedalsCol.getColumns().addAll(OlympicsGoldCol, OlympicsSilverCol, OlympicsBronzeCol);
		tableOlympicsRank.getColumns().addAll(rankCol, OlympicsNationalTeamCol, numAthletesCol, OlympicsRankMedalsCol);

		gpMakeOlympicsRoot.add(tableOlympicsRank, 0, 0, 1, 8);
		gpMakeOlympicsRoot.add(lblStartDateOlympics, 1, 1);
		gpMakeOlympicsRoot.add(lblEndDateOlympics, 1, 2);
		gpMakeOlympicsRoot.add(lblNumOfTournaments, 1, 3);
		gpMakeOlympicsRoot.add(lblNumOfAthletes, 1, 4);
		gpMakeOlympicsRoot.add(lblNumOfReferees, 1, 5);
		gpMakeOlympicsRoot.add(lblNumOfStadiums, 1, 6);

		spRoot.getChildren().addAll(gpAthletesRoot, gpRefereesRoot, gpStadiumsRoot, gpOlympicsRoot, gpMakeOlympicsRoot);

		gpRoot.add(hbRoot, 0, 0);
		gpRoot.add(spRoot, 0, 1);

		theStage.setScene(new Scene(gpRoot, 1400, 800));
		theStage.show();

		btnAthletes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				gpAthletesRoot.setVisible(true);
				gpRefereesRoot.setVisible(false);
				gpStadiumsRoot.setVisible(false);
				gpOlympicsRoot.setVisible(false);
				gpMakeOlympicsRoot.setVisible(false);

				tableAthletes.getItems().clear();

				for (OlympicsUIEventsListener l : allListeners)
					l.showAllAthletesViewEvent();
			}
		});

		tableAthletes.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event action) {
				clearAthleteInputs();

				if (!(tableAthletes.getSelectionModel().isEmpty())) {
					currentAthlete = tableAthletes.getSelectionModel().getSelectedItems().get(0);
					tfAthleteName.setText(currentAthlete.getName());
					cmbAthleteCountry.setValue(currentAthlete.getCountry());

					if (currentAthlete instanceof Runable) {
						chkRunner.setSelected(true);
						lblMaxSpeed.setVisible(true);
						tfMaxSpeed.setVisible(true);
						tfMaxSpeed.setText(String.valueOf(((Runable) currentAthlete).getMaxSpeed()));
					}

					if (currentAthlete instanceof Jumpable) {
						chkJumper.setSelected(true);
						lblMaxJump.setVisible(true);
						tfMaxJump.setVisible(true);
						tfMaxJump.setText(String.valueOf(((Jumpable) currentAthlete).getMaxJumpHeight()));
					}

					btnAddAthlete.setVisible(false);
					btnUpdateAthlete.setVisible(true);
					btnDeleteAthlete.setVisible(true);
				}
			}
		});

		btnClearAthlete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				clearAthleteInputs();
				clearAthleteErrors();
				btnAddAthlete.setVisible(true);
				btnUpdateAthlete.setVisible(false);
				btnDeleteAthlete.setVisible(false);
			}
		});

		chkRunner.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				runnerOptions();
			}
		});

		chkJumper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				jumperOptions();
			}
		});

		btnAddAthlete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {

				if (checkAthleteInputs()) {
					float maxSpeed = 0.0f, maxJump = 0.0f;

					if (chkRunner.isSelected())
						maxSpeed = Float.parseFloat(tfMaxSpeed.getText());

					if (chkJumper.isSelected())
						maxJump = Float.parseFloat(tfMaxJump.getText());

					for (OlympicsUIEventsListener l : allListeners)
						l.addAthleteViewEvent(tfAthleteName.getText(), cmbAthleteCountry.getValue(),
								chkRunner.isSelected(), chkJumper.isSelected(), maxSpeed, maxJump);

					clearAthleteInputs();
				}
			}
		});

		btnUpdateAthlete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (checkAthleteInputs()) {
					float maxSpeed = 0.0f, maxJump = 0.0f;

					if (chkRunner.isSelected())
						maxSpeed = Float.parseFloat(tfMaxSpeed.getText());

					if (chkJumper.isSelected())
						maxJump = Float.parseFloat(tfMaxJump.getText());

					for (OlympicsUIEventsListener l : allListeners)
						l.updateAthleteViewEvent(currentAthlete, tfAthleteName.getText(), cmbAthleteCountry.getValue(),
								chkRunner.isSelected(), chkJumper.isSelected(), maxSpeed, maxJump);

					clearAthleteInputs();
				}
			}
		});

		btnDeleteAthlete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (JOptionPane.showConfirmDialog(null, currentAthlete.toString(), "Athlete delete",
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					for (OlympicsUIEventsListener l : allListeners)
						l.deleteAthleteViewEvent(currentAthlete);

					clearAthleteInputs();
				}
			}
		});

		btnReferees.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				gpAthletesRoot.setVisible(false);
				gpRefereesRoot.setVisible(true);
				gpStadiumsRoot.setVisible(false);
				gpOlympicsRoot.setVisible(false);
				gpMakeOlympicsRoot.setVisible(false);

				tableReferees.getItems().clear();

				for (OlympicsUIEventsListener l : allListeners)
					l.showAllRefereesViewEvent();
			}
		});

		tableReferees.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event action) {
				if (!(tableReferees.getSelectionModel().isEmpty())) {
					currentReferee = tableReferees.getSelectionModel().getSelectedItems().get(0);
					tfRefereeName.setText(currentReferee.getName());
					cmbRefereeCountry.setValue(currentReferee.getCountry());
					cmbRefereeField.setValue(currentReferee.getField());

					btnAddReferee.setVisible(false);
					btnUpdateReferee.setVisible(true);
					btnDeleteReferee.setVisible(true);
				}
			}
		});

		btnClearReferee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				clearRefereeInputs();
				clearRefereeErrors();
				btnAddReferee.setVisible(true);
				btnUpdateReferee.setVisible(false);
				btnDeleteReferee.setVisible(false);
			}
		});

		btnAddReferee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (checkRefereeInputs()) {
					for (OlympicsUIEventsListener l : allListeners)
						l.addRefereeViewEvent(tfRefereeName.getText(), cmbRefereeCountry.getValue(),
								cmbRefereeField.getValue());

					clearRefereeInputs();
				}
			}
		});

		btnUpdateReferee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (checkRefereeInputs()) {
					for (OlympicsUIEventsListener l : allListeners)
						l.updateRefereeViewEvent(currentReferee, tfRefereeName.getText(), cmbRefereeCountry.getValue(),
								cmbRefereeField.getValue());

					clearRefereeInputs();
				}
			}
		});

		btnDeleteReferee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (JOptionPane.showConfirmDialog(null, currentReferee.toString(), "Referee delete",
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					for (OlympicsUIEventsListener l : allListeners)
						l.deleteRefereeViewEvent(currentReferee);

					clearRefereeInputs();
				}
			}
		});

		btnStadiums.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				gpAthletesRoot.setVisible(false);
				gpRefereesRoot.setVisible(false);
				gpStadiumsRoot.setVisible(true);
				gpOlympicsRoot.setVisible(false);
				gpMakeOlympicsRoot.setVisible(false);

				tableStadiums.getItems().clear();

				for (OlympicsUIEventsListener l : allListeners)
					l.showAllStadiumsViewEvent();
			}
		});

		tableStadiums.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event action) {
				if (!(tableStadiums.getSelectionModel().isEmpty())) {
					currentStadium = tableStadiums.getSelectionModel().getSelectedItems().get(0);
					tfStadiumName.setText(currentStadium.getName());
					cmbStadiumLocation.setValue(currentStadium.getLocation());
					tfStadiumCapacity.setText(String.valueOf(currentStadium.getCapacity()));

					btnAddStadium.setVisible(false);
					btnUpdateStadium.setVisible(true);
					btnDeleteStadium.setVisible(true);
				}
			}
		});

		btnClearStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				clearStadiumInputs();
				clearStadiumErrors();
				btnAddStadium.setVisible(true);
				btnUpdateStadium.setVisible(false);
				btnDeleteStadium.setVisible(false);
			}
		});

		btnAddStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (checkStadiumInputs()) {
					for (OlympicsUIEventsListener l : allListeners)
						l.addStadiumViewEvent(tfStadiumName.getText(), cmbStadiumLocation.getValue(),
								Integer.parseInt(tfStadiumCapacity.getText()));

					clearStadiumInputs();
				}
			}
		});

		btnUpdateStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (checkStadiumInputs()) {
					for (OlympicsUIEventsListener l : allListeners)
						l.updateStadiumViewEvent(currentStadium, tfStadiumName.getText(), cmbStadiumLocation.getValue(),
								Integer.parseInt(tfStadiumCapacity.getText()));

					clearStadiumInputs();
				}
			}
		});

		btnDeleteStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (JOptionPane.showConfirmDialog(null, currentStadium.toString(), "Stadium delete",
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					for (OlympicsUIEventsListener l : allListeners)
						l.deleteStadiumViewEvent(currentStadium);

					clearStadiumInputs();
				}
			}
		});

		btnOlympics.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				gpAthletesRoot.setVisible(false);
				gpRefereesRoot.setVisible(false);
				gpStadiumsRoot.setVisible(false);
				gpOlympicsRoot.setVisible(true);
				gpMakeOlympicsRoot.setVisible(false);
			}
		});

		btnMakeOlympics.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (checkOlympicsInputs()) {
					tableOlympicsRank.getItems().clear();
					gpAthletesRoot.setVisible(false);
					gpRefereesRoot.setVisible(false);
					gpStadiumsRoot.setVisible(false);
					gpOlympicsRoot.setVisible(false);
					gpMakeOlympicsRoot.setVisible(true);

					int numRunningIndividualTournaments = Integer.parseInt(tfRunningIndividualTournaments.getText());
					int numJumpingIndividualTournaments = Integer.parseInt(tfJumpingIndividualTournaments.getText());
					int numRunningTeamTournaments = Integer.parseInt(tfRunningTeamTournaments.getText());
					int numJumpingTeamTournaments = Integer.parseInt(tfJumpingTeamTournaments.getText());

					for (OlympicsUIEventsListener l : allListeners)
						l.makeOlympicsViewEvent(numRunningIndividualTournaments, numJumpingIndividualTournaments,
								numRunningTeamTournaments, numJumpingTeamTournaments);

					if (tableOlympicsRank.getItems().isEmpty()) {
						gpOlympicsRoot.setVisible(true);
						gpMakeOlympicsRoot.setVisible(false);
					} else
						clearOlympicsInputs();
				}
			}
		});

	}

	@Override
	public void registerListener(OlympicsController newListener) {
		allListeners.add(newListener);
	}

	@Override
	public void getStartDateViewEvent(LocalDateTime startDate) {
		lblStartDateOlympics.setText(startDate.toString());
	}

	@Override
	public void getEndDateViewEvent(LocalDateTime endDate) {
		lblEndDateOlympics.setText(endDate.toString());
	}

	@Override
	public void addAthleteViewEvent(Athleteable newAthlete) {
		tableAthletes.getItems().add(newAthlete);
		athleteTypeCol.setCellValueFactory(
				athlete -> new SimpleStringProperty(athlete.getValue().getClass().getSimpleName()));
	}

	@Override
	public void updateAthleteViewEvent(Athleteable oldAthlete, Athleteable newAthlete) {
		for (int i = 0; i < tableAthletes.getItems().size(); i++) {
			if (oldAthlete.equals(tableAthletes.getItems().get(i)))
				tableAthletes.getItems().set(i, newAthlete);
		}
	}

	@Override
	public void deleteAthleteViewEvent(Athleteable athlete) {
		tableAthletes.getItems().remove(athlete);
	}

	@Override
	public void addRefereeViewEvent(Referee newReferee) {
		tableReferees.getItems().add(newReferee);
	}

	@Override
	public void updateRefereeViewEvent(Referee oldReferee, Referee newReferee) {
		for (int i = 0; i < tableReferees.getItems().size(); i++) {
			if (oldReferee.equals(tableReferees.getItems().get(i)))
				tableReferees.getItems().set(i, newReferee);
		}
	}

	@Override
	public void deleteRefereeViewEvent(Referee referee) {
		tableReferees.getItems().remove(referee);
	}

	@Override
	public void addStadiumViewEvent(Stadium newStadium) {
		tableStadiums.getItems().add(newStadium);
	}

	@Override
	public void updateStadiumViewEvent(Stadium oldStadium, Stadium newStadium) {
		for (int i = 0; i < tableStadiums.getItems().size(); i++) {
			if (oldStadium.equals(tableStadiums.getItems().get(i)))
				tableStadiums.getItems().set(i, newStadium);
		}
	}

	@Override
	public void deleteStadiumViewEvent(Stadium stadium) {
		tableStadiums.getItems().remove(stadium);
	}

	@Override
	public void olympicsResultsViewEvent(OlympicsModelable olympicsResult) {
		tableOlympicsRank.getItems().clear();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lblStartDateOlympics.setText("Start Date: " + olympicsResult.getStartDate().format(dtf));
		lblEndDateOlympics.setText("End Date: " + olympicsResult.getEndDate().format(dtf));
		lblNumOfTournaments.setText("Number Of Tournaments: " + olympicsResult.getNumOfTournaments());
		lblNumOfAthletes.setText("Number Of Athletes: " + olympicsResult.getNumOfAthletes());
		lblNumOfReferees.setText("Number Of Referees: " + olympicsResult.getNumOfReferees());
		lblNumOfStadiums.setText("Number Of Stadiums: " + olympicsResult.getNumOfStadiums());

		for (NationalTeam nationalTeam : olympicsResult.getAllNationalTeams()) {
			tableOlympicsRank.getItems().add(nationalTeam);
			rankCol.setCellFactory(col -> {
				TableCell<NationalTeam, Integer> cell = new TableCell<>();

				cell.textProperty().bind(Bindings.createStringBinding(() -> {
					return Integer.toString(cell.getIndex() + 1);
				}, cell.emptyProperty(), cell.indexProperty()));

				return cell;
			});
			numAthletesCol
					.setCellValueFactory(team -> new SimpleStringProperty("" + team.getValue().getNumOfAthletes()));
			OlympicsGoldCol.setCellValueFactory(
					team -> new SimpleStringProperty("" + team.getValue().getAllMedals().get(Medals.Gold)));
			OlympicsSilverCol.setCellValueFactory(
					team -> new SimpleStringProperty("" + team.getValue().getAllMedals().get(Medals.Silver)));
			OlympicsBronzeCol.setCellValueFactory(
					team -> new SimpleStringProperty("" + team.getValue().getAllMedals().get(Medals.Bronze)));
		}
	}

	@Override
	public void eceptionMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private boolean checkAthleteInputs() {
		clearAthleteErrors();
		boolean checkInputs = true;

		if (tfAthleteName.getText().isEmpty()) {
			errAthleteName.setVisible(true);
			checkInputs = false;
		}

		if (cmbAthleteCountry.getSelectionModel().isEmpty()) {
			errAthleteCountry.setVisible(true);
			checkInputs = false;
		}

		if (!chkRunner.isSelected() && !chkJumper.isSelected()) {
			errAthleteAbility.setVisible(true);
			checkInputs = false;
		}

		if (chkRunner.isSelected()) {
			if (tfMaxSpeed.getText().isEmpty()) {
				errMaxSpeed.setVisible(true);
				checkInputs = false;
			} else {
				try {
					@SuppressWarnings("unused")
					float maxSpeed = Float.parseFloat(tfMaxSpeed.getText());
				} catch (NumberFormatException e) {
					errMaxSpeedFloat.setVisible(true);
					checkInputs = false;
				}
			}
		}

		if (chkJumper.isSelected()) {
			if (tfMaxJump.getText().isEmpty()) {
				errMaxJump.setVisible(true);
				checkInputs = false;
			} else {
				try {
					@SuppressWarnings("unused")
					float maxJump = Float.parseFloat(tfMaxJump.getText());
				} catch (NumberFormatException e) {
					errMaxJumpFloat.setVisible(true);
					checkInputs = false;
				}
			}
		}

		return checkInputs;
	}

	private boolean checkRefereeInputs() {
		clearRefereeErrors();
		boolean checkInputs = true;

		if (tfRefereeName.getText().isEmpty()) {
			errRefereeName.setVisible(true);
			checkInputs = false;
		}

		if (cmbRefereeCountry.getSelectionModel().isEmpty()) {
			errRefereeCountry.setVisible(true);
			checkInputs = false;
		}

		if (cmbRefereeField.getSelectionModel().isEmpty()) {
			errRefereeField.setVisible(true);
			checkInputs = false;
		}

		return checkInputs;
	}

	private boolean checkStadiumInputs() {
		clearStadiumErrors();
		boolean checkInputs = true;

		if (tfStadiumName.getText().isEmpty()) {
			errStadiumName.setVisible(true);
			checkInputs = false;
		}

		if (cmbStadiumLocation.getSelectionModel().isEmpty()) {
			errStadiumLocation.setVisible(true);
			checkInputs = false;
		}

		if (tfStadiumCapacity.getText().isEmpty()) {
			errStadiumCapacity.setVisible(true);
			checkInputs = false;
		} else {
			try {
				@SuppressWarnings("unused")
				int capacity = Integer.parseInt(tfStadiumCapacity.getText());
			} catch (NumberFormatException e) {
				errStadiumCapacityInteger.setVisible(true);
				checkInputs = false;
			}
		}

		return checkInputs;
	}

	private boolean checkOlympicsInputs() {
		clearOlympicsErrors();
		boolean checkInputs = true;
		int numRunningIndividualTournaments = 0, numJumpingIndividualTournaments = 0, numRunningTeamTournaments = 0,
				numJumpingTeamTournaments = 0;
		if (!(tfRunningIndividualTournaments.getText().isEmpty())) {
			try {
				numRunningIndividualTournaments = Integer.parseInt(tfRunningIndividualTournaments.getText());
			} catch (NumberFormatException e) {
				errRunningIndividualTournaments.setVisible(true);
				checkInputs = false;
			}
		} else
			tfRunningIndividualTournaments.setText("0");

		if (!(tfJumpingIndividualTournaments.getText().isEmpty())) {
			try {
				numJumpingIndividualTournaments = Integer.parseInt(tfJumpingIndividualTournaments.getText());
			} catch (NumberFormatException e) {
				errJumpingIndividualTournaments.setVisible(true);
				checkInputs = false;
			}
		} else
			tfJumpingIndividualTournaments.setText("0");

		if (!(tfRunningTeamTournaments.getText().isEmpty())) {
			try {
				numRunningTeamTournaments = Integer.parseInt(tfRunningTeamTournaments.getText());
			} catch (NumberFormatException e) {
				errRunningTeamTournaments.setVisible(true);
				checkInputs = false;
			}
		} else
			tfRunningTeamTournaments.setText("0");

		if (!(tfJumpingTeamTournaments.getText().isEmpty())) {
			try {
				numJumpingTeamTournaments = Integer.parseInt(tfJumpingTeamTournaments.getText());
			} catch (NumberFormatException e) {
				errJumpingTeamTournaments.setVisible(true);
				checkInputs = false;
			}
		} else
			tfJumpingTeamTournaments.setText("0");

		if (numRunningIndividualTournaments == 0 && numJumpingIndividualTournaments == 0
				&& numRunningTeamTournaments == 0 && numJumpingTeamTournaments == 0) {
			errMakeOlympics.setVisible(true);
			checkInputs = false;
		}

		return checkInputs;
	}

	private void runnerOptions() {
		if (chkRunner.isSelected()) {
			lblMaxSpeed.setVisible(true);
			tfMaxSpeed.setVisible(true);
		} else {
			tfMaxSpeed.clear();
			lblMaxSpeed.setVisible(false);
			tfMaxSpeed.setVisible(false);
		}
	}

	private void jumperOptions() {
		if (chkJumper.isSelected()) {
			lblMaxJump.setVisible(true);
			tfMaxJump.setVisible(true);
		} else {
			tfMaxJump.clear();
			lblMaxJump.setVisible(false);
			tfMaxJump.setVisible(false);
		}
	}

	private void clearAthleteInputs() {
		btnAddAthlete.setVisible(true);
		btnUpdateAthlete.setVisible(false);
		btnDeleteAthlete.setVisible(false);
		tfAthleteName.clear();
		cmbAthleteCountry.getSelectionModel().clearSelection();
		chkRunner.setSelected(false);
		tfMaxSpeed.clear();
		runnerOptions();
		chkJumper.setSelected(false);
		tfMaxJump.clear();
		jumperOptions();

		btnAddAthlete.setVisible(true);
		btnUpdateAthlete.setVisible(false);
		btnDeleteAthlete.setVisible(false);
	}

	private void clearRefereeInputs() {
		tfRefereeName.clear();
		cmbRefereeCountry.getSelectionModel().clearSelection();
		cmbRefereeField.getSelectionModel().clearSelection();

		btnAddReferee.setVisible(true);
		btnUpdateReferee.setVisible(false);
		btnDeleteReferee.setVisible(false);
	}

	private void clearStadiumInputs() {
		tfStadiumName.clear();
		cmbStadiumLocation.getSelectionModel().clearSelection();
		tfStadiumCapacity.clear();

		btnAddStadium.setVisible(true);
		btnUpdateStadium.setVisible(false);
		btnDeleteStadium.setVisible(false);
	}

	private void clearOlympicsInputs() {
		tfRunningIndividualTournaments.clear();
		tfJumpingIndividualTournaments.clear();
		tfRunningTeamTournaments.clear();
		tfJumpingTeamTournaments.clear();
	}

	private void clearAthleteErrors() {
		errAthleteName.setVisible(false);
		errAthleteName.setTextFill(Color.RED);
		errAthleteCountry.setVisible(false);
		errAthleteCountry.setTextFill(Color.RED);
		errAthleteAbility.setVisible(false);
		errAthleteAbility.setTextFill(Color.RED);
		errMaxSpeed.setVisible(false);
		errMaxSpeed.setTextFill(Color.RED);
		errMaxSpeedFloat.setVisible(false);
		errMaxSpeedFloat.setTextFill(Color.RED);
		errMaxJump.setVisible(false);
		errMaxJump.setTextFill(Color.RED);
		errMaxJumpFloat.setVisible(false);
		errMaxJumpFloat.setTextFill(Color.RED);
	}

	private void clearRefereeErrors() {
		errRefereeName.setVisible(false);
		errRefereeName.setTextFill(Color.RED);
		errRefereeCountry.setVisible(false);
		errRefereeCountry.setTextFill(Color.RED);
		errRefereeField.setVisible(false);
		errRefereeField.setTextFill(Color.RED);
	}

	private void clearStadiumErrors() {
		errStadiumName.setVisible(false);
		errStadiumName.setTextFill(Color.RED);
		errStadiumLocation.setVisible(false);
		errStadiumLocation.setTextFill(Color.RED);
		errStadiumCapacity.setVisible(false);
		errStadiumCapacity.setTextFill(Color.RED);
		errStadiumCapacityInteger.setVisible(false);
		errStadiumCapacityInteger.setTextFill(Color.RED);
	}

	private void clearOlympicsErrors() {
		errRunningIndividualTournaments.setVisible(false);
		errRunningIndividualTournaments.setTextFill(Color.RED);
		errJumpingIndividualTournaments.setVisible(false);
		errJumpingIndividualTournaments.setTextFill(Color.RED);
		errRunningTeamTournaments.setVisible(false);
		errRunningTeamTournaments.setTextFill(Color.RED);
		errJumpingTeamTournaments.setVisible(false);
		errJumpingTeamTournaments.setTextFill(Color.RED);
		errMakeOlympics.setVisible(false);
		errMakeOlympics.setTextFill(Color.RED);
	}

}
