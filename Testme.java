package com.test;


import com.test.RemoveLoopInLinkedList.Node;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.of;


public class Testme {
    /*
      Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
      For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
     */
    static String getLongestSubstringWithKUniqueChars(String s, int maxSubstringSize) {
        Map<Character, Integer> map = new HashMap();
        String maxSubstring = "";
        String tempSubstr = "";

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            int count = map.size();
            boolean exists = map.containsKey(c);

            if (count == maxSubstringSize && !exists) {
                //reset map and start over
                //take snapshot first
                if (tempSubstr.length() > maxSubstring.length()) {
                    maxSubstring = tempSubstr;
                }

                map.remove(tempSubstr.charAt(0));
                tempSubstr = trimOffFirstChars(tempSubstr);

            }
            if (!exists) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
            tempSubstr = tempSubstr + c;

        }

        if (tempSubstr.length() > maxSubstring.length()) {
            maxSubstring = tempSubstr;
        }

        //c[s.charAt(i)-'a']=true;

        return maxSubstring;
    }


    private static long taumBday(long b, long w, long bc, long wc, long z) {
        long sum=b*bc+w*wc;
        sum=Math.min(sum,b*bc+w*bc+w*z);
        sum=Math.min(sum,b*wc+w*wc+b*z);
        return sum;

    }

    private static String trimOffFirstChars(String s) {
        char c = s.charAt(0);
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                j = i;
                break;
            }
        }
        if (j == 0) j = s.length();
        return s.substring(j);
    }


    public static int partition(Integer[] arr, int l, int r) {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++) {
            if (arr[j] <= x) {
                //Swapping arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        //Swapping arr[i] and arr[r]
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
    }

    // This function returns k'th smallest element  in arr[l..r] using QuickSort based method.
    // ASSUMPTION: ALL ELEMENTS IN ARR[] ARE DISTINCT
    public static int kthSmallest(Integer[] arr, int l, int r, int k) {
        // If k is smaller than number of elements in array
        if (k > 0 && k <= r - l + 1) {
            // Partition the array around last element and get position of pivot element in sorted array
            int pos = partition(arr, l, r);

            // If position is same as k
            if (pos - l == k - 1)
                return arr[pos];

            // If position is more, recur for left subarray
            if (pos - l > k - 1)
                return kthSmallest(arr, l, pos - 1, k);

            // Else recur for right subarray
            return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
        }

        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }

    //5,2,1,3,4 should return 4,2,5,1,3

    static int[] permutationEquation(int[] p) {
        int[] newone = new int[p.length + 1];

        for (int i = 0; i < p.length; i++) {
            newone[p[i]] = i + 1;
        }

        int[] ret = new int[p.length];

        for (int i = 0; i < p.length; i++) {
            ret[i] = newone[newone[i + 1]];

        }

        return ret;
    }


    static class Person {
        String firstName;
        String lastName;
        Integer SSN;

        @Override
        public String toString() {
            return "f=" + firstName + ":l=" + lastName + ":ssn=" + SSN;
        }
    }

    static class comp implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            int i = o1.firstName.compareTo(o2.firstName);
            if (i == 0) {
                i = o1.lastName.compareTo(o2.lastName);

                if (i == 0) {
                    return (o1.SSN > o2.SSN ? 1 : 0);
                }
            }
            return i;
        }
    }


    static List<Person> sortPersons(List<Person> list) {
        Comparator<Person> comp1 = new comp();
        Collections.sort(list, comp1);

        return list;
    }


    static String[] fizzArray;//=new String[100];

    static class pair {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x=" + x + ", y=" + y;
        }


    }

    /*
	 *  4 9 2
	    3 5 7
	    8 1 5    change s(2,2) to 6 to fix.  Cost=1
	 */
    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int cost;
        int minCost = 999;

        int magic[][] =
                {
                        //these are the only 8 magic squares formed
                        {4, 9, 2, 3, 5, 7, 8, 1, 6},
                        {4, 3, 8, 9, 5, 1, 2, 7, 6},
                        {2, 9, 4, 7, 5, 3, 6, 1, 8},
                        {2, 7, 6, 9, 5, 1, 4, 3, 8},
                        {8, 1, 6, 3, 5, 7, 4, 9, 2},
                        {8, 3, 4, 1, 5, 9, 6, 7, 2},
                        {6, 7, 2, 1, 5, 9, 8, 3, 4},
                        {6, 1, 8, 7, 5, 3, 2, 9, 4},
                };

        for (int i = 0; i < 8; i++) {
            cost = 0;
            for (int j = 0; j < 9; j++) {
                cost += Math.abs(magic[i][j] - s[(int) j / 3][j % 3]);
            }

            if (cost < minCost) {
                minCost = cost;
            }
        }

        return minCost;
    }

    //
    //100 90 90 80 75 60 scores
    //50 65 77 90 102  alice
    //should get back 6,5,4,2,1
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] l = new int[alice.length];
        int[] array = of(scores).distinct().toArray();


        int i = array.length - 1;
        int j = 0;

        for (int score : alice) {
            while (i >= 0) {
                if (score >= array[i]) {
                    i--;
                } else {
                    //System.out.println(i+2);
                    l[j] = i + 2;
                    j++;
                    break;
                }
            }

            if (i < 0) {
                //System.out.println(1);
                l[j] = 1;
                j++;
            }
        }
        return l;

    }


    static int[] climbingLeaderboard44(int[] scores, int[] alice) {
        List<Integer> list = new ArrayList<Integer>();

        //perhaps use ArrayList here?
        int[] l = new int[alice.length];

        for (int score : scores) {
            if (!list.contains(score)) {
                list.add(score);
            }
        }

        boolean found = false;

        for (int i = 0; i < alice.length; i++) {
            int aliceScore = alice[i];

            found = false;
            for (int j = list.size() - 1; j >= 0; j--) {
                int score = list.get(j);
                if (aliceScore < score) {
                    list.remove(j);
                    l[i] = j + 2;
                    found = true;
                    break;
                } else {
                    //l[i]=j+1;
                    list.remove(j);
                    //break;
                }
            }


        }

        if (!found) {
            l[alice.length - 1] = 1;
        }

        return l;
    }


    //find the longest subarray of int with a difference of 1.  O(n)

    static int pickingNumbers(List<Integer> a) {
        int maxCount = 0;
        int count = 0;

        Collections.sort(a);
        int start = a.get(0);

        for (int i : a) {
            if (Math.abs(start - i) < 2) {
                count++;
            } else {

                if (count > maxCount) {
                    maxCount = count;
                }
                count = 1;
                start = i;
            }
        }

        if (count > maxCount) {
            maxCount = count;
        }

        return maxCount;
    }


    static class LinkedListNode {
        String name;
        LinkedListNode next;
        LinkedListNode prev;

        LinkedListNode(String name) {
            this.name = name;
            next = null;
            prev = null;
        }

        @Override
        public String toString() {
            return "name=" + name;
        }
    }


    //remove 1,3 or B and D
    static LinkedListNode Remove(LinkedListNode node, int[] removalRequests, int removalRequests_Length) {
        LinkedListNode start = node;
        LinkedListNode prev = null;

        int i = 0;
        for (int j : removalRequests) {
            while (i < j) {
                prev = start;

                if (start != null) {
                    start = start.next;
                }

                i++;
            }

            if (start == null || start.next == null) {
                if (i == j) {
                    start = removeNode(prev, start);
                }
                break;
            }


            start = removeNode(prev, start);
            i++;
            //i++;
        }


        return node;
    }

    //need next node since we can do this with only the current node.
    private static LinkedListNode removeNode(LinkedListNode prev, LinkedListNode current) {
        if (current == null)
            return null;

        prev.next = current.next;

        //LinkedListNode next=current.next.next;
        //current.next=next;
        current = null;

        return prev.next;
    }


    static boolean isOneAPermutationOfOther(String a, String b) {
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();

        char c = a1[0];

        char d = b1[c - 'a'];

        int index = 0;

        if (d != 0) {
            index = c - 'a';
            for (int i = 0; i < a.length(); i++) {
                if (a1[i] != b1[index + 1]) {
                    break;
                }
            }
        }
        return false;

    }

    static boolean isAllCharsUnique(String s) {
        boolean[] c = new boolean[s.length()];

        if (s.length() > 26) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {

            System.out.println(s.charAt(i) - 'a');

            if (c[s.charAt(i) - 'a']) {
                return false;
            }

            c[s.charAt(i) - 'a'] = true;
        }

        return true;
    }

    static String catAndMouse(int x, int y, int z) {
        if (Math.abs(z - x) > Math.abs(z - y))
            return "Cat B";
        else if (Math.abs(z - x) < Math.abs(z - y))
            return "Cat A";
        else
            return "Mouse C";


    }

    // Complete the designerPdfViewer function below.
    static int designerPdfViewer(int[] h, String word) {
        int asciAdj = 97;
        //int maxHeight=1;
        //zaba
        int len = word.length();


        //word.chars().map(c -> h[c - 'a']).max().orElse(0);

        int maxHeight = word.chars().map(i -> h[i - asciAdj]).max().getAsInt();

        //int maxHeight = Arrays.asList(what).stream().reduce(Integer::max).get();

        //int maxHeight=word.codePoints().mapToObj(i->h[i-asciAdj]).reduce(Integer::max).get();


        //	word.chars().forEach(i->System.out.println(h[i-asciAdj]));


        int area = len * maxHeight;
        return area;
    }

    static void charTest() {
        String s = "abcdefghijz";

        s.chars().forEach(i -> System.out.println(i));

    }

    //return difference between k and the max of vector
    static int hurdleRace(int k, int[] height) {
        int i = Arrays.stream(height).max().getAsInt();

        return Math.max(0, i - k);


    }

    static String getZigZag(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder sb = new StringBuilder();
        // step
        int step = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            // first & last rows
            if (i == 0 || i == numRows - 1) {
                for (int j = i; j < s.length(); j = j + step) {
                    sb.append(s.charAt(j));
                }
                // middle rows
            } else {
                int j = i;
                boolean flag = true;
                int step1 = 2 * (numRows - 1 - i);
                int step2 = step - step1;

                while (j < s.length()) {
                    sb.append(s.charAt(j));
                    if (flag)
                        j = j + step1;
                    else
                        j = j + step2;
                    flag = !flag;
                }
            }
        }

        return sb.toString();
    }


    //return true if string is a valid json string, i.e., matching {}, (), []
    static boolean jsonParser(String json) {
        Stack<Character> s = new Stack<Character>();
        boolean found;
        Map<Character, Character> mapper = new HashMap<Character, Character>();
        mapper.put(')', '(');
        mapper.put('}', '{');
        mapper.put(']', '[');

        Character startChar;

        for (int i = 0; i < json.length(); i++) {
            char t = json.charAt(i);

            s.push(t);

            startChar = mapper.get(t);


            if (startChar != null) {
                found = false;

                while (!s.isEmpty()) {
                    char newPop = s.pop();

                    if (newPop == startChar) {
                        found = true;
                        break;
                    }

                    //System.out.println("char="+s);
                }

                if (!found) {
                    return false;
                }
            }
        }

        if (s.search('}') > -1 || s.search('{') > -1) {
            return false;
        }
        return true;

    }

    //array of billable items, k=# items, b=0 based index of what anna didn't eat.
    static void bonAppetit(List<Integer> bill, int k, int b) {
        //should return 5


        int sum = bill.stream().mapToInt(i -> i).sum();

        int newSum = sum / 2;

        int removal = bill.get(k);


        int result = (sum - removal) / 2;
        int newResult = b - result;

        if (newResult <= 0)
            System.out.println("Bon Appetit");
        else
            System.out.println(newResult);
        //s.forEach(a->System.out.println(a));
    }

    static void testme2() throws IOException {
        File file = new File("c://temp//testFile1.txt");
        file.getName();
        //Create the file
        if (file.createNewFile()) {
            FileWriter writer = new FileWriter(file);
            writer.write("Test data");
            writer.close();
        }

    }

    static String getFileName(String val) {
        //val=val.toLowerCase();
        String lowerVal = val.toLowerCase();
        //must contain gif
        String[] splitter = lowerVal.toLowerCase().split(" ");

        if (splitter[splitter.length - 2].equals("200")) {
            if (!val.contains(".gif")) {
                return "";//not found
            } else {
                int gifIndex = lowerVal.indexOf(".gif") + 4;
                int slashIndex = -1;

                //work backward to find /
                for (int i = gifIndex; i > 0; i--) {
                    if (val.charAt(i) == '/') {
                        System.out.println(val.charAt(i));
                        slashIndex = i + 1;
                        break;
                    }
                }
                if (slashIndex > 0) {
                    return val.substring(slashIndex, gifIndex);
                }

            }
        }
        return "";
    }

    //use stack
    static boolean isPalindrome(String val) {
        String reverse = "";
        Stack<Character> stack = new Stack<Character>();

        int size = val.length();

        for (int i = 0; i < size; i++) {
            stack.push(val.charAt(i));
        }

        while (!stack.isEmpty()) {
            reverse += stack.pop();
        }


        if (val.equals(reverse)) {
            return true;
        }
        return false;
    }


    static boolean isPalindrome3(String val) {
        String reverse = "";

        int size = val.length();

        for (int i = 0; i < size; i++) {
            reverse = reverse + val.charAt(size - i - 1);
        }

        if (val.equals(reverse)) {
            return true;
        }
        return false;
    }

    //alternative way to do it

    static boolean isPalindrome2(String val) {
        String reverse;
        char[] charRev = new char[val.length()];
        int size = val.length();

        for (int i = 0; i < size; i++) {
            charRev[i] = val.charAt(size - i - 1);
        }

        reverse = String.valueOf(charRev);

        if (val.equals(reverse)) {
            return true;
        }
        return false;
    }


    static String dayOfProgrammer(int year) {
        boolean isLeap = (year % 400 == 0 || (year % 4 == 0 && (year % 100) != 0));

        final int dayProg = 256;
        int days = 0;
        //should get 13.09.2017
        for (int i = 1; i < 9; i++) {
            YearMonth yearMonthObject = YearMonth.of(year, i);
            days += yearMonthObject.lengthOfMonth();
        }

        if (year == 1800) days++;
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, year);
        //calendar.set(Calendar.MONTH, 1);
        //int numDays = calendar.getActualMaximum(Calendar.DATE);


        days = dayProg - days;

