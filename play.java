package automataFSA;

import java.io.IOException;

public class play {
	
	final static String dir = "/Users/shuoqiaoliu/Documents/workspace/automataFSA/src/automataFSA/";
	
	public static void main(String[] args) throws IOException {
		
		fsa f1 = new fsa(dir + "input1.txt");
		f1.fsaMachine();
		f1.showMeAnswer();
		fsa f2 = new fsa(dir + "input2.txt");
		f2.fsaMachine();
		f2.showMeAnswer();
		fsa f3 = new fsa(dir + "input3.txt");
		f3.fsaMachine();
		f3.showMeAnswer();
		fsa f4 = new fsa(dir + "input4.txt");
		f4.fsaMachine();
		f4.showMeAnswer();
		fsa f5 = new fsa(dir + "input5.txt");
		f5.fsaMachine();
		f5.showMeAnswer();
		
	}
	


}
