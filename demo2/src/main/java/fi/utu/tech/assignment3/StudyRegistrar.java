package fi.utu.tech.assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class StudyRegistrar extends Thread {

    /**
     * Vaihdettiin submissionQue:n muotoa
     */
    private LinkedBlockingQueue<Submission> submissionQueue;
    private List<StudyRecord> finalGrades;
    private String courseCode;

    public StudyRegistrar(LinkedBlockingQueue<Submission> gradedSubmissions, List<StudyRecord> finalGrades, String courseCode) {
        submissionQueue = gradedSubmissions;
        this.finalGrades = finalGrades;
        this.courseCode = courseCode;
    }

    /**
     * run() -metodi vaati muutoksia, sillä tyyppimuutoksen takia s.getGrade()... ei toiminut
     * .take funktiolla saadaan yksittäinen arviointi ja se laitetaan addtoStudyRegistery metodin parametriksi
     * Toimien jälkeen catch lohko nappaa poikkeuksen. Olisi varmastikin jokin fiksumpi tapa toteuttaa looppi, jottei niin kävisi
     */
    public void run() {
        try{
            while (true) {
                addToStudyRegistery(submissionQueue.take());
            }
        }catch (InterruptedException e){
            System.out.println("Work done");
        }
    }

    void addToStudyRegistery(Submission take) {
        int grade = take.getGrade();
        String name = take.getSubmittedBy();
        System.out.printf("Adding grade %d for %s on %s%n", grade, name, this.courseCode);
        finalGrades.add(new StudyRecord(name, this.courseCode, grade));
    }
}
