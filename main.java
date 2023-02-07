import java.util.ArrayList;
public class main {
    public static void main(String[] argc) {

        Staff object1 = new Staff();
        String[] temp1 =  {"Logan Snow", "Sincere Curry", "Spencer Marshall", "Mylee Fisher", "Caleb Hanna"};
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < temp1.length; i++) {
            temp.add(temp1[i]);
        }
        
        object1.setstaff(temp);
        ArrayList<String> use = object1.getstaff();

        for (int i = 0; i < use.size(); i++) {
            System.out.println(use.get(i));
        }
    }
}
