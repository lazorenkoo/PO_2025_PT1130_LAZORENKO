package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.MapChangeListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    private AbstractWorldMap worldMap;

    private static final int SQUARE_WIDTH = 30;
    private static final int BORDER_WIDTH = 2;
    private static final int BORDER_OFFSET = 1;

    private final List<Vector2d> initialPositions =
            List.of(new Vector2d(3, 7), new Vector2d(6, 6));

    @FXML public Canvas canvasMap;
    @FXML public Label moveInfoLabel;
    @FXML public TextField moveList;

    public void setWorldMap(AbstractWorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void drawMap() {
        Boundary boundary = worldMap.getCurrentBounds();
        Vector2d size = boundary.upperRight().subtract(boundary.lowerLeft());

        int columns = size.getX() + 2;
        int rows = size.getY() + 2;

        double totalWidth = columns * SQUARE_WIDTH + (columns + 1) * BORDER_WIDTH;
        double totalHeight = rows * SQUARE_WIDTH + (rows + 1) * BORDER_WIDTH;

        canvasMap.setWidth(totalWidth);
        canvasMap.setHeight(totalHeight);

        clearGrid();

        GraphicsContext graphics = canvasMap.getGraphicsContext2D();
        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(BORDER_WIDTH);

        double increment = SQUARE_WIDTH + BORDER_WIDTH;

        for (double x = BORDER_OFFSET; x <= totalWidth; x += increment) {
            graphics.strokeLine(x, 0, x, totalHeight);
        }
        for (double y = BORDER_OFFSET; y <= totalHeight; y += increment) {
            graphics.strokeLine(0, y, totalWidth, y);
        }

        drawCoordinates(graphics, boundary);
        drawMapContent(graphics, boundary);
    }

    private void drawCoordinates(GraphicsContext graphics, Boundary boundary) {
        graphics.setFill(Color.BLACK);
        graphics.setFont(new Font("Arial", SQUARE_WIDTH / 2.0));
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);

        double cellSize = SQUARE_WIDTH + BORDER_WIDTH;
        double halfCell = BORDER_WIDTH + (SQUARE_WIDTH / 2.0);

        graphics.fillText("y\\x", halfCell, halfCell);

        int mapWidth = boundary.upperRight().getX() - boundary.lowerLeft().getX() + 1;
        for (int i = 0; i < mapWidth; i++) {
            double x = halfCell + (i + 1) * cellSize;
            double y = halfCell;
            int coordValue = boundary.lowerLeft().getX() + i;
            graphics.fillText(String.valueOf(coordValue), x, y);
        }

        int mapHeight = boundary.upperRight().getY() - boundary.lowerLeft().getY() + 1;
        for (int i = 0; i < mapHeight; i++) {
            double x = halfCell;
            double y = halfCell + (i + 1) * cellSize;
            int coordValue = boundary.upperRight().getY() - i;
            graphics.fillText(String.valueOf(coordValue), x, y);
        }
    }

    private void drawMapContent(GraphicsContext graphics, Boundary boundary) {
        graphics.setFont(new Font("Arial", SQUARE_WIDTH * 0.7));
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);

        double cellSize = SQUARE_WIDTH + BORDER_WIDTH;
        double halfCell = BORDER_WIDTH + (SQUARE_WIDTH / 2.0);

        for (int x = boundary.lowerLeft().getX(); x <= boundary.upperRight().getX(); x++) {
            for (int y = boundary.lowerLeft().getY(); y <= boundary.upperRight().getY(); y++) {
                Object object = worldMap.objectAt(new Vector2d(x, y));
                if (object == null) continue;

                int visualCol = x - boundary.lowerLeft().getX() + 1;
                int visualRow = boundary.upperRight().getY() - y + 1;

                double pixelX = halfCell + visualCol * cellSize;
                double pixelY = halfCell + visualRow * cellSize;

                graphics.setFill(object.getClass() == Animal.class ? Color.RED : Color.GREEN);
                graphics.fillText(object.toString(), pixelX, pixelY);
            }
        }
    }

    private void clearGrid() {
        GraphicsContext graphics = canvasMap.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, canvasMap.getWidth(), canvasMap.getHeight());
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveInfoLabel.setText(message);
        });
    }

    @FXML
    public void onSimulationStartClicked(ActionEvent actionEvent) {
        worldMap = new GrassField("GUI-Grass", 10);
        setWorldMap(worldMap);
        worldMap.addObserver(this);

        List<MoveDirection> moves;
        try {
            String text = moveList.getText().trim();
            String[] tokens = text.isEmpty() ? new String[0] : text.split("\\s+");
            moves = OptionsParser.parse(tokens);
        } catch (IllegalArgumentException e) {
            canvasMap.setVisible(false);
            moveInfoLabel.setText(e.getMessage());
            return;
        }

        canvasMap.setVisible(true);

        Simulation simulation = new Simulation(worldMap, initialPositions, moves);

        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsyncInThreadPool();
    }
}