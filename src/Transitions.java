import java.util.HashMap;

public class Transitions {
    public HashMap<String[], String> table;

    public Transitions(String[] transitions){
        this.table = new HashMap();
        addItem(transitions);
    }

    //key is used for right side of rotation and stack operations, value is used for target state(left) of given key.
    //example, format of transition: q2 1 Y e q3
    //hashtable -> {["q2","1","Y","e"]": "q3"} (key,value)
    //first two string of key and value represent transitions route: ("q2","1" -> "q3") goes from q2 to q3 state with input 1.
    //last two string of key represent stack operations, 3th item of the key is popped,
             //4th item of the key is pushed to stack. (Y is popped, nothing is pushed)


    public void addItem(String[] transitions){
        for(String rules : transitions){
            String[] item = rules.split(" ");
            String[] route = { item[0], item[1], item[2], item[3]};
            String stack_ops = item[4];
            this.table.put(route,stack_ops);
        }
    }

    public HashMap<String[], String> getTable(){
        return this.table;
    }
}
