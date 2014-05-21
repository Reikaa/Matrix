public class Tuple {
    protected int size;
    protected int[] values;

    public Tuple() {
        values = null;
        size = 0;
    }

    public Tuple(int... numbers) {
        int length = numbers.length;
        size = length;
        values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = numbers[i];
        }
    }


    @Override
    public boolean equals(Object obj) {
        Tuple t = (Tuple) obj;
        if (this.size != t.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.getElement(i) != t.getElement(i)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        String result = "(";
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                result = result + values[i] + ", ";
            } else {
                result = result + values[i] + ")";
            }
        }
        return result;
    }

    int getElement(int index)
            throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return values[index];
    }
}