package LinkedList;

import java.util.ArrayList;

public class test {

    private int x;

    public test (int x)
    {
        this.x = x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public String toString (){
        return "x =" + x + "";
    }

    public static void SwapIt(test s1, test s2, test s3)
    {
        s3 = new test (7);
        s3 = s2;
        s1.setX(2);
        s1=s3;
        s2=s1;
        s2.setX(6);
    }

    public static void main (String[] args)
    {
       test s1 = new test (1);

        test s2 = new test (3);

        test s3 = new test (5);

        SwapIt(s1, s2, s3);
        System.out.println("Output1: "+ s1 + s2 + s3);

        s1 = new test (1);

        s2 = new test (3);

        s3 = new test (5);

        SwapIt(s3, s2,s2);
        System.out.println("\nOutput1: "+ s1 + s2 + s3);
    }

}
