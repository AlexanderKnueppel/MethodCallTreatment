public class ParserTestClass2 {
	/*@ public normal_behavior
	  @ ensures \result == \old(i);
	  @*/
	public int tailLamp(int i){
		return i;
	}


	public int a1(int i){
		int j = b1(i);
		i = j+1;
		j = middleLamp(i);
		i = j+1;
		j = b3(i);
		i = j+1;
		return i;
	}


	public int b1(int i){
		int j = c1(i);
		i = j+1;
		j = c2(i);
		i = j+1;
		j = c3(i);
		i = j+1;
		return i;
	}


	public int middleLamp(int i){
		int j = c4(i);
		i = j+1;
		j = c5(i);
		i = j+1;
		j = c6(i);
		i = j+1;
		return i;
	}


	public int b3(int i){
		int j = c7(i);
		i = j+1;
		j = c8(i);
		i = j+1;
		j = c9(i);
		i = j+1;
		return i;
	}


	public int c1(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}

	
	public int c2(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}


	public int c3(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}

	/*@ public normal_behavior
	  @ requires i < 2147483644;
	  @ ensures \result == \old(i)+3;
	  @*/
	public int c4(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}


	public int c5(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}

	/*@ public normal_behavior
	  @ requires i < 2147483644;
	  @ ensures \result == \old(i)+3;
	  @*/
	public int c6(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}


	public int c7(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}

	/*@ public normal_behavior
	  @ requires i < 2147483644;
	  @ ensures \result == \old(i)+3;
	  @*/
	public int c8(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}

	/*@ public normal_behavior
	  @ requires i < 2147483644;
	  @ ensures \result == \old(i)+3;
	  @*/
	public int c9(int i){
		int j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		j = tailLamp(i);
		i = j+1;
		return i;
	}

}