package FrequentPathFinder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Path {
	private Queue<String> pathQueue = new LinkedList<>();
	//pageNum is the "3-page"'s 3.
	int pageNum;
	public Path(int pageNum) {
		this.pageNum = pageNum;
	}
	// return the path string only if the pathQueue's size is equals to pageNum
	public String getPath() {
		if (pathQueue.size() == pageNum){
			StringBuilder sb = new StringBuilder();
			Iterator<String> it = pathQueue.iterator();
			while(it.hasNext()) {
				sb.append(it.next());
				sb.append("->");
			}
			return sb.substring(0, sb.length() - 2).toString();
		}
		return null;
	}
	//add current path to the pathQueue, poll the very first path if the size is more than the pageNum
	public String addPages(String page) {
		if (pathQueue.size() == pageNum){
			pathQueue.poll();
			pathQueue.offer(page);
		}else {
			pathQueue.offer(page);
		}
		return getPath();
	}

}