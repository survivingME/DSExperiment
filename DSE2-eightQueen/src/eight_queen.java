import java.io.*;
/**
 * Created by Tremble on 2016/9/23.
 */
public class eight_queen {
    int[] queenList;
    int resultCount, queenNumber;

    public eight_queen(int queenNumber) {
        this.queenNumber = queenNumber;
        queenList = new int[queenNumber];
    }

    public boolean isSafe(int col, int row) {
        int tempCol;
        int tempRow;
        for(tempCol = 0;tempCol < col;tempCol++) {
            tempRow = queenList[tempCol];
            if(row == tempRow) {
                return false;
            } else if(tempRow + tempCol == row + col) {
                return false;
            } else if(tempCol - tempRow == col - row) {
                return false;
            }
        }
        return true;
    }

    public void eight_queen(int col) {
        int row;
        for(row = 0;row < queenNumber;row++) {
            if(isSafe(col, row)) {
                queenList[col] = row;

                if(col == queenNumber-1) {
                    writeResult(queenList);
                    ++resultCount;
                    queenList[col] = 0;
                    return;
                }
                eight_queen(col+1);
                queenList[col] = 0;
            }
        }
    }

    public int getResultCount() {
        return this.resultCount;
    }

    public void writeResult(int[] queenList) {
        char ch[] = new char[40000];
        String str = "";
        for(int i = 0;i < queenList.length;i++) {
            str += "("+queenList[i]+","+i+") ";
        }
        str += "\r\n";
        ch = str.toCharArray();
        try {
            File file = new File("queen.txt");
            FileWriter out = new FileWriter("queen.txt", true);
            try {
                out.write(ch);
                out.flush();
                out.close();
                file.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e){
                e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        eight_queen a = new eight_queen(8);
        a.eight_queen(0);
        System.out.println(a.getResultCount());
    }*/
}
