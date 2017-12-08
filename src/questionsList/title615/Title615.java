package questionsList.title615;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;


public class Title615 {
	Boolean flag = true;
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		// 妈的 逼老子用DFS
		int[] cirleFlag = new int[numCourses];
		HashMap<Integer, Stack<Integer>> map = new HashMap<Integer, Stack<Integer>>();
		Stack<Integer> queue = new Stack<Integer>();
		for (int i = 0; i < cirleFlag.length; i++) {
			cirleFlag[i] = 0;
		}
		for (int[] a : prerequisites) {
			Stack<Integer> stack = map.get(a[0]);
			if (stack == null) {
				stack = new Stack<Integer>();
			}
			stack.add(a[1]);
			map.put(a[0], stack);
		}
		for (int i = 0; i < cirleFlag.length; i++) {
			if(cirleFlag[i]==0) {
				while(i != -1) {
					i = iterationDfs(cirleFlag, i, map, queue);
					if (!flag) {
						return false;
					}
				}
			}
		}
		return flag;
	}
    public boolean canFinishByDfs(int numCourses, int[][] prerequisites) {
        //妈的 逼老子用DFS
        int[] cirleFlag = new int[numCourses]; 
        HashMap<Integer,HashSet<Integer>> map = new HashMap<Integer,HashSet<Integer>>();
        Stack<Integer> queue = new Stack<Integer>();
        for(int i =0; i<cirleFlag.length; i++){
        	cirleFlag[i] = 0;
        }
        for(int[] a : prerequisites){
        	HashSet<Integer> hashSet = map.get(a[0]);
        	if(hashSet == null) {
        		hashSet = new HashSet<Integer>();
        	}
        	hashSet.add(a[1]);
        	map.put(a[0], hashSet);
        }
        for(int i =0; i<cirleFlag.length; i++){
        	if(cirleFlag[i] == 0) {
        		HashSet<Integer> hashSet = map.get(i);
        		if(hashSet  == null) {
        			cirleFlag[i] = 1;
        		}
        		Iterator<Integer> iter = hashSet.iterator();
        		while(iter.hasNext()) {
        			int x = iter.next();
        			
        		}
        	}
        }
        return true;
    }
    
    public int iterationDfs(int[] cirleFlag,int index,HashMap<Integer,Stack<Integer>> map,Stack<Integer> queue) {
    	if(cirleFlag[index] == 0) {
    		Stack<Integer> behindElement = map.get(index);
    		if(behindElement  == null || behindElement.isEmpty()) {
    			cirleFlag[index] = 1;
    			while(!queue.empty()) {
    				index = queue.pop();
    				Stack<Integer> nextElement = map.get(index);
    				if(nextElement  != null && !nextElement.empty()) {
    					queue.push(index);
    					index = nextElement.pop();
    					//TODO
    					return index;
    				}
    				cirleFlag[index] = 1;
    			}
    		}else {
    			queue.push(index);
    			cirleFlag[index] = -1;
    			return behindElement.pop();
    		}
    	}else if(cirleFlag[index] == 1) {
    		while(!queue.empty()) {
				index = queue.pop();
				Stack<Integer> nextElement = map.get(index);
				if(nextElement  != null && !nextElement.empty()) {
					queue.push(index);
					return nextElement.pop();
					//TODO
				}
				cirleFlag[index] = 1;
			}
    	}else if(cirleFlag[index] == -1) {
    		flag = false;
    	}
    	return -1;
    }

    //AC
    public boolean canFinishByBfs(int numCourses, int[][] prerequisites) {
    	int[] indegree = new int[numCourses];
    	for(int i =0; i<indegree.length; i++){
    		indegree[i] = 0;
    	}
    	HashMap<Integer,Stack<Integer>> map = new HashMap<>();
    	for(int[] a : prerequisites){
    		indegree[a[1]]++;
    		Stack<Integer> stack = map.get(a[0]);
    		if(stack == null) {
    			stack = new Stack<>();
    			map.put(a[0], stack);
    		}
    		stack.add(a[1]);
    	}
    	Queue<Integer> queue = new LinkedList<>();
    	for(int i =0; i<indegree.length; i++){
    		if(indegree[i] == 0) {
    			queue.offer(i);
    		}
    	}
    	while(!queue.isEmpty()){
    		int index = queue.poll();
    		Stack<Integer> stack = map.get(index);
    		while(stack!=null && !stack.empty()) {
    			int i = stack.pop();
    			if(--indegree[i] == 0) {
    				queue.offer(i);
    			}
    		}
    		indegree[index]--;
    	}
    	for(int i =0; i<indegree.length; i++){
    		if(indegree[i] > 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    //空间超了
    public boolean canFinishBydoubleArrays(int numCourses, int[][] prerequisites) {
        // write your code here
        int[][] graph = new int[numCourses][numCourses];
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].length;j++){
                graph[i][j] = -1;
            }
        }
        for(int[] list : prerequisites){
            if(graph[list[0]][list[1]] == 1){
                return false;
            }
            graph[list[1]][list[0]] = 1;
            for(int i = 0; i<numCourses;i++){
                if(graph[list[0]][i]==1){
                    if(graph[i][list[1]]==1){
                        return false;
                    }
                    graph[list[1]][i]=1;
                }
                if(graph[i][list[1]]==1){
                    if(graph[list[0]][i] == 1){
                        return false;
                    }
                    graph[i][list[0]] = 1;
                }
            }
        }
        return true;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		HashMap<Integer,Stack<Integer>> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		map.put(1, stack);
		stack.push(2);
		Stack<Integer> stack2 = new Stack<>();
		stack2.push(3);
		System.out.println("stack"+stack.toString());
		System.out.println("stack2"+stack2.toString());
		System.out.println("map.get(1)"+map.get(1).toString());
		int i = stack.pop();
		stack2.push(i);
		i = stack.pop();
		System.out.println("stack"+stack.toString());
		System.out.println("stack2"+stack2.toString());
		System.out.println(i);
		Title615 title = new Title615();
		int icc =2;
		int[][] xx = {{1,0}};
		System.out.println(title.canFinishByBfs(icc, xx));
	}

}
