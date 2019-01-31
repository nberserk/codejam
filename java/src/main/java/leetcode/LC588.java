package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class LC588 {

    static class Node{
        HashMap<String, Node> child = new HashMap<>();
        String content="";
        boolean isDir;
    }

    Node root;
    public LC588() {
        root = new Node();
        root.isDir=true;
    }

    public List<String> ls(String path) {
        List<String> ret = new ArrayList<>();
        String[] dir = path.substring(1).split("/");

        Node cur = root;
        for (String d: dir){
            Node c = cur.child.get(d);
            if(c==null) break;
            cur = c;
        }
        for (String k : cur.child.keySet()){
            ret.add(k);
        }
        return ret;
    }

    public void mkdir(String path) {
        String[] dir = path.substring(1).split("/");

        Node cur = root;
        for(String s: dir){
            Node c = cur.child.get(s);
            if(c==null){
                c = new Node();
                c.isDir=true;
                cur.child.put(s, c);
            }
            cur=c;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dir = filePath.substring(1).split("/");

        Node cur = root;
        for (int i = 0; i < dir.length ; i++) {
            Node c = cur.child.get(dir[i]);
            if(c==null){
                c = new Node();
                cur.child.put(dir[i], c);
            }
            cur = c;
        }
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
        String content = l.readContentFromFile("/a/b/c/d");
        Assert.assertEquals("hello", content);





    }
}
