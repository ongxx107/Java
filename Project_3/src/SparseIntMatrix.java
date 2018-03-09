
/* Part IV -

*  a) From the given memory unit assumptions, the memory usage per element in the sparseIntMatrix would be 5m for m non-zero elements as
*  the memory to store one matrix element would be 5 units (row, column, data, columnPointer, rowPointer - 1 unit for each). In contrast,
*  the memory usage to store m non-zero elements or for that matter any number of elements in a 2D Array is N^2 as we need to store even
*  0s in the case of 2D array.
*
*  b) For N = 100,000 and m = 1,000,000, the memory usage is 5,000,000 units in the case of sparseIntMatrix and 10,000,000,000 units in
*  the case of a 2D array. Clearly, the sparseIntMatrix implementation is better than the 2D array implementation in this case by about
*  9,995,000,000 units which is a huge deal.
*
*  In general, for the 2D array implementation to be more space efficient than the sparseIntMatrix implementation, 5m > N^2 => m > (N^2)/5.
*  So, for all the values of m > (N^2)/5, the 2D array implementation takes up less space than the linked list implementation of the
*  sparseIntMatrix.
*
* */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.Scanner;

public class SparseIntMatrix { /* The Sparse Int Matrix class for adding and modifying the matrix elements implemented using Linked Lists */

    private int numRows;
    /* the number of rows the sparseIntMatrix contains */
    private int numCols;
    /* the number of columns the sparseIntMatrix contains */

    private MatrixEntry[] rowHeaderArray;
    /* the header array to head the row linked lists */
    private MatrixEntry[] colHeaderArray;
    /* the header array to head the column linked lists */

