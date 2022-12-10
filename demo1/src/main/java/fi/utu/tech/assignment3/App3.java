package fi.utu.tech.assignment3;

import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App3 {
    public static void main( String[] args ) {
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }


        //Luodaan uusi arviointitehtävä, jolle annetaan parametrina arviointia odottavat tehtävänpalautukset
        GradingTask gradingTask = new GradingTask(ungradedSubmissions);
        Thread task = new Thread(gradingTask);

        //Käynnistetään säie
        task.start();

        //Odotetaan, että 'task' lopettaa suorituksen
        try{
            task.join();
        }catch (Exception e){}



        // Tulostetaan arvioidut palautukset getterin avulla
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradingTask.getGradedSubmissions()) {
            System.out.println(gs);

            // Lasketaan funktion suoritusaika
            System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
        }

    }
}
