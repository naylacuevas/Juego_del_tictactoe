public class Tablero {
    private Ficha[][] casillas = new Ficha[3][3];
    private String turno = "X";

    public boolean colocarFicha(int fila, int columna) {
        if (casillas[fila][columna] == null) {
            casillas[fila][columna] = new Ficha(turno, fila, columna);
            turno = turno.equals("X") ? "O" : "X";
            return true;
        }
        return false;
    }

    public Ficha[][] getCasillas() {
        return casillas;
    }

    public String getTurno() {
        return turno;
    }

    public String verificarGanador() {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if (casillas[i][0] != null && casillas[i][0].getTipo().equals(casillas[i][1].getTipo()) && casillas[i][1].getTipo().equals(casillas[i][2].getTipo())) {
                return casillas[i][0].getTipo();
            }
            if (casillas[0][i] != null && casillas[0][i].getTipo().equals(casillas[1][i].getTipo()) && casillas[1][i].getTipo().equals(casillas[2][i].getTipo())) {
                return casillas[0][i].getTipo();
            }
        }
        if (casillas[0][0] != null && casillas[0][0].getTipo().equals(casillas[1][1].getTipo()) && casillas[1][1].getTipo().equals(casillas[2][2].getTipo())) {
            return casillas[0][0].getTipo();
        }
        if (casillas[0][2] != null && casillas[0][2].getTipo().equals(casillas[1][1].getTipo()) && casillas[1][1].getTipo().equals(casillas[2][0].getTipo())) {
            return casillas[0][2].getTipo();
        }
        return null;
    }

    public boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (casillas[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}