public class Day15 {

	public static void main(String[] args) {
		
		long valA = 618;
		long valB = 814;
		long a = 16807;
		long b = 48271;	
		long matchNmbr = 0;
		for(int i=0;i<5*1000000;i++){
			
			valA = (valA * a) % 2147483647;	
			valB = (valB * b) % 2147483647;

			while(valA % 4 != 0) {
				valA = (valA * a) % 2147483647;
			}
			while( valB % 8 != 0) {
				valB = (valB * b) % 2147483647;
			}
 			String ba = Long.toBinaryString(valA);
			String bb = Long.toBinaryString(valB);
			boolean match = true;
			
			while(ba.length()<32){
				ba = "0"+ba;
			}
			while(bb.length()<32) {
				bb = "0"+bb;
			}
			
			for(int j = 0;j<16;j++){
				if ( ba.charAt(ba.length()-j-1) != bb.charAt(bb.length()-j-1)) {
					match = false;
					break; 
				}
			}
			if (match) {
				matchNmbr++;
			}
		}
		System.out.println(matchNmbr);
	}
}
