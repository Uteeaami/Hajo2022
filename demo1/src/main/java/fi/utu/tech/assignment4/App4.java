package fi.utu.tech.assignment4;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
import fi.utu.tech.common.TaskAllocator;
import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App4 {
    public static void main( String[] args ) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }


        //Luodaan uusi Lista gradingtask olioita
        List<GradingTask> gradingTaskList = TaskAllocator.sloppyAllocator(ungradedSubmissions);
        Thread temp = null;

        for(int i=0;i<gradingTaskList.size();i++){
            temp = new Thread(gradingTaskList.get(i));
            temp.start();
            System.err.println("Started -- "+temp.getName());
        }

        try{
            temp.join();
            temp.sleep(300);

        }catch (Exception e){
        }


        for(var s: gradingTaskList){
            for(var x : s.getGradedSubmissions()){
                System.out.println(x);

                System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
            }
        }




    }
}
