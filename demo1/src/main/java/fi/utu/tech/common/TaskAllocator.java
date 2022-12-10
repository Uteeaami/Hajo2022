package fi.utu.tech.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You need to modify this file
 */


public class TaskAllocator {

    /**
     * Allocator that creates list of two (2) GradingTask objects with each having half of the given submissions
     * @param submissions The submissions to be allocated
     * @return The two GradingTask objects in a list, each having half of the submissions
     */
    public static List<GradingTask> sloppyAllocator(List<Submission> submissions) {
        List<GradingTask> GradingList = new ArrayList<>();
        GradingTask grading1,grading2;
        List<Submission> tmplist = new ArrayList<>();
        List<Submission> tmplist2 = new ArrayList<>();

        for(int i=0;i<submissions.size();i++){
            if(i > submissions.size()/2){
                tmplist2.add(submissions.get(i));
            }
            else{
                tmplist.add(submissions.get(i));
            }
        }

        grading1 = new GradingTask(tmplist);
        grading2 = new GradingTask(tmplist2);
        GradingList.add(grading1);
        GradingList.add(grading2);

        return GradingList;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {
        //NONII VITTU x2
        List<GradingTask> GradingList = new ArrayList<>();
        GradingTask grading1;
        List<List<Submission>> listList = new ArrayList<>();
        List<Submission> tmpList;
        int taskHelp=taskCount;
        int c=0;

        for(int i=0;i<taskCount;i++){
            tmpList = new ArrayList<>();
            listList.add(tmpList);
        }

        for(int i=0;i<submissions.size();i++){
            tmpList = listList.get(c);
            tmpList.add(submissions.get(i));
            if(i==taskHelp-1){
                c=0;
                taskHelp += taskCount;
                continue;
            }
            c++;
        }
        for(int i=0;i<taskCount;i++){
            grading1= new GradingTask(listList.get(i));
            GradingList.add(grading1);
        }


        return GradingList;
    }
}
