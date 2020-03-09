import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MatrixImage implements Image {
    public int[][] field;

    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    // copy constructor
    public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }

    public MatrixImage(String filename) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }

    @Override
    public int sizeX() {
        return field.length;
    }

    @Override
    public int sizeY() {
        return field[0].length;
    }

    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // TODO
        if( p0.x < 0 || p0.x > sizeX() - 1 || p0.y < 0 || p0.y > sizeY() -1){
            throw new InputMismatchException("eine der Koorordinaten ist außerhalb des Bildes");
        }
        if( p1.x < 0 || p1.x > sizeX() - 1 || p1.y < 0 || p1.y > sizeY() -1){
            throw new InputMismatchException("eine der Koorordinaten ist außerhalb des Bildes");
        }
        double a = field[p0.x][p0.y] - field[p1.x][p1.y];
                if(a<0)
                    return -a;
                else
                    return a;
    }

    @Override
    public void removeVPath(int[] path) {
        // TODO
        int [][] testField = new int[this.sizeX()-1][this.sizeY()];
        Stack<Integer> Stack = new Stack<>();
        for(int i=0; i < this.sizeY();i++){
            for(int j=0; j < this.sizeX();j++){
                if(j != path[i]){
                    Stack.push(field[j][i]);
                }
            }
        }

        for(int i=sizeY()-1;i>=0;i--){
            for(int j=sizeX()-2;j>=0;j--){
                testField[j][i] = Stack.pop();
            }
        }

        this.field = testField;
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }

}

