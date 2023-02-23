import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class LayoutPacketDecimal{
    static File layout_file;
    static File packed_decimal_file;

    static BufferedReader layout_file_reader;
    static BufferedReader packed_decimal_file_reader;

    static FileWriter fWriter;
    
    static BufferedWriter packed_decimal_writer;
    
    static ArrayList<String> layout_names = new ArrayList<>();
    static ArrayList<Integer> layout_values = new ArrayList<>();

    static Set<Character> posCharSet = new HashSet<>();
    static Set<Character> negCharSet = new HashSet<>();

    public static void main(String[] args) {

        //Creating an object of LayoutPacketDecimal class.
        LayoutPacketDecimal layoutPacketDecimal = new LayoutPacketDecimal();

        //Opening record layout file.
        try {
            layout_file = new File("record layout.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Opening packed decimal test file.
         try {
            packed_decimal_file = new File("packed decimal test file.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Creating a file writer to write result to a result.txt file.
        try {
            fWriter = new FileWriter("result.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initializing packet_decimal_writer.
        packed_decimal_writer = new BufferedWriter(fWriter);

        //Calling createPosNegCharSets to create postive and negative identifier character sets.
        layoutPacketDecimal.createPosNegCharSets();      

        //Calling recordLayout method to read layout file, and write formatted layout names to result.txt.
        layoutPacketDecimal.recordLayout();  
        
        //Calling packetDecimal method to read Packet Decimal test file and format data according to record layout file.
        layoutPacketDecimal.packetDecimal();
    }

    /*
     * Reads data from layout file.
     * Builds a StringBuilder to write the layout names to the result.txt file.
     * Writes formatted layout names to result.txt
     */
    public void recordLayout(){
        try{
            layout_file_reader = new BufferedReader(new FileReader(layout_file));
            
            String st;
            while((st = layout_file_reader.readLine()) != null){
                //Splitting the values with tab delimiter and adding the layout names and values to the arraylist.
                String arr[] = st.split("\t");
                layout_names.add(arr[0]);
                layout_values.add(Integer.parseInt(arr[1]));
            }

            //Building layout names with "|" pipe delimiter.
            StringBuilder sb_layout_names = new StringBuilder("");
            for(String string : layout_names){
                sb_layout_names.append(string).append("|");
            } 
           // System.out.println(sb_layout_names);

            //Writing formatted layout names to result.txt.
            try{
                packed_decimal_writer.write(sb_layout_names.toString());
                packed_decimal_writer.newLine();
            } catch(Exception e){
                e.printStackTrace();
            }
            
            layout_file_reader.close();
        } catch(Exception e){
            e.printStackTrace();
        } 
    }

    /*
     * Reads data from packet decimal test file.
     * Formats the data in the file according to the record layout file.
     * i.e value ranges, positive and negative values based on given character sets.
     * 
     */
    public void packetDecimal(){
        try{
            packed_decimal_file_reader = new BufferedReader(new FileReader(packed_decimal_file));
        
            String st;
            while((st = packed_decimal_file_reader.readLine()) != null){
                //split_value variable used to get the substring based on the values in record layout file.
                int split_value = 0;
                StringBuilder sb_layout_values = new StringBuilder("");
                for(int val : layout_values){
                    //Getting substring based on record layout values 
                    String packed_decimal_string = st.substring(split_value, split_value + val);
                    split_value += val;
                    String temp_val;

                    //Checking the last character of substring to assign positive or negative sign.
                    if(posCharSet.contains(packed_decimal_string.charAt(packed_decimal_string.length() - 1))){
                        temp_val = packed_decimal_string.substring(0, packed_decimal_string.length() - 1);
                    } else if(negCharSet.contains(packed_decimal_string.charAt(packed_decimal_string.length() - 1))){
                        temp_val = "-" + packed_decimal_string.substring(0, packed_decimal_string.length() - 1);
                    } else{
                        temp_val = packed_decimal_string;
                    }

                    //Building the final string with "|" pipe delimiter.
                    sb_layout_values.append(temp_val).append("|");
                }
                // System.out.println(sb_layout_values);

                //Writing formatted final string to result.txt.
                try{
                    packed_decimal_writer.write(sb_layout_values.toString());
                    packed_decimal_writer.newLine();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            //Closing packet decimal file reader.
            packed_decimal_file_reader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            //Closing packet decimal writer.
            packed_decimal_writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Creates positive and negative character sets from given data in readme.txt file
     * Positive numbers are identified by the following characters "{,A,B,C,D,E,F,G,H,I"
     * Negative numbers are itdentied by the following characters "},J,K,L,M,N,O,P,Q,R"
     */
    public void createPosNegCharSets(){
        //Creating a set for positive character array.
        char positive_char_array[] = {'{','A','B','C','D','E','F','G','H','I'};
        for(char i : positive_char_array){
            posCharSet.add(i);
        }

        //Creating a set for negative character array.
        char negative_char_array[] = {'}','J','K','L','M','N','O','P','Q','R'};
        for(char i : negative_char_array){
            negCharSet.add(i);
        }
    }
}