import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TipCalculator extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Tip Calculator");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label billLabel = new Label("Bill Amount:");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage:");
        TextField tipField = new TextField();
        tipField.setPromptText("Enter tip %");

        Button calcButton = new Button("Calculate Tip");
        Button resetButton = new Button("Reset");
        HBox buttonBox = new HBox(10, calcButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER);

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        calcButton.setOnAction(e -> {
            try {
                double bill = Double.parseDouble(billField.getText());
                double tipPercent = Double.parseDouble(tipField.getText());
                if (bill < 0 || tipPercent < 0) throw new NumberFormatException();
                double tipAmount = bill * tipPercent / 100;
                resultLabel.setText(String.format("Tip Amount: $%.2f", tipAmount));
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Error: Please enter valid numeric values");
                resultLabel.setText("");
            }
        });

        resetButton.setOnAction(e -> {
            billField.clear();
            tipField.clear();
            resultLabel.setText("");
            errorLabel.setText("");
        });

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(titleLabel, billLabel, billField, tipLabel, tipField, buttonBox, resultLabel, errorLabel);

        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Tip Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
