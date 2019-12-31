package Groups;

import org.testng.annotations.Test;

public class TestNGDependencyOnMethodsExample {
	
	@Test
	public void insert() {
		System.out.println("inserting demo data");
	}
	
	@Test(dependsOnMethods="insert")
	public void select() {
		System.out.println("selecting demo data");
	}
	
	@Test(dependsOnMethods="select")
	public void update() {
		System.out.println("updating demo data");
	}
	
	@Test(dependsOnMethods={"update", "insert"})
	public void delete() {
		System.out.println("deleting demo data");
	}

}
