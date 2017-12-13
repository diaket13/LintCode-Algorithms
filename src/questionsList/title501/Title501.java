package questionsList.title501;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
class Tweet{
	static private int allId = 0;
	 public int id;
	 public int user_id;
	 public String text;
	 public static Tweet create(int user_id, String tweet_text) {
		 Tweet tweet = new Tweet();
		 tweet.user_id = user_id;
		 tweet.text = tweet_text;
		 tweet.id = ++allId;
		 return tweet;
	 }
}
/**
 * 写个练练手 懒得管CA不CA了 这个太浪费时间
 * @author wangw
 *
 */
public class Title501 {
    static HashMap<Integer,LinkedList<Tweet>> userLine = new HashMap<>();
    static HashMap<Integer,LinkedList<Integer>> followListMap = new HashMap<>();
    public Title501() {
        // do intialization if necessary
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        // write your code here
        Tweet tweet = Tweet.create(user_id,tweet_text);
        LinkedList<Tweet> list = userLine.get(user_id);
        if(list == null){
            list = new LinkedList<Tweet>();
            userLine.put(user_id,list);
        }
        list.add(tweet);
        return tweet;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        // write your code here
        LinkedList<Integer> followList = followListMap.get(user_id);
        if(followList == null || followList.size() == 0){
            return getTimeline(user_id);
        }
        int size = followList.size();
        ArrayList<ArrayList<Tweet>> aa=new ArrayList<ArrayList<Tweet>>(size+1);
        aa.add((ArrayList<Tweet>)getTimeline(user_id));
        Iterator<Integer> iter = followList.iterator();
        while(iter.hasNext()){
            Integer number = iter.next();
            aa.add((ArrayList<Tweet>)getTimeline(number));
        }
        int[] aSize = new int[aa.size()];
        for(int i=0;i<aa.size();i++){
            aSize[i] = aa.get(i).size();
        }
        int n = 10;
        List<Tweet> nList = new ArrayList<Tweet>(10);
        while(n>0){
            int follow = -1;
            Tweet temp = null;
            for(int i=0;i<aa.size();i++){
                if(aSize[i]>0){
                    Tweet tweet = aa.get(i).get(aa.get(i).size() - aSize[i]);
                    if(temp == null){
                        temp = tweet;
                        follow = i;
                    }else if(tweet.id > temp.id){
                        temp = tweet;
                        follow = i;
                    }
                }
            }
            if(temp != null){
                aSize[follow]--;
                n--;
                nList.add(temp);
            }else{
                break;
            }
        }
        return nList;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        // write your code here
        LinkedList<Tweet> list = userLine.get(user_id);
        List<Tweet> nList = new ArrayList<Tweet>(10);
        if(list == null){
            return nList;
        }
        ListIterator<Tweet> itor = list.listIterator();
        int n = 10;
        while(n>0 && itor.hasPrevious()){
            nList.add(itor.previous());
            n--;
        }
        return nList;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        // write your code here
        LinkedList<Integer> followList = followListMap.get(from_user_id);
        if(followList == null){
            followList = new LinkedList<Integer>();
            followListMap.put(from_user_id,followList);
        }
        Iterator<Integer> iter = followList.iterator();
        while(iter.hasNext()){
            if(to_user_id == iter.next()){
                return;
            }
        }
        followList.add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        // write your code here
        LinkedList<Integer> followList = followListMap.get(from_user_id);
        if(followList == null){
            return;
        }
        followList.remove((Object)to_user_id);
    }


}
