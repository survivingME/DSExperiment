import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.Thread;
/**
 * Created by Tremble on 2016/9/23.
 */
public class view extends Application{
    private static final long serialVersionID = 0;
    private Label tips = new Label("请输入共有几位皇后：");
    private Label result = new Label("结果将显示在这里:          ");
    private Label time = new Label("记录耗时:  ");
    private TextField input = new TextField();
    private Button run = new Button("Run!");
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(4);
        pane.setVgap(5);
        pane.add(tips, 0, 0);
        pane.add(input, 1, 0);
        pane.add(run, 2, 0);
        pane.add(result, 0, 2);
        pane.add(time, 1, 2);
        input.setPromptText("x>=8");
        input.setFocusTraversable(false);
        run.setOnAction(ActionEvent-> {
            if(Integer.parseInt(input.getText()) >= 8) {
                long start = System.currentTimeMillis();
                eight_queen instance = new eight_queen(Integer.parseInt(input.getText()));
                instance.eight_queen(0);
                result.setText("已找到"+instance.getResultCount()+"种方案");
                long end = System.currentTimeMillis();
                time.setText(String.valueOf("耗时:"+(end-start)/1000.0)+"s");
                try {
                    Runtime.getRuntime().
                            exec("cmd /c start C:\\Users\\Tremble\\Desktop\\DSExperiment\\DSE2-eightQueen\\queen.txt");
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Queen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
