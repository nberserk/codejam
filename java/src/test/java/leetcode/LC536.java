package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class LC536 {
    public TreeNode str2tree(String s) {
        TreeNode root = null;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='-' || (c>='0' && c<='9')){
                int start=i;
                while(i+1<s.length()&& s.charAt(i+1)>='0' && s.charAt(i+1)<='9' )
                    i++;
                Integer v = Integer.valueOf(s.substring(start, i+1));
                TreeNode n = new TreeNode(v);
                if(!stack.isEmpty()){
                    if(stack.peek().left!=null) stack.peek().right=n;
                    else stack.peek().left=n;
                }
                stack.push(n);
            }else if(c==')')
                stack.pop();
        }
        if(!stack.isEmpty()) root=stack.pop();
        return root;
    }

    public TreeNode str2tree_1st(String s) {
        if(s.length()==0) return null;
        int n=-1;
        boolean minus=false;
        TreeNode root=null;
        Stack<TreeNode> parent = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c>='0' && c<='9'){
                if(n==-1) n=c-'0';
                else n=n*10 + c-'0';
            }

            else if(c=='('){
                if(n!=-1){
                    if(minus) n=-n;
                    TreeNode t = new TreeNode(n);
                    if(!parent.isEmpty() && parent.peek().left==null)
                        parent.peek().left = t;
                    else if(!parent.isEmpty() && parent.peek().left!=null)
                        parent.peek().right = t;
                    parent.push(t);
                    if(root==null) root=t;
                    n=-1;
                    minus=false;
                }
            }else if(c==')'){
                if(n!=-1){
                    if(minus) n=-n;
                    TreeNode t = new TreeNode(n);
                    if(!parent.isEmpty() && parent.peek().left==null)
                        parent.peek().left = t;
                    else if(!parent.isEmpty() && parent.peek().left!=null)
                        parent.peek().right = t;
                    n=-1;
                    minus=false;
                }else{
                    if(parent.size()>0) parent.pop();
                }
            }else if(c=='-'){
                minus=true;
            }
        }
        if(n!=-1){
            if(minus) n=-n;
            root = new TreeNode(n);
        }
        return root;
    }

    @Test
    public void test(){
        Assert.assertEquals(5, str2tree("4(2(3)(1))(6(5))").right.left.val);
        Assert.assertEquals(-4, str2tree("-4").val);
        Assert.assertEquals(null, str2tree(""));
    }
}
