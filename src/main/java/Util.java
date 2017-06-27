
public class Util {
    public static void main(String[] args) {
        String str = "cast(coalesce(col_name, 000) as decimal(3,0)),";
        String columnName = str.substring( 14, str.indexOf(',') );
        System.out.println(columnName);
    }
}
