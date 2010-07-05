package org.jtheque.films.stats.services.impl.utils;

import org.jtheque.utils.count.Counter;
import org.jtheque.utils.count.Counters;

import java.util.Map;

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
