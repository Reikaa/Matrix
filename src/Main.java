import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Speicher für die eingegebenen Matrizen
        ArrayList<Matrix> matrices = new ArrayList<Matrix>();
        //Variable für die gewählte Menüoption
        int option = 0;
        //Programmschleife
        do
        {
            option = showOptions(matrices);
            switch(option) {
                case 1:
                    add(matrices);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Your stored matrices");
                    System.out.println("-------------------------\n");
                    showMatrices(matrices);
                    break;
                case 3:
                    addition(matrices);
                    break;
                case 4:
                    subtraction(matrices);
                    break;
                case 5:
                    multiplication(matrices);
                    break;
                case 6:
                    transpose(matrices);
                    break;
                case 7:
                    rowSum(matrices);
                    break;
                case 8:
                    rank(matrices);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Can't match your option input.");
                    break;
            }
        }
        while(true);
    }

    public static int showOptions(ArrayList<Matrix> matrices) {
        System.out.println("------------------");
        System.out.println("Matrix operations");
        System.out.println("------------------\n");
        System.out.println("Please enter 1 to add a matrix");
        if(matrices.size()>0) {
            System.out.println("Please enter 2 to show your stored matrices");
            System.out.println("Please enter 3 for summation");
            System.out.println("Please enter 4 for subtraction");
            System.out.println("Please enter 5 for multiplication");
            System.out.println("Please enter 6 to get transposition");
            System.out.println("Please enter 7 to get row sum");
            System.out.println("Please enter 8 to get rank");
        }
        System.out.println("Please enter 9 to exit\n");

        System.out.print("Enter Option: ");
        Scanner select = new Scanner(System.in);
        return select.nextInt();
    }

    public static void showMatrices(ArrayList<Matrix> matrices) {
        Scanner choice = new Scanner(System.in);
        if(matrices.isEmpty()) {
            System.out.println();
            System.out.println("No matrices found.");
        }
        for (int i=0;i<matrices.size(); i++) {
            System.out.println("Index " + (i+1));
            System.out.println("...........");
            System.out.println();
            System.out.println(matrices.get(i).toString());
        }
        System.out.println();
        System.out.println("Press any key to continue...");
        choice.nextLine();
    }

    public static void add(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Add a matrix");
        System.out.println("-------------------------\n");
        System.out.println("Please enter 1 to add a identity matrix");
        System.out.println("Please enter 2 to add a custom matrix\n");
        System.out.print("Enter Option: ");
        int dOption = select.nextInt();

        if(dOption==1) {
            System.out.println();
            System.out.println("Add a new identity matrix");
            System.out.println("-------------------------\n");
            System.out.print("Enter the dimension (NxN): ");
            int n = select.nextInt();
            Matrix m = new Matrix(n);
            matrices.add(m);
            System.out.println();
            System.out.println(m.toString());
            System.out.println("Press any key to continue...");
            select.nextLine();
        }
        if(dOption==2) {
            System.out.println();
            System.out.println("Add a custom matrix");
            System.out.println("-------------------------\n");
            System.out.print("Enter the number of rows: ");
            int rows = select.nextInt();
            System.out.print("Enter the number of columns: ");
            int columns = select.nextInt();
            System.out.println();
            double[][] matrix = new double[rows][columns];
            for (int i=0; i<rows; i++) {
                for (int j=0; j<columns; j++) {
                    System.out.print("Enter a value for element [" + i + "," + j + "]: ");
                    matrix[i][j] = select.nextDouble();
                }
            }
            Matrix m = new Matrix(matrix);
            matrices.add(m);
            System.out.println();
            System.out.println(m.toString());
            System.out.println("Press any key to continue...");
            choice.nextLine();
        }
    }

    public static void addition(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Summation");
        System.out.println("-------------------------\n");
        showMatrices(matrices);
        System.out.print("Enter index of the first matrix: ");
        int a = select.nextInt();
        System.out.print("Enter index of the second matrix: ");
        int b = select.nextInt();
        if(a > matrices.size() || b > matrices.size()) {
            System.out.println();
            System.out.println("Index not found.");
        }
        else {
            System.out.println();
            System.out.println("Sum of matrix " + a + " and " + b);
            System.out.println(".......................\n");
            try {
                System.out.println(matrices.get(--a).addition(matrices.get(--b)));
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
        System.out.println();
        System.out.println("Press any key to continue...");
        choice.nextLine();
    }

    public static void subtraction(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Subtraction");
        System.out.println("-------------------------\n");
        showMatrices(matrices);
        System.out.print("Enter index of the first matrix: ");
        int a = select.nextInt();
        System.out.print("Enter index of the second matrix: ");
        int b = select.nextInt();
        if(a > matrices.size() || b > matrices.size()) {
            System.out.println();
            System.out.println("Index not found.");
        }
        else {
            System.out.println();
            System.out.println("Difference of matrix " + a + " and " + b);
            System.out.println(".......................");
            try {
                System.out.println(matrices.get(--a).subtract(matrices.get(--b)));
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
        System.out.println();
        System.out.println("Press any key to continue...");
        choice.nextLine();
    }

    public static void multiplication(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Multiplication");
        System.out.println("-------------------------\n");
        System.out.println("Please enter 1 to multiplicate a matrix with scalar product");
        System.out.println("Please enter 2 to multiplicate a matrix with another matrix\n");
        System.out.print("Enter Option: ");
        int dOption = select.nextInt();

        if(dOption==1) {
            System.out.println();
            System.out.println("Multiplicate with scalar");
            System.out.println("-------------------------\n");
            showMatrices(matrices);
            System.out.print("Enter the index of the matrix: ");
            int a = select.nextInt();
            System.out.print("Enter the scalar: ");
            double b = select.nextDouble();
            if(a > matrices.size()) {
                System.out.println();
                System.out.println("Matrix doesn't exist.");
            }
            else {
                System.out.println();
                System.out.println("Product for matrix " + a + " with scalar " + b);
                System.out.println(".......................\n");
                try {
                    System.out.println(matrices.get(--a).multiplicate(b));
                } catch (Exception e) {
                    System.out.print(e.toString());
                }
            }
            System.out.println();
            System.out.println("Press any key to continue...");
            choice.nextLine();
        }
        if(dOption==2) {
            System.out.println();
            System.out.println("Multiplication with matrix");
            System.out.println("-------------------------\n");
            showMatrices(matrices);
            System.out.print("Enter index of the first matrix: ");
            int a = select.nextInt();
            System.out.print("Enter index of the second matrix: ");
            int b = select.nextInt();
            if(a > matrices.size() || b > matrices.size()) {
                System.out.println("Index not found.");
            }
            else {
                System.out.println();
                System.out.println("Product for matrix " + a + " and " + b);
                System.out.println("...........\n");
                try {
                    System.out.println(matrices.get(--a).multiplicate(matrices.get(--b)));
                } catch (Exception e) {
                    System.out.print(e.toString());
                }
            }
            System.out.println();
            System.out.println("Press any key to continue...");
            choice.nextLine();
        }
    }

    public static void transpose(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Transposition");
        System.out.println("-------------------------\n");
        showMatrices(matrices);
        System.out.print("Enter the index of matrix: ");
        int a = select.nextInt();
        if(a > matrices.size()) {
            System.out.println();
            System.out.println("Index not found.");
        }
        else {
            System.out.println();
            System.out.println("Transposition of matrix " + a);
            System.out.println("...........\n");
            try {
                System.out.println(matrices.get(--a).transpose());
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
        System.out.println();
        System.out.println("Press any key to continue...");
        choice.nextLine();
    }

    public static void rowSum(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Row sum");
        System.out.println("-------------------------\n");
        showMatrices(matrices);
        System.out.print("Enter index of matrix: ");
        int a = select.nextInt();
        if(a > matrices.size()) {
            System.out.println("Index not found.");
        }
        else {
            System.out.println();
            System.out.println("Row sum for matrix " + a);
            System.out.println("...........\n");
            try {
                System.out.println(matrices.get(--a).rowSum());
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
        System.out.println();
        System.out.println("Press any key to continue...");
        choice.nextLine();
    }

    public static void rank(ArrayList<Matrix> matrices) {
        Scanner select = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("Rank");
        System.out.println("-------------------------\n");
        showMatrices(matrices);
        System.out.print("Enter index of matrix: ");
        int a = select.nextInt();
        if(a > matrices.size()) {
            System.out.println();
            System.out.println("Index not found.");
        }
        else {
            System.out.println();
            System.out.println("Rank of matrix " + a);
            System.out.println("...........\n");
            try {
                System.out.println(matrices.get(--a).rank());
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
        System.out.println();
        System.out.println("Press any key to continue...");
        choice.nextLine();
    }
}
