package com.leopin.parkfifty.test.admin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.leopin.parkfifty.unittest.admin.json.AdminJsonUnitTests;
import com.leopin.parkfifty.webtest.admin.json.AdminJsonWebTests;

@RunWith(Suite.class)
@SuiteClasses({
	AdminJsonWebTests.class,
	AdminJsonUnitTests.class})
public class AllTests {

}
