package org.jtheque.films.stats.tests;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
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
