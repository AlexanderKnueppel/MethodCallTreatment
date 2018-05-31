public class Test {
	/*@ public normal_behavior
	  @ ensures \result == \old(i);
	  @*/
	public int a0(int i){
		return i;
	}

	/*@ public normal_behavior
	  @ requires i < 2147483635;
	  @ ensures \result == \old(i)+12;
	  @*/
	public int a1(int i){
		int j = b1(i);
		i = j+1;
		j = b1(i);
		i = j+1;
		j = b1(i);
		i = j+1;
		return i;
	}

	/*@ public normal_behavior
	  @ requires i < 2147483644;
	  @ ensures \result == \old(i)+3;
	  @*/
	public int b1(int i){
		int j = a0(i);
		i = j+1;
		j = a0(i);
		i = j+1;
		j = a0(i);
		i = j+1;
		return i;
	}
}