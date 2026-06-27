import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {
   


    /**
     * Does the parsing for every question in the file PerguntasPoo.txt
     * @return ArrayList of question stored in the file
     */

    public static ArrayList<Question> getQuestionsFromFile() {
            ArrayList<Question> questionList = new ArrayList<Question>();

            try {
                FileReader frd = new FileReader(new File("PerguntasPoo.txt"));
                BufferedReader fR = new BufferedReader(frd);
                String line; // each line is stored here, replacing the previous
                String[] optionStrings; //temp array where optionStrings are stored
                String[] footPlayersInfo; //temp array with the the format "playerName;shirtNumber"

                while ((line = fR.readLine()) != null) {
                    if (line.contains("//")) {

                        String[] parts = line.split("//"); //Split each line into the diff parts
                        String typeQuestion = parts[0]; // 1st part has the classType
                        optionStrings = parts[2].split(","); //3rd part has the String with options, separated by ","
                        switch (typeQuestion) { //Each quesion type 
                            case ("Arte"):
                                Art artQuestion = new Art();  //Declares Type Question
                                artQuestion.typeQuestion = typeQuestion; 
                                artQuestion.question = parts[1]; //Store the question text
                                for (String item : optionStrings) { //Add each option string to the optionArray
                                    Option tempOption = new Option(item); // create options with the strings in optionStrings
                                    artQuestion.arrayOption.add(tempOption); //add to question optionArray
                                }
                                questionList.add(artQuestion); //adicionar ao array de perguntas final
                                break;

                            case ("Ciencia"):
                                Science sciQuestion = new Science();
                                sciQuestion.typeQuestion = typeQuestion;
                                sciQuestion.question = parts[1];
                                String[] arrayOptionEasy = parts[3].split(","); //Second list with the easy options
                                
                                for (int i = 0; i < optionStrings.length; i++) {
                                    Option tempOption = new Option(optionStrings[i]); 
                                    sciQuestion.arrayOption.add(tempOption); //add to regular options
                                    Option tempOption2 = new Option(arrayOptionEasy[i]);
                                    sciQuestion.arrayOptionEasy.add(tempOption2); //add to easy options
                                }

                                questionList.add(sciQuestion);
                                break;

                            case ("Ski"):
                                Ski skiQuestion = new Ski();                                
                                skiQuestion.typeQuestion = typeQuestion;
                                skiQuestion.question = parts[1];
                                for (String item : optionStrings) {
                                    Option tempOption = new Option(item);
                                    skiQuestion.arrayOption.add(tempOption);
                                }

                                questionList.add(skiQuestion);
                                break;

                            case ("Natacao"):
                                Swimming swimQuestion = new Swimming();
                                swimQuestion.typeQuestion = typeQuestion; 
                                swimQuestion.question = parts[1];
                                for (String item : optionStrings) {
                                    Option tempOption = new Option(item);
                                    swimQuestion.arrayOption.add(tempOption);
                                }

                                questionList.add(swimQuestion);
                                break;

                            case ("Futebol"):
                                Football footQuestion = new Football();
                                footQuestion.typeQuestion = typeQuestion;
                                footQuestion.question = parts[1];
                                for (String item : optionStrings) {
                                    footPlayersInfo = item.split(";"); //Player;shirt -> {player,shirt}
                                    FootballPlayer tempFootballPlayer = new FootballPlayer();
                                    tempFootballPlayer.playerName = footPlayersInfo[0]; //store name
                                    tempFootballPlayer.shirtNumber = footPlayersInfo[1]; //store number
                                    Option tempOption = new Option(footPlayersInfo[1]);
                                    footQuestion.arrayOption.add(tempOption);

                                    footQuestion.arrayPlayers.add(tempFootballPlayer);
                                    
                                }
                                questionList.add(footQuestion);
                                break;
                            default:
                                break;
                        }

                    }

                }

                frd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return questionList;
        }




        /**
         *Get the info from the .dat files
         * @param filename file to extract info from
         * @return extracter Person object
         */

        public static Person getInfoFromFile(String filename){
            Person person = new Person();
            File f = new File("Files" + File.separator + filename);
            try{
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                Object obj = ois.readObject();

                ois.close();
                fis.close();
                person = (Person) obj;

            }
            catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
            
            
            return person;


        }


        /**
         * Creates a new .dat file and saves an object of type Person
         * @param player object Person to be stored
         */
    

        public static void saveGameFile(Person player){

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            String formattedDate = dateFormat.format(new Date());

            String initials = getInitials(player.name);
            String filename = "pootrivia_jogo_" + formattedDate + "_" + initials + ".dat";

            saveFileName(filename);

            File f = new File("Files" + File.separator + filename);
            try {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(player);

                oos.close();
                fos.close();
                
            } catch (FileNotFoundException ex) {
                System.out.println("Erro ao criar ficheiro");
            } catch(IOException ex) {
                System.out.println("Erro ao escrever para o ficheiro");
                ex.printStackTrace();
            }
        }



        /**
         * Get the Initials of a name 
         * @param nome Name 
         * @return String with the Initials
         */

        public static String getInitials(String nome){
            String name = "";
            String[] temp = nome.split(" ");
            for (int i = 0; i < temp.length; i++){
                    name += temp[i].charAt(0);
            }
            name = name.toUpperCase();
            System.out.println(name);
            return name;
        }



        /**
         * Saves into a .txt file a line with the name of teh newly created .dat file, for later easier access to every .dat file
         * @param filename File's name to be stored
         */

        public static void saveFileName(String filename){
            try {

                FileWriter writer = new FileWriter("Files" + File.separator + "filesnames.txt", true);
            
                writer.write(filename);
            
                writer.write(System.lineSeparator()); // Add a newline character

                writer.close();

            
                System.out.println("String saved to file successfully.");
            } catch (IOException e) {
                // Handle exceptions, e.g., if the file can't be created or written to
                e.printStackTrace();
            }

        }

        /**
         * Method that reads every line from the txt file and stores into a ArrayList<String>
         * @return ArrayList<String> with every line from the txt file
         */

        public static ArrayList<String> getTxtLines(){ 
            
            ArrayList<String> lines = new ArrayList<String>();

            try (BufferedReader br = new BufferedReader(new FileReader("Files" + File.separator + "filesnames.txt"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return lines;

        }

        /**
         * Reads and stores the object Person from each .dat file
         * @return ArraList<Person> with the info from every game file
         */
        
        public static ArrayList<Person> readGames(){ 
            ArrayList<Person> games = new ArrayList<>();
            ArrayList<String> fileNames = new ArrayList<>();
            fileNames = getTxtLines();
            for (String line : fileNames){
                games.add(getInfoFromFile(line));
            }
            return games;
        }
    
}
