import java.util.*;

public class Results {

    private PDA automata;
    private HashMap<String[], String> t_table;
    private Stack<String> stack;

    public Results(PDA pda, String[] inputs){
        this.t_table =  pda.transition_table.getTable();

        this.automata = pda;

        //info for inputs
        given_inputs();

        String current_state = this.automata.initial_state;
        System.out.println();
        System.out.println("~~~~~~~~~~~cs410~~~~~~~~~~~");

        for(int i = 0; i < inputs.length; i++) {
            stack = new Stack<String>();
            String[] key_s = null;
            String value_s = "";
            String main_current_state = current_state;

            System.out.print(current_state + " ");



            int len = inputs[i].length();
            for (int j = 0; j < len; j++) {
                char current_char = inputs[i].charAt(j);
                String[] key = null;
                String value = "";

                String next_state = main_current_state + " " + current_char;
                String[] tokens = next_state.split(" ");


                // symbol of # used for to see a rejecting case
                for (String[] k : this.t_table.keySet()) {
                    if (k[0].equals(tokens[0]) && k[1].equals(tokens[1])) {
                        if (k[2].equals("e") && k[3].equals("e")){
                            if (value.equals(""))
                                value = this.t_table.get(k);
                        }
                        else if(k[2].equals("e") && !k[3].equals("e")) {
                            if(value.equals("")){
                                stack.push(k[3]);
                                value = this.t_table.get(k);
                            }
                        }
                        else if(!k[2].equals("e") && k[3].equals("e")){
                            if(stack.lastElement().equals(k[2])){
                                if(value.equals("")){
                                    stack.pop();
                                    value = this.t_table.get(k);
                                }
                            }
                            else{value = "#"; break;}
                        }
                        else if (!k[2].equals("e") && !k[3].equals("e")){
                            if(stack.lastElement().equals(k[2])){
                                if(value.equals("")){
                                    stack.pop();
                                    stack.push(k[3]);
                                    value = this.t_table.get(k);
                                }
                            }
                            else{value = "#"; break;}
                        }
                    }
                }

                //empty transition
                if(value.equals("")){
                    for (String[] k : this.t_table.keySet()) {
                        if (k[0].equals(tokens[0]) && k[1].equals("e")){
                            if(k[2].equals("e") && !k[3].equals("e")){
                                if(value.equals("")){
                                    stack.push(k[3]);
                                    value = this.t_table.get(k);
                                }
                            }

                            else if(!k[2].equals("e") && k[3].equals("e")){
                                if(stack.lastElement().equals(k[2])){
                                    if(value.equals("")){
                                        stack.pop();
                                        value = this.t_table.get(k);
                                    }
                                }
                                else{
                                    value = "#"; break;}
                            }
                            else if (!k[2].equals("e") && !k[3].equals("e")){
                                if(stack.lastElement().equals(k[2])){
                                    if(value.equals("")){
                                        stack.pop();
                                        stack.push(k[3]);
                                        value = this.t_table.get(k);
                                    }
                                }
                                else{value = "#"; break;}
                            }
                            else if(k[2].equals("e") && k[3].equals("e")){
                                if (value.equals(""))
                                    value = this.t_table.get(k);
                            }
                        }
                    }
                    next_state = value;
                    main_current_state = next_state;
                    System.out.print(main_current_state + " ");
                    j--;
                }
                else if (!value.equals("#") && !value.equals("")){
                    next_state = value;
                    main_current_state = next_state;
                    System.out.print(main_current_state + " ");
                }else if(value.equals("#")){
                    main_current_state = "#";
                    break;
                }
            }


            //if ends with e transition
            if(len != 0) {
                String value_f = "";
                String next_state_f = "";
                next_state_f = main_current_state + " " + "e";
                String[] tokens_f = next_state_f.split(" ");

                for (String[] k : this.t_table.keySet()) {
                    if (!tokens_f[0].equals("#")) {
                        if (k[0].equals(tokens_f[0]) && k[1].equals(tokens_f[1])) {
                            if (!k[2].equals("e"))
                                if (stack.lastElement().equals(k[2])) {
                                    if(value_f.equals("")){
                                        stack.pop();
                                        value_f = this.t_table.get(k);
                                    }
                                } else {value_f = "#"; break;}
                        }
                    }else {
                        value_f = "#";
                        break;
                    }
                }

                if(value_f.equals("")){
                    //empty transition
                }
                else if (!value_f.equals("") && !value_f.equals("#")) {
                    String next_state_end = value_f;
                    main_current_state = next_state_end;
                    System.out.print(next_state_end + " ");
                } else {
                    main_current_state = "#";
                }
            }


            //for empty check
            String next = this.automata.initial_state + " " + "e";
            String[] token = next.split(" ");
            String value = "";

            for (String[] k : this.t_table.keySet()) {
                if(k[0].equals(token[0]) && k[1].equals(token[1])){
                    value += this.t_table.get(k);
                }
            }


            System.out.println("(route taken)");
            if (stack.isEmpty() && Arrays.asList(this.automata.final_states).contains(main_current_state)){
                System.out.println("Accepted");
                System.out.println("--------------------------");
            }
            //given empty string
            else if(main_current_state == this.automata.initial_state && Arrays.asList(this.automata.final_states).contains(this.automata.initial_state)){
                System.out.println("Accepted");
                System.out.println("--------------------------");
            }
            else {
                System.out.println("Rejected");
                System.out.println("--------------------------");
            }

            current_state = this.automata.initial_state;
        }
    }

    public void given_inputs(){
        System.out.println();
        System.out.print("Given inputs: ");
        System.out.print(this.automata.inputs[0]);
        for(int i = 1; i < this.automata.inputs.length; i++)
            System.out.print(", " + this.automata.inputs[i]);
        System.out.println();
    }
}
