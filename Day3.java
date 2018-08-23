public class Day3 {
	
	public static void main(String args[]){

		// check what spiral
		int input = Integer.parseInt(args[0]);
		int spiralnmbr = 0;
		int acc = 1;
		int corner = 1;
	
		while(input > corner){
			acc = acc + 2;
			corner = acc * acc;
			spiralnmbr++;
		}
	
		// find distance in spiral

		int dist = 2 * spiralnmbr;
		boolean in = true; 
		while(corner != input){
		
			if(in){
				if(dist > spiralnmbr){
					dist--;
				}
				else{
					in = false;
					dist++;
				}
			}	
			else{
				if(spiralnmbr * 2 > dist){
					dist++;
				}
				else{
					in = true;
					dist--;
				}
			}	
			corner--;		
		}
	System.out.println(dist);
	}

}

