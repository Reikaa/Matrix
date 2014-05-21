import junit.framework.TestCase;

public class MatrixTest extends TestCase {

    Matrix m1;
    Matrix m2;


    public void setUp() throws Exception {
        super.setUp();
        double[][] d1 = {
                { 1, 2 },
                { 1, 5 }
        };
        m1 = new Matrix(d1);
        double[][] d2 = {
                { 1, 2 },
                { 0, 4 }
        };
        m2 = new Matrix(d2);
    }

    public void testGetElement() throws Exception {
        assertEquals(1.0, m1.getElement(0,0));
    }

    public void testSize() throws Exception {
        assertEquals(2, m1.size().getElement(0));
        assertEquals(2, m1.size().getElement(1));
    }

    public void testAddition() throws Exception {
        Matrix result = m1.addition(m2);
        double[][] temp = {
                { 2, 4 },
                { 1, 9 }
        };
       Matrix expectation = new Matrix(temp);
       assertTrue(result.equals(expectation));
    }

    public void testMultiplicateWithMatrix() throws Exception {
        Matrix result = m1.multiplicate(m2);
        double[][] temp = {
                { 1, 10 },
                { 1, 22 }
        };
        Matrix expectation = new Matrix(temp);
        assertTrue(result.equals(expectation));
    }

    public void testMultiplicateWithScalar() throws Exception {
        Matrix result = m1.multiplicate(2);
        double[][] temp = {
                { 2, 4 },
                { 2, 10 }
        };
        Matrix expectation = new Matrix(temp);
        assertTrue(result.equals(expectation));
    }

    public void testSubtract() throws Exception {
        Matrix result = m1.subtract(m2);
        double[][] temp = {
                { 0, 0 },
                { 1, 1 }
        };
        Matrix expectation = new Matrix(temp);
        assertTrue(result.equals(expectation));
    }

    public void testTranspose() throws Exception {
        Matrix result = m1.transpose();
        double[][] temp = {
                { 1, 1 },
                { 2, 5 }
        };
        Matrix expectation = new Matrix(temp);
        assertTrue(result.equals(expectation));
    }

    public void testRowSum() throws Exception {
        int[] result = m1.rowSum().values;
        assertEquals(result[0], 3);
        assertEquals(result[1], 6);
    }

    public void testRank() throws Exception {
        assertEquals(m1.rank(), 2);
    }
}