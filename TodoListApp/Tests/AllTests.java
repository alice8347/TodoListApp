import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ EmptyListTest.class, ListDBTest.class, ListTest.class,
		UserDBTest.class })
public class AllTests {

}
