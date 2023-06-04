package ziro.com;

 
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        primaryStage = stage;

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.BLACK);
        
        Image logo = new Image(getClass().getResourceAsStream("/img/NugasKuy.png"));
        ImageView viewImage = new ImageView(logo);

        viewImage.setFitWidth(200);
        viewImage.setFitHeight(200);

        
        Text text = new Text("Kalkulator Coolyeah");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Button button = new Button("Masuk");
        button.setPrefWidth(120); // Memperbesar lebar tombol
        button.setPrefHeight(40); // Memperbesar tinggi tombol
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Mengubah ukuran font tombol
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); // Mengubah warna latar belakang dan teks tombol
        
        // Efek saat tombol ditekan
        button.setOnMousePressed(event -> {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.WHITE);
            button.setEffect(dropShadow);
        });
        
        // Efek saat tombol dilepas
        button.setOnMouseReleased(event -> {
            button.setEffect(null);
        });

        button.setOnMousePressed(event -> {
            showKalkulatorMenu(); // Memanggil method untuk menampilkan tampilan baru
        });

        VBox vBox = new VBox(viewImage, text, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        StackPane stackPane = new StackPane(vBox);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPrefSize(scene.getWidth(), scene.getHeight());
        
        root.getChildren().add(stackPane);
        stage.setResizable(false);
        stage.setScene(scene);;
        stage.show();
    }
    
    private void showKalkulatorMenu() {
        Stage menuStage = new Stage();
        menuStage.setTitle("Pilih Kalkulator");
        
        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setSpacing(20);
        menuBox.setPadding(new Insets(20));
        
        Text menuText = new Text("Pilih Kalkulator");
        menuText.setFill(Color.WHITE);
        menuText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Button bmiButton = new Button("Kalkulator BMI");
        bmiButton.setPrefWidth(200);
        bmiButton.setPrefHeight(40);
        bmiButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        bmiButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        bmiButton.setOnAction(event -> {
            showBMICalculator(menuStage); // Menampilkan kalkulator BMI saat tombol "Kalkulator BMI" ditekan
            menuStage.close();
        });
        
        Button kaloriButton = new Button("Kalkulator Kalori");
        kaloriButton.setPrefWidth(200);
        kaloriButton.setPrefHeight(40);
        kaloriButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        kaloriButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        kaloriButton.setOnAction(event -> {
            showKaloriCalculator(menuStage); // Menampilkan kalkulator Kalori saat tombol "Kalkulator Kalori" ditekan
            menuStage.close();
        });
        
        menuBox.getChildren().addAll(menuText, bmiButton, kaloriButton);
        menuBox.setStyle("-fx-background-color: black;");
        
        Scene menuScene = new Scene(menuBox, 400, 300, Color.BLACK);
        menuStage.setScene(menuScene);
        menuStage.show();
    }
    
    private void showBMICalculator(Stage previousStage) {
        Stage bmiStage = new Stage();
        bmiStage.setTitle("Kalkulator BMI");
        
        VBox bmiBox = new VBox();
        bmiBox.setAlignment(Pos.CENTER);
        bmiBox.setSpacing(20);
        bmiBox.setPadding(new Insets(20));
        
        Text bmiText = new Text("Kalkulator BMI");
        bmiText.setFill(Color.WHITE);
        bmiText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Label heightLabel = new Label("Tinggi (cm):");
        TextField heightField = new TextField();
        heightLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        heightLabel.setStyle("-fx-text-fill: white");

        Label weightLabel = new Label("Berat (kg):");
        weightLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        weightLabel.setStyle("-fx-text-fill: white");

        TextField weightField = new TextField();
        
        Button calculateButton = new Button("Hitung");
        calculateButton.setPrefWidth(120);
        calculateButton.setPrefHeight(40);
        calculateButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        calculateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        calculateButton.setOnAction(event -> {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double bmi = calculateBMI(height, weight);
            showBMIResult(bmi);
        });
        
        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(120);
        clearButton.setPrefHeight(40);
        clearButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        clearButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        clearButton.setOnAction(event -> {
            heightField.clear();
            weightField.clear();
        });
        
        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        backButton.setOnAction(event -> {
            bmiStage.close();
            previousStage.show();
        });
        
        bmiBox.getChildren().addAll(bmiText, heightLabel, heightField, weightLabel, weightField, calculateButton,
                clearButton, backButton);
        bmiBox.setStyle("-fx-background-color: black;");
        
        Scene bmiScene = new Scene(bmiBox, 400, 400, Color.BLACK);
        bmiStage.setScene(bmiScene);
        bmiStage.show();
        
        previousStage.close();
    }
    
    private void showKaloriCalculator(Stage previousStage) {
        Stage kaloriStage = new Stage();
        kaloriStage.setTitle("Kalkulator Kalori");
        
        VBox kaloriBox = new VBox();
        kaloriBox.setAlignment(Pos.CENTER);
        kaloriBox.setSpacing(20);
        kaloriBox.setPadding(new Insets(20));
        
        Text kaloriText = new Text("Kalkulator Kalori");
        kaloriText.setFill(Color.WHITE);
        kaloriText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Label ageLabel = new Label("Usia:");
        ageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        ageLabel.setStyle("-fx-text-fill: white");

        TextField ageField = new TextField();
        
        Label weightLabel = new Label("Berat (kg):");
        weightLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        weightLabel.setStyle("-fx-text-fill: white");

        TextField weightField = new TextField();
        
        Label heightLabel = new Label("Tinggi (cm):");
        heightLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        heightLabel.setStyle("-fx-text-fill: white");

        TextField heightField = new TextField();
        
        Button calculateButton = new Button("Hitung");
        calculateButton.setPrefWidth(120);
        calculateButton.setPrefHeight(40);
        calculateButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        calculateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        calculateButton.setOnAction(event -> {
            int age = Integer.parseInt(ageField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double kalori = calculateKalori(age, weight, height);
            showKaloriResult(kalori);
        });
        
        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(120);
        clearButton.setPrefHeight(40);
        clearButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        clearButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        clearButton.setOnAction(event -> {
            ageField.clear();
            weightField.clear();
            heightField.clear();
        });
        
        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        backButton.setOnAction(event -> {
            kaloriStage.close();
            previousStage.show();
        });
        
        kaloriBox.getChildren().addAll(kaloriText, ageLabel, ageField, weightLabel, weightField, heightLabel,
                heightField, calculateButton, clearButton, backButton);
        kaloriBox.setStyle("-fx-background-color: black;");
        
        Scene kaloriScene = new Scene(kaloriBox, 400, 400, Color.BLACK);
        kaloriStage.setScene(kaloriScene);
        kaloriStage.show();
        
        previousStage.close();
    }
    
    private double calculateBMI(double height, double weight) {
        // Rumus perhitungan BMI
        double heightInMeters = height / 100; // Konversi tinggi dari cm menjadi meter
        return weight / (heightInMeters * heightInMeters);
    }
    
    private void showBMIResult(double bmi) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Hasil BMI");
        
        VBox resultBox = new VBox();
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setSpacing(20);
        resultBox.setPadding(new Insets(20));
        
        Text resultText = new Text("Hasil BMI");
        resultText.setFill(Color.WHITE);
        resultText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Text bmiResultText = new Text("BMI: " + bmi);
        bmiResultText.setFill(Color.WHITE);
        bmiResultText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        backButton.setOnAction(event -> {
            resultStage.close();
            primaryStage.show();
        });
        
        resultBox.getChildren().addAll(resultText, bmiResultText, backButton);
        resultBox.setStyle("-fx-background-color: black;");
        
        Scene resultScene = new Scene(resultBox, 400, 300, Color.BLACK);
        resultStage.setScene(resultScene);
        resultStage.show();
    }
    
    private double calculateKalori(int age, double weight, double height) {
        // Rumus perhitungan kalori
        double bmr;
        if (age < 18) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
        return bmr;
    }
    
    private void showKaloriResult(double kalori) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Hasil Kalori");
        
        VBox resultBox = new VBox();
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setSpacing(20);
        resultBox.setPadding(new Insets(20));
        
        Text resultText = new Text("Hasil Kalori");
        resultText.setFill(Color.WHITE);
        resultText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Text kaloriResultText = new Text("Kalori: " + kalori);
        kaloriResultText.setFill(Color.WHITE);
        kaloriResultText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        backButton.setOnAction(event -> {
            resultStage.close();
            primaryStage.show();
        });
        
        resultBox.getChildren().addAll(resultText, kaloriResultText, backButton);
        resultBox.setStyle("-fx-background-color: black;");
        
        Scene resultScene = new Scene(resultBox, 400, 300, Color.BLACK);
        resultStage.setScene(resultScene);
        resultStage.show();
    }
}



