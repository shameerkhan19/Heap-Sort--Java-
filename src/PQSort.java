import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PQSort {

	private int PQAry[];
	private int capacity;
	private int size;
	private String args;
	private String args2;
	private String args3;
	private PrintWriter pw;
	private PrintWriter pw2;
	private Scanner scan;
	
	public PQSort(){};
	
	public PQSort(int x, String arg0, String arg1,String arg2) throws IOException{
		capacity=x+1;
		PQAry= new int[capacity];
		PQAry[0]=0;
		size=PQAry[0];
		args=arg0;
		args2=arg1;
		args3=arg2;
		scan= new Scanner(new FileReader(args));
		pw = new PrintWriter(new FileWriter(args2));
		pw2 = new PrintWriter(new FileWriter (args3));
	}
	

	public int getQueueSize(){
		return PQAry.length;
	}
	
	public boolean isEmpty(){
		if(PQAry[0]==0)
			return true;
		return false;
	}
	public int getSize(){
		return size;
	}
	
	public boolean isFull(){
		if (size==capacity)
			return true;
		return false;
	}
	
	public void buildPQAry()throws IOException{
		while(scan.hasNext()){
			int data= Integer.parseInt(scan.next());
			insertOneDataItem(data);
			printPQAry(pw,10);
		}
		scan.close();
		pw.close();
	}
	
	public void insertOneDataItem(int x){
		if(isFull()==true)throw new RuntimeException ("Full heap!");
		else{
			size=++PQAry[0];
			PQAry[size]=x;
			bubbleUp(size);
		}
	}
	public void bubbleUp(int kid ){
		int father=(kid/2);
		while(PQAry[father]>PQAry[kid] && father!=0){
			int temp;
			temp=PQAry[kid];
			PQAry[kid]=PQAry[father];
			PQAry[father]=temp;
			kid =father;
			father=kid/2;
		}
	}
	
	public void deleteRoot()throws RuntimeException, IOException{
		if(PQAry[0]==0)throw new RuntimeException("Empty Queue!");
		PrintWriter pw3= new PrintWriter(new FileWriter (args2,true));
		int root=PQAry[1];
		PQAry[1]=PQAry[PQAry[0]];
		PQAry[PQAry[0]]=root;
		PQAry[PQAry[0]]=0;
		size=--PQAry[0];
		pw2.print(root+" ");
		bubbleDown(1);
		printPQAry(pw3,10);
		if(isEmpty()){
		pw2.close();
		}
		pw3.close();
	}
	
	public void bubbleDown(int spot){
		int father=spot;
		int left=leftChild(father);
		int right=rightChild(father);
		int minIndex=0;
		if(left > size  && right > size)
			return;
		else{
		minIndex=locateMinIndex(left,right);
		while(PQAry[father] > PQAry[minIndex] && father<=(size/2)){
			int temp=PQAry[father];
			PQAry[father]=PQAry[minIndex];
			PQAry[minIndex]=temp;
			father=minIndex;
			left=leftChild(father);
			right=rightChild(father);
			if(left < size || right < size)
			minIndex=locateMinIndex(left,right);
			}
		}
	}
	
	private int leftChild(int father){
		return father*2;
	}
	private int rightChild(int father){
		return (father*2)+1;
	}
	
	public int locateMinIndex(int left, int right){
		if(left <=size && right >size)
			return left;
		if(PQAry[left]-PQAry[right]<0)
			return left;
		else 
			return right;
	}

	public void printPQAry(PrintWriter writer, int limit){
		for(int i=0;i<limit;i++){
		if(PQAry[i]!=0)
				writer.append(PQAry[i]+" ");
		}
		writer.println();
		writer.println();
	}
}
