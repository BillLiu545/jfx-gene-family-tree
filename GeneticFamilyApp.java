

import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn.*;
import javafx.application.*;
import javafx.scene.chart.*;
public class GeneticFamilyApp extends Application
{
    private final GeneticFamily family = new GeneticFamily();
    public void start(Stage mainStage)
    {
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 1200, 400);
        mainStage.setScene(mainScene);
        mainStage.setTitle("Genetic Family Tree");
        VBox mainLayout = new VBox();
        mainLayout.setSpacing(20);
        root.setCenter(mainLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label label = new Label(family.toString());
        label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        label.setTextFill(Color.DARKGREEN);
        mainLayout.getChildren().add(label);
        
        HBox buttonRow = new HBox();
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setStyle("-fx-font-size: 15");
        buttonRow.setSpacing(10);
        mainLayout.getChildren().add(buttonRow);
        
        Button addButton = new Button("Add Family Member");
        addButton.setOnAction((event)->
        {
            family.addMember();
            label.setText(family.toString());
        });
        Button removeButton = new Button("Remove Family Member");
        removeButton.setOnAction((event)->
        {
            family.remove_input();
            label.setText(family.toString());
        });
        buttonRow.getChildren().addAll(addButton, removeButton);
        
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15;");
        root.setTop(topMenu);
        
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        MenuItem mutateItem = new MenuItem("Mutate a Family Member");
        mutateItem.setOnAction((e->
        {
            family.mutate_input();
            label.setText(family.toString());
        }));
        
        MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction((e->
        {
            family.clear();
            label.setText(family.toString());
        }));
        
        MenuItem compareItem = new MenuItem("Compare Genetic Composition");
        compareItem.setOnAction((e->
        {
            String s = family.relation_input();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Comparison Info");
            alert.setHeaderText("Comparison Info");
            alert.setContentText(s);
            alert.showAndWait();
        }));
        
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
            System.exit(0);
        }));
        
        fileMenu.getItems().addAll(mutateItem, compareItem, resetItem, quitItem);
        
        mainStage.show();
    }
}
