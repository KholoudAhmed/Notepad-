
import static java.awt.SystemColor.text;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Notepad extends Application {

    private BorderPane pane;

    @Override
    public void init() {
        MenuBar menuebar = new MenuBar();

        Menu file = new Menu("FILE");
        Menu edit = new Menu("EDIT");
        Menu view = new Menu("VIEW");
        Menu help = new Menu("HELP");

        MenuItem New = new MenuItem("New");
        New.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");
        New.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        MenuItem Exit = new MenuItem("Exit");

        MenuItem undo = new MenuItem("Undo");
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        MenuItem delete = new MenuItem("Delete");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem select_all = new MenuItem("Select all");

        MenuItem about = new MenuItem("AboutNotepad");

        file.getItems().addAll(New, Open, Save, separator1, Exit);
        edit.getItems().addAll(undo, cut, copy, paste, delete, separator, select_all);
        help.getItems().addAll(about);

        menuebar.getMenus().addAll(file, edit, view, help);

        TextArea txtarea = new TextArea();

        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.clear();
//                FindTxt.setText("");
            }
        });
        cut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.cut();
            }
        });
        copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.copy();
            }
        });
        paste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.paste();
            }
        });
        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.undo();
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.deleteText(txtarea.getSelection());
            }
        });
        select_all.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtarea.selectAll();
            }
        });
        Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files", "*.txt");
                File savedFile = fc.showSaveDialog(null);
                try {
                    FileWriter fw = new FileWriter(savedFile);
                    fw.write(txtarea.getText());
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileChooser fc = new FileChooser();
                    FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files", "*.txt");
                    File opened_file = fc.showOpenDialog(null);
                    Scanner scan = new Scanner(opened_file);
                    String fileContent = "";
                    while (scan.hasNext()) {
                        txtarea.appendText(scan.nextLine() + '\n');
//                          fileContent = fileContent.concat(scan.nextLine()+"\n");
                    }
                    scan.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        pane = new BorderPane();
        pane.setTop(menuebar);
        pane.setCenter(txtarea);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent pane = null;
        Scene scene = new Scene(pane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
