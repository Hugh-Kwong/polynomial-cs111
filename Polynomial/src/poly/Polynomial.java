package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node sum = new Node(0, 0, null);
		Node sumP = sum;
		try{
			while(poly1 != null || poly2 != null){
				if(poly1.term.degree < poly2.term.degree){
					Node sumL = new Node(poly1.term.coeff, poly1.term.degree, sumP.next);
					sumP.next = sumL;
					poly1 = poly1.next;
					sumP = sumP.next;
				
				}else if(poly1.term.degree > poly2.term.degree){
					Node sumG = new Node(poly2.term.coeff, poly2.term.degree, sumP.next);
					sumP.next = sumG;
					poly2 = poly2.next;
					sumP = sumP.next;
				}else if(poly1.term.degree == poly2.term.degree && poly1.term.coeff == -poly2.term.coeff){
					poly1 = poly1.next;
					poly2 = poly2.next;
				}
				else{
					Node sumF = new Node(poly1.term.coeff + poly2.term.coeff, poly1.term.degree, null);
					sumP.next = sumF;
					poly1 = poly1.next;
					poly2 = poly2.next;
					sumP = sumP.next;
				}
			}
		}
		catch(NullPointerException e){ 
			while(poly1 != null){
				Node sumT = new Node(poly1.term.coeff, poly1.term.degree, sumP.next);
				sumP.next = sumT;
				sumP = sumP.next;
				poly1 = poly1.next;
			}while(poly2 != null){
				Node sumT = new Node(poly2.term.coeff, poly2.term.degree, sumP.next);
				sumP.next = sumT;
				sumP = sumP.next;
				poly2 = poly2.next;
			}
			sorted(sum);
			return sum.next;
		}
		sorted(sum);
		return sum.next;
	}	
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
	// 	/** COMPLETE THIS METHOD **/
	// 	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
	// 	// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	// 	Node product = new Node(0, 0, null);
	// 	Node p1 = poly1;
	// 	Node p2 = poly2;
	// 	Node p3 = product;
	// 	if(p1 == null || p2 == null){
	// 		return null;
	// 	}
	// 	while(p1 != null){
	// 		Node second = new Node (0, 0, null);
	// 		Node secP = second;
	// 		while(p2 != null){
	// 			Node mult = new Node(p1.term.coeff*p2.term.coeff, p1.term.degree + p2.term.degree, null);
	// 			secP.next = mult;
	// 			p2 = p2.next;
	// 			}
	// 		product = add(second, product);
	// 		p2 = poly2;
	// 		p1 = p1.next;
		
	// 		}
			
	// 	// liketerms(product);
	// 	// sorted(product);
	// 	if(product.term.coeff == 0 && product.term.degree == 0){
	// 		return product.next;
	// 	}
	// 	return product;
	// }	
		
	if ( poly1 == null || poly2 == null ){
		return null;
	}

	Node product = new Node (0, 0, null);
	Node poly2Head = poly2;

	

	while ( poly1 != null ){
		Node second = new Node (0, 0, null);
		Node otherFirst = second;
		Node secondPrev = new Node (0, 0, null);

		while ( poly2 != null ){
			
			second.term.coeff = poly1.term.coeff * poly2.term.coeff;
			second.term.degree = poly1.term.degree + poly2.term.degree;
			
			System.out.println(second.term.coeff + "x^" + product.term.degree);

			second.next = new Node(0, 0, null);
			secondPrev = second;
			second = second.next;

			poly2 = poly2.next;
			
		}
		
		//System.out.println("Nested loop ended");

		secondPrev.next = null;
		second = otherFirst;

		product = add(second, product);
		

		poly1 = poly1.next;
		poly2 = poly2Head;

	}

	//case 1 requires product.next
	//case 2 and 3 requires product
	//case 5 has a null pointer exception
	if ( product.term.coeff == 0 && product.term.degree == 0 ){
		return product.next;
	}
	
	return product;
}
	
		
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float evaluate = 0;
		while ( poly != null ){
			evaluate += poly.term.coeff * Math.pow(x, poly.term.degree);
			poly = poly.next;
		}
		return evaluate;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	

	private static void liketerms(Node poly1) 
	{ 
		Node ptr1, ptr2; 
		ptr1 = poly1; 
	 
		while (ptr1 != null && ptr1.next != null) { 
			ptr2 = ptr1; 
			while (ptr2.next != null) { 
				if (ptr1.term.degree == ptr2.next.term.degree) { 	 
					ptr1.term.coeff = ptr1.term.coeff + ptr2.next.term.coeff;  
					ptr2.next = ptr2.next.next; 
	 
				} 
				else
					ptr2 = ptr2.next; 
			} 
			ptr1 = ptr1.next; 
		} 
	} 
	private static void sorted(Node poly1){
		Node c, n, p, p2;
		c = poly1;
		p = null;
		p2 = null;
		while(c !=null && c.next != null){
			p = c;
			n = c.next;
			while(n.next != null){
				if(c.term.degree > n.term.degree){
					Node next = new Node(n.term.coeff, n.term.degree, c);
					p2.next = next;
					p.next = p.next.next;
					p = p.next;
					n = n.next;
					 
				}else{
					p = p.next;
					n = n.next;
				}
			}
			p2 = c;
			c = c.next;
			
		}
		
	}
} 

