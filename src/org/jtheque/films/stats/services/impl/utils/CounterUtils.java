package org.jtheque.films.stats.services.impl.utils;

import org.jtheque.utils.count.Counter;
import org.jtheque.utils.count.Counters;

import java.util.Map;

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

/**
 * Utils class for the Counters.
 *
 * @author Baptiste Wicht
 */
public final class CounterUtils {
    /**
     * Create a new CounterUtils. This class in an utility class, it cannot be instantiated.
     */
    private CounterUtils() {
        super();
    }

    /**
     * Clear the map of all the counters but keep the generals counter.
     *
     * @param data The map of counters to clear.
     */
    public static void clearButGenerals(Map<String, Counters> data) {
        Counters generals = data.get(StatsCalculator.GENERALS);

        data.clear();

        data.put(StatsCalculator.GENERALS, generals);
    }

    /**
     * Clear all the counters.
     *
     * @param counters The counters to clear.
     */
    public static void clear(Iterable<Counter> counters) {
        for (Counter c : counters) {
            c.clear();
        }
    }
}