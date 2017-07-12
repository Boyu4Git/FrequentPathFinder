# FrequentPathFinder

The Problem:

Given a dataset that represents a user's navigation of a website, use Java to implement a function to find the top N most frequently visited paths.

The Data:

The data comes from a web server's access logs where you typically get the following fields: timestamp, IP address, request string, response code, user agent and cookies. For brevity, we provide a dataset that has the user and page parsed out.
User

Page

U1

/

U1

login

U1

subscriber

U2

/

U2

login

U2

subscriber

U3

/

U3

login

U3

product

U1

/

U4

/

U4

login

U4

product

U5

/

U5

login

U5

subscriber

The following words are used to describe the function that needs to be written: Find the top N most popular 3-page paths, where a path is three sequential page visits by a user.
The example I use is from U1, where the traversal is: / -> login -> subscriber -> /
In the above example, we have two paths:
1.      / -> login -> subscriber
2.      login -> subscriber -> /
Expected Output

Examples of output for the above example data.
Top 10

/ -> login -> subscribers : 3
/ -> login -> product : 2
login -> subscriber -> / : 1
Top 2

/ -> login -> subscribers : 3
/ -> login -> product : 2

----------------------------------------------------------------------------------------------------

Added feature: 
---
  instead of only 3-page paths, you can configure to any number of page, the default number is 3.

Assumption: 
---
1. From the example, I assume the user behavior is consistent from the log. 
So
U1 “/”
U1 “login”
U1 “subscriber”
U1 “/” 
the path is “/-> login -> subscriber -> /“. U1 can not go to subscriber directly.like this: “/ -> login” and “subscriber -> /” so that there is no path from “login” to “subscriber”

2. this solution is for a log file and not for live traffic. Because keeping all the users in map without expiring them is very bad. So the size of input is able to fit in the memory.

3. The possible paths variation should be much smaller than users.

Solution:
---
Input data: I assume it’s a log file as example input, so that I can parse each of entry to an Object VisitLog. For easy test, I just created a ArrayList<VisitLog> for the input.

Data Structure and Algorithms:
---
Class FrequentPathFinder maintains
  A userMap, the key is the “N-page”(by default it is 3-page, you can config it to any number you like for scalability) path string. The    value is the “N-page” Path.

  A pathMap, the key is the “N-page” path string, the value is counter for visit times.

  A PriorityQueue, maintains the TopN frequent visited “N-pages”  

  A HashSet maintains the paths that the PriorityQueue have which make sure the PriorityQueue does not have duplicate paths. 

Methods:
	Initiator
		FrequentPathFinder (int topN)
		FrequentPathFinder(int topN, int popularNum) you can set the popularNum to any number, the default is 3, because you are asking “3-page”.
	getTopPages() return the list of topN frequent visitedPath
	addPathLog(List<VisitLog> logs) add list of VisitLog  

Class VisitLog
	Domain class, only have user and currentPath

Class Path
	Maintains a Queue that have N-page(default 3-page) path maximal.
	getPath() return the current path if it reaches “N-page”, return null otherwise.
	addPath(String page) add new path and remove the very first path if needed.    


The time complexity is O(n * log(m))
The space complexity is O(n) 
n is the log entry size, m is topN’s N.
