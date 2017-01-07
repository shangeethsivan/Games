import java.util.*;
import java.util.Random;
import java.io.*;

class XO{
public static final int MAX=3;
public static final char HUMAN='X';
public static final char COMPUTER='O';
public static int turn=0;

	static void init(){
		System.out.println("The Computer will start the game. Computer will play first. \nYOU are \"X\" \n Please select from the following numbers. \n For placing your position ");
		System.out.println("\t\t\t  1 | 2 | 3 ");
		System.out.println("\t\t\t --- --- ---");
		System.out.println("\t\t\t  4 | 5 | 6 ");
		System.out.println("\t\t\t --- --- ---");
		System.out.println("\t\t\t  7 | 8 | 9 ");
	}
	public static void start_game(){
		Game game=Game.getInstance();
		game.printgrid();
	}
	public static void main(String[] args){
		init();
		System.out.println("Press any Number to start Game")
		Scanner sn=new Scanner(System.in);
		if(sn.hasNextInt()){
		start_game();
		}
	}

static class Game{
	char a[][];
	int arr[];
	public static Game game;
	Boolean full=false;
	public Game(){
		a=new char[MAX][MAX];
		arr=new int[]{0,0,0,0,0,0,0,0,0};
	}	

	public static Game getInstance(){
		if(game==null){
			game=new Game();
		}
		return game;
	}
	public void n_game(){
		a=new char[MAX][MAX];
		arr=new int[]{0,0,0,0,0,0,0,0,0};
		full=false;
		Scanner sn=new Scanner(System.in);
		System.out.println("Press 1 to start New Game or other key to exit");
		if(sn.hasNextInt() && sn.nextInt()==1){
			printgrid();
		}
	}


	public void printgrid(){
		System.out.printf("\n\n\t\t\t  %c | %c | %c\n",a[0][0],a[0][1],a[0][2]);
		System.out.println("\t\t\t --- --- ---");
		System.out.printf("\t\t\t  %c | %c | %c\n",a[1][0],a[1][1],a[1][2]);
		System.out.println("\t\t\t --- --- ---");
		System.out.printf("\t\t\t  %c | %c | %c\n",a[2][0],a[2][1],a[2][2]);

		if(check_result()||full){
			if(check_result()==false){
				System.out.println("Match draw");
			}else{
			System.out.println("Winner Declared");
			if(turn==1){
				System.out.println("COMPUTER WINS");
			}
			else{
			System.out.println("YOU WIN");	
			}
		}
			n_game();
		}else{
			if(turn==0){
				System.out.println("Computer's Turn");
				play_computer();
			}
			else{
		System.out.println("Press the number to continue");
		Scanner sn=new Scanner(System.in);
		if(sn.hasNextInt()){
			int n=sn.nextInt();
			if(check_number(n)){
				turn=0;
				load_value(n,HUMAN);
			}
			else{
				System.out.println("Already filled or invalid value\n");
				printgrid();
			}
		}
		else {
			System.out.println("Bad Input");
			printgrid();
		}
	}
	}}

	void play_computer(){
		Random rand=new Random();
		int n=rand.nextInt(9)+1;
		if(check_number(n)){
				turn=1;
				load_value(n,COMPUTER);
		}
		else{
			play_computer();
		}
	}


	Boolean check_number(int n){
		if(n>=1 &&n<=9){
			if(arr[n-1]==0){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	Boolean check_result(){
		int count=0;
		for(int a:arr){
			if(a==1){
				count++;
			}
		}
		if(count==9){
			full=true;
		}
		// Rows Crossed
		for(int i=0;i<3;i++){
			if(a[i][0]==a[i][1] && a[i][1]==a[i][2] &&( a[i][0]=='X'||a[i][0]=='O'))
			{
				return true;
			}
		}

		//Columns Crossed
		for(int i=0;i<3;i++){
			if(a[0][i]==a[1][i] && a[1][i]==a[2][i] && (a[0][i]=='X'||a[0][i]=='O')){
				return true;
			}
		}

		//Diagonals Crossed
		if(a[0][0]==a[1][1]&& a[1][1]==a[2][2] && (a[0][0]=='X'||a[0][0]=='O')){
			return true;
		}
		else if(a[0][2]==a[1][1]&& a[1][1]==a[2][0] && (a[0][2]=='X'||a[0][2]=='O'))
		{
			return true;
		}
		return false;
	}
	void load_value(int n,char player){
		
		switch(n){
			case 1:
				arr[0]=1;
				a[0][0]=player;
				break;	
			case 2:
				arr[1]=1;
				a[0][1]=player;
				break;
			case 3:
				arr[2]=1;
				a[0][2]=player;
				break;
			case 4:
				arr[3]=1;
				a[1][0]=player;
				break;
			case 5:
				arr[4]=1;
				a[1][1]=player;
				break;
			case 6:
				arr[5]=1;
				a[1][2]=player;
				break;
			case 7:
				arr[6]=1;
				a[2][0]=player;
				break;
			case 8:
				arr[7]=1;
				a[2][1]=player;
				break;
			case 9:
				arr[8]=1;
				a[2][2]=player;
				break;
		}

		printgrid();
	
	}
}


}