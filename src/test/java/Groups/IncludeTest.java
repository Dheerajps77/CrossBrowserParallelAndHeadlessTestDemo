package Groups;

import org.testng.annotations.Test;

public class IncludeTest {

	@Test(/*groups = "pre-tests"*/)
	public void init() {
		System.out.println("init resources");
	}

	@Test(/*groups = "tests", dependsOnGroups = "pre-tests"*/)
	public void insert() {
		System.out.println("inserting demo data");
	}

	@Test(/*dependsOnMethods = "insert", groups = "tests"*/)
	public void select() {
		System.out.println("selecting demo data");
	}
	
}
