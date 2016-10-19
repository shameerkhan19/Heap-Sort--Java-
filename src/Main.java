import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0;
		Scanner scan = null;
		try{
			scan=new Scanner(new FileReader(args[0]));
			while(scan.hasNext()){
				count++;
				scan.next();
			}
		PQSort newHeap = new PQSort(count,args[0], args[1],args[2]);
		newHeap.buildPQAry();
		while(!newHeap.isEmpty()){
			newHeap.deleteRoot();
		}
		scan.close();
		}
		catch(Exception e){
			System.out.println(e);
		}

	}

}
