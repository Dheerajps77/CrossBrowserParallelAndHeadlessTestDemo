package Groups;

import org.testng.annotations.Test;

public class GroupsDemo {
	
	
	@Test(priority=0,groups="Smoke")
	public void Test1()
	{
		try {
			System.out.println("I am in Test1");
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(priority=1,groups="Sanity")
	public void Test2()
	{
		try {
			System.out.println("I am in Test2");
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(priority=2,groups="Sanity")
	public void Test3()
	{
		try {
			System.out.println("I am in Test3");
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(priority=3,groups="Smoke")
	public void Test4()
	{
		try {
			System.out.println("I am in Test4");
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(priority=4,groups="Smoke")
	public void Test5()
	{
		try {
			System.out.println("I am in Test5");
		} catch (Exception e) {
			throw e;
		}
	}
}
