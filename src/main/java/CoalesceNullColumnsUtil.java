import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CoalesceNullColumnsUtil {
    public static void main(String[] args) {
        if(args.length < 2) {
            throw new RuntimeException("Missing parameters");
        }

        String viewFile = args[0];
        String nullColumnFile = args[1];

        // Read the file contents
        List<String> viewFileLines = new ArrayList<>();
        Set<String> nullColumnLines = new HashSet<>();
        try(BufferedReader viewReader = new BufferedReader(new FileReader(viewFile));
            BufferedReader nullColumnReader = new BufferedReader(new FileReader(nullColumnFile))) {
            String line;
            while( (line = viewReader.readLine()) != null) {
                if(!line.trim().isEmpty()) {
                    viewFileLines.add(line.trim());
                }
            }
            while( (line = nullColumnReader.readLine()) != null ) {
                if(!line.trim().isEmpty()) {
                    nullColumnLines.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parse the lines and prepare final select list
        StringBuilder sb = new StringBuilder();
        for(String line: viewFileLines) {
            if(line.startsWith("cast")) {
                String columnName = line.substring(14, line.indexOf(',') );
                if(nullColumnLines.contains(columnName)) {
                    sb.append(line);
                } else {
                    sb.append(columnName).append(',');
                }
            } else {
                sb.append(line);
            }

            sb.append("\n");
        }

        // For now print it to console
        System.out.println(sb.toString());
    }
}
