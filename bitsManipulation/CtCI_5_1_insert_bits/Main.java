public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    int N = 0b11111111;
	    int M = 0b11001001;

	    int res = s.insert(N, M, 4, 8);
		int res2 = s.insert(N, M, 0, 8);
		int res3 = s.insert(N, M, 8, 9);

	    System.out.println(Integer.toBinaryString(res));
		System.out.println(Integer.toBinaryString(res2));
		System.out.println(Integer.toBinaryString(res3));

	}
}
