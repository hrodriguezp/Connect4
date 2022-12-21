package com.aetxabao.connect4;

/**
 * @author Nombre Apellido
 */
public class Tablero {

    public final static char O = 'O';
    public final static char X = 'X';
    public final static char L = ' ';
    private final static int W = 7;
    private final static int H = 6;
    private int contador;
    private char turno;
    private final int ancho;
    private final int alto;
    private final char[][] m;

    public Tablero() {
        contador = 0;
        turno = O;
        ancho = W;
        alto = H;
        m = new char[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                m[i][j] = L;
            }
        }
    }

    public Tablero(char[][] m) {
        this.m = m;

        int rojas = 0;
        int amarillas = 0;

        for (int i = 0; i < this.m.length; i++) {
            for (int j = 0; j < this.m[i].length; j++) {
                if (this.m[i][j] == O) {
                    rojas++;
                    this.contador++;
                } else if (this.m[i][j] == X) {
                    amarillas++;
                    this.contador++;
                }
            }
        }

        if (rojas > amarillas) {
            this.turno = X;
        } else {
            this.turno = O;
        }

        this.ancho = this.m.length;
        this.alto = this.m[0].length;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getContador() {
        return contador;
    }

    public char[][] getMatriz() {
        return m;
    }

    public char getTurno() {
        return turno;
    }

    public void iniciaTurno() {
        int numero = (int) (Math.random() * (2) + 1);
        if (numero == 1) {
            this.turno = X;
        } else if (numero == 2) {
            this.turno = O;
        }
    }

    public void cambiaTurno() {
        if (turno == X) {
            turno = O;
        } else {
            turno = X;
        }
    }

    public boolean estaColumnaLibre(int columna) {
        if (columna < 0 || columna > m.length - 1) {
            return false;
        }
        return m[columna][5] == L;
    }

    /*

    COLUMNA     1
    FICHA       X

    X  O  X  L  L  L
    char[][] m1 = {
 {X, O, X, O, L, L},   00 01 02 03 04 05
 {X, O, L, L, L, L},   10 11 12 13 14 15
 {X, O, X, O, O, X},   20 21 22 23 24 25
 {O, X, O, O, X, X},   30 31 32 33 34 35
 {O, X, X, O, L, L},   40 41 42 43 44 45
 {O, O, L, L, L, L},
 {X, X, O, L, L, L}
 };
 pinta(m1);
}
###################################
# CONECTA 4 #
###################################

F |   |   | X | X |   |   |   | F
E |   |   | O | X |   |   |   | E
D | O |   | O | O | O |   |   | D
C | X |   | X | O | X |   | O | C
B | O | O | O | X | X | O | X | B
A | X | X | X | O | O | O | X | A
 -----------------------------
 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
     */
    public void inserta(char ficha, int columna) {
        for (int j = 0; j < m.length; j++) {
            if (m[columna][j] == L) {
                m[columna][j] = ficha;
                contador++;
                break;
            }
        }
    }
        /*
            boolean noIntroducida=false;
            int j = 0;
            while (j < m.length && !noIntroducida){
                if (m[columna][j] == L){
                    m[columna][j] = ficha;
                    contador++;
                    noIntroducida = true;
                }
                j++;
            }
         */

    public boolean estaLleno() {
        return contador == alto * ancho;
    }

    public boolean gana(char jugador) {
        boolean b;
        b = ganaHorizontal(jugador);
        b = b || ganaVertical(jugador);
        b = b || ganaDiagonalArriba(jugador);
        b = b || ganaDiagonalAbajo(jugador);
        return b;
    }

    public boolean ganaHorizontal(char jugador) {
        for (int j = 0; j < m[0].length; j++) {
            for (int i = 0; i < m.length - 3; i++)
            {
                if (hay4Horizontales(i, j, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4Horizontales(int columna, int fila, char jugador) {
        if (m[columna][fila] == jugador && m[columna + 1][fila] == jugador && m[columna + 2][fila] == jugador && m[columna + 3][fila] == jugador) {
            return true;
        } else {
            return false;
        }
    }

    /*
        char[][] m1 = {
 {X, O, X, O, L, L},   00 01 02 03 04 05
 {X, O, L, L, L, L},   10 11 12 13 14 15
 {X, O, X, O, O, X},   20 21 22 23 24 25
 {O, X, O, O, X, X},   30 31 32 33 34 35
 {O, X, X, O, L, L},   40 41 42 43 44 45
 {O, O, L, L, L, L},   50 51 52 53 54 55
 {X, X, O, L, L, L}    60 61 62 63 64 65
 };
    /*



     */
    private boolean ganaVertical(char jugador) {
        for (int j = 0; j <= m[0].length; j++) {
            for (int i = 0; i < m.length - 3; i++)
            {
                if (hay4Verticales(j, i, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4Verticales(int columna, int fila, char jugador) {
        if (m[columna][fila] == jugador && m[columna][fila + 1] == jugador && m[columna][fila + 2] == jugador && m[columna][fila + 3] == jugador) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ganaDiagonalArriba(char jugador) {
        for (int i = 0; i < m[0].length - 3; i++) {
            for (int j = 0; j < m.length - 3; j++)
            {
                if (hay4DiagonalesArriba(j, i, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4DiagonalesArriba(int columna, int fila, char jugador) {
        //TODO: hay4DiagonalesArriba
        if (m[columna][fila] == jugador && m[columna + 1][fila + 1] == jugador && m[columna + 2][fila + 2] == jugador && m[columna + 3][fila + 3] == jugador) {
            return true;
        } else {
            return false;
        }
    }




    private boolean ganaDiagonalAbajo(char jugador) {
        for (int j = 3; j < m.length; j++) {
            for (int i = 0; i < m[0].length - 3; i++)
            {
                if (hay4DiagonalesAbajo(j, i, jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hay4DiagonalesAbajo(int columna, int fila, char jugador) {
        if (m[columna][fila] == jugador && m[columna + 1][fila + 1] == jugador && m[columna + 2][fila + 2] == jugador && m[columna + 3][fila + 3] == jugador) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaFinalizado() {
        return estaLleno() || gana(turno);
    }
}
