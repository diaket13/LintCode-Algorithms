package questionsList.title615;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;


public class Title615 {

    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //妈的 逼老子用DFS
        int[] cirleFlag = new int[numCourses]; 
        HashMap<Integer,Stack<Integer>> map = new HashMap<Integer,Stack<Integer>>();
        Stack<Integer> queue = new Stack<Integer>();
        for(int i =0; i<cirleFlag.length; i++){
        	cirleFlag[i] = 0;
        }
        for(int[] a : prerequisites){
        	Stack<Integer> hashSet = map.get(a[0]);
        	if(hashSet == null) {
        		hashSet = new Stack<Integer>();
        	}
        	hashSet.add(a[1]);
        	map.put(a[0], hashSet);
        }
        for(int i =0; i<cirleFlag.length; i++){
        	if(cirleFlag[i] == 0) {
        		Stack<Integer> hashSet = map.get(i);
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
    
    public void iterationDfs(int[] cirleFlag,int index,HashMap<Integer,Stack<Integer>> map,Stack<Integer> queue,Boolean flag) {
    	if(cirleFlag[index] == 0) {
    		Stack<Integer> behindElement = map.get(index);
    		if(behindElement  == null || behindElement.isEmpty()) {
    			cirleFlag[index] = 1;
    			while(!queue.empty()) {
    				index = queue.pop();
    				Stack<Integer> nextElement = map.get(index);
    				while(behindElement  != null || !behindElement.empty()) {
    					queue.push(index);
    					index = behindElement.pop();
    				}
    			}
    		}else {
    			queue.push(index);
    			cirleFlag[index] = -1;
    			index = behindElement.pop();
    		}
    	}
    	if(cirleFlag[index] == 1) {
    		if(queue.empty()) {
				return;
			}else {
				index = queue.pop();
				Stack<Integer> behindElement = map.get(index);
			}
    	}
    	if(cirleFlag[index] == -1) {
    		flag = false;
    	}
    }
    
    public boolean canFinishBybfs1(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for(int i =0; i<indegree.length; i++){
            indegree[i] = 0;
        }
        for(int[] a : prerequisites){
            indegree[a[1]]++;
        }
        int num = prerequisites.length;
        while(num > 0){
            boolean fundZero = false;
            for(int i =0; i<indegree.length; i++){
                if(indegree[i] == 0){
                    indegree[i]--;
                    fundZero = true;
                    for(int j=0;j<prerequisites.length;j++){
                        if(prerequisites[j][0]==i){
                            num--;
                            indegree[prerequisites[j][1]]--;
                        }
                    }
                    break;
                }
            }
            if(!fundZero){
                return false;
            }
        }
        return true;
    
    }
    
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
	}

}
