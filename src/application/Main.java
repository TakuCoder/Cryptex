package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import org.apache.commons.codec.digest.DigestUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	private static final String TAB_0 = "sss";
	private static final String TAB_01 = "Open Certificate";
	private static final String TAB_02 = "Select algorithm";
	private static final String TAB_03 = "Encrypt Data";
	private static final String msg = TAB_0;
	private static String filepath = "";
	private static String filepathoforiginaltext = "";
	private static int windowHeight = 660;
	private static int wnidowWidth = 400;
	private final SecureRandom random = new SecureRandom();

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Tabs");
		JFXTabPane tabPane = new JFXTabPane();

		Tab tab = new Tab();
		tab.setText(msg);
		tab.setContent(new Label(TAB_0));

		// tabPane.setPrefSize(1024, 768);

		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		tabPane.setMaxWidth(700);
		tabPane.setMinWidth(700);
		Tab tab1 = new Tab();
		tab1.setText(TAB_01);

		Tab tab2 = new Tab();
		tab2.setText(TAB_02);
		// tab2.setOnSelectionChanged(new EventHandler<Event>() {
		//
		// @Override
		// public void handle(Event event) {
		// System.out.println("sfasf");
		//
		// }
		// });
		Tab tab3 = new Tab();
		tab3.setText(TAB_03);
		// tabPane.getTabs().addAll(tab,tab2);
		Label label = new  Label("Load the keystore Certificate");
		Label label1 = new Label("Enter the Alias Password,      \n"
				               + "KeyStore and Key password");
		Label label2 = new Label("Decrypt the Certificate to \n"
				+ "calculate the fingerprints");
		Label label3 = new Label("Step 3. Select any hash algorithm as a key");
		label.getStyleClass().add("label");
		JFXButton Browse = new JFXButton("Browse");
		Browse.getStyleClass().add("button-raised");

		JFXTextField aliasname = new JFXTextField();
		JFXTextField keystorepass = new JFXTextField();
		JFXTextField keypassword = new JFXTextField();
		JFXButton selectedalgorithm = new JFXButton();
		selectedalgorithm.getStyleClass().add("button-raised");

		aliasname.setPromptText("Enter alias Name");
		keystorepass.setPromptText("Enter keystore Password");
		keypassword.setPromptText("Enter key password");

		aliasname.getStyleClass().add("textfield");
		keystorepass.getStyleClass().add("textfield");
		keypassword.getStyleClass().add("textfield");

		VBox vboxforthreefields = new VBox();
		vboxforthreefields.getChildren().addAll(aliasname, keystorepass, keypassword);

		VBox vbox = new VBox();

		HBox hBox = new HBox();
		// hBox.
		hBox.getChildren().addAll(label, Browse);
		hBox.setSpacing(30);
		vbox.getChildren().addAll(hBox);

		HBox secondHbox = new HBox();
		VBox vboxforalias = new VBox();
		vboxforalias.getChildren().addAll(aliasname, keystorepass, keypassword);
		vboxforalias.setSpacing(30);
		secondHbox.getChildren().addAll(label1, vboxforalias);

		secondHbox.setSpacing(30);
		VBox vBoxxx = new VBox();
		JFXButton buttonn = new JFXButton();
		buttonn.setText("Decrypt");
		HBox hbowxthree = new HBox();
		hbowxthree.setSpacing(50);
		buttonn.getStyleClass().add("button-raised");
		;
		hbowxthree.getChildren().addAll(label2, buttonn);

		selectedalgorithm.setText("select any algorithm as key");

		VBox hboxfour = new VBox();
		hboxfour.setSpacing(30);

		JFXRadioButton md5 = new JFXRadioButton();
		JFXRadioButton sha = new JFXRadioButton();
		JFXRadioButton sha256 = new JFXRadioButton();

		Label md5label = new Label();
		Label shalabel = new Label();
		Label sha256label = new Label();

		HBox vBox2 = new HBox();
		HBox vBox21 = new HBox();
		HBox vBox22 = new HBox();

		vBox2.getChildren().addAll(md5, md5label);
		vBox21.getChildren().addAll(sha, shalabel);
		vBox22.getChildren().addAll(sha256, sha256label);

		hboxfour.getChildren().addAll(vBox2, vBox21, vBox22, selectedalgorithm);
		hboxfour.setPadding(new Insets(10));

		vBoxxx.getChildren().addAll(vbox, secondHbox, hbowxthree);
		vBoxxx.setPadding(new Insets(10));
		vBoxxx.setSpacing(20);
		tab1.setContent(vBoxxx);
		// second tab

		
		VBox hboxxxx = new VBox();
		hboxxxx.setSpacing(20);
	

		Label LabelForKey = new Label("key");
	
		Label LabelKeyData = new Label();
		JFXButton BrowseDataFile = new JFXButton();
		
		BrowseDataFile.getStyleClass().add("button-raised");
		BrowseDataFile.setText("Browse the data file");

		JFXButton Encrypt = new JFXButton();
		Encrypt.getStyleClass().add("button-raised");
		Encrypt.setText("Encrypt the file and Generate Class files");
		String notes = "First 16 digit(128 bits) of the hash value will be used as key and it is used to encrypt \n"
				+ "the textfile which is having sensitive data in your android application";
		Label LabelForNote = new Label(notes);

		// vboxforkey.setSpacing(30);
		hboxxxx.getChildren().addAll(LabelForKey, LabelKeyData, LabelForNote, BrowseDataFile, Encrypt);

		tab3.setContent(hboxxxx);

		tabPane.getTabs().addAll(tab1, tab2, tab3);

		// SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		// selectionModel.select(1);
		Encrypt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event)

			{

				System.out.println(filepathoforiginaltext);

				String key = LabelKeyData.getText().substring(0, 16);
				System.out.println("this is my key" + key);
				File inputFile = new File(filepathoforiginaltext);
				String encrypted = null;

				try {
					StringBuilder sb = new StringBuilder();
					BufferedReader bf = new BufferedReader(new FileReader(inputFile));
					String line;

					while ((line = bf.readLine()) != null) {

						sb.append(line);

					}

					bf.close();
					System.out.println(sb.toString());

					AesEnc aesenc = new AesEnc();
					encrypted = aesenc.encrypt(sb.toString(),key.getBytes());
					System.out.println("encrypted TExt  " + encrypted);

				}

				catch (Exception e) {

					System.out.println(e.toString());

				}

				String userHomeFolder1 = System.getProperty("user.home") + "\\Desktop\\testlocation";
				String userHomeFolder = userHomeFolder1 + "\\text.encrypted";
				System.out.println(userHomeFolder);

				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(userHomeFolder));
					writer.write(encrypted);
					writer.close();
					Runtime.getRuntime().exec("explorer " + userHomeFolder1);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// File encryptedFile = new
				// File("C:\\Users\\thiyagu\\Desktop\\testlocation\\text.encrypted");
				// File decryptedFile = new
				// File("C:\\Users\\thiyagu\\Desktop\\testlocation\\decrypted-text.txt");
				//
				// try
				// {
				// Crypto.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
				// Crypto.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
				// System.out.println("Success");
				// } catch (Exception ex)
				// {
				// System.out.println(ex.getMessage());
				// ex.printStackTrace();
				// }

			}
		});
		selectedalgorithm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				selectionModel.select(2);
				if (sha.isSelected() == true) {

					System.out.println("selected sha");

					System.out.println("selected sha256");
					String val = sha.getText().replaceAll(":", "");
					System.out.println(val + "test");
					val = val.replaceAll("SHA", "");
					LabelKeyData.setText(val.trim().substring(0,16));

				} else if (sha256.isSelected() == true) {

					System.out.println("selected sha256");
					String val = sha256.getText().replaceAll(":", "");
					System.out.println(val + "test");
					val = val.replaceAll("SHA256", "");
					LabelKeyData.setText(val.trim().substring(0,16));

				}
				if (md5.isSelected() == true) {

					System.out.println("selected md5");
					String val = md5.getText().replaceAll(":", "");
					System.out.println(val + "test");
					val = val.replaceAll("MD5", "");
					LabelKeyData.setText(val.trim().substring(0,16));
				}
			}
		});
		md5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("MD5");
				sha.setSelected(false);
				sha256.setSelected(false);
				selectedalgorithm.setText("Use MD5 as Key");

			}
		});
		sha.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("sha");
				md5.setSelected(false);
				sha256.setSelected(false);
				selectedalgorithm.setText("Use sha as Key");

			}
		});
		sha256.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("sha256");
				sha.setSelected(false);
				md5.setSelected(false);
				selectedalgorithm.setText("Use sha256 as Key");

			}
		});

		tabPane.setMaxWidth(500);

		HBox hbox = new HBox();
		hbox.getChildren().addAll(tabPane);
		hbox.setSpacing(50);
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-padding:20");

		Group root = new Group();
		Scene scene = new Scene(root, windowHeight, wnidowWidth);
		root.getChildren().addAll(hbox);
		// scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-components.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		buttonn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					FileInputStream is = new FileInputStream(filepath);

					KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
					keystore.load(is, keypassword.getText().toCharArray());

					String alias = aliasname.getText();

					Key key = keystore.getKey(alias, keystorepass.getText().toCharArray());
					if (key instanceof PrivateKey)

					{
						// Get certificate of public key
						Certificate cert = keystore.getCertificate(alias);

						String md5val = (DigestUtils.md5Hex(cert.getEncoded()).toUpperCase());
						String sha1val = (DigestUtils.sha1Hex(cert.getEncoded()).toUpperCase());
						String sha256val = (DigestUtils.sha256Hex(cert.getEncoded()).toUpperCase());

						md5.setText("MD5 : " + md5val);
						sha.setText("SHA : " + sha1val);
						sha256.setText("SHA256 : " + sha256val);
						tab2.setContent(hboxfour);
						selectionModel.select(1);
					}

				}

				catch (Exception e) {

				}

			}
		});

		BrowseDataFile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				File file;
				FileChooser fileChooser = new FileChooser();

				fileChooser.setTitle("Select the text file to encrypt");
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
				fileChooser.getExtensionFilters().addAll(

						new FileChooser.ExtensionFilter("TXT", "*.txt")

				);
				file = fileChooser.showOpenDialog(primaryStage);
				System.out.println(file);
				BrowseDataFile.getStyleClass().add("buttonok");
				BrowseDataFile.setText("Text file loaded");
				filepathoforiginaltext = file.getAbsolutePath();

			}
		});

		Browse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File file;
				FileChooser fileChooser = new FileChooser();

				fileChooser.setTitle("View Pictures");
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
				fileChooser.getExtensionFilters().addAll(

						new FileChooser.ExtensionFilter("JKS", "*.JKS")

				);
				file = fileChooser.showOpenDialog(primaryStage);
				System.out.println(file);
				Browse.getStyleClass().add("buttonok");
				Browse.setText("Cert Loaded");
				filepath = file.getAbsolutePath();
				System.out.println(filepath);

			}

		});

	
		primaryStage.setTitle("JFX Tabs Demo");
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println(newVal);
		});

		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
			// Do whatever you want
			System.out.println(newVal);
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}

