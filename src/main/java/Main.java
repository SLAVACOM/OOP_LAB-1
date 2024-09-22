import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private JFrame frame = new JFrame("Лабораторная работа №1");

    private JButton bBack = new JButton("Назад");

    private JButton bLine = new JButton("Линия");
    private JButton bCircle = new JButton("Окружность");
    private JButton bRectangle = new JButton("Прямоугольник");
    private JButton bTriangle = new JButton("Треугольник");

    private JButton bCreate = new JButton("Создать");
    JButton bCreateSomeObject = new JButton("Создать несколько объектов");
    private JButton bMove = new JButton("Переместить");

    private JButton bChangeColorAll = new JButton("Изменить цвет всех элементов");
    private JButton bDelete = new JButton("Удалить");
    private JButton bDeleteAll = new JButton("Удалить все");

    private JButton bChangeRadius = new JButton("Изменить радиус");

    private JButton bSetVisible = new JButton("Сделать видимым/невидимым");

    private JButton bChangeSize = new JButton("Изменить размер");
    private JButton bRotate = new JButton("Повернуть");

    private JComboBox<String> figureSelector = new JComboBox<>();
    private JPanel centerPanel = createCenterPanel();
    private JPanel southPanel = createSouthPanel();

    private List<Line> lines = new ArrayList<>();
    private List<Circle> circles = new ArrayList<>();
    private List<Rectangle> rectangles = new ArrayList<>();
    private List<Triangle> triangles = new ArrayList<>();

    private int selectedFigureType;
    private int selectedIndex = -1;

    public Main() {
        frame.setLayout(new BorderLayout());
        frame.setSize(CONSTANTS.WITH, CONSTANTS.HEIGHT);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        return panel;
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.GRAY);
        panel.add(bLine);
        panel.add(bCircle);
        panel.add(bRectangle);
        panel.add(bTriangle);
        panel.add(bDeleteAll);

        bLine.addActionListener(e -> switchToFigurePanel(1));
        bCircle.addActionListener(e -> switchToFigurePanel(2));
        bRectangle.addActionListener(e -> switchToFigurePanel(3));
        bTriangle.addActionListener(e -> switchToFigurePanel(4));

        bBack.addActionListener(e -> returnToMainPanel());
        bCreate.addActionListener(e -> createFigure());
        bMove.addActionListener(e -> moveSelectedFigure());
        bDelete.addActionListener(e -> deleteSelectedFigure());
        bDeleteAll.addActionListener(e -> deleteAllFigures());
        bSetVisible.addActionListener(e -> toggleVisibilitySelectedFigure());
        bChangeRadius.addActionListener(e -> changeRadius());

        figureSelector.addActionListener(e -> selectedIndex = figureSelector.getSelectedIndex());

        bCreateSomeObject.addActionListener(e -> {
            int count = Integer.parseInt(JOptionPane.showInputDialog("Введите количество окружностей:"));
            createMultipleObject(count);
        });

        bChangeColorAll.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Выберите новый цвет", Color.GREEN);
            changeColorOfAllObjects(newColor);
        });

        return panel;
    }

    private void switchToFigurePanel(int figureType) {
        southPanel.removeAll();
        southPanel.add(bBack);
        southPanel.add(bCreate);
        southPanel.add(bMove);
        southPanel.add(bDelete);
        southPanel.add(bDeleteAll);
        southPanel.add(bSetVisible);
        southPanel.add(figureSelector);

        southPanel.add(bCreateSomeObject);
        southPanel.add(bChangeColorAll);
        switch (figureType) {
            case 1 -> {
                southPanel.add(bRotate);
                selectedFigureType = 1;
                bRotate.addActionListener(e -> rotateSelectedFigure());
            }
            case 2 -> {
                southPanel.add(bChangeRadius);
                selectedFigureType = 2;
            }
            case 3 -> {
                southPanel.add(bChangeSize);
                selectedFigureType = 3;
                bChangeSize.addActionListener(e -> changeSizeOfSelectedFigure());

            }
            case 4 -> {
                southPanel.add(bChangeSize);
                southPanel.add(bRotate);
                selectedFigureType = 4;
                bRotate.addActionListener(e -> rotateSelectedFigure());
                bChangeSize.addActionListener(e -> changeSizeOfSelectedFigure());
            }
        }
        updateFigureSelector();
        southPanel.revalidate();
        southPanel.repaint();
    }

    private void changeColorOfAllObjects(Color color) {
        switch (selectedFigureType) {
            case 1 -> {
                for (Line line : lines) line.setColor(color);
            }
            case 2 -> {
                for (Circle circle : circles) circle.setColor(color);
            }
            case 3 -> {
                for (Rectangle rectangle : rectangles) rectangle.setColor(color);
            }
            case 4 -> {
                for (Triangle triangle : triangles) triangle.setColor(color);
            }
        }
    }


    private void createMultipleObject(int count) {
        for (int i = 0; i < count; i++) {
            int x1 = (int) (Math.random() * CONSTANTS.WITH);
            int x2 = (int) (Math.random() * CONSTANTS.WITH);
            int x3 = (int) (Math.random() * CONSTANTS.WITH);


            int y1 = (int) (Math.random() * CONSTANTS.HEIGHT);
            int y2 = (int) (Math.random() * CONSTANTS.HEIGHT);
            int y3 = (int) (Math.random() * CONSTANTS.HEIGHT);
            switch (selectedFigureType) {
                case 1 -> {
                    Line line = new Line(x1, y1, x2, y2, Color.RED);
                    lines.add(line);
                    centerPanel.add(line);
                }
                case 2 -> {
                    Circle circle = new Circle(x1, y2, (int) (Math.random() * 300), Color.GREEN);
                    circles.add(circle);
                    centerPanel.add(circle);
                }
                case 3 -> {
                    Rectangle rectangle = new Rectangle(x1, y1, x2, y2, Color.blue);
                    rectangles.add(rectangle);
                    centerPanel.add(rectangle);
                }
                case 4 -> {
                    Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3, Color.black);
                    triangles.add(triangle);
                    centerPanel.add(triangle);
                }
            }
        }
        centerPanel.revalidate();
        centerPanel.repaint();
        updateFigureSelector();
    }

    private void changeSizeOfSelectedFigure() {
        if (selectedIndex >= 0) {
            switch (selectedFigureType) {
                case 3 -> {
                    int newWidth = Integer.parseInt(JOptionPane.showInputDialog("Введите новую ширину:"));
                    int newHeight = Integer.parseInt(JOptionPane.showInputDialog("Введите новую высоту:"));
                    rectangles.get(selectedIndex).resize(newWidth, newHeight);
                }
                case 4 -> {
                    double resize = Double.parseDouble(JOptionPane.showInputDialog("Введите во сколько раз изменить размер:"));
                    triangles.get(selectedIndex).resize(resize);
                }
            }
            centerPanel.repaint();
        } else JOptionPane.showMessageDialog(frame, "Выберите фигуру для изменения размера");
    }

    private void returnToMainPanel() {
        southPanel.removeAll();
        southPanel.add(bLine);
        southPanel.add(bCircle);
        southPanel.add(bRectangle);
        southPanel.add(bTriangle);
        southPanel.add(bDeleteAll);
        southPanel.revalidate();
        southPanel.repaint();
        selectedFigureType = 0;
    }

    private void createFigure() {
        int x1 = (int) (Math.random() * CONSTANTS.WITH);
        int x2 = (int) (Math.random() * CONSTANTS.WITH);
        int x3 = (int) (Math.random() * CONSTANTS.WITH);

        int r = (int) (Math.random() * 300);

        int y1 = (int) (Math.random() * CONSTANTS.HEIGHT);
        int y2 = (int) (Math.random() * CONSTANTS.HEIGHT);
        int y3 = (int) (Math.random() * CONSTANTS.HEIGHT);


        switch (selectedFigureType) {
            case 1 -> {
                Line line = new Line(x1, y1, x2, y2, Color.RED);
                lines.add(line);
                centerPanel.add(line);
            }
            case 2 -> {
                Circle circle = new Circle(x1, y1, r, Color.GREEN);
                circles.add(circle);
                centerPanel.add(circle);
            }
            case 3 -> {
                Rectangle rectangle = new Rectangle(x1, y1, x2, y2, Color.BLUE);
                rectangles.add(rectangle);
                centerPanel.add(rectangle);
            }
            case 4 -> {
                Triangle triangle = new Triangle(x1, y1, x2, y1, x3, y3, Color.BLACK);
                triangles.add(triangle);
                centerPanel.add(triangle);
            }
        }
        centerPanel.revalidate();
        centerPanel.repaint();
        updateFigureSelector();
    }

    private void updateFigureSelector() {
        figureSelector.removeAllItems();
        String[] figureList = getFigureList();
        for (String name : figureList) {
            figureSelector.addItem(name);
        }
    }

    private String[] getFigureList() {
        List<String> figureNames = new ArrayList<>();
        switch (selectedFigureType) {
            case 1 -> {
                for (int i = 0; i < lines.size(); i++) {
                    figureNames.add(lines.get(i).isVisible() ? "Линия " + (i + 1) : "Линия " + (i + 1) + " (скрыта)");
                }
            }
            case 2 -> {
                for (int i = 0; i < circles.size(); i++) {
                    figureNames.add(circles.get(i).isVisible() ? "Окружность " + (i + 1) : "Окружность " + (i + 1) + " (скрыта)");
                }
            }
            case 3 -> {
                for (int i = 0; i < rectangles.size(); i++)
                    figureNames.add(rectangles.get(i).isVisible() ? "Прямоугольник " + (i + 1) : "Прямоугольник " + (i + 1) + " (скрыт)");
            }
            case 4 -> {
                for (int i = 0; i < triangles.size(); i++)
                    figureNames.add(triangles.get(i).isVisible() ? "Треугольник " + (i + 1) : "Треугольник " + (i + 1) + " (скрыт)");
            }
        }
        return figureNames.toArray(new String[0]);
    }

    private void moveSelectedFigure() {
        int dx = Integer.parseInt(JOptionPane.showInputDialog("Введите на сколько переместить по координате Х:"));
        int dy = Integer.parseInt(JOptionPane.showInputDialog("Введите на сколько переместить по координате Y:"));

        if (selectedIndex >= 0) {
            switch (selectedFigureType) {
                case 1 -> lines.get(selectedIndex).moveTo(dx, dy);
                case 2 -> circles.get(selectedIndex).moveTo(dx, dy);
                case 3 -> rectangles.get(selectedIndex).moveTo(dx, dy);
                case 4 -> triangles.get(selectedIndex).moveTo(dx, dy);
            }
            centerPanel.repaint();
        } else JOptionPane.showMessageDialog(frame, "Выберите фигуру для перемещения");
    }

    private void deleteSelectedFigure() {
        if (selectedIndex >= 0) {
            switch (selectedFigureType) {
                case 1 -> centerPanel.remove(lines.remove(selectedIndex));
                case 2 -> centerPanel.remove(circles.remove(selectedIndex));
                case 3 -> centerPanel.remove(rectangles.remove(selectedIndex));
                case 4 -> centerPanel.remove(triangles.remove(selectedIndex));
            }
            selectedIndex = -1;
            centerPanel.revalidate();
            centerPanel.repaint();
            updateFigureSelector();
        } else JOptionPane.showMessageDialog(frame, "Выберите фигуру для удаления");
    }

    private void deleteAllFigures() {
        System.out.println(selectedFigureType);
        switch (selectedFigureType) {
            case 1 -> {
                for (Line line: lines) centerPanel.remove(line);
                lines.clear();
            }
            case 2 -> {
                for (Circle circle: circles) centerPanel.remove(circle);
                circles.clear();
            }
            case 3 -> {
                for (Rectangle rectangle: rectangles) centerPanel.remove(rectangle);
                rectangles.clear();
            }
            case 4 -> {
                for (Triangle triangle: triangles) centerPanel.remove(triangle);
                triangles.clear();
            }
            case 0 -> {
                lines.clear();
                circles.clear();
                rectangles.clear();
                triangles.clear();
                centerPanel.removeAll();
            }
        }

        centerPanel.revalidate();
        centerPanel.repaint();
        updateFigureSelector();
    }

    private void toggleVisibilitySelectedFigure() {
        if (selectedIndex >= 0) {
            switch (selectedFigureType) {
                case 1 -> {
                    boolean newVisibility = !lines.get(selectedIndex).isVisible();
                    lines.get(selectedIndex).show(newVisibility);
                }
                case 2 -> {
                    boolean newVisibility = !circles.get(selectedIndex).isVisible();
                    circles.get(selectedIndex).show(newVisibility);
                }
                case 3 -> {
                    boolean newVisibility = !rectangles.get(selectedIndex).isVisible();
                    rectangles.get(selectedIndex).show(newVisibility);
                }
                case 4 -> {
                    boolean newVisibility = !triangles.get(selectedIndex).isVisible();
                    triangles.get(selectedIndex).show(newVisibility);
                }
            }
            updateFigureSelector();
            centerPanel.repaint();
        } else JOptionPane.showMessageDialog(frame, "Выберите фигуру для изменения видимости");
    }

    private void changeRadius() {
        if (selectedIndex >= 0 && selectedFigureType == 2) {
            int newRadius = Integer.parseInt(JOptionPane.showInputDialog("Введите новый радиус:"));
            circles.get(selectedIndex).setRadius(newRadius);
            centerPanel.repaint();
        } else JOptionPane.showMessageDialog(frame, "Выберите окружность для изменения радиуса");
    }

    private void rotateSelectedFigure() {
        if (selectedIndex >= 0) {
            int angle = Integer.parseInt(JOptionPane.showInputDialog("Введите на какой угол повернуть:"));
            switch (selectedFigureType) {
                case 1 -> lines.get(selectedIndex).rotate(angle);
                case 4 -> triangles.get(selectedIndex).rotate(angle);
            }
            centerPanel.repaint();
        } else JOptionPane.showMessageDialog(frame, "Выберите фигуру для поворота");
    }

    public static void main(String[] argc) {
        SwingUtilities.invokeLater(Main::new);
    }
}