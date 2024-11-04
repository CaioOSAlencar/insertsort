import javax.swing.*;
import java.awt.*;

public class InsertionSort extends JPanel {
    private int[] array;
    private int currentIndex = 1;
    private int currentJ;

    public InsertionSort(int[] array) {
        this.array = array;
        this.currentJ = currentIndex - 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / array.length;
        
        for (int i = 0; i < array.length; i++) {
            if (i == currentIndex) {
                g.setColor(Color.RED); // Cor para o elemento em processamento
            } else if (i == currentJ) {
                g.setColor(Color.BLUE); // Cor para o elemento sendo comparado
            } else {
                g.setColor(Color.GRAY); // Cor para os demais elementos
            }
            int barHeight = array[i];
            int xPosition = i * width;
            int yPosition = getHeight() - barHeight;

            // Desenha a barra
            g.fillRect(xPosition, yPosition, width - 2, barHeight);

            // Desenha o valor do elemento acima da barra
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), xPosition + (width / 2) - 5, yPosition - 5);
        }
    }

    public void sortStep() {
        if (currentIndex < array.length) {
            int key = array[currentIndex];
            currentJ = currentIndex - 1;
            while (currentJ >= 0 && array[currentJ] > key) {
                array[currentJ + 1] = array[currentJ];
                currentJ--;
                repaint();
                sleep(200);
            }
            array[currentJ + 1] = key;
            currentIndex++;
            repaint();
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visual Insertion Sort");
        int[] array = { 47, 12, 85, 23, 61, 94, 38, 76, 29, 53 };
        InsertionSort panel = new InsertionSort(array);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(panel);
        frame.setVisible(true);

        Timer timer = new Timer(500, e -> panel.sortStep());
        timer.start();
    }
}
