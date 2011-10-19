package com.leopin.parkfifty.admin.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.leopin.parkfifty.admin.unit.tests.AdminJsonUnitTests;
import com.leopin.parkfifty.admin.web.tests.AdminJsonWebTests;

@RunWith(Suite.class)
@SuiteClasses({
	AdminJsonWebTests.class,
	AdminJsonUnitTests.class})
public class AllTests {

}
