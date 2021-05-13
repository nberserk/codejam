package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 *
 *
 609. Find Duplicate File in System My SubmissionsBack To Contest

 Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.

 A group of duplicate files consists of at least two files that have exactly the same content.

 A single directory info string in the input list has the following format:

 "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

 It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

 The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

 "directory_path/file_name.txt"

 Example 1:
 Input:
 ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
 Output:
 [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 Note:
 No order is required for the final output.
 You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
 The number of files given is in the range of [1,20000].
 You may assume no files or directories share the same name in a same directory.
 You may assume each given directory info represents a unique directory. Directory path and file infos are separated by a single blank space.
 Follow up beyond contest:
 Imagine you are given a real file system, how will you search files? DFS or BFS ?
 If the file content is very large (GB level), how will you modify your solution?
 If you can only read the file by 1kb each time, how will you modify your solution?
 What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
 How to make sure the duplicated files you find are not false positive?
 *
 */

public class LC609 {

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: paths){
            String[] p = s.split(" ");
            String base = p[0];
            for(int i=1;i<p.length;i++){
                String[] c = p[i].split("\\(");
                String file = c[0];
                String content = c[1].substring(0, c[1].length()-1);
                String full=base+"/"+file;
                if(map.containsKey(content) ){
                    map.get(content).add(full);
                }else{
                    List<String> list = new ArrayList<>();
                    list.add(full);
                    map.put(content, list);
                }
            }
        }

        List<List<String>> ret = new ArrayList<>();
        for( String key: map.keySet() ){
            if(map.get(key).size()>1)
                ret.add(map.get(key) );
        }
        return ret;
    }


    @Test
    public void test(){
        String[] in = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};

        List<List<String>> expected = new ArrayList<>();
        List<String> f = new ArrayList<>();
        List<String> s = new ArrayList<>();

        f.add("root/a/2.txt");
        f.add("root/c/d/4.txt");
        f.add("root/4.txt");

        s.add("root/a/1.txt");
        s.add("root/c/3.txt");
        expected.add(f);
        expected.add(s);
        assertEquals(expected, findDuplicate(in));
    }
}
