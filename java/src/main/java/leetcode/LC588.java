package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class LC588 {

    static class Node{
        String name;
        public HashMap<String, Node> child = new HashMap<>();
        String content;
        public Node(String n){
            this.name = n;
        }
    }


    Node root;
    public LC588() {
        root = new Node("");
    }

    public List<String> ls(String path) {
        List<String> ret = new ArrayList<>();
        String[] dir = path.split("/");

        Node cur = root;
        for (int i = 1; i < dir.length; i++) {
            String n = dir[i];
            Node next = cur.child.get(n);
            if(next==null) break;
            cur = next;
        }

        if (cur.child.size()>0){
            for ( String key: cur.child.keySet()){
                ret.add(key);
            }
            Collections.sort(ret);
        }else{
            if(cur.name.length()>0)
                ret.add(cur.name);
        }
        return ret;
    }

    public void mkdir(String path) {
        String[] dir = path.split("/");

        Node cur = root;
        for (int i = 1; i < dir.length; i++) {
            String k = dir[i];
            Node next = cur.child.get(k);
            if(next==null) {
                next = new Node(k);
                cur.child.put(k, next);
            }
            cur = next;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dir = filePath.split("/");

        Node cur = root;
        for (int i = 1; i < dir.length ; i++) {
            Node c = cur.child.get(dir[i]);
            if(c==null){
                c = new Node(dir[i]);
                cur.child.put(dir[i], c);
            }
            cur = c;
        }
        if(cur.content==null)
            cur.content="";
        cur.content +=content;
    }

    public String readContentFromFile(String filePath) {
        String[] dir = filePath.substring(1).split("/");

        Node cur = root;
        for (int i = 0; i < dir.length ; i++) {
            Node c = cur.child.get(dir[i]);
            if(c==null){
                return null;
            }
            cur = c;
        }
        return cur.content;
    }

    @Test
    public void test(){
        LC588 l = new LC588();
        List<String> t = l.ls("/") ;
        l.mkdir("/a/b/c"); ;
        l.addContentToFile("/a/b/c/d", "hello");
        Assert.assertEquals(1, l.ls("/").size());
        String content = l.readContentFromFile("/a/b/c/d");
        Assert.assertEquals("hello", content);
    }
}
