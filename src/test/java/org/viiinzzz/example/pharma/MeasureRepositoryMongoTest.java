package org.viiinzzz.pharma;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
// or, to load only beans involved with MongoDB + MeasureRepositoryTester
// @DataMongoTest(includeFilters = @ComponentScan.Filter(type =
// FilterType.ASSIGNABLE_TYPE, classes = MeasureRepositoryTester.class))
@ActiveProfiles("mongodb")
public class MeasureRepositoryMongoTest {

    @Autowired
    private MeasureRepositoryTester measureRepositoryTester;

    @Test
    public void save_and_find() {
        measureRepositoryTester.save_and_find();
    }
}