//		13.09.2017
        return days + ".09." + year;
    }


    //return id of most frequent number.  If tie, return lower one.
    //1 2 3 4 5 4 3 2 1 3 4
    static int migratoryBirds(List<Integer> arr) {
        int count = 0;
        //int tempCount=0;
        int mostFreqNum = arr.get(0);
        int[] newArray = new int[arr.size()];


        //int start;
        for (int i = 0; i < arr.size(); i++) {
            newArray[arr.get(i)]++;
        }


        for (int i = 0; i < newArray.length; i++) {
            if (count < newArray[i]) {
                count = newArray[i];
                mostFreqNum = i;
            }
        }

        return mostFreqNum;
    }


    private static Character getMostOccuringCharInString(String str) {
        long count = 0;
        char mostChar = 0;
        long tempCounter;
        List<Character> used = new ArrayList<>();

        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            //don't count again
            if (!used.contains(c)) {
                tempCounter = 0;
                for (int j = i; j < charArray.length; j++) {
                    if (c == charArray[j]) {
                        tempCounter++;
                    }
                }

                if (tempCounter > count) {
                    count = tempCounter;
                    mostChar = c;
                }

                used.add(c);
            }
        }


        return mostChar;
    }

    private static Character getMostOccuringCharInStringJava8(String str) {
        long count = 0;
        char mostChar = 0;
        long tempCounter;
        List<Character> l = new ArrayList<>();


        str.chars().forEach(c -> l.add((char) c));

        Set<Character> s = new HashSet<Character>(l);

        Iterator<Character> it = s.iterator();

        while (it.hasNext()) {
            Character c = it.next();

            tempCounter = str.codePoints().filter(s2 -> s2 == c).count();

            if (tempCounter > count) {
                count = tempCounter;
                mostChar = c;
            }
        }


        return mostChar;
    }

    private static String reverseRecursively(String str) {
        if (str.length() < 2) {
            return str;
        }

        return reverseRecursively(str.substring(1)) + str.charAt(0);
    }


    //should get c
    private static char firstNonRepeatingChar(String word) {
        Set<Character> repeating = new HashSet<>();
        List<Character> nonRepeating = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (repeating.contains(letter)) {
                continue;
            }
            if (nonRepeating.contains(letter)) {
                nonRepeating.remove((Character) letter);
                repeating.add(letter);
            } else {
                nonRepeating.add(letter);
            }
        }
        return nonRepeating.get(0);
    }


    private static String queueString(String str) {
        Queue<Integer> s = new ArrayDeque<Integer>();

        //String ret="";
        StringBuilder same = new StringBuilder();
        str.codePoints().forEach(cp -> s.offer(cp));


        System.out.println(s.size());


        //for (int c:str.toCharArray())
        //	{
        //	s.push(c);
        //	}

        while (!s.isEmpty()) {
            //ret+=s.pop();
            same.appendCodePoint(s.poll());
        }

        //alternative way.  Easier.
        //StringBuilder builder = new StringBuilder(str);
        //same =builder.reverse().toString();

        return same.toString();
    }


    //reverse order
    private static String reverseString(String str) {
        Stack<Integer> s = new Stack<Integer>();
        String ret = "";
        StringBuilder reversed = new StringBuilder();
        str.codePoints().forEach(cp -> s.push(cp));
        System.out.println(s.size());
        //for (int c:str.toCharArray())
        //	{
        //	s.push(c);
        //	}

        while (!s.isEmpty()) {
            //ret+=s.pop();
            reversed.appendCodePoint(s.pop());
        }

        //alternative way.  Easier.
        StringBuilder builder = new StringBuilder(str);
        ret = builder.reverse().toString();

        return ret;
    }

    //remove dups in array and return remaining array
    private static Object[] RemoveDupsFromArray(int[] is) {
        List<Integer> newArray = new ArrayList<Integer>();

        boolean found = false;

        for (int i : is) {
            found = false;

            for (int k : newArray) {
                if (k == i) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                newArray.add(i);
            }
        }

        //return newArray.stream().mapToInt(i -> i).toArray();
        return newArray.toArray();


    }

    //remove dups in array and return remaining array
    //hashset changes the order due to internal hash.  Linkedhashset does better at not sorting.
    private static Integer[] RemoveDupsFromArrayWithSet(Integer[] is) {
        List<Integer> l = Arrays.asList(is);
        Set<Integer> t = new HashSet<>(l);


        //Set<Integer> linkedHashSet = new LinkedHashSet<>( l );

        //Get back the array without duplicates

        return t.toArray(new Integer[]{});
    }


    //find all words in the dictionary using any direction of adjacent cells
    //should get GEEKS and QUIZ
    private static void findWords(char[][] boggle) {
        String dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};

        //system.out matching words


    }


    private static int getMaxContigSubArray(int[] is) {
        int max = is[0] + is[1];

        for (int i = 1; i < is.length - 1; i++) {
            if (is[i] + is[i + 1] > max) {
                max = is[i] + is[i + 1];
            }
        }
        return max;
    }

    //find the number of ar pairs where i>j and sum pair is divisible by k
    private static int divisibleSumPairs(int n, int k, int[] ar) {
        int counter = 0;
        for (int i = 0; i < ar.length; i++) {
            for (int j = i + 1; j < ar.length; j++) {
                if ((ar[i] + ar[j]) % k == 0) {
                    counter++;
                }
            }
        }

        //java 8 way, hard to read
        counter = IntStream.range(0, n).map(x -> IntStream.range(x + 1, n).map(y -> (ar[x] + ar[y]) % k == 0 ? 1 : 0).sum()).sum();

        return counter;

    }

    //return number of ways we can loop through s and add numPieces touching segments to get sum
    private static int birthday(List<Integer> s, int sum, int numPieces) {
        int counter = 0;

        s.forEach(a -> System.out.println(a));

        for (int i = 0; i <= s.size() - numPieces; i++) {
            int chunk = 0;

            for (int j = i; j < i + numPieces; j++) {
                chunk += Optional.of(s.get(j)).orElse(0).intValue();
            }

            if (chunk == sum) {
                counter++;
            }
        }

        return counter;

    }

    //how many times the min or max score is broken
    private static int[] breakingRecords(int[] scores) {
        int maxCounter = 0;
        int minCounter = 0;
        int min = 0;
        int max = 0;
        min = scores[0];
        max = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
                minCounter++;
            }
            if (scores[i] > max) {
                max = scores[i];
                maxCounter++;
            }
        }

        return new int[]{maxCounter, minCounter};
    }

    private static void intStreams() {
        IntStream.of(1, 2, 3, 4, 4, 3, 2).forEach(i ->
        {
            System.out.println(i);
        });

        IntStream.of(1, 2, 3, 4, 4, 3, 2).distinct().forEach(i ->
        {
            System.out.println(i);
        });

        System.out.println(IntStream.of(1, 2, 3, 4, 4, 3, 2).distinct().average().getAsDouble());

        System.out.println(IntStream.of(1, 2, 3, 4, 4, 3, 2).distinct().sum());

        IntPredicate predicate1 = a -> a > 10;
        IntPredicate predicate2 = a -> a < 10;
        IntPredicate predicate3 = a -> a == 10;
        IntPredicate predicate = predicate1.or(predicate2).or(predicate3);

        System.out.println(IntStream.of(1, 2, 3, 4, 4, 3, 2).distinct().anyMatch(predicate));

        IntSupplier s = () -> 123;
        System.out.println(s.getAsInt());

        IntSupplier intSupplier = new IntSupplier() {
            private int key = 1;

            @Override
            public int getAsInt() {
                return key = key * 2;
            }

        };

        IntStream.generate(intSupplier).limit(10).forEach(i -> System.out.println(i));


    }


    private static void fizzBuzz2() {
        IntStream.rangeClosed(1, 100).forEach(i ->
        {
            String res = "";
            res += (i % 3 == 0) ? "fizz" : "";
            res += (i % 5 == 0) ? "buzz" : "";
            res += (res.isEmpty() ? i : "");

            //System.out.println(res);
        });


        //IntStream.rangeClosed(1, 100).forEach(i -> {System.out.println(i % 15 == 0 ? "FizzBuzz" : (i % 3 == 0) ? "Fizz" : (i % 5 == 0) ? "Buzz" : i);});


    }

    private static void fizzBuzz() {
        String res = "";

        res = "5" + "6";
        int i1 = Integer.valueOf("5") + Integer.valueOf("6");

        if (fizzArray == null) {
            fizzArray = new String[100];

            for (int i = 1; i <= 100; i++) {
                res = "";
                res += (i % 3 == 0) ? "fizz" : "";
                res += (i % 5 == 0) ? "buzz" : "";
                res += (res.isEmpty() ? i : "");
                fizzArray[i - 1] = res;

                System.out.println(res);
            }

        } else {
            //use this array


        }

        //IntStream.rangeClosed(1, 100).forEach(i -> {System.out.println(i % 15 == 0 ? "FizzBuzz" : (i % 3 == 0) ? "Fizz" : (i % 5 == 0) ? "Buzz" : i);});


    }


    private static String fizzBuzzInJava8(int number) {
        String result = Optional.of(number).map(n -> (n % 3 == 0 ? "Fizz" : "") + (n % 5 == 0 ? "Buzz" : "")).get();
        //return result.isEmpty() ? Integer.toString(number) : result;
        return null;
    }


    private static List<List<Integer>> optimalUtilization(int maxTravelDist, List<List<Integer>> forwardRouteList, List<List<Integer>> returnRouteList) {
        //key=distance, val=list of points
        Map<Integer, List<List<Integer>>> pointMapper = new HashMap<Integer, List<List<Integer>>>();

        List<Integer> point;
        int k = 0;
        //sort distances and then pick top numDeliveries
        for (int i = 0; i < forwardRouteList.size(); i++) {
            point = forwardRouteList.get(i);

            int x1 = point.get(0);
            int y1 = point.get(1);
            for (int j = 0; j < returnRouteList.size(); j++) {
                point = returnRouteList.get(j);
                int x2 = point.get(0);
                int y2 = point.get(1);

                Integer[] intd = new Integer[]{x1, x2};
                k++;

                addToPointMapper(pointMapper, y1 + y2, Arrays.asList(intd));
            }
        }

        int distanceToUse = 0;
        for (Integer key : pointMapper.keySet()) {
            if (key > distanceToUse && key <= maxTravelDist) {
                distanceToUse = key;
            }
        }

        //there could be more here
        List<List<Integer>> points = pointMapper.get(distanceToUse);

        return points;
    }

    //intd is the new point to add
    private static void addToPointMapper(Map<Integer, List<List<Integer>>> pointMapper, int i, List<Integer> intd) {
        List<List<Integer>> val = pointMapper.get(i);

        if (val == null) {
            val = new ArrayList<List<Integer>>();
        }

        val.add(intd);
        pointMapper.put(i, val);
    }

    private static List<List<Integer>> ClosestXdestinations(int numDestinations, List<List<Integer>> allLocations, int numDeliveries) {
        double[] distances = new double[numDestinations];
        List<List<Integer>> closestResults = new ArrayList<List<Integer>>();
        Map<Double, List<Integer>> pointMapper = new HashMap<Double, List<Integer>>();

        List<Integer> point;
        //sort distances and then pick top numDeliveries
        for (int i = 0; i < numDestinations; i++) {
            point = allLocations.get(i);
            int x = point.get(0);
            int y = point.get(1);

            double dist = Math.sqrt(x * x + y * y);
            distances[i] = dist;

            pointMapper.put(dist, point);
        }

        //sort to get closest first
        Arrays.sort(distances);

        //now stuff numDeliveries points into results with closest first
        for (int i = 0; i < numDeliveries; i++) {
            closestResults.add(pointMapper.get(distances[i]));
        }

        return closestResults;
    }


    private static int getGCD(int num, int[] arr) {
        Arrays.sort(arr);

        int smallVal = arr[0];
        int match = 0;

        //gcd must be less than or equal to smallest value.  Start at 2 since 0,1 don't make sense.
        //start big and work your way down
        for (int j = smallVal; j > 0; j--) {
            match = 0;
            for (int i = 0; i < num; i++) {
                //if all divide then a winner and break
                if (arr[i] % j != 0) {
                    match = 0;
                    break;
                } else {
                    match = j;
                }
            }

            if (match > 0) {
                return match;
            }

        }

        return -1;
    }

    //https://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/
	/*
	 * - Print value of "peak" after each operation.

- POP       : get the value of "peak", if there is no more value after pop, then print "EMPTY"
- PUSH d : put the new value(d) after "peak", then print the value of "peak"
- INC x d : add the new value(d) from index 0 to x elements, then print the value of "peak"
 the values in the Super Stack
 peak       9
               5
 base       4
	 */
    private static void karlSuperStack(String[] operations) {
		/*
		  want this
		  	1
			EMPTY
			3
			4
			5
			6
			5
			9
		 */
        LinkedList<String> l = new LinkedList<String>();
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            String splitter[] = op.split(" ");
            if (op.startsWith("push")) {
                l.add(splitter[1]);
            } else if (op.startsWith("pop")) {
                l.removeLast();
            } else {
                int endpoint = Integer.valueOf(splitter[1]);
                int adder = Integer.valueOf(splitter[2]);

                for (int j = 0; j < endpoint; j++) {
                    l.set(j, String.valueOf(Integer.valueOf(l.get(j)) + adder));
                }
            }

            if (l.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(l.getLast());
            }
        }

    }

    /* return 2 numbers in an array that sum up to i
     *
     */
    private static int[] sum2Numbers(int sum, int[] numbers) {
        int len = numbers.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {

                if (numbers[i] + numbers[j] == sum) {
                    System.out.println("number1=" + numbers[i] + ":number2=" + numbers[j]);
                    return new int[]{numbers[i], numbers[j]};
                }
            }
        }
        //return Arrays.stream(numbers).filter(x->)

        return null;
    }

    /*
	  	s starting point of Sam's house location.
		t: integer, ending location of Sam's house location.
		a: integer, location of the Apple tree.
		b: integer, location of the Orange tree.
		apples: integer array, distances at which each apple falls from the tree.
		oranges: integer array, distances at which each orange falls from the tre
	*/
    static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {
        //add a to apples and b to oranges
        Arrays.setAll(apples, i -> apples[i] + a);
        Arrays.setAll(oranges, i -> oranges[i] + b);

        //any apples between s and t?
        int[] apples2 = Arrays.stream(apples).filter(x -> x >= s && x <= t).toArray();

        //any oranges between a and b?
        int[] oranges2 = Arrays.stream(oranges).filter(x -> x >= s && x <= t).toArray();

        System.out.println(apples2.length);
        System.out.println(oranges2.length);

    }

    static List<Integer> cellCompete(int[] states, int days) {
        //loop through all days
        for (int i = 0; i < days; i++) {
            int[] copyArray = Arrays.copyOf(states, states.length);

            //adjust for a single day
            Arrays.setAll(states, j ->
            {
                if (getState(copyArray, j - 1) == getState(copyArray, j + 1)) {
                    return 0;
                } else {
                    return 1;
                }
            });
        }

        return Arrays.stream(states).boxed().collect(Collectors.toList());
    }

    static int getState(int[] states, int index) {
        if (index < 0 || index >= states.length)
            return 0;
        else
            return states[index];


    }


    static int[] gradingStudents(int[] grades) {
        int len = grades.length;
        int[] result = new int[len];
        Arrays.setAll(result, i -> grades[i] < 38 ? grades[i] : (grades[i] % 5 >= 3 ? grades[i] - grades[i] % 5 + 5 : grades[i]));

        Arrays.setAll(result, i ->
                {
                    if (grades[i] < 38) {
                        return grades[i];
                    } else {
                        int k = grades[i] % 5;

                        if (k >= 3) {
                            return grades[i] - k + 5;

                        } else {
                            return grades[i];
                        }
                    }
                }
        );


        return result;
    }

    public static List<String> reformatDate(List<String> dates) {
        List<String> newList = new ArrayList<String>();
        //20th Oct 2052
        for (String g : dates) {
            g = g.replaceAll("th", "").trim();
            g = g.replaceAll("nd", "");
            g = g.replaceAll("st", "");
            g = g.replaceAll("rd", "");

            String[] parseDate = g.split(" ");

            String newDate = parseDate[2] + "-" + getMonth(parseDate[1]) + "-" + padDay(parseDate[0].trim());
            //2052-10-20
            newList.add(newDate);
            //System.out.println(newDate);
        }


        return newList;
    }


    private static String padDay(String day) {
        day = day.replaceAll("st", "");
        day = day.replaceAll("rd", "");

        if (day.length() == 1) {
            return "0" + day;
        }
        return day;
    }


    private static String getMonth(String date) {
        if (date.equals("Jan"))
            return "01";
        else if (date.equals("Feb"))
            return "02";
        else if (date.equals("Mar"))
            return "03";
        else if (date.equals("Apr"))
            return "04";
        else if (date.equals("May"))
            return "05";
        else if (date.equals("Jun"))
            return "06";
        else if (date.equals("Jul"))
            return "07";
        else if (date.equals("Aug"))
            return "08";
        else if (date.equals("Sep"))
            return "09";
        else if (date.equals("Oct"))
            return "10";
        else if (date.equals("Nov"))
            return "11";
        else if (date.equals("Dec"))
            return "12";

        return "";
    }

    static String Matchthem(String text, String prefixString, String suffixString) {
        int textLen = text.length();
        String match1 = "";
        String match2 = "";
        //a=engine, b=raven, c=ginkgo
        for (int i = 0; i < textLen; i++) {
            //if match found, exit
            if (!match1.isEmpty()) break;

            //get all substrings.  engin, ngine, ...
            for (int j = 0; j < textLen; j++) {
                if (textLen - j >= textLen - i) {
                    String substr = text.substring(j, j + textLen - i);

                    if (prefixString.indexOf(substr, prefixString.length() - substr.length()) > -1) {
                        //System.out.println("match="+substr);
                        match1 = substr;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < textLen; i++) {
            if (!match2.isEmpty()) break;

            for (int j = 0; j < textLen; j++) {
                if (textLen - j >= textLen - i) {
                    String substr = text.substring(j, j + textLen - i);

                    //match on starting text this time
                    if (suffixString.startsWith(substr)) {
                        // System.out.println("match2="+substr);
                        match2 = substr;
                        break;
                    }
                }
            }
        }


        return match1 + match2;
    }


    static List<Integer> oddNumbers(int l, int r) {

        List<Integer> listArr = new ArrayList<Integer>();
        for (int i = l; i <= r; i++) {
            if (i % 2 == 1) {
                listArr.add(i);
            }
        }
        return listArr;
    }


    static List<Integer> oddNumbers2(int l, int r) {

        List<Integer> listArr = IntStream.rangeClosed(l, r).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());


        return listArr;
    }


    static String findNumber(List<Integer> arr, int k) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == k) {
                return "YES";
            }
        }

        return "NO";


    }


    static String findNumber2(List<Integer> arr, int k) {
        //Predicate predicate= a->a==k;
        boolean found = arr.stream().filter(i -> i == k).findAny().isPresent();

        if (found) {
            return "YES";
        } else {
            return "NO";
        }

    }


    public String Testme(String name) {
        List<Integer> listArr = new ArrayList<Integer>();

        List<Integer> arr = new ArrayList<Integer>();
        int k = 1;

        Integer[] arr2 = (Integer[]) arr.toArray();
        System.out.println(arr2.length);

        //int size=arr.get(0);

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr2[i] == k) {
                return "YES";
            }
        }
        return "NO";


        // System.out.println("Passed Name is :" + name );
    }

    public static void main(String[] args) throws InterruptedException {
        String test = "10 200 3 40000 5";
        List<String> list = new ArrayList<String>(Arrays.asList(test.split(" ")));
        int i = 2;

        System.out.println("Passed Name is " + list + ": count=" + list.size());

        if (!list.isEmpty()) {
            list.forEach(name -> System.out.println(name));
        }


        System.out.println();

        System.out.println(getTime("12:05:45AM"));
        System.out.println(getTime("07:05:45PM"));
        System.out.println(getTime("12:05:45PM"));

        System.out.println(getTime2("12:05:45AM"));
        System.out.println(getTime2("07:05:45PM"));
        System.out.println(getTime2("12:05:45PM"));


        System.out.println();


        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        }

        if (i > list.size() || i < 0) {
            System.out.println("NUL");
        } else {
            System.out.println(list.get(list.size() - i).trim());
        }

        System.out.println(list.get(list.size() - i));

        // Scanner StdIn = new Scanner(System.in);


        // Scanner StdOut = new Scanner(System.out);
        // String i = StdIn.nextLine();
        // System.out.println("Passed Name is " + i);


        // Write your code here.

        //  System.out.println("String: " + i);
        // while (StdIn.hasNextLine())
        // {
        //   String line = StdIn.nextLine();
        //   System.out.println("String: " + line);
        //StdIn.next()
        // StdOut.println(line);
        //  }

        // StdIn.close();

        System.out.println(5 % 2);
        System.out.println(6 % 2);


        if ((i % 2) == 0) {
            System.out.println("Weird");
        }

        if (2 < i && i < 5) {
            System.out.println("Weird");
        }

        System.out.println();
        findLongestNoRepeatChars("karlwashere");
        System.out.println();
        findLongestNoRepeatChars("javaconceptoftheday");

        System.out.println();
        findLongestNoRepeatChars("abcdefghijklmnopqrstuv");

        findLongestNoRepeatChars("a");

        findLongestNoRepeatChars("");

        findLongestNoRepeatChars("aaaaaaaaaaaaaa");


        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        System.out.println(findNumber(arr, 1));


        System.out.println(findNumber2(arr, 1));

        arr.clear();
        arr.add(2);
        arr.add(3);
        arr.add(1);
        System.out.println(findNumber(arr, 5));

        System.out.println(findNumber2(arr, 5));


        System.out.println(oddNumbers(2, 5));
        System.out.println(oddNumbers(3, 9));

        System.out.println(oddNumbers2(2, 5));
        System.out.println(oddNumbers2(3, 9));


        System.out.println(Matchthem("engine", "raven", "gingko"));
        System.out.println(Matchthem("nothing", "bruno", "ingenious"));

        System.out.println(Matchthem("ab", "b", "a"));

        System.out.println(Matchthem("abracadabra", "habrahabr", "bracket"));

        List<String> dateList = new ArrayList<String>();
        dateList.add("20th Oct 2052");
        dateList.add("6th Jun 1933");
        dateList.add("26th May 1960");
        dateList.add("20th Sep 1958");
        dateList.add("16th Mar 2068");
        dateList.add("25th May 1912");
        dateList.add("16th Dec 2018");
        dateList.add("26th Dec 2061");
        dateList.add("22nd May 1925");


        System.out.println(reformatDate(dateList));


        System.out.println(gradingStudents(new int[]{73, 67, 38, 33}));


        countApplesAndOranges(7, 11, 5, 15, new int[]{-2, 2, 1}, new int[]{5, -6});


        countApplesAndOranges(2, 3, 1, 5, new int[]{-2}, new int[]{-1});

        System.out.println(sum2Numbers(5, new int[]{1, 2, 5, 7, -1, -3, -4, 3, 4, 6, -8, -9}));

        int M[][] = new int[][]{{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };

        System.out.println("Number of islands is: " + countIslands(M));

        int N[][] = new int[][]{{0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}

        };
        System.out.println(getMaxRowCount(N));
        //should be 2

        int n[] = new int[]{1, 0, 0, 0, 0, 1, 0, 0};
        System.out.println(cellCompete(n, 1));


        n = new int[]{1, 1, 1, 0, 1, 1, 1, 1};
        System.out.println(cellCompete(n, 2));


        System.out.println(getGCD(5, new int[]{2, 4, 6, 8, 10}));

        System.out.println(getGCD(5, new int[]{2, 3, 4, 5, 6}));

        System.out.println(getGCD(3, new int[]{12, 24, 18}));

        Integer[] inta = new Integer[]{1, 2};
        Integer[] intb = new Integer[]{3, 4};
        Integer[] intc = new Integer[]{1, -1};


        List<List<Integer>> bigList = new ArrayList<List<Integer>>();
        bigList.add(Arrays.asList(inta));
        bigList.add(Arrays.asList(intb));
        bigList.add(Arrays.asList(intc));

        System.out.println(ClosestXdestinations(3, bigList, 2));
        bigList.clear();

        inta = new Integer[]{1, 2};
        intb = new Integer[]{-3, 0};
        intc = new Integer[]{1, 2};
        Integer[] intd = new Integer[]{7, 8};
        Integer[] inte = new Integer[]{-1, 22};
        Integer[] intf = new Integer[]{11, 12};


        //List<Integer> l = Arrays.asList(inta);
        bigList = new ArrayList<List<Integer>>();
        bigList.add(Arrays.asList(inta));
        bigList.add(Arrays.asList(intb));
        bigList.add(Arrays.asList(intc));
        bigList.add(Arrays.asList(intd));
        bigList.add(Arrays.asList(inte));
        bigList.add(Arrays.asList(intf));


        System.out.println(ClosestXdestinations(6, bigList, 0));
        //////////////////////
        List<List<Integer>> forwardRouteList = new ArrayList<List<Integer>>();
        inta = new Integer[]{1, 2000};
        intb = new Integer[]{2, 4000};
        intc = new Integer[]{3, 6000};
        forwardRouteList.add(Arrays.asList(inta));
        forwardRouteList.add(Arrays.asList(intb));
        forwardRouteList.add(Arrays.asList(intc));


        List<List<Integer>> returnRouteList = new ArrayList<List<Integer>>();
        inta = new Integer[]{1, 2000};
        returnRouteList.add(Arrays.asList(inta));


        System.out.println(optimalUtilization(7000, forwardRouteList, returnRouteList));
        forwardRouteList.clear();
        returnRouteList.clear();


        ///////////////////
        forwardRouteList = new ArrayList<List<Integer>>();
        inta = new Integer[]{1, 3000};
        intb = new Integer[]{2, 5000};
        intc = new Integer[]{3, 7000};
        intd = new Integer[]{4, 10000};
        forwardRouteList.add(Arrays.asList(inta));
        forwardRouteList.add(Arrays.asList(intb));
        forwardRouteList.add(Arrays.asList(intc));
        forwardRouteList.add(Arrays.asList(intd));


        returnRouteList = new ArrayList<List<Integer>>();
        inta = new Integer[]{1, 2000};
        intb = new Integer[]{2, 3000};
        intc = new Integer[]{3, 4000};
        intd = new Integer[]{4, 5000};


        returnRouteList.add(Arrays.asList(inta));
        returnRouteList.add(Arrays.asList(intb));
        returnRouteList.add(Arrays.asList(intc));
        returnRouteList.add(Arrays.asList(intd));


        System.out.println(optimalUtilization(10000, forwardRouteList, returnRouteList));

        //should return 2,4 and 3,2


        fizzBuzz();


        //timing test
        long start1 = System.currentTimeMillis();
        for (i = 0; i < 10; i++) {
            fizzBuzz();
        }
        long end1 = System.currentTimeMillis();

        System.out.println("time(ms)=" + (end1 - start1));
        //1854

        //old=1777
        System.out.println(fizzBuzzInJava8(100));

        intStreams();


        System.out.println(Arrays.toString(breakingRecords(new int[]{10, 5, 20, 20, 4, 5, 2, 25, 1})));


        System.out.println(Arrays.toString(breakingRecords(new int[]{3, 4, 21, 36, 10, 28, 35, 5, 24, 42})));

        System.out.println(birthday(Arrays.asList(1, 2, 1, 3, 2), 3, 2));

        System.out.println(birthday(Arrays.asList(1, 1, 1, 1, 1, 1), 3, 2));

        Animal dog = new Dog();
        System.out.println(dog.getGreeting());

        Animal cow = new Cow();
        System.out.println(cow.getGreeting());

        Animal duck = new Duck();
        System.out.println(duck.getGreeting());

        superStack s = new superStack();
        superStack.superStack(new String[]{
                "push 1",
                "pop",
                "push 3",
                "push 4",
                "push 5",
                "inc 3 1",
                "pop",
                "push 9"});


        class threadTest implements Runnable {
            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            public void run() {
                SingletonThreadSafety.getInstance().setName("karl");
            }
        }

        threadTest thread1 = new threadTest();
        Thread th1 = new Thread(thread1);
        System.out.println("thread1=" + thread1.toString());
        threadTest thread2 = new threadTest();
        Thread th2 = new Thread(thread2);

        System.out.println("thread2=" + thread2.toString());

        th1.start();
        th2.start();

        //SingletonThreadSafety.getInstance().setName("karl");
        System.out.println(SingletonThreadSafety.getInstance().getName());


        ImmutableClass c = new ImmutableClass(i, test, null);

        System.out.println(divisibleSumPairs(6, 3, new int[]{1, 3, 2, 6, 1, 2}));


        RemoveLoopInLinkedList list3 = new RemoveLoopInLinkedList();
        RemoveLoopInLinkedList.head = new Node(50);
        RemoveLoopInLinkedList.head.next = new Node(20);
        RemoveLoopInLinkedList.head.next.next = new Node(15);
        RemoveLoopInLinkedList.head.next.next.next = new Node(4);
        RemoveLoopInLinkedList.head.next.next.next.next = new Node(10);

        list3.printList(RemoveLoopInLinkedList.head);

        // Creating a loop for testing
        RemoveLoopInLinkedList.head.next.next.next.next.next = RemoveLoopInLinkedList.head.next.next;


        list3.detectAndRemoveLoop(RemoveLoopInLinkedList.head);
        System.out.println("Linked List after removing loop : ");
        list3.printList(RemoveLoopInLinkedList.head);


        LeftRightNode lrn = new LeftRightNode(1, "1");
        lrn.left = new LeftRightNode(2, "two");
        lrn.right = new LeftRightNode(3, "three");
        lrn.right.left = new LeftRightNode(3, "three left");
        lrn.left.left = new LeftRightNode(4, "four");
        lrn.right.right = new LeftRightNode(5, "five");
        lrn.left.left.left = new LeftRightNode(6, "six");
        lrn.right.right.right = new LeftRightNode(7, "seven");

        //should return "five"
        System.out.println(lrn.findInt(lrn, 5));


        System.out.println(getMaxContigSubArray(new int[]{2, 6, 11, 54, -98, 0, 16, 45, -76, 42, 9}));

        char boggle[][] = {{'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};


        findWords(boggle);


        ReverseLinkedList list4 = new ReverseLinkedList();
        ReverseLinkedList.head = new ReverseLinkedList.Node(85);
        ReverseLinkedList.head.next = new ReverseLinkedList.Node(15);
        ReverseLinkedList.head.next.next = new ReverseLinkedList.Node(4);
        ReverseLinkedList.head.next.next.next = new ReverseLinkedList.Node(20);

        System.out.println("Given Linked list");
        list4.printList(ReverseLinkedList.head);
        ReverseLinkedList.head = list4.reverse(ReverseLinkedList.head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list4.printList(ReverseLinkedList.head);


        karlSuperStack(new String[]{"push 1", "pop", "push 3", "push 4", "push 5", "inc 3 1", "pop", "push 9"});

        SpecialTrueStack s2 = new SpecialTrueStack();
        s2.push(10);
        s2.push(20);
        s2.push(30);
        System.out.println(s2.getMin());
        s2.push(5);
        System.out.println(s2.getMin());

        System.out.println(RemoveDupsFromArray(new int[]{3, 4, 7, 2, 1, 9, 7, 6, 4, 5, 7, 8, 0, 2, 4, 3, 5, 6, 7, 8, 9, 0, 1, 2, 3}));

        System.out.println(Arrays.toString(RemoveDupsFromArrayWithSet(new Integer[]{3, 4, 7, 2, 1, 9, 7, 6, 4, 5, 7, 2, 4, 3, 5, 6, 7, 8, 1, 2, 3})));

        System.out.println(Arrays.toString(RemoveDupsFromArrayWithSet(new Integer[]{3, 4, 7, 2})));


        String str1 = reverseString("karlwashere");
        System.out.println(str1);
        str1 = reverseString(str1);

        //should get karlwashere
        System.out.println(queueString(str1));


        firstNonRepeatingChar("aaaabbbcdefghi");

        System.out.println(reverseRecursively("karlwashere"));


        scheduledService s4 = new scheduledService();
        // s4.beepForAnHour();

        //should be c
        System.out.println(getMostOccuringCharInString("abacdefgczxcy"));

        System.out.println(getMostOccuringCharInStringJava8("abacdefgczxcy"));


        migratoryBirds(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4}));

        //DaoFactory factory=new DaoFactory();

        Dao dao1 = DaoFactory.getInstance();

        DaoCustBean custBean = new DaoCustBean();
        custBean.setName("karl");
        dao1.insertCustomer(custBean);


        //should get 13.09.2017
        dayOfProgrammer(2017);

        //12.09.2016
        dayOfProgrammer(2016);

        dayOfProgrammer(1800);

        isPalindrome("ravan");
        isPalindrome("dad");
        isPalindrome("abcdefg");

        //should return nothing
        String s3 = "unicomp6.unicomp.net - - [01/Jul/1995:00:00:06 -0400] \\\"GET /shuttle/countdown/ HTTP/1.0\\\" 200 3985";
        System.out.println(getFileName(s3));

        //should return livevideo.gif
        s3 = "burger.letters.com - - [01/Jul/1995:00:00:12 -0400] \"GET /shuttle/countdown/video/livevideo.gif HTTP/1.0\" 200 0";
        System.out.println(getFileName(s3));


        testBankTransaction();


        //should print 5
        bonAppetit(Arrays.asList(new Integer[]{3, 10, 2, 9}), 1, 12);

        //should print bon appetit
        bonAppetit(Arrays.asList(new Integer[]{3, 10, 2, 9}), 1, 7);

        IObjectTest test1 = new IObjectTestImpl();
        //List l = new ArrayList
        List it = Arrays.asList(new Integer[]{3, 10, 2, 9});

        FilteringIterator f = new FilteringIterator(test1, it.iterator());
        System.out.println(f.hasNext());
        System.out.println(f.next());
        System.out.println(f.hasNext());
        System.out.println(f.next());
        System.out.println(f.next());
        System.out.println(f.next());
        System.out.println(f.next());
        System.out.println(f.next());


        //should be falsez
         String jsonStr = "{]}";
        System.out.println(jsonParser(jsonStr));

         jsonStr = "{{{aaaaa}}}";
        //should be true
        System.out.println(jsonParser(jsonStr));

        //should be false
        jsonStr = "{a{a{}fff}";
        System.out.println(jsonParser(jsonStr));

        //should be true
        jsonStr = "{aaa}{aaaa}";
        System.out.println(jsonParser(jsonStr));

        //should be false
        jsonStr = "}}}";
        System.out.println(jsonParser(jsonStr));

        //should be false
        jsonStr = "{{{";
        System.out.println(jsonParser(jsonStr));

        //should be true
        jsonStr = "{{{ [aa],,,() }}bbb}";
        System.out.println(jsonParser(jsonStr));

        //should be false
        jsonStr = "{{{ [aa],,,) }}bbb}";
        System.out.println(jsonParser(jsonStr));


        StockBuySell sbs = new StockBuySell();

        //should be 30
        System.out.println(sbs.profitLoss(new int[]{70, 80, 100}));

        //should be 30
        System.out.println(sbs.profitLoss(new int[]{80, 70, 90, 100}));

        //should be 65
        System.out.println(sbs.profitLoss(new int[]{70, 80, 75, 100, 60, 90}));

        //should be 35
        System.out.println(sbs.profitLoss(new int[]{80, 90, 100, 70, 80, 90}));


        System.out.println(sbs.profitLoss(new int[]{70, 80, 100, 90, 60, 110, 80}));
        System.out.println(sbs.profitLoss(new int[]{170, 80, 100, 60, 90, 70, 40, 50}));


        //should be 0 since no way to make any money.
        System.out.println(sbs.profitLoss(new int[]{100, 90, 80, 70, 60, 50}));


        //should return aebdfhcg
        System.out.println(getZigZag("abcdefgh", 3));

        BinaryTree tree = new BinaryTree();
        Node2 node2 = new Node2(1);
        node2.left = new Node2(2);
        node2.right = new Node2(3);
        node2.left.left = new Node2(4);
        node2.left.right = new Node2(5);
        node2.left.left.left = new Node2(8);

        //should be unbalanced
        if (tree.isBalanced(node2))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");


        node2 = new Node2(1);
        node2.left = new Node2(2);
        node2.right = new Node2(3);
        node2.left.left = new Node2(4);
        node2.left.right = new Node2(5);
        node2.right.right = new Node2(6);
        node2.left.left.left = new Node2(7);

        //should be balanced
        if (tree.isBalanced(node2))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");


        System.out.println(hurdleRace(4, new int[]{1, 6, 3, 5, 2}));

        System.out.println(hurdleRace(7, new int[]{2, 5, 4, 5, 2}));

        charTest();

        //should be 28
        System.out.println(designerPdfViewer(new int[]{1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7}, "zaba"));


        //returns Cat B
        System.out.println(catAndMouse(1, 2, 3));

        //returns Mouse C
        System.out.println(catAndMouse(1, 3, 2));


        //return true
        System.out.println(isAllCharsUnique("abcdefghijk"));

        //return false
        System.out.println(isAllCharsUnique("abcdefghifjk"));

        //return true
        System.out.println(isOneAPermutationOfOther("def", "abcdabcdefg"));


        //return true
        System.out.println(isOneAPermutationOfOther("def", "abcdefg"));


        //should get ACE
        printNode(Remove(initNode(), new int[]{1, 3}, 2));
        System.out.println();
        //should get A
        printNode(Remove(initNode(), new int[]{1, 2, 3, 4}, 2));
        System.out.println();
        //should get ACDE
        printNode(Remove(initNode(), new int[]{1}, 2));
        System.out.println();
        //should get ABCDE
        printNode(Remove(initNode(), new int[]{5}, 2));
        System.out.println();
        //should get ABCD
        printNode(Remove(initNode(), new int[]{4}, 2));
        System.out.println();

        //should get 5
        System.out.println(pickingNumbers(Arrays.asList(new Integer[]{1, 2, 2, 3, 1, 2})));

        //should get 3
        System.out.println(pickingNumbers(Arrays.asList(new Integer[]{4, 6, 5, 3, 3, 1})));

        //should get 6
        System.out.println(pickingNumbers(Arrays.asList(new Integer[]{5, 5, 5, 5, 5, 5})));


        //100 90 90 80 75 60 scores
        //50 65 77 90 102  alice
        //should get back 6,5,4,2,1
        System.out.println(climbingLeaderboard(new int[]{100, 90, 90, 80, 75, 60}, new int[]{50, 65, 77, 90, 102}));

        //should get back 6,4,2,1
        System.out.println(climbingLeaderboard(new int[]{100, 100, 50, 40, 40, 20, 10}, new int[]{5, 25, 50, 120}));


        //timing test
        long start = System.currentTimeMillis();
        for (i = 0; i < 10000; i++) {
            climbingLeaderboard(new int[]{100, 90, 90, 80, 75, 60}, new int[]{50, 65, 77, 90, 102});
        }
        long end = System.currentTimeMillis();

        System.out.println("time(ms)=" + (end - start));


        WeatherData wd = new WeatherData();
        CurrentConditionsDisplay cd = new CurrentConditionsDisplay(wd);
        wd.setMeasurements(1, 2, 3);
        wd.setMeasurements(4, 5, 6);

	       /* 4 9 2
	        3 5 7
	        8 1 5
	        */
        //should return 1
        System.out.println(formingMagicSquare(new int[][]{{4, 9, 2}, {3, 5, 7}, {8, 1, 5}}));

	        /*
	     	5 3 4
			1 5 8
			6 4 2
	        */
        //should return 7
        System.out.println(formingMagicSquare(new int[][]{{5, 3, 4}, {1, 5, 8}, {6, 4, 2}}));


	        /*
				4 8 2
				4 5 7
				6 1 6
	        */
        //should return 4
        System.out.println(formingMagicSquare(new int[][]{{4, 8, 2}, {4, 5, 7}, {6, 1, 6}}));

        Person a = new Person();
        a.firstName = "karl";
        a.lastName = "weck";
        a.SSN = 123456;

        Person b = new Person();
        b.firstName = "karl";
        b.lastName = "wecka";
        b.SSN = 123456;

        Person c1 = new Person();
        c1.firstName = "karl";
        c1.lastName = "weckb";
        c1.SSN = 1234567;


        List<Person> list1 = new ArrayList<Person>();
        list1.add(b);
        list1.add(a);
        list1.add(c1);

        System.out.println(sortPersons(list1));

        hashMapPerfTests();


        Integer arr22[] = new Integer[]{12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        System.out.print("K'th smallest element is " + kthSmallest(arr22, 0, arr22.length - 1, k));

        Scheduler sch = new Scheduler();
        ScheduleClient sClient = new ScheduleClient();
        sch.ScheduleMe(sClient, 100);


        System.out.println(findMaxNonAdjNumbers(new int[]{5, 1, 1, 5}));
        System.out.println(findMaxNonAdjNumbers(new int[]{2, 4, 6, 2, 5}));


        //should return true

        System.out.println(anySumMatching(new int[]{10, 15, 3, 7}, 17));

        //should be 2
        System.out.println(findFirstMissingInteger(new Integer[]{3, 4, -1, 1}));

        //should be 3
        System.out.println(findFirstMissingInteger(new Integer[]{1, 2, 0}));

        printPairsWithDifferenceOfK(new int[]{1, 7, 5, 9, 2, 12, 3}, 2);

        //should return bcb
        System.out.println(getLongestSubstringWithKUniqueChars("abcba", 2));

        //should return aaaaabbbbbbbc
        System.out.println(getLongestSubstringWithKUniqueChars("aaaaaaabbbbbbccccccddddddabcdefg", 3));

        //should return cbaaaaa
        System.out.println(getLongestSubstringWithKUniqueChars("abcbaaaaa", 2));

    /*

10 10
1 1 1


5 9
2 3 4

3 6

9 1 1
7 7

4 2 1

3 3
1 9 2
     */
        assert taumBday(5, 9, 2, 3, 4) == 37;
        assert taumBday(3, 6, 9, 1, 1) == 12;
        assert taumBday(7, 7, 4, 2, 1) == 35;
        assert taumBday(3, 3, 1, 9, 2) == 12;
        assert taumBday(10, 10, 1, 1, 1) == 20;
        assert taumBday(3, 3, 1, 9, 2) == 12;
        assert taumBday(443463982, 833847400, 429734889, 628664883, 610875522) == 714782523241122198l;

        assert findDigits(1012) == 3;
        assert findDigits(12) == 2;

        assert squares(3, 9) == 2;
        assert squares(17, 24) == 0;
        assert squares(395151610, 407596310) == 311;
    }
    // Complete the findDigits function below.
    static int findDigits(int n) {
        String s=String.valueOf(n);
        int digit;
        int count=0;

        for (int i =0;i<s.length();i++)
        {
            digit=Integer.valueOf(s.substring(i,i+1));
            if (digit>0 && n%digit==0)
            {
                count++;
            }
        }

        return count;
    }

    static int squares(int a, int b) {

        int count = (int)Math.floor(Math.sqrt(b)) - (int)Math.ceil(Math.sqrt(a)) + 1;

        return count;


    }

    //should print (1, 3), (3, 5), (5, 7), (7, 9).
    private static void printPairsWithDifferenceOfK(int[] ints, int k) {
        Set<Integer> set = new HashSet();

        for (int i = 0; i < ints.length; i++) {
            if (set.contains(Math.abs(ints[i] - k))) {
                System.out.println("pair=(" + ints[i] + "," + (ints[i] - k) + ")");
            }

            if (set.contains(Math.abs(ints[i] + k))) {
                System.out.println("pair=(" + ints[i] + "," + (ints[i] + k) + ")");
            }
            set.add(ints[i]);
        }
    }

    //return first int not in array
    private static int findFirstMissingInteger(Integer[] ints) {
        Set<Integer> set = new HashSet();
        Collections.addAll(set, ints);

        for (int i = 1; i < ints.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return ints.length;
    }

    //return true if any 2 numbers in array match k
    private static boolean anySumMatchingBad(int[] ints, int k) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = i; j < ints.length; j++) {
                if (ints[i] + ints[j] == k) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean anySumMatching(int[] ints, int k) {
        boolean flag = false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < ints.length; i++) {
            if (set.contains(k - ints[i])) {
                return true;
            }
            set.add(ints[i]);
        }

        return false;
    }


    private static int findMaxNonAdjNumbers(int[] a) {
        int dp[] = new int[]{a[0], a[1]};

        for (int i = 2; i < a.length; i++) {
            int temp = dp[1];
            dp[1] = dp[0] + a[i];
            dp[0] = Math.max(dp[0], temp);
        }

        int ans = Math.max(dp[0], dp[1]);
        return ans;
    }


    private static class ScheduleClient implements ScheduleInterface {
        @Override
        public void Notify() {
            System.out.println("notified");
        }
    }


    private static void hashMapPerfTests() throws InterruptedException {
        final int TESTNUM = 1000;
        Map m1 = new HashMap();
        Map m2 = new WeakHashMap<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < TESTNUM; i++) {
            m1.put(i, "valueforkarl" + i);
            //System.out.println(m1.get(i));
        }
        //System.gc();
        System.out.println("m1 size=" + m1.size());

        long end = System.currentTimeMillis();
        System.out.println("hashmap time=" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < TESTNUM; i++) {
            m2.put(i, "valueforkarl" + i);
            //System.out.println(m2.get(i));
        }
        //System.gc();
        //Thread.sleep(2000);
        System.out.println("m2 size=" + m2.size());
        end = System.currentTimeMillis();

        System.out.println("weakhashmap time=" + (end - start));


        permutationEquation(new int[]{2, 3, 1});
        permutationEquation(new int[]{5, 2, 1, 3, 4});

        permutationEquation(new int[]{4, 3, 5, 1, 2});


    }

    private static LinkedListNode initNode() {
        LinkedListNode n1 = new LinkedListNode("A");
        n1.next = new LinkedListNode("B");
        n1.next.next = new LinkedListNode("C");
        n1.next.next.next = new LinkedListNode("D");
        n1.next.next.next.next = new LinkedListNode("E");
        return n1;
    }

    private static void printNode(LinkedListNode node) {
        LinkedListNode n = node;
        while (n != null) {
            System.out.print(n.name);

            n = n.next;
        }

    }

    private static void testBankTransaction() throws InterruptedException {
        Account ACCOUNT = new Account();
        Transaction TRANSACTION = new Transaction(ACCOUNT);
        int i;

        int threadsCount = 2;//Integer.parseInt(SCANNER.nextLine());
        Thread[] threads = new Thread[threadsCount];

        int expectedTransactionsCount = 0;
        for (i = 0; i < threadsCount; i++) {
            int transactionsCount = 3;
            expectedTransactionsCount += transactionsCount;

            threads[i] = new Thread(new TransactionRunnable(TRANSACTION, transactionsCount));
        }

        for (i = 0; i < threadsCount; i++) {
            threads[i].start();
        }

        for (i = 0; i < threadsCount; i++) {
            threads[i].join();
        }

        List<String> transactions = TRANSACTION.getTransaction();

        if (transactions.size() != expectedTransactionsCount) {
            System.out.println("Wrong Answer");
        } else {
            boolean correct = true;
            for (String transaction : transactions) {
                if (transaction == null) {
                    correct = false;

                    break;
                }
            }

            if (!correct) {
                System.out.println("Wrong Answer");
            } else {
                int balance = ACCOUNT.getBalance();

                if (balance < 0) {
                    System.out.println("Wrong Answer");
                } else {
                    for (String transaction : transactions) {
                        System.out.println(transaction);
                    }

                    System.out.println("Balance $" + balance);
                }
            }
        }


    }

    private static int getMaxRowCount(int[][] n) {
        int maxRow = 0;
        long maxRowSum = 0;
        long rowSum = 0;

        for (int i = 0; i < n.length; i++) {
            rowSum = Arrays.stream(n[i]).filter(x -> x == 1).count();
            if (rowSum > maxRowSum) {
                maxRowSum = rowSum;
                maxRow = i;
            }
        }
        return maxRow;
    }

    // A function to check if a given cell (row, col) can
    // be included in DFS
    private static boolean isSafe(int M[][], int row, int col, boolean visited[][]) {
        final int ROW = 5, COL = 5;
        // row number is in range, column number is in range
        // and value is 1 and not yet visited
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !visited[row][col]);
    }

    // A utility function to do DFS for a 2D boolean matrix.
    // It only considers the 8 neighbors as adjacent vertices
    private static void DFS(int M[][], int row, int col, boolean visited[][]) {
        // These arrays are used to get row and column numbers
        // of 8 neighbors of a given cell
        int rowNbr[] = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int colNbr[] = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        // Mark this cell as visited
        visited[row][col] = true;

        // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k)
            if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited))
                DFS(M, row + rowNbr[k], col + colNbr[k], visited);
    }

    private static int countIslands(int[][] M) {
        //No of rows and columns
        final int ROW = 5, COL = 5;

        // The main function that returns count of islands in a given
        // boolean 2D matrix

        // Make a bool array to mark visited cells.
        // Initially all cells are unvisited
        boolean visited[][] = new boolean[ROW][COL];

        // Initialize count as 0 and traverse through the all cells of given matrix
        int count = 0;
        for (int i = 0; i < ROW; ++i)
            for (int j = 0; j < COL; ++j)
                if (M[i][j] == 1 && !visited[i][j])
                // If a cell with value 1 is not visited yet, then new island found, Visit all
                // cells in this island and increment island count
                {
                    DFS(M, i, j, visited);
                    ++count;
                }

        return count;

    }

    private static void findLongestNoRepeatChars(String s) {
        String longSubStringSoFar = "";
        String tempLongSubStringSoFar = "";
        int i = 0;
        int j = 0;
        // String ret=s;

        while (i < s.length()) {
            j = i + 1;
            while (j < s.length()) {
                tempLongSubStringSoFar = s.substring(i, j);
                char repeatChar = anyRepeatingChars(tempLongSubStringSoFar);

                if (repeatChar != 0) {
                    tempLongSubStringSoFar = tempLongSubStringSoFar.substring(0, tempLongSubStringSoFar.length() - 1);

                    if (tempLongSubStringSoFar.length() > longSubStringSoFar.length()) {
                        longSubStringSoFar = tempLongSubStringSoFar;

                    }

                    i = s.indexOf(repeatChar, i) + 1;
                    j = i;

                }
                j++;
            }

            i++;
        }

        if (longSubStringSoFar.isEmpty()) {
            longSubStringSoFar = s;
        }
        System.out.println(longSubStringSoFar);

    }

    //karl returns false
    //karla returns true
    private static char anyRepeatingChars(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i), i + 1) > 0) {
                return s.charAt(i);
            }
        }
        return 0;
    }

    private static String getTime2(String s) {
        int hour = Integer.parseInt(s.substring(0, 2)) % 12;
        if (s.contains("PM"))
            hour += 12;

        s = String.format("%02d%s", hour, s.substring(2, 8));

        return s;

    }

    private static String getTime(String s) {
        String timer[] = s.split(":");

        for (int j = 0; j < timer.length; j++) {
            // System.out.println(timer[j]);
        }

        String ampm = timer[2].substring(timer.length - 1);
        int hour = new Integer(timer[0]);

        if (ampm.equals("PM")) {
            hour += 12;
        }

        if (hour >= 24)
            hour -= 12;

        if (hour == 12 && ampm.equals("AM"))
            hour = 0;


        String hourStr = Integer.toString(hour);

        if (hour == 0) {
            hourStr = "00";
        } else if (hourStr.length() == 1) {
            hourStr = "0" + hourStr;
        }
        String lastTime = timer[2].substring(timer.length - 3, 2);

        if (ampm.equals("PM")) {
            s = (hourStr + ":" + timer[1] + ":" + lastTime);
        } else {
            s = (hourStr + ":" + timer[1] + ":" + lastTime);
        }
        //System.out.println("lastTime="+lastTime);
        // System.out.println("hour="+hour);
        //  System.out.println();
        return s;
    }
}
