package Html;

import java.util.*;
import java.io.*;


public class Html{
    private List<String> code;

    public Html (){
        code = new ArrayList<>();
    }

    public void readFromFile (String fileName) throws IOException{
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()){
            code.add(scanner.nextLine());
        }
        scanner.close();
    }

    public void print (String fileName) throws IOException{
        PrintStream ps = new PrintStream(fileName);
        for (String item : code){
            ps.println(item);
        }
        ps.close();
    }

    public String findTags (){
        StringBuilder sb = new StringBuilder();
        Set<String> set = new TreeSet<>(new Comparator<String>(){
            @Override
            public int compare (String o1, String o2){
                return o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length();
            }
        });
        int position = 0;
        for (String item : code){
            while ((position = item.indexOf("<", position)) != -1){
                String tmp = item.substring(position, item.indexOf(">", position) + 1);
                if (!tmp.contains("/")){
                    if (!set.contains(tmp)){
                        set.add(tmp);
                    }
                }
                position++;
            }
        }
        for (String item : set){
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

    public Map<String, Integer> findContext (String context){
        String[] find = context.split("([ \n\t]*);([ \n\t]*)");
        Map<String, Integer> map = new HashMap<>();
        for (String word : find){
            map.put(word, -1);
        }

        StringBuilder sb;
        for (Integer i = 0; i < code.size(); i++){
            sb = new StringBuilder();
            for (String item : code.get(i).split("\\<(/?[^>]+)>")){
                sb.append(item.toLowerCase());
            }
            for (String item : find){
                if (sb.indexOf(item) != -1){
                    map.replace(item, -1, i);
                }
            }
        }
        return map;
    }
}
