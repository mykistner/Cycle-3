import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Do you choose rock (1), paper (2), or scissors (3)?");

		//1 = rock, 2 = paper, 3 = scissors
		int computerChoice = (int) (Math.random()*3+1);
		if(input.hasNextInt()){
			int choice = input.nextInt();
		if (choice == computerChoice){
			System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a Tie!");
			return;
		}
		if(choice==1){
			if(computerChoice==2){
				System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a computer win!");
			}
			else{
				System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a user win!");
			}
		}
		if(choice==2){
			if(computerChoice==3){
				System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a computer win!");
			}
			else{
				System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a user win!");
			}
		}
		if(choice==3){
			if(computerChoice==1){
				System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a computer win!");
			}
			else{
				System.out.println("User "+choice+" vs Computer "+computerChoice+": The result of the game was a user win!");
			}
		}
	}
		else{
			System.out.println("The input used is not valid");
		}
	}

}
