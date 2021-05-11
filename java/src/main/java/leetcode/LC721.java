package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/12/17.
 */
public class AccountsMerge_721 {
    static class Account{
        String name;
        Set<String> emails = new TreeSet<>();
    }

    void merge(Account acc, List<String> list){
        for(int i=1;i<list.size();i++){
            acc.emails.add(list.get(i));
        }
    }

    // assume email as a node
    public List<List<String>> accountsMerge_3(List<List<String>> accounts) {
        int N = accounts.size();

        HashMap<String, String> emailToName = new HashMap<>();
        HashMap<String, Set<String>> edges = new HashMap<>();

        for (List<String> acc: accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                String e = acc.get(i);

                emailToName.put(e, name);

                edges.putIfAbsent(e, new HashSet<>());
                edges.get(e).add(e);
                if(i==1) continue;

                String prev = acc.get(i-1);

                edges.get(e).add(prev);
                edges.get(prev).add(e);
            }
        }


        List<List<String>> ret = new ArrayList<>();
        HashSet<String> v = new HashSet<>();
        for (String key: edges.keySet()) {
            if (v.contains(key)) continue;

            List<String> a = new ArrayList<>();
            a.add(emailToName.get(key));
            TreeSet<String> set = new TreeSet<>();
            traverse_3(edges, set, v, key);
            a.addAll(set);


            ret.add(a);
        }

        return ret;
    }

    void traverse_3(HashMap<String, Set<String>> edges,Set<String> out, Set<String> visited, String cur){
        if(visited.contains(cur)) return;

        visited.add(cur);
        out.add(cur);

        Set<String> connected = edges.get(cur);
        for (String next: connected){
            traverse_3(edges, out, visited, next);
        }
    }

    // assume account as a node
    public List<List<String>> accountsMerge_2(List<List<String>> accounts) {
        int N = accounts.size();
        int[][] graph = new int [N][N];
        List<Set<String>> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], -1);
            Set<String> set = new HashSet<>();
            for (int j = 1; j < accounts.get(i).size(); j++) {
                set.add(accounts.get(i).get(j));
            }
            list.add(set);
        }

        for (int i = 0; i < N; i++) {
            List<String> acc = accounts.get(i);
            for (int j = i+1; j < N; j++) {
                Set<String > other = list.get(j);
                for (int k = 1; k < acc.size(); k++) {
                    if(other.contains(acc.get(k))){
                        graph[i][j]=1;
                        graph[j][i]=1;
                    }
                }
            }
        }


        List<List<String>> ret = new ArrayList<>();
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            if (v[i]==1) continue;

            List<String> a = new ArrayList<>();
            a.add(accounts.get(i).get(0));
            TreeSet<String> set = new TreeSet<>();
            traverse_2(graph, accounts, set, v, i);
            a.addAll(set);


            ret.add(a);
        }

        return ret;
    }

    void traverse_2(int[][] graph, List<List<String>> account, Set<String> out, int[] visited, int cur){
        if(visited[cur]==1) return;

        visited[cur]=1;


        List<String > acc = account.get(cur);
        for (int i = 1; i < acc.size(); i++)
            out.add(acc.get(i));

        // propagte
        for (int i = 0; i < graph.length; i++) {
            if (graph[cur][i]==1)
                traverse_2(graph, account, out, visited, i);
        }
    }

    public List<List<String>> accountsMerge_1(List<List<String>> accounts) {
        HashMap<String, Account> map = new HashMap<>();
        List<Account> list = new ArrayList<>();
        for(List<String> acc: accounts){

            boolean conflict=false;
            Account dest=null;
            for(int i=1;i<acc.size();i++){
                String e = acc.get(i);
                if(map.containsKey(e) ){
                    if(dest==null){
                        conflict=true;
                        dest = map.get(e);
                    }else{
                        if(dest!= map.get(e)){
                            Account next = map.get(e);
                            list.remove(next);
                            for(String ee: next.emails){
                                dest.emails.add(ee);
                                map.put(ee, dest);
                            }
                        }
                    }
                }
            }

            if(conflict){
                merge(dest, acc);
                for(int i=1;i<acc.size();i++){
//                    if(!map.containsKey(acc.get(i)))
                    map.put(acc.get(i), dest);
                }
            }else{
                Account a = new Account();
                a.name = acc.get(0);
                for(int i=1;i<acc.size();i++){
                    String e = acc.get(i);
                    a.emails.add(e);
                    map.put(e, a);
                }
                list.add(a);
            }
        }

        List<List<String>> ret = new ArrayList<>();
        for(Account a: list){
            List<String> l = new ArrayList<>();
            l.add(a.name);
            for(String e: a.emails){
                l.add(e);
            }
            ret.add(l);
        }

        return ret;
    }


    @Test
    public void test(){
        List<List<String>> input2 = Arrays.asList(Arrays.asList("J", "1", "2"), Arrays.asList("J", "2", "0"),
                Arrays.asList("M", "mary"), Arrays.asList("J", "100")
        );
        List<List<String>> r4 = accountsMerge_3(input2);
        assertEquals(3, r4.size());


        List<List<String>> input = Arrays.asList(Arrays.asList("H", "2", "3"), Arrays.asList("H", "4", "5"),
                Arrays.asList("H", "0", "1"), Arrays.asList("H", "3","4"), Arrays.asList("H", "7", "8"),
                Arrays.asList("H", "1", "2"), Arrays.asList("H", "6","7"), Arrays.asList("H", "5", "6")
        );

        List<List<String>> r = accountsMerge_2(input);
        assertEquals(10, r.get(0).size());
        List<List<String>> r3 = accountsMerge_3(input);
        assertEquals(10, r3.get(0).size());





    }
}
