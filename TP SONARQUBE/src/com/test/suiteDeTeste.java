package com.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatabaseConnectionTest.class,
        DatabaseServiceTest.class,
})
public class suiteDeTeste {
}