    public SparseIntMatrix(int numRows, int numCols) {/* default constructor to set the number of rows, columns for the sparseIntMatrix */

        this.numRows = numRows;
        /* set number of rows in the matrix */
        this.numCols = numCols;
        /* set the number of columns in the matrix */

        rowHeaderArray = new MatrixEntry[numRows + 1];
        /* initializing a row header array to point to row linked lists */
        colHeaderArray = new MatrixEntry[numCols + 1];
        /* initializing a column header array to point to column linked lists */

    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) { /* constructor to populate the non-zero elements of the sparseIntMatrix using a text file
        input with each line containing row, column and data separated by a comma delimeter */

        this.numRows = numRows;
        /* set the number of rows in this matrix */
        this.numCols = numCols;
        /* set the number of columns in this matrix */

        rowHeaderArray = new MatrixEntry[numRows + 1];
        /* initializing a row header array to point to row linked lists */
        colHeaderArray = new MatrixEntry[numCols + 1];
        /* initializing a column header array to point to column linked lists */

        File file = new File(inputFile);
        /* creates a new object from the file path, to read using the Scanner function */

        try {
            /* try block of code to see if the specified file exists in the file path given */
            Scanner fileInput = new Scanner(file);
            /* the scanner function is used to read the contents of the file fetched from the File object */

            while (fileInput.hasNext()) {

                String[] textInputArray = (fileInput.nextLine()).split(",");
                /* reading a single line per turn and splitting the row by the comma delimiter to get three values stored as arrays */

                int r = Integer.parseInt(textInputArray[0]);
                /* storing the first chunk of the information as the row number where the element needs to be added to the sparseIntMatrix */
                int c = Integer.parseInt(textInputArray[1]);
                /* storing the second chunk of the information as the column number where the element needs to be added to the sparseIntMatrix */
                int data = Integer.parseInt(textInputArray[2]);
                /* storing the data of the element that needs to be added to the sparseIntMatrix */
                this.setElement(r, c, data);
                /* set method to set the element in the right position in the sparseIntMatrix*/
            }
        } catch (FileNotFoundException e) {
            /* catch block of code to return a custom message indicating that the file is not found at the specified file path */
            System.out.println("Sorry, can't find file!");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    private MatrixEntry getBeforeElementRow(int row, int col) { /* get method to get the previous row reference for the current element */

        MatrixEntry current = colHeaderArray[col];
        /* fix column number */
        /* it's not col-1 but less than col as we already are passing in 0-based index */
        MatrixEntry prev = null;
        /* initially assume that the previous row element is null and update it if non-null element is found */

        while (current != null && current.getRow() < row) { /* it's not row-1 but less than row as we already pass in 0-based index */
            prev = current;
            current = current.getNextRow();
        }
        return prev;
    }


    private MatrixEntry getAfterElementRow(int row,int col) { /* get method to get the next row reference for the current element */

        MatrixEntry current = colHeaderArray[col];
        /* fix the column number */
        /* it's not col-1 but less than col as we already are passing in 0-based index */
        MatrixEntry next = null;
        /* initially assume that the next row element is null and update it if non-null element is found */

        while(current != null && current.getRow() <= row){ /* it's not row-1 but less than row as we already pass in 0-based index */
            current = current.getNextRow();
        }
        return current;
    }


    private MatrixEntry getBeforeElementColumn(int row, int col){ /* get method to get the previous column reference for the current element */

        MatrixEntry current = rowHeaderArray[row];
        /* fix the row number */
        /* it's not row-1 but less than row as we already are passing in 0-based index */
        MatrixEntry prev = null;
        /* initially assume that the previous column element is null and update it if non-null element is found */

        while (current != null && current.getColumn() < col) { /* it's not col-1 but less than col as we already pass in 0-based index */
            prev = current;
            current = current.getNextColumn();
        }
        return prev;
    }


    private MatrixEntry getAfterElementColumn(int row,int col) { /* get method to get the next column reference for the current element */

        MatrixEntry current = rowHeaderArray[row];
        /* fix the row number */
        /* it's not row-1 but less than row as we already are passing in 0-based index */
        MatrixEntry next = null;
        /* initially assume that the next column element is null and update it if non-null element is found */

        while(current != null && current.getColumn() <= col){ /* it's not col-1 but less than col as we already pass in 0-based index */
            current = current.getNextColumn();
        }
        return current;
    }


    public int getElement(int row, int col) { /* get method to get the data of the specified sparseMatrixEntry element */

        MatrixEntry ptr = colHeaderArray[col];
        /* fixing the column number */

        while (ptr != null) {
            if ((ptr.getRow()) == (row)) {
                return ptr.getData();
            }
            ptr = ptr.getNextRow();
        }
        /* traverse to the row number specified while not null */
        return 0;
    }

    // 4 Total cases while inserting a new element
    // previous row is null...next is null
    // previous !=null and next == null
    // previous = null and next !=null
    // previous !=null and next !=null

    public boolean setElement(int row, int col, int data) { /* set method to set the data of the specified sparseMatrixEntry element */

        boolean isSuccessful = false;
        /* initializing this variable to false so that we can set it to true on successfully setting the data to a valid element location in the sparseIntMatrix */

        MatrixEntry nextRow = getAfterElementRow(row, col);
        /* fetching the next row reference for the current element location */
        MatrixEntry previousRow = getBeforeElementRow(row, col);
        /* fetching the previous row reference for the current element location */
        MatrixEntry nextCol = getAfterElementColumn(row, col);
        /* fetching the next column reference for the current element location */
        MatrixEntry previousCol = getBeforeElementColumn(row, col);
        /* fetching the previous column reference for the current element location */
        MatrixEntry newEntry = new MatrixEntry(row, col, data);
        /* defining a new matrix entry based on the given row, col and data */

        if (row < this.getNumRows() && col < this.getNumCols()) {
            /* checking if the specified entry location is legal or not */

            if (previousRow == null && nextRow == null) {
                colHeaderArray[col] = newEntry;
            }
            /* setting the first element in the case of previous and next row being null */

            if (previousRow == null && nextRow != null) {
                colHeaderArray[col] = newEntry;
                newEntry.setNextRow(nextRow);
            }
            /* setting an element when there is an element already in the next row but no element
               in the row prior to the current element */

            if (previousRow != null && nextRow == null) {
                if (previousRow.getRow() == row) {
                    previousRow.setData(data);
                } else {
                    previousRow.setNextRow(newEntry);
                }
            }
            /* setting an element when there is already an element in the row prior to the
               current element location but no element in the next row */

            if (previousRow != null && nextRow != null) {
                if (previousRow.getRow() == row) {
                    previousRow.setData(data);
                } else {
                    previousRow.setNextRow(newEntry);
                    newEntry.setNextRow(nextRow);
                }
            }
            /* setting an element when there are elements in the row before and after the current
               element's location */
//        }
//        if (col < this.getNumCols() && row < this.getNumRows()) {

            if (previousCol == null && nextCol == null) {
                rowHeaderArray[row] = newEntry;
            }
            /* setting the first element in the case of previous and next column being null */

            if (previousCol == null && nextCol != null) {
                rowHeaderArray[row] = newEntry;
                newEntry.setNextColumn(nextCol);
            }
            /* setting an element when there is an element already in the next column but no element
               in the column prior to the current element */

            if (previousCol != null && nextCol == null) {
                if (previousCol.getColumn() == col) {
                    previousCol.setData(data);
                } else {
                    previousCol.setNextColumn(newEntry);
                }
            }
            /* setting an element when there is already an element in the column prior to the
               current element location but no element in the next column */

            if (previousCol != null && nextCol != null) {
                if (previousCol.getRow() == col) {
                    previousCol.setData(data);
                } else {
                    previousCol.setNextColumn(newEntry);
                    newEntry.setNextColumn(nextCol);
                }
            }
            /* setting an element when there are elements in the column before and after the current
               element's location */

            isSuccessful = true;
            /* setting the variable to true when setting an element in the sparseIntMatrix is successful */
        }

        return isSuccessful;
    }

    public int getNumRows() { /* get method to get the number of rows in the sparseIntMatrix */
        return numRows;
    }

    public int getNumCols() { /* get method to get the number of columns in the sparseIntMatrix */
        return numCols;
    }

    private MatrixEntry[] getRowHeaderArray(){ return rowHeaderArray; }

    private MatrixEntry[] getColHeaderArray(){ return colHeaderArray; }

    public boolean plus(SparseIntMatrix other){ /* plus method to add two sparseIntMatrices */

        boolean isSuccessful = false;
        /* initialization of the variable to false so that we can set it to true on successful addition operation */

        if (this.getNumCols() == other.getNumCols() && this.getNumRows() == other.getNumRows()){
            /* checking if the dimensions of the two matrices to be added are same or not */

            for(int row = 0; row < this.numRows; row++) {

                MatrixEntry[] ptrOtherRowArray = other.getRowHeaderArray();
                MatrixEntry ptrOther = ptrOtherRowArray[row];

                while (ptrOther != null) {

                    int otherRow = ptrOther.getRow();
                    int otherCol = ptrOther.getColumn();

                    int thisElement = this.getElement(otherRow, otherCol);
                    int otherElement = other.getElement(otherRow, otherCol);
                    if (thisElement == 0) {
                        this.setElement(otherRow, otherCol, otherElement);
                    } else {
                        this.setElement(otherRow, otherCol, thisElement + otherElement);
                    }
                    ptrOther = ptrOther.getNextColumn();

                }
            }
            isSuccessful = true;
        }
        /* addition logic to add two sparseIntMatrices */

        return  isSuccessful;
    }

    public boolean minus(SparseIntMatrix other){ /* minus method to subtract two sparseIntMatrices */

        boolean isSuccessful = false;
        /* initialization of the variable to false so that we can set it to true on successful subtraction operation */

        if (this.getNumCols() == other.getNumCols() && this.getNumRows() == other.getNumRows()){
            /* checking if the dimensions of the two matrices to be subtracted are same or not */

            for(int row = 0; row < this.numRows; row++) {

                MatrixEntry[] ptrOtherRowArray = other.getRowHeaderArray();
                MatrixEntry ptrOther = ptrOtherRowArray[row];

                while (ptrOther != null) {

                    int otherRow = ptrOther.getRow();
                    int otherCol = ptrOther.getColumn();

                    int thisElement = this.getElement(otherRow, otherCol);
                    int otherElement = other.getElement(otherRow, otherCol);
                    if (thisElement == 0) {
                        this.setElement(otherRow, otherCol, -1 * (otherElement));
                    } else {
                        this.setElement(otherRow, otherCol, thisElement - otherElement);
                    }
                    ptrOther = ptrOther.getNextColumn();

                }
            }
            isSuccessful = true;
        }
        /* subtraction logic to subtract two sparseIntMatrices */

        return isSuccessful;
    }

    public static void main(String[] args) {

        SparseIntMatrix mat = new SparseIntMatrix(1000,1000,"src/matrix1_data.txt");
        MatrixViewer.show(mat);
        /* populating sparseIntMatrix with elements specified in the matrix1_data.txt file */

        SparseIntMatrix mat1 = new SparseIntMatrix(1000,1000,"src/matrix2_noise.txt");
        MatrixViewer.show(mat1);
        /* populating sparseIntMatrix with elements specified in the matrix2_noise.txt file */

        SparseIntMatrix mat2 = new SparseIntMatrix(1000,1000,"src/matrix2_data.txt");
        MatrixViewer.show(mat2);
        /* populating sparseIntMatrix with elements specified in the matrix2_data.txt file */

        System.out.println(mat2.minus(mat1));
        MatrixViewer.show(mat2);
        /* subtracting the matrix 1 from matrix 2 */

    }

}
