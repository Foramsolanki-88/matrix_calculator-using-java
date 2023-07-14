package com.company;

import java.util.Scanner;

class takeMatrix {
    Scanner sc = new Scanner(System.in);
    int[][] a = new int[3][3];
    int[][] b = new int[3][3];

    void matrix() {
        System.out.println("Enter elements of first matrix:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextInt();

        System.out.println("Enter elements of second matrix:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                b[i][j] = sc.nextInt();
    }
}

class ASM extends takeMatrix {
    int[][] add = new int[3][3];
    int[][] sub = new int[3][3];
    int[][] mux = new int[3][3];
    int i, j, k;

    void Addition() {
        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                add[i][j] = a[i][j] + b[i][j];

        System.out.println("Addition of two matrix:");
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(add[i][j] + "\t");
            }
            System.out.println();
        }
    }

    void Subtraction() {
        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                sub[i][j] = a[i][j] - b[i][j];

        System.out.println("Subtraction of two matrix:");
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(sub[i][j] + "\t");
            }
            System.out.println();
        }
    }

    void Multiplication() {
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                int temp = 0;
                for (k = 0; k < 3; k++) {
                    temp += a[i][k] * b[k][j];
                }
                mux[i][j] = temp;
            }
        }
        System.out.println("Multiplication of two matrix:");
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(mux[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

class oneMatrix {
    Scanner sc = new Scanner(System.in);
    static final int N = 3;
    int[][] a = new int[3][3];
    int i, j;

    void matrix() {
        System.out.println("Enter elements of matrix:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextInt();
    }
}

class RTID extends oneMatrix {
    int[][] b = new int[3][3];

    void Transpose() {
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                b[i][j] = a[j][i];
            }
        }
        System.out.println("Transpose of a matrix:");
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(b[i][j] + "\t");
            }
            System.out.println();
        }
    }

    int[][] swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
        return arr;
    }

    float d;

    void Determinant() {
        int num1, num2, det = 1, index, total = 1;
        int[] temp = new int[4];

        for (int i = 0; i < 3; i++) {
            index = i;
            while (a[index][i] == 0 && index < 3) {
                index++;
            }
            if (index == 3) {
                continue;
            }
            if (index != i) {
                for (int j = 0; j < 3; j++) {
                    swap(a, index, j, i, j);
                }

                det = (int) (det * Math.pow(-1, index - i));
            }

            for (int j = 0; j < 3; j++) {
                temp[j] = a[i][j];
            }

            for (int j = i + 1; j < 3; j++) {
                num1 = temp[i];
                num2 = a[j][i];

                for (int k = 0; k < 3; k++) {

                    a[j][k] = (num1 * a[j][k]) - (num2 * temp[k]);
                }
                total = total * num1;
            }
        }

        for (int i = 0; i < 3; i++) {
            det = det * a[i][i];
        }
        d = (det / total);

    }

    void getCofactor(int A[][], int temp[][], int p, int q, int n) {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = A[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    int determinant(int A[][], int n) {
        int D = 0;

        if (n == 1)
            return A[0][0];

        int[][] temp = new int[N][N];
        int sign = 1;

        for (int f = 0; f < n; f++) {
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return D;
    }

    void adjoint(int A[][], int[][] adj) {
        if (N == 1) {
            adj[0][0] = 1;
            return;
        }

        int sign = 1;
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                getCofactor(A, temp, i, j, N);

                sign = ((i + j) % 2 == 0) ? 1 : -1;

                adj[j][i] = (sign) * (determinant(temp, N - 1));
            }
        }
    }

    void Inverse() {
        float[][] inverse = new float[N][N];
        int det = determinant(a, N);
        if (det == 0) {
            System.out.print("Singular matrix, can't find its inverse");
        } else {
            int[][] adj = new int[N][N];
            adjoint(a, adj);

            System.out.println("Inverse of a matrix:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(adj[i][j] / (float) det + "\t");
                }
                System.out.println();
            }
        }
    }

    void swap(int mat[][], int row1, int row2, int col) {
        for (int i = 0; i < col; i++) {
            int temp = mat[row1][i];
            mat[row1][i] = mat[row2][i];
            mat[row2][i] = temp;
        }
    }

    void rankOfMatrix() {
        int rank = 3;
        for (int row = 0; row < rank; row++) {
            if (a[row][row] != 0) {
                for (int col = 0; col < 3; col++) {
                    if (col != row) {
                        double mult = (double) a[col][row] / a[row][row];
                        for (int i = 0; i < rank; i++)
                            a[col][i] -= mult * a[row][i];
                    }
                }
            } else {
                boolean reduce = true;
                for (int i = row + 1; i < 3; i++) {
                    if (a[i][row] != 0) {
                        swap(a, row, i, rank);
                        reduce = false;
                        break;
                    }
                }
                if (reduce) {
                    rank--;
                    for (int i = 0; i < 3; i++)
                        a[i][row] = a[i][rank];
                }
                row--;
            }

        }
        System.out.println("Rank of a matrix: " + rank);
    }
}

public class MatrixCal {
    int n;

    void display() {
        Scanner sc = new Scanner(System.in);
        ASM asm = new ASM();
        RTID fab = new RTID();
        System.out.println("------------3x3 MATRIX CALCULATOR-----------");
        System.out.println("1. Addition of two matrix");
        System.out.println("2. Subtraction of two matrix");
        System.out.println("3. Multiplication of two matrix");
        System.out.println("4. Transpose of a matrix");
        System.out.println("5. Determinant of a matrix");
        System.out.println("6. Inverse of a matrix");
        System.out.println("7. Rank of a matrix");
        System.out.println("-------------------------------------------");
        System.out.print("Enter your choice: ");
        n = sc.nextInt();

        switch (n) {
            case 1:
                asm.matrix();
                asm.Addition();
                break;
            case 2:
                asm.matrix();
                asm.Subtraction();
                break;
            case 3:
                asm.matrix();
                asm.Multiplication();
                break;
            case 4:
                fab.matrix();
                fab.Transpose();
                break;
            case 5:
                fab.matrix();
                fab.Determinant();
                System.out.println("Detreminant of a matrix: " + fab.d);
                break;
            case 6:
                fab.matrix();
                fab.Inverse();
                break;
            case 7:
                fab.matrix();
                fab.rankOfMatrix();
                break;
            default:
                System.out.println("Invalid option!!!");
        }
    }

    public static void main(String[] args) {
        MatrixCal mc = new MatrixCal();
        mc.display();

    }
}
