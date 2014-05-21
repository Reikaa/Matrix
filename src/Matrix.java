import java.util.Arrays;

public class Matrix {
    protected double[][] m;

    /**
     * Der leere Konstruktor erstellt eine 1 x 1 Matrix mit 0 als einziges Element.
     */
    public Matrix() {
        m = new double[1][];
        m[0] = new double[1];
        m[0][0] = 0;
    }

    /**
     * Gibt eine n x n Einheitsmatrix zur�ck
     *
     * @param n Dimension der Matrix
     */
    public Matrix(int n) {
        m = new double[n][];
        for (int i = 0; i < n; i++) {
            m[i] = new double[n];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    m[i][j] = 1;
                } else {
                    m[i][j] = 0;
                }
            }
        }
    }

    /**
     * Macht aus einem zweidimensionalen Array eine Matrix
     *
     * @param mat 2D Array vom Typ double
     */
    public Matrix(double[][] mat) {
        double[][] tmp;
        tmp = new double[mat.length][];
        for (int i = 0; i < mat.length; i++) {
            double[] row = Arrays.copyOf(mat[i], mat[i].length);
            tmp[i] = row;
        }

        m = tmp;
    }

    /**
     * Gibt ein Element der Matrix zur�ck.
     *
     * @param row    Zeile
     * @param column Spalte
     * @return Das Element an den angegeben Koordinaten.
     */
    public double getElement(int row, int column) throws IndexOutOfBoundsException {
        if (row < m.length && column < m[0].length) {
            return m[row][column];
        } else {
            throw new IndexOutOfBoundsException("Element with the specific index doesn't exist.");
        }
    }

    /**
     * Gibt die Gr��e eines Arrays als Tupel zur�ck
     *
     * @return Gr��e des Arrays als Tupel
     */
    public Tuple size() {
        return new Tuple(m.length, m[0].length);
    }

    /**
     * Addiert zwei Matrizen, vorrausgesetzt deren Dimensionen sind gleich.
     *
     * @param mat Die zu addierende Matrix.
     * @return Die Summe als Matrix.
     */
    public Matrix addition(Matrix mat) throws Exception {
        if (this.size().equals(mat.size())) {
            double[][] result = new double[size().getElement(0)][size().getElement(1)];
            for (int i = 0; i < size().getElement(0); i++) {
                for (int j = 0; j < size().getElement(1); j++) {
                    result[i][j] = this.getElement(i, j) + mat.getElement(i, j);
                }
            }
            return new Matrix(result);
        } else {
            throw new Exception("The matrices have different dimensions and therefore couldn't aggregated / subtracted.");
        }
    }

    /**
     * Multipliziert die Matrix mit einem Skalarprodukt
     *
     * @param c Das Skalar
     * @return Das Ergebnis als Matrix nach der Multiplikation mit dem Skalar
     */
    public Matrix multiplicate(double c) {
        double[][] result = new double[size().getElement(0)][size().getElement(1)];
        for (int i = 0; i < size().getElement(0); i++) {
            for (int j = 0; j < size().getElement(1); j++) {
                result[i][j] = c * getElement(i, j);
            }
        }
        return new Matrix(result);
    }

    /**
     * Multipliziert zwei Matrizen
     *
     * @param mat Die zu multiplizierende Matrix.
     * @return Das Ergebnis der Multiplikation als neue Matrix.
     */
    public Matrix multiplicate(Matrix mat) throws Exception {
        Tuple size1 = this.size();
        Tuple size2 = mat.size();
        int rows1 = size1.getElement(0), rows2 = size2.getElement(0),
                columns1 = size1.getElement(1), columns2 = size2.getElement(1);

        if (columns1 != rows2) {
            throw new Exception("The matrices have different dimensions and therefore couldn't multiplied.");
        }

        double[][] result = new double[rows1][columns2];

        // by definition of matrix multiplication
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                double sum = 0;
                for (int k = 0; k < columns1; k++) { // columns1 == rows2
                    sum += this.getElement(i, k) * mat.getElement(k, j);
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    /**
     * Subtrahiert zwei Matrizen, vorrausgesetzt deren Dimensionen sind gleich.
     *
     * @param mat Die zu subtrahierende Matrix.
     * @return Die Summe als Matrix.
     */
    public Matrix subtract(Matrix mat) throws Exception {

        return this.addition(mat.multiplicate(-1));
    }

    /**
     * Gibt die Transponierte einer Matrix zur�ck
     *
     * @return Transponierte
     */
    public Matrix transpose() {
        int rows = size().getElement(0), columns = size().getElement(1);
        double[][] result = new double[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = m[i][j];
            }
        }

        return new Matrix(result);
    }

    /**
     * Gibt die Zeilensumme einer Matrix zurück
     *
     * @return Zeilensumme als Tupel
     */
    public Tuple rowSum() {
        int rows = size().getElement(0), columns = size().getElement(1);
        int[] result = new int[rows];

        for (int i = 0; i < rows; i++) {
            int sum = 0;
            for (int j = 0; j < columns; j++) {
                sum += m[i][j];
            }
            result[i] = sum;
        }
        return new Tuple(result);
    }

    /**
     * Gibt den Rang der Matrix zurück
     *
     * @return Rang der Matrix als Integer
     */
    public int rank() {
        int rank = 0;
        Matrix rref = echelonForm();
        for (int i = 0; i < size().getElement(0); i++) {
            boolean match = false;
            for (int j = 0; j < size().getElement(1); j++) {
                if (rref.getElement(i,j) != 0) {
                    match = true;
                }
            }
            if(match) {
                rank++;
            }
        }
        return rank;
    }

    /**
     * Source: http://muhammadsaadkhan31.blogspot.de/2013/06/matrix-echelon-form.html
     * Bringt die Matrix in die Stufenform nach dem Gaußsches Eliminationsverfahren
     * um anschließend den Rang der Matrix bestimmen zu können
     */
    public Matrix echelonForm() {
        double[][] rref = m;
        if (rref[0][0] == 0) {
            double[] row = new double[rref.length];
            for (int i = 0; i < rref.length; i++) {
                row[i] = rref[0][i];
            }
            for (int j = 0; j < rref.length; j++) {
                int i = 1;
                for (i = 1; i < rref.length; i++) {
                    if (rref[i][j] != 0) {

                        for (int f = 0; f < row.length; f++) {
                            rref[0][f] = rref[i][f];
                            rref[i][f] = row[f];
                        }
                        break;
                    }
                }
                if (i != rref.length) {
                    break;
                }
            }
        }
        rowOperations(rref);
        return new Matrix(rref);
    }


    /**
     * Gaußsches Eliminationsverfahren
     *
     * @param a Die zu bearbeitende Matrix.
     */
    private void rowOperations(double[][] a) {
        double Variable = 0;
        for (int i = 0, j = 0; i < a.length - 1; ) {
            if (j == 0) {
                while (j < a.length && a[i][j] == 0) {
                    j++;
                }
            }
            if (j == a.length) {
                i++;
                j = 0;
            } else {

                for (int l = i + 1; l < a.length; l++) {

                    for (int m = 0; m < a.length; m++) {
                        if (m == 0) {
                            Variable = ((1 / a[i][j]) * a[l][j]);
                            if ((Variable * a[i][j] > 0 && a[l][j] > 0) || (Variable * a[i][j] < 0 && a[l][j] < 0))
                                Variable = Variable * (-1);
                        }
                        a[l][m] = Math.round(Variable * a[i][m] + a[l][m]);
                    }
                }
                i++;
                j = 0;
            }
        }
    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                result = result + String.format("% f ", m[i][j]);
            }
            result = result + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        Matrix mat = (Matrix) obj;
        // Matrizen m�ssen die selbe Dimension haben um gleich zu sein
        if (this.m.length != mat.m.length || this.m[0].length != mat.m[0].length) {
            return false;
        }

        //Matrizen m�ssen au�erdem die selben Werte haben
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (this.m[i][j] != mat.m[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

