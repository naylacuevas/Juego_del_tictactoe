import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class TicTacToe extends JFrame {
    private Tablero tablero;
    private JButton[][] botones = new JButton[3][3];
    private JLabel mensaje;

    public TicTacToe() {
        tablero = new Tablero();
        setTitle("Tic Tac Toe - Inspirado en Billar Pool");
        setSize(400, 400);
        setLayout(new GridLayout(4, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear botones
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                final int fila = i;
                final int columna = j;
                botones[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (tablero.colocarFicha(fila, columna)) {
                            botones[fila][columna].setText(tablero.getTurno().equals("X") ? "O" : "X");
                            reproducirSonido("golpe.wav"); // Reproduce el sonido al colocar la ficha

                            String ganador = tablero.verificarGanador();
                            if (ganador != null) {
                                mensaje.setText("¡El jugador " + ganador + " ha ganado!");
                                deshabilitarBotones();
                            } else if (tablero.esEmpate()) {
                                mensaje.setText("¡Es un empate!");
                                deshabilitarBotones();
                            }
                        }
                    }
                });
                add(botones[i][j]);
            }
        }

        mensaje = new JLabel("", SwingConstants.CENTER);
        add(mensaje);
        setVisible(true);
    }

    private void deshabilitarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

    private void reproducirSonido(String ruta) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}