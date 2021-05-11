package leetcode;


import java.util.*;

public class _843 {
    private interface Master {
        public int guess(String word);
    }

    private class MasterImpl implements Master{
        private String secret;

        @Override
        public int guess(String word) {
            int count=0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i)==secret.charAt(i))
                    count++;
            }
            return count;
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {
        int N = wordlist.length;
        int[][] m = new int[N][N];
        List<Integer> remain = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            remain.add(i);
            for (int j = i+1; j < N; j++) {
                int c=0;
                for (int k = 0; k < wordlist[i].length(); k++) {
                    if(wordlist[i].charAt(k)==wordlist[j].charAt(k)) c++;
                }
                m[i][j]=c;
                m[j][i]=c;
            }
        }

        int wordLen = wordlist[0].length();
        int step=10;
        Random r = new Random();
        HashSet<Character> blacklist = new HashSet<>();
        while(step>0){
            int rand =  r.nextInt(remain.size());
            int v = remain.get(rand);
            remain.remove(rand);

            int match = master.guess(wordlist[v]);
            List<Integer> nextCandidate = new ArrayList<>();
            if(match == wordLen){
                nextCandidate.add(v);
                remain=nextCandidate;
            }else{
                // 1<=match<wordLen
                for(int i : remain){
                    if(m[v][i]==match){
                        nextCandidate.add(i);
                    }
                }
                remain=nextCandidate;
            }
            step--;
        }
    }

    @org.junit.Test
    public void test() {
        MasterImpl master = new MasterImpl();
//        master.secret="vftnkr";
//        findSecretWord(new String[]{"mjpsce","giwiyk","slbnia","pullbr","ezvczd","dwkrmt","qgzebh","wvhhlm","kqbmny","zpvrkz","pdwxvy","gilywa","gmrrdc","vvqvla","rmjirt","qmvykq","mhbmuq","unplzn","qkcied","eignxg","fbfgng","xpizga","twubzr","nnfaxr","skknhe","twautl","nglrst","mibyks","qrbmpx","ukgjkq","mhxxfb","deggal","bwpvsp","uirtak","tqkzfk","hfzawa","jahjgn","mteyut","jzbqbv","ttddtf","auuwgn","untihn","gbhnog","zowaol","feitjl","omtiur","kwdsgx","tggcqq","qachdn","dixtat","hcsvbw","chduyy","gpdtft","bjxzky","uvvvko","jzcpiv","gtyjau","unsmok","vfcmhc","hvxnut","orlwku","ejllrv","jbrskt","xnxxdi","rfreiv","njbvwj","pkydxy","jksiwj","iaembk","pyqdip","exkykx","uxgecc","khzqgy","dehkbu","ahplng","jomiik","nmcsfe","bclcbp","xfiefi","soiwde","tcjkjp","wervlz","dcthgv","hwwghe","hdlkll","dpzoxb","mpiviy","cprcwo","molttv","dwjtdp","qiilsr","dbnaxs","dbozaw","webcyp","vftnkr","iurlzf","giqcfc","pcghoi","zupyzn","xckegy"}, master);
        master.secret=        "hbaczn";
        findSecretWord(new String[]{"gaxckt","trlccr","jxwhkz","ycbfps","peayuf","yiejjw","ldzccp","nqsjoa","qrjasy","pcldos","acrtag","buyeia","ubmtpj","drtclz","zqderp","snywek","caoztp","ibpghw","evtkhl","bhpfla","ymqhxk","qkvipb","tvmued","rvbass","axeasm","qolsjg","roswcb","vdjgxx","bugbyv","zipjpc","tamszl","osdifo","dvxlxm","iwmyfb","wmnwhe","hslnop","nkrfwn","puvgve","rqsqpq","jwoswl","tittgf","evqsqe","aishiv","pmwovj","sorbte","hbaczn","coifed","hrctvp","vkytbw","dizcxz","arabol","uywurk","ppywdo","resfls","tmoliy","etriev","oanvlx","wcsnzy","loufkw","onnwcy","novblw","mtxgwe","rgrdbt","ckolob","kxnflb","phonmg","egcdab","cykndr","lkzobv","ifwmwp","jqmbib","mypnvf","lnrgnj","clijwa","kiioqr","syzebr","rqsmhg","sczjmz","hsdjfp","mjcgvm","ajotcx","olgnfv","mjyjxj","wzgbmg","lpcnbj","yjjlwn","blrogv","bdplzs","oxblph","twejel","rupapy","euwrrz","apiqzu","ydcroj","ldvzgq","zailgu","xgqpsr","wxdyho","alrplq","brklfk"}, master);
    }
}
