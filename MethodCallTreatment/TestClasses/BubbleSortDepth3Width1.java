public class BubbleSortDepth3Width1 {
	/*@ public normal_behavior
	  @ requires a.length == 5;
	  @ ensures (\forall int i; 0 <= i && i < a.length -1; a[i] <= a[i+1]);
	  @*/
	public void execute(int[] a){
		a1(a,0);

	}

	/*@ public normal_behavior
	  @ requires pos >= 0 && pos < a.length;
	  @ requires (pos > 0 ==>(\forall int y; 0 <= y && y < a.length - pos; a[y] <= a[a.length - pos]));
	  @ requires (\forall int z; a.length - pos <= z && z < a.length -1; a[z] <= a[z+1]);
	  @ ensures (\forall int o; 0 <= o && o < a.length - (1+pos); a[o] <= a[a.length - (1+pos)]);
	  @ ensures (\forall int p; a.length - (1+pos) <= p && p < a.length -1; a[p] <= a[p+1]);
	  @*/
	public void a0(int[] a, int pos){
		int b = 0;
		/*@ loop_invariant
		  @ 0 <= b && b < a.length - pos &&
		  @ (\forall int k; 0 <= k && k < b; a[k] <= a[b]) &&
		  @ (\forall int l; a.length - pos <= l && l < a.length -1; a[l] <= a[l+1]) &&
		  @ (pos > 0 ==> (\forall int m; 0 <= m && m < a.length - pos; a[m] <= a[a.length - pos]));
		  @ decreasing a.length - b;
		  @ assignable a[*];
		  @*/
		while(b < a.length-(1+pos)){
			if(a[b] > a[b+1]){
				int x = a[b];
				a[b] = a[b+1];
				a[b+1] = x;
			}
			b++;
		}

	}


	public void a1(int[] a, int pos){
		b1(a,pos+0);
		int b = 0;
		/*@ loop_invariant
		  @ 0 <= b && b < a.length - (pos + 3) &&
		  @ (\forall int k; 0 <= k && k < b; a[k] <= a[b]) &&
		  @ (\forall int l; a.length - (pos + 3) <= l && l < a.length -1; a[l] <= a[l+1]) &&
		  @ ((pos + 3) > 0 ==> (\forall int m; 0 <= m && m < a.length - (pos + 3); a[m] <= a[a.length - (pos + 3)]));
		  @ decreasing a.length - b;
		  @ assignable a[*];
		  @*/
		while(b < a.length-(1+(pos + 3))){
			if(a[b] > a[b+1]){
				int x = a[b];
				a[b] = a[b+1];
				a[b+1] = x;
			}
			b++;
		}

	}

	/*@ public normal_behavior
	  @ requires pos >= 0 && pos + 3 < a.length;
	  @ requires (pos > 0 ==>(\forall int y; 0 <= y && y < a.length - pos; a[y] <= a[a.length - pos]));
	  @ requires (\forall int z; a.length - pos <= z && z < a.length -1; a[z] <= a[z+1]);
	  @ ensures (\forall int o; 0 <= o && o < a.length - (3+pos); a[o] <= a[a.length - (3+pos)]);
	  @ ensures (\forall int p; a.length - (3+pos) <= p && p < a.length -1; a[p] <= a[p+1]);
	  @*/
	public void b1(int[] a, int pos){
		c1(a,pos+0);
		int b = 0;
		/*@ loop_invariant
		  @ 0 <= b && b < a.length - (pos + 2) &&
		  @ (\forall int k; 0 <= k && k < b; a[k] <= a[b]) &&
		  @ (\forall int l; a.length - (pos + 2) <= l && l < a.length -1; a[l] <= a[l+1]) &&
		  @ ((pos + 2) > 0 ==> (\forall int m; 0 <= m && m < a.length - (pos + 2); a[m] <= a[a.length - (pos + 2)]));
		  @ decreasing a.length - b;
		  @ assignable a[*];
		  @*/
		while(b < a.length-(1+(pos + 2))){
			if(a[b] > a[b+1]){
				int x = a[b];
				a[b] = a[b+1];
				a[b+1] = x;
			}
			b++;
		}

	}


	public void c1(int[] a, int pos){
		a0(a,pos+0);
		int b = 0;
		/*@ loop_invariant
		  @ 0 <= b && b < a.length - (pos + 1) &&
		  @ (\forall int k; 0 <= k && k < b; a[k] <= a[b]) &&
		  @ (\forall int l; a.length - (pos + 1) <= l && l < a.length -1; a[l] <= a[l+1]) &&
		  @ ((pos + 1) > 0 ==> (\forall int m; 0 <= m && m < a.length - (pos + 1); a[m] <= a[a.length - (pos + 1)]));
		  @ decreasing a.length - b;
		  @ assignable a[*];
		  @*/
		while(b < a.length-(1+(pos + 1))){
			if(a[b] > a[b+1]){
				int x = a[b];
				a[b] = a[b+1];
				a[b+1] = x;
			}
			b++;
		}

	}

}