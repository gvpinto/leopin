package com.leopin.parkfifty.admin.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.leopin.parkfifty.admin.tests.unit.server.AdminJsonUnitTests;
import com.leopin.parkfifty.admin.tests.web.AdminJsonWebTests;


@RunWith(Suite.class)
@SuiteClasses({
	AdminJsonWebTests.class,
	AdminJsonUnitTests.class})
public class AllTests {

}
