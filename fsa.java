package automataFSA;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class fsa {
	private final int CURRENT_STATE_INDEX = 0;
	private final int NEXT_INPUT_INDEX = 1;
	private final int MOVED_STATE_INDEX = 2;
	
	private int number_of_states;
	private boolean[] final_states;
	private TreeSet<String> alphabet;
	private Map<String,Integer> transitions;
	private ArrayList<String> keys;
	private ArrayList<String> input_strings;
	private ArrayList<String> answers;
	
	private void setNumberOfStates(String s){
		number_of_states = Integer.parseInt(s) ;
	}
	
	private void setFinalStates(String s){
		final_states = new boolean[number_of_states];
		String[] input = s.split(" ");
		for(int i=0,j=0;i<final_states.length && j<input.length;i++){
			int compare = Integer.parseInt(input[j]);
			if(i == compare){
				final_states[i] = true;
				j++;
			}
			else{
				final_states[i] = false;
			}
		}
	}
	
	private void setAlphabet(String s){
		alphabet = new TreeSet<>();
		String[] input = s.split(" ");
		for(int i=0;i<input.length;i++){
			alphabet.add(input[i]);
		}
	}
	
	private String[] getKey(String s){
		String r = s.replace("(", "").replace(")","");
		String [] result = r.split(" ");
		return result;
	}
	
	//return the first of the input
	private String setTransitions(Scanner in){
		String end = "";
		transitions = new HashMap<>();
		keys = new ArrayList<>();
		
		end = in.nextLine();
		while(in.hasNext()&& end.charAt(0) == '('){
			String[] allString = getKey(end);
			String key = allString[CURRENT_STATE_INDEX] + allString[NEXT_INPUT_INDEX];
			keys.add(key);
			transitions.put(key, Integer.parseInt(allString[MOVED_STATE_INDEX]));
			end = in.nextLine();
		};
		return end;
	}
	
	private void setInput(String firstInput,Scanner in){
		input_strings = new ArrayList<>();
		input_strings.add(firstInput);
		while(in.hasNextLine()){
			String line = in.nextLine();
			input_strings.add(line);
		}
	}
	
	public fsa(String fileName) throws IOException{
		
		File file = new File(fileName);
		Scanner in = new Scanner(file);
		
		// number of states
		setNumberOfStates(in.nextLine());
		// Final states
		setFinalStates(in.nextLine());
		// Alphabet
		setAlphabet(in.nextLine());
		// Transitions
		String firstInput = setTransitions(in);
		// Input
		setInput(firstInput,in);
	}
	
	public void fsaMachine(){
		// Rounds based on how many inputs we have
		answers = new ArrayList<>();
		for(int i=0;i<input_strings.size();i++){
			int state = 0;
			boolean reject = false;
			String org_input = input_strings.get(i);
			// & means empty 
			if(org_input.compareTo("&") != 0){
				char [] input = org_input.toCharArray();
				for(int j=0;j<input.length;j++){
					String character_of_the_input = Character.toString(input[j]);
					String key = Integer.toString(state) + character_of_the_input;
					// state + alpha = key = next state
					if(transitions.containsKey(key)){
						state = transitions.get(key);
					}
					else{
						reject = true;
						break;
					}
				}
			}
			// check the last state is at final state or not
			if(reject == true){
				answers.add("Reject");
			}
			else{
				if(final_states[state] == true){
					answers.add("Accept");
				}
				else{
					answers.add("Reject");
				}
			}
		}
	}
	
	public void showMeAnswer(){
		System.out.println("\n\n =================== Result ========================");
		for(int i =0;i<answers.size();i++){
			System.out.println(input_strings.get(i)+ ": "+ answers.get(i));
		}
	}
	
}
