/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.samples;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UndecoratedMaximizeDemo2 extends Application
{

    private XYWH maximizedXywh;

    private XYWH originalXywh;

    private boolean maximized = false;

    @Override
    public void start(Stage stage) throws Exception
    {
        maximizedXywh = getMaximizedWH();
        Button maximize = new Button("Toggle Maximize");
        maximize.setOnAction(e -> {
            toggleMaximize(stage);
        });
        BorderPane root = new BorderPane(maximize);
        root.setPrefSize(400, 400);
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static XYWH getMaximizedWH()
    {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        return new XYWH(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
    }

    private void toggleMaximize(Stage stage)
    {
        if (maximized) {
            //restore
            stage.setX(originalXywh.x);
            stage.setY(originalXywh.y);
            stage.setWidth(originalXywh.w);
            stage.setHeight(originalXywh.h);
            originalXywh = null;
        } else {
            originalXywh = new XYWH(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            //maximize
            stage.setX(maximizedXywh.x);
            stage.setY(maximizedXywh.y);
            stage.setWidth(maximizedXywh.w);
            stage.setHeight(maximizedXywh.h);
        }
        maximized = !maximized;
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static class XYWH
    {
        double x;

        double y;

        double w;

        double h;

        public XYWH(double x, double y, double w, double h)
        {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

    }

}
