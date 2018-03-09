Summary: Many real-world applications rely on processing of large 2D matrices that often have few non-zero elements relative to 
the total number of entries in the matrix. For Project #3, you will use linked lists to implement a memory-efficient, sparse 2-dimensional 
matrix data structure. Then, you will test your data structure by loading in some large 1000x1000 matrices, which we have constructed to 
contain hidden patterns that will be visible if you have implemented your matrix correctly.

DIRECTION: go to src directory and run SparseIntMatrix.java

Overview of a Linked List Sparse Matrix Data Structure Design.
A typical linked list implementation of a sparse matrix relies on two sets of interconnected linked lists: one for 
the rows of the matrix, and one for the columns of the matrix. On both sides, an array can be used to keep track of the 
heads of each linked list, and each element is part of one column linked list as well as one row linked list. 

Part I. Defining and Implementing a MatrixEntry class
The MatrixEntry class contains all of the data members necessary for storing the data and links for each element 
of the sparse matrix. Define and implement this class including the following methods:
• Name: MatrixEntry(int row, int col, int data) (constructor); input: the row, column, and data associated 
  with this matrix element; output: an instance of the MatrixEntry class
• Name: getColumn(); input: none; output: an integer corresponding to the column of this entry
• Name: setColumn(int col); input: integer corresponding to the column of this entry; output: none
• Name: getRow(); input: none; output: an integer corresponding to the row of this entry
• Name: setRow(int row); input: integer corresponding to the row of this entry; output: none
• Name: getData(); input: none; output: an integer corresponding to the data associated with this entry
• Name: setData(int data); input: integer with the data for this matrix entry; output: none:
• Name: getNextColumn(); input: none; output: a reference to the next matrix element in the current element’s column
• Name: setNextColumn(MatrixEntry el); input: MatrixEntry reference of next element in this element’s column; output: none
• Name: getNextRow(); input: none; output: a reference to the next matrix element in the current element’s row
• Name: setNextRow(MatrixEntry el); input: MatrixEntry reference of next element in this element’s row; output: none
Data members: any that are necessary to maintain the object’s state and implement the methods above. 
The MatrixEntry class should be defined as a separate class from the SparseIntMatrix class described below, 
and all data members should be declared private. Your SparseIntMatrix class client should interact with MatrixEntry 
objects through the public accessor/mutator methods.

Part II. (60 points) Defining and Implementing a SparseIntMatrix class
Your second goal is to design and implement the SparseIntMatrix class, which should provide the basic functionality 
of a matrix, but use the linked list structure described above (NOTE: you should not just use a 2D array here—you will 
receive 0 points for a 2D array-based solution).
The SparseIntMatrix class should have all of the following methods:
• Name: SparseIntMatrix(int numRows, int numCols) (constructor); Input: integers with the number of rows and number 
  columns in this matrix; output: instance of this class
• Name: SparseIntMatrix(int numRows, int numCols, String inputFile) (constructor); Input: integers with the number 
  of rows and number columns in this matrix, and a String with the filename of a file with matrix data. The format 
  of the input file should be comma- delimited lines with the row,column,data of each element. This constructor should 
  populate the matrix with this data; output: instance of this class
• Name: getElement(int row, int col); input: integers with the row and column of the desired element; output: corresponding 
  element (integer) if one exists or zero otherwise.
• Name: setElement(int row, int col, int data); input: integers with the row and column of the element to be set and 
  an integer with the matrix data; output: boolean indicating if operation was successful (e.g. row/col were in the correct range)
• Name: getNumCols(); input: none; output: integer with the number of columns in this matrix
• Name: getNumRows(); input: none; output: integer with the number of rows in this matrix
• Name: plus(SparseIntMatrix otherMat); input: another sparse matrix to be added to the current one. 
  This method should update the state of the current object; output: boolean indicating if addition 
  was successful (matrices should be identical dimensions)
• Name: minus(SparseIntMatrix otherMat); input: another sparse matrix to be subtracted from the current one. 
  This method should update the state of the current object; output: boolean indicating if addition was successful (matrices should be identical dimensions)
Data members: All data members should be declared private.

