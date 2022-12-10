package fi.utu.tech.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.utu.tech.common.GradingTask;
import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App1 {
    public static void main( String[] args )
    {
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        //Generoidaan selkeyden vuoksi uusi lista

        //Luodaan uusi arviointitehtävä, jolle annetaan parametrina arviointia odottavat tehtävänpalautukset
        GradingTask gradingTask = new GradingTask(ungradedSubmissions);


        // Annetaan palautukset gradeAll-metodille ja saadaan arvioidut palautukset takaisin MUOKATTU
        //List<Submission> gradedSubmissions =  gradingTask.gradeAll(ungradedSubmissions);
        /*
         * TODO: Muokkaa common-pakkauksen GradingTask-luokkaa siten,
         * että alla oleva run()-metodi (ilman argumentteja!) tarkistaa palautukset (ungradedSubmissions).
         * Yllä olevaa gt.gradeAll()-metodia ei tule enää käyttää suoraan
         * tästä main-metodista. Tarkemmat ohjeet tehtävänannossa.
         * Joudut keksimään, miten GradingTaskille voi antaa tehtävät ja miten ne siltä saa noukittua
         */

        gradingTask.run();




        // Tulostetaan arvioidut palautukset getterin avulla
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradingTask.getGradedSubmissions()) {
            System.out.println(gs);


        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }


    }
}
