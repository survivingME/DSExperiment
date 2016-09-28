/* 我真诚地保证：
我自己独立地完成了整个程序从分析、设计到编码的所有工作。
如果在上述过程中，我遇到了什么困难而求教于人，那么，我将在程序实习报告中
详细地列举我所遇到的问题，以及别人给我的提示。
在此，我感谢 XXX, …, XXX对我的启发和帮助。下面的报告中，我还会具体地提到
他们在各个方法对我的帮助。
我的程序里中凡是引用到其他程序或文档之处，
例如教材、课堂笔记、网上的源代码以及其他参考书上的代码段,
我都已经在程序的注释里很清楚地注明了引用的出处。
我从未没抄袭过别人的程序，也没有盗用别人的程序，
不管是修改式的抄袭还是原封不动的抄袭。
我编写这个程序，从来没有想过要去破坏或妨碍其他计算机系统的正常运转。
付尧*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

/**
 * Created by Tremble on 2016/9/4.
 */
public class view extends Application {
    private static final long serialVersionUID = 0;
    private Label Input = new Label("Input:");
    private Label Output = new Label("Output:");
    private TextField input = new TextField("请输入算式");
    TextField output = new TextField("这里将出现结果");
    private Button run = new Button("Run!");
    private Button reset = new Button("Reset");
    @Override
    public void start(Stage primaryStage) {
        DropShadow shadow = new DropShadow();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(7);
        pane.setVgap(5.5);
        pane.add(Input, 0, 0);
        pane.add(input, 1, 0);
        pane.add(run, 2, 0);
        run.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            run.setEffect(shadow);
        });
        run.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            run.setEffect(null);
        });
        reset.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            reset.setEffect(shadow);
        });
        reset.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            reset.setEffect(null);
        });
        pane.add(Output, 0, 1);
        output.setEditable(false);
        pane.add(output, 1, 1);
        pane.add(reset, 2, 1);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        reset.setOnAction(ActionEvent -> {
            output.setText("这里将出现结果");
            input.setText("请输入算式");
        });
        run.setOnAction(ActionEvent -> {
            output.setText(new calculator(new filter(input.getText()).filterFormula()).calculate());
        });

        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}