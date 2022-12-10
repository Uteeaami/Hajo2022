package fi.utu.tech.assignment5;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App5 {
    public static void main( String[] args )
    {
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(20, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }


        //Luodaan uusi Lista gradingtask olioita
        List<GradingTask> gradingTaskList= TaskAllocator.allocate(ungradedSubmissions, 10);
        GradingTask gradingTask;
        Thread temp = null;

        for(int i=0;i<gradingTaskList.size();i++){
            gradingTask = gradingTaskList.get(i);
            temp = new Thread(gradingTask);
            temp.start();
            System.err.println("Started -- "+temp.getName());
        }

        try{
            temp.join();

        }catch (Exception e){
        }

        int count=0;
        for(var s: gradingTaskList){
            for(var x : s.getGradedSubmissions()){
                System.out.println(x);

                System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
                count++;
            }
        }
        System.out.println(count);
    }
}
