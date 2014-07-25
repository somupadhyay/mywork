package com.test.multi.main.jar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerformanceTest {

	/**
	 * @param args
	 */
	private static final long MEGABYTE = 1024L*1024L;
	
	public static long bytesToMegabytes(long bytes){
		return bytes/MEGABYTE;
	}
	public static void main(String[] args) {
		
		List<Person> persons = new ArrayList<Person>();
		for(int i=0;i<100000;i++){
			persons.add(new Person("Somnath", "Upadhyay"));
		}

		Runtime runtime = Runtime.getRuntime();
		
		//runtime.gc();
		
		long memory = runtime.totalMemory() - runtime.freeMemory();
		
		System.out.println("Used Memory is bytes: "+memory);
		System.out.println("Used Memory is megabytes: "+bytesToMegabytes(memory));
		
		for(Person person:persons){
			System.out.println(person.hashCode());
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter to exit");
		String str = scanner.next();
		System.out.println("presed key"+str);
	}

	static class Person{
		private final String firstName;
		private final String lastName;
		
		private Person(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result
					+ ((lastName == null) ? 0 : lastName.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			return true;
		}
		
		
	}
}
