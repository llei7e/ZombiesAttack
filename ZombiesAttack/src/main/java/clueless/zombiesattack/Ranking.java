package clueless.zombiesattack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Ranking {
    private ArrayList<Winner> winners; //stores the winners
    private File archive; //represents the winners storage file

    public Ranking() {

        try {
            this.archive = new File("ZombiesAttack/src/main/resources/ranking.txt");

            if (!this.archive.exists()) {
                this.archive.createNewFile(); //if the file does not exist, create one
            }

            this.winners = this.getWinnersFromArchive(); //initialize file data
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    //read the winners saved in the file
    private ArrayList<Winner> getWinnersFromArchive() {
        ArrayList<Winner> savedWinners = new ArrayList<>();

        try {
            FileReader fr = new FileReader(this.archive);
            BufferedReader br = new BufferedReader(fr);

            //divides each line into parts
            while (br.ready()) {
                String line = br.readLine();
                String[] parts = line.split(";");
                String name = parts[0];
                int points = Integer.parseInt(parts[1]);

                //Creates "Winner" objects with this data and adds savedWinners to the ArrayList.
                Winner winner = new Winner(name, points);
                savedWinners.add(winner);
            }

            br.close();
            fr.close();
        } catch (IOException err) {
            System.out.println(err.toString());
        }

        return savedWinners;
    }

    private boolean setWinnersToArchive() {
        try {
            FileWriter fw = new FileWriter(this.archive);
            BufferedWriter bw = new BufferedWriter(fw);

            //write the winners to the file
            for (Winner winner : this.winners) {
                bw.write(winner.getName() + ";" + winner.getPoints());
                bw.newLine();
            }

            bw.close();
            fw.close();

            return true;
        } catch (IOException err) {
            System.out.println(err.toString());
            return false;
        }
    }

    //arrange the list in descending order
    private void sortWinners() {
        Collections.sort(this.winners, new Comparator<Winner>() {
            @Override
            public int compare(Winner w1, Winner w2) {
                return Integer.compare(w2.getPoints(), w1.getPoints());
            }
        });
    }

    //adds the Winners objects to the winner list and saves the updated list to the file
    public boolean saveWinner(String name, int points) {
        Winner winner = new Winner(name, points);
        this.winners.add(winner);
        this.sortWinners();

        //limits number of winners
        if (this.winners.size() > 5) {
            this.winners.remove(this.winners.size() - 1);
        }

        return this.setWinnersToArchive();
    }


    //returns the ArrayList of winners
    public ArrayList<Winner> getWinners() {
        return this.winners;
    }
}
