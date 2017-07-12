package FrequentPathFinder;

import java.util.ArrayList;
import java.util.List;
public class Test{

    public static void main(String []args){
    	FrequentPathFinder fpf = new FrequentPathFinder(3);
    	List<VisitLog> input = new ArrayList<>();
    	input.add(new VisitLog("u1", "/"));
    	input.add(new VisitLog("u1", "login"));
    	input.add(new VisitLog("u1", "subscriber"));
    	input.add(new VisitLog("u2", "/"));
    	input.add(new VisitLog("u2", "login"));
    	input.add(new VisitLog("u2", "subscriber"));
    	input.add(new VisitLog("u3", "/"));
    	input.add(new VisitLog("u3", "login"));
    	input.add(new VisitLog("u3", "product"));
    	input.add(new VisitLog("u1", "/"));
    	input.add(new VisitLog("u4", "/"));
    	input.add(new VisitLog("u4", "login"));
    	input.add(new VisitLog("u4", "product"));
    	input.add(new VisitLog("u5", "/"));
    	input.add(new VisitLog("u5", "login"));
    	input.add(new VisitLog("u5", "subscriber"));    	
    	fpf.addPathLog(input);
    	for (String str : fpf.getTopVisitedPages()) {
    		System.out.println(str);
    	}
    }

}
