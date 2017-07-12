package FrequentPathFinder;

public class VisitLog {
	private String user;
	private String path;
	public VisitLog(String user, String path){
		this.user = user;
		this.path = path;
	}
	public String getUser(){
		return user;
	}
	public String getPath() {
		return path;
	}
}
