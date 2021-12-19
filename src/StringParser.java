import java.util.ArrayList;
import java.util.Arrays;

public class StringParser {
    private int len_var_alphabet;
    private int len_stack_alphabet;
    private int len_goal_states;
    private int len_states;
    public String states;
    public String start_state;
    public String goal_states;
    public String stack_alphabet;
    public String input_alphabet;
    public String initial_stack;
    public String transition;
    public String inputs;

    public StringParser(String text){
            String[] tokens = text.split(";");

            for (int i = 0; i < 3; i++)
                if (i == 0)
                    this.len_var_alphabet = Integer.parseInt(tokens[i]);
                else if (i == 0)
                    this.len_stack_alphabet = Integer.parseInt(tokens[i]);
                else if (i == 1)
                    this.len_goal_states = Integer.parseInt(tokens[i]);
                else if (i == 2)
                    this.len_states = Integer.parseInt(tokens[i]);


            for (int i = 4; i < tokens.length; i++) {
                if (i == 4)
                    this.states = tokens[i];
                else if (i == 5)
                    this.start_state = tokens[i];
                else if (i == 6)
                    this.goal_states = tokens[i];
                else if (i == 7)
                    this.stack_alphabet = tokens[i];
                else if (i == 9)
                    this.input_alphabet = tokens[i];
                else if (i == 8)
                    this.initial_stack = tokens[i];
            }


            this.transition = "";
            this.inputs = "";
            String[] arr_alph = this.input_alphabet.split(" ");
            ArrayList<String> list_alph = new ArrayList<>(Arrays.asList(arr_alph));
            for (int i = 10; i < tokens.length; i++) {
                if (tokens[i] != "" && !list_alph.contains(Character.toString(tokens[i].charAt(0)))) {
                    this.transition += tokens[i] + "#";
                } else {
                    this.inputs += tokens[i] + "#";
                }
            }

    }

    public ArrayList<String[]> getStrings(){
        ArrayList<String[]> list_strings = new ArrayList<String[]>();
        list_strings.add(this.states.split(" "));
        list_strings.add(this.stack_alphabet.split(" "));
        list_strings.add(this.input_alphabet.split(" "));
        list_strings.add(this.start_state.split(" "));
        list_strings.add(this.goal_states.split(" "));
        list_strings.add(this.transition.split("#"));
        list_strings.add(this.inputs.split("#"));
        return list_strings;
    }
}
