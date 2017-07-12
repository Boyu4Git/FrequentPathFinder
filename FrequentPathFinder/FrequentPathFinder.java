package FrequentPathFinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class FrequentPathFinder {
	private Map<String, Path> userMap = new HashMap<>();
	private Map<String, Integer> pathMap = new HashMap<>();
	private Queue<String> queue;
	private Set<String> set = new HashSet<>();
	private int topN;
	//pageNum is the "3-page"'s 3. It is 3 by default
	private int pageNum = 3;
	public FrequentPathFinder (int topN){
		queue = new PriorityQueue<>(new Comparator<String>(){
			public int compare(String s1, String s2){
				return pathMap.get(s1) - pathMap.get(s2);
			}
		});
		this.topN = topN;
	}
	//You can set the pathNum 
	public FrequentPathFinder(int topN, int pageNum){
		this(topN);
		this.pageNum = pageNum;
	}
	
	public void addPathLog(List<VisitLog> logs) {
		for (VisitLog log : logs) {
			addPath(log);
		}
	}
	
	
	public List<String> getTopVisitedPages() {
		Queue<String> temp = getSortedPath();
		List<String> res = new ArrayList<>();
		while(!temp.isEmpty()) {
			String pages = temp.poll();
			pages = pages + " : "+ pathMap.get(pages);
			if (pages != null) {
				res.add(pages);
			}
		}
		return res;
	}
	//Since the queue is a minHeap, we need to sort it with reverse order.
	//The stack won't do the work because addToQueue method may not make the queue ordered exactly correct
	private Queue<String> getSortedPath() {
		Queue<String> res = new  PriorityQueue<>(new Comparator<String>(){
			public int compare(String s1, String s2){
				return pathMap.get(s2) - pathMap.get(s1);
			}
		});
		Queue<String> temp = queue;
		while(!queue.isEmpty()) {
			res.offer(queue.poll());
		}
		queue = temp;
		return res;
	}

	//This method can keep topN frequent visited path,but not necessarily ordered.
	//Because the queue only keep topN path,if the set contains the path, 
	//it means the queue already has the the path, so there is no operation here.
	//it is costly if you remove the original path and add the path here again,
	//although it can keep the queue ordered.
	private void addToQueue(String pages) {
		if (set.contains(pages)) {
			return ;
		}
		if (queue.size() == topN) {
			if (pathMap.get(queue.peek()) < pathMap.get(pages)) {
				set.remove(queue.poll());
				queue.offer(pages);
				set.add(pages);
			}
		}else {
			queue.offer(pages);
			set.add(pages);
		}
	}
	
	private void addPath(VisitLog log) {
		if (!userMap.containsKey(log.getUser())) {
			userMap.put(log.getUser(), new Path(pageNum));
		}
		String pages = userMap.get(log.getUser()).addPages(log.getPath());		
		if (pages != null) {
			if (!pathMap.containsKey(pages)) {
				pathMap.put(pages, 1);
			}else {
				pathMap.put(pages, pathMap.get(pages) + 1);
			}
			addToQueue(pages);
		}		
	}
}
