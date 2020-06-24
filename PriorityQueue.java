package minheap;

import java.util.Arrays;

/*
 * Made a priority queue in the Array Queue class in project 3 for reference
 */
public class PriorityQueue {
	
	int[] heap;
	int size;
	/*
	 * {6, 7, 3, 4, 6, 0, 0}
	 * 
	 * parent --> (i - 1) /2
	 * left child --> (i * 2) + 1
	 * right child --> (i * 2) + 2
	 * 
	 */
	public PriorityQueue(int maxSize) {
		heap = new int[maxSize]; size =0;
	}
	public void push(int value) {
		/* if its full throw an error */
		if(size == heap.length) {throw new IllegalStateException("Queue is full! Can't push anything.");}
		//put the value into the heap
		/* put the value into the heap
		 * bubbling UP using a while loop to check if parent value is smaller or bigger
		 */
		int pos = size;
		heap[pos] = value;
		size++;
		while(pos > 0) {
			/* see if parent is smaller than value */
			int parent = (pos + 1) / 2 - 1;
			if(heap[parent] >= heap[pos]) break;
				/* break out of loop because it's in the right spot and we don't need to do anything else */
			swapIndices(parent, pos);
			/* we have a new position, pos variable will eventually decrease over time so we can break out of loop
			 * and we just added another element so we need to increment our size
			 */
			pos = parent;
		}
		
	}
	public int pop() {
		if(size == 0) {throw new IllegalStateException("Empty queue! Can't pop anything.");}
		/* We have to return the root element in the heap, or the first element in the array in this case*/
		int toReturn = heap[0];
		/* Set the root equal to the last element in the array or heap
		 * Re-balance the priority queue, bubbling DOWN with a while loop
		 * and in this case our position starts at 0
		 */
		heap[0] = heap[size-1];
		size--;
		
		int pos = 0;
		/* if you went more than halfway down the array, there's no more children to swap with anyway so it's size/2 */
		while(pos < size/2) {
			int leftChild = pos * 2 + 1;
			int rightChild = leftChild + 1;
			/* it might not have 2 children, compare with left child if only 1 child, otherwise compare with both 
			 * so first check if right child exists
			 * */
			if (rightChild < size && heap[leftChild] < heap[rightChild]) {
				if (heap[pos] >= heap[rightChild]) break;
				swapIndices(pos, rightChild);
				/* update position */
				pos = rightChild;
			}
			else {
				if (heap[pos] >= heap[leftChild]) break;
				swapIndices(pos, leftChild);
				pos = leftChild;
			}
		}
		
		return toReturn;
	}
	public void swapIndices(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	public String toString() {
		for (int i =0; i<heap.length; i++) {
			System.out.print(heap[i] + " ");
		}
		return "";
	}
}
