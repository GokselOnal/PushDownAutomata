public class main {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile();
        String text = rf.getText();
        StringParser sp = new StringParser(text);
        PDA pda = new PDA(sp);
        Results result = new Results(pda, pda.inputs);
    }
}
