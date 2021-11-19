import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        DecimalFormat _numberFormat= new DecimalFormat("#0.000");
    	Scanner in=new Scanner(System.in);
        int i,j;
        //asking users to either put inputs from command line or from txt file.
        
        System.out.println("Choose option");
        System.out.println("1. Encoding message");
        System.out.println("2. Decoding message");
        System.out.println("Select an option: ");
        int option = in.nextInt();
        switch(option)
        {
                case 1:
                    
                    //Reading coded message from text file. 
                    String s = Counter.read_file("text.txt");
                    int[] arr = Counter.count("text.txt");
                    
                    Letter[] letters = Counter.parse(arr);
                    Letter[] sortedLetters = Counter.sorter(letters);
                    Letter[] finalLetters = Counter.deleter(sortedLetters);
                    Node[] rawNodes = Parser.toNodes(finalLetters);
                    Node head = Parser.treeBuilder(rawNodes);

                    Vector<Node> nodes = Parser.toCode(head, "", new Vector<>());
                    for(int i1 = 0; i1 < nodes.size(); i1++) {
                        if(nodes.get(i1).getName().contains("Node")) {
                            nodes.remove(i1);
                            i1--;
                        }
                    }

                    String finalText = Parser.parse(s, nodes);

                    Counter.save(finalText, "new.txt");

                    String loh = "";
                    System.out.println(finalText);

                    for(Node x : nodes) {
                        loh += x.getName() + (char)1 + x.getCode() + (char)2;
                    }
                    Counter.save(loh, "code.txt");
                    break;
                   
                case 2:
                   String a = Encoder.encodedTextToText(Encoder.readCode("code.txt"), Encoder.readDecodedText("new.txt"));
                   // System.out.println(a);
                  String h = new Scanner(new File("text.txt")).nextLine();
                  System.out.println("Original text: " + h);
                  System.out.println("Decoded text: " + a);
                  if (h.equals(a)){
                      System.out.println("The original text and decoded text match.");
                  }else {
                      System.out.println("The original text and decoded text does not match.");
                      break;
                  }
        }                                                 
    }
}
        
     