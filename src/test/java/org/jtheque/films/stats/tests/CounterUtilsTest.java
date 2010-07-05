package org.jtheque.films.stats.tests;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jtheque.films.stats.services.impl.utils.CounterUtils;
import org.jtheque.utils.count.Counter;
import org.jtheque.utils.count.Counters;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static junit.framework.Assert.*;

/**
 * Test the CounterUtils class.
 *
 * @author Baptiste Wicht
 */
public final class CounterUtilsTest {
    /**
     * Test the clearButGenerals() method.
     */
    @Test
    public void testClearButGenerals() {
        Map<String, Counters> counters = new HashMap<String, Counters>(3);

        counters.put("test1", new Counters());
        counters.put("generals", new Counters());
        counters.put("test2", new Counters());

        int added = new Random().nextInt();

        counters.get("generals").getCounterOrAdd("test").add(added);

        CounterUtils.clearButGenerals(counters);

        assertFalse(counters.containsKey("test1"));
        assertTrue(counters.containsKey("generals"));
        assertFalse(counters.containsKey("test2"));

        assertNotNull(counters.get("generals").getCounter("test"));

        assertEquals(counters.get("generals").getCounter("test").getValue(), added);
    }

    @Test
    public void testClear() {
        Collection<Counter> counters = new ArrayList<Counter>(2);

        Counter counter1 = new Counter();
        counter1.increment();
        counters.add(counter1);

        Counter counter2 = new Counter();
        counter2.increment();
        counters.add(counter2);

        CounterUtils.clear(counters);

        assertTrue(counter1.getValue() == 0);
        assertTrue(counter2.getValue() == 0);
    }
}
