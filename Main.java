
import java.io.*;

import Html.*;

import java.util.*;


public class Main{
    public static void main (String[] args) throws IOException{
        Html h = new Html();
        h.readFromFile("input1.html");
        try (PrintStream ps1 = new PrintStream("output1.out");
             PrintStream ps2 = new PrintStream("output2.out");
             PrintStream ps3 = new PrintStream("output3.out");
             Scanner sc = new Scanner(new File("input2.in"))){
            ps1.print(h.findTags());
            StringBuilder context = new StringBuilder();

            while (sc.hasNextLine()){
                context.append(sc.nextLine());
            }
            Map<String, Integer> map = h.findContext(context.toString());
            for (Map.Entry<String, Integer> item : map.entrySet()){
                ps2.println(item.getKey() + " - " + item.getValue());
                if (item.getValue() == -1){
                    ps3.println(item.getKey());
                }
            }
        }
    }
}
