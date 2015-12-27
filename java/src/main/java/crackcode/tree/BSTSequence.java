package main.java.crackcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 4.9 cracking code interview
 */
public class BSTSequence {

    public static void traverse(ArrayList<BSTNode> open,
                                ArrayList<Integer> cur){

        if(open.size()==0){
            System.out.println(cur);
            return;
        }

        for (int i = 0; i < open.size(); i++) {
            BSTNode n = open.get(i);
            ArrayList<BSTNode> nextOpen = new ArrayList<>(open);
            nextOpen.remove(i);
            ArrayList<Integer> nextCur = new ArrayList<>(cur);

            nextCur.add(n.key);
            if(n.left!=null){
                nextOpen.add(n.left);
            }
            if(n.right!=null){
                nextOpen.add(n.right);
            }
            traverse(nextOpen, nextCur);
        }
    }

    public static void printPossibleArrays(BSTNode node){
        ArrayList<BSTNode> open = new ArrayList<>();
        open.add(node);
        traverse(open, new ArrayList<Integer>());
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(2);
        root.add(new BSTNode(1));
        root.add(new BSTNode(4));
        BSTSequence.printPossibleArrays(root);
        ArrayList<LinkedList<Integer>> r = allSequences(root);
        System.out.println(r);

        root.add(new BSTNode(3));
        BSTSequence.printPossibleArrays(root);
        r = allSequences(root);
        System.out.println(r);

        //
        System.out.println("added 5");
        root.add(new BSTNode(5));
        BSTSequence.printPossibleArrays(root);
        r = allSequences(root);
        System.out.println(r);
    }

    public static ArrayList<LinkedList<Integer>> allSequences(BSTNode node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        if (node==null){
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.key);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
        for (LinkedList<Integer> left : leftSeq){
            for(LinkedList<Integer> right: rightSeq){
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    private static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {

        if (first.size() ==0 || second.size()==0){
            LinkedList<Integer> result = (LinkedList<Integer>)prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeFirst();
        second.addFirst(headSecond);
    }
}
