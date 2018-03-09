public class MatrixEntry{ /* Node definition for matrix elements */

    private int row;
    /* row number of the matrix element to enter */
    private int col;
    /* column number of the matrix element to enter */
    private int data;
    /* data of the matrix element to enter */

    private MatrixEntry nextRow;
    /* pointer to the next element in the current row */
    private MatrixEntry nextCol;
    /* pointer to the next element in the current column */

    public MatrixEntry(int row, int col, int data){/* default constructor to set the row, column and data of the matrix element */

        this.row = row;
        /* set the row for the matrix element */
        this.col = col;
        /* set the column for the matrix element */
        this.data = data;
        /* set the data for the matrix element */

        nextRow = null;
        /* the default row pointer from the current to the next element in the current row */
        nextCol = null;
        /* the default column pointer from the current to the next element in the current column */
    }

    public int getRow(){ /* the get method to get the row number of the current element */
        return this.row;
    }
    public void setRow(int row){ /* the set method to set the row number of the current element */
        this.row = row;
    }


    public int getColumn(){ /* the get method to get the column number of the current element */
        return this.col;
    }
    public void setColumn(int col){ /* the set method to set the column number of the current element */
        this.col = col;
    }


    public int getData(){ /* the get method to get the data of the current element */
        return this.data;
    }
    public void setData(int data){ /* the set method to set the data of the current element */
        this.data = data;
    }


    public MatrixEntry getNextRow(){ /* the get method to get the current pointer reference to the next element in the same row */
        return nextRow;
    }
    public void setNextRow(MatrixEntry el){ /* the set method to set the current pointer reference to the next element in the same row*/
        nextRow = el;
    }


    public MatrixEntry getNextColumn(){ /* the get method to get the current pointer reference to the next element in the same column */
        return nextCol;
    }
    public void setNextColumn(MatrixEntry el){ /* the set method to set the current pointer reference to the next element in the same column*/
        nextCol = el;
    }

}
