import javax.swing.*;
import java.awt.*;

public class InsertionSort extends JPanel {
    
    private int[] array; // numeros a serem ordenados
    private int currentIndex = 1; // elemento atual sendo inserido na troca de posição
    private int currentJ; // posição de comparação dos elementos que podem fazer a troca

    // essa linha aqui toda é um construtor que vai iniciar o array e o index comparação
    public InsertionSort(int[] array) {
        this.array = array;
        this.currentJ = currentIndex - 1;
    }

    // método vai responsavel desenhar cada barra que vai representar cada elemento do array

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / array.length;
        
        for (int i = 0; i < array.length; i++) {
            if (i == currentIndex) {
                g.setColor(Color.RED); // a barra vermelha representa o elemento atual
            } else if (i == currentJ) {
                g.setColor(Color.BLUE); // a barra azul representa o elemento que esta sendo comparado
            } else {
                g.setColor(Color.GRAY); // a barra cinza representa os elementos ja posicionados
            }
            // esses 3 sao responsaveis por calcular altura e a posição da barra desenho
            int barHeight = array[i];
            int xPosition = i * width;
            int yPosition = getHeight() - barHeight;

            // essa linha vai pegar os valores dos elementos do array e vai desenhar barras com os tamanhos 
            // correspondente ao valor 
            g.fillRect(xPosition, yPosition, width - 2, barHeight);

            // esses 2 responsaveis por colocar numero encima das barras
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array[i]), xPosition + (width / 2) - 5, yPosition - 5);
        }
    }

    // esse método vai executar uma etapa do insertion sort
    public void sortStep() {
        // vai verificar se ainda possui elementos para ordenar
    if (currentIndex < array.length) {
        int key = array[currentIndex]; // essa parte vai indicar o elemento que precisa ser posicionado
        currentJ = currentIndex - 1;

        // essa parte vai mover os elementos maiores para direita ate localizar a posição correta de key
        while (currentJ >= 0 && array[currentJ] > key) {
            array[currentJ + 1] = array[currentJ]; // essa linha vai deslocar os elementos para direita
            currentJ--;
            sleep(500);  // essa linha é uma pausa para visualizar a movimentação
        }
        // para inserir o key na posição correta
        array[currentJ + 1] = key;
        currentIndex++;
        
        repaint(); // essa linha é para atualizar a vizualização apos cada passo
    }
}

    // esse método é como um auxiliar para pausar a execução por um tempo
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // janela principal
        JFrame frame = new JFrame("Visual Insertion Sort");
        int[] array = { 9, 5, 2, 7, 1, 8, 6, 3, 4}; // os elementos a serem ordenados
        InsertionSort panel = new InsertionSort(array);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(panel);
        frame.setVisible(true);

        // essa linha é um temporizador responsavel por chamar o sortSetp() a cada 300ms
        Timer timer = new Timer(300, e -> panel.sortStep());
        timer.start(); // essa linha vai iniciar o temporizador, assim o processo de ordenação
    }
}
