package fi.utu.tech.assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App6{
    public static void main(String[] args) {
        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(2000, 200, Strategy.UNFAIR);

        // Kopioi edellisen tehtävän ratkaisu tähän lähtökohdaksi
        // Voit käyttää yllä olevaa riviä palautusten generointiin. Se ei vaikuta ratkaisuun, mutta
        // "epäreilu" strategia tekee yhdestä palautuksesta paljon muita haastavamman tarkistettavan,
        // demonstroiden dynaamisen työnjaon etuja paremmin.
        long startTime = System.currentTimeMillis();

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        //Luodaan uusi Lista gradingtask olioita
        List<GradingTask> gradingTaskList= TaskAllocator.allocate(ungradedSubmissions, 2000);
        ExecutorService executor = Executors.newFixedThreadPool(2000);

        for(int i=0;i<gradingTaskList.size();i++){
            executor.submit(gradingTaskList.get(i));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                }
        } catch (InterruptedException ex) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        for(var s: gradingTaskList){
            for(var x : s.getGradedSubmissions()){
                System.out.println(x);

                System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
            }
        }

    }


}
